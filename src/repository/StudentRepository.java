package repository;

import model.StudentAccount;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository  {

    // Singleton: one instance only
    private static final StudentRepository INSTANCE =
            new StudentRepository();

    // Store student accounts using their IDs
    private final Map<String, StudentAccount> accounts =
            new LinkedHashMap<>();

    // Private constructor
    private StudentRepository() {
    }

    // Global access point
    public static StudentRepository getInstance() {
        return INSTANCE;
    }

    // Add a student account
    public void add(StudentAccount student) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null"
            );
        }

        if (accounts.containsKey(student.getId())) {
            throw new IllegalArgumentException(
                    "Student ID already exists: " + student.getId()
            );
        }

        accounts.put(student.getId(), student);

        System.out.println(
                "Student added successfully: " + student.getId()
        );
    }

    // Find a student by ID
    public StudentAccount findById(String id) {
        return accounts.get(id);
    }

    // Remove a student
    public boolean remove(String id) {
        return accounts.remove(id) != null;
    }

    // Return all students
    public List<StudentAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }

    // Return the number of students
    public int count() {
        return accounts.size();
    }
}