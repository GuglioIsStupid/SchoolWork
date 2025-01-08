import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char playing = 'y';

        while (playing == 'y') {
            int incorrectGuesses = 0;
            String guessedLetters = "";
            String word = getRandomWord();

            while (!isGameOver(word, guessedLetters)) {
                System.out.println(fillInBlanks(word, guessedLetters));
                System.out.println("Enter a letter: ");
                // get next line from the user
                String letterOrWord = scanner.nextLine();
                if (!guessedLetters.contains(String.valueOf(letterOrWord))) {
                    guessedLetters += letterOrWord;
                } else if (letterOrWord.equals(word)) {
                    System.out.println("You got it! The word was: " + word);
                } else {
                    incorrectGuesses++;
                    System.out.println("You already guessed that letter!");
                }
            }

            if (isWordComplete(word, guessedLetters)) {
                System.out.println("Congratulations! You guessed the word!");
            } else {
                System.out.println("Sorry, you ran out of guesses!");
            }

            // Display the word and the number of incorrect guesses
            // Then prompt if the user would like to play again
            System.out.println("The word was: " + word);
            System.out.println("You made " + incorrectGuesses + " incorrect guesses.");
            System.out.println("Would you like to play again? (y/n)");
            playing = scanner.nextLine().charAt(0);
        }

        scanner.close();
    }

    public static String getRandomWord() {
        int random = (int) (Math.random() * 10) + 1;
        switch (random) {
            case 1:
                return "apple";
            case 2:
                return "banana";
            case 3:
                return "cherry";
            case 4:
                return "date";
            case 5:
                return "elderberry";
            case 6:
                return "fig";
            case 7:
                return "grape";
            case 8:
                return "honeydew";
            case 9:
                return "kiwi";
            case 10:
                return "lemon";
            default:
                return "";
        }
    }

    public static String fillInBlanks(String word, String guessedLetters) {
        // This function goes through each letter in the word and checks if its in the guessedLetters string
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters.contains(word.substring(i, i + 1))) {
                result += word.substring(i, i + 1);
            } else {
                result += "_";
            }
        }
        return result;
    }

    public static boolean isWordComplete(String word, String guessedLetters) {
        // If the guessedLetters string contains all the letters in the word, return true
        for (int i = 0; i < word.length(); i++) {
            if (!guessedLetters.contains(word.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGameOver(String word, String guessedLetters) {
        // If the word is complete or the user has made 6 incorrect guesses, then the game is over
        return isWordComplete(word, guessedLetters) || guessedLetters.length() >= 6;
    }
}
