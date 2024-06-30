package view;

import javax.swing.*;

public class FakeMusic extends JFrame {
    GamePanel gamePanel = new GamePanel();
    public FakeMusic(){
        this.setVisible(false);
        gamePanel.playMusic(0);
        gamePanel.setVisible(false);

    }

    public void getStopMusic(){
        gamePanel.stopMusic();
    }

    public void getPlayMusic(int i){
        gamePanel.playMusic(i);
    }
}
