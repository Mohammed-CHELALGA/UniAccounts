package state;

import model.StudentAccount;

public class ActiveState implements AccountState {

    @Override
    public void activate(StudentAccount account) {
        System.out.println("Account is already active.");
    }

    @Override
    public void suspend(StudentAccount account) {
        account.setState(new SuspendedState());

        System.out.println("Account suspended.");
    }

    @Override
    public void close(StudentAccount account) {
        account.setState(new ClosedState());

        System.out.println("Account closed.");
    }

    @Override
    public void access(StudentAccount account) {
        System.out.println(
                "Access granted to " + account.getFullName()
        );
    }

    @Override
    public String getName() {
        return "ACTIVE";
    }
}