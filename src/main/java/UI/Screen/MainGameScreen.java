package UI.Screen;

import Handler.GameHandler;
import Main.GameFrame;
import Model.Chance;
import Model.Land;
import Model.Player;
import UI.Item.CornerItem;
import UI.Item.LandItem;
import UI.Item.PlayerItem;
import UI.Renderer.LandLVRenderer;
import UI.Renderer.PlayerLVRenderer;
import Model.GameParameter;
import UI.Util.DisplayAction;
import com.github.weisj.darklaf.listener.MouseClickListener;
import org.jdesktop.jxlayer.JXLayer;
import org.json.JSONObject;
import org.pbjar.jxlayer.plaf.ext.transform.DefaultTransformModel;
import org.pbjar.jxlayer.plaf.ext.transform.TransformUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainGameScreen extends JPanel {
    private int indexLand=0;
    private int playingIndex = -1;
    public final static int WIDTH_SCREEN=1100;
    public final static int HEIGHT_SCREEN=750;

    private final static int START_BOARD_POS_X=25;
    private final static int START_BOARD_POS_Y=25;

    private final static int ACTION_INTERVAL=100;

    private static ArrayList<JSONObject> landInformationList;
    private static ArrayList<JSONObject> chanceInformationList;

    private int nPlayer;
    private ArrayList<Player> playerArrayList;
    private ArrayList<PlayerItem> playerItemsArrayList;
    private ArrayList<LandItem> landItemList;

    private ArrayList<Chance> chanceList;
    private boolean isSell;
    private boolean isMortage;
    private boolean isTakedDice;
    private int nDuplicate;


    public MainGameScreen() throws IOException {
        nPlayer = GameParameter.getnPlayer();
        playerArrayList = new ArrayList<>();
        landItemList=new ArrayList<>();
        landInformationList= GameHandler.readLandList();
//        playerItems=new ArrayList<>();
        initChanceList();


        initComponents();
        setUpOthersForComponent();
        drawGameBoard();
    }

    private void setUpActions()
    {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(MainGameScreen.this);
                gameFrame.setSize(new Dimension(ChooseNPlayerScreen.WIDTH_SCREEN,ChooseNPlayerScreen.HEIGHT_SCREEN));
                gameFrame.changeGamePanel(new ChooseNPlayerScreen());
            }
        });

        // TODO: Add action for other game logic button
        buildBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = playerList.getSelectedValue();
                LandItem landItem = landItemList.get(player.getCurrentLocation());
