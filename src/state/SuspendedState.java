package state;

import model.StudentAccount;

public class SuspendedState implements AccountState {

    @Override
    public void activate(StudentAccount account) {
        account.setState(new ActiveState());

        System.out.println("Account reactivated.");
    }

    @Override
    public void suspend(StudentAccount account) {
        System.out.println("Account is already suspended.");
    }

    @Override
    public void close(StudentAccount account) {
        account.setState(new ClosedState());

        System.out.println("Account closed.");
    }

    @Override
    public void access(StudentAccount account) {
        throw new IllegalStateException(
                "Access denied: account is suspended."
        );
    }

    @Override
    public String getName() {
        return "SUSPENDED";
    }
}