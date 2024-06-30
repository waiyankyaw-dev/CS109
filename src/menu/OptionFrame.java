package menu;

import view.GameFrame;
import view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class OptionFrame extends JFrame  {
    JLabel storeChooseMaxScore;
    JLabel t1,t2,t3,t4, difficultyText, scoreToWinText;
    JPanel storePanel, storeText, storeRadio;
    JButton button4by4;
    JButton button5by5;
    JButton button6by6;
    JButton button8by8;
    JRadioButton mode1, mode2, mode3;
    JButton  backToMenuButton;
    JPanel storeDifficutlyText, storeScoreToWinText;
    GamePanel gamePanel;

    public String imagePath = "src/image/";
    private ButtonGroup group;

    private  String username = "";
    private  int size = 4;
    private  int winscore = 2048;

    private int minutes = 1;

    private int Difficulity = 1;


//    OptionFrame(){
//        this.setVisible(true);
//    }

    public OptionFrame(String username){
        this.username = username;

        storePanel = new JPanel(new GridLayout(1, 4, 20,5));
        storePanel.setBackground(new Color(0,0,0,0)); // set background to transparency
        storeText = new JPanel(new GridLayout(1, 4, 20,5));
        storeText.setBackground(new Color(0,0,0,0));
        storeDifficutlyText = new JPanel(new GridLayout(1, 1, 20,5));
        storeDifficutlyText.setBackground(new Color(0,0,0,0));
        storeScoreToWinText = new JPanel(new GridLayout(1, 1, 20,5));
        storeScoreToWinText.setBackground(new Color(0,0,0,0));

        storePanel.setBounds(100,200, 600, 150);
        storeText.setBounds(100,300, 600, 150);
        storeDifficutlyText.setBounds(100,400, 400, 150);
        storeScoreToWinText.setBounds(650,30,200,100);


        button4by4 = new JButton();
        t1 = new JLabel("4 x 4");
        String grid4by4 = imagePath + "4by4gridImage.png";
        ImageIcon grid = new ImageIcon(new ImageIcon(grid4by4).getImage().getScaledInstance
                (160,175, Image.SCALE_DEFAULT));
        button4by4.setIcon(grid);

//        ImageIcon icon = new ImageIcon(imagePath+"4by4gridImage.jpeg");
//        panel4by4.setIcon(icon);

        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Ink Free", Font.BOLD, 25));
        t1.setBackground(Color.BLACK);
//        panel4by4.setBackground(new Color(0x69E8B6));
        storePanel.add(button4by4);
        storeText.add(t1);


        button5by5 = new JButton();
        String gridImage5by5 = imagePath + "5by5gridImage.png";
        ImageIcon grid5by5 = new ImageIcon(new ImageIcon(gridImage5by5).getImage().getScaledInstance
                (140,150, Image.SCALE_DEFAULT));
        button5by5.setIcon(grid5by5);
        t2 = new JLabel("5 x 5");
        t2.setForeground(Color.WHITE);
        t2.setFont(new Font("Ink Free", Font.BOLD, 25));
        t2.setBackground(Color.BLACK);
        button5by5.setBackground(new Color(0x26C1E1));
        storePanel.add(button5by5);
        storeText.add(t2);


        button6by6 = new JButton();
        String gridImage6by6 = imagePath + "6by6gridImage.png";
        ImageIcon grid6by6 = new ImageIcon(new ImageIcon(gridImage6by6).getImage().getScaledInstance
                (140,140, Image.SCALE_DEFAULT));
        button6by6.setIcon(grid6by6);
        t3 = new JLabel("6 x 6");
        t3.setForeground(Color.WHITE);
        t3.setFont(new Font("Ink Free", Font.BOLD, 25));
        t3.setBackground(Color.BLACK);
        button6by6.setBackground(new Color(0x1266D3));
        storePanel.add(button6by6);
        storeText.add(t3);

        button8by8 = new JButton();
        String gridImage8by8 = imagePath + "8by8gridImage.png";
        ImageIcon grid8by8 = new ImageIcon(new ImageIcon(gridImage8by8).getImage().getScaledInstance
                (135, 150, Image.SCALE_DEFAULT));
        button8by8.setIcon(grid8by8);
        t4 = new JLabel("8 x 8");
        t4.setForeground(Color.WHITE);
        t4.setFont(new Font("Ink Free", Font.BOLD, 25));
        t4.setBackground(Color.BLACK);
        button8by8.setBackground(new Color(0x7413FF));
        storePanel.add(button8by8);
        storeText.add(t4);



        difficultyText = new JLabel("Please select 1 mode");
        difficultyText.setFont(new Font("Ink Free", Font.BOLD, 20));
        difficultyText.setForeground(Color.WHITE);
        storeDifficutlyText.add(difficultyText);

        scoreToWinText = new JLabel("Select score to win");
        scoreToWinText.setFont(new Font("Ink Free", Font.BOLD, 15));
        scoreToWinText.setForeground(Color.WHITE);
        storeScoreToWinText.add(scoreToWinText);


        button4by4.addActionListener(e-> {
            System.out.println("Load Game Frame");
            GameFrame gameFrame = new GameFrame(700,500, username, 4, winscore , Difficulity, minutes);
            gameFrame.setVisible(true);
//            this.setVisible(false);
            this.dispose();
        });

        button5by5.addActionListener(e-> {
            System.out.println("Load Game Frame");
            GameFrame gameFrame = new GameFrame(700,500, username, 5, winscore, Difficulity, minutes );
            gameFrame.setVisible(true);
//            this.setVisible(false);
            this.dispose();
        });

        button6by6.addActionListener(e-> {
            System.out.println("Load Game Frame");
            GameFrame gameFrame = new GameFrame(700,500, username, 6, winscore, Difficulity, minutes);
            gameFrame.setVisible(true);
//            this.setVisible(false);
            this.dispose();
        });


        button8by8.addActionListener(e-> {
            System.out.println("Load Game Frame");
            GameFrame gameFrame = new GameFrame(700,500, username, 8, winscore, Difficulity, minutes );
            gameFrame.setVisible(true);
//            this.setVisible(false);
            this.dispose();
        });


        group = new ButtonGroup();

        this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        this.createMode1();
        this.createMode2();
        this.createMode3();
        this.createBackToMenuButton();
        this.createStartButton();
        this.createSelectMaxScoreBox();
        this.createTimeMin();

        this.add(storePanel);
        this.add(storeText);
        this.add(storeDifficutlyText);
        this.add(storeScoreToWinText);
        this.setLocationRelativeTo(null);
        this.setBackground();

    }
    public void createStartButton(){
        button4by4 = new JButton("Start New Game");
        button4by4.setBounds(200,50,400,80);
        button4by4.setFont(new Font("Ink Free" , Font.BOLD , 30));

        button4by4.setFocusable(false);// these 3 lines make button transparent
        button4by4.setBackground(Color.white);
        button4by4.setOpaque(false);

        button4by4.setContentAreaFilled(false);
//        startbutton.setBorderPainted(false);
        button4by4.addActionListener(e-> {
            System.out.println("Load Game Frame");
            GameFrame gameFrame = new GameFrame(700,500, username, 4, winscore, Difficulity, minutes );
            gameFrame.setVisible(true);
            this.setVisible(false);
        });
        this.add(button4by4);
    }
    public void createBackToMenuButton(){
        backToMenuButton = new JButton("BACK");
        backToMenuButton.setFocusable(false);// these 3 lines make button transparent
        backToMenuButton.setBackground(Color.white);
        backToMenuButton.setOpaque(false);
        backToMenuButton.setBounds(40,50,100,50);
        backToMenuButton.setFont(new Font("Ink Free" , Font.BOLD , 15));
        backToMenuButton.setFocusable(false);
        backToMenuButton.setBackground(Color.white);
        backToMenuButton.addActionListener(e ->{
            MenuFrame menuFrame = new MenuFrame();
            System.out.println("Load Menu Frame");
            menuFrame.setVisible(true);
            this.dispose();
        });
        this.add(backToMenuButton);
    }
    private void createMode1(){
        mode1 = new JRadioButton("BASE");
        mode1.setFocusable(false);// these 3 lines make button transparent
        mode1.setBackground(Color.RED);
        mode1.setOpaque(false);
        mode1.setFont(new Font("Ink Free", Font.BOLD, 15));
        mode1.setForeground(Color.WHITE);
        mode1.setFocusable(false);
        mode1.setBounds(100,500,150,30);
        mode1.setSelected(true);
        mode1.addActionListener(e->{
            Difficulity = 1;
        });
        group.add(mode1);
        this.add(mode1);

    };
    private void createMode2(){
        mode2 = new JRadioButton("BLOCKS");
        mode2.setFocusable(false);// these 3 lines make button transparent
        mode2.setBackground(Color.RED);
        mode2.setOpaque(false);
        mode2.setFont(new Font("Ink Free", Font.BOLD, 15));
        mode2.setForeground(Color.WHITE);
        mode2.setFocusable(false);
        mode2.setBounds(300,500,150,30);
        mode2.addActionListener(e->{
            Difficulity = 2;
            System.out.println(Difficulity);
        });
        group.add(mode2);
        this.add(mode2);

    };
    private void createMode3(){
        mode3 = new JRadioButton("TIME(minutes)");
        mode3.setFocusable(false);// these 3 lines make button transparent
        mode3.setBackground(Color.RED);
        mode3.setOpaque(false);
        mode3.setFont(new Font("Ink Free", Font.BOLD, 15));
        mode3.setForeground(Color.WHITE);
        mode3.setFocusable(false);
        mode3.setBounds(500,500,150,30);
        mode3.addActionListener(e->{
            Difficulity = 3;
        });
        group.add(mode3);
        this.add(mode3);

    };
    private void createSelectMaxScoreBox(){ // combo box
        Integer[] scores = {16,32, 64,256, 512, 1028, 2048, 4096};
        JComboBox comboBox = new JComboBox(scores);
        comboBox.setSelectedIndex(6);
        comboBox.setFont(new Font("Ink Free", Font.BOLD , 14));
        comboBox.setBounds(650,100,80,30);
        comboBox.setBackground(new Color(0xFFFFFF));
        comboBox.setFocusable(false);// these 3 lines make button transparent
        comboBox.setOpaque(true);
        comboBox.setLightWeightPopupEnabled(false);
        comboBox.setForeground(Color.BLACK);



        comboBox.addActionListener(e->{
            if (e.getSource() == comboBox){

                this.winscore = (int) comboBox.getSelectedItem();
                System.out.println(this.winscore + "fromoption");
                System.out.println("Selected " + comboBox.getSelectedItem());

                // set it to the max score
            }
        });
        this.add(comboBox);
    }

    private void createTimeMin(){ // combo box
        Integer[] scores = {1,3,5,10};
        JComboBox comboBox = new JComboBox(scores);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("Ink Free", Font.BOLD , 14));
        comboBox.setBounds(650,500,80,30);
        comboBox.setBackground(new Color(0xFFFFFF));
        comboBox.setFocusable(false);// these 3 lines make button transparent
        comboBox.setOpaque(true);
        comboBox.setLightWeightPopupEnabled(false);
        comboBox.setForeground(Color.BLACK);



        comboBox.addActionListener(e->{
            if (e.getSource() == comboBox){

                this.minutes = (int) comboBox.getSelectedItem();

                System.out.println(this.winscore + "fromoption");
                System.out.println("Selected " + comboBox.getSelectedItem());

                // set it to the max score
            }
        });
        this.add(comboBox);
    }
    private void setBackground() {

        String backgroundIconPath = imagePath +"optionFrame.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (800, 600, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 600);
        this.add(background);
    }


}



