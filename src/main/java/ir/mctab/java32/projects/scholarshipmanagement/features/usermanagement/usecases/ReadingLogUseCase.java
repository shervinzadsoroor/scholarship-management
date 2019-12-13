package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

@UseCase
public interface ReadingLogUseCase {
    void readingLogByRole(String role);
    void readingLogByDate(String date);
}
