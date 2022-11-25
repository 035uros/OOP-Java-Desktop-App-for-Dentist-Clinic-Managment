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
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class unospacijanata implements ActionListener{
	
	JTextField ime;
	JTextField prezime;
	
	static JFrame prozor;
	
	JButton button; 
	JButton button3;

	public unospacijanata(){
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Унеси");
		button3 = new JButton("Назад");
		
		button.addActionListener(this);
		button3.addActionListener(this);
		
		JLabel label3 = new JLabel("Име:");
		JLabel label4 = new JLabel("Презиме:");
		
		ime = new JTextField();
		prezime = new JTextField();

		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label3);
		panel.add(ime);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(label4);
		panel.add(prezime);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button3);

		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Унос пацијената");
		prozor.pack();
		prozor.setVisible(true);
	}
	
	public static void unesiPacijenta(String pass, String imePacijent, String prezimePacijent) {
		try(FileWriter fw = new FileWriter("datoteke\\техничар\\" + "пацијенти" + ".txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
		{
			int flag =1;
			
			File myObj = new File("datoteke\\техничар\\" + "пацијенти" + ".txt" );
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
		        String delovi[] =data.split(" ");
		        if(pass.equals(delovi[0])) {
		        	flag = 0;
		        	}
		        }
		      if(flag == 1) {
		    	String str =  pass + " " + imePacijent + " " + prezimePacijent ;
				out.println(str);
		      }
		      else {
		    	  new greska("Прекпаање ID-a");
		    	  }
		      
		      myReader.close();
		      prozor.dispose();
		      new tehnicar(); 
		      
		    } 
		catch(IOException ex) {
			new greska("Грешка при коришћењу '"+ "datoteke\\техничар\\" + "пацијенти" + ".txt" + "'");
			}
		}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			String pass=UUID.randomUUID().toString();
			String imePac=ime.getText();
			String prezimePac=prezime.getText();
			unesiPacijenta(pass, imePac, prezimePac);
		}
		
		if(e.getSource() == button3) {
			prozor.dispose();
			new tehnicar();
			}
		}
	}