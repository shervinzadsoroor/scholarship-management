package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.ReadingLogUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class ReadingLogUseCaseImpl implements ReadingLogUseCase {
    @Override
    public void readingLogByRole(String role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select log from log where user_role=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("log by role..........................................");
            while (rs.next()) {
                String log = rs.getString("log");
                System.out.println(log);
            }
            System.out.println(".....................................................");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readingLogByDate(String date) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select log from log where date like ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date + "%");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("log by date..........................................");
            while (rs.next()) {
                String log = rs.getString("log");
                System.out.println(log);
            }
            System.out.println(".....................................................");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
