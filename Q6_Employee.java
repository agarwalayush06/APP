// 6. Employee Management System
class Employee {
    String name;
    int empId;
    String designation;
    double salary;

    void display() {
        System.out.println("Employee ID : " + empId);
        System.out.println("Name        : " + name);
        System.out.println("Designation : " + designation);
        System.out.println("Salary      : Rs. " + salary);
    }
}

public class Q6_Employee {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.empId = 1001;
        e1.name = "Sneha Reddy";
        e1.designation = "Software Engineer";
        e1.salary = 55000;

        Employee e2 = new Employee();
        e2.empId = 1002;
        e2.name = "Vikram Singh";
        e2.designation = "Project Manager";
        e2.salary = 85000;

        System.out.println("---- Employee 1 ----");
        e1.display();

        System.out.println("\n---- Employee 2 ----");
        e2.display();
    }
}
