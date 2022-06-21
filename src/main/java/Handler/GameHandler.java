package Handler;

import Model.Chance;
import Model.GameParameter;
import Model.Player;
import UI.Item.HouseItem;
import UI.Item.LandItem;
import UI.Item.PlayerItem;
import UI.Screen.MainGameScreen;
import UI.Util.DisplayAction;
import com.github.weisj.darklaf.listener.MouseClickListener;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.util.Random;

public class GameHandler {
    public final static String PATH_PARAM_FILE = "resources/params.json";
    public static final String LAND_SOURCE_FILE="resources/lands.json";
    public static final String CHANCE_SOURCE_FILE="resources/chances.json";

    //set up params
    public static void generateParams()
    {
        try {
            //read params
            BufferedReader br=new BufferedReader(new FileReader(PATH_PARAM_FILE));
            String data="";
            String line;
            while((line= br.readLine())!=null)
            {
                data+=line;
            }
            JSONObject jsonData=new JSONObject(data);
            JSONArray names=jsonData.getJSONArray("PLAYER_NAMES");
            ArrayList<String> nameList = new ArrayList<>();
            for (int i=0;i<names.length();i++)
            {
                nameList.add(names.getString(i));
            }
            GameParameter._playerNameListDefault.add("Alex");
            GameParameter._playerNameListDefault.add("Alexandra");
            GameParameter._playerNameListDefault.add("Kate");
            GameParameter._playerNameListDefault.add("Harry");
            GameParameter._playerNameListDefault.add("Hermione");
            //set params
            GameParameter.setMoney(jsonData.getInt("START_MONEY"));
            GameParameter.set_playerNameList(nameList);
            GameParameter.set_landInformationList(readLandList());
            GameParameter.set_chanceInformationList(readChanceList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readNameFile() throws IOException {
        ArrayList<String> nameList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH_PARAM_FILE))));
        while (true){
            String name = br.readLine();
            if(name != null){
                nameList.add(name);
            }
            else{
                break;
            }
        }
        return nameList;
    }
    public static ArrayList<JSONObject> readLandList()
    {
        ArrayList<JSONObject> ret=new ArrayList<>();

        try {
            BufferedReader br=new BufferedReader(new FileReader(LAND_SOURCE_FILE));
            String data="";
            String line;
            while((line= br.readLine())!=null)
            {
                data+=line;
            }
            JSONArray landArr=new JSONArray(data);
            for (int i=0;i<landArr.length();i++)
            {
                ret.add(landArr.getJSONObject(i));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return ret;
    }

    public static ArrayList<JSONObject> readChanceList()
    {
        ArrayList<JSONObject> chanceList=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(CHANCE_SOURCE_FILE));
            String data="";
            String line;
            while((line= br.readLine())!=null)
            {
                data+=line;
            }
            JSONArray landArr = new JSONArray(data);
            for (int i = 0; i < landArr.length();i++)
            {
                chanceList.add(landArr.getJSONObject(i));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return chanceList;
    }

    public static void move(Player player, PlayerItem playerItem, ArrayList<LandItem> landItemList, int[] dice){
        int step = dice[0] + dice[1];
        if (player.isInPrison()){
            if (dice[0] == dice[1]){
                JOptionPane.showMessageDialog(null, "You are released from prison");
                player.setInPrison(false);
                player.setCurrentLocation(player.getCurrentLocation() + step);
                DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,step);
            }
            else{
                int result;
                if (player.getMoney() >= 200){
                    result = JOptionPane.showConfirmDialog(null, "Do you want to pay 200$ for the prison to be released?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );

                    if(result == JOptionPane.YES_OPTION){
                        player.setInPrison(false);
                        player.setMoney(player.getMoney() - 200);
                        player.setCurrentLocation(player.getCurrentLocation() +  step);
                        DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,step);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"You do not have enough to pay for the prison to be released.");
                }
            }
        }
        else{
            player.setCurrentLocation(player.getCurrentLocation() +  step);
            DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,step);
        }

        if (player.getCurrentLocation() >= landItemList.size()){
            JOptionPane.showMessageDialog(null, "You have passed the target land and received " + GameParameter.getMoney() + "$.");
            player.setMoney(player.getMoney() + GameParameter.getMoney());
            player.setCurrentLocation(player.getCurrentLocation() - landItemList.size());
        }

    }
    public static int handle(Player player, PlayerItem playerItem, ArrayList<LandItem> landItemList, ArrayList<Chance> chanceList){
        LandItem landItem = landItemList.get(player.getCurrentLocation());

        if(landItem.getLand().isLand()){
            if(landItem.getOwner() != null) {
                if (landItem.getOwner() != player && landItem.isMortgage() == false) {
                    JOptionPane.showMessageDialog(null, "You have to pay rent for " + landItem.getOwner().getName() + ": " + landItem.getRent() + "$");
                    player.setMoney(player.getMoney() - landItem.getRent());
                    landItem.getOwner().setMoney(landItem.getOwner().getMoney() + landItem.getRent());
                    return checkMoney(player);
                }
            }
        }
        else{
            switch (landItem.getLand().getPriority()){
                case -4:
                    JOptionPane.showMessageDialog(null, "You are locked in prison");
                    DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,18);
                    player.setCurrentLocation(9);
                    player.setInPrison(true);
                    break;
                case 0:
                    Random rd = new Random();
                    int chanceIndex = rd.nextInt(chanceList.size());
                    Chance chance = chanceList.get(chanceIndex);

                    if(chance.getType() == 0){
                        JOptionPane.showMessageDialog(null,chance.getDescription(),"Notification",JOptionPane.INFORMATION_MESSAGE);

                        int currentPosition = player.getCurrentLocation();
                        if(currentPosition < 9){
                            DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,9-currentPosition);
                        }
                        else{
                            DisplayAction.movePlayer(MainGameScreen.gameLayerPanel,playerItem,9-currentPosition + 36);
                        }

                        player.setInPrison(true);
                        player.setCurrentLocation(9);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,chance.getDescription() + ": " + String.valueOf(chance.getType() * chance.getValue()), chance.getName(),JOptionPane.INFORMATION_MESSAGE);
                        player.setMoney(player.getMoney() + chance.getType() * chance.getValue());
                    }
