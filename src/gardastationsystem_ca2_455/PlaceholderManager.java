package gardastationsystem_ca2_455;

/**
 * Represents a placeholder manager used when a real manager
 * is not required or known (e.g., during dummy search comparisons).
 * Prevents null pointer exceptions by providing a safe default.
 * 
 * Inherits from Manager.
 * 
 * @author kamil
 */
public class PlaceholderManager extends Manager {

    /**
     * Constructs a placeholder manager with the title "N/A".
     */
    public PlaceholderManager() {
        super("N/A");
    }    
}