package Model;

import org.json.JSONObject;

public class Chance {
    private String name;
    private int type;
    private int value;
    private String description;

    public Chance(String name, int type, int value, String description){
        this.name = name;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    public Chance(JSONObject chanceInfo){
        name = chanceInfo.getString("name");
        type = chanceInfo.getInt("type");
        value = chanceInfo.getInt("value");
        description = chanceInfo.getString("description");
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    public JSONObject toJSONObject()
    {
        JSONObject jo=new JSONObject();
        jo.put("name",name);
        jo.put("type",type);
        jo.put("value",value);
        jo.put("description",description);
        return jo;
    }
}
