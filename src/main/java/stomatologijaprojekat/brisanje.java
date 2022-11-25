package stomatologijaprojekat;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class brisanje implements ActionListener{
	
	int zastavica = 0;
	static String tipKorisnika;
	
	JPasswordField sifra;
	
	JTextField korisnickoIme;
	JTextField ime;
	JTextField prezime;
	
	String role[]= { "Стоматолог","Техничар"};
	JList<String>  funkcija = new JList<String>(role) ;
	JList<String>  listaKorisnika;
	
	static JFrame prozor;
	static JPanel panel;
	
	JLabel label;
	
	JButton button;
	JButton button2;
	JButton button3;

	
	public brisanje(){
		prozor = new JFrame();
		panel = new JPanel();
		
		label = new JLabel("Одабери корисника:");
		JLabel label2 = new JLabel("Одабери тип корисника:");
		
		button = new JButton("Обриши");
		button2 = new JButton("Излистај кориснике");
		button3 = new JButton("Назад");

		button2.addActionListener(this);
		button3.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button3);
		panel.add(label2);
		panel.add(funkcija);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button2);

		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Брисање");
		prozor.pack();
		prozor.setVisible(true);
	}
	
	public void izlistaj(String tip) {
		try {
			if(zastavica == 1) {
				panel.remove(label);
				panel.remove(listaKorisnika);
				panel.remove(button);
				button.removeActionListener(this);
			}
			
			int i=0;
			File myObj = new File("datoteke\\login\\" + tip + ".txt" );
			Scanner myReader1 = new Scanner(myObj);
			while (myReader1.hasNextLine()) {
				myReader1.nextLine();
				i++;
			}
			myReader1.close();
			
			String osobe[] = new String[i];
			int b =0;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String delovi[] =data.split(" ");
				if(tip == "Стоматолог") {
					osobe[b]=delovi[0] + " " + delovi[2] + " " + delovi[3] + " " + delovi[4] + " " + delovi[5];
				}
				else {
					osobe[b]=delovi[0] + " " + delovi[2] + " " + delovi[3] + " " + delovi[4];
				}
				b++;
				}
			myReader.close();
			
			listaKorisnika = new JList<String>(osobe);
			
			button.addActionListener(this);
			
			panel.add(label);
			panel.add(listaKorisnika);
			panel.add(button);
			zastavica=1;
			
			
			prozor.add(panel, BorderLayout.CENTER);
			prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			prozor.setTitle("Брисање");
			prozor.pack();
			prozor.setVisible(true);
		}
		catch (FileNotFoundException e) {
		      new greska("");
		      e.printStackTrace();
		    }
	}
	
	public static void brisi(String korisnik) {
		try {
			File inputFile = new File("datoteke\\login\\" + tipKorisnika + ".txt");
			File tempFile = new File("datoteke\\login\\temp.txt");
			File rename = new File("datoteke\\login\\" + tipKorisnika + ".txt");
			
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    String delovi[] =trimmedLine.split(" ");
			    String delovi2[]=korisnik.split(" ");
			    if(delovi[0].equals(delovi2[0])) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			    }
			
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			tempFile.renameTo(rename);
			prozor.dispose();
			new brisanje();
			
		}
		catch(FileNotFoundException e){
			new greska("");
			e.printStackTrace();
			}
		catch(IOException ex) {
			new greska("");
			}
	    }
	        	
	           
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button2) {
			tipKorisnika=funkcija.getSelectedValue();
			izlistaj(String.valueOf(funkcija.getSelectedValue())); 
		}
		if(e.getSource() == button) {
			String korisnik = String.valueOf(listaKorisnika.getSelectedValue()); 
			brisi(korisnik);
		}
		if(e.getSource() == button3) {
			prozor.dispose();
			new admin();
		}

	}

}
