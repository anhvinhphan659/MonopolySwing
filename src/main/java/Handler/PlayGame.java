package Handler;

import Model.Chance;
import Model.Player;
import UI.Item.LandItem;
import UI.Screen.MainGameScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class PlayGame {
    private MainGameScreen mainGameScreen;
    public PlayGame(MainGameScreen mainGameScreen){
        this.mainGameScreen = mainGameScreen;
    }

//    public void play(ArrayList<Player> playerArrayList, ArrayList<LandItem> landItemList, ArrayList<Chance> chanceList){
//        while (true){
//            int i = mainGameScreen.nextPlayer();
//
//            Player player = playerArrayList.get(i);
//            JOptionPane.showMessageDialog(null, "Đến lượt của " + player.getName());
//            GameHandler.move(player, landItemList);
//            GameHandler.handle(player, landItemList);
//        }
//    }
}
