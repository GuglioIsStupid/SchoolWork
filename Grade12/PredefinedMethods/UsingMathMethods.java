package PredefinedMethods;

// Guglio - 2024/04/10

import javax.swing.*;

public class UsingMathMethods {
    static JFrame frame = new JFrame("MAGICAL MATH METHODS");
    static JLabel titleLabel = new JLabel("MAGICAL MATH METHODS");
    // All functions:
    // Enter two numbers and click the button. The program will display the largest of the two numbers
    static JLabel largestLabel = new JLabel("Input two numbers. The program will display the largest of the two numbers.");
    static JTextField firstNumberField = new JTextField();
    static JTextField secondNumberField = new JTextField();
    static JButton largestButton = new JButton("Largest");
    static JLabel largestOutput = new JLabel("...");
    // Enter two numbers and click the button. The program will display the smallest of the two numbers
    static JLabel smallestLabel = new JLabel("Input two numbers. The program will display the smallest of the two numbers.");
    static JTextField firstNumberField2 = new JTextField();
    static JTextField secondNumberField2 = new JTextField();
    static JButton smallestButton = new JButton("Smallest");
    static JLabel smallestOutput = new JLabel("...");
    // Enter two numbers and click the button. The program will display the first number to the power of the second number
    static JLabel powerLabel = new JLabel("Input two numbers. The program will display number 1 to the power of number 2.");
    static JTextField firstNumberField3 = new JTextField();
    static JTextField secondNumberField3 = new JTextField();
    static JButton powerButton = new JButton("Power");
    static JLabel powerOutput = new JLabel("...");
    // Enter the length of two sides of a right angle triangle. The program will display the hypotenuse of the triangle
    static JLabel hypotenuseLabel = new JLabel("Enter two sides of a right angle triangle. The program will display the hypotenuse.");
    static JTextField side1Field = new JTextField();
    static JTextField side2Field = new JTextField();
    static JButton hypotenuseButton = new JButton("Hypotenuse");
    static JLabel hypotenuseOutput = new JLabel("...");
    // Enter a decimal value. The program will display the value rounded down to the nearest integer
    static JLabel roundLabel = new JLabel("Enter a decimal value. The program will display the value rounded down.");
    static JTextField decimalField = new JTextField();
    static JButton roundButton = new JButton("Round");
    static JLabel roundOutput = new JLabel("...");
    // Enter a decimal value. THe program will display the value rounded up to the nearest integer
    static JLabel ceilLabel = new JLabel("Enter a decimal value. The program will display the value rounded up.");
    static JTextField decimalField2 = new JTextField();
    static JButton ceilButton = new JButton("Ceil");
    static JLabel ceilOutput = new JLabel("...");
    // Enter an interger. The program will display the square root of the integer
    static JLabel sqrtLabel = new JLabel("Enter an integer. The program will display the square root of the integer.");
    static JTextField sqrtField = new JTextField();
    static JButton sqrtButton = new JButton("Square Root");
    static JLabel sqrtOutput = new JLabel("...");
    // Enter an angle in degrees. The program will display the sine of the angle
    static JLabel sinLabel = new JLabel("Enter an angle in degrees. The program will display the sine of the angle.");
    static JTextField sinField = new JTextField();
    static JButton sinButton = new JButton("Sine");
    static JLabel sinOutput = new JLabel("...");

    static final int WINDOW_WIDTH = 850;
    static final int WINDOW_HEIGHT = 775;

