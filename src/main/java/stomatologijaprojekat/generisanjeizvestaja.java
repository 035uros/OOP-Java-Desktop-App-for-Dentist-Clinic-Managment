package stomatologijaprojekat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


public class generisanjeizvestaja implements ActionListener{
	
	private JLabel label;
	JFrame prozor;
	
	JButton button;
	JButton button2;
	
	JTextArea prostor;
	String pacijent;

	public generisanjeizvestaja(String ime) {
		pacijent=ime;
		
		prozor = new JFrame();
		JPanel panel = new JPanel();
		
		button = new JButton("Генериши");
		button2 = new JButton("Назад");
		
		button.addActionListener(this);
		button2.addActionListener(this);
		
		label = new JLabel("Извештај:");
		prostor = new JTextArea(5, 40);

		
		panel.setBorder(BorderFactory.createEmptyBorder(20,80,50,80));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(prostor);
		panel.add(button);

		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(button2);
		prozor.add(panel, BorderLayout.CENTER);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prozor.setTitle("Генерисање извештаја");
		prozor.pack();
		prozor.setVisible(true);
	}
	
	public void praviPDF(String tekst) throws FileNotFoundException{
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("hh-mm dd-MM-yyyy");  
		    Date date = new Date();  
		    String datum=String.valueOf(formatter.format(date));
		    
		   File directory = new File("datoteke\\стоматолог\\"+pacijent);
		    if (! directory.exists()){
		    	directory.mkdir();
		    }
			String putanja="datoteke\\стоматолог\\"+pacijent+"\\"+ pacijent +" " +datum+".pdf";
			PdfWriter pisac = new PdfWriter(putanja);
			
			PdfDocument pdfDokument=new PdfDocument(pisac);
			
			pdfDokument.addNewPage();
			Paragraph paragraph = new Paragraph(tekst);
			
			Document document = new Document(pdfDokument);
			document.add(paragraph);
			
			document.close();
			
			new stomatolog();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	

	public void actionPerformed(ActionEvent e) {
		prozor.dispose(); 
		if(e.getSource() == button) {
			
			try 
			{
				String tekst=prostor.getText();
				praviPDF(tekst);
			}
			catch(FileNotFoundException er)
			{
				System.out.println(er);
			}
			
		}
		if(e.getSource() == button2) {
			new prikazpregleda("Стоматолог"); 
			}
		}
	}