package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.ReceiveScholarshipByUniversityUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;

@Service
public class ReceiveScholarshipByUniversityUseCaseImpl implements ReceiveScholarshipByUniversityUseCase {
    public boolean receive(Long scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();

        boolean isIdExists = false;
        // connection
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();

            //checking the validity of id.......................................
            String sql1 = "select id from scholarship where status='AcceptedByManager'";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                Long dbId = rs.getLong("id");
                if (dbId == scholarshipId) {
                    isIdExists = true;
                }
            }
            //...................................................................


            // sql
            if (isIdExists) {
                String sql = "update scholarship set status = 'ReceivedByUniversity' " +
                        "where id = ?";
                // execute
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, scholarshipId);
                preparedStatement.executeUpdate();
                System.out.println("Done.");
            } else {
                System.out.println("ID NOT FOUND !!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isIdExists;
    }
}
