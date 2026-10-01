import javax.swing.*;
import java.awt.*;

class VehicleModel {
    private String registrationNumber;
    private String vehicleType;
    private double cost;

    public void calculateCost(String registrationNumber, String vehicleType,
                              boolean generalService, boolean oilChange,
                              boolean brakeService, boolean batteryCheck) {

        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        cost = 0;

        if (generalService)
            cost += 1000;

        if (oilChange)
            cost += 800;

        if (brakeService)
            cost += 1200;

        if (batteryCheck)
            cost += 500;
    }

    public double getCost() {
        return cost;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}

class VehicleView {
    JFrame frame;
    JTextField registrationField;
    JComboBox<String> vehicleTypeBox;
    JCheckBox generalServiceBox;
    JCheckBox oilChangeBox;
    JCheckBox brakeServiceBox;
    JCheckBox batteryCheckBox;
    JButton calculateButton;
    JLabel resultLabel;

    public VehicleView() {
        frame = new JFrame("Vehicle Service Cost Estimator");
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        registrationField = new JTextField();

        vehicleTypeBox = new JComboBox<>(
            new String[]{"Two Wheeler", "Car"}
        );

        generalServiceBox = new JCheckBox("General Service - ₹1000");
        oilChangeBox = new JCheckBox("Oil Change - ₹800");
        brakeServiceBox = new JCheckBox("Brake Service - ₹1200");
        batteryCheckBox = new JCheckBox("Battery Check - ₹500");

        calculateButton = new JButton("Calculate Cost");
        resultLabel = new JLabel("Total cost will appear here");

        frame.add(new JLabel("Vehicle Registration Number:"));
        frame.add(registrationField);

        frame.add(new JLabel("Vehicle Type:"));
        frame.add(vehicleTypeBox);

        frame.add(generalServiceBox);
        frame.add(new JLabel(""));

        frame.add(oilChangeBox);
        frame.add(new JLabel(""));

        frame.add(brakeServiceBox);
        frame.add(new JLabel(""));

        frame.add(batteryCheckBox);
        frame.add(new JLabel(""));

        frame.add(calculateButton);
        frame.add(new JLabel(""));

        frame.add(new JLabel("Result:"));
        frame.add(resultLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class VehicleController {
    private VehicleModel model;
    private VehicleView view;

    public VehicleController(VehicleModel model, VehicleView view) {
        this.model = model;
        this.view = view;

        view.calculateButton.addActionListener(e -> calculate());
    }

    private void calculate() {
        String registrationNumber =
            view.registrationField.getText().trim();

        if (registrationNumber.isEmpty()) {
            JOptionPane.showMessageDialog(
                view.frame,
                "Please enter vehicle registration number."
            );
            return;
        }

        String vehicleType =
            (String) view.vehicleTypeBox.getSelectedItem();

        model.calculateCost(
            registrationNumber,
            vehicleType,
            view.generalServiceBox.isSelected(),
            view.oilChangeBox.isSelected(),
            view.brakeServiceBox.isSelected(),
            view.batteryCheckBox.isSelected()
        );

        view.resultLabel.setText(
            String.format(
                "Total Cost: ₹%.2f",
                model.getCost()
            )
        );
    }
}

public class Main {
    public static void main(String[] args) {
        VehicleModel model = new VehicleModel();
        VehicleView view = new VehicleView();

        new VehicleController(model, view);
    }
}   