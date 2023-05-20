// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - InvalidCSVFileException Error Class
// Yuri Dolukhanyan

package csvparser;

public class InvalidCSVFileException extends RuntimeException {
    public InvalidCSVFileException() {
        super();
    }

    public InvalidCSVFileException(String message) {
        super(message);
    }

    public InvalidCSVFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCSVFileException(Throwable cause) {
        super(cause);
    }
}
