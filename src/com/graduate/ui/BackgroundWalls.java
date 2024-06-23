package com.graduate.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundWalls extends JPanel {
    private ImageIcon wall;
    private int width;
    private int height;
    private int maxSize;
    private int size;

    public BackgroundWalls(ImageIcon backgroundImage, int width, int height, int maxSize, int size) {
        this.wall = backgroundImage;
        this.width = width;
        this.height = height;
        this.maxSize = maxSize;
        this.size = size;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Get the image from the ImageIcon
        Image img = wall.getImage();
    
        // Draw the image scaled
        g.drawImage(img,0,0,(maxSize / size),(maxSize / size),this);
    }
}
