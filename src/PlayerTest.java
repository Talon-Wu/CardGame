import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class PlayerTest {
    CardGame game = new CardGame();
    Player player;
    @Before
    public void setUp() throws Exception {
        //game = new
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
        player = Players.get(1) ;
        ArrayList<Card> testDeck = new ArrayList<Card>(Arrays.asList(
                card1,card1,card2,card1,card1,card1
        ));
        player.setHandCards(testDeck);
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
    public void addToHandCards() {

    }
    @Test
    public void discardCard(){
        Card card1 = new Card(1);
        Card card2 = player.discardCard();
        System.out.println("Player" + player.getPlayerNumber()+ "handCards:");
        int i = 0;
        for (Card card0 : player.getHandCards()) {
            System.out.println("Player" + player.getPlayerNumber()+ "handCards"+ (++i) +":" + card0.getValue());
        }
        assertEquals(1,card2.getValue());
    }
}