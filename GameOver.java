package it.gioco;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.applet.*;

public class GameOver extends JFrame {

	private JButton menu, riprova;
	private JLabel GameOver;
	private JLabel campo, campo2, campo3;

	private JPanel panel;

	private Timer timer;
	private ImageIcon imageicon;

	private int x, y;
	public GameOver() {




		menu = new JButton("Menu");
		Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 50);
		menu.setFont(buttonFont);
		menu.setOpaque(false);
		menu.setContentAreaFilled(false);
		menu.setBorderPainted(false);
		menu.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		menu.setVisible(true);
		menu.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				menu.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				menu.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				menu.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				menu.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				menu.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				menu.setFont(buttonFont2);
			}
		}
				);


		riprova = new JButton("Riprova");
		riprova.setOpaque(false);
		riprova.setContentAreaFilled(false);
		riprova.setBorderPainted(false);
		riprova.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		riprova.setVisible(true);
		riprova.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				riprova.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				riprova.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				riprova.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				riprova.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				riprova.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				riprova.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				riprova.setFont(buttonFont2);

			}
		}
				);
		Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
		riprova.setFont(buttonFont2);

		GameOver = new JLabel("Game Over");
		GameOver.setForeground(Color.RED);
		GameOver.setHorizontalAlignment(SwingConstants.CENTER);
		GameOver.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = GameOver.getFont();
		int labelFontSize = labelFont.getSize() + 45;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		GameOver.setFont(newFont);
		Font newFont2 = new Font("Comic Sans MS", Font.BOLD, 70);
		GameOver.setFont(newFont2);

		campo = new JLabel();

		campo2 = new JLabel();

		campo3 = new JLabel();

		panel = new JPanel(new GridLayout(2,3)) {

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

		ImageIcon icona = new ImageIcon("SfondoVolpe.jpg");
		setIconImage(icona.getImage());
		setTitle("DeerRun");
		setSize(1180, 820);

		this.imageicon = new ImageIcon("SfondoVolpe.jpg");
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

		panel.add(campo);
		panel.add(GameOver);
		panel.add(campo2);
		panel.add(menu);
		panel.add(campo3);
		panel.add(riprova);

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainScreen();
				dispose();
			}
		});

		riprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String immagine_utilizzata="";
				String immagine_sfondo="";
				String immagine_ostacolo="";

				try {
					Properties props = new Properties();
					FileInputStream in = new FileInputStream("database.propreties");

					props.load(in);
					in.close();


					String url = props.getProperty("url");
					String username = props.getProperty("username");
					String password = props.getProperty("password");





					Connection    conn = DriverManager.getConnection(url,username,password);
					Statement stmt=conn.createStatement();
					ResultSet     rs=stmt.executeQuery("SELECT * FROM utilizzo WHERE id = (SELECT MAX(id) FROM utilizzo)");
					
					while(rs.next()) {
					 immagine_utilizzata = rs.getString("ultimo_percorso");
					 immagine_ostacolo = rs.getString("ultimo_ostacolo");
					 immagine_sfondo = rs.getString("ultimo_livello");
						
					}
					
				}catch( IOException ex) {
					ex.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Jumping2 game = new Jumping2(immagine_utilizzata,immagine_sfondo,immagine_ostacolo);
				JFrame frame = new JFrame("Jumping Fox Game");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setContentPane(game);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
			}
		});


		add(panel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


	}

}