package it.gioco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jumping2 extends JPanel implements ActionListener, KeyListener {

	private Timer timer;
	private int x, y;
	double ySpeed;
	private int bgX, bgY, bgSpeed;
	private int score;
	private int life;
	private Image bgImage, 	obstacleImage4,bgImage2,bgImage3,foxImage, obstacleImage, obstacleImage2, obstacleImage3;

	private int obstacleX, obstacleY, obstacleSpeed,platformSpeed, obstacleX2, obstacleX3, obstacleY3, obstacleY2,obstacleSpeed2, obstacleSpeed3;
	private boolean obstaclePassed, obstaclePassed2, obstaclePassed3, gameOver;

	private Image platformImage,obstacleCity;
	private Platform[] platforms;
	private Obstacle2 obstacle2;
	private Obstacle3 obstacle3;
	private boolean isOnPlatform,cambia;

	
	public Jumping2(String percorso_skin,String percorso_livello,String percorso_ostacolo) {

		
		x = 50;
		life = 100;
		y = 445;
		ySpeed = 0;
		bgX = 0;
		bgY = 0;
		bgSpeed = 10;
		score = 0;
		obstacleX = 2000;
		obstacleY = 520;
		obstacleX2 = 2100;
		obstacleY2 = 520;
		obstacleSpeed = 10;
		gameOver = false;
		cambia=false;
		obstaclePassed = true;
		obstaclePassed2 = true;
		obstaclePassed3 = true;
		platformSpeed = bgSpeed;
		obstacleSpeed2 = bgSpeed;



		this.bgImage = new ImageIcon(percorso_livello).getImage();
		bgImage2= new ImageIcon("C:\\Users\\Salvatore\\eclipse-workspace\\Gioco\\Citta (1).jpg").getImage();
		
		this.foxImage = new ImageIcon(percorso_skin).getImage();
		obstacleImage = new ImageIcon(percorso_ostacolo).getImage();
		obstacleImage2 = new ImageIcon("C:\\Users\\Salvatore\\eclipse-workspace\\Gioco\\fragolaLife.png").getImage();
		obstacleImage3 = new ImageIcon("steroids.png").getImage();
		obstacleCity= new ImageIcon("panca (3).png").getImage();
		platformImage = new ImageIcon("platform.png").getImage();

		platforms = new Platform[2];
		platforms[0] = new Platform(getWidth() + (int)(Math.random() * 400), (int)(Math.random() * 200) + 1000, 270, 34);
		platforms[1] = new Platform(getWidth() + (int)(Math.random() * 400), (int)(Math.random() * 200) + 1000, 270, 34);
		isOnPlatform = false;


		setPreferredSize(new Dimension(1180, 826));
		setOpaque(false);

		addKeyListener(this);
		setFocusable(true);

		timer = new Timer(10, this);
		timer.start();

	}


		// Metodo Per Disegnare Le Immagini
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegna lo sfondo scorrevole
		g.drawImage(bgImage, bgX, bgY, null);
		g.drawImage(bgImage, bgX + bgImage.getWidth(null), bgY, null);

		// Disegna la volpe
		g.drawImage(foxImage, x, y, null);

		// Disegna l'ostacolo se non è stato superato
		if (!obstaclePassed || obstaclePassed == true) {
			g.drawImage(obstacleImage, obstacleX, obstacleY, null);
		}



		// Disegna il punteggio
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Score: " + score, 50, 50);

		// Disegna le piattaforme
		g.setColor(Color.GRAY);
		for (Platform platform : platforms) {
			g.drawImage(platformImage, platform.x, platform.y, platform.width, platform.height, null);
		}

	

		// Disegna Il power UP
		if(!obstaclePassed2) { 
			g.drawImage(obstacleImage2, obstacleX2, obstacleY2, null);
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Life: " + life, 90, 20);

		int barWidth = 200;
		int barHeight = 20;
		int barX = 50;
		int barY = 80;
		int healthWidth = (int) ((barWidth /100) * ((int) life / 1));


		// Barra Della vita
		g.setColor(Color.BLACK);
		g.drawRect(barX, barY, barWidth, barHeight);
		g.setColor(Color.GREEN);
		g.fillRect(barX, barY, healthWidth, barHeight);

		// Gestisce il cambio di sfondo e ostacoli raggiunto un determinato score
		if (score > 10 && score <= 20 && !cambia) {
			bgImage = bgImage2;
			obstacleImage = obstacleCity;
			cambia = true;
			g.drawImage(obstacleImage, obstacleX, obstacleY, null);
			g.drawImage(bgImage, bgX, bgY, null);
			g.drawImage(bgImage, bgX + bgImage.getWidth(null), bgY, null);
		}

		
	



	}

	
	
	// Gestione Colisioni e Scorrimento Sfondo
	public void actionPerformed(ActionEvent e) {



		if(score>= 20) {
			bgSpeed = 12;
			obstacleSpeed = 12;
			obstacleSpeed2 = 12;
			obstacleSpeed3 = 12;
		}



		// Scorre lo sfondo e le piattaforme
		bgX -= bgSpeed;

		for (Platform platform : platforms) {
			platform.x -= platformSpeed;
			if (platform.x + platform.width < 0) {
				platform.x = getWidth() + (int) (Math.random() * 400);
				platform.y = (int) (Math.random() * 150) + 340;
			}
		}


		
		if (obstacleX2 < -obstacleImage2.getWidth(null)) {
			obstacleX2 = 2100;
			obstaclePassed2 = false;
		}

		if (obstacleY2 < -obstacleImage2.getHeight(null)) {
			obstacleY2 = 550;
			obstaclePassed2 = false;
		}
		
		if (bgX <= -bgImage.getWidth(null)) {
			bgX = 0;
		}

		// Gestisce il salto della volpe
		y += ySpeed;
		ySpeed += 0.7;
		if (y > 445) {
			y = 445;
			ySpeed = 0.7;
		}

		// Gestisce l'ostacolo
		obstacleX -= obstacleSpeed;
		if (obstacleX < -obstacleImage.getWidth(null)) {
			obstacleX = 1100;
			obstaclePassed = false;
		}

		if (obstacleY < -obstacleImage.getHeight(null)) {
			obstacleY = 520;
			obstaclePassed = false;
		}
		// Gestisce l'ostacolo
		obstacleX2 -= obstacleSpeed2;
		if (obstacleX2 < -obstacleImage2.getWidth(null)) {
			obstacleX2 = 2100;
			obstaclePassed2 = false;
		}

		if (obstacleY2 < -obstacleImage2.getHeight(null)) {
			obstacleY2 = 550;
			obstaclePassed2 = false;
		}
		// Gestisce l'ostacolo
		obstacleX3 -= obstacleSpeed3;
		if (obstacleX3 < -obstacleImage3.getWidth(null)) {
			obstacleX3 = 1700;
			obstaclePassed3 = false;
		}

		if (obstacleY3 < -obstacleImage3.getHeight(null)) {
			obstacleY3 = 370;
			obstaclePassed3 = false;
		}

		int dimensioneOstacolo = obstacleImage.getWidth(null) - 65;
		int dimensioneOstacolo2 = obstacleImage2.getWidth(null) - 30;
		int dimensioneOstacolo3 = obstacleImage3.getWidth(null) - 30;
		int altezzaOstacolo = obstacleImage.getHeight(null) - 60;
		int dimensioneFox = foxImage.getWidth(null) - 64;
		int oby = obstacleY;
		int obx = obstacleX;
		int foximgheight = foxImage.getHeight(null) - 12;
		int foxy = y;

		// Gestisce la collisione tra la volpe e l'ostacolo
		if (!obstaclePassed && x + dimensioneFox > obstacleX && x < obstacleX + dimensioneOstacolo
				&& y + foximgheight > obstacleY) {
			life = life-30;
			obstaclePassed = true;

		}
		// Gestisce la collisione tra la volpe e l'ostacolo
		if (!obstaclePassed2 && x + dimensioneFox > obstacleX2 && x < obstacleX2 + dimensioneOstacolo2
				&& y + foximgheight > obstacleY2) {
			if(life <= 150) {
				life = life+5 ;}
			obstaclePassed2 = true;


		}
		// Gestisce la collisione tra la volpe e l'ostacolo
		if (!obstaclePassed3 && x + dimensioneFox > obstacleX3 && x < obstacleX3 + dimensioneOstacolo3
				&& y + foximgheight > obstacleY3) {
			score = score + 1;
			obstaclePassed3 = true;

		}

		// Gestisce il superamento dell'ostacolo
		if (!obstaclePassed && x > obstacleX + obstacleImage.getWidth(null)) {
			obstaclePassed = true;
			score++;
		}

		// Controlla se la volpe è sulla piattaforma
		isOnPlatform = false;
		for (Platform platform : platforms) {
			if (x + foxImage.getWidth(null) > platform.x && x < platform.x + platform.width
					&& y + foxImage.getHeight(null) >= platform.y && y + foxImage.getHeight(null) <= platform.y + 20) {
				isOnPlatform = true;
				y = platform.y - foxImage.getHeight(null);
				ySpeed = 0;
				break;
			}
		}
		if (life <=0) {

			int record = insermentoRecord(score);


			JOptionPane.showMessageDialog(this, "Game Over!\nScore: " + score);
			gameOver = true;
			timer.stop();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			new GameOver();		


		}



		// If the fox is not on a platform, apply gravity
		if (!isOnPlatform) {
			y += ySpeed;
			ySpeed += 0.3;  
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && y == 445) {
			ySpeed = -17;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE && isOnPlatform ) {
			ySpeed = -17;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {}


	public int insermentoRecord(int record) {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("database.propreties");
			props.load(in);
			in.close();

			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			Connection conn = DriverManager.getConnection(url,username,password);

			String sql = "insert into record(score) values (?)";
			System.out.println("Sono morto " + record);
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, record);
			stmt.execute();


			stmt.close();
			conn.close();

		}catch( SQLException ex) {

		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return record;
	}


}





