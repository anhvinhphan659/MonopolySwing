package Model;

import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class Land {
    public static final Color[] colors = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.PINK,
            Color.GRAY};

    private int thLand;
    private String name;
    private int price;
    private int priority;
    private boolean isLand;

    public Land(JSONObject landInformation)
    {
        name=landInformation.getString("name");
        price=landInformation.getInt("landPrice");
        priority=landInformation.getInt("priority");
        isLand=landInformation.getBoolean("isLand");
    }
    public Land(ArrayList<JSONObject>lands,int th)
    {
        thLand=th;
        JSONObject landInformation=lands.get(th);
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

    public JSONObject toJSONObject()
    {
        JSONObject jo=new JSONObject();
        jo.put("name",name);
        jo.put("landPrice",price);
        jo.put("priority",priority);
        jo.put("isLand",isLand);
        return jo;
    }
}
