import java.util.ArrayList;

public class Deck {

    private Object lock = new Object();
    private ArrayList<Card> deckOfCards = new ArrayList<>();
    public Card pickCard(){
       // if(!deckOfCards.isEmpty()){
            Card pickedCard = deckOfCards.get(0);
            return pickedCard;
        //} else{
        //    return null;
       // }
    }
    public Object getLock() {
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
