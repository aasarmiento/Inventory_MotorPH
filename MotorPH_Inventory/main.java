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
        
        // DATA PERSISTENCE  Hydrate system from RAM-based structures to permanent CSV
        manager.loadInventoryFromCSV("inventory.csv");

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
                System.out.println("Invalid input. Please enter a number (1-5).");
                sc.next(); 
            }
            choice = sc.nextInt();
            sc.nextLine(); 
           
            switch (choice) {
                case 1 -> {
                    // ADD NEW STOCK LOGIC 
                    // Implements Dynamic Entry by attaching records to the DLL and BST simultaneously.
                    System.out.print("Enter Engine Number: "); 
                    String eNum = sc.next();
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Brand: "); 
                    String brand = sc.nextLine();
                    System.out.print("Enter Status (Old/New): "); 
                    String status = sc.nextLine();
                    System.out.print("Enter Date Entered (MM/DD/YYYY): "); 
                    String date = sc.nextLine();
                    
                    // Logic for Data Entry: Ensures the new Data Object is linked without resizing overhead.
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
                    // Uses In-Order Tree Traversal to generate an ordered report by Engine Number.
                    System.out.println("\n--- Current Inventory Report ---");
                    manager.generateReport(manager.getRoot());
                    System.out.println("--------------------------------");
                }
                case 4 -> {
                    // DELETE LOGIC
                    // Removes records from both BST and DLL to maintain data consistency.
                    System.out.print("Enter Engine Number to DELETE: ");
                    String dId = sc.next();
                    
                    // Logic for Deletion: Uses pointer re-linking (DLL) and recursive removal (BST).
                    manager.deleteStock(dId);
                    System.out.println("\nPress Enter to return to menu...");
                    sc.nextLine(); sc.nextLine();
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