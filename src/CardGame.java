import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {

    public static boolean isWin;
    // private ArrayList<Player> players;
    private Player[] players;
    // private ArrayList<Deck> decks;
    private Deck[] decks;

    public static void main(String[] args) {
        start();
    }
    public static void start() {
        String path = null;
        try {
            ArrayList<Card> pack = readPack(path);
        } catch (IOException e) {

        }
    }
    /** Test
     *
     */
//    public static void main(String[] args) throws IOException{
//        CardGame game = new CardGame();
//        int playerAmount = 4;
//        game.createDeck(playerAmount);
//        game.createPlayer(playerAmount);
//        game.dealCards(CardGame.readPack("D:\\IntelliJ IDEA 2023.2.2\\CA 2414\\CA\\pack.txt"));
//    }

    /**
     * Ask the player to input how many people are playing this game.
     *
     * @return number of players
     */
    public int getPlayerAmount() {
        Scanner scanner = new Scanner(System.in);

        int n = 0;
        while (true) {
            System.out.println("Please enter the number of players");
            try {
                n = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Player amount must be integer");
                continue;
            }

            if (n <= 0) {
                System.out.println("The number of players can't be less than 1, please enter again.");
                continue;
            }
            break;
        }
        scanner.close();
        return n;
    }

    /**
     * Ask the player which file he wants to load
     * @return the location of the file
     */
    public String getPackPath() {
        Scanner scanner = new Scanner(System.in);
        String path;

        while (true){
            System.out.println("Please enter location of pack to load");
            path = scanner.nextLine();

            if (getPackPath(path)) {
                break;
            } else {
                System.out.println("Invalid file location, please enter again.");
            }
        }
        scanner.close();
        return path;
        // Needs to add what if the path entered is invalid
    }

    /**
     * This part explains what will happen if the pack the player input is invalid.
     * @param path receiving the value we get from getPackPath
     * @return which asks the system to inform the player the reason of fail to read the pack.
     */
    private boolean getPackPath(String path){
        try {
            File file = new File(path);
            return file.exists();
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Generates a pack
     *
     * @param n generates 8n numbers
     * @return Generated pack
     */
    public ArrayList<Integer> generatePack(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        // Generate 4 cards for each number
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 2 * n; j++) {
                result.add(j);
            }
        }

        Collections.shuffle(result);

        return result;
    }

    /**
     * Creates decks and stores them in the decks attribute
     * @param n Creates n decks
     */
    public void createDeck(int n) {
//        ArrayList<Deck> decks = new ArrayList<>(n);
        decks = new Deck[n + 1];
        // for decks, we use it from decks[1], so the size should be n + 1
        for (int i = 1; i < n + 1; i++) {
            decks[i] = new Deck();
        }
        this.decks = decks;
    }

    /**
     * Creates decks and stores them in the decks attribute
     * @param n Creates n players
     */
    public void createPlayer(int n){
//        ArrayList<Player> players = new ArrayList<>(n);
        players = new Player[n + 1];
        // for players, we use it from players[1], so the size should be n + 1
        for (int i = 1; i < n; i++){
            players[i] = new Player(i,n,this.decks) ;
            //
        }
        this.players = players;
    }

    /**
     * Deal the cards in the pack to the player and deck in alternating order
     * @param pack txt file
     */
    public void dealCards(ArrayList<Card> pack) {

        //Give the cards to the Players.
        int cardIndex = 0;
        for (int j = 0; j < 4; j++) {
            for (Player player : this.players) {
                player.getHandCards().add(pack.get(cardIndex++));
            }
        }

        try {
            while (true) {
                for (Deck deck : this.decks) {
                    deck.getDeckOfCards().add(pack.get(cardIndex++));
                }
            }
        } catch (IndexOutOfBoundsException ignored) { }

    }


    /**
     * Writes a pack to a file
     * @param numbers the pack to write
     * @param fileName the file to write to
     */
    public void writeToFile(ArrayList<Integer> numbers, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int number : numbers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e){
            //e.printStackTrace();
        }
    }

    /**
     * Read pack from file
     * @param filePath where we find the file which is going to be use
     * @return Card reads from the file
     * @ line A variable, temporary restore each line of numbers just read
     * @ throws IOException
     */
    public ArrayList<Card> readPack(String filePath) throws IOException {
        ArrayList<Card> cards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null){
                int card = Integer.parseInt(line.trim());
                cards.add(new Card(card));
            }
        }
        return cards;
    }
}
