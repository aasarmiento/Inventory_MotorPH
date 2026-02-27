/**
 * 8. TECHNICAL IMPLEMENTATION - DATA OBJECT
 * This class represents a single inventory record, encapsulating the 
 * essential fields identified in Section 3: Date, Status, Brand, and Engine Number.
 */
public class Motorcycle {
    // Unique Identifier (Primary Key) used for BST Search and Hash Map lookups
    String engineNumber; 
    String brand;
    String status; // Represents "On-hand" or "Sold"    
    String dateEntered;

    public Motorcycle(String engineNumber, String brand, String status, String dateEntered) {
        this.engineNumber = engineNumber;
        this.brand = brand;
        this.status = status;
        this.dateEntered = dateEntered;
    }

    /**
     * 7.1.5 DATA SERIALIZATION
     * Formats the motorcycle object into a readable string for reports 
     * and console display (Section 5.2).
     */
    @Override
    public String toString() {
        // Uses string formatting to ensure clear, consistent output for the 
        // Generate Stock Report process (Section 7.1.4).
        return String.format("Engine: %s | Brand: %s | Status: %s | Entered: %s", 
                              engineNumber, brand, status, dateEntered);
    }
}