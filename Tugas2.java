/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tugas2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author ASUS
 */


class MenuItem {
    String name;
    double price;
    String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class Tugas2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, MenuItem> menuMap = new HashMap<>();

    public static void main(String[] args) {
        initializeMenu();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Customer Order");
            System.out.println("2. Menu Management");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    processCustomerOrder();
                    break;
                case 2:
                    processMenuManagement();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void initializeMenu() {
        menuMap.put(1, new MenuItem("Burger", 50000, "Food"));
        menuMap.put(2, new MenuItem("Pizza", 75000, "Food"));
        menuMap.put(3, new MenuItem("Coke", 15000, "Drink"));
        menuMap.put(4, new MenuItem("Coffee", 10000, "Drink"));
    }

    private static void processCustomerOrder() {
        // Implementation of customer order remains the same
        // ... (refer to the previous code)

        // You can copy and paste the customer order logic from the previous code here
    }

    private static void processMenuManagement() {
        while (true) {
            System.out.println("\nMenu Management:");
            displayMenu();
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice (menu number to edit, 5 to return): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 5) {
                break;
            }

            if (menuMap.containsKey(choice)) {
                displayMenuDetails(choice);
                System.out.print("Do you want to edit/delete this menu? (Yes/No): ");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("Yes")) {
                    editOrDeleteMenu(choice);
                }
            } else {
                System.out.println("Invalid choice. Please enter a valid menu number.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nCurrent Menu List:");
        for (Map.Entry<Integer, MenuItem> entry : menuMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name + " - IDR " + entry.getValue().price);
        }
    }

    private static void displayMenuDetails(int menuNumber) {
        MenuItem menuItem = menuMap.get(menuNumber);
        System.out.println("\nMenu Details:");
        System.out.println("Name: " + menuItem.name);
        System.out.println("Price: IDR " + menuItem.price);
        System.out.println("Category: " + menuItem.category);
    }

    private static void editOrDeleteMenu(int menuNumber) {
        System.out.println("\nEdit/Delete Menu:");
        System.out.println("1. Edit Menu");
        System.out.println("2. Delete Menu");
        System.out.println("3. Cancel");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                editMenu(menuNumber);
                break;
            case 2:
                deleteMenu(menuNumber);
                break;
            case 3:
                System.out.println("Cancelled. Returning to Menu Management.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private static void editMenu(int menuNumber) {
        System.out.print("Enter the new price for the menu: IDR ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        menuMap.get(menuNumber).price = newPrice;
        System.out.println("Menu updated successfully!");
    }

    private static void deleteMenu(int menuNumber) {
        System.out.print("Are you sure you want to delete this menu? (Yes/No): ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Yes")) {
            menuMap.remove(menuNumber);
            System.out.println("Menu deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}


