import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private ChoiceBox<String> inputUnitChoice, outputUnitChoice;
    private TextField inputValueField, outputValueField;

    @Override
    public void start(Stage stage) {
        // Create labels for the input and output units and values
        Label inputUnitLabel = new Label("Input Unit:");
        Label outputUnitLabel = new Label("Output Unit:");
        Label inputValueLabel = new Label("Input Value:");
        Label outputValueLabel = new Label("Output Value:");

        // Create choice boxes for the input and output units
        inputUnitChoice = new ChoiceBox<>();
        inputUnitChoice.getItems().addAll("Yards", "Feet", "Inches", "Centimeters", "Meters", "Miles", "Kilometers");
        inputUnitChoice.setValue("Yards");

        outputUnitChoice = new ChoiceBox<>();
        outputUnitChoice.getItems().addAll("Yards", "Feet", "Inches", "Centimeters", "Meters", "Miles", "Kilometers");
        outputUnitChoice.setValue("Feet");

        // Create input and output fields for the converter
        inputValueField = new TextField();
        outputValueField = new TextField();

        // Create a button to perform the conversion
        Button convertButton = new Button("Convert");
        convertButton.setOnAction(event -> convert());

        // Create a grid pane to arrange the labels, choice boxes, and fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, inputUnitLabel, inputUnitChoice);
        inputGrid.addRow(1, outputUnitLabel, outputUnitChoice);
        inputGrid.addRow(2, inputValueLabel, inputValueField);
        inputGrid.addRow(3, outputValueLabel, outputValueField);

        // Create a horizontal box to hold the convert button
        HBox buttonBox = new HBox(convertButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Create a vertical box to hold the input grid and button box
        VBox vbox = new VBox(inputGrid, buttonBox);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        // Create a scene with the vbox as the root node
        Scene scene = new Scene(vbox, 300, 200);

        // Set the scene on the stage and show the stage
        stage.setScene(scene);
        stage.setTitle("Metric Converter");
        stage.show();
    }

    private void convert() {
        // Get the input unit, output unit, and input value from the GUI
        String inputUnit = inputUnitChoice.getValue();
        String outputUnit = outputUnitChoice.getValue();
        double inputValue = Double.parseDouble(inputValueField.getText());

        // Convert the input value to meters
        double metersValue = convertToMeters(inputUnit, inputValue);

        // Convert the meters value to the output unit
        double outputValue = convertFromMeters(outputUnit, metersValue);

        // Update the output field with the converted value
        outputValueField.setText(Double.toString(outputValue));
    }

    private double convertToMeters(String unit, double value) {
        // Convert the input value to meters
        switch (unit) {
            case "Yards":
                return value * 0.9144;
            case "Feet":
                return value * 0.3048;
            case "Inches":
                return value * 0.0254;
            case "Centimeters":
                return value * 0.01;
            case "Meters":
                return value;
            case "Miles":
                return value * 1609.344;
            case "Kilometers":
                return value * 1000;
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
    
    private double convertFromMeters(String unit, double metersValue) {
        // Convert the meters value to the output unit
        switch (unit) {
            case "Yards":
                return metersValue / 0.9144;
            case "Feet":
                return metersValue / 0.3048;
            case "Inches":
                return metersValue / 0.0254;
            case "Centimeters":
                return metersValue / 0.01;
            case "Meters":
                return metersValue;
            case "Miles":
                return metersValue / 1609.344;
            case "Kilometers":
                return metersValue / 1000;
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
}
    
