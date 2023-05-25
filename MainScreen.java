package it.gioco;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.util.*;

import java.applet.*;

public class MainScreen extends JFrame{

	//DICHIARIAMO I COMPONENTI
	private JLabel titleLabel, campo1;
	private JButton newGameButton, visualCreditsButton,visualScoreButton ,buyCreditsButton,exitButton;
	private Timer timer;
	private ImageIcon imageicon;
	private JPanel panel;

	private int x, y;
	private int z=50;
	private int e=50;


	public MainScreen() {

		//INIZIALIZZAZIONE DEI COMPONENTI
		titleLabel = new JLabel("SUPER FOX RUN");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		Font font1	= new Font("Comic Sans MS", Font.PLAIN, 50);
		titleLabel.setFont(font1);
		titleLabel.setForeground(Color.RED);
		Font labelFont = titleLabel.getFont();
		int labelFontSize = labelFont.getSize() + 45;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		titleLabel.setFont(newFont);
		Font newFont2 = new Font("Comic Sans MS", Font.BOLD, 50);
		titleLabel.setFont(newFont2);

		visualScoreButton = new JButton("Visualizza Record Score");
		Font buttonFonte = new Font("Comic Sans MS", Font.BOLD, 50);
		visualScoreButton.setFont(buttonFonte);
		visualScoreButton.setOpaque(false);
		visualScoreButton.setContentAreaFilled(false);
		visualScoreButton.setBorderPainted(false);
		visualScoreButton.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente


		visualScoreButton.setVisible(true);

		
		
		visualScoreButton.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				visualScoreButton.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				visualScoreButton.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				visualScoreButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				visualScoreButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				visualScoreButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				visualScoreButton.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				visualScoreButton.setFont(buttonFont2);
			}
		}
				);




		newGameButton = new JButton("NEW GAME");
		Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 50);
		newGameButton.setFont(buttonFont);
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		newGameButton.setVisible(true);
		newGameButton.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				newGameButton.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				newGameButton.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				newGameButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				newGameButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				newGameButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				newGameButton.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				newGameButton.setFont(buttonFont2);
			}
		}
				);


		
		exitButton = new JButton("EXIT");
		Font buttonFont3 = new Font("Comic Sans MS", Font.BOLD, 50);
		exitButton.setFont(buttonFont3);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		exitButton.setVisible(true);
		exitButton.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				exitButton.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				exitButton.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				exitButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				exitButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				exitButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				exitButton.setFont(buttonFont2);
			}
		}
				);

		campo1 = new JLabel("Copyright by Salvatore Formicola Â©");


		//AGGIUNTA DEI COMPONENTI
		panel = new JPanel(new GridLayout(7,1)) {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Disegno dell'immagine nella nuova posizione
				g.drawImage(imageicon.getImage(), -x, y, null);
				g.drawImage(imageicon.getImage(), getWidth() - x, y, null);
			}


			@Override
			public Dimension getPreferredSize() {
				return new Dimension(imageicon.getIconWidth(), imageicon.getIconHeight());
			}
		};

		// Gestione Scorrimento Sfondo
		ImageIcon icona = new ImageIcon("C:\\Users\\Salvatore\\eclipse-workspace\\Gioco\\SfondoVolpe.jpg");
		setIconImage(icona.getImage());
		setTitle("FoxGame");
		setSize(1180, 820);

		this.imageicon = new ImageIcon("C:\\Users\\Salvatore\\eclipse-workspace\\Gioco\\SfondoVolpe.jpg");
		this.x = 0;
		this.y = 0;

		timer = new Timer(10, e -> {
			x++;
			if (x > imageicon.getIconWidth()) {
				x = 0;
			}
			panel.repaint();
		});
		
		timer.start();

		panel.add(titleLabel);
		panel.add(newGameButton);
		
		panel.add(visualScoreButton);
		panel.add(exitButton);
		panel.add(campo1);
		add(panel);

		//SETTAGGIO DIMENSIONI DELLA FINESTRA
		setTitle("FOX GAMES");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		//BOTTONE EXIT
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
				);

		// Visualizza Record
		visualScoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();


				new VisualRecord();

			}
		});

	
		// Nuova Partita
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SelectLevel();
			}
		}
				);

	}

	public static void main(String[] args) {
		new MainScreen();
	}

}