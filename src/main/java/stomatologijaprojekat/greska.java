package stomatologijaprojekat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class greska implements ActionListener {
	
	JTextField korisnickoIme;
	JPasswordField sifra;
	
	static String tipKorisnika;
	
	static JFrame prozor;
	
	public greska(String greska) {
		
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		JButton button = new JButton("У реду");
		
		button.addActionListener(this);
		
		JLabel label = new JLabel("Дошло је до грешке!");
		JLabel label2 = new JLabel(greska);
		
		sifra = new JPasswordField();
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button);
		
		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Грешка");
		prozor.pack();
		prozor.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e) {
		prozor.dispose();
		}
	}
