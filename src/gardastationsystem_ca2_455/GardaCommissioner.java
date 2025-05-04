package gardastationsystem_ca2_455;

/**
 * Represents the Garda Commissioner role.
 * This is the highest ranking management type in the system.
 * @author kamil
 */
public class GardaCommissioner extends Manager {
    /*
    * Constructs a GardaCommissioner with the predefined title.
    * Using 'super("...")' is the parent class (Manager) requries 
    * a title to be provided, and this is the standard ,
    * best-practice way to pass that value from subclass.
    */
    public GardaCommissioner() {
        super("Garda Commissioner");
    }
    
}
