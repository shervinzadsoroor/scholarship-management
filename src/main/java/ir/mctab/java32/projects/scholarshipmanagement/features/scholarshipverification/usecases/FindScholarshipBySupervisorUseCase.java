package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipBySupervisorUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;

@UseCase
public interface FindScholarshipBySupervisorUseCase {

    List<Scholarship> listScholarships();
    void find(String command, User user);
}
