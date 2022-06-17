package Model;

import UI.Item.LandItem;

import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private int currentLocation;
    private boolean isInPrison;
    private ArrayList<LandItem> landList;


    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.currentLocation = 0;
        this.isInPrison = false;
        this.landList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public ArrayList<LandItem> getLandList() {
        return landList;
    }

    public boolean isInPrison() {
        return isInPrison;
    }
    //    public void setName(String name) {
//        this.name = name;
//    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setInPrison(boolean inPrison) {
        isInPrison = inPrison;
    }
}
