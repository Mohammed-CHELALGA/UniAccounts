package strategy;

import model.StudentAccount;
import java.util.List;

public interface ExportStrategy {

    String export(List<StudentAccount> students);
}