//                    player.setMoney(player.getMoney() - chance.getValue());
                    return checkMoney(player);
                default:
                    break;
            }
        }
        return 1;
    }

    public static int checkMoney(Player player){
        if (player.getMoney() < 0) {
            int totalAssets = player.getTotalAssets();

            if (totalAssets < 0) {
                JOptionPane.showMessageDialog(null,player.getName() + ", Your total assets are not enough to repay the debt. You have been bankrupt.");
                return -1;
            } else {
                JOptionPane.showMessageDialog(null, "You do not have enough money to pay rent. You have to sell Houses or mortage land.");
                return 0;
            }
        }
        return 1;
    }

    public static void buildHouse(Player player, LandItem landItem){
        if(landItem.getnHouse() == 5){
            JOptionPane.showMessageDialog(null, player.getName() + ", You cannot build more houses on " + landItem.getLand().getName());
        }
        else{
            HouseItem h;
            Point pos;

            int index = 0;
            for(int i = 0; i < landItem.getIsHasHouse().length; i++){
                if(!landItem.getIsHasHouse()[i]){
                    index = i;
                    break;
                }
            }

            if(index != 4){
                h = new HouseItem(HouseItem.HOUSE);
                pos = DisplayAction.getHousePosition(player.getCurrentLocation(),index+1,HouseItem.HOUSE);
                h.setBounds(pos.x,pos.y,24,24);
                MainGameScreen.gameLayerPanel.add(h,JLayeredPane.MODAL_LAYER);
            }
            else{
                h = new HouseItem(HouseItem.HOTEL);
                pos = DisplayAction.getHousePosition(player.getCurrentLocation(),index+1,HouseItem.HOTEL);
                h.setBounds(pos.x,pos.y,24*2,24*2);
                MainGameScreen.gameLayerPanel.add(h,JLayeredPane.DRAG_LAYER);

            }

            landItem.setIsHasHouse(index, true);

            landItem.setnHouse(landItem.getnHouse() + 1);
            player.setMoney(player.getMoney() - landItem.getPriceBuildHouse());

            int finalIndex = index;
            h.addMouseListener(new MouseClickListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if(MainGameScreen.isIsSell() && landItem.getOwner() == MainGameScreen.playerList.getSelectedValue()){
                        int choose = JOptionPane.showConfirmDialog(null, player.getName() + ", Are you sure that you want sell this house on " + landItem.getLand().getName(),
                                "Confirm",
                                JOptionPane.YES_NO_OPTION);
                        if(choose == JOptionPane.YES_OPTION){
                            h.setVisible(false);
                            h.setEnabled(false);
                            MainGameScreen.gameLayerPanel.remove(h);
                            landItem.setnHouse(landItem.getnHouse() - 1);
                            landItem.setIsHasHouse(finalIndex, false);

                            player.setMoney((int) (player.getMoney() + landItem.getPriceBuildHouse() * 0.8));
                            MainGameScreen.playerList.updateUI();
                        }
                    }
                }
            });


        }


    }

    public static int saveParams()
    {
        System.out.println("Save Data");
        JSONObject settings=new JSONObject();
        settings.put("START_MONEY",GameParameter.getMoney());
        settings.put("PLAYER_NAMES",new JSONArray(GameParameter.get_playerNameList().toArray()));
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(PATH_PARAM_FILE));
            System.out.println(settings);
            bw.write(settings.toString());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


}
