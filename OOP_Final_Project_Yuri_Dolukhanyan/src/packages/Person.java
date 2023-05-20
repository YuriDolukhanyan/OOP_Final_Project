// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Person Abstract Class
// Yuri Dolukhanyan

package packages;

// PERSON ABSTRACT CLASS
public abstract class Person {

    private String name;
    private String surname;
    private int age;
    private String ID;

    // PERSON CONSTRUCTOR
    public Person(String name, String surname, int age, String ID) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ID = ID;
    }

    // PERSON TOSTRING
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\nName: " + name + "\nSurname: " + surname + "\nAge: " + age + " y/o" + "\nID: " + ID;
    }

    // GETTER
    public String getName() {
        return name;
    }

    // SETTER
    public void setName(String name) {
        int l = name.length();
        boolean allLetters = true;

        for (int i = 0; i < l; i++) {
            if (!Character.isLetter(name.charAt(i))) {
                allLetters = false;
                break;
            }
        }

        if (allLetters) {
            this.name = name;
        } else {
            this.name = "Error! Wrong name...";
        }
    }

    // GETTER
    public String getSurname() {
        return surname;
    }

    // SETTER
    public void setSurname(String surname) {
        int l = surname.length();
        boolean allLetters = true;

        for (int i = 0; i < l; i++) {
            if (!Character.isLetter(surname.charAt(i))) {
                allLetters = false;
                break;
            }
        }

        if (allLetters) {
            this.surname = surname;
        } else {
            this.surname = "Error! Wrong surname...";
        }
    }

    // GETTER
    public int getAge() {
        return age;
    }

    // SETTER
    public void setAge(int age) {
        this.age = age;
    }

    // GETTER
    public String getID() {
        return ID;
    }

    // SETTER
    public void setID(String iD) {
        ID = iD;
    }
}