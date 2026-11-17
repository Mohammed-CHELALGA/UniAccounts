package observer;

import model.StudentAccount;

public interface AccountObserver {

    void update(StudentAccount account, String event);
}