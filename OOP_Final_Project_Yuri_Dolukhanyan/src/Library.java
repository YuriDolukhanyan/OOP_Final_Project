// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Library Class
// Yuri Dolukhanyan

import java.util.Arrays;

import packages.*;
import csvparser.*;

// LIBRARY CLASS
public class Library {

    private String name;
    private String address;
    private Manager ceo;
    private Person librarian;
    private Person[] employees;

    // LIBRARY CONSTRUCTOR
    public Library(String name, String address, Manager ceo, Person librarian, Person[] employees, Book[] books) {
        this.name = name;
        this.address = address;
        this.ceo = ceo;
        this.librarian = librarian;
        this.employees = employees;
    }

    // LIBRARY TOSTRING
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\nName: " + name + "\nAddress: " + address + "\n\nCEO: " + ceo;
    }

    // GETTER
    public String getName() {
        return name;
    }

    // SETTER
    public void setName(String name) {
        this.name = name;
    }

    // GETTER
    public String getAddress() {
        return address;
    }

    // SETTER
    public void setAddress(String address) {
        this.address = address;
    }

    // GETTER
    public Manager getCeo() {
        return ceo;
    }

    // SETTER
    public void setCeo(Manager ceo) {
        this.ceo = ceo;
    }

    // GETTER
    public Person getLibrarian() {
        return librarian;
    }

    // SETTER
    public void setLibrarian(Person librarian) {
        this.librarian = librarian;
    }

    // GETTER
    public Person[] getEmployees() {
        return employees;
    }

    // SETTER
    public void setEmployees(Person[] employees) {
        this.employees = employees;
    }

    public static void main(String[] args) throws Exception {

        // Objects & Instances
        Librarian l_1 = new Librarian("Tom", "Smith", 40, "438017", 10, new Book[0]);
        Librarian l_2 = new Librarian("Jenny", "Wilson", 45, "765499", 12, new Book[0]);

        // Library employees array
        Person[] employees = {l_1, l_2};

        // Objects & Instances
        Admin a_1 = new Admin("John", "Doe", 55, "589032", employees);
        Library lib_1 = new Library("AGBU Library", "5GV3+7P4, Yerevan", a_1, l_1, employees, null);

        // Validating employees' names
        for (Person i : employees) {
            i.setName(i.getName());
            a_1.hireEmployee(i);
        }

        // ------------------------------------------------------------------ //

        System.out.println("\n/* " + "-".repeat(75) + " */");

        System.out.println("\nObject: 'csv_reader_1'.");

        CSVReader csv_reader_1 = new CSVReader("C:/Users/User/Desktop/OOP_Final_Project_Yuri_Dolukhanyan/books_csv_1.csv");

        while (csv_reader_1.hasNextRow()) {
            String[] rows = csv_reader_1.nextRow();

            Book b_n = new Book(rows[0], rows[1], Integer.parseInt(rows[2]), Float.parseFloat(rows[3]));

            l_1.addBook(b_n);

            System.out.println("\n" + Arrays.toString(rows) + " was added to books_arr_1!");
        }

        // ------------------------------------------------------------------ //

        System.out.println("\n/* " + "-".repeat(75) + " */");

        System.out.println("\nObject: 'csv_reader_2'.");

        CSVReader csv_reader_2 = new CSVReader("C:/Users/User/Desktop/OOP_Final_Project_Yuri_Dolukhanyan/books_csv_2.csv");

        while (csv_reader_2.hasNextRow()) {
            String[] rows = csv_reader_2.nextRow();

            Book b_n = new Book(rows[0], rows[1], Integer.parseInt(rows[2]), Float.parseFloat(rows[3]));

            l_2.addBook(b_n);

            System.out.println("\n" + Arrays.toString(rows) + " was added to books_arr_2!");
        }

        // ------------------------------------------------------------------ //

        System.out.println("\n/* " + "-".repeat(75) + " */");

        System.out.println("\n" + lib_1.toString());
        LibrarySystemWindow librarySysWind = new LibrarySystemWindow(lib_1);

        System.out.println("\n/* " + "-".repeat(75) + " */\n");

//        for (int i = 0; i < l_1.getBooks().length; i++) {
//            System.out.println("***" + l_1.getBooks()[i].getTitle() + "\n");
//        }
//
//        System.out.println(Arrays.toString(l_1.getBooks()));
//        System.out.println(Arrays.toString(l_2.getBooks()));

        // ------------------------------------------------------------------ //
    }
}