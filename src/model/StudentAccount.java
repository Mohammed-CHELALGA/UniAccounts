package model;
import observer.AccountObserver ;
import state.PendingState ;
import state.AccountState ;
import java.util.ArrayList;
import java.util.List;

public class StudentAccount {

    // Required fields
    private String id;
    private String fullName;
    private String email;

    // Optional fields
    private String phone;
    private String level;
    private String major;
    private boolean scholarship;
    private List<String> courses;
    private AccountState state = new PendingState();
    private final List<AccountObserver>  observers = new ArrayList<>() ;
    // Private constructor: object creation through Builder
    private StudentAccount(Builder builder) {


        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;

        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;

        this.courses = new ArrayList<>(builder.courses);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLevel() {
        return level;
    }

    public String getMajor() {
        return major;
    }

    public boolean hasScholarship() {
        return scholarship;
    }

    public List<String> getCourses() {
        return new ArrayList<>(courses);
    }

    // Setters needed for account lifecycle and future extensions
    public void setLevel(String level) {
        validateLevel(level);
        this.level = level;
    }

    // Prototype: create a copy with a new identity
    public StudentAccount copyWithNewIdentity(
            String newId,
            String newFullName,
            String newEmail) {

        return new Builder(newId, newFullName, newEmail)
                .phone(this.phone)
                .level(this.level)
                .major(this.major)
                .scholarship(this.scholarship)
                .courses(this.courses)
                .build();
    }

    // Builder Design Pattern
    public static class Builder {

        private String id;
        private String fullName;
        private String email;

        private String phone = null;
        private String level = "L1";
        private String major = null;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder courses(List<String> courses) {
            this.courses = new ArrayList<>(courses);
            return this;
        }

        public StudentAccount build() {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID is required");
            }

            if (fullName == null || fullName.trim().isEmpty()) {
                throw new IllegalArgumentException("Full name is required");
            }

            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }

            validateLevel(level);

            return new StudentAccount(this);
        }
    }

    private static void validateLevel(String level) {

        List<String> validLevels = List.of(
                "L1", "L2", "L3", "M1", "M2"
        );

        if (level == null || !validLevels.contains(level)) {
            throw new IllegalArgumentException("Invalid student level");
        }
    }
    // Register an observer
    public void addObserver(AccountObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // Remove an observer
    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    // Notify all registered observers
    public void notifyObservers(String event) {

        for (AccountObserver observer : new ArrayList<>(observers)) {
            observer.update(this, event);
        }
    }

    // Display account information

    // State Pattern

    public void setState(AccountState newState) {

        String oldState = this.state.getName();

        this.state = newState;

        String newStateName = this.state.getName();

        notifyObservers(
                "State changed: " + oldState + " -> " + newStateName
        );

    }

    public AccountState getState() {
        return state;
    }

    public String getStateName() {
        return state.getName();
    }

    public void activate() {
        state.activate(this);
    }

    public void suspend() {
        state.suspend(this);
    }

    public void close() {
        state.close(this);
    }

    public void access() {
        state.access(this);
    }
    public String toString() {
        return "StudentAccount{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", level='" + level + '\'' +
                ", major='" + major + '\'' +
                ", scholarship=" + scholarship +
                ", courses=" + courses +
                '}';
    }
}
