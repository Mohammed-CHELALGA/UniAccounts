package app;
import observer.AccountObserver ;
import observer.AuditObserver;
import observer.EmailNotifier;
import observer.AdminDashboard;
import model.StudentAccount;
import repository.StudentRepository;
import audit.AuditLogger;
import strategy.StudentExportService;
import strategy.CSVExportStrategy;
import strategy.JSONExportStrategy;
import java.util.List ;
public class Main {

    public static void main(String[] args) {

        System.out.println("===== UNIACCOUNTS =====");

        // Get Singleton instances
        StudentRepository repository =
                StudentRepository.getInstance();

        AuditLogger logger =
                AuditLogger.getInstance();

        // Create first student using Builder
        StudentAccount student1 =
                new StudentAccount.Builder(
                        "2026001",
                        "Ahmed Ali",
                        "ahmed@gmail.com"
                )
                        .level("L2")
                        .major("Computer Science")
                        .scholarship(true)
                        .build();

        repository.add(student1);

        logger.log("Student created: " + student1.getId());
        StudentAccount student2 = new StudentAccount.Builder(
                "ST002",
                "Observer Test",
                "observer@test.com"
        ).build();

        System.out.println("\n===== STRATEGY PATTERN TEST =====");

// Get all students
        List<StudentAccount> students =
                StudentRepository.getInstance().findAll();

// Create export service with CSV strategy
        StudentExportService exportService =
                new StudentExportService(new CSVExportStrategy());

// Export to CSV
        System.out.println("\n===== CSV EXPORT =====");

        String csvResult =
                exportService.exportStudents(students);

        System.out.println(csvResult);

// Change strategy to JSON
        exportService.setStrategy(new JSONExportStrategy());

// Export to JSON
        System.out.println("\n===== JSON EXPORT =====");

        String jsonResult =
                exportService.exportStudents(students);

        System.out.println(jsonResult);


        System.out.println("\n===== OBSERVER PATTERN TEST =====");

// Create a separate student for Observer test
        StudentAccount student4 = new StudentAccount.Builder(
                "ST004",
                "اسم الطالب",
                "email@test.com"
        ).build();

// Create observers
        AuditObserver auditObserver = new AuditObserver();
        EmailNotifier emailNotifier = new EmailNotifier();
        AdminDashboard adminDashboard = new AdminDashboard();

// Register observers
        student2.addObserver(auditObserver);
        student2.addObserver(emailNotifier);
        student2.addObserver(adminDashboard);

// Test state changes
        student2.activate();
        student2.suspend();
        student2.activate();
        student2.close();

        System.out.println(
                "Total events received by dashboard: "
                        + adminDashboard.getTotalEvents()
        );
        System.out.println("\n===== STATE PATTERN TEST =====");

// Initial state
        System.out.println("Current state: "
                + student1.getStateName());

// Activate
        student1.activate();

        System.out.println("Current state: "
                + student1.getStateName());

// Access
        student1.access();

// Suspend
        student1.suspend();

        System.out.println("Current state: "
                + student1.getStateName());

// Try accessing suspended account
        try {
            student1.access();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

// Reactivate
        student1.activate();

        System.out.println("Current state: "
                + student1.getStateName());

// Close
        student1.close();

        System.out.println("Current state: "
                + student1.getStateName());

        // Prototype: copy first student
        StudentAccount student3 =
                student1.copyWithNewIdentity(
                        "2026002",
                        "Sara Ahmed",
                        "sara@gmail.com"
                );

        repository.add(student2);

        logger.log("Student copied: " + student2.getId());

        // Display all students
        System.out.println("\n===== STUDENT LIST =====");

        for (StudentAccount student : repository.findAll()) {
            System.out.println(student);
        }

        // Test Singleton
        StudentRepository repo2 =
                StudentRepository.getInstance();

        System.out.println("\n===== SINGLETON TEST =====");

        System.out.println(
                "Same repository? " + (repository == repo2)
        );

        // Test duplicate ID
        System.out.println("\n===== DUPLICATE TEST =====");

        try {
            repository.add(student1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Final count
        System.out.println("\nTotal students: " +
                repository.count());
    }
}