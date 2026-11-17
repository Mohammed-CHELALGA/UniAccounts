package observer;

import model.StudentAccount;
import audit.AuditLogger;

public class AuditObserver implements AccountObserver {

    public void update(StudentAccount account, String event) {

        AuditLogger.getInstance().log(
                "Student: " + account.getFullName()
                        + " | Event: " + event
        );

    }
}