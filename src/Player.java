import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
public class Player implements PlayerInterface, Runnable {
    private Logger logger = Logger.getLogger(Player.class.getName());
    // added from ShiYu
    private int playerNumber;
    public int leftNumber;
    public int rightNumber;
    public int lastPlayer;
    public int nextPlayer;
    public int showNumber;
    private ArrayList<Card> handCards = new ArrayList<>();
    private String playerFile;
    private String leftDeckFile;
    private String rightDeckFile;
    //private Deck[] cardDecks;
    public ArrayList<Deck> cardDecks;
    public static Player[] players = new Player[10];
    // max volume of player is 10
    public CardGame gameInstance;
    //public int lastMost = 0;

//    public void addToHandCards(Card card) {
//        this.handCards.add(card);
//    }


    public Player(int playerNumber, int amountOfPlayer, ArrayList<Deck> cardDecks, CardGame gameInstance) {
        //public Player(int playerNumber, int amountOfPlayer,  ArrayList<Deck> cardDecks){
        this.playerNumber = playerNumber;
        this.showNumber = playerNumber + 1;
        this.cardDecks = cardDecks;
        if (playerNumber == amountOfPlayer - 1) {
            //last player
            this.rightNumber = 0;
            this.nextPlayer = 0;
        } else {
            //normal player
            this.rightNumber = playerNumber + 1;
            nextPlayer = playerNumber + 1;
            // The ArrayList start from 0,
            // so Player1(0)'s right number is Deck2(1)
            // that is 1 in ArrayList
        }
        if(playerNumber == 0){
            this.lastPlayer = amountOfPlayer - 1;
        }else{
            this.lastPlayer = playerNumber - 1;
        }
        this.leftNumber = playerNumber;
        // The ArrayList start from 0, so the left number should - 1
        this.playerFile = "Player" + this.playerNumber + "_output.txt";
        this.leftDeckFile = "Deck" + leftNumber + "_output.txt";
        this.rightDeckFile = "Deck" + rightNumber + "_output.txt";
        // added from ShiYu
        try {
            logger.addHandler(new FileHandler("Player" + this.playerNumber + ".log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        players[playerNumber] = this;

        this.gameInstance = gameInstance;
    }

    @Override
    public void run() {
        while (!CardGame.hasWinner) {
            if (checkIWin()) {
                declareAWin();
                // not implemented
                System.out.println("Player" + showNumber + " has won");
                System.out.println("Player" + showNumber + " exit");
                return;
            }
            //!roundFinished
            synchronized (CardGame.lock) {
                // now this thread gain the lock of left deck
                // should have a boolean from CardGame that show if this round of play is over
                System.out.println("Player" + showNumber + "gained a lock");
                Card card = pickCard();
                if (card != null) {
                    System.out.println("Player" + showNumber + "pickCard");
                } else {
                    System.out.println("Player" + showNumber + " can't pickCard");
                    int i = 0;
                    for (Card card0 : this.getHandCards()) {
                        if(card0 != null)
                            System.out.println("Player" + this.showNumber+ "handCards" + (++i) + ":" + card0.getValue());
                    }
                    System.out.println("Player" + showNumber + " release lock");
                    continue;
                }
                Card card1 = discardCard();
                int i = 0;
                for (Card card0 : this.getHandCards()) {
                    if(card0 != null)
                    System.out.println("Player" + this.showNumber+ "handCards" + (++i) + ":" + card0.getValue());
                }
                i = 0;
                System.out.println("Player" + showNumber + "successfully pick and discard");
            }
            System.out.println("failed to gain a lock");
        }
        /*
          Need something else rather than players to represent the rest of the players.
         */

        System.out.println("Player "+this.gameInstance.whoWin.getPlayerNumber()+" has informed player "+this.showNumber);
//      System.out.println("Player" + winnerNumber + " has won");
        System.out.println("Player"+ showNumber+ " 's losing hand: ");
        for (Card card : getHandCards()){
            System.out.println(card.getValue());
        }
        System.out.println("Player"+ showNumber+ " now exit.");

    }

    @Override
    public boolean checkIWin() {
        if(handCards.size() != 4){
            return false;}
        for (int i = 0; i < handCards.size() - 1; i++){
            if (handCards.get(i).getValue() != handCards.get(i + 1).getValue()) {
                // if there are different values in handCards
                // then this player isn't win, set the hasWinner to false
                return false;
                //Thread.currentThread().interrupt();
                // should interrupt other threads
            }
        }
        return true;
    }

    @Override
    public boolean declareAWin() {
        this.gameInstance.hasWinner = true;
        this.gameInstance.whoWin = this;
        return true;
    }

    @Override
    public Card pickCard() {
        Deck leftDeck = cardDecks.get(leftNumber);
        Card pickedCard;
        //from the Deck array(All the Deck) pick the right one.
        if(handCards.size() >= 5){
             pickedCard = null;
        }else{
         pickedCard = leftDeck.pickCard();}
        //pick the first card of this deck.

        if(pickedCard != null){
        //pickCard() will return a null if the deck is empty.
        handCards.add(pickedCard);
        //ArrayList.add() will add Object to the end of the list by default
        int i = 0;
        for(Card card: leftDeck.getDeckOfCards()){
            System.out.println("*Picked by Player"+ showNumber +"*"+"Deck"+ (leftNumber + 1)+ " card" + (++i) + ": " + card.getValue());
        }
        i = 0;
        return pickedCard;
        } else {
            int i = 0;
            for(Card card: leftDeck.getDeckOfCards()){
                System.out.println("*Picked by Player"+ showNumber +"*"+"Deck"+ (leftNumber + 1)+ " card" + (++i) + ": " + card.getValue());
            }
           return null;
        //the error Output is left to CardGame class to deal with
        }

    }

    @Override
    public Card discardCard() {
        int deleteNumber = -1;
        int most = mostFrequentNumber();
        for (int i = handCards.size() - 1; i >= 0; i--) {
            int num = handCards.get(i).getValue();
            //get every (value of card) of handCards
            if (num != most && num != (showNumber)) {
                // player number: 1, 2, 3 ArrayList number: 0, 1, 2
                deleteNumber = i;
                break;
            }
        }
        if (deleteNumber == -1) {
            //that there are only frequent value and player number value
            //e.g. Player1 {1, 2, 2, 2}
            for (int i = handCards.size() - 1; i >= 0; i--) {
                int num = handCards.get(i).getValue();
                //get every (value of card) of handCards
                if (num != showNumber ) {
                    // lastMost changed after deleting a Card,
                    // if player discard same card
                    // wrong, problem is Player1{3, 3, 4, 4}
                    deleteNumber = i;
                    break;
                }
            }
        }
        if(deleteNumber == -1){
            System.out.println("Player" + this.showNumber+"failed to delete a card");
            int i = 0;
            for (Card card0 : this.getHandCards()) {
                System.out.println("Player" + this.showNumber+ "handCards"+ (++i) +":" + card0.getValue());
                }
            i = 0;
            if(checkIWin() && !(CardGame.hasWinner)){
                declareAWin();
                }
            //lastMost = most;
            return new Card(-1);
        }
        int cardint = handCards.get(deleteNumber).getValue();
        System.out.println("Player" + this.showNumber+"successfully deleted card: "+ cardint);
        Card deletedCard = handCards.remove(deleteNumber);
        Deck rightDeck = cardDecks.get(rightNumber);
        rightDeck.addToDeck(deletedCard);
        int i = 0;
        for(Card card: rightDeck.getDeckOfCards()){
            System.out.println("*Discard by Player"+ showNumber +"*"+"Deck"+ (rightNumber + 1)+ " card" + (++i) + ": " + card.getValue());
        }
        i = 0;
        return deletedCard;
        // the remove method will both remove an element and return that element
    }

    public int mostFrequentNumber() {
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
    public boolean outputDeck(int number) {
        ArrayList<Card> deck = cardDecks.get(number).getDeckOfCards();
        int size = deck.size();
        int[] deckCardsInt = new int[size];
        for (int i = 0; i < size; i++) {
            // deckCardsInt[i] = handCards.get(i).getValue();
            deckCardsInt[i] = deck.get(i).getValue();
            // 获得牌堆里的每一个card并转成数字
        }
        CardGame game = new CardGame();
        //game.output(deckCardsInt);
        // 获取int数组，以及对应的路径
        return true;
    }

    @Override
    public boolean outputPlayer() {
        int size = handCards.size();
        int[] handCardsInt = new int[size];
        for (int i = 0; i < size; i++) {
            handCardsInt[i] = handCards.get(i).getValue();
        }
        CardGame game = new CardGame();
        //game.output(handCardsInt);
        return true;
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

    public ArrayList<Deck> getCardDecks() {
        return cardDecks;
    }

    public void setCardDecks(ArrayList<Deck> cardDecks) {
        this.cardDecks = cardDecks;
    }

}



