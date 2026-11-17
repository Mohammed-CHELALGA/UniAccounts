
package strategy;

import model.StudentAccount;
import java.util.List;

public class StudentExportService {

    private ExportStrategy strategy;

    // Constructor
    public StudentExportService(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    // Change export strategy
    public void setStrategy(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    // Export students using selected strategy
    public String exportStudents(List<StudentAccount> students) {

        if (strategy == null) {
            throw new IllegalStateException(
                    "Export strategy is not selected."
            );
        }

        return strategy.export(students);
    }
}
