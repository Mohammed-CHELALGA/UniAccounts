
package strategy;

import model.StudentAccount;
import java.util.List;

public class JSONExportStrategy implements ExportStrategy {

    @Override
    public String export(List<StudentAccount> students) {

        StringBuilder result = new StringBuilder();

        result.append("[\n");

        for (int i = 0; i < students.size(); i++) {

            StudentAccount student = students.get(i);

            result.append("  {\n");

            result.append("    \"id\": \"")
                    .append(escape(student.getId()))
                    .append("\",\n");

            result.append("    \"fullName\": \"")
                    .append(escape(student.getFullName()))
                    .append("\",\n");

            result.append("    \"email\": \"")
                    .append(escape(student.getEmail()))
                    .append("\"\n");

            result.append("  }");

            if (i < students.size() - 1) {
                result.append(",");
            }

            result.append("\n");
        }

        result.append("]");

        return result.toString();
    }

    // Escape special JSON characters
    private String escape(String value) {

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
