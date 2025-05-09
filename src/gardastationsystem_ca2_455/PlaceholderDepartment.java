package gardastationsystem_ca2_455;

/**
 * Represents a placeholder department used in situations
 * where a real department is not assigned (e.g., during search operations).
 * This prevents null values and avoids potential run time errors.
 * 
 * Inherits from Department.
 * 
 * @author kamil
 */
public class PlaceholderDepartment extends Department {
    
    /**
     * Constructs a placeholder department with the name "N/A".
     */
    public PlaceholderDepartment() {
        super("N/A");
    }
}