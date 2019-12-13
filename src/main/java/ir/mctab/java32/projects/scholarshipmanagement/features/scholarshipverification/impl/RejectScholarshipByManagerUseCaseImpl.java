package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectScholarshipByManagerUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class RejectScholarshipByManagerUseCaseImpl implements RejectScholarshipByManagerUseCase {
    public boolean reject(Long scholarshipId) {
        boolean isIdExists = false;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            //checking the validity of id.......................................
            String sql1 = "select id from scholarship where status='AcceptedBySupervisor'";
            preparedStatement = connection.prepareStatement(sql1);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long dbId = rs.getLong("id");
                if (dbId == scholarshipId) {
                    isIdExists = true;
                }
            }
            //...................................................................
            connection = DatabaseConfig.getDatabaseConnection();
            statement = connection.createStatement();
            String sql = "update scholarship set status='RejectedByManager' where id=" + scholarshipId;
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIdExists;
    }
}
