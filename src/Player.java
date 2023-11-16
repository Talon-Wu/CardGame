public class Player implements PlayerInterface, Runnable{
    private boolean isWin = false;
    private boolean hasWinner = false;
    private int playerNumber;
    private int leftNumber;
    private int rightNumber;

    Card[] handCards = new Card[10];
    //default handCards limit is 10, should be always less than 5
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
        this.playerFile =null;
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

        return false;
    }

    @Override
    public boolean discardCard() {
        return false;
    }

    @Override
    public boolean outputDeck() {
        return false;
    }

    @Override
    public boolean writePlayer() {
        return false;
    }

    public Card[] getHandCards() {
        return handCards;
    }

    public void setHandCards(Card[] handCards) {
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

}
