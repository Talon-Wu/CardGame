import java.util.ArrayList;
import
public class Player implements PlayerInterface, Runnable{
    private boolean isWin = false;
    private boolean hasWinner = false;
    private int playerNumber;
    private int leftNumber;
    private int rightNumber;

    private ArrayList<Card> handCards = new ArrayList<>();
    private int handCardAmount;//to record how many card the player has

    private String playerFile;
    private String leftDeckFile;

    private String rightDeckFile;
    private Deck[] cardDecks;


    public Player(int playerNumber, int amountOfPlayer,  Deck[] cardDecks){
        this.playerNumber = playerNumber;
        this.cardDecks = cardDecks;
        if(playerNumber == amountOfPlayer){
            //last player
            this.rightNumber = 1;
        }else{
            //normal player
            this.rightNumber = ++playerNumber;
        }
        this.leftNumber = playerNumber;
        this.playerFile = "Player" + this.playerNumber +"_output";
        this.leftDeckFile = "Deck" + leftNumber + "_output";
        this.rightDeckFile = "Deck" + rightNumber + "_output";
    }
    @Override
    public void run() {

    }
    @Override
    public boolean checkWin() {
        return false;
    }

    @Override
    public boolean declareAWin() {
        return false;
    }

    @Override
    public boolean pickCard() {
        Deck leftDeck = cardDecks[leftNumber];
        //from the Deck array(All the Deck) pick the right one.
        Card pickedCard = leftDeck.pickCard();
        //pick the first card of this deck.

        if(pickedCard != null){
            //pickCard() will return a null if the deck is empty.
            handCards.add(pickedCard) ;
            //ArrayList.add() will add Object to the end of the list by default
            return true;
        } else {
            return false;
            //the error Output is left to CardGame class to deal with
        }
    }

    @Override
    public boolean discardCard() {
        int deleteNumber = -1;
        int most = mostFrequentNumber();
        for (int i = 0; i < handCards.size(); i++) {
            int num = handCards.get(i).getValue();
            //get every (value of card) of handCards
            if(num != most && num != playerNumber){
                deleteNumber = i;
                break;
            }
        }
        if(deleteNumber == -1){
            //that there are only frequent value and player number value
            //e.g. Player1 {1, 2, 2, 2}
            for (int i = 0; i < handCards.size(); i++) {
                int num = handCards.get(i).getValue();
                //get every (value of card) of handCards
                if(num != playerNumber){
                    deleteNumber = i;
                    break;
                }
            }
        }
        handCards.remove(deleteNumber);
        return true;
    }

    public int mostFrequentNumber(){
        int maxCount = 0;
        int maxValue = 0;
        for (int i = 0; i < handCards.size(); i++) {
            //each i compare frequency of every number
            int count = 0;
            int currentValue = handCards.get(i).getValue();

            for (int j = 0; j < handCards.size(); j++) {
                //each j find out frequency of every number
                if (handCards.get(j).getValue() == currentValue) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = currentValue;
            }
        }
        return maxValue;
    }
    @Override
    public boolean outputDeck() {
        int size = handCards.size();
        int[] handCardsInt = new int[size];
        for (int i = 0; i < size; i++) {
            handCardsInt[i] = handCards.get(i).getValue();
        }
        //Cardgame game = new CardGame();
        return false;
    }

    @Override
    public boolean outputPlayer() {
        return false;
    }






    public ArrayList<Card> getHandCards() {
        return handCards;
    }
    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerFile() {
        return playerFile;
    }

    public void setPlayerFile(String playerFile) {
        this.playerFile = playerFile;
    }

    public String getLeftDeckFile() {
        return leftDeckFile;
    }

    public void setLeftDeckFile(String leftDeckFile) {
        this.leftDeckFile = leftDeckFile;
    }

    public String getRightDeckFile() {
        return rightDeckFile;
    }

    public void setRightDeckFile(String rightDeckFile) {
        this.rightDeckFile = rightDeckFile;
    }

    public Deck[] getCardDecks() {
        return cardDecks;
    }

    public void setCardDecks(Deck[] cardDecks) {
        this.cardDecks = cardDecks;
    }

    public int getHandCardAmount() {
        return handCardAmount;
    }

    public void setHandCardAmount(int handCardAmount) {
        this.handCardAmount = handCardAmount;
    }

}
