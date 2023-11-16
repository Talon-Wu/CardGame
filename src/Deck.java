import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deckOfCards = new ArrayList<>();
    public Card pickCard(){
        if(!deckOfCards.isEmpty()){
            Card pickedCard = deckOfCards.get(0);
            return pickedCard;
        } else{
            return null;
        }
    }
}
