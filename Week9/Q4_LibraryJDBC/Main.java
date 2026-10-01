import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    static final String USER = "root";
    static final String PASSWORD = "MySQL@12345";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    static void addBook(Scanner sc) {
        String sql = "INSERT INTO Book VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Title: ");
            String title = sc.nextLine();

            System.out.print("Author: ");
            String author = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Available (true/false): ");
            boolean available = sc.nextBoolean();

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setDouble(4, price);
            ps.setBoolean(5, available);

            ps.executeUpdate();
            System.out.println("Book added successfully.");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    static void displayBooks() {
        String sql = "SELECT * FROM Book";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID\tTitle\t\tAuthor\t\tPrice\tAvailable");
            System.out.println("------------------------------------------------------------");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("BookID") + "\t" +
                    rs.getString("Title") + "\t\t" +
                    rs.getString("Author") + "\t\t" +
                    rs.getDouble("Price") + "\t" +
                    rs.getBoolean("Availability")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    static void updateBook(Scanner sc) {
        String sql = "UPDATE Book SET Price = ?, Availability = ? WHERE BookID = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            System.out.print("New Price: ");
            double price = sc.nextDouble();

            System.out.print("Available (true/false): ");
            boolean available = sc.nextBoolean();

            ps.setDouble(1, price);
            ps.setBoolean(2, available);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Book updated successfully.");
            else
                System.out.println("Book not found.");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    static void deleteBook(Scanner sc) {
        String sql = "DELETE FROM Book WHERE BookID = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter Book ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Book deleted successfully.");
            else
                System.out.println("Book not found.");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;

                case 2:
                    displayBooks();
                    break;

                case 3:
                    updateBook(sc);
                    break;

                case 4:
                    deleteBook(sc);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}