package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindScholarshipBySupervisorUseCaseImpl implements FindScholarshipBySupervisorUseCase {
    public List<Scholarship> listScholarships() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList();
        if (loginUser != null) {
            if (loginUser.getRole().equalsIgnoreCase("Supervisor")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query

                    String sql1 = "select status from scholarship";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    ResultSet rs = preparedStatement1.executeQuery();
                    String status = null;
                    boolean isContainingRequestedByStudent = false;
                    while (rs.next()) {
                        status = rs.getString("status");
                        if (status.equalsIgnoreCase("RequestedByStudent")) {
                            isContainingRequestedByStudent = true;
                        }
                    }
                    if (!isContainingRequestedByStudent) {
                        System.out.println("NOTHING TO SHOW !!!");
                    } else if (isContainingRequestedByStudent) {
                        String sql = "select * from scholarship where status = 'RequestedByStudent' ";
                        // result
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            Scholarship scholarship = new Scholarship(
                                    resultSet.getLong("id"),
                                    resultSet.getString("status"),
                                    resultSet.getString("name"),
                                    resultSet.getString("family"),
                                    resultSet.getString("nationalCode"),
                                    resultSet.getString("lastUni"),
                                    resultSet.getString("lastDegree"),
                                    resultSet.getString("lastField"),
                                    resultSet.getFloat("lastScore"),
                                    resultSet.getString("applyUni"),
                                    resultSet.getString("applyDegree"),
                                    resultSet.getString("applyField"),
                                    resultSet.getString("applyDate")
                            );
                            result.add(scholarship);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public void find(String command, User user) {
        FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                = new FindScholarshipBySupervisorUseCaseImpl();

        List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                .listScholarships();
        if (scholarships.size() > 0) {
            System.out.printf("%-5s%-25s%-15s%-15s%-17s%-15s%-15s%-20s%-15s%-15s%-15s%-20s%s\n%s\n",
                    "id", "status", "name", "family", "national code", "last uni", "last degree", "last field",
                    "last score", "apply uni", "apply degree", "apply field", "apply date",
                    "-------------------------------------------------------------------------------------------" +
                            "-----------------------------------------------------------------------------------" +
                            "------------------------------");
            for (int i = 0; i < scholarships.size(); i++) {
                scholarships.get(i).showScholarship();
            }
            System.out.println("-------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------" +
                    "------------------------------");

        }
    }

}
