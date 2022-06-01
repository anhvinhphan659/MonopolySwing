package Model;

public class GameParameter {
    private static int nPlayer = 2;
    private static String[] playerNameList = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"};
    private static int money = 200;


    public static int getnPlayer() {
        return nPlayer;
    }

    public static void setnPlayer(int nPlayer) {
        GameParameter.nPlayer = nPlayer;
    }

    public static String[] getPlayerNameList() {
        return playerNameList;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        GameParameter.money = money;
    }
}
