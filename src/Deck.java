import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Deck {



    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<Card> deckOfCards = new ArrayList<>();
    public Card pickCard(){
       // if(!deckOfCards.isEmpty()){
            Card pickedCard = deckOfCards.get(0);
            return pickedCard;
        //} else{
        //    return null;
       // }
    }

    public ReentrantLock getLock() {
        return lock;
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
