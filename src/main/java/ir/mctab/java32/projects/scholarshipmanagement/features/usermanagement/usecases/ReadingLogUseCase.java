package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

@UseCase
public interface ReadingLogUseCase {
    void readingLogByRole(String role);
    void readingLogByDate(String date);
    void readingLogById(Long id);
}
