import UI.GameFrame;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        GameFrame.getGameDisplay();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","hello");
        jsonObject.put("class","1111111");


        System.out.println(jsonObject);
        System.out.println(jsonObject.get("name"));

    }
}
