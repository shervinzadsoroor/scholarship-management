package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.AcceptScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        User user = null;
        while (!command.equals("exit")) {
            if (user == null) {
                System.out.println("what do you want? (exit | login) :");
            }
            command = scanner.nextLine();
            if (user==null && !command.equals("exit") &&!command.equals("login")){
                System.out.println("WRONG COMMAND !!!");
            }
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.printf("+----------------------------------+\n|  Login successful by %-12s|\n" +
                            "+----------------------------------+\n", user.getRole());
                }
            }
            // find scholarship by supervisor
            if (command.equals("svlist") && user.getRole().equals("Supervisor")) {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                        = new FindScholarshipBySupervisorUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            // the if below is between two if blocks because the result of the above if may be NOTHING TO SHOW
            // and the purpose is to keep the sequence of showing messages
            if (command.equals("logout")){
                user=null;
            }
            if (user !=null &&user.getRole().equals("Supervisor")) {
                System.out.println("what do you want? ( svlist | svaccept | logout):");
            }

            // accept
            if (command.equals("svaccept") && user.getRole().equals("Supervisor")) {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));

            }
        }
    }
}
