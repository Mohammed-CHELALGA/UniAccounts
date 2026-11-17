package state;

import model.StudentAccount;

public class PendingState implements AccountState {

    @Override
    public void activate(StudentAccount account) {
        System.out.println("Account activated successfully.");

        account.setState(new ActiveState());
    }

    @Override
    public void suspend(StudentAccount account) {
        throw new IllegalStateException(
                "Cannot suspend a pending account."
        );
    }

    @Override
    public void close(StudentAccount account) {
        account.setState(new ClosedState());

        System.out.println("Account closed.");
    }

    @Override
    public void access(StudentAccount account) {
        throw new IllegalStateException(
                "Cannot access a pending account."
        );
    }

    @Override
    public String getName() {
        return "PENDING";
    }
}