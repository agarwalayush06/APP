// 2. Library Book Tracking System
class Book {
    String title;
    String author;
    double price;
}

public class Q2_Book {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.title = "The Alchemist";
        b1.author = "Paulo Coelho";
        b1.price = 299.50;

        System.out.println("---- Book Details ----");
        System.out.println("Title : " + b1.title);
        System.out.println("Author: " + b1.author);
        System.out.println("Price : Rs. " + b1.price);
    }
}
