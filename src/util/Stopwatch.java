package util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch {
    JLabel timeLabel = new JLabel();
    int elapsedTime = 120000;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime -= 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) %60;
            String seconds_string = String.format("%02d", seconds);
            String minutes_string = String.format("%02d", minutes);
            String hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

        }
    });

    public Stopwatch(){ // Constructor

    }

    public void startTime(){
        timer.start();
        elapsedTime = 0;
        seconds =0;
        minutes = 0;
        hours =0;
        String seconds_string = String.format("%02d", seconds);
        String minutes_string = String.format("%02d", minutes);
        String hours_string = String.format("%02d", hours);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

    }

    public void stopTime(){
        timer.stop();
    }

    public void resetTime(){
        timer.stop();
        elapsedTime = 0;
        seconds =0;
        minutes = 0;
        hours =0;
        String seconds_string = String.format("%02d", seconds);
        String minutes_string = String.format("%02d", minutes);
        String hours_string = String.format("%02d", hours);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

    }
}
