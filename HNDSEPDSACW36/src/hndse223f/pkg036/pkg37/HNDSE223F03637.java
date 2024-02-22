
package hndse223f.pkg036.pkg37;


import java.util.Scanner;

class Medicine {
    String name;
    int quantity;

    Medicine(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

class TreeNode {
    Medicine medicine;
    TreeNode left, right;

    TreeNode(Medicine medicine) {
        this.medicine = medicine;
        left = right = null;
    }
}

class MedicineManager {
    private TreeNode root;

    MedicineManager() {
        root = null;
    }

    void insert(String name, int quantity) {
        root = insertRecode(root, new Medicine(name, quantity));
    }

    private TreeNode insertRecode(TreeNode root, Medicine medicine) {
        if (root == null) {
            root = new TreeNode(medicine);
            return root;
        }

        if (medicine.name.compareTo(root.medicine.name) < 0)
            root.left = insertRecode(root.left, medicine);
        else if (medicine.name.compareTo(root.medicine.name) > 0)
            root.right = insertRecode(root.right, medicine);
  else {
           
            Scanner scanner = new Scanner(System.in);
            System.out.println("Medicine " + medicine.name + " already exists.");
            System.out.println("Do you want to update the quantity? (Y/N)");
            char choice = scanner.next().charAt(0);
            if (Character.toUpperCase(choice) == 'Y') {
                
                root.medicine.quantity += medicine.quantity;
                System.out.println("Quantity updated successfully.");
            }
            
            scanner.close();
        }


        return root;
    }

    Medicine search(String name) {
        return searchRec(root, name);
    }

    private Medicine searchRec(TreeNode root, String name) {
        if (root == null || root.medicine.name.equals(name))
            return root != null ? root.medicine : null;

        if (name.compareTo(root.medicine.name) < 0)
            return searchRec(root.left, name);
        return searchRec(root.right, name);
    }

    void remove(String name) {
        root = removeRec(root, name);
    }

    private TreeNode removeRec(TreeNode root, String name) {
        if (root == null)
            return root;

        if (name.compareTo(root.medicine.name) < 0)
            root.left = removeRec(root.left, name);
        else if (name.compareTo(root.medicine.name) > 0)
            root.right = removeRec(root.right, name);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.medicine = minValueNode(root.right).medicine;

            root.right = removeRec(root.right, root.medicine.name);
        }
        return root;
    }

    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    void display() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.medicine.name + " - " + root.medicine.quantity);
            inorder(root.right);
        }
    }
}

public class HNDSE223F03637 {
    public static void main(String[] args) {
        MedicineManager manager = new MedicineManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Medicine");
            System.out.println("2. Search Medicine");
            System.out.println("3. Remove Medicine");
            System.out.println("4. Display Medicines");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter medicine name: ");
                    String name = scanner.next();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    manager.insert(name, quantity);
                    break;
                case 2:
                    System.out.print("Enter medicine name to search: ");
                    String searchName = scanner.next();
                    Medicine searchResult = manager.search(searchName);
                    if (searchResult != null)
                        System.out.println("Found: " + searchResult.name + " - " + searchResult.quantity);
                    else
                        System.out.println("Medicine not found");
                    break;
                case 3:
                    System.out.print("Enter medicine name to remove: ");
                    String removeName = scanner.next();
                    manager.remove(removeName);
                    System.out.println("Medicine removed successfully");
                    break;
                case 4:
                    System.out.println("Medicines in stock:");
                    manager.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}


