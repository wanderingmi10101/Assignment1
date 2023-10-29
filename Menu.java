import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MenuItem {
    String name;
    double price;
    String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }



    public static void main(String[] args) {
        // Define menu items
        MenuItem[] menu = {
            new MenuItem("Burger", 8.99, "Food"),
            new MenuItem("Pizza", 10.99, "Food"),
            new MenuItem("Coke", 2.49, "Drink"),
            new MenuItem("Coffee", 1.99, "Drink")
        };

        // Display menu
        System.out.println("Welcome to the Restaurant Menu!");
        System.out.println("Menu Items:");
        for (MenuItem item : menu) {
            System.out.println(item.name + " - $" + item.price + " (" + item.category + ")");
        }

        // Input order
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> order = new HashMap<>(); // Stores the customer's order

        while (order.size() < 4) {
            System.out.println("Enter your order (item name = quantity, e.g., 'Coke = 2'). Enter 'done' to finish:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] orderParts = input.split("=");
            if (orderParts.length == 2) {
                String itemName = orderParts[0].trim();
                int quantity = Integer.parseInt(orderParts[1].trim());

                for (MenuItem item : menu) {
                    if (item.name.equalsIgnoreCase(itemName)) {
                        if (order.containsKey(itemName)) {
                            order.put(itemName, order.get(itemName) + quantity);
                        } else {
                            order.put(itemName, quantity);
                        }
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input. Use the format 'item name = quantity'.");
            }
        } scanner.close();

        // Calculate and display the total cost
        double totalCost = 0.0;
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            for (MenuItem item : menu) {
                if (item.name.equalsIgnoreCase(itemName)) {
                    totalCost += item.price * quantity;
                }
            }
        }

        // Apply discounts and fees
        double discount = 0.0;
        if (totalCost > 6) {
            discount = totalCost * 0.1; // 10% discount
        }

        int freeCokes = 0;
        if (totalCost > 4 && order.containsKey("Coke")) {
            int cokeQuantity = order.get("Coke");
            freeCokes = cokeQuantity / 2; // Buy one get one free for Coke
        }

        double taxRate = 0.1; // 10% tax
        double tax = totalCost * taxRate;
        double serviceFee = 2.0;

        double finalTotal = totalCost - discount - (menu[2].price * freeCokes) + tax + serviceFee;

        // Print the order receipt
        System.out.println("\nOrder Receipt:");
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            for (MenuItem item : menu) {
                if (item.name.equalsIgnoreCase(itemName)) {
                    double itemTotal = item.price * quantity;
                    System.out.println(item.name + " x" + quantity + " - $" + item.price + " each - $" + itemTotal);
                }
            }
        }

        System.out.println("Subtotal: $" + totalCost);
        if (discount > 0) {
            System.out.println("Discount: -$" + discount);
        }
        if (freeCokes > 0) {
            System.out.println("Buy one get one free offer: -$" + (menu[2].price * freeCokes));
        }
        System.out.println("Tax (" + (taxRate * 100) + "%): $" + tax);
        System.out.println("Service Fee: $" + serviceFee);
        System.out.println("Total: $" + finalTotal);
    }
}
