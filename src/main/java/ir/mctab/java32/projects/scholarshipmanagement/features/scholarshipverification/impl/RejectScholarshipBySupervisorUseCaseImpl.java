package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.DashboardUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectScholarshipBySupervisorUseCase;

import java.sql.*;

@Service
public class RejectScholarshipBySupervisorUseCaseImpl implements RejectScholarshipBySupervisorUseCase {
    public void reject(Long id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            statement = connection.createStatement();
            String sql = "update scholarship set status='RejectedBySupervisor' where id=" + id;
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
