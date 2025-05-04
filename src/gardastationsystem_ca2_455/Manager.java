package gardastationsystem_ca2_455;

/**
 * Base class for all Manager roles in the Garda Station System.
 * @author kamil
 */
public class Manager {
    
    // The Official title of the manager.
    protected String title;
    
    /*
    * Constructor to set the title of the manager.
    */
    public Manager(String title){
        this.title = title;
    }
    
    /*
    * Returns the title of the manager.
    */
    public String getTitle(){
        return title;
    }
    
    /**
     * Returns the title when printed or converted to String
     * @return
     */

    @Override
    public String toString() {
        return title;
    }
    
}
