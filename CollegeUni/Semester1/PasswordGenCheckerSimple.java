import java.util.Scanner;

public class PasswordGenCheckerSimple {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int MIN_LENGTH = 12; // Most websites have a minimum password length of 12 characters
        final int MAX_LENGTH = 92; // And most websites have a maximum password length of 92 characters
    
        final int MIN_DIGITS = 2;
        final int MIN_SPECIAL = 2;
        final int MIN_UPPER = 2;
        final int MIN_LOWER = 2;
    
        final String DIGITS_STRING = "0123456789";
        final String SPECIAL_STRING = "!@#$%^&*()_+{}|:<>?~";
        final String CHARACTERS_STRING = "abcdefghijklmnopqrstuvwxyz";

        String prompt = "";

        do {
            System.out.println("Enter a password to check, or type 'exit' to exit");
            System.out.println("Or type 'generate' to generate a medium-strong password: ");

            prompt = input.nextLine();

            System.out.println("You entered: " + prompt);

            if (prompt.equals("exit")) {
                // Do nothing
            } else if (prompt.equals("generate")) {
                int length = 0;
                do {
                    System.out.println("Enter the length of the password: ");
                    length = input.nextInt();
                    input.nextLine(); // Clear our input buffer (cuz scanner is a bit weird)
                    // Check if the length is valid
                    if (length < MIN_LENGTH) {
                        System.out.println("Password is too short");
                    } else if (length > MAX_LENGTH) {
                        System.out.println("Password is too long");
                    } else {
                        // Generate the password
                        String password = "";
                        for (int i = 0; i < length; i++) {
                            int random = (int) (Math.random() * 4);
                            if (random == 0) {
                                password += DIGITS_STRING.charAt((int) (Math.random() * DIGITS_STRING.length())); // Adds a random digit
                            } else if (random == 1) {
                                password += SPECIAL_STRING.charAt((int) (Math.random() * SPECIAL_STRING.length())); // Adds a random special character
                            } else if (random == 2) {
                                password += Character.toUpperCase(CHARACTERS_STRING.charAt((int) (Math.random() * CHARACTERS_STRING.length()))); // Adds a random uppercase character
                            } else {
                                password += CHARACTERS_STRING.charAt((int) (Math.random() * CHARACTERS_STRING.length())); // Adds a random  lowercase character
                            }
                        }
                        System.out.println("Generated password: " + password);
                    }
                } while (length < MIN_LENGTH || length > MAX_LENGTH);
            } else {
                // check for atleast 2 characters of each type (uppercase, lowercase, number, special)
                int upper = 0;
                int lower = 0;
                int number = 0;
                int special = 0;
                
                for (int i = 0; i < prompt.length(); i++) {
                    char c = prompt.charAt(i);
                    if (Character.isUpperCase(c)) {
                        upper++;
                    } else if (Character.isLowerCase(c)) {
                        lower++;
                    } else if (Character.isDigit(c)) {
                        number++;
                    } else {
                        special++;
                    }
                }

                int badChecks = 0;

                if (upper < MIN_UPPER) {
                    System.out.println("Password does not contain enough uppercase characters");
                    badChecks++;
                } 
                if (lower < MIN_LOWER) {
                    System.out.println("Password does not contain enough lowercase characters");
                    badChecks++;
                } 
                if (number < MIN_DIGITS) {
                    System.out.println("Password does not contain enough digits");
                    badChecks++;
                } 
                if (special < MIN_SPECIAL) {
                    System.out.println("Password does not contain enough special characters");
                    badChecks++;
                } 
                
                // strong, weak, or medium
                if (badChecks == 0) {
                    System.out.println("Password is strong");
                } else if (badChecks == 1) {
                    System.out.println("Password is medium");
                } else {
                    System.out.println("Password is weak");
                }
            }
        } while (!prompt.equals("exit"));
        
        input.close();
    }
}
