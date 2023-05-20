// Introduction to Object-Oriented Programming (Sec. A) - Spring 2023
// Final Project - Manager Interface
// Yuri Dolukhanyan

package packages;

// MANAGER INTERFACE
public interface Manager {

    // INTERFACE METHODS
    public boolean verifyEmployeeHiring(Person employee);

    public boolean hireEmployee(Person employee);

    public boolean fireEmployee(Person employee);

    public void reviewSalary(Person employee);
}