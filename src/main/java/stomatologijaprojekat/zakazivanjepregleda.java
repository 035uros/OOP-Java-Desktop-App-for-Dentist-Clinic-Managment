package stomatologijaprojekat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class zakazivanjepregleda implements ActionListener{
	
	JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
	JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "dd.MM.yyyy/HH:mm:ss");
	
	static JFrame prozor;
	
	JList<String> listaKorisnika;
	
	JButton button;
	JButton button3;
	
	static int i;
	
	public zakazivanjepregleda(){
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Закажи");
		button3 = new JButton("Назад");
		
		button3.addActionListener(this);
		
		JLabel label = new JLabel("Пацијент:");
		JLabel label2 = new JLabel("Термин:");
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));

		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); 
		
		panel.add(label2);
		panel.add(timeSpinner);
		try {

			i=0;
			
			File myObj = new File("datoteke\\техничар\\" + "пацијенти" + ".txt" );
			
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
				osobe[b]=delovi[1] + " " + delovi[2];
				b++;
				}
			myReader.close();
			
			listaKorisnika = new JList<String>(osobe) ;
			button.addActionListener(this);
			
			panel.add(label);
			panel.add(listaKorisnika);
			panel.add(Box.createRigidArea(new Dimension(5, 0)));
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(5, 0)));
			panel.add(button3);
		}
		catch (FileNotFoundException e) {
		      new greska("");
		      e.printStackTrace();
		    }

		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Унос");
		prozor.pack();
		prozor.setVisible(true);
	}
	
	public static void unesipregled(String vreme, String pacijent) {
		
		int flag=0;
		try {
			
			File myObj = new File("datoteke\\техничар\\" + "прегледи" + ".txt" );
			
			String zauzetiTermini[] = new String[i];
			
			int b =0;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String delovi[] =data.split(" ");
				zauzetiTermini[b]=delovi[0] + " " + delovi[1]+ " " + delovi[2]+ " " + delovi[3]+ " " + delovi[4] + " " + delovi[5];
				if(zauzetiTermini[b].equals(vreme)) {
					new greska("Термин је заузет!");
					new tehnicar();
					flag=1;
					break;
					}
				b++;
				}
			myReader.close();
		}
		catch (FileNotFoundException e) {
		      new greska("");
		      e.printStackTrace();
		    }
		
		if(flag != 1) {
			try(FileWriter fw = new FileWriter("datoteke\\техничар\\" + "прегледи" + ".txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
			{
			      File myObj = new File("datoteke\\техничар\\" + "прегледи" + ".txt" );
			      Scanner myReader = new Scanner(myObj);
			     
			      String str =  vreme + " " + pacijent;
			      out.println(str);
			  		
			      myReader.close();
			      
			      prozor.dispose();
			      new tehnicar();
			      
			    } 
			catch(IOException ex)
			{
				new greska("Greska pri upotrebi fajla '"+ "datoteke\\техничар\\прегледи.txt" + "'");}
		    }
		}
		
	public void actionPerformed(ActionEvent e) {
		
		prozor.dispose();
		
		if(e.getSource() == button) {
			String vreme = String.valueOf(timeSpinner.getValue());
			String pacijent= String.valueOf(listaKorisnika.getSelectedValue());
			unesipregled(vreme, pacijent);
			}
		
		if(e.getSource() == button3) {
			new tehnicar();
			}
		}
	}
