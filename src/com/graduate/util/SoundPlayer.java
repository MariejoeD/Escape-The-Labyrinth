package com.graduate.util;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    private static Clip clip;
    private static boolean soundOn = true; // Global sound state

    public static void playSound(String soundFile) {
        if (!soundOn) return; // Check global sound state before playing

        try {
            File soundPath = new File(soundFile);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Sound file not found: " + soundFile);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void loopSound(String soundFile) {
        if (!soundOn) return; // Check global sound state before looping

        try {
            File soundPath = new File(soundFile);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("Sound file not found: " + soundFile);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void restartSound() {
        if (soundOn && clip != null) {
            clip.start();
        }
    }

    public static void setSoundOn(boolean soundOn) {
        SoundPlayer.soundOn = soundOn;
        if (soundOn) {
            restartSound();
        } else {
            stopSound();
        }
    }

    public static boolean isSoundOn() {
        return soundOn;
    }
}
