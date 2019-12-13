package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectScholarshipBySupervisorUseCase;

import java.sql.*;

@Service
public class RejectScholarshipBySupervisorUseCaseImpl implements RejectScholarshipBySupervisorUseCase {
    public boolean reject(Long scholarshipId) {
        Connection connection = null;
        Statement statement = null;
        boolean isIdExists = false;
        try {
            //checking the validity of id.......................................
            String sql1 = "select id from scholarship where status='RequestedByStudent'";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                Long dbId = rs.getLong("id");
                if (dbId == scholarshipId) {
                    isIdExists = true;
                }
            }
            //...................................................................
            if (isIdExists) {
                connection = DatabaseConfig.getDatabaseConnection();
                statement = connection.createStatement();
                String sql = "update scholarship set status='RejectedBySupervisor' where id=" + scholarshipId;
                statement.executeUpdate(sql);
            } else {
                System.out.println("ID NOT FOUND !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIdExists;
    }
}
