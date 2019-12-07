package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AcceptScholarshipBySupervisorUseCaseImpl implements AcceptScholarshipBySupervisorUseCase {
    public void accept(Long scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();

        if (user != null && user.getRole().equals("Supervisor")) {

            // connection
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                boolean isIdExists = false;

                //checking the validity of id.......................................
                String sql1 = "select id from scholarship where status='RequestedByStudent'";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                ResultSet rs =preparedStatement1.executeQuery();
                while (rs.next()){
                    Long dbId = rs.getLong("id");
                    if (dbId==scholarshipId){
                        isIdExists = true;
                    }
                }
                //...................................................................


                // sql
                if (isIdExists) {
                    String sql = "update scholarship set status = 'AcceptedBySupervisor' " +
                            "where id = ?";
                    // execute
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setLong(1, scholarshipId);
                    preparedStatement.executeUpdate();
                    System.out.println("Done.");
                }else {
                    System.out.println("ID NOT FOUND !!!");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
