import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * INVENTORY MANAGER - Core Logic Implementation
 * Combines a Doubly Linked List for chronological storage and a BST for searching.
 */
public class InventoryManager {
    private Node head, tail; // DLL Pointers
    private Node root;       // BST Root

    // --- ADDITION LOGIC ---
    public void addStock(Motorcycle m) {
        if (search(root, m.engineNumber) != null) {
            System.out.println("Error: Duplicate Engine Number " + m.engineNumber);
            return;
        }
        
        Node newNode = new Node(m);
        // DLL: Add to end O(1)
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        // BST: Insert O(log n)
        root = insertBST(root, newNode);
      /**  // COMMENT THIS LINE OUT:
        System.out.println("Stock Added: " + m.brand);
         */
    }

    private Node insertBST(Node current, Node newNode) {
        if (current == null) return newNode;
        if (newNode.data.engineNumber.compareTo(current.data.engineNumber) < 0)
            current.left = insertBST(current.left, newNode);
        else
            current.right = insertBST(current.right, newNode);
        return current;
    }

    // --- SEARCH LOGIC ---
    public Node search(Node current, String id) {
        if (current == null || current.data.engineNumber.equals(id)) return current;
        if (id.compareTo(current.data.engineNumber) < 0) 
            return search(current.left, id);
        return search(current.right, id);
    }

    // --- DELETION LOGIC (The "Fix") ---
    public void deleteStock(String engineNum) {
        Node toDelete = search(root, engineNum);
        
        if (toDelete != null) {
            // 1. Unlink from Doubly Linked List
            if (toDelete.prev != null) toDelete.prev.next = toDelete.next;
            else head = toDelete.next; 

            if (toDelete.next != null) toDelete.next.prev = toDelete.prev;
            else tail = toDelete.prev; 

            // 2. Remove from BST
            root = deleteFromBST(root, engineNum);
            System.out.println("Record " + engineNum + " successfully removed.");
        } else {
            System.out.println("Error: Record not found.");
        }
    }

    private Node deleteFromBST(Node current, String id) {
        if (current == null) return null;

        if (id.compareTo(current.data.engineNumber) < 0) {
            current.left = deleteFromBST(current.left, id);
        } else if (id.compareTo(current.data.engineNumber) > 0) {
            current.right = deleteFromBST(current.right, id);
        } else {
            // Node found - Handle 3 cases:
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Two children: Get successor (smallest in right subtree)
            current.data = findMin(current.right).data;
            current.right = deleteFromBST(current.right, current.data.engineNumber);
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // --- REPORTING ---
    public void generateReport(Node node) {
        if (node != null) {
            generateReport(node.left);
            System.out.println(node.data);
            generateReport(node.right);
        }
    }

    public Node getRoot() { return this.root; }

    // --- CSV OPERATIONS ---
  
    /**
     * GEMINI UPDATE: Updated to return boolean so main class can detect if file loading failed.
     * This ensures the mentor's requirement for a manual fallback is triggered correctly.
     */
    public boolean loadInventoryFromCSV(String fileName) {
        boolean hasData = false; // Start as false
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 4) {
                    // Logic for internal call: Uses local method scope (Fixes "manager cannot be resolved")
                    addStock(new Motorcycle(values[3], values[2], values[1], values[0]));
                    hasData = true; // We successfully added at least one bike!
                }
            }
        } catch (IOException e) {
            return false; // Triggers manual initialization in main
        }
        return hasData; // Return the actual result (Fixes the "Notice" appearing incorrectly)
    }

    public void saveToCSV(String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("Date Entered,Stock Label,Brand,Engine Number");
            Node current = head;
            while (current != null) {
                Motorcycle m = current.data;
                pw.printf("%s,%s,%s,%s%n", m.dateEntered, m.status, m.brand, m.engineNumber);
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void seedInventory() {
        System.out.println("No CSV found. Initializing from hardcoded Excel data...");
        
        // Attributes: Date Entered, Status, Brand, Engine Number
        addStock(new Motorcycle("142QVTSIUR", "Honda", "On-hand", "2026-01-10"));
        addStock(new Motorcycle("992XPTYIOP", "Kawasaki", "Sold", "2026-01-15"));
        addStock(new Motorcycle("442BZTREWQ", "Yamaha", "On-hand", "2026-01-20"));
        
        System.out.println("Manual initialization complete.");
    }
}