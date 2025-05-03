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
                        gardaList.bubbleRecursiveSort();
                        
                        System.out.println("\n=== First 20 Sorted Gardaí ===");
                        System.out.printf("%-8s %-40s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                        System.out.println("------------------------------------------------------------------------------------------------------");
                        
                        for(int i=0; i < Math.min(20, gardaList.size()); i++){
                            System.out.println((i+1) +". \t" + gardaList.get(i));
                        }
                    }
                    case SEARCH -> {
                        System.out.println("-> Searching records...");
                        
                        if (gardaList.isEmpty()) {
                            System.out.println("Garda list is empty. Add or import records first.");
                            break;
                        }
                        
                        // Ask for name to search
                        System.out.print("Please enter the full Garda name to search for: ");
                        String input = kb.nextLine().trim();

                        // Sort list before searching (binary search requires sorted)
                        gardaList.bubbleRecursiveSort();

                        // Create a dummy Garda with only the name (to match against)
                        Garda searchKey = new Garda(input, "", "");

                        // Call your recursive binary search
                        int result = gardaList.binarySearch_Recursive(searchKey, 0, gardaList.size() - 1);
                        
                        if (result != -1) {
                            System.out.println("\n Garda found: ");
                            System.out.printf("%-8s %-40s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                            System.out.println("------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-8d %s%n", (result + 1), gardaList.get(result));
                        } else {
                            System.out.println("No Garda found with the name: " + input);
                        }                        
                    }    
                    case ADD_RECORD -> System.out.println("-> Adding a new Garda...");
                    case GENERATE_RANDOM -> System.out.println("-> Generating random Gardaí...");
                    case DISPLAY_ALL -> {
                        System.out.println("-> Displaying all Gardaí...");
                        if (gardaList.isEmpty()){
                            System.out.println("No Gardaí found.");
                        }
                        else{
                            System.out.printf("%-8s %-40s %-30s %-30s%n", "#", "Name", "Manager", "Department");
                            System.out.println("------------------------------------------------------------------------------------------------------");
                            
                            for(int i=0; i < gardaList.size(); i++){
                                System.out.println((i+1) + ".\t" + gardaList.get(i));
                            }
                        }
                    }
                    case DELETE_GARDA -> System.out.println("-> Deleting a Garda...");
                    case EXPORT_REPORT -> System.out.println("-> Exporting report...");
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
        
        String[] managerTypes = {"Garda Commissioner",
                                 "Deputy Commissioner",
                                 "Superintendent",
                                 "Inspector",
                                 "Sergeant"};
        String[] departments = {"Crime Investigaion Unit",
                                 "Cyber Crime Unit",
                                 "Drugs & Organised Crime Bureau",
                                 "Community Policing Unit",
                                 "National Immigration Bureau"};
        
        Random random = new Random(); // To split the types and departments randomly
        BufferedReader reader = new BufferedReader(new FileReader(filename)); 
        try{
            String line;
            
            while ((line = reader.readLine()) != null){
            
            if(!line.trim().isEmpty()){
                String randomManager = managerTypes[random.nextInt(managerTypes.length)];
                    String randomDepartment = departments[random.nextInt(departments.length)];
                    Garda newGarda = new Garda(line.trim(), randomManager, randomDepartment);
                    gardaList.add(newGarda);
            }
            System.out.println("File read successfully. Gardaí loaded from Applicants_Form.txt.\n");
            } 
        }catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }   
    }
    
}
