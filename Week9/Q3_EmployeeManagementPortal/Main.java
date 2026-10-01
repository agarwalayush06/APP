import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Employee {
    int id;
    String name;
    String department;

    Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String toString() {
        return id + " - " + name + " - " + department;
    }
}

class EmployeeModel {
    private String username = "admin";
    private String password = "admin123";

    private ArrayList<Employee> employees = new ArrayList<>();

    public boolean login(String username, String password) {
        return this.username.equals(username) &&
               this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public void addEmployee(int id, String name, String department) {
        employees.add(new Employee(id, name, department));
    }

    public String getEmployees() {
        if (employees.isEmpty()) {
            return "No employees found.";
        }

        StringBuilder result = new StringBuilder();

        for (Employee employee : employees) {
            result.append(employee).append("\n");
        }

        return result.toString();
    }
}

class EmployeeView {
    JFrame frame;

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    JMenuBar menuBar;
    JMenu employeeMenu;
    JMenu toolsMenu;
    JMenu exitMenu;

    JMenuItem addEmployeeItem;
    JMenuItem viewEmployeeItem;
    JMenuItem changePasswordItem;
    JMenuItem logoutItem;
    JMenuItem exitApplicationItem;

    public EmployeeView() {
        showLoginWindow();
    }

    private void showLoginWindow() {
        frame = new JFrame("Employee Management Portal");
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);

        frame.add(new JLabel("Password:"));
        frame.add(passwordField);

        frame.add(new JLabel(""));
        frame.add(loginButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showMainWindow() {
        frame.getContentPane().removeAll();
        frame.setTitle("Employee Management Portal");
        frame.setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        employeeMenu = new JMenu("Employee");
        toolsMenu = new JMenu("Tools");
        exitMenu = new JMenu("Exit");

        addEmployeeItem = new JMenuItem("Add Employee");
        viewEmployeeItem = new JMenuItem("View Employee");
        changePasswordItem = new JMenuItem("Change Password");
        logoutItem = new JMenuItem("Logout");
        exitApplicationItem = new JMenuItem("Exit Application");

        employeeMenu.add(addEmployeeItem);
        employeeMenu.add(viewEmployeeItem);

        toolsMenu.add(changePasswordItem);

        exitMenu.add(logoutItem);
        exitMenu.add(exitApplicationItem);

        menuBar.add(employeeMenu);
        menuBar.add(toolsMenu);
        menuBar.add(exitMenu);

        frame.setJMenuBar(menuBar);

        JLabel welcomeLabel =
            new JLabel("Welcome to Employee Management Portal",
                       SwingConstants.CENTER);

        frame.add(welcomeLabel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}

class EmployeeController {
    private EmployeeModel model;
    private EmployeeView view;

    public EmployeeController(EmployeeModel model, EmployeeView view) {
        this.model = model;
        this.view = view;

        view.loginButton.addActionListener(e -> login());

        setupMenuActions();
    }

    private void login() {
        String username = view.usernameField.getText();
        String password =
            new String(view.passwordField.getPassword());

        if (model.login(username, password)) {
            JOptionPane.showMessageDialog(
                view.frame,
                "Login successful!"
            );

            view.showMainWindow();
        } else {
            JOptionPane.showMessageDialog(
                view.frame,
                "Invalid username or password."
            );
        }
    }

    private void setupMenuActions() {
        view.addEmployeeItem.addActionListener(e -> addEmployee());

        view.viewEmployeeItem.addActionListener(e -> viewEmployees());

        view.changePasswordItem.addActionListener(
            e -> changePassword()
        );

        view.logoutItem.addActionListener(e -> logout());

        view.exitApplicationItem.addActionListener(
            e -> System.exit(0)
        );
    }

    private void addEmployee() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField departmentField = new JTextField();

        Object[] fields = {
            "Employee ID:", idField,
            "Employee Name:", nameField,
            "Department:", departmentField
        };

        int result = JOptionPane.showConfirmDialog(
            view.frame,
            fields,
            "Add Employee",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());

                String name = nameField.getText();
                String department = departmentField.getText();

                if (name.isEmpty() || department.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        view.frame,
                        "Please fill all fields."
                    );
                    return;
                }

                model.addEmployee(id, name, department);

                JOptionPane.showMessageDialog(
                    view.frame,
                    "Employee added successfully."
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "Employee ID must be a number."
                );
            }
        }
    }

    private void viewEmployees() {
        JOptionPane.showMessageDialog(
            view.frame,
            model.getEmployees(),
            "Employee List",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void changePassword() {
        JPasswordField oldPassword = new JPasswordField();
        JPasswordField newPassword = new JPasswordField();
        JPasswordField confirmPassword = new JPasswordField();

        Object[] fields = {
            "Old Password:", oldPassword,
            "New Password:", newPassword,
            "Confirm Password:", confirmPassword
        };

        int result = JOptionPane.showConfirmDialog(
            view.frame,
            fields,
            "Change Password",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String oldPass =
                new String(oldPassword.getPassword());

            String newPass =
                new String(newPassword.getPassword());

            String confirmPass =
                new String(confirmPassword.getPassword());

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "New passwords do not match."
                );
                return;
            }

            if (newPass.isEmpty()) {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "New password cannot be empty."
                );
                return;
            }

            if (model.changePassword(oldPass, newPass)) {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "Password changed successfully."
                );
            } else {
                JOptionPane.showMessageDialog(
                    view.frame,
                    "Old password is incorrect."
                );
            }
        }
    }

    private void logout() {
        view.frame.dispose();

        EmployeeView newView = new EmployeeView();
        new EmployeeController(model, newView);
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeModel model = new EmployeeModel();
        EmployeeView view = new EmployeeView();

        new EmployeeController(model, view);
    }
}