package Handler;

import Model.Chance;
import Model.Land;
import Model.Player;
import UI.Item.LandItem;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameHandler {
    public final static String PATH_NAME_FILE = "resources/names.txt";
    public static final String LAND_SOURCE_FILE="resources/lands.json";
    public static final String CHANCE_SOURCE_FILE="resources/chances.json";
    public static ArrayList<String> readNameFile() throws IOException {
        ArrayList<String> nameList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH_NAME_FILE))));
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

    public static void move(Player player, ArrayList<LandItem> landItemList, int[] dice){
        int step = dice[0] + dice[1];
        if (player.isInPrison()){
            if (dice[0] == dice[1]){
                JOptionPane.showMessageDialog(null, "You are released from prison");
                player.setInPrison(false);
                player.setCurrentLocation(player.getCurrentLocation() + step);
            }
            else{
                // TODO: Xử lý khi đi tù
                int result;
                if (player.getMoney() >= 200){
                    result = JOptionPane.showConfirmDialog(null, "Do you want to pay 200$ for the prison to be released?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );

                    if(result == JOptionPane.YES_OPTION){
                        player.setInPrison(false);
                        player.setMoney(player.getMoney() - 200);
                        player.setCurrentLocation(player.getCurrentLocation() +  step);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"You do not have enough to pay for the prison to be released.");
                }
            }
        }
        else{
            player.setCurrentLocation(player.getCurrentLocation() +  step);
        }

        if (player.getCurrentLocation() >= landItemList.size()){
            JOptionPane.showMessageDialog(null, "You have passed the target land and received 200$.");
            player.setMoney(player.getMoney() + 200);
            player.setCurrentLocation(player.getCurrentLocation() - landItemList.size());
        }

    }
    public static void handle(Player player, ArrayList<LandItem> landItemList, ArrayList<Chance> chanceList){
        LandItem landItem = landItemList.get(player.getCurrentLocation());

        if(landItem.getLand().isLand()){
            if(landItem.getOwner() != null) {
                if (landItem.getOwner() != player && landItem.isMortgage() == false) {
                    JOptionPane.showMessageDialog(null, "You have to pay rent for " + landItem.getOwner().getName());
                    player.setMoney(player.getMoney() - landItem.getRent());
                    checkMoney(player);
                }
            }
        }
        else{
            switch (landItem.getLand().getPriority()){
                case -4:
                    JOptionPane.showMessageDialog(null, "You are locked in prison");
                    player.setCurrentLocation(9);
                    player.setInPrison(true);
                    break;
                case 0:
                    Random rd = new Random();
                    int chanceIndex = rd.nextInt(chanceList.size());
                    Chance chance = chanceList.get(chanceIndex);

                    JOptionPane.showMessageDialog(null,chance.getDescription() + ": " + String.valueOf(chance.getType() * chance.getValue()), chance.getName(),JOptionPane.INFORMATION_MESSAGE);
                    player.setMoney(player.getMoney() + chance.getType() * chance.getValue());
                    checkMoney(player);
                    break;
                default:
                    break;
            }
        }
    }

    public static void checkMoney(Player player){
        if (player.getMoney() < 0) {
            int totalAssets = 0;
            for (LandItem land : player.getLandList()) {
                totalAssets += land.getPriceOfHouseWhenSell();
                totalAssets += land.getPriceOfLandWhenMortage();
            }

            if (totalAssets + player.getMoney() < 0) {
                // TODO: Xử lý EndGame
            } else {
                JOptionPane.showMessageDialog(null, "You do not have enough money to pay rent. You have to sell Houses or mortage land.");
            }
        }
    }
    public static void sellHouse(){


    };
    public static void mortageLand(){};

}
