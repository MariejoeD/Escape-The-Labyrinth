package com.graduate.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerUtil {

    private Timer timer;
    private int timeRemaining; // time in seconds
    private JLabel timerLabel;
    private Runnable onTimeUp;

    public TimerUtil(int timeInSeconds, JLabel timerLabel, Runnable onTimeUp) {
        this.timeRemaining = timeInSeconds;
        this.timerLabel = timerLabel;
        this.onTimeUp = onTimeUp;
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                updateTimerLabel();
                if (timeRemaining <= 0) {
                    timer.stop();
                    onTimeUp.run();
                }
            }
        });
    }

    private void updateTimerLabel() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset(int timeInSeconds) {
        this.timeRemaining = timeInSeconds;
        updateTimerLabel();
    }

}