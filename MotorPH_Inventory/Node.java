/**
 * 5. RECOMMENDED DATA STRUCTURES - THE MULTI-PURPOSE NODE
 * This class serves as the fundamental building block for the hybrid 
 * architecture defined in the MotorPH solution. 
 */
public class Node {
    // 8. TECHNICAL IMPLEMENTATION
    // Encapsulates the Motorcycle Data Object within the structure.
    public Motorcycle data;

    // 5.1 PRIMARY DATA STRUCTURE: Doubly Linked List (DLL) Pointers
    // These links allow for bidirectional traversal (Section 6.1), 
    // supporting efficient sequential reporting and manual navigation.
    public Node prev, next; 

    // 5.3 SEARCHING STRUCTURE: Binary Search Tree (BST) Pointers
    // These links organize the inventory into a branching hierarchy (Section 7.1.3),
    // enabling "Divide and Conquer" search performance (O(log n)).
    public Node left, right; 

    public Node(Motorcycle m) {
        this.data = m;
    }
}