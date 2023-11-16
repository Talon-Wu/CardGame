public class Card {
    private int value;

    public int getValue() {
        return value;
    }

    public boolean setValue(int value) {
        if (value <= 0){
            System.out.println("Invalid input");
            return false;
        }
        this.value = value;
        return true;
    }
}
