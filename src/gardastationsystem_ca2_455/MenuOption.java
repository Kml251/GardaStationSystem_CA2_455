package gardastationsystem_ca2_455;

/**
 * Enum representing the different options available in the Garda Station System menu.
 * Each constant corresponds to a different feature or action the user can perform.
 * 
 * SORT - Sort the Gardaí list alphabetically
 * SEARCH - Search for a Garda in the list
 * ADD_RECORD - Add a new Garda manually
 * GENERATE_RANDOM - Generate random Gardaí data
 * DISPLAY_ALL - Display all stored Gardaí
 * DELETE_GARDA - Delete a Garda by name
 * EXPORT_REPORT - Export Gardaí list to a file
 * EXIT - Exit the system
 * 
 * @author kamil
 */

public enum MenuOption {
    SORT,
    SEARCH,
    ADD_RECORD,
    GENERATE_RANDOM,
    DISPLAY_ALL,
    DISPLAY_SORTED_BY_ROLE,
    DELETE_GARDA,
    EXPORT_REPORT,
    EXIT;
    
    /**
     * Prints all available menu options to the console.
     * Each option is displayed with its corresponding number for user selection.
     */
    public static void printMenu(){
        System.out.println("======= Menu =======");
        for(int i=0; i < values().length; i++){
            System.out.println((i+1) + "." + values()[i]);
        }
    }
    
    /**
     * Converts a numeric input into the corresponding MenuOption.
     * 
     * @param input the number entered by the user (expected to be 1-8)
     * @return the corresponding MenuOption if valid; otherwise, null
     */
    public static MenuOption fromInt(int input){
        if(input >= 1 && input <= values().length){
            return values()[input - 1];
        }
        return null;
    }
    
}
