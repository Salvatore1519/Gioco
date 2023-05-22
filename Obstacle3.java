package it.gioco;

import java.awt.Image;

public class Obstacle3 {
	private int x3, y3, width3, height3;
    private Image obstacleImage3;

    public Obstacle3(int x3, int y3, int width3, int height3, Image obstacleImage3) {
        this.x3 = x3;
        this.y3 = y3;
        this.width3 = width3;
        this.height3 = height3;
        this.obstacleImage3 = obstacleImage3;
    }

    public int getX() {
        return x3;
    }

    public int getY() {
        return y3;
    }

    public int getWidth() {
        return width3;
    }

    public int getHeight() {
        return height3;
    }

    public Image getObstacleImage() {
        return obstacleImage3;
    }

    public void move(int speed3) {
        x3 -= speed3;
    }
}