package Main;

import Handler.GameHandler;
import UI.Item.HouseItem;
import UI.Util.DisplayAction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        System.out.println(DisplayAction.getHousePosition(12,1, HouseItem.HOUSE));
        System.out.println(DisplayAction.getHousePosition(12,2, HouseItem.HOUSE));
        System.out.println(DisplayAction.getHousePosition(12,3, HouseItem.HOUSE));
        System.out.println(DisplayAction.getHousePosition(12,4, HouseItem.HOUSE));
        System.out.println(DisplayAction.getHousePosition(12,5, HouseItem.HOUSE));

        //generate Params
        GameHandler.generateParams();
        //init UI
        GameFrame.getGameDisplay();

    }
}
