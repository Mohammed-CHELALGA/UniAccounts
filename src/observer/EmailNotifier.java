package observer;

import model.StudentAccount;

public class EmailNotifier implements AccountObserver {

    @Override
    public void update(StudentAccount account, String event) {

        System.out.println(
                "[EMAIL] To: " + account.getEmail()
        );

        System.out.println(
                "[EMAIL] Hello " + account.getFullName()
        );

        System.out.println(
                "[EMAIL] Your account event: " + event
        );
    }
}