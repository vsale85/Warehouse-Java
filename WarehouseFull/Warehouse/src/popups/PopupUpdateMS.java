package popups;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Baza;
import main.Warehouse;

public class PopupUpdateMS extends Warehouse{

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void popUp(int id,String k, String v, String s, String n) {
		
		
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(370, 450));
        GridBagConstraints gbc = new GridBagConstraints();
        
        JTextField txtKupac = new JTextField();
        txtKupac.setPreferredSize(new Dimension(190,30));
        txtKupac.setFont(new Font("SansSerif", Font.PLAIN, 20));
        txtKupac.setForeground(new Color(0,153,255));
        txtKupac.setText(k);
        	    
	    JTextField txtVozilo = new JTextField();	   	
	    txtVozilo.setPreferredSize(new Dimension(190,30));
	    txtVozilo.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    txtVozilo.setForeground(new Color(0,153,255));
	    txtVozilo.setText(v);	    

	    JComboBox comboStatus = new JComboBox();
	    comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Vraceno", "Zamena", "Depozit", "Proba"}));
	    comboStatus.setForeground(new Color(0,153,255));
	    comboStatus.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	    comboStatus.setPreferredSize(new Dimension(190,30));
	    comboStatus.setSelectedItem(s);
	    
	    JLabel lblKupac = new JLabel("Kupac");
	    lblKupac.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblKupac.setForeground(new Color(0,153,255));
	    lblKupac.setBackground(Color.GRAY);
	    lblKupac.setOpaque(false);
	    lblKupac.setPreferredSize(new Dimension(160, 30));	    	   
	    
	    JLabel lblVozilo = new JLabel("Vozilo");
	    lblVozilo.setFont(new Font("SansSerif", Font.PLAIN, 20));
	    lblVozilo.setForeground(new Color(0,153,255));
	    lblVozilo.setBackground(Color.GRAY);
	    lblVozilo.setOpaque(false);
	    lblVozilo.setPreferredSize(new Dimension(160, 30));	   
	    
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
	    
	    JTextArea areaNap = new JTextArea(6, 2);
	    areaNap.setWrapStyleWord(true);
	    areaNap.setLineWrap(true);
	    areaNap.setFont(new Font("SansSerif", Font.PLAIN , 16));
	    areaNap.setForeground(new Color(0,153,255));
	    areaNap.setText(n);
	    JScrollPane scroll = new JScrollPane(areaNap);
	    
	    gbc.gridx = 0;
        gbc.gridy = 0;
	    panel.add(lblKupac,gbc);
	    
        gbc.gridx = 1;
        gbc.gridy = 0;
	    panel.add(txtKupac, gbc);
	   
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    panel.add(lblVozilo,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    panel.add(txtVozilo,gbc);
	    	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    panel.add(lblStatus,gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    panel.add(comboStatus,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    panel.add(lblNap,gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(scroll,gbc);
	    
        
        int result = JOptionPane.showConfirmDialog(null, panel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);       
	      if (result == JOptionPane.OK_OPTION) {
	    	  
	    	  try {
	
	    		  Baza.IzmenaMS(id, txtKupac.getText(), txtVozilo.getText(), comboStatus.getSelectedItem().toString(), areaNap.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	         System.out.println();
	         
	      }
	}

}
