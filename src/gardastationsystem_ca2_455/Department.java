package gardastationsystem_ca2_455;

/**
 * Base class for all Departments in the Garda Station System.
 * Each department subclass will extend this and provide a name.
 * @author kamil
 */
public class Department {
    protected String name;
    
    /*
    * Constructor to assign department names.
    */
    public Department(String name){
        this.name = name;
    }
    
    /*
    * Returns the name of the department.
    */
    public String getName(){
        return name;
    }
    
    /**
     * Used when printing or converting department to String.
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
}
