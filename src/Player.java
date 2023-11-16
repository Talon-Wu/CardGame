public class Player implements PlayerInterface, Runnable{
    private boolean isWin;
    private boolean hasWinner;
    private int playerNumber;
    private int leftNumber;
    private int rightNumber;
    Card[] handCards = new Card[10];
    //default handCards limit is 10, should be always less than 5
    private String playerFile;
    private String leftDeckFile;
    private String rightDeckFile;
    private Deck[] cardDecks;

    public Player(int playerNumber, Deck[] cardDecks){
        this.playerNumber = playerNumber;
        this.cardDecks = cardDecks;
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


}
