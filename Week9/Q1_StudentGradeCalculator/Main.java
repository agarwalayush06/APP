import javax.swing.*;
import java.awt.*;

class StudentModel {
    private String name;
    private double mark1, mark2, mark3;

    public void setData(String name, double mark1, double mark2, double mark3) {
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public double getTotal() {
        return mark1 + mark2 + mark3;
    }

    public double getAverage() {
        return getTotal() / 3;
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 90)
            return "A";
        else if (average >= 75)
            return "B";
        else if (average >= 60)
            return "C";
        else if (average >= 50)
            return "D";
        else
            return "F";
    }

    public String getName() {
        return name;
    }
}

class StudentView {
    JFrame frame;
    JTextField nameField;
    JTextField mark1Field;
    JTextField mark2Field;
    JTextField mark3Field;
    JButton calculateButton;
    JLabel resultLabel;

    public StudentView() {
        frame = new JFrame("Student Grade Calculator");
        frame.setSize(450, 400);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        nameField = new JTextField();
        mark1Field = new JTextField();
        mark2Field = new JTextField();
        mark3Field = new JTextField();

        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result will appear here");

        frame.add(new JLabel("Student Name:"));
        frame.add(nameField);

        frame.add(new JLabel("Subject 1 Marks:"));
        frame.add(mark1Field);

        frame.add(new JLabel("Subject 2 Marks:"));
        frame.add(mark2Field);

        frame.add(new JLabel("Subject 3 Marks:"));
        frame.add(mark3Field);

        frame.add(calculateButton);
        frame.add(new JLabel(""));

        frame.add(new JLabel("Result:"));
        frame.add(resultLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        view.calculateButton.addActionListener(e -> calculate());
    }

    private void calculate() {
        try {
            String name = view.nameField.getText();

            double mark1 = Double.parseDouble(view.mark1Field.getText());
            double mark2 = Double.parseDouble(view.mark2Field.getText());
            double mark3 = Double.parseDouble(view.mark3Field.getText());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "Please enter student name."
                );
                return;
            }

            if (mark1 < 0 || mark1 > 100 ||
                mark2 < 0 || mark2 > 100 ||
                mark3 < 0 || mark3 > 100) {

                JOptionPane.showMessageDialog(
                    view.frame,
                    "Marks must be between 0 and 100."
                );
                return;
            }

            model.setData(name, mark1, mark2, mark3);

            view.resultLabel.setText(
                String.format(
                    "Total: %.2f | Average: %.2f | Grade: %s",
                    model.getTotal(),
                    model.getAverage(),
                    model.getGrade()
                )
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                view.frame,
                "Please enter valid marks."
            );
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentModel model = new StudentModel();
        StudentView view = new StudentView();
        new StudentController(model, view);
    }
}