public class MethodsLab4 {
    public static boolean isPalindrome(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        if (!str.equals(reversed)) {
            return false;
        }

        return true;
    }

    public static int countUpperCase(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                count++;
            }
        }

        return count;
    }

    public static int countWords(String sentence) {
        int count = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                count++;
            }
        }
        
        return count + 1;
    }

    public static double averageThreeNumbers(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }

    public static String removeVowels(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                result += c;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int success = 0;
        int total = 0;
        System.out.println("--- TESTS ---");
        System.out.println("Testing palindrome checker");
        
        String palindrome = "racecar";
        String notPalindrome = "hello";
        boolean isPalindrome = isPalindrome(palindrome);
        boolean isNotPalindrome = isPalindrome(notPalindrome);

        if (isPalindrome && !isNotPalindrome) {
            System.out.println("Palindrome test passed");
            success++;
        } else {
            System.out.println("Palindrome test failed");
        }

        total++;

        System.out.println("\nTesting uppercase counter");
        String upperCaseString = "Hello, World!";
        int upperCount = countUpperCase(upperCaseString);

        if (upperCount == 2) { 
            System.out.println("Uppercase test passed");
            success++;
        } else {
            System.out.println("Uppercase test failed");
        }

        total++;

        System.out.println("\nTesting word counter");
        String sentence = "Hello, World!";
        int wordCount = countWords(sentence);

        if (wordCount == 2) {
            System.out.println("Word count test passed");
            success++;
        } else {
            System.out.println("Word count test failed");
        }

        total++;

        System.out.println("\nTesting average calculator");
        double num1 = 1;
        double num2 = 2;
        double num3 = 3; // average is 2
        double average = averageThreeNumbers(num1, num2, num3);

        if (average == 2) {
            System.out.println("Average test passed");
            success++;
        } else {
            System.out.println("Average test failed");
        }

        total++;

        System.out.println("\nTesting vowel remover");
        String withVowels = "Hello, World!";
        String withoutVowels = "Hll, Wrld!";
        String result = removeVowels(withVowels);
        
        if (result.equals(withoutVowels)) {
            System.out.println("Vowel removal test passed");
            success++;
        } else {
            System.out.println("Vowel removal test failed");
        }

        total++;

        System.out.println("\n--- RESULTS ---");

        double percentage = (success / (double) total) * 100;
        System.out.println("Tests passed: " + success + "/" + total + " (" + percentage + "%)");
    }
}
