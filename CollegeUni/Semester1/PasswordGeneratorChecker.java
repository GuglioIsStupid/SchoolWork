import java.util.List;
import java.util.Scanner;

public class PasswordGeneratorChecker {
    // PASSWORD VALIDATOR CONSTANTS
    //#region PVALID CONST
    private static final String[] COMMON_PHRASES = {
        "password",
        "p4ssw0rd",
        "qwerty",
        "123<num_plus>" // the <num_plus> will be replaced by a number with repeating characters when required
    };
    private static final int MIN_LENGTH = 12;
    private static final int MAX_LENGTH = 92; // Most websites have a maximum password length of 92 characters

    private static final int MIN_UPPERCASE = 1;
    private static final int MIN_LOWERCASE = 1;
    private static final int MIN_NUMBER = 1;

    private static final int MIN_REPEATING = 2;
    private static final int MIN_COMMON_PHRASES = 1;

    private static final int ALL_CHECKS = 6;

    // PASSWORD GENERATOR CONSTANTS
    //#region PGEN CONST
    private static final int MIN_DIGITS = 2;
    private static final int MIN_SPECIAL = 2;
    private static final int MIN_UPPER = 2;
    private static final int MIN_LOWER = 2;

    private static final String DIGITS_STRING = "0123456789";
    private static final String SPECIAL_STRING = "!@#$%^&*()_+{}|:<>?~";
    private static final String CHARACTERS_STRING = "abcdefghijklmnopqrstuvwxyz";

    /**
     * PASSWORD VALIDATOR
    */
    //#region PVALID FUNC

    public static List<Object> checkLength(String password) {
        String warning = "";
        boolean tooShort = password.length() < MIN_LENGTH;
        boolean tooLong = password.length() > MAX_LENGTH;

        if (tooShort) {
            warning = "Password is too short";
        } else if (tooLong) {
            warning = "Password is too long";
        }

        return List.of(tooShort && tooLong, warning);
    }

