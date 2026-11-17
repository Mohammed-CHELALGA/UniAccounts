package strategy;

import model.StudentAccount;
import java.util.List;

public class CSVExportStrategy implements ExportStrategy {

    @Override
    public String export(List<StudentAccount> students) {

        StringBuilder result = new StringBuilder();

        // CSV header
        result.append("ID,Full Name,Email\n");

        // Export each student
        for (StudentAccount student : students) {

            result.append(student.getId()).append(",");
            result.append(student.getFullName()).append(",");
            result.append(student.getEmail()).append("\n");
        }

        return result.toString();
    }
}