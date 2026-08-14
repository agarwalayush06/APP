// 1. Student Details System
class Student {
    String name;
    int rollNumber;
    String department;
}

public class Q1_Student {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ananya Sharma";
        s1.rollNumber = 101;
        s1.department = "Computer Science";

        System.out.println("---- Student Details ----");
        System.out.println("Name       : " + s1.name);
        System.out.println("Roll Number: " + s1.rollNumber);
        System.out.println("Department : " + s1.department);
    }
}
