package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    public User login(String username, String password) {
        // get connection
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            boolean isUsenameExists = false;
            boolean isPasswordExists = false;


            //checking the validity of username.......................
            String sql1 = "select username from user";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            ResultSet rsUsername = preparedStatement1.executeQuery();
            while (rsUsername.next()) {
                String dbUsername = rsUsername.getString("username");
                if (username.equals(dbUsername)) {
                    isUsenameExists = true;
                }
            }
            //...............................................................


            // checking the validity of password
            String sql2 = "select password from user";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            ResultSet rsPass = preparedStatement2.executeQuery();
            while (rsPass.next()) {
                String dbPass = rsPass.getString("password");
                if (dbPass.equals(password)) {
                    isPasswordExists = true;
                }
            }
            //......................................................

            if (isUsenameExists && isPasswordExists) {
                String sql = "select id, username, password, role from user where " +
                        " username = ? and password = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                    AuthenticationService.getInstance().setLoginUser(user);
                    return user;
                }
            } else {
                System.out.println("USERNAME OR PASSWORD DOESNT'T EXISTS !!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return result
        return null;
    }
}
