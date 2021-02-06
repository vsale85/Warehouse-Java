package popups;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import main.Baza;
import main.Warehouse;
import model.DatePickerNew;

public class PopupUpdateP extends Warehouse{

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void popUp(int deo,String f1, String f2, String f3,String f4,String f5,String f6,String f7, String ulogovani ) {
		
		// Define the panel to hold the components
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(370, 450));
        GridBagConstraints gbc = new GridBagConstraints();
        
        JTextField txtNaziv = new JTextField();
        txtNaziv.setPreferredSize(new Dimension(190,30));
        txtNaziv.setFont(new Font("SansSerif", Font.PLAIN, 20));
        txtNaziv.setForeground(new Color(0,153,255));
        txtNaziv.setText(f1);
        
        String dateSql = f2; 
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(f2);
        
        DatePicker date = new DatePicker(DatePickerNew.newDateSet());
        date.setDate(d1);
        date.getComponentDateTextField().setFont(new Font("SansSerif", Font.BOLD, 20));
        date.setPreferredSize(new Dimension(190,30));
		
	    Warehouse w = new Warehouse();
	    JTextField txtKupac = new JTextField();	   	
	    txtKupac.setPreferredSize(new Dimension(190,30));
	    txtKupac.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    txtKupac.setForeground(new Color(0,153,255));
	    txtKupac.setText(f3);
	    
	    JTextField txtCena = new JTextField();
	    txtCena.setDocument(w.new FieldLimit(10));
	    txtCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {					
				justNumbers(evtN);
			}
		});	
	    txtCena.setPreferredSize(new Dimension(190,30));
	    txtCena.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    txtCena.setForeground(new Color(0,153,255));
	    txtCena.setText(f4);	    
	    	    
	    JComboBox model = new JComboBox();
		model.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		model.setPreferredSize(new Dimension(190,30));
		model.setForeground(new Color(0,153,255));
		model.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		model.setBounds(227, 235, 200, 45);

	    JComboBox status = new JComboBox();
	    status.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Vraceno", "Zamena", "Depozit", "Proba"}));
	    status.setForeground(new Color(0,153,255));
	    status.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	    status.setPreferredSize(new Dimension(190,30));
	    
	    JLabel lblNaziv = new JLabel("Naziv dela");
	    lblNaziv.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblNaziv.setForeground(new Color(0,153,255));
	    lblNaziv.setBackground(Color.GRAY);
	    lblNaziv.setOpaque(false);
	    lblNaziv.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblDatum = new JLabel("Datum prodaje");
	    lblDatum.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblDatum.setForeground(new Color(0,153,255));
	    lblDatum.setBackground(Color.GRAY);
	    lblDatum.setOpaque(false);
	    lblDatum.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblKupac = new JLabel("Kupac");
	    lblKupac.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblKupac.setForeground(new Color(0,153,255));
	    lblKupac.setBackground(Color.GRAY);
	    lblKupac.setOpaque(false);
	    lblKupac.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblCena = new JLabel("Cena");
	    lblCena.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblCena.setForeground(new Color(0,153,255));
	    lblCena.setBackground(Color.GRAY);
	    lblCena.setOpaque(false);
	    lblCena.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblModel = new JLabel("Model");
	    lblModel.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblModel.setForeground(new Color(0,153,255));
	    lblModel.setBackground(Color.GRAY);
	    lblModel.setOpaque(false);
	    lblModel.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblStatus = new JLabel("Status");
	    lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblStatus.setForeground(new Color(0,153,255));
	    lblStatus.setBackground(Color.GRAY);
	    lblStatus.setOpaque(false);
	    lblStatus.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lblNap = new JLabel("Napomena");
	    lblNap.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblNap.setForeground(new Color(0,153,255));
	    lblNap.setBackground(Color.GRAY);
	    lblNap.setOpaque(false);
	    lblNap.setPreferredSize(new Dimension(160, 30));
	    
	    JTextArea area = new JTextArea(6, 2);
	    area.setWrapStyleWord(true);
	    area.setLineWrap(true);
	    area.setFont(new Font("SansSerif", Font.PLAIN , 16));
	    area.setForeground(new Color(0,153,255));
	    area.setText(f7);
	    JScrollPane scroll = new JScrollPane(area);
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
	    panel.add(lblNaziv,gbc);
	    
        gbc.gridx = 1;
        gbc.gridy = 0;
	    panel.add(txtNaziv, gbc);
	    
        gbc.gridx = 0;
        gbc.gridy = 2;
	    panel.add(lblDatum,gbc);	    
	
        gbc.gridx = 1;
        gbc.gridy = 2;   
	    panel.add(date,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    panel.add(lblKupac,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    panel.add(txtKupac,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    panel.add(lblCena,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    panel.add(txtCena,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    panel.add(lblModel,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 5;
	    panel.add(model,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    panel.add(lblStatus,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 6;
	    panel.add(status,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 7;
	    panel.add(lblNap,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 8;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(scroll,gbc);
	    
        
        int result = JOptionPane.showConfirmDialog(null, panel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);       
	      if (result == JOptionPane.OK_OPTION) {
	    	  
	    	  LocalDate dateto = date.getDate();
			  DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  String d = df.format(dateto);
	  
	    	  try {
				Baza.IzmenaDelovi(deo, txtNaziv.getText(), d.toString(), txtKupac.getText(), txtCena.getText(),
						model.getSelectedItem().toString(), status.getSelectedItem().toString(), area.getText(), ulogovani);
			} catch (SQLException e) {

				e.printStackTrace();
			}
	         System.out.println();
	         
	      }
	}

}
