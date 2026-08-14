// 5. School Records - Multiple Students
class Student {
    String name;
    int age;

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Q5_MultipleStudents {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Priya Nair";
        s1.age = 17;

        Student s2 = new Student();
        s2.name = "Arjun Kumar";
        s2.age = 18;

        System.out.println("---- Student 1 ----");
        s1.display();

        System.out.println("---- Student 2 ----");
        s2.display();
    }
}
