package state;

import model.StudentAccount;

public interface AccountState {

    void activate(StudentAccount account);

    void suspend(StudentAccount account);

    void close(StudentAccount account);

    void access(StudentAccount account);

    String getName();
}