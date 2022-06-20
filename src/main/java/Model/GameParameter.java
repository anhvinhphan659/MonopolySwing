package Model;

import org.json.JSONObject;

import java.util.ArrayList;

public class GameParameter {
    private static int _N_PLAYER = 2;
    private static ArrayList<String> _playerNameList = new ArrayList<>();
    private static ArrayList<JSONObject> _landInformationList =new ArrayList<>();
    private static ArrayList<JSONObject> _chanceInformationList =new ArrayList<>();
    private static int _MONEY = 200;


    public static int getNPlayer() {
        return _N_PLAYER;
    }

    public static void setNPlayer(int NPlayer) {
        GameParameter._N_PLAYER = NPlayer;
    }

    public static ArrayList<String> get_playerNameList() {
        return _playerNameList;
    }

    public static void set_playerNameList(ArrayList<String> pNL)
    {
        _playerNameList =pNL;
    }

    public static int getMoney() {
        return _MONEY;
    }

    public static void setMoney(int Money) {
        GameParameter._MONEY = Money;
    }

    public static ArrayList<JSONObject> get_landInformationList() {
        return _landInformationList;
    }

    public static void set_landInformationList(ArrayList<JSONObject> _landInformationList) {
        GameParameter._landInformationList = _landInformationList;
    }

    public static ArrayList<JSONObject> get_chanceInformationList() {
        return _chanceInformationList;
    }

    public static void set_chanceInformationList(ArrayList<JSONObject> _chanceInformationList) {
        GameParameter._chanceInformationList = _chanceInformationList;
    }
}
