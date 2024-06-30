package controller;

import model.GridNumber;
import util.Sound;
import view.GamePanel;



/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class GameController {
    private GamePanel view;
    private GridNumber model;
    Sound sound = new Sound();


    public GameController(GamePanel view, GridNumber model) {
        this.view = view;
        this.model = model;

    }
    public void restartGame() {
        model.restart();
        view.updateGridsNumber();
        view.afterMove();
        System.out.println("Do restart game here");
    }

    public void redoGame() {
        model.redo();
        view.updateGridsNumber();
        System.out.println("Do redo game here");
        view.afterMove();
    }

    public void saveGame(String filePath,  long elaspedTime ){
        model.save(filePath, elaspedTime);
    }

    public void loadGame(String filePath, String username, int size){
        model.load(filePath, username, size);
        view.updateGridsNumber();;
        view.afterMove();
    }

    public void autoLoadGame(String filePath, String username, int size){
        model.autoLoad(filePath, username, size);
        view.updateGridsNumber();;
        view.afterMove();
    }

    public long getMinutes(){
        return model.getMinutes();
    }

    public long getSeconds(){
        return model.getSeconds();
    }

    public void exitGame(String username, long elaspedTime){
        model.save(username, elaspedTime);
    }





    //todo: add other methods such as loadGame, saveGame...

}
