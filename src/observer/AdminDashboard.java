package observer;

import model.StudentAccount;

public class AdminDashboard implements AccountObserver {

    private int totalEvents = 0;

    @Override
    public void update(StudentAccount account, String event) {

        totalEvents++;

        System.out.println(
                "[ADMIN DASHBOARD] Student: "
                        + account.getFullName()
        );

        System.out.println(
                "[ADMIN DASHBOARD] Event: " + event
        );

        System.out.println(
                "[ADMIN DASHBOARD] Total events: " + totalEvents
        );
    }

    public int getTotalEvents() {
        return totalEvents;
    }
}