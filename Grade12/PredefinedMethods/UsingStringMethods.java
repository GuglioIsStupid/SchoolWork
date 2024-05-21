package PredefinedMethods;

// Guglio - 2024/04/10

import javax.swing.*;

public class UsingStringMethods {
    static JFrame frame = new JFrame("SUPER STRING SOFTWARE");
    static JLabel titleLabel = new JLabel("SUPER STRING SOFTWARE");
    // All functions:
    // Enter a string. The program outputs the number of characters.
    static JLabel lengthLabel = new JLabel("Enter a string. The program outputs the number of characters.");
    static JTextField lengthField = new JTextField();
    static JButton lengthButton = new JButton("Length");
    static JLabel lengthOutput = new JLabel("...");
    // Enter a string using all lowercase letters. The program outputs the string in all uppercase letters.
    static JLabel upperLabel = new JLabel("Enter a string using all lowercase letters. The program outputs the string in uppercase letters.");
    static JTextField upperField = new JTextField();
    static JButton upperButton = new JButton("Uppercase");
    static JLabel upperOutput = new JLabel("...");
    // Enter a string. The program will output a character at the 4th character.
    static JLabel charLabel = new JLabel("Enter a string. The program will output a character at the 4th character.");
    static JTextField charField = new JTextField();
    static JButton charButton = new JButton("Character");
    static JLabel charOutput = new JLabel("...");
    // Enter two strings. The program will output the whether the two strings are identical.
    static JLabel equalLabel = new JLabel("Enter two strings. The program will output the whether the two strings are identical.");
    static JTextField equalField1 = new JTextField();
    static JTextField equalField2 = new JTextField();
    static JButton equalButton = new JButton("Equal");
    static JLabel equalOutput = new JLabel("...");
    // Enter a string and a letter. The program will output the number of times the letter appears in the string.
    static JLabel countLabel = new JLabel("Enter a string and a letter. The program will output the number of times the letter appears in the string.");
    static JTextField countField1 = new JTextField();
    static JTextField countField2 = new JTextField();
    static JButton countButton = new JButton("Count");
    static JLabel countOutput = new JLabel("...");
    // Enter a string that is atleast 8 characters long. The program will output the first characters 4-6.
    static JLabel subLabel = new JLabel("Enter a string that is atleast 8 characters long. The program will output the first characters 4-6.");
    static JTextField subField = new JTextField();
    static JButton subButton = new JButton("Substring");
    static JLabel subOutput = new JLabel("...");
    // Enter a string that contains one "a". The program will output the string with the "a" replaced with "x".
    static JLabel replaceLabel = new JLabel("Enter a string that contains one 'a'. The program will output the string with the 'a' replaced with 'x'.");
    static JTextField replaceField = new JTextField();
    static JButton replaceButton = new JButton("Replace");
    static JLabel replaceOutput = new JLabel("...");
    
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

        // Set all the positions of the components Grid. Each line has 3 different functions
        lengthLabel.setBounds(10, 50, 800, 30);
        lengthLabel.setFont(lengthField.getFont().deriveFont(6.0f));
        lengthField.setBounds(10, 80, 200, 30);
        lengthButton.setBounds(10, 120, 200, 30);
        lengthOutput.setBounds(10, 160, 200, 30);

        upperLabel.setBounds(290, 50, 500, 30);
        upperLabel.setFont(upperField.getFont().deriveFont(6.0f));
        upperField.setBounds(290, 80, 200, 30);
        upperButton.setBounds(290, 120, 200, 30);
        upperOutput.setBounds(290, 160, 200, 30);

        charLabel.setBounds(570, 50, 500, 30);
        charLabel.setFont(charField.getFont().deriveFont(6.0f));
        charField.setBounds(570, 80, 200, 30);
        charButton.setBounds(570, 120, 200, 30);
        charOutput.setBounds(570, 160, 200, 30);

        equalLabel.setBounds(10, 200, 800, 30);
        equalLabel.setFont(equalField1.getFont().deriveFont(6.0f));
        equalField1.setBounds(10, 230, 200, 30);
        equalField2.setBounds(10, 270, 200, 30);
        equalButton.setBounds(10, 310, 200, 30);
        equalOutput.setBounds(10, 350, 200, 30);

        countLabel.setBounds(290, 200, 800, 30);
        countLabel.setFont(countField1.getFont().deriveFont(6.0f));
        countField1.setBounds(290, 230, 200, 30);
        countField2.setBounds(290, 270, 200, 30);
        countButton.setBounds(290, 310, 200, 30);
        countOutput.setBounds(290, 350, 200, 30);

        subLabel.setBounds(570, 200, 800, 30);
        subLabel.setFont(subField.getFont().deriveFont(6.0f));
        subField.setBounds(570, 230, 200, 30);
        subButton.setBounds(570, 270, 200, 30);
        subOutput.setBounds(570, 310, 200, 30);

        replaceLabel.setBounds(10, 400, 800, 30);
        replaceLabel.setFont(replaceField.getFont().deriveFont(6.0f));
        replaceField.setBounds(10, 430, 200, 30);
        replaceButton.setBounds(10, 470, 200, 30);
        replaceOutput.setBounds(10, 510, 200, 30);
        
        // Add all the components to the frame
        frame.add(titleLabel);

        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(lengthButton);
        frame.add(lengthOutput);

        frame.add(upperLabel);
        frame.add(upperField);
        frame.add(upperButton);
        frame.add(upperOutput);

        frame.add(charLabel);
        frame.add(charField);
        frame.add(charButton);
        frame.add(charOutput);

        frame.add(equalLabel);
        frame.add(equalField1);
        frame.add(equalField2);
        frame.add(equalButton);
        frame.add(equalOutput);

        frame.add(countLabel);
        frame.add(countField1);
        frame.add(countField2);
        frame.add(countButton);
        frame.add(countOutput);

        frame.add(subLabel);
        frame.add(subField);
        frame.add(subButton);
        frame.add(subOutput);

        frame.add(replaceLabel);
        frame.add(replaceField);
        frame.add(replaceButton);
        frame.add(replaceOutput);

        // Make the frame visible
        frame.setVisible(true);

        // Add action listeners to the buttons
        lengthButton.addActionListener(e -> {
            lengthOutput.setText(Integer.toString(lengthField.getText().length()));
        });

        upperButton.addActionListener(e -> {
            upperOutput.setText(upperField.getText().toUpperCase());
        });

        charButton.addActionListener(e -> {
            try {
                charOutput.setText(Character.toString(charField.getText().charAt(3)));
            } catch (Exception ex) {
                charOutput.setText("String is less than 4 characters");
            }
        });

        equalButton.addActionListener(e -> {
            equalOutput.setText(Boolean.toString(equalField1.getText().equals(equalField2.getText())));
        });

        countButton.addActionListener(e -> {
            countOutput.setText(Integer.toString(countField1.getText().length() - countField1.getText().replace(countField2.getText(), "").length()));
        });

        subButton.addActionListener(e -> {
            subOutput.setText(subField.getText().substring(3, 6));
        });

        replaceButton.addActionListener(e -> {
            replaceOutput.setText(replaceField.getText().replace("a", "x"));
        });
    }
}