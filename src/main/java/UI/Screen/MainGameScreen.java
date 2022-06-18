package UI.Screen;

import Handler.GameHandler;
import Handler.PlayGame;
import Main.GameFrame;
import Model.Chance;
import Model.Land;
import Model.Player;
import UI.Item.CornerItem;
import UI.Item.HouseItem;
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
    private ArrayList<LandItem> landItemList;
    private ArrayList<PlayerItem> playerItems;
    private ArrayList<Chance> chanceList;


    public MainGameScreen() throws IOException {
        nPlayer = GameParameter.getnPlayer();
        playerArrayList = new ArrayList<>();
        landItemList=new ArrayList<>();
        landInformationList= GameHandler.readLandList();
        playerItems=new ArrayList<>();
        initChanceList();


        initComponents();
        setUpOthersForComponent();
        drawGameBoard();

//        PlayGame play = new PlayGame(this);
//        play.play(playerArrayList, landItemList, chanceList);
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
                HouseItem h=new HouseItem(HouseItem.HOUSE);
                Point pos=DisplayAction.getHousePosition(1,1,HouseItem.HOUSE);
                h.setBounds(pos.x,pos.y,24,24);
                HouseItem h2=new HouseItem(HouseItem.HOUSE);
                pos=DisplayAction.getHousePosition(35,3,HouseItem.HOUSE);
                h2.setBounds(pos.x,pos.y,24,24);
                gameLayerPanel.add(h,JLayeredPane.MODAL_LAYER);
                gameLayerPanel.add(h2,JLayeredPane.MODAL_LAYER);
            }
        });


    }
    private void drawGameBoard()
    {
        Thread t=new Thread(){
            @Override
            public void run() {
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

            }
        };
        t.start();
    }

    private void drawLaneLine(int direction, ArrayList<Land> l,int startX,int startY)
    {
//        System.out.println(""+startX+"-"+startY);
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
                    System.out.println("Draw corner");
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
                        DisplayAction.showLandInformation(finalLand);
                    }
                });
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        rotateLandItem.setBounds(finalStartX1,finalStartY1,CornerItem.HEIGHT_ITEM,CornerItem.WIDTH_ITEM);
                        gameLayerPanel.add(rotateLandItem,JLayeredPane.DEFAULT_LAYER);
                        gameLayerPanel.repaint();

                        System.out.println("Draw land");
                        System.out.println(""+finalStartX1+"-"+finalStartY1);
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
        PlayerItem pi=new PlayerItem(new Player("name",1000));
        pi.setBounds(30,30,30,30);
        gameLayerPanel.add(pi,JLayeredPane.MODAL_LAYER);

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
                play();

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
                rollDices();
                DisplayAction.movePlayer(gameLayerPanel,pi,5);
            }
        });
        diceBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDices();
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
        tradeBtn = new javax.swing.JButton();

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

        tradeBtn.setText("TRADE");
        jPanel4.add(tradeBtn);

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
        moneyLb.setText(String.valueOf(player.getMoney()));
        landDefaultListModel.removeAllElements();
        for(LandItem item: player.getLandList()){
            landDefaultListModel.addElement(item.getLand());
        }
    }

    private void rollDices()
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
        int i = nextPlayer();

        Player player = playerArrayList.get(i);
        JOptionPane.showMessageDialog(null, "Đến lượt của " + player.getName());
        GameHandler.move(player, landItemList);
        GameHandler.handle(player, landItemList);
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
    private javax.swing.JButton tradeBtn;

    //model list view
    private DefaultListModel<Player> playerDefaultListModel;
    private DefaultListModel<Land> landDefaultListModel;

    //layerpane
    JLayeredPane gameLayerPanel;

    //other button
    private JButton doneBtn;
    private JButton diceBtn1;
    private JButton diceBtn2;
}
