package stomatologijaprojekat;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class prijava implements ActionListener{
	
	JTextField korisnickoIme;
	JPasswordField sifra;
	static String tipKorisnika;
	
	static JFrame prozor;
	
	JButton button3; 
	JButton button; 
	
	public prijava(String tip){
		
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Пријава");
		button3 = new JButton("Назад");
		
		button.addActionListener(this);
		button3.addActionListener(this);
		
		JLabel label = new JLabel("Корисничко име:");
		JLabel label2 = new JLabel("Шифра:");
		
		korisnickoIme = new JTextField();
		sifra = new JPasswordField();

		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(korisnickoIme);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label2);
		panel.add(sifra);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button);
		
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button3);
		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Пријава");
		prozor.pack();
		prozor.setVisible(true);
		
		tipKorisnika=tip;
	}
	
	public static void verifikacija(String ime, char[] pass) {
		 try {
			 int flag =1;
			 
			 File myObj = new File("datoteke\\login\\" + tipKorisnika + ".txt" );
			 
			 Scanner myReader = new Scanner(myObj);
			 while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String delovi[] =data.split(" ");
		        if(ime.equals(delovi[0]) && delovi[1].equals(String.valueOf(pass))) {
		        	flag = 0;
		        	}
		        }
			 
		      if(flag == 0) {
		    	  prozor.dispose();
		    	  if(tipKorisnika == "Админ")
		    	  {
		    		  new admin(); 
		    		  }
		    	  if(tipKorisnika == "Техничар")
		    	  {
		    		  new tehnicar(); 
		    		  }
		    	  if(tipKorisnika == "Стоматолог")
		    	  {
		    		  new stomatolog(); 
		    		  }
		    	  }
		      else 
		      {
		    	  new greska("Погрешно корисничко име/лозинка");
		      }
		      myReader.close();
		      
		    } 
		 catch (FileNotFoundException e) {
			 new greska("");
			 e.printStackTrace();
			 }
		 }
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			String ime=korisnickoIme.getText();
			char[] pass=sifra.getPassword();
			verifikacija(ime, pass);
			}
		if(e.getSource() == button3) {
			prozor.dispose();
			new main();
			}
		}
	}

