import java.util.ArrayList;

/*
 *@ Talon
 *11.16
 *2023/11/16
 */public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String path = null;
        CardGame game = new CardGame();
//        try {
//            ArrayList<Card> pack = game.readPack(path);
//        } catch (IOException e) {
//
//        }
//        game.createPlayer(5);
        CardGame.playerAmount = game.getPlayerAmount();
        ArrayList<Card> pack = game.readPack(game.getPackPath());
        game.createDeck(CardGame.playerAmount);
        game.createPlayer(CardGame.playerAmount);
        game.dealCards(pack);
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