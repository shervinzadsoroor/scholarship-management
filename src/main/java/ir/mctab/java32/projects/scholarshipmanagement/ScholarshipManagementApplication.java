package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.ReadingLogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.ReadingLogUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);
            String command = "";
            User user = null;
            while (!command.equalsIgnoreCase("exit")) {
                if (user == null) {
                    System.out.println("what do you want? (login | exit) :");
                }
                //receive command from input
                command = scanner.nextLine();

                //checking the validity of command before login .............................
                if (user == null && !command.equalsIgnoreCase("exit") &&
                        !command.equalsIgnoreCase("login")) {
                    System.out.println("WRONG COMMAND !!!");
                }

                // Login .......................
                user = User.login(command, user);

                //_____________________________________________________________________________________________________________
                //show dashboard
                if (((user != null) && command.equalsIgnoreCase("dashboard") &&
                        user.getRole().equalsIgnoreCase("Manager")) ||
                        ((user != null) && command.equalsIgnoreCase("dashboard") &&
                        user.getRole().equalsIgnoreCase("Supervisor"))) {
                    ShowDashboardUseCase dashboardUseCase = new ShowDashboardUseCaseImpl();
                    dashboardUseCase.execute();
                }
                //_____________________________________________________________________________________________________________
                // find scholarship by supervisor
                if (user != null && command.equalsIgnoreCase("svlist") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase =
                            new FindScholarshipBySupervisorUseCaseImpl();
                    findScholarshipBySupervisorUseCase.find();
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.writingLog(user, command);
                }
                // accept by supervisor
                if (user != null && command.equalsIgnoreCase("svaccept") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                            = new AcceptScholarshipBySupervisorUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipId = scanner.nextLine();
                    boolean isDone = acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                    if (isDone) {
                        LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                        logUseCase.writingLog(user, command, Long.parseLong(scholarshipId));
                    }
                }
                // reject by supervisor
                if (user != null && command.equalsIgnoreCase("svreject") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase
                            = new RejectScholarshipBySupervisorUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipID = scanner.nextLine();
                    boolean isDone = rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipID));
                    if (isDone) {
                        LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                        logUseCase.writingLog(user, command, Long.parseLong(scholarshipID));
                    }
                }
                //_____________________________________________________________________________________________________________

                //find scholarship by manager .................
                if (user != null && command.equalsIgnoreCase("mglist") && user.getRole().equalsIgnoreCase("Manager")) {
                    FindScholarshipByManagerUseCase findScholarshipByManagerUseCase =
                            new FindScholarshipByManagerUseCaseImpl();
                    findScholarshipByManagerUseCase.find();
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.writingLog(user, command);
                }
                //accept by manager
                if (user != null && command.equalsIgnoreCase("mgaccept") && user.getRole().equalsIgnoreCase("Manager")) {
                    AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                            = new AcceptScholarshipByManagerUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipId = scanner.nextLine();
                    boolean isDone = acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                    if (isDone) {
                        LogUseCase logUseCase = new LogUseCaseImpl();
                        logUseCase.writingLog(user, command, Long.parseLong(scholarshipId));
                    }
                }
                // reject by manager
                if (user != null && command.equalsIgnoreCase("mgreject") && user.getRole().equalsIgnoreCase("Manager")) {
                    RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase
                            = new RejectScholarshipByManagerUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String ScholarshipID = scanner.nextLine();
                    boolean isDone = rejectScholarshipByManagerUseCase.reject(Long.parseLong(ScholarshipID));
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.writingLog(user, command, Long.parseLong(ScholarshipID));
                }
                // show log to manager according to user role
                if (user != null && command.equalsIgnoreCase("log by role") && user.getRole().equalsIgnoreCase("Manager")) {
                    ReadingLogUseCase readingLogUseCase = new ReadingLogUseCaseImpl();
                    System.out.println("type the role you want to see their logs : ");
                    String role = scanner.nextLine();
                    readingLogUseCase.readingLogByRole(role);
                }
                // show log to manager according to date
                if (user != null && command.equalsIgnoreCase("log by date") && user.getRole().equalsIgnoreCase("Manager")) {
                    ReadingLogUseCase readingLogUseCase = new ReadingLogUseCaseImpl();
                    System.out.println("type the date you want to see the logs (yyyy/mm/dd): ");
                    String date = scanner.nextLine();
                    readingLogUseCase.readingLogByDate(date);
                }
                // show log to manager according to id
                if (user != null && command.equalsIgnoreCase("log by id") && user.getRole().equalsIgnoreCase("Manager")) {
                    ReadingLogUseCase readingLogUseCase = new ReadingLogUseCaseImpl();
                    System.out.println("enter the id you want to see the logs : ");
                    String id = scanner.nextLine();
                    readingLogUseCase.readingLogById(Long.parseLong(id));
                }
                //_____________________________________________________________________________________________________________

                //find scholarship by university...................
                if (user != null && command.equalsIgnoreCase("unilist") && user.getRole().equalsIgnoreCase("University")) {
                    FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase
                            = new FindScholarshipByUniversityUseCaseImpl();
                    findScholarshipByUniversityUseCase.find();
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.writingLog(user, command);
                }
                //receive by university
                if (user != null && command.equalsIgnoreCase("receive") && user.getRole().equalsIgnoreCase("University")) {
                    ReceiveScholarshipByUniversityUseCase receiveScholarshipByUniversityUseCase
                            = new ReceiveScholarshipByUniversityUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String ScholarshipID = scanner.nextLine();
                    boolean isDone = receiveScholarshipByUniversityUseCase.receive(Long.parseLong(ScholarshipID));
                    if (isDone) {
                        LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                        logUseCase.writingLog(user, command, Long.parseLong(ScholarshipID));
                    }
                }

                //_____________________________________________________________________________________________________________

                //find by student
                if (user != null && command.equalsIgnoreCase("status") && user.getRole().equalsIgnoreCase("Student")) {
                    FindScholarshipByStudentUseCase findScholarshipByStudentUseCase
                            = new FindScholarshipByStudentUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipid = scanner.nextLine();
                    findScholarshipByStudentUseCase.find(Long.parseLong(scholarshipid));
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.LogStringForFind(user, command);
                }

                //apply by student
                if (user != null && command.equalsIgnoreCase("apply") && user.getRole().equalsIgnoreCase("Student")) {
                    ApplyByStudentUseCase applyByStudentUseCase = new ApplyByStudentUseCaseImpl();
                    applyByStudentUseCase.apply();
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.LogStringForFind(user, command);

                }


                // logout
                if (command.equalsIgnoreCase("logout")) {
                    user = null;
                }

                //shows the appropriate message to user
                User.showOptionsForEachRole(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
