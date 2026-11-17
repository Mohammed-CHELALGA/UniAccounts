package state;

import model.StudentAccount;

public class ClosedState implements AccountState {

    @Override
    public void activate(StudentAccount account) {
        throw new IllegalStateException(
                "Cannot activate a closed account."
        );
    }

    @Override
    public void suspend(StudentAccount account) {
        throw new IllegalStateException(
                "Cannot suspend a closed account."
        );
    }

    @Override
    public void close(StudentAccount account) {
        System.out.println("Account is already closed.");
    }

    @Override
    public void access(StudentAccount account) {
        throw new IllegalStateException(
                "Access denied: account is closed."
        );
    }

    @Override
    public String getName() {
        return "CLOSED";
    }
}