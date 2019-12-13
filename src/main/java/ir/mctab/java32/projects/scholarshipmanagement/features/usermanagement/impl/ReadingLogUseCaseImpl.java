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
            String sql = "select log,date from log where user_role=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("log by role..........................................");
            while (rs.next()) {
                String log = rs.getString("log");
                String date = rs.getString("date");
                System.out.println(log + " -- " + date);
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
            String sql = "select log,date from log where date like ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date + "%");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("log by date..........................................");
            while (rs.next()) {
                String log = rs.getString("log");
                String Date = rs.getString("date");
                System.out.println(log + " -- " + Date);
            }
            System.out.println(".....................................................");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readingLogById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select log,date from log where scholarship_id= ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("log by id..........................................");
            while (rs.next()) {
                String log = rs.getString("log");
                String date = rs.getString("date");
                System.out.println(log + " -- " + date);
            }
            System.out.println(".....................................................");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
