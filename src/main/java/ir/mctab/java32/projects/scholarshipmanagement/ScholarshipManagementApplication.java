package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.AcceptScholarshipByManagerUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.AcceptScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipByManagerUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipByManagerUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByManagerUseCase;
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
        while (!command.equalsIgnoreCase("exit")) {
            if (user == null) {
                System.out.println("what do you want? (exit | login) :");
            }
            //receive command from input
            command = scanner.nextLine();

            //checking the validity of command before login .............................
            if (user == null && !command.equalsIgnoreCase("exit") && !command.equalsIgnoreCase("login")) {
                System.out.println("WRONG COMMAND !!!");
            }

            // Login .......................
            user = User.login(command, user);

            //_____________________________________________________________________________________________________________
            // find scholarship by supervisor
            if (user!=null && command.equalsIgnoreCase("svlist") && user.getRole().equalsIgnoreCase("Supervisor")) {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase =
                        new FindScholarshipBySupervisorUseCaseImpl();
                findScholarshipBySupervisorUseCase.find(command, user);
            }
            // accept by supervisor
            if (user!=null && command.equalsIgnoreCase("svaccept") && user.getRole().equalsIgnoreCase("Supervisor")) {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
            }
            //_____________________________________________________________________________________________________________


            //find by manager .................
            if (user!=null &&  command.equalsIgnoreCase("mglist") && user.getRole().equalsIgnoreCase("Manager")) {
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase =
                        new FindScholarshipByManagerUseCaseImpl();
                findScholarshipByManagerUseCase.find(command,user);
            }


            //accept by manager
            if (user!=null && command.equalsIgnoreCase("mgaccept") && user.getRole().equalsIgnoreCase("Manager")) {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                        = new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
            }
            //_____________________________________________________________________________________________________________


            //find by student todo
            //apply by student todo


            // logout
            if (command.equalsIgnoreCase("logout")) {
                user = null;
            }

            //shows the appropriate message to user
            User.showOptionsForEachRole(user);

        }
    }
}
