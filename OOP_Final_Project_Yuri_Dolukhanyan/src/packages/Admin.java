// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Admin Class
// Yuri Dolukhanyan

package packages;

import java.util.Arrays;

// ADMIN CLASS
public class Admin extends Person implements Manager {

    private Person[] team;

    // ADMIN CONSTRUCTOR
    public Admin(String name, String surname, int age, String ID, Person[] team) {
        super(name, surname, age, ID);
        this.team = team;
    }

    // ADMIN TOSTRING
    @Override
    public String toString() {
        return super.toString() + "\n\nTeam: " + Arrays.toString(team).substring(1, Arrays.toString(team).length() - 1);
    }

    // INTERFACE OVERRIDDEN METHOD #1
    @Override
    public boolean verifyEmployeeHiring(Person employee) {
        if (employee.getAge() > 18 && employee.getAge() < 63) {
            return true;
        } else {
            System.out.println("Unable to hire the employee " + employee.toString() +  "...");
            return false;
        }
    }

    // INTERFACE OVERRIDDEN METHOD #2
    @Override
    public boolean hireEmployee(Person employee) {

        // Check if the employee is already in the team array
        for (int i = 0; i < team.length; i++) {
            if (team[i] == employee) {
                return false;
            }
        }

        int currentSize = team.length;
        int newSize = currentSize + 1;
        Person[] newTeam = new Person[newSize];

        // Copy existing team to the newTeam
        for (int i = 0; i < currentSize; i++) {
            newTeam[i] = team[i];
        }

        newTeam[newSize - 1] = employee;
        team = newTeam;
        return true;
    }

    public int indexOf(Person employee) {

        int index = -1;

        for (int i = 0; i < team.length; i++) {
            if (employee == team[i] || employee.equals(team[i])) {
                index = i;
            }
        }
        return index;
    }

    // INTERFACE OVERRIDDEN METHOD #3
    @Override
    public boolean fireEmployee(Person employee) {
        if (indexOf(employee) != -1) {

            int index = indexOf(employee);

            if (index < 0 || index > team.length) {
                throw new IndexOutOfBoundsException("Index: '" + index + "' is invalid!");
            }

            for (int i = index; i < team.length - 1; i++) {
                team[i] = team[i + 1];
            }

            team[team.length - 1] = null;
            System.out.println("Element: '" + employee + "' was removed from the array!\n");
            return true;
        } else {
            System.out.println("Element: '" + employee + "' is not in the array!\n");
            return false;
        }
    }

    // INTERFACE OVERRIDDEN METHOD #4
    @Override
    public void reviewSalary(Person employee) {
        // TODO Auto-generated method stub
    }

    // GETTER
    public Person[] getTeam() {
        return team;
    }

    // SETTER
    public void setTeam(Person[] team) {
        this.team = team;
    }
}