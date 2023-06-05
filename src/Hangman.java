import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"};

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String word = randomWord(words);
        System.out.println(word);
        char[] placeholders = new char[word.length()];
        for(int i = 0; i < placeholders.length; i++){
            placeholders[i] = '_';
        }

        int misses = 0;
        char[] missedGuesses = new char[5];

        while(misses < 5){
            System.out.print(gallows[misses]);
            System.out.print("\nWord: ");
            printPlaceholders(placeholders);

            System.out.print("\nMisses: ");
            printMissedGuesses(missedGuesses);

            System.out.print("\nGuess: ");
            char guess = scanner.next().charAt(0);

            if(checkGuess(word, guess)){
                updatePlaceholders(word, placeholders, guess);
            } else {
                missedGuesses[misses] = guess;
                misses++;
            }

            if(Arrays.equals(placeholders, word.toCharArray())){
                System.out.print(gallows[misses]);
                System.out.print("\nWord: " + word);
                System.out.print("\nGood Work!");
                break;
            }
        }

        if(misses == 5){
            System.out.print(gallows[misses]);
            System.out.print("RIP!");
            System.out.print("The word was: " + word);
        }

        scanner.close();
    }

    public static String randomWord(String[] words){
        int index = (int) (Math.random() * words.length);
        String word = words[index];
        return word;
    }

    public static boolean checkGuess(String word, char guess){
        for(int i = 0; i < word.length(); i++){
            if(guess == word.charAt(i))
                return true;
        }

        return false;
    }

    public static void updatePlaceholders(String word, char[] placeholders, char guess){
        for(int i = 0; i< word.length(); i++){
            if(word.charAt(i) == guess)
                placeholders[i] = guess;
        }
    }

    public static void printPlaceholders(char[] placeholders){
        for(int i = 0; i < placeholders.length; i++){
            System.out.print(placeholders[i] + " ");
        }
        System.out.print("\n");
    }

    public static void printMissedGuesses(char[] missesGuesses){
        for(int i = 0; i < missesGuesses.length; i++){
            if(missesGuesses[i] != 0){
                System.out.print(missesGuesses[i] + " ");
            }
        }
        System.out.print("\n");
    }

}





