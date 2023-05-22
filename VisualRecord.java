package it.gioco;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;


public class VisualRecord extends JFrame {

 int y,x;


     private Timer timer;
	private JButton visualizzaButton,indietroButton;
	private JLabel visualizzalbl;
	private JPanel panel;
	
	private ImageIcon imageicon;


	public VisualRecord() {
	imageicon= new ImageIcon("SfondoVolpe.jpg");

	panel = new JPanel(new GridLayout(6,1)) {
		
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
	setTitle("FoxGame");
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



				
				
				
		setTitle("Record");
		setSize(1180,820);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		visualizzaButton = new JButton("Visualizza I Tuoi Record");
		Font buttonFont3 = new Font("Comic Sans MS", Font.BOLD, 50);
		visualizzaButton.setFont(buttonFont3);
		visualizzaButton.setOpaque(false);
		visualizzaButton.setContentAreaFilled(false);
		visualizzaButton.setBorderPainted(false);
		visualizzaButton.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		visualizzaButton.setVisible(true);
		visualizzaButton.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				visualizzaButton.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				visualizzaButton.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				visualizzaButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				visualizzaButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				visualizzaButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				visualizzaButton.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				visualizzaButton.setFont(buttonFont2);
			}
		}
				);
		
		
		
		indietroButton = new JButton("**Torna Alla Home**");
		Font indietroFont= new Font("Comic Sans MS", Font.BOLD, 50);
		indietroButton.setFont(indietroFont);
		indietroButton.setOpaque(false);
		indietroButton.setContentAreaFilled(false);
		indietroButton.setBorderPainted(false);
		indietroButton.setFocusPainted(false);
		//menu.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
		indietroButton.setVisible(true);
		indietroButton.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				indietroButton.setForeground(Color.RED);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 70);
				indietroButton.setFont(buttonFont2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				indietroButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				indietroButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				indietroButton.setBackground(new Color(0,0,0,0));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				indietroButton.setForeground(Color.BLACK);
				Font buttonFont2= new Font("Comic Sans MS", Font.BOLD, 50);
				indietroButton.setFont(buttonFont2);
			}
		}
				);


		
		
		panel.add(visualizzaButton);
		panel.add(indietroButton);



		indietroButton.addActionListener(e->{
			dispose();
			new MainScreen();
		});

		visualizzaButton.addActionListener(e->{

			try {

				Properties props = new Properties();
				FileInputStream in = new FileInputStream("database.propreties");
				props.load(in);
				in.close();

				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				Connection conn = DriverManager.getConnection(url,username,password);

				String sql = "Select * from record";

				PreparedStatement stmt = conn.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery();

				int max = 0; // Inizializza max con il valore minimo possibile

				StringBuilder text = new StringBuilder(); 
				while (rs.next()) {
					int record = rs.getInt("score");

					if (record > max) { // Confronta con il valore massimo attuale
						max = record; // Aggiorna il valore massimo
					}

					// Aggiungi il valore corrente al StringBuilder
					text.append(record).append("\n");
				}

				// Aggiungi il valore massimo in cima al StringBuilder
				text.insert(0, "Record " + max + "\n\n");

				// Creazione di un JOptionPane con uno scroll
				JTextArea textArea = new JTextArea(10, 30);
				textArea.setText(text.toString());
				JScrollPane scrollPane = new JScrollPane(textArea);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				// Visualizzazione del JOptionPane con lo scroll
				JOptionPane.showMessageDialog(null, scrollPane, "I Tuoi Score", JOptionPane.PLAIN_MESSAGE);

				System.out.println("Record Attuale " + max);


			  




			}catch(IOException | SQLException ex ) {
				ex.printStackTrace();
			}



		});
		
		
		
		
		add(panel);
		setVisible(true);


	

	}


}





