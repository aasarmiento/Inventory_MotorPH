import java.util.Scanner;

/**
 * MOTORPH INVENTORY SYSTEM - MAIN INTERFACE
 * This class implements the "Menu Option" logic to serve as 
 * the central starting point for all inventory tasks using a switch-case structure.
 */
public class main { 
    public static void main(String[] args) {
        // Initialize the Primary Data Structure: Doubly Linked List (DLL) and BST Index
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);
        
        // DATA PERSISTENCE - Hydrate system from RAM-based structures to permanent CSV
        // GEMINI NOTE: Added return value check to satisfy mentor requirement for manual fallback.
        boolean csvLoaded = manager.loadInventoryFromCSV("inventory.csv");

        // incase csv is missing or fails to load, we will initialize the system with predefined data as per the excel file attributes.
        if (!csvLoaded) {
            System.out.println("Notice: CSV data not detected. Initializing from Excel-defined attributes.");
            
            // Defining attributes as indicated on the excel file
            // Format: Engine Number, Brand, Status, Date Entered
            manager.addStock(new Motorcycle("142QVTSIUR", "Honda", "On-hand", "01/10/2026"));
            manager.addStock(new Motorcycle("992XPTYIOP", "Kawasaki", "Sold", "01/15/2026"));
            manager.addStock(new Motorcycle("442BZTREWQ", "Yamaha", "On-hand", "01/20/2026"));
            manager.addStock(new Motorcycle("771MLKJHGF", "Suzuki", "On-hand", "01/25/2026"));
            
            System.out.println("Manual initialization complete.\n");
        }

        int choice = 0;

        do {
            System.out.println("\n============================");
            System.out.println("   MOTORPH INVENTORY SYSTEM ");
            System.out.println("============================");
            System.out.println("1. Add New Stock");
            System.out.println("2. Search Inventory (BST)");
            System.out.println("3. Generate Stock Report (Sorted)");
            System.out.println("4. Delete Stock Record"); // Added for Requirement 2.1
            System.out.println("5. Save & Exit");
            System.out.print("Select Operation: ");
            
            // Input Validation: Ensures data integrity by preventing non-integer choices
            while (!sc.hasNextInt()) {
                String wrongInput = sc.next(); // Capture the "bad" data
                System.out.println("Invalid input ['" + wrongInput + "']. Please enter a number (1-5).");
            }
            choice = sc.nextInt();
            sc.nextLine(); 
           
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Engine Number: "); 
                    String eNum = sc.nextLine(); // Changed to nextLine for consistency
                    
                    System.out.print("Enter Brand: "); 
                    String brand = sc.nextLine();
                    
                    System.out.print("Enter Status (Old/New): "); 
                    String status = sc.nextLine();
                    
                    System.out.print("Enter Date Entered (MM/DD/YYYY): "); 
                    String date = sc.nextLine();
                    
                    manager.addStock(new Motorcycle(eNum, brand, status, date));
                    System.out.println("\nPress Enter to return to menu...");
                    sc.nextLine(); 
                }
                case 2 -> {
                    // SEARCH LOGIC  
                    // Implements the "Divide and Conquer" strategy (O(log n)) using the Binary Search Tree.
                    System.out.print("Enter Engine Number to Search: ");
                    String sId = sc.next(); 
                    
                    // BST Search: Ignores half of the data at every step for high-speed retrieval.
                    Node result = manager.search(manager.getRoot(), sId); 
                    
                    if (result != null) {
                        System.out.println("\n--- Match Found ---");
                        System.out.println(result.data.toString());
                    } else {
                        System.out.println("Result: Engine Number not found.");
                    }
                }
                case 3 -> {
                    // SORTED STOCK PROCESS 
                    // Integrated Choice: Allows user to pick between Engine Number (BST) or Brand Name (DLL).
                    System.out.println("\nHow would you like to sort the report?");
                    System.out.println("[1] By Engine Number (Default)");
                    System.out.println("[2] By Brand Name");
                    System.out.print("Choice: ");
                    
                    // Internal validation for sort choice
                    while (!sc.hasNextInt()) {
                        sc.next();
                        System.out.print("Please enter 1 or 2: ");
                    }
                    int sortChoice = sc.nextInt();
                    sc.nextLine();

                    if (sortChoice == 2) {
                        manager.generateReportByBrand();
                    } else {
                        System.out.println("\n--- Current Inventory Report (Sorted by Engine Number) ---");
                        manager.generateReport(manager.getRoot());
                    }
                    System.out.println("--------------------------------");
                }
                case 4 -> {
                    System.out.print("Enter Engine Number to DELETE: ");
                    String dId = sc.nextLine(); // Use nextLine to keep buffer clean
                    
                    manager.deleteStock(dId);
                    System.out.println("\nPress Enter to return to menu...");
                    sc.nextLine(); 
                }
                case 5 -> {
                    // EXIT AND DATA PERSISTENCE 
                    // Linear Data Extraction translates temporary RAM structures back to flat-file storage.
                    System.out.println("Saving all records to inventory.csv...");
                    manager.saveToCSV("inventory.csv"); 
                    System.out.println("System shut down successfully. Goodbye!");
                }
                default -> System.out.println("Invalid selection. Choose 1, 2, 3, 4, or 5.");
            }
        } while (choice != 5);
        
        sc.close();
    }
}