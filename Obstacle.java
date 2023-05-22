package it.gioco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
	private int x, y, width, height;
	private Image obstacleImage;

	public Obstacle(int x, int y, int width, int height, Image obstacleImage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.obstacleImage = obstacleImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Image getObstacleImage() {
		return obstacleImage;
	}

	public void move(int speed) {
		x -= speed;
	}
}