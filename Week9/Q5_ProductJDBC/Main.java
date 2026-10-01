package Q5_ProductJDBC;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/productdb";
        String user = "root";
        String password = "MySQL@12345";

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String insertSQL = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement insertPS = con.prepareStatement(insertSQL);

            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            insertPS.setInt(1, id);
            insertPS.setString(2, name);
            insertPS.setDouble(3, price);
            insertPS.setInt(4, quantity);
            insertPS.executeUpdate();

            System.out.println("Product inserted successfully.");

            System.out.print("Enter Product ID to retrieve: ");
            int searchId = sc.nextInt();

            String selectSQL = "SELECT * FROM Product WHERE ProductID = ?";
            PreparedStatement selectPS = con.prepareStatement(selectSQL);
            selectPS.setInt(1, searchId);

            ResultSet rs = selectPS.executeQuery();

            if (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("ProductID"));
                System.out.println("Product Name: " + rs.getString("ProductName"));
                System.out.println("Price: " + rs.getDouble("Price"));
                System.out.println("Quantity: " + rs.getInt("Quantity"));
            } else {
                System.out.println("Product not found.");
            }

            System.out.print("Enter new quantity: ");
            int newQuantity = sc.nextInt();

            String updateSQL = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";
            PreparedStatement updatePS = con.prepareStatement(updateSQL);
            updatePS.setInt(1, newQuantity);
            updatePS.setInt(2, searchId);
            updatePS.executeUpdate();

            System.out.println("Quantity updated successfully.");

            String lowStockSQL = "SELECT * FROM Product WHERE Quantity <= 10";
            PreparedStatement lowStockPS = con.prepareStatement(lowStockSQL);
            ResultSet lowStockRS = lowStockPS.executeQuery();

            System.out.println("\nProducts with quantity <= 10:");

            while (lowStockRS.next()) {
                System.out.println(
                    lowStockRS.getInt("ProductID") + " | " +
                    lowStockRS.getString("ProductName") + " | " +
                    lowStockRS.getDouble("Price") + " | " +
                    lowStockRS.getInt("Quantity")
                );
            }

            con.close();
            sc.close();

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}