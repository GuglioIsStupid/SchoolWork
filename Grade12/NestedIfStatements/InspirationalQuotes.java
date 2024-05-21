package NestedIfStatements;

// Guglio - 2024/03/18

import javax.swing.*;
import java.util.Random;

public class InspirationalQuotes {
	static JFrame frame = new JFrame("Positive Practice");

	static JLabel quoteLabel = new JLabel("Positive Practice");
	static JLabel descriptionLabel = new JLabel("Click the generate quote button in order to be inspired!");

	static JButton generateButton = new JButton("Generate Quote");

	static JLabel outputField = new JLabel("");
	static JLabel authorLabel = new JLabel("");

	static final int WINDOW_WIDTH = 750;
	static final int WINDOW_HEIGHT = 450;

	static int lastQuote = 0; // used to prevent the same quote from being generated twice in a row

	public static void main(String args[]) {
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set our font sizes (title is 30, sub's are 20, and normal text are 15)
		quoteLabel.setFont(quoteLabel.getFont().deriveFont(30.0f));
		descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(20.0f));
		generateButton.setFont(generateButton.getFont().deriveFont(20.0f));
		outputField.setFont(outputField.getFont().deriveFont(java.awt.Font.ITALIC).deriveFont(15.0f));
		authorLabel.setFont(authorLabel.getFont().deriveFont(java.awt.Font.ITALIC).deriveFont(15.0f));

		// Set our alignment to center
		quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		generateButton.setHorizontalAlignment(SwingConstants.CENTER);
		outputField.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the bounds on the window
		quoteLabel.setBounds(0, 50, 750, 30);
		descriptionLabel.setBounds(0, 100, 750, 30);
		generateButton.setBounds(250, 150, 200, 30);
		outputField.setBounds(0, 200, 750, 30);
		authorLabel.setBounds(0, 250, 750, 30);
		
		// Add our components to the frame
		frame.add(quoteLabel);
		frame.add(descriptionLabel);
		frame.add(generateButton);
		frame.add(outputField);
		frame.add(authorLabel);

		// Make out frame visible and center it to the desktop
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		generateButton.addActionListener(e -> { // Generate out quote (0-7) giving the quote and the author
			Random rand = new Random();
			// set random seed
			rand.setSeed(System.currentTimeMillis()); // Default seed is usually 42, so we set it to the current time to prevent getting the same result order with each start up
			int n = rand.nextInt(7); // 8 possible quotes
			while (n == lastQuote) { // While statement just in case it returns the same quote twice
				n = rand.nextInt(7);
			}
			lastQuote = n; // Save our last quote to prevent it from giving the same one multiple times
			String quote = "";
			String author = "";
			
			switch (n) { // Figure out which quote we will be getting
				case 0:
					quote = "We talk on principal, but act on motivation.";
					author = "- Walter Savage Landor";
					break;
				case 1:
					quote = "The secret of getting ahead is getting started.";
					author = "- Mark Twain";
					break;
				case 2:
					quote = "Winners never quit, and quitters never win.";
					author = "- Vince Lombardi";
					break;
				case 3:
					quote = "When the going gets tough, the tough get going.";
					author = "- Joseph P. Kennedy";
					break;
				case 4:
					quote = "The best way to predict the future is to create it.";
					author = "- Abraham Lincoln";
					break;
				case 5:
					quote = "Always make a total effort, even when the odds are against you.";
					author = "- Arnold Palmer";
					break;
				case 6:
					quote = "Don't be afraid to give up the good to go for the great.";
					author = "- John D. Rockefeller";
					break;
				case 7:
					quote = "Don't let the fear of losing be greater than the excitement of winning.";
					author = "Robert Kiyosaki";
					break;
			}
			// Set our results to the output fields
			outputField.setText(quote);
			authorLabel.setText(author);
		});
	}
}
