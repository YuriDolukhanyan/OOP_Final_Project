// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - InconsistentCSVRow Error Class
// Yuri Dolukhanyan

package csvparser;

public class InconsistentCSVRow extends RuntimeException {
    public InconsistentCSVRow() {
        super();
    }

    public InconsistentCSVRow(String message) {
        super(message);
    }

    public InconsistentCSVRow(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentCSVRow(Throwable cause) {
        super(cause);
    }
}