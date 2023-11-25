import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Deck {


    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<Card> deckOfCards = new ArrayList<>();// cardsOfDeck 更好
    public Card pickCard(){
        if(!deckOfCards.isEmpty()){
            Card pickedCard = deckOfCards.remove(0);
            return pickedCard;
        } else{
            System.out.println("This Deck is empty");
            return null;
       }
    }
    public void addToDeck(Card card) {
        this.deckOfCards.add(deckOfCards.size(),card);
    }
    public ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }
    public void setDeckOfCards(ArrayList<Card> deckOfCards) {
        this.deckOfCards = deckOfCards;
    }
    public void writeToFile() {

    }

}
