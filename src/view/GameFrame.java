//package view;
//
//import controller.GameController;
//import util.ColorMap;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GameFrame extends JFrame {
//
//    private GameController controller;
//    private JButton restartBtn;
//    private JButton saveBtn;
//    private JButton loadBtn;
//    private JButton redoBtn;
//    private JButton exitBtn;
//    private JButton timeBtn;
//
//    private JLabel stepLabel;
//    private JLabel scoreLabel;
//    private JLabel highestScoreLabel;
//    private JLabel timeLabel;
//
//
//    private GamePanel gamePanel;
//
//    private String username;
//    private int size;
//
//    private int winScore;
//
//    int elapsedTime = 12000;
//    int seconds = 0;
//    int minutes = 0;
//    int hours = 0;
//
//    boolean started = false;
//    String seconds_string = String.format("%02d", seconds);
//    String minutes_string = String.format("%02d", minutes);
//    String hours_string = String.format("%02d", hours);
//
//    Timer timer;
//
//
//    public GameFrame(int width, int height, String username, int size, int winScore) {
//        this.winScore = winScore;
//        this.username = username;
//        this.size = size;
//        this.setTitle("2024 CS109 Project Demo");
//        this.setLayout(null);
//        this.setSize(width, height);
//        ColorMap.InitialColorMap();
//        gamePanel = new GamePanel((int) (this.getHeight() * 0.8), username, size, winScore);
//        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
//        this.add(gamePanel);
//
//        this.controller = new GameController(gamePanel, gamePanel.getModel());
//
//        this.restartBtn = createButton("Restart", new Point(500, 150), 80, 30);
//        this.saveBtn = createButton("Save", new Point(500, 190), 80, 30);
//        if (username.isEmpty()){
//            saveBtn.setEnabled(false);
//        }
//        this.loadBtn = createButton("Load", new Point(500, 230), 80, 30);
//        if (username.isEmpty()){
//            loadBtn.setEnabled(false);
//        }
//        this.redoBtn = createButton("Redo", new Point(500, 270), 80, 30);
//        this.exitBtn = createButton("Exit", new Point(500, 310), 80, 30);
//
//        this.stepLabel = createLabel("Step: 0", new Font("serif", Font.ITALIC, 22), new Point(520, 10), 180, 50);
//        this.scoreLabel = createLabel("Score: 0", new Font("serif", Font.ITALIC, 22), new Point(520, 50), 180, 50);
//        this.highestScoreLabel = createLabel("HighScore: 0", new Font("serif", Font.ITALIC, 22), new Point(520, 90), 180, 50);
//        this.timeLabel = createLabel("Time: 0:00", new Font("serif", Font.ITALIC, 22), new Point(520, 350), 180, 50);
////        this.timeLabel = new JLabel();
//
//
//
//
//        if(false){
//            timer = new Timer(1000, e -> {
//
//                elapsedTime -= 1000;
//
//
//
//                hours = (elapsedTime / 3600000);
//                minutes = (elapsedTime/60000) % 60;
//                seconds = (elapsedTime/1000) %60;
//                seconds_string = String.format("%02d", seconds);
//                minutes_string = String.format("%02d", minutes);
//                hours_string = String.format("%02d", hours);
//
//                timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
//
//                if(hours == 0 && minutes ==0) {
//                    if(seconds == 0){
//                        System.out.println("wai");
//                        this.stop();
//                    }
//
//                }
//
//
//            });
//
//
//
//            timer.start();
//        }
//
//
//        gamePanel.setStepLabel(stepLabel);
//        gamePanel.setScoreLabel(scoreLabel);
//        gamePanel.setHighestsScoreLabel(highestScoreLabel);
//
//
//
//
//        //automatic load
//        if(!username.isEmpty()){
//            System.out.println("from gameframe"+ username + size);
//            controller.autoLoadGame(username, username, size);// the first username is filename, the second one is folder name, automatic load
//        }
//
//
//
//        this.restartBtn.addActionListener(e -> {
//            controller.restartGame();
//            gamePanel.requestFocusInWindow();//enable key listener
//        });
//        this.saveBtn.addActionListener(e -> {
//            String string = JOptionPane.showInputDialog(this, "Input path:");
//            controller.saveGame(string);
//            System.out.println(string);
//            gamePanel.requestFocusInWindow();//enable key listener
//        });
//        this.loadBtn.addActionListener(e -> {
//            String string = JOptionPane.showInputDialog(this, "Input path:");
//            controller.loadGame(string, username, size);
//            System.out.println(string);
//            gamePanel.requestFocusInWindow();//enable key listener
//        });
//
//        this.redoBtn.addActionListener(e -> {
//            controller.redoGame();
//            gamePanel.requestFocusInWindow();//enable key listener
//        });
//        this.exitBtn.addActionListener(e -> {
//            if(!username.isEmpty()){ //if he is guest
//                controller.exitGame(username);
//                System.out.println("guest won't run");
//            }
//            gamePanel.requestFocusInWindow();//enable key listener
//            System.exit(0);
//        });
//
//
//
//
//
//
//        //todo: add other button here
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }
//
//
//    private void stop(){
//        timer.stop();
//        new GameoverFrame(username);
//    }
//    private JButton createButton(String name, Point location, int width, int height) {
//        JButton button = new JButton(name);
//        button.setLocation(location);
//        button.setSize(width, height);
//        this.add(button);
//        return button;
//    }
//
//    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
//        JLabel label = new JLabel(name);
//        label.setFont(font);
//        label.setLocation(location);
//        label.setSize(width, height);
//        this.add(label);
//        return label;
//    }
//}

