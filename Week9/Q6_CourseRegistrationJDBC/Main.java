package Q6_CourseRegistrationJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/collegedb";
        String user = "root";
        String password = "MySQL@12345";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();

        String sql = "SELECT * FROM CourseRegistration WHERE CourseCode = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, courseCode);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Student ID: " + rs.getInt("StudentID"));
                System.out.println("Student Name: " + rs.getString("StudentName"));
                System.out.println("Course Code: " + rs.getString("CourseCode"));
                System.out.println("Course Name: " + rs.getString("CourseName"));
                System.out.println("Semester: " + rs.getInt("Semester"));
                System.out.println();
            }

            if (!found) {
                System.out.println("No students registered for course " + courseCode);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        sc.close();
    }
}