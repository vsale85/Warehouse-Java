package popups;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import model.DatePickerNew;

public class PopupUpdateK {

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
        
        JTextField Field1 = new JTextField();
        Field1.setPreferredSize(new Dimension(190,30));
        Field1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        Field1.setForeground(new Color(0,153,255));
        Field1.setText(f1);
        
        String dateSql = f2; 
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(f2);
        
        DatePicker date = new DatePicker(DatePickerNew.newDateSet());
        date.setDate(d1);
        date.getComponentDateTextField().setFont(new Font("SansSerif", Font.BOLD, 20));
        date.setPreferredSize(new Dimension(190,30));
	    			    
	    JTextField Field3 = new JTextField();
	    Field3.setPreferredSize(new Dimension(190,30));
	    Field3.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    Field3.setForeground(new Color(0,153,255));
	    Field3.setText(f3);
	    
	    JTextField Field4 = new JTextField();
	    Field4.setPreferredSize(new Dimension(190,30));
	    Field4.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    Field4.setForeground(new Color(0,153,255));
	    Field4.setText(f4);	    
	    	    
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
	    
	    JLabel lbl1 = new JLabel("Naziv dela");
	    lbl1.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl1.setForeground(new Color(0,153,255));
	    lbl1.setBackground(Color.GRAY);
	    lbl1.setOpaque(false);
	    lbl1.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl2 = new JLabel("Datum prodaje");
	    lbl2.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl2.setForeground(new Color(0,153,255));
	    lbl2.setBackground(Color.GRAY);
	    lbl2.setOpaque(false);
	    lbl2.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl3 = new JLabel("Kupac");
	    lbl3.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl3.setForeground(new Color(0,153,255));
	    lbl3.setBackground(Color.GRAY);
	    lbl3.setOpaque(false);
	    lbl3.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl4 = new JLabel("Cena");
	    lbl4.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl4.setForeground(new Color(0,153,255));
	    lbl4.setBackground(Color.GRAY);
	    lbl4.setOpaque(false);
	    lbl4.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl5 = new JLabel("Model");
	    lbl5.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl5.setForeground(new Color(0,153,255));
	    lbl5.setBackground(Color.GRAY);
	    lbl5.setOpaque(false);
	    lbl5.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl6 = new JLabel("Status");
	    lbl6.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl6.setForeground(new Color(0,153,255));
	    lbl6.setBackground(Color.GRAY);
	    lbl6.setOpaque(false);
	    lbl6.setPreferredSize(new Dimension(160, 30));
	    
	    JLabel lbl7 = new JLabel("Napomena");
	    lbl7.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lbl7.setForeground(new Color(0,153,255));
	    lbl7.setBackground(Color.GRAY);
	    lbl7.setOpaque(false);
	    lbl7.setPreferredSize(new Dimension(160, 30));
	    
	    JTextArea area = new JTextArea(6, 2);
	    area.setWrapStyleWord(true);
	    area.setLineWrap(true);
	    area.setFont(new Font("SansSerif", Font.PLAIN , 16));
	    area.setForeground(new Color(0,153,255));
	    area.setText(f7);
	    JScrollPane scroll = new JScrollPane(area);
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
	    panel.add(lbl1,gbc);
	    
        gbc.gridx = 1;
        gbc.gridy = 0;
	    panel.add(Field1, gbc);
	    
        gbc.gridx = 0;
        gbc.gridy = 2;
	    panel.add(lbl2,gbc);	    
	
        gbc.gridx = 1;
        gbc.gridy = 2;   
	    panel.add(date,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    panel.add(lbl3,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    panel.add(Field3,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    panel.add(lbl4,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    panel.add(Field4,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    panel.add(lbl5,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 5;
	    panel.add(model,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    panel.add(lbl6,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 6;
	    panel.add(status,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 7;
	    panel.add(lbl7,gbc);
	    
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
				Baza.IzmenaDelovi(deo, Field1.getText(), d.toString(), Field3.getText(), Field4.getText(),
						model.getSelectedItem().toString(), status.getSelectedItem().toString(), area.getText(), ulogovani);
			} catch (SQLException e) {

				e.printStackTrace();
			}
	         System.out.println();
	         
	      }
	}

}
