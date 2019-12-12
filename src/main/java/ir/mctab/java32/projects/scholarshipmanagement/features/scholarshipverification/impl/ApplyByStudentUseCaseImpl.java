package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.ApplyByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.Scanner;
import java.sql.*;

@Service
public class ApplyByStudentUseCaseImpl implements ApplyByStudentUseCase {
    public void apply() {
        Scanner input = new Scanner(System.in);
        Scanner inputFloat = new Scanner(System.in);
        System.out.println("name:");
        String name = input.nextLine();
        System.out.println("family:");
        String family = input.nextLine();
        System.out.println("national code (10 digits):");
        String nationalCode = input.nextLine();
        System.out.println("last university:");
        String lastUni = input.nextLine();
        System.out.println("last degree:");
        String lastDegree = input.nextLine();
        System.out.println("last field:");
        String lastField = input.nextLine();
        System.out.println("last score:");
        float lastScore = inputFloat.nextFloat();
        System.out.println("apply university:");
        String applyUni = input.nextLine();
        System.out.println("apply degree:");
        String applyDegree = input.nextLine();
        System.out.println("apply field:");
        String applyField = input.nextLine();
        System.out.println("apply date:");
        String applyDate = input.nextLine();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "insert into scholarship (status,name,family,nationalCode,lastUni,lastDegree,lastField,lastScore," +
                    "applyUni,applyDegree,applyField,applyDate) values('RequestedByStudent',?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, family);
            preparedStatement.setString(3, nationalCode);
            preparedStatement.setString(4, lastUni);
            preparedStatement.setString(5, lastDegree);
            preparedStatement.setString(6, lastField);
            preparedStatement.setFloat(7, lastScore);
            preparedStatement.setString(8, applyUni);
            preparedStatement.setString(9, applyDegree);
            preparedStatement.setString(10, applyField);
            preparedStatement.setString(11, applyDate);

            boolean isApplySuccessfully = preparedStatement.execute();
            //log ..................................


            //todo checking the reason of not printing the massage
            if (isApplySuccessfully) {
                System.out.println("+------------------------+\n|  successfully applied  |\n+------------------------+\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


