package Handler;

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

    public static void move(Player player, ArrayList<LandItem> landItemList){
        Random rd = new Random();
        int dice1 = rd.nextInt(6) + 1;
        int dice2 = rd.nextInt(6) + 1;
        System.out.println(dice1 + " + " + dice2 + " = " + (dice1 + dice2));

        if (player.isInPrison()){
            if (dice1 == dice2){
                JOptionPane.showMessageDialog(null, "Bạn đã được ra tù");
                player.setInPrison(false);
            }
            else{
                // TODO: Xử lý khi đi tù
            }
        }
        else{
            player.setCurrentLocation(player.getCurrentLocation() + dice1 + dice2);

            if (player.getCurrentLocation() >= landItemList.size()){
                JOptionPane.showMessageDialog(null, "Bạn đã qua trạm khỏi hành và nhận được 200$.");
                player.setMoney(player.getMoney() + 200);
                player.setCurrentLocation(player.getCurrentLocation() - landItemList.size());

            }
        }

    }
    public static void handle(Player player, ArrayList<LandItem> landItemList){
        LandItem landItem = landItemList.get(player.getCurrentLocation());

        if(landItem.getLand().isLand()){
            if(landItem.getOwner() == null && player.getMoney() > landItem.getLand().getPrice()){
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn mua ô đất " + landItem.getLand().getName() + " không?", "Mua đất", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );

                if (choice == JOptionPane.YES_OPTION){
                    player.setMoney(player.getMoney() - landItem.getLand().getPrice());
                    landItem.setOwner(player);
                    player.getLandList().add(landItem);
                }
            }
        }
        else{
            switch (landItem.getLand().getPriority()){
                case -1:
                    JOptionPane.showMessageDialog(null, "Bạn đã đến đích, được thưởng 200$");
                    player.setMoney(player.getMoney() + 200);
                    break;
                case -4:
                    JOptionPane.showMessageDialog(null, "Bạn bị vào tù");
                    player.setCurrentLocation(9);
                    player.setInPrison(true);
                    break;
                case 0:
                    // Todo: Xử lý khi vào ô cơ hội
                    break;
                default:
                    break;
            }
        }
    }

}
