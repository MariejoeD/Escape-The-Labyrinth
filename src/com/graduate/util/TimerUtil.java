package com.graduate.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerUtil {
    private Timer timer;
    private int timeRemaining;
    private JLabel timerLabel;
    private boolean isPaused = false;
    private ActionListener onTimeUp;

    public TimerUtil(int timeInSeconds, JLabel timerLabel, ActionListener onTimeUp) {
        this.timeRemaining = timeInSeconds;
        this.timerLabel = timerLabel;
        this.onTimeUp = onTimeUp;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    timeRemaining--;
                    updateTimerLabel();
                    if (timeRemaining <= 0) {
                        timer.stop();
                        onTimeUp.actionPerformed(null);
                    }
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void resume() {
        isPaused = false;
    }

    public void updateTimerLabel() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        timerLabel.setText(String.format("%d:%02d", minutes, seconds));
    }

    public void restart() {
        timer.stop();
        timer.start();
    }
}
