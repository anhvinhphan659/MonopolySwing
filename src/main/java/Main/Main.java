package Main;

import Handler.GameHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

//        generate Params
        GameHandler.generateParams();
        //init UI
        GameFrame.getGameDisplay();

    }
}
