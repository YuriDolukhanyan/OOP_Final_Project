// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Book Abstract Class
// Yuri Dolukhanyan

package packages;

// BOOK ABSTRACT CLASS
public class Book {

    private String title;
    private String author;
    private int year;
    private float price;
    private Person[] authors;

    // BOOK CONSTRUCTOR
    public Book(String title, String author, int year, float price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    // BOOK TOSTRING
    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName() + "... Title: " + title + ", Author: " + author + ", Year: " + year + ", Price: " + price;
    }

    // CLASS METHOD #1
    public boolean addAuthor(Person author) {

        // Check if the author is already in the authors array
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == author) {
                return false;
            }
        }

        int currentSize = authors.length;
        int newSize = currentSize + 1;
        Person[] newAuthors = new Person[newSize];

        // Copy existing authors to the newAuthors
        for (int i = 0; i < currentSize; i++) {
            newAuthors[i] = authors[i];
        }

        newAuthors[newSize - 1] = author;
        authors = newAuthors;
        return true;
    }

    // CLASS METHOD #2
    public int indexOf(Person author) {

        int index = -1;

        for (int i = 0; i < authors.length; i++) {
            if (author == authors[i] || author.equals(authors[i])) {
                index = i;
            }
        }
        return index;
    }

    // CLASS METHOD #3
    public boolean removeAuthor(Person author) {

        if (indexOf(author) != -1) {

            int index = indexOf(author);

            if (index < 0 || index > authors.length) {
                throw new IndexOutOfBoundsException("Index: '" + index + "' is invalid!");
            }

            for (int i = index; i < authors.length - 1; i++) {
                authors[i] = authors[i + 1];
            }

            authors[authors.length - 1] = null;
            System.out.println("Element: '" + author + "' was removed from the array!\n");
            return true;
        } else {
            System.out.println("Element: '" + author + "' is not in the array!\n");
            return false;
        }
    }

    // GETTER
    public String getTitle() {
        return title;
    }

    // SETTER
    public void setTitle(String title) {
        int l = title.length();
        boolean allLetters = true;

        for (int i = 0; i < l; i++) {
            if (!Character.isLetter(title.charAt(i))) {
                allLetters = false;
                break;
            }
        }

        if (allLetters) {
            this.title = title;
        } else {
            this.title = "Error! Wrong name...";
        }
    }

    // GETTER
    public String getAuthor() {
        return author;
    }

    // SETTER
    public void setAuthor(String author) {
        this.author = author;
    }

    // GETTER
    public int getYear() {
        return year;
    }

    // SETTER
    public void setYear(int year) {
        this.year = year;
    }

    // GETTER
    public float getPrice() {
        return price;
    }

    // SETTER
    public void setPrice(float price) {
        this.price = price;
    }

    // GETTER
    public Person[] getAuthors() {
        return authors;
    }

    // SETTER
    public void setAuthors(Person[] authors) {
        this.authors = authors;
    }
}