//                GameHandler.buildHouse(player, landItem);

                if(!landItem.getLand().isLand()){
                    JOptionPane.showMessageDialog(null,player.getName() + ", This is not land, so you can't build house(s).");
                }
                else{
                    if(landItem.getOwner() == null){
                        JOptionPane.showMessageDialog(null,player.getName() + ", You haven't bought " + landItem.getLand().getName() + " yet, so you can't build house(s).");
                    }
                    else{
                        if(landItem.getOwner() != player){
                            JOptionPane.showMessageDialog(null,player.getName() + ", You are not the owner of " + landItem.getLand().getName() + ", so you can't build house(s).");
                        }
                        else{
                            if(!isBuyAllLandSameLevel(player, landItem)){
                                JOptionPane.showMessageDialog(null,player.getName() + ", You haven't bought all lands which have same color with " + landItem.getLand().getName() + ", so you can't build house(s).");
                            }
                            else {
                                if(player.getMoney() < landItem.getPriceBuildHouse()){
                                    JOptionPane.showMessageDialog(null,player.getName() + ", You do not enough money to build house on " + landItem.getLand().getName() + ".");
                                }
                                else{
                                    GameHandler.buildHouse(player, landItem);
                                    updateMoneyLabel();
                                }
                            }
                        }
                    }
                }




            }
        });

        sellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                GameHandler.sellHouse();
                isSell = !isSell;

                if(isSell){
                    buildBtn.setEnabled(false);
                    mortageBtn.setEnabled(false);
                    buyBtn.setEnabled(false);
                }
                else{
                    buildBtn.setEnabled(true);
                    mortageBtn.setEnabled(true);
                    buyBtn.setEnabled(true);
                }
            }
        });

        mortageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMortage = !isMortage;

                if(isMortage){
                    buildBtn.setEnabled(false);
                    sellBtn.setEnabled(false);
                    buyBtn.setEnabled(false);
                }
                else{
                    buildBtn.setEnabled(true);
                    sellBtn.setEnabled(true);
                    buyBtn.setEnabled(true);
                }
            }
        });

        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player  player = playerList.getSelectedValue();
                LandItem land = landItemList.get(player.getCurrentLocation());

                if(land.getLand().isLand()){
                    if(land.getOwner() == null){
                        if (player.getMoney() < land.getLand().getPrice()){
                            JOptionPane.showMessageDialog(null, "You do not have enough to buy this land");
                        }
                        else{
                            int choose = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want buy " + land.getLand().getName() + "?",
                                    "Buy Land",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                            if(choose == JOptionPane.YES_OPTION){

                                land.setOwner(player);

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Color ownColor=PlayerItem.ownColors[playerItemsArrayList.get(playerList.getSelectedIndex()).getPlayerColor()];
                                        land.setColor(ownColor);
                                    }
                                });

                                player.setMoney(player.getMoney() - land.getLand().getPrice());
                                player.getLandList().add(land);
                                updateMoneyLabel();

                                landDefaultListModel.addElement(land.getLand());
                            }
                        }
                    }
                    else{
                        if(land.getOwner() == player){
                            JOptionPane.showMessageDialog(null, "You bought this land");
                        }
                        else{
                            if(land.isMortgage()){
                                JOptionPane.showMessageDialog(null,
                                        land.getLand().getName() + " has been mortgaged for the bank. You can not buy it.");
                            }
                            else {
                                if (player.getMoney() < land.getPriceOfLandAndHourseWhenSell()){
                                    JOptionPane.showMessageDialog(null, "You do not have enough to buy this land");
                                }
                                else{
                                    int choose = JOptionPane.showConfirmDialog(null,
                                            land.getOwner().getName() + " owns land" + land.getLand().getName() + ". Do you want to buy back it for $" + land.getPriceOfLandAndHourseWhenSell() + "?",
                                            "Buy Land",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE);
                                    if(choose == JOptionPane.YES_OPTION){
                                        choose = JOptionPane.showConfirmDialog(null,
                                                "Hello, " + land.getOwner().getName() + ". " + player.getName() + " want buy " + land.getLand().getName() + " for $" + land.getPriceOfLandAndHourseWhenSell() + ". Do you want to sell it?",
                                                "Sell Land",
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.QUESTION_MESSAGE);

                                        if(choose == JOptionPane.YES_OPTION){
                                            land.getOwner().getLandList().remove(land);
                                            land.getOwner().setMoney(land.getOwner().getMoney() + land.getPriceOfLandAndHourseWhenSell());

                                            land.setOwner(player);

                                            player.setMoney(player.getMoney() - land.getPriceOfLandAndHourseWhenSell());
                                            player.getLandList().add(land);
                                            updateMoneyLabel();

                                            landDefaultListModel.addElement(land.getLand());
                                        }
                                    }
                                }
                            }

                        }

                    }
                }
            }
        });


    }
    private void drawGameBoard()
    {
        Thread t=new Thread(){
            @Override
            public void run() {
                //lock backbtn
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            backBtn.setEnabled(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                //lock dice
                isTakedDice=true;
                buyBtn.setEnabled(false);
                buildBtn.setEnabled(false);
                sellBtn.setEnabled(false);
                mortageBtn.setEnabled(false);
                doneBtn.setEnabled(false);

                int board_x = START_BOARD_POS_X;
                int board_y = START_BOARD_POS_Y;

                drawLaneLine(1, new ArrayList<>(), board_x, board_y);
                board_x+=CornerItem.HEIGHT_ITEM+LandItem.HEIGHT_ITEM*8;
                drawLaneLine(2, new ArrayList<>(), board_x, board_y);
                board_y+=CornerItem.HEIGHT_ITEM+LandItem.HEIGHT_ITEM*8;
                drawLaneLine(-1, new ArrayList<>(), board_x, board_y);
                board_x-=CornerItem.HEIGHT_ITEM+LandItem.HEIGHT_ITEM*8;
                drawLaneLine(-4, new ArrayList<>(), board_x, board_y);

                play();
                //unlock backbtn
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            backBtn.setEnabled(true);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                //unlock dice
                isTakedDice=false;
                buyBtn.setEnabled(true);
                buildBtn.setEnabled(true);
                sellBtn.setEnabled(true);
                mortageBtn.setEnabled(true);
                doneBtn.setEnabled(true);
            }
        };
        t.start();
    }

    private void drawLaneLine(int direction, ArrayList<Land> l,int startX,int startY)
    {
        //draw corner
        Land land=new Land(landInformationList,indexLand++);
        CornerItem corner=new CornerItem(land);
        try {
            int finalStartX = startX;
            int finalStartY = startY;
            DefaultTransformModel transformModel=new DefaultTransformModel();
            transformModel.setRotation(Math.toRadians(direction*90));
            transformModel.setScale(1);
            if(direction%2==0)
                transformModel.setScale(-1);
            JXLayer<JComponent> rotateCorner=TransformUtils.createTransformJXLayer(corner,transformModel);


            rotateCorner.setBounds(finalStartX, finalStartY,CornerItem.WIDTH_ITEM,CornerItem.HEIGHT_ITEM);
            // TODO: add mouse listener for rotate corner and rotate land
//            Land finalLand = land;
//            rotateCorner.addMouseListener(new MouseClickListener() {
//                @Override
//                public void mouseClicked(MouseEvent mouseEvent) {
//                    DisplayAction.showLandInformation(finalLand);
//                }
//            });
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    gameLayerPanel.add(rotateCorner,JLayeredPane.DEFAULT_LAYER);
                    gameLayerPanel.repaint();
//                    System.out.println("Draw corner");
                    try {
                        Thread.sleep(ACTION_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(direction%2!=0)
        {
            startX+=(direction>0)?CornerItem.HEIGHT_ITEM-25:-CornerItem.HEIGHT_ITEM+25;
        }
        else
        {
            startY+=(direction>0)?+CornerItem.HEIGHT_ITEM-25:-CornerItem.HEIGHT_ITEM+25;
        }
        landItemList.add(corner);
//        System.out.println(""+startX+"-"+startY);
        //draw land
        for(int i=1;i<=8;i++)
        {
            //get land from list
            land=new Land(landInformationList,indexLand++);
            LandItem landItem=new LandItem(land);
            try {
                int finalStartX1 = startX;
                int finalStartY1 = startY;
                DefaultTransformModel transformModel=new DefaultTransformModel();
                transformModel.setRotation(Math.toRadians(direction*90));
                transformModel.setScale(1);
                JXLayer<JComponent> rotateLandItem=TransformUtils.createTransformJXLayer(landItem,transformModel);
                Land finalLand = land;
                rotateLandItem.addMouseListener(new MouseClickListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(isMortage){
                            if(landItem.getOwner() != null && landItem.getOwner() == playerList.getSelectedValue()){
                                if(landItem.isMortgage()){
                                    int choose = JOptionPane.showConfirmDialog(null,
                                            "Are you sure you want to redeem " + finalLand.getName() + "?",
                                            "Redeem Land",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE);
                                    if(choose == JOptionPane.YES_OPTION){
                                        landItem.setMortgage(false);
                                        playerList.getSelectedValue().setMoney(playerList.getSelectedValue().getMoney() - landItem.getPriceOfLandWhenMortage() * 3);
                                        updateMoneyLabel();
                                    }
                                }
                                else{
                                    int choose = JOptionPane.showConfirmDialog(null,
                                            "Are you sure you want to mortage " + finalLand.getName() + "?",
                                            "Mortage Land",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE);
                                    if(choose == JOptionPane.YES_OPTION){
                                        landItem.setMortgage(true);
                                        playerList.getSelectedValue().setMoney(playerList.getSelectedValue().getMoney() + landItem.getPriceOfLandWhenMortage());
                                        updateMoneyLabel();
                                    }
                                }

                            }
                        }else {
                            DisplayAction.showLandInformation(finalLand);
                        }
                    }
                });
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        rotateLandItem.setBounds(finalStartX1,finalStartY1,CornerItem.HEIGHT_ITEM,CornerItem.WIDTH_ITEM);
                        gameLayerPanel.add(rotateLandItem,JLayeredPane.DEFAULT_LAYER);
                        gameLayerPanel.repaint();

//                        System.out.println("Draw land");
//                        System.out.println(""+finalStartX1+"-"+finalStartY1);
                        try {

                            Thread.sleep(ACTION_INTERVAL);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if(direction%2!=0)
            {
                startX+=(direction>0)?LandItem.HEIGHT_ITEM:-LandItem.HEIGHT_ITEM;
            }
            else
            {
                startY+=(direction>0)?LandItem.HEIGHT_ITEM:-LandItem.HEIGHT_ITEM;
            }
            landItemList.add(landItem);
        }
    }


    private void setUpOthersForComponent() throws IOException {
        gameLayerPanel=new JLayeredPane();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameLayerPanel,BorderLayout.CENTER);

        playerDefaultListModel=new DefaultListModel<>();
        landDefaultListModel=new DefaultListModel<>();

        playerList.setModel(playerDefaultListModel);
        landList.setModel(landDefaultListModel);

        playerList.setCellRenderer(new PlayerLVRenderer());

        ArrayList<String> namePlayerList = GameHandler.readNameFile();
        for(int i = 0; i< nPlayer; i++){
            playerArrayList.add(new Player(namePlayerList.get(i), GameParameter.getMoney()));
            playerDefaultListModel.addElement(playerArrayList.get(i));
        }

        playerList.setEnabled(false);
        playerList.setBackground(Color.WHITE);
        playerList.setSelectedIndex(0);

        landList.setCellRenderer(new LandLVRenderer());
        landList.setEnabled(false);


        showPlayerInfo(playerArrayList.get(0));

        playerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                showPlayerInfo(playerList.getSelectedValue());
            }
        });

        playerItemsArrayList = new ArrayList<>();
        Integer[] colors={0,1,2,3,4,5};
        List<Integer> colorList= Arrays.asList(colors);
        Rectangle[] positions={
                new Rectangle(30,30+16*0+2*0,16,16),
                new Rectangle(30+5+16,30+16*0+2*0,16,16),
                new Rectangle(30,30+16*1+2*1,16,16),
                new Rectangle(30+5+16,30+16*1+2*1,16,16),
                new Rectangle(80,30+16*0+2*0,16,16),

        };
        Collections.shuffle(colorList);

        int i=0;

        for(Player player: playerArrayList){
            PlayerItem pi=new PlayerItem(player);

            pi.setPlayerColor(colorList.get(i));
            pi.setBounds(positions[i]);
            gameLayerPanel.add(pi,JLayeredPane.MODAL_LAYER);
            ++i;
            playerItemsArrayList.add(pi);
        }
//        PlayerItem pi=new PlayerItem(new Player("name",1000));


//        JButton moveBtn=new JButton("Move");
//        moveBtn.setBounds(200,200,100,40);
//        gameLayerPanel.add(moveBtn);


        doneBtn=new JButton("DONE");
        doneBtn.setBounds(275,300,100,40);
        gameLayerPanel.add(doneBtn,JLayeredPane.DEFAULT_LAYER);
        doneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: handle done action
                if(!isTakedDice){
                    JOptionPane.showMessageDialog(null,playerList.getSelectedValue().getName() + ", You have not completed your turn.");
                }
                else{
                    if (playerList.getSelectedValue().getMoney() >= 0){
                        buyBtn.setEnabled(true);
                        buildBtn.setEnabled(true);
                        sellBtn.setEnabled(true);
                        mortageBtn.setEnabled(true);
                        play();

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "You still haven't paid the debt yet. Please pay off the debt before the end of the version.");
                    }
                }


            }
        });

        diceBtn1=new JButton();
        diceBtn2=new JButton();

        diceBtn1.setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/dice1.png")));
        diceBtn2.setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/dice6.png")));
        diceBtn1.setBounds(250,150,64,64);
        diceBtn2.setBounds(350,150,64,64);
        diceBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isTakedDice){
                    int[] dice = rollDices();
                    GameHandler.move(playerList.getSelectedValue(),playerItemsArrayList.get(playerList.getSelectedIndex()), landItemList, dice);
                    updateMoneyLabel();
                    int result = GameHandler.handle(playerList.getSelectedValue(),playerItemsArrayList.get(playerList.getSelectedIndex()), landItemList, chanceList);
                    updateMoneyLabel();

                    if (result == -1){
                        endGame();
                    }
                    else {
                        // 2 same dice can continue
                        if(dice[0] != dice[1])
                            isTakedDice = true;
                        else{
                            nDuplicate++;
                            if(nDuplicate == 3){
                                JOptionPane.showMessageDialog(null,playerList.getSelectedValue().getName() + ", you have to go to fail.");
                                int currentPosition = playerList.getSelectedValue().getCurrentLocation();
                                playerList.getSelectedValue().setCurrentLocation(9);
                                playerList.getSelectedValue().setInPrison(true);

                                if(currentPosition < 9){
                                    DisplayAction.movePlayer(gameLayerPanel,playerItemsArrayList.get(playerList.getSelectedIndex()),9-currentPosition);
                                }
                                else{
                                    DisplayAction.movePlayer(gameLayerPanel,playerItemsArrayList.get(playerList.getSelectedIndex()),9-currentPosition + 36);
                                }
                                isTakedDice = true;
                            }
                        }
                    }

                }
            }
        });
        diceBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isTakedDice){
                    int[] dice = rollDices();
                    GameHandler.move(playerList.getSelectedValue(),playerItemsArrayList.get(playerList.getSelectedIndex()), landItemList, dice);
                    updateMoneyLabel();
                    int result = GameHandler.handle(playerList.getSelectedValue(),playerItemsArrayList.get(playerList.getSelectedIndex()), landItemList, chanceList);
                    updateMoneyLabel();

                    if(result == -1){
                        endGame();
                    }
                    else{
                        // 2 same dice can continue
                        if(dice[0] != dice[1])
                            isTakedDice = true;
                        else{
                            nDuplicate++;
                            if(nDuplicate == 3){
                                JOptionPane.showMessageDialog(null,playerList.getSelectedValue().getName() + ", you have to go to fail.");
                                int currentPosition = playerList.getSelectedValue().getCurrentLocation();
                                playerList.getSelectedValue().setCurrentLocation(9);
                                playerList.getSelectedValue().setInPrison(true);

                                if(currentPosition < 9){
                                    DisplayAction.movePlayer(gameLayerPanel,playerItemsArrayList.get(playerList.getSelectedIndex()),9-currentPosition);
                                }
                                else{
                                    DisplayAction.movePlayer(gameLayerPanel,playerItemsArrayList.get(playerList.getSelectedIndex()),9-currentPosition + 36);
                                }
                                isTakedDice = true;
                            }
                        }
                    }

                }

            }
        });

        gameLayerPanel.add(diceBtn1,JLayeredPane.DEFAULT_LAYER);
        gameLayerPanel.add(diceBtn2,JLayeredPane.DEFAULT_LAYER);

    }

    private void initComponents() {
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList<>();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        moneyLb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        landList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        buildBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        mortageBtn = new javax.swing.JButton();
        buyBtn = new javax.swing.JButton();

        jButton5.setText("BACK");

        setSize(new Dimension(WIDTH_SCREEN,HEIGHT_SCREEN));
        setPreferredSize(new java.awt.Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 32767));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 750));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        backBtn.setText("BACK");
        jPanel10.add(backBtn);

        jPanel1.add(jPanel10);

        jPanel5.setMaximumSize(new java.awt.Dimension(200, 300));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 250));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));

        jScrollPane1.setViewportView(playerList);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jPanel9.setMaximumSize(new java.awt.Dimension(35222, 355522));

        jPanel12.setBackground(null);
        jPanel12.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel12.setPreferredSize(new java.awt.Dimension(200, 50));

        jLabel1.setText("MONEY");
        jPanel12.add(jLabel1);

        moneyLb.setText("000$");
        jPanel12.add(moneyLb);

        jPanel9.add(jPanel12);

        jLabel3.setText("LAND LIST");
        jPanel9.add(jLabel3);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 250));

        landList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(landList);

        jPanel9.add(jScrollPane2);

        jPanel1.add(jPanel9);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setPreferredSize(new java.awt.Dimension(650, 750));
        jPanel2.setLayout(new java.awt.BorderLayout());

        gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        gamePanel.setMaximumSize(new java.awt.Dimension(550, 32767));
        gamePanel.setPreferredSize(new java.awt.Dimension(650, 650));
        gamePanel.setLayout(null);
        jPanel2.add(gamePanel, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 20, 10));
        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 20, 0));

        buildBtn.setText("BUILD");
        jPanel4.add(buildBtn);

        sellBtn.setText("SELL");
        sellBtn.setMaximumSize(new java.awt.Dimension(50, 22));
        sellBtn.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel4.add(sellBtn);

        mortageBtn.setText("MORTAGE");
        jPanel4.add(mortageBtn);

        buyBtn.setText("BUY");
        jPanel4.add(buyBtn);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
        //add actions
        setUpActions();
    }

    private void initChanceList(){
        chanceInformationList = GameHandler.readChanceList();
        chanceList = new ArrayList<>();
        for(int i = 0; i < chanceInformationList.size(); i++){
            chanceList.add(new Chance(chanceInformationList.get(i)));
        }
    }

    private void showPlayerInfo(Player player){
        moneyLb.setText(String.valueOf(player.getMoney())+"$");
        landDefaultListModel.removeAllElements();
        for(LandItem item: player.getLandList()){
            landDefaultListModel.addElement(item.getLand());
        }
    }

    private int[] rollDices()
    {
        int n1=DisplayAction.randomDice();
        int n2=DisplayAction.randomDice();
//        System.out.println(""+n1+"-"+n2);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                diceBtn1.setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/dice" +
                        String.valueOf(n1) +
                        ".png")));
                diceBtn2.setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/dice" +
                        String.valueOf(n2) +
                        ".png")));
            }
        });
        // TODO: get current player and move (using move player function in  DisplayAction )
        return new int[] {n1, n2};
    }

    public int nextPlayer(){
        playingIndex++;

        if(playingIndex == nPlayer){
            playingIndex = 0;
        }

        playerList.setSelectedIndex(playingIndex);

        showPlayerInfo(playerList.getSelectedValue());

        return playingIndex;
    }

    private void play(){
        isSell = false;
        isMortage = false;
        isTakedDice = false;
        nDuplicate = 0;

        int i = nextPlayer();

        Player player = playerArrayList.get(i);
        JOptionPane.showMessageDialog(null, "" + player.getName()+"'s Turn");


//        dice = rollDices();
//        DisplayAction.movePlayer(gameLayerPanel,pi,step);

    }
    public void endGame(){
        DisplayAction.showRank(playerArrayList);
        GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(MainGameScreen.this);
        gameFrame.setSize(new Dimension(ChooseNPlayerScreen.WIDTH_SCREEN,ChooseNPlayerScreen.HEIGHT_SCREEN));
        gameFrame.changeGamePanel(new ChooseNPlayerScreen());
    }

    private boolean isBuyAllLandSameLevel(Player player, LandItem landItem){
        int level = landItem.getLand().getPriority();
        int n_land = 0;
        int n_owed_land = 0;
        for(LandItem li: landItemList){
            if(li.getLand().getPriority() == level){
                n_land++;
            }
        }
        for(LandItem li: player.getLandList()){
            if(li.getLand().getPriority() == level){
                n_owed_land++;
            }
        }

        return n_land == n_owed_land;
    }

    private void updateMoneyLabel(){
        moneyLb.setText(String.valueOf(playerList.getSelectedValue().getMoney()));

//        PlayerLVRenderer playerLVRenderer = (PlayerLVRenderer)playerList.getCellRenderer();
//        playerLVRenderer.se
    }
    private javax.swing.JButton backBtn;
    private javax.swing.JButton buildBtn;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Land> landList;
    private javax.swing.JLabel moneyLb;
    private javax.swing.JButton mortageBtn;
    private javax.swing.JList<Player> playerList;
    private javax.swing.JButton sellBtn;
    private javax.swing.JButton buyBtn;

    //model list view
    private DefaultListModel<Player> playerDefaultListModel;
    private DefaultListModel<Land> landDefaultListModel;

    //layerpane
    public static JLayeredPane gameLayerPanel;

    //other button
    private JButton doneBtn;
    private JButton diceBtn1;
    private JButton diceBtn2;
}
