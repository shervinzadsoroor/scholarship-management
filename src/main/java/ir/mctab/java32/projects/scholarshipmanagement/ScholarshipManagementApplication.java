package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {
        try {


            Scanner scanner = new Scanner(System.in);
            String command = "";
            User user = null;
            while (!command.equalsIgnoreCase("exit")) {
                if (user == null) {
                    System.out.println("what do you want? (dashboard | login | exit) :");
                }
                //receive command from input
                command = scanner.nextLine();

                //checking the validity of command before login .............................
                if (user == null && !command.equalsIgnoreCase("exit") &&
                        !command.equalsIgnoreCase("login") && !command.equalsIgnoreCase("dashboard")) {
                    System.out.println("WRONG COMMAND !!!");
                }

                // Login .......................
                user = User.login(command, user);

                //_____________________________________________________________________________________________________________
                //show dashboard
                if (user == null && command.equalsIgnoreCase("dashboard")) {
                    DashboardUseCase dashboardUseCase = new DashboardUseCaseImpl();
                    dashboardUseCase.execute();
                }
                //_____________________________________________________________________________________________________________
                // find scholarship by supervisor
                if (user != null && command.equalsIgnoreCase("svlist") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase =
                            new FindScholarshipBySupervisorUseCaseImpl();
                    findScholarshipBySupervisorUseCase.find();
                }
                // accept by supervisor
                if (user != null && command.equalsIgnoreCase("svaccept") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                            = new AcceptScholarshipBySupervisorUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipId = scanner.nextLine();
                    acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                }
                // reject by supervisor
                if (user != null && command.equalsIgnoreCase("svreject") && user.getRole().equalsIgnoreCase("Supervisor")) {
                    RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase
                            = new RejectScholarshipBySupervisorUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipID = scanner.nextLine();
                    rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipID));
                }
                //_____________________________________________________________________________________________________________

                //find scholarship by manager .................
                if (user != null && command.equalsIgnoreCase("mglist") && user.getRole().equalsIgnoreCase("Manager")) {
                    FindScholarshipByManagerUseCase findScholarshipByManagerUseCase =
                            new FindScholarshipByManagerUseCaseImpl();
                    findScholarshipByManagerUseCase.find();
                }
                //accept by manager
                if (user != null && command.equalsIgnoreCase("mgaccept") && user.getRole().equalsIgnoreCase("Manager")) {
                    AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                            = new AcceptScholarshipByManagerUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipId = scanner.nextLine();
                    acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                }
                // reject by manager
                if (user != null && command.equalsIgnoreCase("mgreject") && user.getRole().equalsIgnoreCase("Manager")) {
                    RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase
                            = new RejectScholarshipByManagerUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String ScholarshipID = scanner.nextLine();
                    rejectScholarshipByManagerUseCase.reject(Long.parseLong(ScholarshipID));
                }
                //_____________________________________________________________________________________________________________

                //find scholarship by university...................
                if (user != null && command.equalsIgnoreCase("unilist") && user.getRole().equalsIgnoreCase("University")) {
                    FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase
                            = new FindScholarshipByUniversityUseCaseImpl();
                    findScholarshipByUniversityUseCase.find();
                }
                //receive by university
                if (user != null && command.equalsIgnoreCase("receive") && user.getRole().equalsIgnoreCase("University")) {
                    ReceiveScholarshipByUniversityUseCase receiveScholarshipByUniversityUseCase
                            = new ReceiveScholarshipByUniversityUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String ScholarshipID = scanner.nextLine();
                    receiveScholarshipByUniversityUseCase.receive(Long.parseLong(ScholarshipID));
                }

                //_____________________________________________________________________________________________________________

                //find by student
                if (user != null && command.equalsIgnoreCase("status") && user.getRole().equalsIgnoreCase("Student")) {
                    FindScholarshipByStudentUseCase findScholarshipByStudentUseCase
                            = new FindScholarshipByStudentUseCaseImpl();
                    System.out.println("Scholarship Id: ");
                    String scholarshipid = scanner.nextLine();
                    findScholarshipByStudentUseCase.find(Long.parseLong(scholarshipid));
                }

                //apply by student
                if (user != null && command.equalsIgnoreCase("apply") && user.getRole().equalsIgnoreCase("Student")) {
                    ApplyByStudentUseCase applyByStudentUseCase = new ApplyByStudentUseCaseImpl();
                    applyByStudentUseCase.apply();
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
