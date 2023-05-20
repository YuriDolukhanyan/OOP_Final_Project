// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - CSVWriter Class
// Yuri Dolukhanyan

package csvparser;

import java.util.Arrays;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {

    // CSVWriter Class private variables
    private String filePath;
    private String[] columnNames;
    private String[][] rows;
    private boolean appendMode;

    // CSVWriter CONSTRUCTOR (Overload #1)
    public CSVWriter(String filePath, String[] columnNames) throws FileNotFoundException {

        this.filePath = filePath;
        this.columnNames = columnNames;

        rows = new String[0][columnNames.length];
        File file = new File(filePath);

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("\n" + e.toString());
            }

            System.out.println("New file at: '" + filePath + "' is being created...\n");
            throw new FileNotFoundException("\n\n*Error: File not found: '" + filePath + "'.");

        } else if (!file.canWrite()) {
            throw new FileNotFoundException("\n\n*Error: File is not for writing: '" + filePath + "'.");
        }
    }

    // CSVWriter CONSTRUCTOR (Overload #2)
    public CSVWriter(String filePath, boolean appendMode) throws FileNotFoundException, InvalidCSVFileException {

        this.filePath = filePath;
        this.appendMode = appendMode;

        if (appendMode) {
            rows = new String[0][0];
            File file = new File(filePath);

            // Validating file #1
            if (!file.exists()) {
                throw new FileNotFoundException("\n\n*Error: File not found: '" + filePath + "'.");
            } else if (!file.canWrite()) {
                throw new FileNotFoundException("\n\n*Error: File is not for writing: '" + filePath + "'.");
            }

            // Validating file #2
            if (file.length() == 0) {
                throw new InvalidCSVFileException("\n\n*Error: File is empty...");
            }

            try {
                Scanner sc = new Scanner(new FileInputStream(file));
                columnNames = sc.nextLine().split(",");
                int rowCount = 0;

                // Validating the 'input CSV' file's content #1
                if (Arrays.toString(columnNames) == null || Arrays.toString(columnNames).isEmpty() || !Arrays.toString(columnNames).contains(",")) {
                    throw new InvalidCSVFileException("\n\n*Error / *Warning: CSV file is not valid...");
                }

                // Counting number of the rows in the 'input CSV' file
                while (sc.hasNextLine()) {
                    sc.nextLine();
                    rowCount++;
                }

                rows = new String[rowCount][columnNames.length];
                int rowIndex = 0;

                sc = new Scanner(new FileInputStream(file));
                sc.nextLine(); // Skipping header_row

                while (sc.hasNextLine()) {

                    String[] values = sc.nextLine().split(",");

                    // Validating the 'input CSV' file's content #2
                    if (Arrays.toString(values) == null || Arrays.toString(values).isEmpty() || !Arrays.toString(values).contains(",")) {
                        throw new InvalidCSVFileException("\n\n*Error / *Warning: CSV file is not valid...");
                    }

                    // Checking whether each 'row' has the same number of 'columns'
                    if (values.length != columnNames.length) {
                        throw new InvalidCSVFileException("\n\n*Error: Invalid number of columns in a row " + (rowIndex + 1));
                    }

                    rows[rowIndex] = values;
                    rowIndex++;
                }
            } catch (FileNotFoundException | NoSuchElementException e) {
                System.out.println("\n" + e.toString());
                return;
            }
        }
    }

    // CLASS METHOD #1 (addRow)
    public void addRow(String[] row) throws InconsistentCSVRow {

        // Checking whether input 'row' is valid
        try {
            if (row.length != columnNames.length) {
                throw new InconsistentCSVRow("\n\n*Error: Invalid row - number of columns does not match the header.");
            }
        } catch (NullPointerException e) {
            System.out.println("\n" + e.toString());
            return;
        }

        // Initializing new 2D array 'newRows'
        String[][] newRows = new String[rows.length + 1][columnNames.length];

        // Copying everything from 'rows' to 'newRows'
        System.arraycopy(rows, 0, newRows, 0, rows.length);

        newRows[rows.length] = row;

        // Referring to 'rows'
        rows = newRows;
    }

    // CLASS METHOD #2 (addRows)
    public void addRows(String[][] newRows) throws InconsistentCSVRow {

        // Initializing new 2D array 'concatenatedRows'
        String[][] concatenatedRows = new String[rows.length + newRows.length][columnNames.length];
        int counter = 0;

        // Copying everything from 'rows' to 'concatenatedRows'
        for (int i = 0; i < rows.length; i++) {
            concatenatedRows[counter++] = rows[i];
        }

        // Checking whether input 'rows' are valid
        for (int i = 0; i < newRows.length; i++) {

            if (newRows[i].length != columnNames.length) {
                throw new InconsistentCSVRow("\n\n*Error: Invalid row - number of columns does not match the header.\nRow: " + Arrays.toString(newRows[i]));
            }

            concatenatedRows[counter++] = newRows[i];
        }
        // Referring to 'rows'
        rows = concatenatedRows;
    }

    // CLASS METHOD #3 (updateRow)
    public boolean updateRow(String columnName, String columnValue, String columnNewValue) {

        int valueIndex = -1;

        // Finding the index of the input 'columnName'
        for (int i = 0; i < columnNames.length; i++) {
            if (columnName.equals(columnNames[i])) {
                valueIndex = i;
                break;
            }
        }

        // Changing the input 'columnValue' to 'columnNewValue' and returning after the first occurrence
        if (valueIndex == -1) {
            return false;
        } else {
            for (int i = 0; i < rows.length; i++) {
                if (columnValue.equals(rows[i][valueIndex])) {
                    rows[i][valueIndex] = columnNewValue;
                    return true;
                }
            }
        }
        return false;
    }

    // CLASS METHOD #4 (updateAllRows)
    public boolean updateAllRows(String columnName, String columnValue, String columnNewValue) {

        boolean flag = false;
        int columnIndex = -1;

        // Finding the index of the input 'columnName'
        for (int i = 0; i < columnNames.length; i++) {
            if (columnName.equals(columnNames[i])) {
                columnIndex = i;
                break;
            }
        }

        // Changing the input 'columnValue' to 'columnNewValue' and continuing after the first occurrence
        if (columnIndex == -1) {
            return flag;
        } else {
            for (int i = 0; i < rows.length; i++) {
                if (columnValue.equals(rows[i][columnIndex])) {
                    rows[i][columnIndex] = columnNewValue;
                    flag = true;
                }
            }
        }
        return flag;
    }

    // CLASS METHOD #5 (printCSV)
    public void printCSV() {

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath, false);

            PrintWriter writer = new PrintWriter(outputStream);

            // Converting the 'columnNames' array into a 'header_row'
            for (int i = 0; i < columnNames.length; i++) {
                if (i != columnNames.length - 1) {
                    writer.print(columnNames[i] + ",");
                } else {
                    writer.print(columnNames[i]);
                    writer.print("\n");
                }
            }

            // Converting each nested row of 'rows' array into a 'csv_row'
            for (int i = 0; i < rows.length; i++) {

                for (int j = 0; j < rows[i].length; j++) {
                    if (j != rows[i].length - 1) {
                        writer.print(rows[i][j] + ",");
                    } else {
                        writer.print(rows[i][j]);
                    }
                }

                if (i != rows.length - 1) {
                    writer.print("\n");
                }
            }

            writer.close();

            System.out.println("Data has been written to the CSV file: '" + filePath + "'.");

        } catch (IOException e) {
            System.out.println("\n" + e.toString());
        }
    }

    // CLASS METHOD #6 (sysOutRow)
    public void sysOutRow() {

        // Printing String[][] rows
        for (int i = 0; i < rows.length; i++) {
            System.out.println("\n" + Arrays.toString(rows[i]));
        }

        System.out.println("\nFull Rows array: ");
        System.out.println("\n" + Arrays.deepToString(rows) + "\n");
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

    // GETTER
    public String[][] getRows() {
        return rows;
    }

    // SETTER
    public void setRows(String[][] rows) {
        this.rows = rows;
    }
}