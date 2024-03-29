package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByManagerUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindScholarshipByManagerUseCaseImpl implements FindScholarshipByManagerUseCase {

    public List<Scholarship> listScholarships() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList();
        if (loginUser != null) {
            if (loginUser.getRole().equalsIgnoreCase("Manager")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query

                    String sql1 = "select status from scholarship";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    ResultSet rs = preparedStatement1.executeQuery();
                    String status = null;
                    boolean isContainingAcceptedBySupervisor = false;
                    while (rs.next()) {
                        status = rs.getString("status");
                        if (status.equalsIgnoreCase("AcceptedBySupervisor")) {
                            isContainingAcceptedBySupervisor = true;
                        }
                    }
                    if (!isContainingAcceptedBySupervisor) {
                        System.out.println("NOTHING TO SHOW !!!");
                    } else if (isContainingAcceptedBySupervisor) {
                        String sql = "select * from scholarship where status = 'AcceptedBySupervisor' ";
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

    public void find() {
        FindScholarshipByManagerUseCase findScholarshipByManagerUseCase
                = new FindScholarshipByManagerUseCaseImpl();

        List<Scholarship> scholarships = findScholarshipByManagerUseCase
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