package view;

import controller.GameController;
import menu.OptionFrame;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton redoBtn;
    private JButton exitBtn;
    private JButton backBtn;
    private JButton timeBtn;
    JButton upButton, downButton, leftButton, rightButton;


    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel highestScoreLabel;
    private JLabel timeLabel;
    private JLabel timeLabel2;



    private GamePanel gamePanel;

    private String username;
    private int size;

    private int winScore;
    public String imagePath = "src/image/";

    long elapsedTime = 0;

    long limitElapsedTime = 60000;
    long seconds = 0;
    long minutes = 0;
    long hours = 0;

    long minutes2 = 0;
    long seconds2 = 0;

    long limitHours = 0;
    long limitMinutes = 0;
    long limitSeconds = 0;

    int optionMinutes = 1;

    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    String seconds_string2 = String.format("%02d", limitSeconds);
    String minutes_string2 = String.format("%02d", limitMinutes);
    String hours_string2 = String.format("%02d", limitHours);

    private int Difficulty = 1;

    Timer timer;

    public GameFrame(int width, int height, String username, int size, int winScore, int Difficulty, int minutesOption) {
        this.optionMinutes = minutesOption;
        this.limitElapsedTime *= optionMinutes;
        this.Difficulty = Difficulty;
        System.out.println("DIFFICULTY FROM FRAME" + this.Difficulty);
        this.winScore = winScore;
        this.username = username;
        this.size = size;
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8), username, size, winScore, Difficulty);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());

        this.restartBtn = createButton("⇅ Restart", new Point(500, 90), 90, 30);
        this.saveBtn = createButton("\uD83D\uDCBE Save", new Point(500, 130), 90, 30);
        this.loadBtn = createButton("\uD83C\uDFAE Load", new Point(500, 170), 90, 30);
        this.redoBtn = createButton("↩\uFE0F Redo", new Point(500, 210), 90, 30);
        this.exitBtn = createButton("❌ Exit", new Point(580, 420), 90, 30);
        this.backBtn = createButton("Back", new Point(460, 420), 90, 30);


        this.upButton = createButton("⏫", new Point(530, 250), 70, 40);
        this.downButton = createButton("⏬", new Point(530, 350), 70, 40);
        this.leftButton = createButton("◀\uFE0F", new Point(480, 300), 70, 40);
        this.rightButton = createButton("▶\uFE0F", new Point(580, 300), 70, 40);
        upButton.addActionListener(e -> {
            gamePanel.doMoveUp();
        });
        downButton.addActionListener(e -> {
            gamePanel.doMoveDown();
        });
        leftButton.addActionListener(e -> {
            gamePanel.doMoveLeft();
        });
        rightButton.addActionListener(e -> {
            gamePanel.doMoveRight();
        });

        if (username.isEmpty()) { // disable the button when login as guest
            saveBtn.setEnabled(false);
            loadBtn.setEnabled(false);
        }

        this.stepLabel = createLabel("Step: 0", new Font("Ink Free", Font.BOLD, 18), new Point(20, 5), 180, 50);
        this.scoreLabel = createLabel("Score: 0", new Font("Ink Free", Font.BOLD, 18), new Point(160, 5), 180, 50);
        this.highestScoreLabel = createLabel("HighScore: 0", new Font("Ink Free", Font.BOLD, 18), new Point(310, 5), 180, 50);
        this.timeLabel = createLabel("Time: 0:00", new Font("Ink Free", Font.BOLD, 18), new Point(500, 5), 180, 50);
        this.timeLabel2 = createLabel("Limit Time: 0:00", new Font("Ink Free", Font.BOLD, 18), new Point(500, 40), 180, 50);

