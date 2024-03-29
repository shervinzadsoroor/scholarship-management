package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

@UseCase
public interface ReceiveScholarshipByUniversityUseCase {
    boolean receive(Long scholarshipId);
}