    public static void main(String args[]) {
        // Setup the frame
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label (blue)
        titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
        titleLabel.setForeground(java.awt.Color.BLUE);
        titleLabel.setBounds(255, 10, 500, 30);

        // Set all the positions of the components
        largestLabel.setBounds(50, 50, 800, 30);
        largestLabel.setFont(largestLabel.getFont().deriveFont(6.0f));
        firstNumberField.setBounds(50, 100, 200, 30);
        secondNumberField.setBounds(50, 150, 200, 30);
        largestButton.setBounds(50, 200, 200, 30);
        largestOutput.setBounds(50, 250, 200, 30);

        smallestLabel.setBounds(300, 50, 800, 30);
        smallestLabel.setFont(smallestLabel.getFont().deriveFont(6.0f));
        firstNumberField2.setBounds(300, 100, 200, 30);
        secondNumberField2.setBounds(300, 150, 200, 30);
        smallestButton.setBounds(300, 200, 200, 30);
        smallestOutput.setBounds(300, 250, 200, 30);

        powerLabel.setBounds(550, 50, 800, 30);
        powerLabel.setFont(powerLabel.getFont().deriveFont(6.0f));
        firstNumberField3.setBounds(550, 100, 200, 30);
        secondNumberField3.setBounds(550, 150, 200, 30);
        powerButton.setBounds(550, 200, 200, 30);
        powerOutput.setBounds(550, 250, 200, 30);

        hypotenuseLabel.setBounds(50, 300, 800, 30);
        hypotenuseLabel.setFont(hypotenuseLabel.getFont().deriveFont(6.0f));
        side1Field.setBounds(50, 350, 200, 30);
        side2Field.setBounds(50, 400, 200, 30);
        hypotenuseButton.setBounds(50, 450, 200, 30);
        hypotenuseOutput.setBounds(50, 500, 200, 30);

        roundLabel.setBounds(300, 300, 800, 30);
        roundLabel.setFont(roundLabel.getFont().deriveFont(6.0f));
        decimalField.setBounds(300, 350, 200, 30);
        roundButton.setBounds(300, 400, 200, 30);
        roundOutput.setBounds(300, 450, 200, 30);

        ceilLabel.setBounds(550, 300, 800, 30);
        ceilLabel.setFont(ceilLabel.getFont().deriveFont(6.0f));
        decimalField2.setBounds(550, 350, 200, 30);
        ceilButton.setBounds(550, 400, 200, 30);
        ceilOutput.setBounds(550, 450, 200, 30);

        sqrtLabel.setBounds(50, 550, 800, 30);
        sqrtLabel.setFont(sqrtLabel.getFont().deriveFont(6.0f));
        sqrtField.setBounds(50, 600, 200, 30);
        sqrtButton.setBounds(50, 650, 200, 30);
        sqrtOutput.setBounds(50, 700, 200, 30);

        sinLabel.setBounds(300, 550, 800, 30);
        sinLabel.setFont(sinLabel.getFont().deriveFont(6.0f));
        sinField.setBounds(300, 600, 200, 30);
        sinButton.setBounds(300, 650, 200, 30);
        sinOutput.setBounds(300, 700, 200, 30);

        // Add all the components to the frame
        frame.add(titleLabel);

        frame.add(largestLabel);
        frame.add(firstNumberField);
        frame.add(secondNumberField);
        frame.add(largestButton);
        frame.add(largestOutput);

        frame.add(smallestLabel);
        frame.add(firstNumberField2);
        frame.add(secondNumberField2);
        frame.add(smallestButton);
        frame.add(smallestOutput);

        frame.add(powerLabel);
        frame.add(firstNumberField3);
        frame.add(secondNumberField3);
        frame.add(powerButton);
        frame.add(powerOutput);

        frame.add(hypotenuseLabel);
        frame.add(side1Field);
        frame.add(side2Field);
        frame.add(hypotenuseButton);
        frame.add(hypotenuseOutput);

        frame.add(roundLabel);
        frame.add(decimalField);
        frame.add(roundButton);
        frame.add(roundOutput);

        frame.add(ceilLabel);
        frame.add(decimalField2);
        frame.add(ceilButton);
        frame.add(ceilOutput);
        
        frame.add(sqrtLabel);
        frame.add(sqrtField);
        frame.add(sqrtButton);
        frame.add(sqrtOutput);

        frame.add(sinLabel);
        frame.add(sinField);
        frame.add(sinButton);
        frame.add(sinOutput);

        // Make the frame visible
        frame.setVisible(true);

        // Add action listeners to the buttons (convert value to string)
        largestButton.addActionListener(e -> {
            double firstNumber = Double.parseDouble(firstNumberField.getText());
            double secondNumber = Double.parseDouble(secondNumberField.getText());
            double largest = Math.max(firstNumber, secondNumber);
            largestOutput.setText(Double.toString(largest));
        });

        smallestButton.addActionListener(e -> {
            double firstNumber = Double.parseDouble(firstNumberField2.getText());
            double secondNumber = Double.parseDouble(secondNumberField2.getText());
            double smallest = Math.min(firstNumber, secondNumber);
            smallestOutput.setText(Double.toString(smallest));
        });

        powerButton.addActionListener(e -> {
            double firstNumber = Double.parseDouble(firstNumberField3.getText());
            double secondNumber = Double.parseDouble(secondNumberField3.getText());
            double power = Math.pow(firstNumber, secondNumber);
            powerOutput.setText(Double.toString(power));
        });

        hypotenuseButton.addActionListener(e -> {
            double side1 = Double.parseDouble(side1Field.getText());
            double side2 = Double.parseDouble(side2Field.getText());
            double hypotenuse = Math.hypot(side1, side2);
            hypotenuseOutput.setText(Double.toString(hypotenuse));
        });

        roundButton.addActionListener(e -> {
            double decimal = Double.parseDouble(decimalField.getText());
            double rounded = Math.floor(decimal);
            roundOutput.setText(Double.toString(rounded));
        });

        ceilButton.addActionListener(e -> {
            double decimal = Double.parseDouble(decimalField2.getText());
            double ceiled = Math.ceil(decimal);
            ceilOutput.setText(Double.toString(ceiled));
        });

        sqrtButton.addActionListener(e -> {
            double number = Double.parseDouble(sqrtField.getText());
            double sqrt = Math.sqrt(number);
            sqrtOutput.setText(Double.toString(sqrt));
        });

        sinButton.addActionListener(e -> {
            double angle = Double.parseDouble(sinField.getText());
            double sin = Math.sin(Math.toRadians(angle));
            sinOutput.setText(Double.toString(sin));
        });

    }
}