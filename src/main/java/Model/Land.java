package Model;

import org.json.JSONObject;

import java.awt.*;

public class Land {
    public static final Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.PINK, Color.GRAY};

    private String name;
    private int price;
    private int priority;
    private boolean isLand;

    public Land(String name, int location, int priority,boolean isLand) {
        this.name = name;
        this.price = location;
        this.priority = priority;
        this.isLand=isLand;
    }

    public Land(JSONObject landInformation)
    {
        name=landInformation.getString("name");
        price=landInformation.getInt("landPrice");
        priority=landInformation.getInt("priority");
        isLand=landInformation.getBoolean("isLand");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isLand() {
        return isLand;
    }
}