    public static List<Object> checkUppercase(String password) {
        String warning = "";
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                count++;
            }
        }

        boolean tooFew = count < MIN_UPPERCASE;
        if (tooFew) {
            warning = "Password has too few uppercase characters";
        }

        return List.of(tooFew, warning);
    }

    public static List<Object> checkLowercase(String password) {
        String warning = "";
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                count++;
            }
        }

        boolean tooFew = count < MIN_LOWERCASE;
        if (tooFew) {
            warning = "Password has too few lowercase characters";
        }

        return List.of(tooFew, warning);
    }

    public static List<Object> checkNumber(String password) {
        String warning = "";
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                count++;
            }
        }

        boolean tooFew = count < MIN_NUMBER;
        if (tooFew) {
            warning = "Password has too few numbers";
        }

        return List.of(tooFew, warning);
    }

    public static List<Object> checkRepeating(String password) {
        String warning = "";
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int j = i + 1;
            while (j < password.length() && password.charAt(j) == c) {
                j++;
            }

            if (j - i >= MIN_REPEATING+1) {
                count++;
                i = j - 1;
            }
        }

        boolean tooMany = count > 0;
        if (tooMany) {
            warning = "Password has too many repeating characters";
        }

        return List.of(tooMany, warning);
    }

    public static List<Object> checkCommonPhrases(String password) {
        String warning = "";
        int count = 0;

        for (String phrase : COMMON_PHRASES) {
            /* if (password.contains(phrase)) {
                count++;
            } */
           // regex if <num_plus> is present
            if (phrase.contains("<num_plus>")) {
                for (int i = 0; i < 10; i++) {
                    String num = Integer.toString(i);
                    String newPhrase = phrase.replace("<num_plus>", num + num);
                    if (password.contains(newPhrase)) {
                        count++;
                    }
                }
            } else if (password.contains(phrase)) {
                count++;
            }
        }

        boolean tooMany = count >= MIN_COMMON_PHRASES;
        if (tooMany) {
            warning = "Password contains too many common phrases";
        }

        return List.of(tooMany, warning);
    }

    public static List<Object> checkPassword(String password) {
        List<Object> length = checkLength(password);
        List<Object> uppercase = checkUppercase(password);
        List<Object> lowercase = checkLowercase(password);
        List<Object> number = checkNumber(password);
        List<Object> repeating = checkRepeating(password);
        List<Object> commonPhrases = checkCommonPhrases(password);

        boolean[] checks = {
            (boolean) length.get(0),
            (boolean) uppercase.get(0),
            (boolean) lowercase.get(0),
            (boolean) number.get(0),
            (boolean) repeating.get(0),
            (boolean) commonPhrases.get(0),
        };

        String[] warnings = {
            (String) length.get(1),
            (String) uppercase.get(1),
            (String) lowercase.get(1),
            (String) number.get(1),
            (String) repeating.get(1),
            (String) commonPhrases.get(1),
        };

        int count = 0;
        for (boolean check : checks) {
            if (check) {
                count++;
            }
        }

        return List.of(count == ALL_CHECKS, warnings, ALL_CHECKS - count);
    }

    /**
     * PASSWORD GENERATOR
    */
    //#region PGEN FUNC

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        // modify the mins to make up to the password length as the default is made just for 8 characters (The average password minimum length)
        int minDigits = MIN_DIGITS;
        int minSpecial = MIN_SPECIAL;
        int minUpper = MIN_UPPER;
        int minLower = MIN_LOWER;

        int minTotal = minDigits + minSpecial + minUpper + minLower;
        if (minTotal > length) {
            minDigits = length / 4;
            minSpecial = length / 4;
            minUpper = length / 4;
            minLower = length / 4;
        }

        for (int i = 0; i < minDigits; i++) {
            int index = (int) (Math.random() * DIGITS_STRING.length());
            password.append(DIGITS_STRING.charAt(index));
        }

        for (int i = 0; i < minSpecial; i++) {
            int index = (int) (Math.random() * SPECIAL_STRING.length());
            password.append(SPECIAL_STRING.charAt(index));
        }
        
        for (int i = 0; i < minUpper; i++) {
            int index = (int) (Math.random() * CHARACTERS_STRING.length());
            password.append(Character.toUpperCase(CHARACTERS_STRING.charAt(index)));
        }

        for (int i = 0; i < minLower; i++) {
            int index = (int) (Math.random() * CHARACTERS_STRING.length());
            password.append(CHARACTERS_STRING.charAt(index));
        }

        int remaining = length - minTotal;
        for (int i = 0; i < remaining; i++) { // just fill the rest with random characters as we meet the minimums
            int type = (int) (Math.random() * 4);
            switch (type) {
                case 0:
                    int index = (int) (Math.random() * DIGITS_STRING.length());
                    password.append(DIGITS_STRING.charAt(index));
                    break;
                case 1:
                    index = (int) (Math.random() * SPECIAL_STRING.length());
                    password.append(SPECIAL_STRING.charAt(index));
                    break;
                case 2:
                    index = (int) (Math.random() * CHARACTERS_STRING.length());
                    password.append(Character.toUpperCase(CHARACTERS_STRING.charAt(index)));
                    break;
                case 3:
                    index = (int) (Math.random() * CHARACTERS_STRING.length());
                    password.append(CHARACTERS_STRING.charAt(index));
                    break;
            }
        }

        return password.toString();
    }

    //#region HELPERS
    
    public static void checkPasswordHelper(String input) {
        List<Object> result = checkPassword(input);
        boolean valid = (boolean) result.get(0);
        String[] warnings = (String[]) result.get(1);
        int count = (int) result.get(2);

        if (valid) {
            System.out.println("Password is valid with no warnings (6/6 valid checks)");
        } else {
            System.out.println("Password is invalid with warnings (" + count + "/6 valid checks)");
            for (String warning : warnings) {
                if (!warning.isEmpty()) {
                    System.out.println(warning);
                }
            }
        }
    }
    
    //#region DEBUG
    public static void debug_Main(String[] args) {
        boolean checkPassword = false;
        if (checkPassword) {
            String password = "p4ssW0rD18$";

            System.out.println("Checking password: " + password);
            checkPasswordHelper(password);
        } else {
            String password = generatePassword(14); // generate a password with 14 characters
            System.out.println("Generated password: " + password);
        }
    }

    //#region MAIN
    public static void main(String[] args) {
        // if debugging, call debug_Main. Makes it so user doesn't need to give inputs
        if (args.length > 0 && args[0].equals("debug")) {
            debug_Main(args);
        } else {
            Scanner scanner = new Scanner(System.in);

            boolean running = true;
            do {
                System.out.println("Enter a password to check or type 'exit' to quit: ");
                System.out.println("Or type 'generate' to generate a password: ");
                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    running = false;
                } else if (input.equals("generate")) {
                    System.out.println("Enter the length of the password to generate: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    String password = generatePassword(length);
                    System.out.println("Generated password: " + password);
                } else {
                    checkPasswordHelper(input);
                }
            } while (running);
        }
    }
}
