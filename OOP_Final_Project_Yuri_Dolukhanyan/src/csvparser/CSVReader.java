// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - CSVReader Class
// Yuri Dolukhanyan

package csvparser;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;

public class CSVReader {

    // CSVReader Class private variables
    private String filePath;
    private String[] columnNames;
    private Scanner sc;

    // CSVReader CONSTRUCTOR
    public CSVReader(String filePath) throws InvalidCSVFileException {

        this.filePath = filePath;
        File file = new File(filePath);

        // Checking validateCSV() for Object / Instance initialization
        if (validateCSV()) {
            System.out.println("\n*validateCSV() is True*\n*Object is being initialized...*");
        } else {
            throw new InvalidCSVFileException("\n\n*validateCSV() is False*\n*Object is not being initialized...*");
        }

        // Initializing the Scanner sc object with the given file path
        try {
            sc = new Scanner(new FileInputStream(file));

            // Skipping the header_row for nextRow() and storing to columnNames
            columnNames = sc.nextLine().split(",");

        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("\n" + e.toString());
        }
    }

    // CLASS METHOD #1
    private boolean validateCSV() {

        File file = new File(filePath);

        // Checker #1
        try {
            if (!file.exists()) {

                System.out.println("\n*Error: File not found: " + filePath);
                file.createNewFile();
                return false;

            } else if (file.length() == 0) {

                System.out.println("\n*Error: File is empty...");
                return false;

            }
        } catch (IOException e) {
            System.out.println("\n" + e.toString());
            return false;
        }

        // Checker #2
        String extension = "";
        int lastIndex = filePath.lastIndexOf('.');

        if (lastIndex > 0) {
            extension = filePath.substring(lastIndex + 1);

            if (!extension.equals("csv")) {
                System.out.println("\n*Error / *Warning: CSV file is not valid...");
                return false;
            }
        }

        int NumberOfColumns = 0;
        int header_row_commas_count = 0;
        int other_commas_count = 0;
        String header_row = "";
        String other_row = "";

        // Checker #3 (Initializing the Scanner validate_sc object with the given file path
        // and stroing the first row data to header_row)
        try {

            Scanner validate_sc = new Scanner(new FileInputStream(file));

            while (validate_sc.hasNextLine()) {
                header_row = validate_sc.nextLine();
                break;
            }

            validate_sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("\n" + e.toString());
            return false;
        }

        // Number of commas in header_row
        for (int i = 0; i < header_row.length(); i++) {
            if (header_row.charAt(i) == ',') {
                header_row_commas_count++;
            }
        }

        // Checker #4 (Number of commas in each other row)
        try {

            Scanner validate_sc = new Scanner(new FileInputStream(file));
            validate_sc.nextLine(); // Skipping header_row

            while (validate_sc.hasNextLine()) {

                other_commas_count = 0;
                other_row = validate_sc.nextLine();

                for (int i = 0; i < other_row.length(); i++) {
                    if (other_row.charAt(i) == ',') {
                        other_commas_count++;
                    }
                }

                if (other_commas_count == header_row_commas_count) {
                    continue;
                } else {
                    System.out.println("\n*Error: Each line does not have the same number of commas...");
                    return false;
                }
            }

            validate_sc.close();

        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("\n" + e.toString());
            return false;
        }

        NumberOfColumns = other_commas_count + 1;

        System.out.println("\nHeader row of the input CSV file: '" + header_row + "'.");
        System.out.println("\nNumber of commas in the header row of the input CSV file: '" + header_row_commas_count + "'.");
        System.out.println("\nNumber of commas in the other rows of the input CSV file: '" + other_commas_count + "'.");
        System.out.println("\nNumber of columns of the input CSV file: '" + NumberOfColumns + "'.");

        return true;
    }

    // CLASS METHOD #2
    public boolean hasNextRow() {
        return sc.hasNextLine();
    }

    // CLASS METHOD #3
    public String[] nextRow() {

        if (!hasNextRow()) {
            return null;
        }

        String row = sc.nextLine();
        String[] columns_data = row.split(",");
        return columns_data;
    }

    // GETTER
    public String getFilePath() {
        return filePath;
    }

    // SETTER
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // GETTER
    public String[] getColumnNames() {
        return columnNames;
    }

    // SETTER
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}