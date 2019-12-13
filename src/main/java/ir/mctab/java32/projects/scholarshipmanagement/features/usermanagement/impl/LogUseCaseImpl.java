package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class LogUseCaseImpl implements LogUseCase {
    public String writingLogForAcceptAndReject(User user, String command, Long id) {

        String log = null;
        if (user.getRole().equalsIgnoreCase("Supervisor") && command.equalsIgnoreCase("svaccept")) {
            log = "accepted id " + id + " by supervisor - ";
        } else if (user.getRole().equalsIgnoreCase("Supervisor") && command.equalsIgnoreCase("svreject")) {
            log = "rejected id " + id + " by supervisor - ";
        } else if (user.getRole().equalsIgnoreCase("Manager") && command.equalsIgnoreCase("mgaccept")) {
            log = "accepted id " + id + " by manager - ";
        } else if (user.getRole().equalsIgnoreCase("Manager") && command.equalsIgnoreCase("mgreject")) {
            log = "rejected id " + id + " by manager - ";
        } else if (user.getRole().equalsIgnoreCase("University") && command.equalsIgnoreCase("receive")) {
            log = "received id " + id + " by university - ";
        }
        return log;

    }

    public String writingLogForFind(User user, String command) {

        String log = null;
        if (user.getRole().equalsIgnoreCase("Student") && command.equalsIgnoreCase("apply")) {
            log = "apply successfully by student - ";
        } else if (user.getRole().equalsIgnoreCase("Student") && command.equalsIgnoreCase("status")) {
            log = "status have been seen by student - ";
        } else if (user.getRole().equalsIgnoreCase("Supervisor") && command.equalsIgnoreCase("svlist")) {
            log = "scholarships seen by supervisor - ";
        } else if (user.getRole().equalsIgnoreCase("Manager") && command.equalsIgnoreCase("mglist")) {
            log = "scholarships seen by manager - ";
        } else if (user.getRole().equalsIgnoreCase("University") && command.equalsIgnoreCase("unilist")) {
            log = "scholarships seen by university - ";
        }
        return log;

    }

    public void writingLog(User user, String command, Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);


            String log = writingLogForAcceptAndReject(user, command, id);

            log = log + time;


            String sql = "insert into log(log,scholarship_id,user_role,date) values (?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log);
            preparedStatement.setLong(2, id);
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, time);
            preparedStatement.execute ();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writingLog(User user, String command) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);


            String log = writingLogForFind(user, command);

            log = log + time;

            String sql = "insert into log(log,user_role,date) values (?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, time);
            preparedStatement.execute();

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
