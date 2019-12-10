package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByStudentUseCase;

import java.sql.*;

@Service
public class FindScholarshipByStudentUseCaseImpl implements FindScholarshipByStudentUseCase {
    public void find(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select * from scholarship where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long Id = resultSet.getLong("id");
                String status = resultSet.getString("status");
                String name = resultSet.getString("name");
                String family = resultSet.getString("family");
                String nationalCode = resultSet.getString("nationalCode");
                String lastUni = resultSet.getString("lastUni");
                String lastDegree = resultSet.getString("lastDegree");
                String lastField = resultSet.getString("lastField");
                float lastScore = resultSet.getFloat("lastScore");
                String applyUni = resultSet.getString("applyUni");
                String applyDegree = resultSet.getString("applyDegree");
                String applyField = resultSet.getString("applyField");
                String applyDate = resultSet.getString("applyDate");

                System.out.printf("%-5s%-25s%-15s%-15s%-17s%-15s%-15s%-20s%-15s%-15s%-15s%-20s%s\n%s\n",
                        "id", "status", "name", "family", "national code", "last uni", "last degree", "last field",
                        "last score", "apply uni", "apply degree", "apply field", "apply date",
                        "-------------------------------------------------------------------------------------------" +
                                "-----------------------------------------------------------------------------------" +
                                "------------------------------");

                System.out.printf("%-5d%-25s%-15s%-15s%-17s%-15s%-15s%-20s%-15.2f%-15s%-15s%-20s%s\n",
                        Id, status, name, family, nationalCode, lastUni, lastDegree, lastField, lastScore, applyUni,
                        applyDegree, applyField, applyDate);
                System.out.println("-------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------" +
                        "------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
