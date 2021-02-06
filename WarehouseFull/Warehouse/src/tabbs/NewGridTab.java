package tabbs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.Warehouse;

 public class NewGridTab extends Warehouse{
//	public JPanel prodaja1;
	

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void gridTab(JTabbedPane tabP) {
		
	
	JPanel prodaja1 = new JPanel();
//	tabp.addTab("New tab", null, Prodaja1, null);
	prodaja1.setLayout(new GridBagLayout());
	System.out.println("layout");
	
	JPanel panel = new JPanel();
//	scrollPane_2.setViewportView(panel);
	prodaja1.add(panel);
	panel.setBackground(Color.green);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[]{170, 200, 0};
	gbl_panel.rowHeights = new int[]{45,3,45, 3, 45,45, 45,45, 45,45, 45,4, 45,3};
	gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	JLabel lblDatum_1 = new JLabel("Datum");
	lblDatum_1.setForeground(new Color(0, 153, 255));
	lblDatum_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblDatum_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblDatum_1 = new GridBagConstraints();
	gbc_lblDatum_1.fill = GridBagConstraints.BOTH;
	gbc_lblDatum_1.insets = new Insets(0, 0, 0, 0);
	gbc_lblDatum_1.gridx = 0;
	gbc_lblDatum_1.gridy = 0;
	panel.add(lblDatum_1, gbc_lblDatum_1);
	
/*	JDateChooser datumP_1 = new JDateChooser();
	datumP_1.setToolTipText("Unesite datum prodaje");
	datumP_1.setForeground(new Color(0, 153, 255));
	datumP_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	datumP_1.setDateFormatString("dd/MM/yyyy");
	datumP_1.setBackground(new Color(204, 204, 204));
	GridBagConstraints gbc_datumP_1 = new GridBagConstraints();
	gbc_datumP_1.fill = GridBagConstraints.BOTH;
	gbc_datumP_1.insets = new Insets(0, 0, 0, 0);
	gbc_datumP_1.gridx = 1;
	gbc_datumP_1.gridy = 0;
	panel.add(datumP_1, gbc_datumP_1);		*/
	
	JLabel lblStyleP1_1_1 = new JLabel("");
	lblStyleP1_1_1.setOpaque(true);
	lblStyleP1_1_1.setBackground(Color.YELLOW);
	GridBagConstraints gbc_lblStyleP1_1_1 = new GridBagConstraints();
	gbc_lblStyleP1_1_1.fill = GridBagConstraints.BOTH;
	gbc_lblStyleP1_1_1.insets = new Insets(0, 0, 0, 0);
	gbc_lblStyleP1_1_1.gridx = 0;
	gbc_lblStyleP1_1_1.gridy = 1;
	gbc_lblStyleP1_1_1.gridwidth = 2;
	panel.add(lblStyleP1_1_1, gbc_lblStyleP1_1_1);
	
	JLabel lblProdato_1 = new JLabel("Prodato");
	lblProdato_1.setOpaque(false);
	lblProdato_1.setForeground(new Color(0, 153, 255));
	lblProdato_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblProdato_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblProdato_1 = new GridBagConstraints();
	gbc_lblProdato_1.fill = GridBagConstraints.BOTH;
	gbc_lblProdato_1.insets = new Insets(0, 0, 0, 0);
	gbc_lblProdato_1.gridx = 0;
	gbc_lblProdato_1.gridy = 2;
	panel.add(lblProdato_1, gbc_lblProdato_1);
	
	JTextField textField = new JTextField();
	textField.setForeground(new Color(0, 153, 255));
	textField.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField.setColumns(10);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.fill = GridBagConstraints.BOTH;
	gbc_textField.insets = new Insets(0, 0, 0, 0);
	gbc_textField.gridx = 1;
	gbc_textField.gridy = 2;
	panel.add(textField, gbc_textField);
	
	JLabel lblStyleP1_1 = new JLabel("");
	lblStyleP1_1.setOpaque(true);
	lblStyleP1_1.setBackground(Color.YELLOW);
	GridBagConstraints gbc_lblStyleP1_1 = new GridBagConstraints();
	gbc_lblStyleP1_1.fill = GridBagConstraints.BOTH;
	gbc_lblStyleP1_1.insets = new Insets(0, 0, 0, 0);
	gbc_lblStyleP1_1.gridx = 0;
	gbc_lblStyleP1_1.gridy = 3;
	gbc_lblStyleP1_1.gridwidth = 2;
	panel.add(lblStyleP1_1, gbc_lblStyleP1_1);
	
	JLabel lblCena_1 = new JLabel("Cena");
	lblCena_1.setForeground(new Color(0, 153, 255));
	lblCena_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblCena_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblCena_1 = new GridBagConstraints();
	gbc_lblCena_1.fill = GridBagConstraints.BOTH;
	gbc_lblCena_1.insets = new Insets(0, 0, 0, 0);
	gbc_lblCena_1.gridx = 0;
	gbc_lblCena_1.gridy = 4;
	panel.add(lblCena_1, gbc_lblCena_1);
	
	JTextField textField_1 = new JTextField();
	textField_1.setForeground(new Color(0, 153, 255));
	textField_1.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField_1.setColumns(10);
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.fill = GridBagConstraints.BOTH;
	gbc_textField_1.insets = new Insets(0, 0, 5, 0);
	gbc_textField_1.gridx = 1;
	gbc_textField_1.gridy = 4;
	panel.add(textField_1, gbc_textField_1);
	
	JLabel lblModel_1 = new JLabel("Model");
	lblModel_1.setForeground(new Color(0, 153, 255));
	lblModel_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblModel_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblModel_1 = new GridBagConstraints();
	gbc_lblModel_1.fill = GridBagConstraints.BOTH;
	gbc_lblModel_1.gridx = 0;
	gbc_lblModel_1.gridy = 5;
	panel.add(lblModel_1, gbc_lblModel_1);
	
	JComboBox txtModel_1 = new JComboBox();
	txtModel_1.setForeground(new Color(0, 153, 255));
	txtModel_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	GridBagConstraints gbc_txtModel_1 = new GridBagConstraints();
	gbc_txtModel_1.fill = GridBagConstraints.BOTH;
	gbc_txtModel_1.insets = new Insets(0, 0, 5, 0);
	gbc_txtModel_1.gridx = 1;
	gbc_txtModel_1.gridy = 5;
	panel.add(txtModel_1, gbc_txtModel_1);
	
	JLabel lblStatus_1 = new JLabel("Status");
	lblStatus_1.setForeground(new Color(0, 153, 255));
	lblStatus_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblStatus_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblStatus_1 = new GridBagConstraints();
	gbc_lblStatus_1.fill = GridBagConstraints.BOTH;
	gbc_lblStatus_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblStatus_1.gridx = 0;
	gbc_lblStatus_1.gridy = 6;
	panel.add(lblStatus_1, gbc_lblStatus_1);
	
	JComboBox txtStatus_1 = new JComboBox();
	txtStatus_1.setForeground(new Color(0, 153, 255));
	txtStatus_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	GridBagConstraints gbc_txtStatus_1 = new GridBagConstraints();
	gbc_txtStatus_1.fill = GridBagConstraints.BOTH;
	gbc_txtStatus_1.insets = new Insets(0, 0, 5, 0);
	gbc_txtStatus_1.gridx = 1;
	gbc_txtStatus_1.gridy = 6;
	panel.add(txtStatus_1, gbc_txtStatus_1);
	
	JLabel lblKupac_1 = new JLabel("Kupac");
	lblKupac_1.setForeground(new Color(0, 153, 255));
	lblKupac_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblKupac_1.setBackground(Color.DARK_GRAY);
	GridBagConstraints gbc_lblKupac_1 = new GridBagConstraints();
	gbc_lblKupac_1.fill = GridBagConstraints.BOTH;
	gbc_lblKupac_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblKupac_1.gridx = 0;
	gbc_lblKupac_1.gridy = 7;
	panel.add(lblKupac_1, gbc_lblKupac_1);
	
	JTextField textField_2 = new JTextField();
	textField_2.setForeground(new Color(0, 153, 255));
	textField_2.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField_2.setColumns(10);
	GridBagConstraints gbc_textField_2 = new GridBagConstraints();
	gbc_textField_2.insets = new Insets(0, 0, 5, 0);
	gbc_textField_2.fill = GridBagConstraints.BOTH;
	gbc_textField_2.gridx = 1;
	gbc_textField_2.gridy = 7;
	panel.add(textField_2, gbc_textField_2);
	
	JButton btnSacuvajP_1 = new JButton("Sacuvaj");
	
	btnSacuvajP_1.setForeground(new Color(0, 153, 255));
	btnSacuvajP_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
	GridBagConstraints gbc_btnSacuvajP_1 = new GridBagConstraints();
	gbc_btnSacuvajP_1.insets = new Insets(0, 0, 5, 0);
	gbc_btnSacuvajP_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnSacuvajP_1.gridx = 0;
	gbc_btnSacuvajP_1.gridy = 11;
	gbc_btnSacuvajP_1.gridwidth = 2;
	panel.add(btnSacuvajP_1, gbc_btnSacuvajP_1);
	
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(Color.GREEN);
	prodaja1.add(panel_1);
	panel_1.setLayout(new GridLayout(8, 2, 0, 0));
	
	JLabel lblDatum_1_1 = new JLabel("Datum");
	lblDatum_1_1.setForeground(new Color(0, 153, 255));
	lblDatum_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblDatum_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblDatum_1_1);
	
/*	JDateChooser datumP_1_1 = new JDateChooser();
	datumP_1_1.setToolTipText("Unesite datum prodaje");
	datumP_1_1.setForeground(new Color(0, 153, 255));
	datumP_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	datumP_1_1.setDateFormatString("dd/MM/yyyy");
	datumP_1_1.setBackground(new Color(204, 204, 204));
	panel_1.add(datumP_1_1);		*/
	
	JLabel lblProdato_1_1 = new JLabel("Prodato");
	lblProdato_1_1.setOpaque(false);
	lblProdato_1_1.setForeground(new Color(0, 153, 255));
	lblProdato_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblProdato_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblProdato_1_1);
	
	JTextField textField_3 = new JTextField();
	textField_3.setForeground(new Color(0, 153, 255));
	textField_3.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField_3.setColumns(10);
	panel_1.add(textField_3);
	
	JLabel lblCena_1_1 = new JLabel("Cena");
	lblCena_1_1.setForeground(new Color(0, 153, 255));
	lblCena_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblCena_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblCena_1_1);
	
	JTextField textField_4 = new JTextField();
	textField_4.setForeground(new Color(0, 153, 255));
	textField_4.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField_4.setColumns(10);
	panel_1.add(textField_4);
	
	JLabel lblModel_1_1 = new JLabel("Model");
	lblModel_1_1.setForeground(new Color(0, 153, 255));
	lblModel_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblModel_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblModel_1_1);
	
	JComboBox txtModel_1_1 = new JComboBox();
	txtModel_1_1.setForeground(new Color(0, 153, 255));
	txtModel_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	panel_1.add(txtModel_1_1);
	
	JLabel lblStatus_1_1 = new JLabel("Status");
	lblStatus_1_1.setForeground(new Color(0, 153, 255));
	lblStatus_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblStatus_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblStatus_1_1);
	
	JComboBox txtStatus_1_1 = new JComboBox();
	txtStatus_1_1.setForeground(new Color(0, 153, 255));
	txtStatus_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	panel_1.add(txtStatus_1_1);
	
	JLabel lblKupac_1_1 = new JLabel("Kupac");
	lblKupac_1_1.setForeground(new Color(0, 153, 255));
	lblKupac_1_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
	lblKupac_1_1.setBackground(Color.DARK_GRAY);
	panel_1.add(lblKupac_1_1);
	
	JTextField textField_5 = new JTextField();
	textField_5.setForeground(new Color(0, 153, 255));
	textField_5.setFont(new Font("SansSerif", Font.BOLD, 20));
	textField_5.setColumns(10);
	panel_1.add(textField_5);
	
	tabP.addTab("Novi TAb", prodaja1);
//	JScrollPane scrollPane_2 = new JScrollPane(Prodaja1);
//	tabPane.add(scrollPane_2);
//	scrollPane_2.setViewportView(Prodaja1);
	
	}
 }
