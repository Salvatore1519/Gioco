package it.gioco;

import java.awt.Image;

public class Obstacle2 {
	private int x2, y2, width2, height2;
    private Image obstacleImage2;

    public Obstacle2(int x2, int y2, int width2, int height2) {
        this.x2 = x2;
        this.y2 = y2;
        this.width2 = width2;
        this.height2 = height2;
        this.obstacleImage2 = obstacleImage2;
    }

    public int getX() {
        return x2;
    }

    public int getY() {
        return y2;
    }

    public int getWidth() {
        return width2;
    }

    public int getHeight() {
        return height2;
    }

    public Image getObstacleImage() {
        return obstacleImage2;
    }

    public void move(int speed2) {
        x2 -= speed2;
    }
}