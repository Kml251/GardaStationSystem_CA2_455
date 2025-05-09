package gardastationsystem_ca2_455;

/**
 * Represents a Garda in the Garda Station System.
 * Each Garda has a name, manager type, and department.
 * Implements Comparable to allow sorting by name.
 * 
 * @author kamil
 */
public class Garda implements Comparable<Garda> {  // Implement Comparable here

    private final String name;
    private final Manager manager;
    private final Department department;

    // Main constructor to create a Garda with all details.
    public Garda(String name, Manager manager, Department department) {
        this.name = name;
        this.manager = manager;
        this.department = department;
    }

    // Secondary constructor used for search operations.
    // Assigns placeholder values for manager and department.
    public Garda(String name) {
    this.name = name;
    this.manager = new PlaceholderManager();
    this.department = new PlaceholderDepartment();
    }

    // Getter for Garda's name
    public String getName() {
        return name;
    }

    // Getter for the manager type (title)
    public Manager getManagerType() {
        return manager;
    }

    // Getter for the department
    public Department getDepartment() {
        return department;
    }

    /**
     * Defines how two Garda objects are compared: by name alphabetically.
     */
    @Override
    public int compareTo(Garda other) {
        return this.name.compareToIgnoreCase(other.name); // Compare names ignoring case
    }

    /**
     * Returns a formatted string representing the Garda's information.
     * Ensures alignment when printing in a table format.
     */
    @Override
    public String toString() {
        return String.format("%-30s %-30s %-30s",
                name,
                manager.getTitle(),
                department.getName());
    }
}