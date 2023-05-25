package it.gioco;
 
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectLevel extends JFrame {

private JPanel panel ;

private JButton buttonFox ;
private Timer timer;

private JLabel titleLabel;
private ImageIcon imageicon;

private int x,y;
private String percorso_sfondo,percorso_ostacolo;







public SelectLevel() {
	
	
	titleLabel = new JLabel("Seleziona Il Livello");
	titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	titleLabel.setVerticalAlignment(SwingConstants.CENTER);
	Font font1	= new Font("Comic Sans MS", Font.PLAIN, 50);
	titleLabel.setFont(font1);
	titleLabel.setForeground(Color.RED);
	
	

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

	
	// Immagine di sfondo
	ImageIcon icona = new ImageIcon("SfondoVolpe.jpg");
	setIconImage(icona.getImage());
	setTitle("FoxGame");
	setSize(1180, 820);

	this.imageicon = new ImageIcon("SfondoVolpe.jpg");
	this.x = 0;
	this.y = 0;

	
  // Temporizzazione per lo scorrimento dello sfondo 
	timer = new Timer(10, e -> {
		x++;
		if (x > imageicon.getIconWidth()) {
			x = 0;
		}
		panel.repaint();
	});

	timer.start();

	panel.add(titleLabel);
	
	
	
	 Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs=null;
	 
	    // Propreties Per la connesione al database
	    try {
	    	Properties props = new Properties();
			FileInputStream in = new FileInputStream("database.propreties");
		
				props.load(in);
				in.close();
			
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			
			
			
	    	
	        conn = DriverManager.getConnection(url,username,password);
	        stmt=conn.createStatement();
	        
	       
	        rs=stmt.executeQuery("select * from livelli");
	       
	
           
	        // Stampa in modo dinamico degli elementi presenti nel database come bottoni per la selezione dei livelli
            while (rs.next()) {
            	 byte[] bytes = rs.getBytes("immagine_livelli");
               String percorso_sfondo = rs.getString("percorso_livello");
                String percorso_ostacolo = rs.getString("percorso_ostacoli");
             
                String nome_renna= rs.getString("descrizione_livello");

                Image image = Toolkit.getDefaultToolkit().createImage(bytes);

                // Ridimensiona l'immagine
                int desiredWidth = 200; // Larghezza desiderata
                int desiredHeight = 200; // Altezza desiderata
                Image resizedImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

                // Crea l'oggetto ImageIcon utilizzando l'immagine ridimensionata
                ImageIcon icon = new ImageIcon(resizedImage);

                JButton buttonImmagine1 = new JButton();
                
                buttonImmagine1.setIcon(icon);
                buttonImmagine1.setOpaque(false);
                buttonImmagine1.setContentAreaFilled(false);
                buttonImmagine1.setBorderPainted(false);
                buttonImmagine1.setFocusPainted(false);
                buttonImmagine1.setForeground(new Color(0, 0, 0, 0));
                buttonImmagine1.setVisible(true);
                
                panel.add(buttonImmagine1);

                buttonImmagine1.addActionListener(e -> {
                    dispose();
                 
                 SelectImage selectImage = new SelectImage();
                 
                 selectImage.setPercorsoSelezionato(percorso_sfondo);
                 selectImage.setPercorsoSelezionatoOstacolo(percorso_ostacolo);
                    JOptionPane.showMessageDialog(null, "Ottima Scelta!!: "+ "Hai Selezionato: " + nome_renna);
                  
                
                    
                  
            
            
          
            
                });
            }

	        

	            
	           
	               
	            
	                 
	                    
	                  

	              
	        
	            
	          
	           
	   	     
	      
	            
	           
	        
	        

	     
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	    	}catch(SQLException e) {
	        e.printStackTrace();
	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    
	    

		add(panel);
		//SETTAGGIO DIMENSIONI DELLA FINESTRA
		setTitle("FOX GAMES");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

     }

	
	




 

}
