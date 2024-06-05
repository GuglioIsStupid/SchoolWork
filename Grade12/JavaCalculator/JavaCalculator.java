// Guglio 28/05/2024 - 04/06/2024
// A "simple" calculator that can evaluate basic arithmetic expressions and trigonometric functions
// Can't handle complex expressions all-to-well

// Created for the culminating project of my course

package JavaCalculator;

// JFrame Components
import javax.swing.*;

import java.util.Stack;

public class JavaCalculator {
    // list that converts the full word to the respective character
    public static final String[][] funcsAndConsts = {
            {"sin", "s"},
            {"cos", "c"},
            {"tan", "t"},
            {"asin", "a"},
            {"acos", "p"},
            {"atan", "q"},
            {"log", "l"},
            {"exp", "e"},
            {"√", "√"},
            {"π", Math.PI + "" /* Converts to a string */}
    };

    public static final String[] orderOfCommands = {
            "√",
            "l",
            "e",
            "s",
            "c",
            "t",
            "a",
            "p",
            "q",
            "^",
            "*",
            "/",
            "+",
            "-"
    };

    // Apply the operator to the numbers
    public static double applyOperator(char operator, double b, double a) {
        switch (String.valueOf(operator)) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            case "^":
                return Math.pow(a, b);
            case "√":
                return Math.sqrt(b);
            case "l":
                return Math.log(b);
            case "e":
                return Math.exp(b);
            case "s":
               return Math.sin(b);
            case "c":
                return Math.cos(b);
            case "t":
                return Math.tan(b);
            case "a":
                return Math.asin(b);
            case "p":
                return Math.acos(b);
            case "q":
                return Math.atan(b);
            case "π":
                return Math.PI;
            default:
                return 0;
        }
    }

    // Check the precedence of the operators
    public static boolean precedence(char op1, char op2) {
        String op1Str = String.valueOf(op1);
        String op2Str = String.valueOf(op2);
        
        if (op2Str == "(" || op2Str == ")") {
            return false;
        }

        if ((op1Str == "√" || op1Str == "l" || op1Str == "e" || op1Str == "s" || op1Str == "c" 
            || op1Str == "t" || op1Str == "a" || op1Str == "p") && (op2Str == "+" || op2Str == "-" 
            || op2Str == "*" || op2Str == "/" || op2Str == "^")) {
            return false;
        }

        if ((op2Str == "^") && (op2Str == "+" || op2Str == "-" || op2Str == "*" || op2Str == "/")) {
            return false;
        }

        return true;
    }

    // eval function
    public static double eval(String equation) {
    	try {
    		// Since the JavaScript engine was removed in Java 11, we need to make our own eval function
            // This function will evaluate the mathematical expression and return the result
            // We use PEMDAS to evaluate the expression (because thats just how math works)
            // This took longer than needed to figure out.... Why did I have to add trigonometric functions...

            // Remove all spaces from the equation
            equation = equation.replaceAll("\\s", "");

            // also make sure to change "char" to "String"
            String[] tokens = equation.split("(?<=op)|(?=op)".replace("op", "[+\\-*/^√lsetapq()]"));

            // Replace the full words with the respective characters
            for (String[] funcAndConst : funcsAndConsts) {
                equation = equation.replace(funcAndConst[0], funcAndConst[1]);
            }

            // Create two stacks, one for the numbers and one for the operators
            Stack<Double> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();

            // Loop through the tokens
            for (String token : tokens) {
                // If the token is a number, push it to the numbers stack
                if (token.matches("[0-9]+")) {
                    numbers.push(Double.parseDouble(token));
                } else if (token.matches("[0-9]+\\.[0-9]+")) {
                    numbers.push(Double.parseDouble(token));
                } else if (token.matches("[+\\-*/^√lsetapq]")) {
                   // Add the operator to the operators stack
                    char operator = token.charAt(0);

                    // If the operators stack is not empty and the precedence of the operator is less than the precedence of the operator at the top of the stack
                    // Pop the operator from the stack and apply it to the numbers
                    while (!operators.isEmpty() && precedence(operator, operators.peek())) {
                        numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                    }

                    // Push the operator to the stack
                    operators.push(operator);
                } else if (token.equals("(")) {
                    operators.push(token.charAt(0));
                } else if (token.equals(")")) {
                    while (operators.peek() != '(') {
                        numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                }
            }

            // Loop through the remaining operators, if numbers has more than 1 element
           /*  while (!operators.isEmpty()) {
                // if its a ( or ) operator, pop it
                if (operators.peek() == '(' || operators.peek() == ')') {
                    operators.pop();
                }
                
                if (numbers.size() > 1) {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                } else {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), 0));
                }
            } */

            // solve in order of commands
            for (String command : orderOfCommands) {
                for (int i = 0; i < operators.size(); i++) {
                    if (String.valueOf(operators.get(i)).equals(command)) {
                        numbers.push(applyOperator(operators.get(i), numbers.pop(), numbers.pop()));
                        operators.remove(i);
                        i--;
                    }
                }
            }

            // Return the result
            return numbers.pop();
    	} catch (Exception e) {
            System.out.println("An error occurred. Is it too complex? or is it a syntax error?");
    		return 0;
    	}
        
    }


    // JFrame Components
    private static JFrame frame;
    private static JTextField textField;
    private static JButton[] numberButtons = new JButton[10];
    private static JButton[] functionButtons = new JButton[17];
    private static JButton[] allButtons = new JButton[27];
    private static JButton decButton, equButton, delButton, clrButton;
    private static JPanel panel;

    // Modify the textfield with each button press

    public static void main(String[] args) {
    	frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 850);
        frame.setTitle("Calculator");
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(textField.getFont().deriveFont(24f));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(numberButtons[i].getFont().deriveFont(18f));
        }

        functionButtons[0] = new JButton("+");
        functionButtons[1] = new JButton("-");
        functionButtons[2] = new JButton("*");
        functionButtons[3] = new JButton("/");
        functionButtons[4] = new JButton("(");
        functionButtons[5] = new JButton(")");
        functionButtons[6] = new JButton("^");
        functionButtons[7] = new JButton("√");
        functionButtons[8] = new JButton("log");
        functionButtons[9] = new JButton("exp");
        functionButtons[10] = new JButton("sin");
        functionButtons[11] = new JButton("cos");
        functionButtons[12] = new JButton("tan");
        functionButtons[13] = new JButton("asin");
        functionButtons[14] = new JButton("acos");
        functionButtons[15] = new JButton("atan");
        functionButtons[16] = new JButton("π");

        for (int i = 0; i < 17; i++) {
            functionButtons[i].setFont(functionButtons[i].getFont().deriveFont(18f));
        }

        // now add the buttons to "allButtons"
        for (int i = 0; i < 10; i++) {
            allButtons[i] = numberButtons[i];
        }

        for (int i = 0; i < 17; i++) {
            allButtons[i + 10] = functionButtons[i];
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 500, 550);
        panel.setLayout(null);

        int x = 0;
        int y = 0;
        int width = 100;
        int height = 50;

        for (int i = 0; i < 27; i++) {
            allButtons[i].setBounds(x, y, width, height);
            x += width;
            if (x >= 300) {
                x = 0;
                y += height;
            }

            panel.add(allButtons[i]);
        }

        frame.setLayout(null);
        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            numberButtons[i].addActionListener(e -> textField.setText(textField.getText() + finalI));
        }

        functionButtons[0].addActionListener(e -> textField.setText(textField.getText() + "+"));
        functionButtons[1].addActionListener(e -> textField.setText(textField.getText() + "-"));
        functionButtons[2].addActionListener(e -> textField.setText(textField.getText() + "*"));
        functionButtons[3].addActionListener(e -> textField.setText(textField.getText() + "/"));
        functionButtons[4].addActionListener(e -> textField.setText(textField.getText() + "("));
        functionButtons[5].addActionListener(e -> textField.setText(textField.getText() + ")"));
        functionButtons[6].addActionListener(e -> textField.setText(textField.getText() + "^"));
        functionButtons[7].addActionListener(e -> textField.setText(textField.getText() + "√"));
        functionButtons[8].addActionListener(e -> textField.setText(textField.getText() + "log("));
        functionButtons[9].addActionListener(e -> textField.setText(textField.getText() + "exp("));
        functionButtons[10].addActionListener(e -> textField.setText(textField.getText() + "sin("));
        functionButtons[11].addActionListener(e -> textField.setText(textField.getText() + "cos("));
        functionButtons[12].addActionListener(e -> textField.setText(textField.getText() + "tan("));
        functionButtons[13].addActionListener(e -> textField.setText(textField.getText() + "asin("));
        functionButtons[14].addActionListener(e -> textField.setText(textField.getText() + "acos("));
        functionButtons[15].addActionListener(e -> textField.setText(textField.getText() + "atan("));
        functionButtons[16].addActionListener(e -> textField.setText(textField.getText() + "π"));

        clrButton = new JButton("Clear");
        delButton = new JButton("Delete");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton.setBounds(0, 450, 100, 50);
        delButton.setBounds(100, 450, 100, 50);
        decButton.setBounds(200, 450, 100, 50);
        equButton.setBounds(0, 500, 300, 50);

        clrButton.addActionListener(e -> textField.setText(""));
        delButton.addActionListener(e -> {
            String str = textField.getText();
            textField.setText("");
            for (int i = 0; i < str.length() - 1; i++) {
                textField.setText(textField.getText() + str.charAt(i));
            }
        });
        decButton.addActionListener(e -> textField.setText(textField.getText() + "."));
        equButton.addActionListener(e -> {
            String str = textField.getText();
            textField.setText("");
            textField.setText(String.valueOf(eval(str)));
        });

        panel.add(clrButton);
        panel.add(delButton);
        panel.add(decButton);
        panel.add(equButton);
    }
}   
