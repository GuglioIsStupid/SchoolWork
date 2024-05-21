package FirstFewPrograms;

// Guglio - 2024/02/20

import javax.swing.*;

public class MakingAPizza {
    // create our window
    static JFrame frame = new JFrame("Putrid Pizza");
    // JFrame components we need: labels, text fields, and a button
    static JLabel pizzaSizeLabel = new JLabel("Enter the diameter of the pizza (CM): ");
    static JTextField pizzaSizeField = new JTextField();
    static JButton calculateButton = new JButton("Calculate");
    static JLabel outputField = new JLabel("");
    static JLabel subtotalLabel = new JLabel("Subtotal: ");
    static JLabel taxesLabel = new JLabel("Taxes: ");
    static JLabel grandTotalLabel = new JLabel("Grand Total: ");
    static JLabel nameLabel = new JLabel("Putrid Pizza");
    // section separators
    static JSeparator horzLine = new JSeparator(SwingConstants.HORIZONTAL);
    static JSeparator vertLine = new JSeparator(SwingConstants.VERTICAL);

    // constants
    static final double LABOUR_COST = 0.75;
    static final double RENT_COST = 0.99;
    static final double INGREDIENT_COST = 0.50;

    //850x650 window
    static final int WINDOW_WIDTH = 850;
    static final int WINDOW_HEIGHT = 650;

    public static void main(String args[]) {
        // Setup the window
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Update our fonts
        pizzaSizeLabel.setFont(pizzaSizeLabel.getFont().deriveFont(20.0f));
        pizzaSizeField.setFont(pizzaSizeField.getFont().deriveFont(20.0f));
        calculateButton.setFont(calculateButton.getFont().deriveFont(20.0f));
        outputField.setFont(outputField.getFont().deriveFont(20.0f));
        subtotalLabel.setFont(subtotalLabel.getFont().deriveFont(20.0f));
        taxesLabel.setFont(taxesLabel.getFont().deriveFont(20.0f));
        grandTotalLabel.setFont(grandTotalLabel.getFont().deriveFont(20.0f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(30.0f));

        pizzaSizeLabel.setBounds(275, 50, 500, 30);
        pizzaSizeField.setBounds(275, 100, 200, 30);
        calculateButton.setBounds(500, 100, 200, 30);
        outputField.setBounds(275, 150, 550, 30);
        subtotalLabel.setBounds(275, 225, 200, 30);
        taxesLabel.setBounds(275, 275, 200, 30);
        grandTotalLabel.setBounds(275, 325, 200, 30);
        nameLabel.setBounds(50, 250, 200, 30); 
        horzLine.setBounds(250, 200, 800, 30);
        vertLine.setBounds(250, 0, 30, 600);
    

        // Add our components
        frame.add(pizzaSizeLabel);
        frame.add(pizzaSizeField);
        frame.add(calculateButton);
        frame.add(outputField);
        frame.add(subtotalLabel);
        frame.add(taxesLabel);
        frame.add(grandTotalLabel);
        frame.add(nameLabel);
        frame.add(horzLine);
        frame.add(vertLine);

        // make the window visible
        frame.setVisible(true);

        // add an action listener to the button
        calculateButton.addActionListener(e -> {
            // Figure out the subtotal, taxes, and grand total
            double pizzaSize = Double.parseDouble(pizzaSizeField.getText());
            double subtotal = LABOUR_COST + RENT_COST + (pizzaSize * INGREDIENT_COST);
            double taxes = subtotal * 0.13;
            double grandTotal = subtotal + taxes;

            // output a message based on the size of the pizza
            if (pizzaSize >= 1 && pizzaSize <= 15) {
                outputField.setText("We are going to make you a cute little pizza!");
            } else if (pizzaSize > 20 && pizzaSize < 40) {
                outputField.setText("This will be delicious!");
            } else if (pizzaSize > 40) {
                outputField.setText("Whoa, big pizza! You might need a truck to get this home!");
            }

            // output the subtotal, taxes, and grand total
            subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subtotal));
            taxesLabel.setText("Taxes: $" + String.format("%.2f", taxes));
            grandTotalLabel.setText("Grand Total: $" + String.format("%.2f", grandTotal));
        });
    }
}
