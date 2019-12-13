package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.ShowDashboardUseCase;

import java.sql.*;

public class ShowDashboardUseCaseImpl implements ShowDashboardUseCase {
    public void execute() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql1 = "select status, count(status) as counter from scholarship group by status";
            preparedStatement = connection.prepareStatement(sql1);

            ResultSet rs1 = preparedStatement.executeQuery();
            System.out.println("________________________________________");
            System.out.printf("%-28s%s\n|======================================|\n", "|  status", "counter\t   |");
            while (rs1.next()) {
                String status = rs1.getString("status");
                int count = rs1.getInt("counter");
                System.out.printf("%-3s%-25s%-11d%s\n", "|", status, count, "|");
            }
            System.out.println("+======================================+\n");

            String sql2 = "select applyUni, count(applyUni) as counter from scholarship group by applyUni";
            preparedStatement = connection.prepareStatement(sql2);
            ResultSet rs2 = preparedStatement.executeQuery();
            System.out.println("________________________________________");
            System.out.printf("%-28s%s\n|======================================|\n", "|  apply university", "counter\t   |");
            while (rs2.next()) {
                String applyUni = rs2.getString("applyUni");
                int count = rs2.getInt("counter");
                System.out.printf("%-3s%-25s%-11d%s\n", "|", applyUni, count, "|");
            }
            System.out.println("+======================================+\n");

            String sql3 = "select applyDegree, count(applyDegree) as counter from scholarship group by applyDegree";
            preparedStatement = connection.prepareStatement(sql3);
            ResultSet rs3 = preparedStatement.executeQuery();
            System.out.println("________________________________________");
            System.out.printf("%-28s%s\n|======================================|\n", "|  apply Degree", "counter\t   |");
            while (rs3.next()) {
                String applyDegree = rs3.getString("applyDegree");
                int count = rs3.getInt("counter");
                System.out.printf("%-3s%-25s%-11d%s\n", "|", applyDegree, count, "|");
            }
            System.out.println("+======================================+\n");

            String sql4 = "select applyField, count(applyField) as counter from scholarship group by applyField";
            preparedStatement = connection.prepareStatement(sql4);
            ResultSet rs4 = preparedStatement.executeQuery();
            System.out.println("________________________________________");
            System.out.printf("%-28s%s\n|======================================|\n", "|  apply Field", "counter\t   |");
            while (rs4.next()) {
                String applyField = rs4.getString("applyField");
                int count = rs4.getInt("counter");
                System.out.printf("%-3s%-25s%-11d%s\n", "|", applyField, count, "|");
            }
            System.out.println("+======================================+\n");

        } catch (SQLException s) {
            s.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
