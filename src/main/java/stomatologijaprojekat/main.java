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

public class main implements ActionListener{
	
	private JLabel label;
	
	JFrame prozor;
	
	JButton button;
	JButton button2;
	JButton button3;
	
	public main(){
		
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Админ");
		button2 = new JButton("Техничар");
		button3 = new JButton("Стоматолог");
		
		button.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		label = new JLabel("Тип корисника:");
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button3);
		
		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Пријава");
		prozor.pack();
		prozor.setVisible(true);
	}

	public static void main(String[] args) {
		new main();
	}
	
	public void actionPerformed(ActionEvent e) {
		prozor.dispose(); 
		if(e.getSource() == button) {
			new prijava("Админ"); 
			}
		if(e.getSource() == button2) {
			new prijava("Техничар"); 
			}
		if(e.getSource() == button3) {
			new prijava("Стоматолог"); 
			}
		}
	}
