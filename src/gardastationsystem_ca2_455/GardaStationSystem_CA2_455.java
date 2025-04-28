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
 * Author: kamil
 */

package gardastationsystem_ca2_455;

import java.util.Scanner;

/**
 *
 * @author kamil
 */
public class GardaStationSystem_CA2_455 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
                    case SORT -> System.out.println("-> Sorting Gardaí list...");
                    case SEARCH -> System.out.println("-> Searching records...");
                    case ADD_RECORD -> System.out.println("-> Adding a new Garda...");
                    case GENERATE_RANDOM -> System.out.println("-> Generating random Gardaí...");
                    case DISPLAY_ALL -> System.out.println("-> Displaying all Gardaí...");
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
    
}
