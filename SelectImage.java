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

public class SelectImage extends JFrame {

private JPanel panel ;

private JButton buttonFox ;
private Timer timer;

private JLabel titleLabel;
private ImageIcon imageicon;

private int x,y;

public SelectImage() {
	
	
	titleLabel = new JLabel("Seleziona La tua Renna");
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

	panel.add(titleLabel);
	
	
	
	 Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs=null;
	 
	    
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
	        rs=stmt.executeQuery("select * from immagine");
	       
	
            ArrayList<String> percorsiImmagini = new ArrayList<>();

            while (rs.next()) {
                String percorso = rs.getString("percorso");
                percorsiImmagini.add(percorso);
                String nome_renna= rs.getString("nome_renna");

            

          
                ImageIcon imageIcon = new ImageIcon(percorso);
                JButton buttonImmagine1 = new JButton(imageIcon);
                buttonImmagine1.setOpaque(false);
                buttonImmagine1.setContentAreaFilled(false);
                buttonImmagine1.setBorderPainted(false);
                buttonImmagine1.setFocusPainted(false);
                buttonImmagine1.setForeground(new Color(0, 0, 0, 0));
                buttonImmagine1.setVisible(true);
                panel.add(buttonImmagine1);

                buttonImmagine1.addActionListener(e -> {
                 
                    JOptionPane.showMessageDialog(null, "Ottima Scelta!!: "+ "Hai Selezionato: " + nome_renna);
                  
                    dispose();
                    Jumping2 game = new Jumping2(percorso);
                    JFrame frame = new JFrame("Jumping Fox Game");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setContentPane(game);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                    try {
                    	String sql="insert into utilizzo(ultimo_percorso) values(?)";
                    	
                    	// Inserimento Percorso del ultima immagine utilizzata dal giocatore 
                    Connection conn_uti = DriverManager.getConnection(url,username,password);
                    PreparedStatement  stmt_uti = conn_uti.prepareStatement(sql);
                    stmt_uti.setString(1, percorso);
                    stmt_uti.execute();
                    
                    }catch(SQLException ex1) {
                    	ex1.printStackTrace();
                    }
                    
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

	
	


public ImageIcon getImage(ImageIcon icon) {
	return icon;
}


}
