import java.util.ArrayList;
import java.util.Arrays;

/*
 *@ Talon
 *11.16
 *2023/11/16
 */public class Main {
    public static void main(String[] args) throws Exception{
        CardGame game = new CardGame();
        Card card1 = new Card(1);
        Card card2 = new Card(2);
//        ArrayList<Card> pack = new ArrayList<Card>(Arrays.asList(
//                card1,card1,card2,card2,card1,card1,card2,card2,
//                card1,card1,card2,card2,card1,card1,card2,card2
//        ));
                ArrayList<Card> pack = new ArrayList<Card>(Arrays.asList(
                card2,card2,card1,card1,card2,card2,card1,card1,
                card2,card2,card1,card1,card2,card2,card1,card1
        ));
        game.createDeck(2);
        game.createPlayer(2);
        game.dealCards(pack);
        ArrayList<Player> Players = game.getPlayers();
        ArrayList<Deck> Decks = game.getDecks();
//        for (Player player : Players) {
////            System.out.println("Player"+ (count++));
////            for (Card card : player.getHandCards()) {
////                System.out.println(card.getValue());
////            }
////        }
        Thread thread1 = new Thread(Players.get(0));
        thread1.start();
        Thread thread2 = new Thread(Players.get(1));
        thread2.start();
//        System.out.println("Hello world!");
//        String path = null;
//        CardGame game = new CardGame();
////        try {
////            ArrayList<Card> pack = game.readPack(path);
////        } catch (IOException e) {
////
////        }
////        game.createPlayer(5);
//        CardGame.playerAmount = game.getPlayerAmount();
//        ArrayList<Card> pack = game.readPack(game.getPackPath());
//        game.createDeck(CardGame.playerAmount);
//        game.createPlayer(CardGame.playerAmount);
//        game.dealCards(pack);
//        ArrayList<Player> Players = game.getPlayers();
//        for (Player player : Players){
//            System.out.println("Player");
//            for (Card card : player.getHandCards()){
//                System.out.println(card.getValue());
//            }
//        }
//        ArrayList<Deck> Decks = game.getDecks();
//        for (Deck deck : Decks){
//            System.out.println("Deck");
//            for (Card card : deck.getDeckOfCards()){
//                System.out.println(card.getValue());
//            }
//        }
//        Thread thread1 = new Thread(Players.get(0));
//        thread1.start();

    }

}
//for(int j=0;j< 4;j++){
//        for(int k=1;k<playerAmount +1;k++){
//        if(players[k].getHandCards()==null){
//        System.out.println("get a null from Player");
//        continue;
//        }else{
//        System.out.println("get a AL from Player");
//        }
//        players[k].getHandCards().add(pack.get(cardIndex++));
//        }
//        }