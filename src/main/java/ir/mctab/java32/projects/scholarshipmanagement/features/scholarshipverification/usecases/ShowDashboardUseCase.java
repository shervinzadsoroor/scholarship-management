package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

import java.sql.SQLException;

@UseCase
public interface ShowDashboardUseCase {
    void execute() throws SQLException;
}
