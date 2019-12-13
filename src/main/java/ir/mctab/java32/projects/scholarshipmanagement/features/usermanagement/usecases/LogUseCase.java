package ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

@UseCase
public interface LogUseCase {
    void writingLog(User user, String command, Long id);

    void writingLog(User user, String command);

    String LogStringForFind(User user, String command);

    String LogStringForAcceptAndReject(User user, String command, Long id);
}
