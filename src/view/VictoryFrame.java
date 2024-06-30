package view;

import javax.swing.*;
import java.awt.*;

public class VictoryFrame extends JFrame {
    public String imagePath = "src/image/";

    private String username;
    public VictoryFrame(String username){
        this.username = username;


        createVitoryText();
        createNewGameButton();
//        createCheckLeaderBoard();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setBackground();
    }

    private void createVitoryText(){
        JLabel vitoryText = new JLabel("Victory");
        vitoryText.setBounds(250,30,350,130);
        vitoryText.setFocusable(false);// these 3 lines make button transparent
        vitoryText.setBackground(Color.white);
        vitoryText.setOpaque(false);
        vitoryText.setFont(new Font("Ink Free", Font.BOLD, 90));
        this.add(vitoryText);
    }

    private void createNewGameButton(){
        JButton newGameButton = new JButton("BACK");
        newGameButton.setBounds(320, 220, 150,100);
        newGameButton.setFocusable(false);// these 3 lines make button transparent
        newGameButton.setBackground(Color.white);
        newGameButton.setOpaque(false);
        newGameButton.setFocusable(false);
        newGameButton.setFont(new Font("Ink Free", Font.BOLD, 20));

        newGameButton.addActionListener(e ->{
//            OptionFrame optionFrame = new OptionFrame(username);
//            optionFrame.setVisible(true);
//            this.setVisible(false);
            this.dispose();
        });
        this.add(newGameButton);
    }

//    private void createCheckLeaderBoard(){
//        JButton newGameButton = new JButton("Leaderboard");
//        newGameButton.setBounds(120, 350, 150,100);
//        newGameButton.setFocusable(false);// these 3 lines make button transparent
//        newGameButton.setBackground(Color.white);
//
//        newGameButton.setOpaque(false);
//        newGameButton.setFocusable(false);
//        newGameButton.setFont(new Font("Ink Free", Font.BOLD, 20));
//        this.add(newGameButton);
//    }
//    private void createCheck(){
//
//
//    }
    private void setBackground() {

        String backgroundIconPath = imagePath +"victory.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (800, 800, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800,600);
        this.add(background);
    }
}
