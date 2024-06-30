package menu;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    Login login;
    OptionFrame optionFrame;
    public String imagePath = "src/image/";
    private boolean musicStarted = false;

    GamePanel gamePanel = new GamePanel();
    final private int BUTTON_WIDTH = 120;
    final private int BUTTON_HEIGHT = 40;

    public MenuFrame() {

        super("2048");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        titleLabel("2048", new Font("Ink Free", Font.BOLD, 60), new Point(320, 50), 300, 100, Color.WHITE);

        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(false);
        playButton();
        loginButton();
//        createTurnMusicOn_OffButton();
        createMusicButton();
        exitButton();

        this.setLocationRelativeTo(null);
        this.setBackground();

    }

    private void setBackground() {

        String backgroundIconPath = imagePath + "MenuBackground.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (1200, 600, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 600);
        this.add(background);
    }

    public void playButton() {
        JButton button = new JButton("PLAY");

        button.setFont(new Font(null, Font.BOLD, 15));
        button.setForeground(Color.black);
        button.setBounds(340, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setFocusable(false);

        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);

        button.addActionListener(e -> {
            optionFrame = new OptionFrame("");
            optionFrame.setVisible(true);

            this.setVisible(false);
            System.out.println("Load Option Frame");
            button.setEnabled(false); // can click only once
        });


        button.setBackground(Color.white);
        this.add(button);

    }

    public void loginButton() {
        JButton button = new JButton("LOGIN");
        button.setFont(new Font(null, Font.BOLD, 15));
        button.setForeground(Color.black);
        button.setBounds(340, 230, BUTTON_WIDTH, BUTTON_HEIGHT);

        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);


        button.setFocusable(false);
        button.addActionListener(e -> {
            this.dispose();
            login = new Login();
            login.setVisible(true);
            System.out.println("Load Login Frame");
        });
        button.setBackground(Color.white);

        this.add(button);
    }

    private void createMusicButton() {
        JButton button = new JButton("MUSIC OFF");
        button.setFont(new Font(null, Font.BOLD, 15));
        button.setForeground(Color.black);
        button.setBounds(340, 310, BUTTON_WIDTH, BUTTON_HEIGHT);

        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);


        button.addActionListener(e -> {
//            gamePanel.playMusic(0);

            if (musicStarted == false) {
                musicStarted = true;
                button.setText("MUSIC ON");
                gamePanel.playMusic(2);
            } else {
                musicStarted = false;
                button.setText("MUSIC OFF");
                gamePanel.stopMusic();
            }
        });

        this.add(button);
    }

    private void exitButton() {
        JButton button = new JButton("EXIT");
        button.setFont(new Font(null, Font.BOLD, 15));
        button.setForeground(Color.black);
        button.setBounds(340, 390, BUTTON_WIDTH, BUTTON_HEIGHT);


        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);


        button.setFocusable(false);
        button.addActionListener(e -> {
            System.out.println("Exit the game");
            System.exit(0);

        });
        button.setBackground(Color.red);
        this.add(button);
    }

    private void titleLabel(String name, Font font, Point location, int width, int height, Color color) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        label.setForeground(color);
        this.add(label);
    }

    public void setMusicStarted(boolean musicStarted) {
        this.musicStarted = musicStarted;
    }

    public boolean getMusicStarted() {
        return this.musicStarted;
    }


}