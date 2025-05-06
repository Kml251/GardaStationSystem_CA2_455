/**
 * Main class for the Garda Station System (CA_2 Project).
 * Handles displaying the menu, capturing user input, and calling the appropriate actions.
 * 
 * Features available to the user:
 * - Sort Gardaí
 * - Search Gardaí
 * - Add a new Garda
 * - Generate random Gardaí
 * - Display all Gardaí
 * - Delete a Garda
 * - Export Gardaí list to a file
 * - Exit the application
 * 
 * Student ID: 2024455
 * @author kamil
 */
package gardastationsystem_ca2_455;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class GardaStationSystem_CA2_455 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        MyArrayList<Garda> gardaList = new MyArrayList<>(); // Create Garda list
        readNamesFromFile(gardaList);                   // Load Garda names from file
        
        // Use try-with-resources to ensure Scanner is closed automatically
        try (Scanner kb = new Scanner(System.in)) {
            boolean exit = false; // Controls when to exit the main menu loop

            // Welcome message
            System.out.println("=========================================");
            System.out.println("Welcome to Garda Station System - CA_2");
            System.out.println("Student ID: 2024455");
            System.out.println("=========================================");

            // Main menu loop
            do {
                MenuOption.printMenu(); // Display menu options
                MenuOption option = null; // Reset option for each loop

                // Input validation loop - ensure a valid option is selected
                while (option == null) {
                    System.out.println("\nPlease select an option (1-8): ");
                    try {
                        int input = Integer.parseInt(kb.nextLine()); // Read user input
                        option = MenuOption.fromInt(input); // Convert input to a MenuOption

                        if (option == null) {
                            System.out.println("Invalid Option. Please choose a number from 1 to 8.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }

                // Execute the selected menu option
                switch (option) {
                    case SORT -> {
                        System.out.println("-> Sorting Gardaí list...");
                        
                        // Prevent sorting if the list is empty
                        if (gardaList.isEmpty()) {
                            System.out.println("Garda list is empty. Add or import records first.");
                            break;
                        }
                        
                        // Sort the Garda list alphabetically by name using a recursive bubble sort
                        // This ensures that searching and listing will be in correct order
                        gardaList.bubbleRecursiveSort();
                        
                        // Display only the first 20 sorted Gardaí
                        System.out.println("\n=== First 20 Sorted Gardaí ===");
                        System.out.printf("%-8s %-30s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                        System.out.println("------------------------------------------------------------------------------------------------------");
                        
                        // Display up to the first 20 Gardaí after sorting
                        for(int i=0; i < Math.min(20, gardaList.size()); i++){
                            // Each Garda is printed using its toString() format
                            System.out.println((i+1) +". \t" + gardaList.get(i));
                        }
                    }
                    case SEARCH -> {
                        System.out.println("-> Searching records...");
                        
                        // Check if the Garda list is empty before attempting search
                        if (gardaList.isEmpty()) {
                            System.out.println("Garda list is empty. Add or import records first.");
                            break;
                        }

                        // Prompt the user to enter the full Garda name to search
                        System.out.print("Please enter the full Garda name to search for: ");
                        String input = kb.nextLine().trim();

                        // Sort list before searching (binary search requires sorted)
                        gardaList.bubbleRecursiveSort();

                        // Create a dummy Garda with only the name
                        // This will be used to compare against existing Garda objects in the list
                        Garda searchKey = new Garda(input, null, null);

                        // Perform recursive binary search for the given Garda name
                        // Returns the index of the match or -1 if not found
                        int result = gardaList.binarySearch_Recursive(searchKey, 0, gardaList.size() - 1);
                        
                        // Display result
                        if (result != -1) {
                            // Garda found, display in a formatted table
                            System.out.println("\n Garda found: ");
                            System.out.printf("%-8s %-30s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                            System.out.println("------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-8d %s%n", (result + 1), gardaList.get(result));
                        } else {
                            // No match found, show warning
                            System.out.println("No Garda found with the name: " + input);
                        }  
                    }
                    case ADD_RECORD -> {
                        System.out.println("-> Adding a new Garda...");
                        
                        // Prompt the user to input the Garda's full name
                        System.out.println("Please enter Garda's full name: ");
                        String name = kb.nextLine();
                        
                        // Define available manager roles
                        Manager[] managerOptions = {
                            new GardaCommissioner(),
                            new DeputyCommissioner(),
                            new Superintendent(),
                            new Inspector(),
                            new Sergeant()
                        };
                        // Display manager options for user to choose from
                        System.out.println("\n Please select Manager's Type: ");
                        for(int i = 0; i < managerOptions.length; i++){
                            System.out.println((i+1) + "." + managerOptions[i].getTitle());
                        }
                        
                        // Validate manager selection input
                        int managerChoice = -1;
                        while (managerChoice < 1 || managerChoice > managerOptions.length) {
                            System.out.print("Enter your choice (1-" + managerOptions.length + "): ");
                            try {
                                managerChoice = Integer.parseInt(kb.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a number.");
                            }
                        }
                        // Assign selected manager
                        Manager selectedManager = managerOptions[managerChoice - 1];
                        
                        // Define available departments
                        Department[] departmentOptions = {
                            new CyberCrimeUnit(),
                            new CrimeInvestigationUnit(),
                            new CommunityPolicingUnit(),
                            new DrugsOrganisedCrimeBureau(),
                            new NationalImmigrationBureau()
                        };
                        
                        // Display department options for user to choose from
                        System.out.println("\nSelect Department:");
                        for (int i = 0; i < departmentOptions.length; i++) {
                            System.out.println((i + 1) + ". " + departmentOptions[i]);
                        }
                        
                        // Validate department selection input
                        int departmentChoice = -1;
                        while (departmentChoice < 1 || departmentChoice > departmentOptions.length) {
                            System.out.print("Enter your choice (1-" + departmentOptions.length + "): ");
                            try {
                                departmentChoice = Integer.parseInt(kb.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a number.");
                            }
                        }
                        // Assign selected department
                        Department selectedDepartment = departmentOptions[departmentChoice - 1];
                        
                        // Create and add the Garda
                        Garda newGarda = new Garda(name, selectedManager, selectedDepartment);
                        gardaList.add(newGarda);
                        
                        // Display confirmation message
                        System.out.println("\n Successfully added:");
                        System.out.println("   Name: \t" + name);
                        System.out.println("   Manager: \t" + selectedManager);
                        System.out.println("   Department: \t" + selectedDepartment);
                    }
                    case GENERATE_RANDOM -> {
                        System.out.println("-> Generating random Gardaí...");
                        
                        // Arrays of sample first names and surnames used to build full Garda names
                        String[] firstname = {
                                "Jack", "Emma", "Danial", "Grace", "James",
                                "Emily", "Micheal", "Raymond", "Niamh", "Amy"
                        };
                        String[] surname = {
                                "Ryan", "Walsh", "Murphy", "Kavanagh", "O'Brian",
                                "Byrne", "O'Connell", "Nolan", "Conor", "Potter"
                        };
                        
                        // Predefined manager roles to assign randomly
                        Manager[] managerOptions = {
                            new GardaCommissioner(),
                            new DeputyCommissioner(),
                            new Superintendent(),
                            new Inspector(),
                            new Sergeant()
                        };
                        // Predefined departments to assign randomly
                        Department[] departmentOptions = {
                            new CyberCrimeUnit(),
                            new CrimeInvestigationUnit(),
                            new CommunityPolicingUnit(),
                            new DrugsOrganisedCrimeBureau(),
                            new NationalImmigrationBureau()                        
                        };
                        
                        // Random number generator for selection
                        Random rand = new Random();
                        // Set how many Gardaí to generate (default is 5)
                        int count = 5;
                        
                        // Display header for the generated records
                        System.out.printf("%-8s %-30s %-30s %-30s %n","#", "Name", "Manager", "Department");
                        System.out.println("------------------------------------------------------------------------------------------------------");
                        
                        // Generate each Garda, assign random manager and department, and add to list
                        for(int i = 0; i< count; i++){
                            // Combine random firstname and surname
                            String name = firstname[rand.nextInt(firstname.length)] +  " "+
                                          surname[rand.nextInt(surname.length)];
                            // Select random manager and department
                            Manager m = managerOptions[rand.nextInt(managerOptions.length)];
                            Department d = departmentOptions[rand.nextInt(departmentOptions.length)];
                            
                            // Create Garda object and add it to the list
                            Garda g = new Garda(name, m, d);
                            gardaList.add(g);
                            
                            // Display the new Garda record in formatted output
                            System.out.printf("%-8s %-30s %-30s %-30s%n",(i+1), g.getName(), m.getTitle(), d.getName());
                        }
                    }
                    case DISPLAY_ALL -> {
                        System.out.println("-> Displaying all Gardaí...");
                        
                        // Check if the list is empty before attempting to display
                        if (gardaList.isEmpty()){
                            System.out.println("No Gardaí found.");
                        }
                        else{
                            // Print the table header with aligned column titles
                            System.out.printf("%-8s %-30s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                            System.out.println("------------------------------------------------------------------------------------------------------");
                            
                            // Loop through and display each Garda in the list
                            for(int i=0; i < gardaList.size(); i++){
                                // Print index and Garda information using the Garda's toString() format
                                System.out.println((i+1) + ".\t" + gardaList.get(i));
                            }
                        }
                    }
                    case DELETE_GARDA -> { 
                        System.out.println("-> Deleting a Garda...");
                        
                        // Prompt user to enter the name of the Garda to delete
                        String nameToDelete = kb.nextLine().trim();
                        boolean found = false; // Flag to track if the Garda was found
                        
                        // Iterate through the Garda list
                        for(int i = 0; i < gardaList.size(); i++){
                            // Check if current Garda's name matches the input
                            if(gardaList.get(i).getName().equalsIgnoreCase(nameToDelete)){
                                // If found, remove the Garda from the list
                                Garda removed = gardaList.remove(i);
                                System.out.println("Deleted: " + removed.getName());
                                found = true; // Mark as found
                                break; // Exit the loop after deleting
                            }
                        }
                        // If no matching Garda was found, show an appropriate message
                        if(!found){
                            System.out.println("Garda named \"" + nameToDelete + "\" not found in the list.");
                        }
                    }
                    case EXPORT_REPORT -> {
                        System.out.println("-> Exporting report...");
                        
                        // Attempt to create and write to a text file named Garda_Report.txt
                        try(java.io.PrintWriter writer = new java.io.PrintWriter("Garda_Report.txt")){
                            
                            // Write table headers to the file
                            writer.printf("%-8s %-30s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                            writer.println("------------------------------------------------------------------------------------------------------");
                            
                            // Loop through each Garda in the list and write their details in formatted rows
                            for(int i = 0; i < gardaList.size(); i++){
                                writer.printf("%-8s %-30s %-30s %-30s%n", (i+1),
                                        gardaList.get(i).getName(),
                                        gardaList.get(i).getManagerType(),
                                        gardaList.get(i).getDepartment());
                            }
                            // Notify user that report was created successfully
                            System.out.println("Report exported successfully!");
                        } catch(Exception e){
                            // Handle any errors during file creation or writing
                            System.out.println("Failed to export report! " + e.getMessage());
                        }
                    }
                    case EXIT -> {
                        exit = true; // Exit selected, break the loop
                        System.out.println("Exiting system. Thank you!");
                    }
                }

            } while (!exit); // Repeat until user selects EXIT

            // Final message after exiting
            System.out.println("\nProgram finished. Goodbye!");
        }
    }

    private static void readNamesFromFile(MyArrayList<Garda> gardaList) throws FileNotFoundException {
        
        String filename = "Applicants_Form.txt"; // To read txt file
        
        // List of possible manager roles to assign randomly
        Manager[] managerTypes = {
            new GardaCommissioner(),
            new DeputyCommissioner(),
            new Superintendent(),
            new Inspector(),
            new Sergeant()
        };
        // List of possible departments to assign randomly
        Department[] departments = {
            new CrimeInvestigationUnit(),
            new CyberCrimeUnit(),
            new DrugsOrganisedCrimeBureau(),
            new CommunityPolicingUnit(),
            new NationalImmigrationBureau()
        };
        
        // Random number generator to assign types and departments
        Random random = new Random();
        // BufferedReader to read from the file
        BufferedReader reader = new BufferedReader(new FileReader(filename)); 
        try{
            String line;
            
            // Read each line from the file and create Garda entries
            while ((line = reader.readLine()) != null){
                // Skip empty lines
                if(!line.trim().isEmpty()){
                    Manager randomManager = managerTypes[random.nextInt(managerTypes.length)];
                    Department randomDepartment = departments[random.nextInt(departments.length)];
                    
                    // Create and add the new Garda object to the list
                    Garda newGarda = new Garda(line.trim(), randomManager, randomDepartment);
                    gardaList.add(newGarda);
                }
            }
            // Notify the user of successful file loading
            System.out.println("File read successfully. Gardaí loaded from Applicants_Form.txt.\n");
        }catch (IOException e) {
            // Handle any reading errors (e.g., file access issue)
            System.out.println("Error reading file: " + e.getMessage());
        }   
    }
    
}
