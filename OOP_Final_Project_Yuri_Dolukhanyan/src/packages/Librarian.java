// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Librarian Class
// Yuri Dolukhanyan

package packages;

import java.util.Arrays;

// LIBRARIAN CLASS
public class Librarian extends Person {

    private int experience;
    private Book[] books;

    // LIBRARIAN CONSTRUCTOR
    public Librarian(String name, String surname, int age, String ID, int experience, Book[] books) {
        super(name, surname, age, ID);
        this.experience = experience;
        this.books = books;
    }

    // LIBRARIAN TOSTRING
    @Override
    public String toString() {
        return "\n\n" + super.toString() + "\nExperience: " + experience + "y" + "\nBooks: " + Arrays.toString(books);
    }

    // CLASS METHOD #1
    public boolean addBook(Book book) {

        // Check if the book is already in the books array
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                return false;
            }
        }

        int currentSize = books.length;
        int newSize = currentSize + 1;
        Book[] newBooks = new Book[newSize];

        // Copy existing authors to the newAuthors
        for (int i = 0; i < currentSize; i++) {
            newBooks[i] = books[i];
        }

        newBooks[newSize - 1] = book;
        books = newBooks;
        System.out.println("\nBook: '" + book.getTitle() + "' was added to the array of the Librarian " + this.getName() + "!\n");
        return true;
    }

    // CLASS METHOD #2
    public int indexOf(Book book) {
        int index = -1;

        for (int i = 0; i < books.length; i++) {
            if (book == books[i] || book.equals(books[i])) {
                index = i;
            }
        }
        return index;
    }

    // CLASS METHOD #3
    public boolean removeBook(Book book) {
        if (indexOf(book) != -1) {

            int index = indexOf(book);

            if (index < 0 || index >= books.length) {
                throw new IndexOutOfBoundsException("Index: '" + index + "' is invalid!");
            }

            for (int i = index; i < books.length - 1; i++) {
                books[i] = books[i + 1];
            }

            books[books.length - 1] = null;
            System.out.println("\nBook: '" + book.getTitle() + "' was removed from the array of the Librarian " + this.getName() + "!\n");
            return true;
        } else {
            System.out.println("\nBook: '" + book.getTitle() + "' is not in the array of the Librarian " + this.getName() + "!\n");
            return false;
        }
    }

    // CLASS METHOD #4
    public boolean reserveBook(Book book) {
        return false;
    }

    // CLASS METHOD #5
    public boolean sellBook(Book book) {
        return false;
    }

    // GETTER
    public int getExperience() {
        return experience;
    }

    // SETTER
    public void setExperience(int experience) {
        this.experience = experience;
    }

    // GETTER
    public Book[] getBooks() {
        return books;
    }

    // SETTER
    public void setBooks(Book[] books) {
        this.books = books;
    }
}