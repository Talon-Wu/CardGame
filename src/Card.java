public class Card {
    private int card;

    public int getCard() {
        return card;
    }

    public boolean setCard(int card) {
        if (card <= 0){
            System.out.println("Invalid input");
            return false;
        }
        this.card = card;
        return true;
    }
}
