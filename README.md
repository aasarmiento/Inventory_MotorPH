# 🏍️ MotorPH Inventory Management System (Phase 3)

**Prepared and Presented by:** Abigail Ann Sarmiento & Janine Arce  
*Mapua-Malayan Digital College*

---

## 📝 Introduction
This project represents a significant technological leap for MotorPH, transitioning from manual, static tracking to a high-performance digital architecture. By combining linear and non-linear data structures, the system ensures that warehouse operations—such as adding, searching, and organizing stock—remain nearly instant, regardless of how much the business expands.

## 🏗️ Technical Architecture: The Hybrid Solution
To solve the limitations of manual spreadsheets, this system implements a **Hybrid Data Structure** that manages data through two distinct paths:

### 1. The Search Path (Binary Search Tree)
Instead of scanning every row from top to bottom, the system organizes **Engine IDs** into a branching hierarchy. 
* **Efficiency:** Reduces search time from $O(n)$ to $O(\log n)$.
* **Speed:** In a database of 1,000 units, a search takes approximately 10 steps rather than 1,000.



### 2. The History Path (Doubly Linked List)
This serves as the permanent chronological logbook of the warehouse.
* **Integrity:** Each unit is linked to the one before and after it.
* **Precision Deletion:** Allows removing incorrect entries in $O(1)$ time by re-linking pointers, without disrupting the entire database.

---

## 🚀 Key Features & Objectives
* **Inventory Expansion (Add):** Seamlessly register motorcycles with full attributes (ID, Brand, Model, Engine ID, etc.).
* **Data Integrity (Delete):** Reliable removal of records using pointer re-linking to prevent data corruption.
* **Automated Organization (Sort):** Uses **TimSort logic** to arrange inventory alphabetically by Brand for management reporting.
* **Targeted Retrieval (Search):** Optimized "Divide and Conquer" search via Engine ID.
* **System Resilience:** A built-in fail-safe loader that rebuilds the inventory from CSV/Excel attributes if the primary file is missing.

---

## 🛠️ Technical Implementation

### Data Structures & Algorithms
| Requirement | Data Structure | Algorithm | Rationale |
| :--- | :--- | :--- | :--- |
| **Search** | Binary Search Tree | Divide & Conquer | Instant retrieval for Engine IDs. |
| **History** | Doubly Linked List | Pointer Re-linking | Chronological logging and fast deletion. |
| **Sorting** | ArrayList | TimSort (Comparator) | Stable sorting for Brand-based reports. |
| **Validation** | Hybrid Manager | Recursive Search | Prevents duplicate Engine IDs at the source. |

### The Motorcycle Entity
The system tracks the following attributes for every unit:
* Inventory ID & Engine ID
* Brand & Model
* Engine Displacement & Transmission
* Color, Price, and Status



---

## 📊 Success Metrics
The implementation is verified through the following operational goals:
1.  **Duplicate Prevention:** System rejects new entries if the Engine ID already exists in the BST.
2.  **Search Speed:** Immediate specification retrieval upon entering an Engine ID.
3.  **Report Accuracy:** Generates a perfectly sorted list of stock by Brand for management review.

---

## 🛡️ Potential Risks & Mitigations
* **Search Latency:** Mitigation via periodic rebalancing of the BST during system startup.
* **Data Persistence:** Auto-serialization saves data to a CSV file incrementally to prevent loss during power failures.
* **Memory Usage:** Active archiving moves sold units to a separate history file to keep the active dataset lean.

---

## ⚖️ Intellectual Property Notice
This project is an exclusive property of **Mapua-Malayan Digital College** and is protected under **Republic Act No. 8293 (IP Code of the Philippines)**. Unauthorized modification, distribution, or sale of this template is strictly prohibited.

---

© 2024 MotorPH Inventory Project - Sarmiento & Arce
