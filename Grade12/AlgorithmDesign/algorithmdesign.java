package AlgorithmDesign;

// Guglio - 2024/05/21

import javax.swing.*;

public class algorithmdesign {
    // Constants
    // The value of each coin in vrobits
    private static final int DROBZIT = 100_000;
    private static final int CLICKWICK = 50_000;
    private static final int GAZOONTIGHT = 10_000;
    private static final int FRAZIONT = 1_000;
    private static final int BLOINTOINT = 500;

    // GUI Components
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel vrobitsLabel;
    private static JTextField vrobitsField;
    private static JButton calculateButton;
    private static JLabel drobzitLabel;
    private static JLabel clickwickLabel;
    private static JLabel gazoontightLabel;
    private static JLabel frazointLabel;
    private static JLabel blointointLabel;
    private static JLabel leftoverLabel;

    public static void main(String[] args) {
        // Create the GUI
        frame = new JFrame("Zirboin Financial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        panel = new JPanel();
        frame.add(panel);

        vrobitsLabel = new JLabel("Enter the number of vrobits:");
        panel.add(vrobitsLabel);

        vrobitsField = new JTextField(10);
        panel.add(vrobitsField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculate());
        panel.add(calculateButton);

        drobzitLabel = new JLabel("Drobzits: 0");
        panel.add(drobzitLabel);

        clickwickLabel = new JLabel("Clickwicks: 0");
        panel.add(clickwickLabel);

        gazoontightLabel = new JLabel("Gazoontights: 0");
        panel.add(gazoontightLabel);

        frazointLabel = new JLabel("Frazoints: 0");
        panel.add(frazointLabel);

        blointointLabel = new JLabel("Blointoints: 0");
        panel.add(blointointLabel);

        leftoverLabel = new JLabel("Leftover vrobits: 0");
        panel.add(leftoverLabel);

        frame.setVisible(true);
    }

    private static void calculate() {
        // Get the number of vrobits from the text field
        int vrobits = Integer.parseInt(vrobitsField.getText());

        // Calculate the number of each coin
        int drobzits = vrobits / DROBZIT;
        vrobits %= DROBZIT;

        int clickwicks = vrobits / CLICKWICK;
        vrobits %= CLICKWICK;

        int gazoontights = vrobits / GAZOONTIGHT;
        vrobits %= GAZOONTIGHT;

        int frazoints = vrobits / FRAZIONT;
        vrobits %= FRAZIONT;

        int blointoints = vrobits / BLOINTOINT;
        vrobits %= BLOINTOINT;

        // Display the results
        drobzitLabel.setText("Drobzits: " + drobzits);
        clickwickLabel.setText("Clickwicks: " + clickwicks);
        gazoontightLabel.setText("Gazoontights: " + gazoontights);
        frazointLabel.setText("Frazoints: " + frazoints);
        blointointLabel.setText("Blointoints: " + blointoints);
        leftoverLabel.setText("Leftover vrobits: " + vrobits);
    }
}
