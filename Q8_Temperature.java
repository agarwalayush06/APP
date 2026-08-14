// 8. Weather Monitoring - Celsius to Fahrenheit Conversion
class Temperature {
    double celsius;

    double convertToFahrenheit() {
        // Standard formula: F = (C * 9/5) + 32
        return (celsius * 9 / 5) + 32;
    }

    void display() {
        double fahrenheit = convertToFahrenheit();
        System.out.println("Temperature in Celsius   : " + celsius + " C");
        System.out.println("Temperature in Fahrenheit: " + fahrenheit + " F");
    }
}

public class Q8_Temperature {
    public static void main(String[] args) {
        Temperature t1 = new Temperature();
        t1.celsius = 36.5;
        t1.display();
    }
}
