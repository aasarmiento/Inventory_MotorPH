/**
 * RECOMMENDED DATA STRUCTURES - THE MULTI-PURPOSE NODE
 * This class serves as the fundamental building block for the hybrid 
 * architecture defined in the MotorPH solution. 
 */
public class Node {
    // TECHNICAL IMPLEMENTATION
    // Encapsulates the Motorcycle Data Object within the structure.
    public Motorcycle data;

    //  PRIMARY DATA STRUCTURE: Doubly Linked List (DLL) Pointers
    // These links allow for bidirectional traversal , 
    // supporting efficient sequential reporting and manual navigation.
    public Node prev, next; 

    // SEARCHING STRUCTURE: Binary Search Tree (BST) Pointers
    // These links organize the inventory into a branching hierarchy ,
    // enabling "Divide and Conquer" search performance (O(log n)).
    public Node left, right; 

    public Node(Motorcycle m) {
        this.data = m;
    }
}