//        this.timeLabel = new JLabel();
















        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);
        gamePanel.setHighestsScoreLabel(highestScoreLabel);


        runTime();


        //automatic load
        if(!username.isEmpty()){
            System.out.println("from gameframe"+ username + size);
            controller.autoLoadGame(username, username, size);// the first username is filename, the second one is folder name, automatic load
            resetTime();
            this.minutes2 = controller.getMinutes();
            this.seconds2 = controller.getSeconds();
            runTime();
        }



        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            resetTime();
            runTime();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.saveBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(elapsedTime+"elasped time from gameframe");
            timer.stop();
            controller.saveGame(string, elapsedTime);
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            controller.loadGame(string, username, size);

            resetTime();
            this.minutes2 = controller.getMinutes();
            this.seconds2 = controller.getSeconds();
            runTime();
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.redoBtn.addActionListener(e -> {
            controller.redoGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.exitBtn.addActionListener(e -> {
            if(!username.isEmpty()){ //if he is guest
                timer.stop();
                System.out.println(elapsedTime+"elasped time from gameframe");

                controller.exitGame(username, elapsedTime);
                System.out.println("guest won't run");
            }
            gamePanel.requestFocusInWindow();//enable key listener
            System.exit(0);
        });

        this.backBtn.addActionListener(e->{
            OptionFrame optionFrame = new OptionFrame(username);
            optionFrame.setVisible(true);
            this.dispose();
        });






        this.setBackground();


        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    } // end of constructor


    private void stop(){
        timer.stop();
        new GameoverFrame(username);
    }
    private void setBackground() {

        String backgroundIconPath = imagePath + "snowImage.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (1200, 600, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 600);
        this.add(background);
    }

    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);

        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);

        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

    public void runTime(){
        long milliSeconds = (minutes2 * 60 * 1000) + (seconds2 * 1000);
        elapsedTime += milliSeconds;

        timer = new Timer(1000, e -> {


            elapsedTime += 1000;
            limitElapsedTime -= 1000;

            limitHours =  (limitElapsedTime / 3600000);
            limitMinutes =  ((limitElapsedTime/60000) % 60) ;
            limitSeconds =  ((limitElapsedTime/1000) %60) ;



            hours =  (elapsedTime / 3600000);
            minutes =  ((elapsedTime/60000) % 60) ;
            seconds =  ((elapsedTime/1000) %60) ;
//            minutes2 +=  ((elapsedTime/60000) % 60);
//            seconds2 +=  ((elapsedTime/1000) %60);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            seconds_string2 = String.format("%02d", limitSeconds);
            minutes_string2 = String.format("%02d", limitMinutes);
            hours_string2 = String.format("%02d", limitHours);

            timeLabel.setText("Time: "+ hours_string + ":" + minutes_string + ":" + seconds_string);

            if(Difficulty == 3){
                timeLabel2.setText("Time: "+ hours_string2 + ":" + minutes_string2 + ":" + seconds_string2);

            }



            if(Difficulty == 3){
                if(limitHours == 0 && limitMinutes ==0) {
                    if(limitSeconds == 0){
                        System.out.println("wai");
                        this.stop();
                    }
                }
            }


        });



        timer.start();
    }

    public void resetTime(){
        timer.stop();
         elapsedTime = 0;

         limitElapsedTime = 60000;
         limitElapsedTime *= optionMinutes;
         seconds = 0;
         minutes = 0;
         hours = 0;

         minutes2 = 0;
         seconds2 = 0;

         limitHours = 0;
         limitMinutes = 0;
         limitSeconds = 0;




         seconds_string = String.format("%02d", seconds);
         minutes_string = String.format("%02d", minutes);
         hours_string = String.format("%02d", hours);

         seconds_string2 = String.format("%02d", limitSeconds);
         minutes_string2 = String.format("%02d", limitMinutes);
         hours_string2 = String.format("%02d", limitHours);
    }
}

