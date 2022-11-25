package stomatologijaprojekat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class prikazpregleda implements ActionListener {
	
	JFrame prozor;
	
	JButton button;
	String tipKorisnika;
	JButton button2;
	JTable jt;
	
	public prikazpregleda(String tip) {
		tipKorisnika=tip;
		
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Назад");
		button2 = new JButton("Креирај извештај");
		
		button.addActionListener(this);
		button2.addActionListener(this);
		
		try {
			String kolona[]={"VREME","IME i PREZIME"}; 
			
			int i=0;
			File myObj = new File("datoteke\\техничар\\" + "прегледи" + ".txt" );
			
			Scanner myReader1 = new Scanner(myObj);
			while (myReader1.hasNextLine()) {
				myReader1.nextLine();
				i++;
			}
			myReader1.close();
			
			String podaci[][] = new String[i][2];
			
			int b =0;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String delovi[] =data.split(" ");
				
				podaci[b][0]=delovi[3] + " " + delovi[2] + " " + delovi[1] + " " + delovi[5];
				podaci[b][1]=delovi[6] + " " + delovi[7];
				
				b++;
				}
			myReader.close();
			
		    jt=new JTable(podaci,kolona);  
		    JScrollPane sp=new JScrollPane(jt); 
		    
			panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
			panel.setLayout(new GridLayout(0,1));
			panel.add(sp);
			panel.add(button);
			if(tip == "Стоматолог") {
				panel.add(Box.createRigidArea(new Dimension(5, 0)));
				panel.add(button2);
			}
		}
		catch (FileNotFoundException e) {
		      new greska("");
		      e.printStackTrace();
		    }
		  
		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Преглед заказаних термина");
		prozor.pack();
		prozor.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e) {
		prozor.dispose(); 
		if(e.getSource() == button) {
			if(tipKorisnika == "Стоматолог") {
				new stomatolog(); 
				}
			else {
				new tehnicar(); 
				}
			}
		if(e.getSource() == button2) {
			int red=jt.getSelectedRow();
			String ime=String.valueOf(jt.getValueAt(red, 1));
			new generisanjeizvestaja(ime);
			}	
		}
	}
