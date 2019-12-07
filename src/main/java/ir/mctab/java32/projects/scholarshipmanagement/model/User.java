package ir.mctab.java32.projects.scholarshipmanagement.model;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Entity;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Id;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;

import java.util.List;
import java.util.Scanner;

@Entity
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String role;

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static User login(String command, User user) {
        Scanner scanner = new Scanner(System.in);
        if (command.equalsIgnoreCase("login")) {
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
        return user;
    }


    public static void showOptionsForEachRole(User user){
        if (user != null && user.getRole().equalsIgnoreCase("Supervisor")) {
            System.out.println("what do you want? ( svlist | svaccept | logout):");
        }
        if (user != null && user.getRole().equalsIgnoreCase("Manager")) {
            System.out.println("what do you want? ( mglist | mgaccept | logout):");
        }
        if (user != null && user.getRole().equalsIgnoreCase("Student")) {
            System.out.println("what do you want? ( stlist | staccept | logout):");
        }
        if (user != null && user.getRole().equalsIgnoreCase("University")) {
            System.out.println("what do you want? ( unilist | uniaccept | logout):");
        }
    }
}
