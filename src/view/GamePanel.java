package view;

import model.GridNumber;
import util.Sound;
import util.Stopwatch;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends ListenerPanel {
    private  int COUNT = 4;
    private GridComponent[][] grids;

    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel highestScoreLabel;



    //    private int steps;
    private final int GRID_SIZE;

    private  String username;
    private int grid_size;
    private int winScore;


    Sound sound = new Sound();
    Stopwatch stopwatch = new Stopwatch();

    private int Difficulty = 1;


    public GamePanel(int size, String username, int grid_size, int winScore, int Difficulty) {
        this.Difficulty = Difficulty;
        System.out.println("Difficuly from Panel"+ this.Difficulty);
        this.winScore = winScore;
        this.username = username;
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(new Color(187, 173, 160));
        this.setSize(size, size);

        this.COUNT = grid_size;

        this.GRID_SIZE = size / COUNT;    //
        this.grids = new GridComponent[COUNT][COUNT];  // for gui view
        this.model = new GridNumber(COUNT, COUNT, username, grid_size, winScore, Difficulty );     // for model
        initialGame();


//        //automatic load
//        if(!username.isEmpty()){
//            System.out.println("from gameframe"+ username + size);
//            controller.loadGame(username, username, size);// the first username is filename, the second one is folder name, automatic load
//        }
    }

    public GamePanel() {
        this.GRID_SIZE = 0;
    }



    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        model.setSteps(0);
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }        repaint();
    }

//    public boolean canCombine(){
//
//    }

    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        this.model.moveRight();
        this.afterMove();
        this.updateGridsNumber();
        playSoundEffect(1);
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        this.model.moveLeft();
        this.afterMove();
        this.updateGridsNumber();
        playSoundEffect(1);
    }


    public void doMoveUp() {
        System.out.println("Click VK_UP");
        this.model.moveUp();
        this.afterMove();
        this.updateGridsNumber();
        playSoundEffect(1);
    }
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        this.model.moveDown();
        this.afterMove();
        this.updateGridsNumber();
        playSoundEffect(1);
    }

    public void afterMove() {
//        this.steps++;
        System.out.println(model.getScore() + "getScore");
        System.out.println(model.getSteps() + "getSteps");
        this.stepLabel.setText(String.format("Step: %d", model.getSteps()));
        this.scoreLabel.setText(String.format("Score: %d", model.getScore()));
        this.highestScoreLabel.setText(String.format("HighScore: %d", model.getHighestScore()));

        if (model.isGameOver()){

        }
    }


    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void setHighestsScoreLabel(JLabel highestScoreLabel) {
        this.highestScoreLabel = highestScoreLabel;
    }






    // SOUND IMPLEMENTATION
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }



}
