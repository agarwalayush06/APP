// 3. Student Registration System
class Student {
    String name;
    int rollNumber;

    void display() {
        System.out.println("---- Student Details ----");
        System.out.println("Name       : " + name);
        System.out.println("Roll Number: " + rollNumber);
    }
}

public class Q3_StudentRegistration {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Rahul Verma";
        s1.rollNumber = 205;
        s1.display();
    }
}
