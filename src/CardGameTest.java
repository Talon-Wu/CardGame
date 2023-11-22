import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *@ Talon
 *11.21
 *2023/11/21
 */public class CardGameTest {
    CardGame game = new CardGame();
    @Before
    public void setUp() throws Exception {
        Card card1 = new Card(1);
        Card card2 = new Card(2);
        ArrayList<Card> pack = new ArrayList<Card>(Arrays.asList(
                card1,card1,card2,card2,card1,card1,card2,card2,
                card1,card1,card2,card2,card1,card1,card2,card2
        ));
        game.createDeck(2);
        game.createPlayer(2);
        game.dealCards(pack);
        ArrayList<Player> Players = game.getPlayers();
        ArrayList<Deck> Decks = game.getDecks();
//        for (Deck deck : Decks){
//            System.out.println("Deck");
//            for (Card card : deck.getDeckOfCards()){
//                System.out.println(card.getValue());
//            }
//        }
//        for (Player player : Players){
//            System.out.println("Player");
//            for (Card card : player.getHandCards()){
//                System.out.println(card.getValue());
//            }
//        }
        System.out.println("successfully set up!");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main() {
        ArrayList<Player> Players = game.getPlayers();
        ArrayList<Deck> Decks = game.getDecks();
        Thread thread1 = new Thread(Players.get(0));
        thread1.start();
        Thread thread2 = new Thread(Players.get(1));
        thread2.start();
        //System.out.println(1);
        for (Player player : Players){
            System.out.println("Player");
            for (Card card : player.getHandCards()){
                System.out.println(card.getValue());
            }
        }
    }

//    @Test
//    public void start() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void getPlayerAmount() {
//
//        System.out.println(1);
//    }
//
//    @Test
//    public void getPackPath() {
//        System.out.println(1);
//    }
//
//
//    @Test
//    public void generatePack() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void createDeck() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void createPlayer() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void dealCards() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void writeToFile() {
//        System.out.println(1);
//    }
//
//    @Test
//    public void readPack() {
//        System.out.println(1);
//    }
}