package stomatologijaprojekat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class unos implements ActionListener{
	
	JPasswordField sifra;
	
	JTextField korisnickoIme;
	JTextField ime;
	JTextField prezime;
	JTextField uzaoblast;
	
	String role[]= { "Стоматолог","Техничар"};
	JList<String>  funkcija = new JList<String>(role) ;

	static JFrame prozor;
	JButton button;
	JButton button3;


	public unos(){
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Унеси");
		button3 = new JButton("Назад");
		
		JLabel label = new JLabel("Корисничко име:");
		JLabel label2 = new JLabel("Шифра:");
		JLabel label3 = new JLabel("Име:");
		JLabel label4 = new JLabel("Презиме:");
		JLabel label5 = new JLabel("Функција:");
		JLabel label6 = new JLabel("Ужа област рада:");
		
		korisnickoIme = new JTextField();
		sifra = new JPasswordField();
		ime = new JTextField();
		prezime = new JTextField();
		uzaoblast = new JTextField();
		
		button.addActionListener(this);
		button3.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(korisnickoIme);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label2);
		panel.add(sifra);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label3);
		panel.add(ime);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label4);
		panel.add(prezime);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label5);
		panel.add(funkcija);
		panel.add(label6);
		panel.add(uzaoblast);
		uzaoblast.setEnabled(false);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button3);
		
		ListSelectionListener ltd = new ListSelectionListener(){
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if(String.valueOf(funkcija.getSelectedValue()) == "Техничар") {
				uzaoblast.setEnabled(false);
				}
			else if(String.valueOf(funkcija.getSelectedValue()) == "Стоматолог") {
				uzaoblast.setEnabled(true);
				}
			}
		 };
		
		funkcija.addListSelectionListener(ltd);

		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Унос");
		prozor.pack();
		prozor.setVisible(true);
	}
	
	public static void unesi(String korisnicko, char[] pass, String imeKorisnik, String prezimeKorisnik, String rolaKorisnik, String oblast) {
		try(FileWriter fw = new FileWriter("datoteke\\login\\" + rolaKorisnik + ".txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
		{
			int flag =1;
			File myObj = new File("datoteke\\login\\" + rolaKorisnik + ".txt" );
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	String data = myReader.nextLine();
		        String delovi[] =data.split(" ");
		        if(korisnicko.equals(delovi[0])) {
		        	flag = 0;
		        }
		      }
		    if(flag == 1) {
		    	String str =  korisnicko + " " + String.valueOf(pass) + " " + imeKorisnik + " " + prezimeKorisnik + " " + rolaKorisnik;
		    	if(rolaKorisnik == "Стоматолог") {
		    		str = str + " " + oblast;
		    	}
		    	out.println(str);
				System.out.println(str);
		      }
		    else {
		    	new greska("Корисничко име је заузето.");
		    	}
		    myReader.close();
		    prozor.dispose();
		    new admin(); 
		    
		    } catch(IOException ex) {
		    	new greska("Greska pri upotrebi fajla '"+ "datoteke\\login\\" + rolaKorisnik + ".txt" + "'");
		    	}
	    }
	        	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			
			String korisnik=korisnickoIme.getText();
			char[] pass=sifra.getPassword();
			String imeKorisnika=ime.getText();
			String prezimeKorisnika=prezime.getText();
			String rolaKorisnika=String.valueOf(funkcija.getSelectedValue());
			String uzaoblastrada;
			
			if(uzaoblast.getText().length() == 0 || String.valueOf(funkcija.getSelectedValue()) == "Техничар"){
				uzaoblastrada = " ";
				}
			else {
				uzaoblastrada=uzaoblast.getText();
				}
			unesi(korisnik, pass, imeKorisnika, prezimeKorisnika, rolaKorisnika, uzaoblastrada);
			}
		if(e.getSource() == button3) {
			prozor.dispose();
		    new admin(); 
		    }
		}
	}