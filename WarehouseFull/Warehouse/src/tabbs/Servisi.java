package tabbs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;

import main.Baza;
import main.Warehouse;
import model.DatabaseConnection;
import model.DatePickerNew;
import model.Search;
import net.proteanit.sql.DbUtils;
import popups.PopupMSpretraga;
import popups.PopupMSunos;
import popups.PopupNabavkaPretraga;
import popups.PopupNabavkaUnos;
import popups.PopupPopravkePretraga;
import popups.PopupPopravkeUnos;
import popups.PopupUpdateMS;
import popups.PopupUpdateP;
import popups.PopupVulkPretraga;
import popups.PopupVulkUnos;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Servisi extends DatabaseConnection{
	private static JTextField txtCenaV;
		
		private static ArrayList<JLabel> lblServisi;	
		public ArrayList<JLabel> getlblServisi(){
			return lblServisi;
		}		
		
		private static ArrayList<JTable> tableFontS;
		public ArrayList<JTable> getTableFont(){
			return tableFontS;
		}
		
		private static ArrayList<JTextField> inputFontColorS;
		public ArrayList<JTextField> getinputFontColorS(){
			return inputFontColorS;
		}
		
		private static ArrayList<JComboBox> comboFontColorS;
		public ArrayList<JComboBox> getComboFontColor(){
			return comboFontColorS;
		}
		
/*		private static ArrayList<JTextArea> areaFontColorS;
		public ArrayList<JTextArea> getAreaFontColor(){
			return areaFontColorS;
		}		*/
		
		private static ArrayList<JButton> btnSaveFontColorS;
		public ArrayList<JButton> getSaveFontColor(){
			return btnSaveFontColorS;
		}
		
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void servisi(JTabbedPane tp) {
		
		lblServisi = new ArrayList<>();
		tableFontS = new ArrayList<>();
		inputFontColorS = new ArrayList<>();
		comboFontColorS = new ArrayList<>();
		btnSaveFontColorS = new ArrayList<>();
		
		JPanel servisiPanel = new JPanel();
		tp.addTab("Servisi", null, servisiPanel, null);
//		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		gbc_panel_1.gridheight = 11;
//		servisiPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {330, 330, 330, 330, 0};
		gbl_panel_1.rowHeights = new int[] {605, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0};
//		panel_1.setLayout(gbl_panel_1);
		servisiPanel.setLayout(gbl_panel_1);
		
		JPanel panelMServisi = new JPanel();
		GridBagConstraints gbc_panelMServisi = new GridBagConstraints();
		gbc_panelMServisi.anchor = GridBagConstraints.NORTH;
		gbc_panelMServisi.insets = new Insets(0, 0, 5, 15);
		gbc_panelMServisi.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelMServisi.gridx = 0;
		gbc_panelMServisi.gridy = 0;
		servisiPanel.add(panelMServisi, gbc_panelMServisi);			//*****
		GridBagLayout gbl_panelMServisi = new GridBagLayout();
		gbl_panelMServisi.columnWidths = new int[] {155, 155};
		gbl_panelMServisi.rowHeights = new int[] {50, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 45, 45, 45, 45};
		gbl_panelMServisi.columnWeights = new double[]{1.0, 1.0};
		gbl_panelMServisi.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0};
		panelMServisi.setLayout(gbl_panelMServisi);
		
		JButton btnMSunos = new JButton("Unesi Mali Servis");
		try {
			Image img = ImageIO.read(Servisi.class.getResource("/service.jpg"));
			btnMSunos.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		btnMSunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {					
					PopupMSunos ms = new PopupMSunos();
				} catch (Exception e2) {
				
				}	
				
			}
		});
		btnMSunos.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_btnMSunos = new GridBagConstraints();
		gbc_btnMSunos.fill = GridBagConstraints.BOTH;
		gbc_btnMSunos.gridwidth = 2;
		gbc_btnMSunos.insets = new Insets(0, 0, 5, 0);
		gbc_btnMSunos.gridx = 0;
		gbc_btnMSunos.gridy = 0;
		panelMServisi.add(btnMSunos, gbc_btnMSunos);
		btnSaveFontColorS.add(btnMSunos);
		
		JLabel lblPretragaMS = new JLabel("PRETRAGA MALIH SERVISA");
		lblPretragaMS.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretragaMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblPretragaMS = new GridBagConstraints();
		gbc_lblPretragaMS.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPretragaMS.gridwidth = 2;
		gbc_lblPretragaMS.insets = new Insets(0, 0, 5, 0);
		gbc_lblPretragaMS.gridx = 0;
		gbc_lblPretragaMS.gridy = 1;
		panelMServisi.add(lblPretragaMS, gbc_lblPretragaMS);
		lblServisi.add(lblPretragaMS);
		
		JLabel sms1 = new JLabel("");
		sms1.setBackground(new Color(0,153,255));
		sms1.setOpaque(true);
		GridBagConstraints gbc_sms1 = new GridBagConstraints();
		gbc_sms1.fill = GridBagConstraints.BOTH;
		gbc_sms1.gridwidth = 2;
		gbc_sms1.insets = new Insets(0, 0, 5, 0);
		gbc_sms1.gridx = 0;
		gbc_sms1.gridy = 2;
		panelMServisi.add(sms1, gbc_sms1);
		lblServisi.add(sms1);
		
		JLabel lbldatumOdMS = new JLabel("Datum od");
		lbldatumOdMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lbldatumOdMS = new GridBagConstraints();
		gbc_lbldatumOdMS.insets = new Insets(0, 0, 5, 5);
		gbc_lbldatumOdMS.gridx = 0;
		gbc_lbldatumOdMS.gridy = 3;
		panelMServisi.add(lbldatumOdMS, gbc_lbldatumOdMS);
		lblServisi.add(lbldatumOdMS);
		
		DatePicker dateMSod = new DatePicker(DatePickerNew.newDateSet());
		GridBagConstraints gbc_dateMSod = new GridBagConstraints();
		gbc_dateMSod.insets = new Insets(0, 0, 5, 0);
		gbc_dateMSod.fill = GridBagConstraints.BOTH;
		gbc_dateMSod.gridx = 1;
		gbc_dateMSod.gridy = 3;
		panelMServisi.add(dateMSod, gbc_dateMSod);
		
		JLabel sms2 = new JLabel("");
		sms2.setBackground(new Color(0,153,255));
		sms2.setOpaque(true);
		GridBagConstraints gbc_sms2 = new GridBagConstraints();
		gbc_sms2.fill = GridBagConstraints.BOTH;
		gbc_sms2.gridwidth = 2;
		gbc_sms2.insets = new Insets(0, 0, 5, 0);
		gbc_sms2.gridx = 0;
		gbc_sms2.gridy = 4;
		panelMServisi.add(sms2, gbc_sms2);
		lblServisi.add(sms2);
		
		JLabel lbldatumDoMS = new JLabel("Datum do");
		lbldatumDoMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lbldatumDoMS = new GridBagConstraints();
		gbc_lbldatumDoMS.insets = new Insets(0, 0, 5, 5);
		gbc_lbldatumDoMS.gridx = 0;
		gbc_lbldatumDoMS.gridy = 5;
		panelMServisi.add(lbldatumDoMS, gbc_lbldatumDoMS);
		lblServisi.add(lbldatumDoMS);
		
		DatePicker dateMSdo = new DatePicker(DatePickerNew.newDateSet());
		dateMSdo.setDateToToday();
		GridBagConstraints gbc_dateMSdo = new GridBagConstraints();
		gbc_dateMSdo.insets = new Insets(0, 0, 5, 0);
		gbc_dateMSdo.fill = GridBagConstraints.BOTH;
		gbc_dateMSdo.gridx = 1;
		gbc_dateMSdo.gridy = 5;
		panelMServisi.add(dateMSdo, gbc_dateMSdo);
		
		JLabel sms3 = new JLabel("");
		sms3.setBackground(new Color(0,153,255));
		sms3.setOpaque(true);
		GridBagConstraints gbc_sms3 = new GridBagConstraints();
		gbc_sms3.fill = GridBagConstraints.BOTH;
		gbc_sms3.gridwidth = 2;
		gbc_sms3.insets = new Insets(0, 0, 5, 0);
		gbc_sms3.gridx = 0;
		gbc_sms3.gridy = 6;
		panelMServisi.add(sms3, gbc_sms3);
		lblServisi.add(sms3);
		
		JLabel lblModelMS = new JLabel("Model");
		lblModelMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblModelMS = new GridBagConstraints();
		gbc_lblModelMS.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelMS.gridx = 0;
		gbc_lblModelMS.gridy = 7;
		panelMServisi.add(lblModelMS, gbc_lblModelMS);
		lblServisi.add(lblModelMS);
		
		JComboBox comboModelMSp = new JComboBox();
		comboModelMSp.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W245", "Ostalo"}));
		comboModelMSp.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboModelMSp = new GridBagConstraints();
		gbc_comboModelMSp.insets = new Insets(0, 0, 5, 0);
		gbc_comboModelMSp.fill = GridBagConstraints.BOTH;
		gbc_comboModelMSp.gridx = 1;
		gbc_comboModelMSp.gridy = 7;
		panelMServisi.add(comboModelMSp, gbc_comboModelMSp);
		comboFontColorS.add(comboModelMSp);
		
		JLabel sms4 = new JLabel("");
		sms4.setBackground(new Color(0,153,255));
		sms4.setOpaque(true);
		GridBagConstraints gbc_sms4 = new GridBagConstraints();
		gbc_sms4.fill = GridBagConstraints.BOTH;
		gbc_sms4.gridwidth = 2;
		gbc_sms4.insets = new Insets(0, 0, 5, 0);
		gbc_sms4.gridx = 0;
		gbc_sms4.gridy = 8;
		panelMServisi.add(sms4, gbc_sms4);
		lblServisi.add(sms4);
		
		JLabel lblKupacMSp = new JLabel("Kupac");
		lblKupacMSp.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupacMSp = new GridBagConstraints();
		gbc_lblKupacMSp.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupacMSp.gridx = 0;
		gbc_lblKupacMSp.gridy = 9;
		panelMServisi.add(lblKupacMSp, gbc_lblKupacMSp);
		lblServisi.add(lblKupacMSp);
		
		JTextField txtKupacMS = new JTextField();
		txtKupacMS.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtKupacMS.setEnabled(true);
		txtKupacMS.setEditable(true);
		txtKupacMS.setText("");
		GridBagConstraints gbc_txtKupacMS = new GridBagConstraints();
		gbc_txtKupacMS.insets = new Insets(0, 0, 5, 0);
		gbc_txtKupacMS.fill = GridBagConstraints.BOTH;
		gbc_txtKupacMS.gridx = 1;
		gbc_txtKupacMS.gridy = 9;
		panelMServisi.add(txtKupacMS, gbc_txtKupacMS);
		txtKupacMS.setColumns(10);
		inputFontColorS.add(txtKupacMS);
		
		JLabel sms5 = new JLabel("");
		sms5.setBackground(new Color(0,153,255));
		sms5.setOpaque(true);
		GridBagConstraints gbc_sms5 = new GridBagConstraints();
		gbc_sms5.fill = GridBagConstraints.BOTH;
		gbc_sms5.gridwidth = 2;
		gbc_sms5.insets = new Insets(0, 0, 5, 0);
		gbc_sms5.gridx = 0;
		gbc_sms5.gridy = 10;
		panelMServisi.add(sms5, gbc_sms5);
		lblServisi.add(sms5);
		
		JLabel lblVoziloMSp = new JLabel("Vozilo");
		lblVoziloMSp.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVoziloMSp = new GridBagConstraints();
		gbc_lblVoziloMSp.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoziloMSp.gridx = 0;
		gbc_lblVoziloMSp.gridy = 11;
		panelMServisi.add(lblVoziloMSp, gbc_lblVoziloMSp);
		lblServisi.add(lblVoziloMSp);
		
		JTextField txtVoziloMSp = new JTextField();
		txtVoziloMSp.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_txtVoziloMSp = new GridBagConstraints();
		gbc_txtVoziloMSp.insets = new Insets(0, 0, 5, 0);
		gbc_txtVoziloMSp.fill = GridBagConstraints.BOTH;
		gbc_txtVoziloMSp.gridx = 1;
		gbc_txtVoziloMSp.gridy = 11;
		panelMServisi.add(txtVoziloMSp, gbc_txtVoziloMSp);
		txtVoziloMSp.setColumns(10);
		inputFontColorS.add(txtVoziloMSp);
		
		JLabel sms6 = new JLabel("");
		sms6.setBackground(new Color(0,153,255));
		sms6.setOpaque(true);
		GridBagConstraints gbc_sms6 = new GridBagConstraints();
		gbc_sms6.fill = GridBagConstraints.BOTH;
		gbc_sms6.gridwidth = 2;
		gbc_sms6.insets = new Insets(0, 0, 5, 0);
		gbc_sms6.gridx = 0;
		gbc_sms6.gridy = 12;
		panelMServisi.add(sms6, gbc_sms6);
		lblServisi.add(sms6);
		
		JLabel lblStatusMS = new JLabel("Status");
		lblStatusMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblStatusMS = new GridBagConstraints();
		gbc_lblStatusMS.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusMS.gridx = 0;
		gbc_lblStatusMS.gridy = 13;
		panelMServisi.add(lblStatusMS, gbc_lblStatusMS);
		lblServisi.add(lblStatusMS);
		
		JComboBox comboStatusMSp = new JComboBox();
		comboStatusMSp.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Ostalo", " "}));
		comboStatusMSp.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboStatusMSp = new GridBagConstraints();
		gbc_comboStatusMSp.insets = new Insets(0, 0, 5, 0);
		gbc_comboStatusMSp.fill = GridBagConstraints.BOTH;
		gbc_comboStatusMSp.gridx = 1;
		gbc_comboStatusMSp.gridy = 13;
		panelMServisi.add(comboStatusMSp, gbc_comboStatusMSp);
		comboFontColorS.add(comboStatusMSp);
		
		JLabel sms7 = new JLabel("");
		sms7.setBackground(new Color(0,153,255));
		sms7.setOpaque(true);
		GridBagConstraints gbc_sms7 = new GridBagConstraints();
		gbc_sms7.insets = new Insets(0, 0, 5, 0);
		gbc_sms7.fill = GridBagConstraints.BOTH;
		gbc_sms7.gridwidth = 2;
		gbc_sms7.gridx = 0;
		gbc_sms7.gridy = 14;
		panelMServisi.add(sms7, gbc_sms7);
		lblServisi.add(sms7);
		
		JLabel kom = new JLabel();
		kom.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblServisi.add(kom);
		JLabel brt = new JLabel();
		brt.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblServisi.add(brt);
		JLabel neto = new JLabel();
		neto.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblServisi.add(neto);
		JLabel razlika = new JLabel();
		razlika.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblServisi.add(razlika);
		JTable t = new JTable();
		tableFontS.add(t);
		
		
		JButton btnPbyDateMS = new JButton("Pretraga po datumu");
		btnPbyDateMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		JTable t = new JTable();
				LocalDate dOd = dateMSod.getDate();
				LocalDate dDo = dateMSdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchByDateMS(dOd, dDo, t, kom, brt, neto, razlika);
						PopupMSpretraga msPretraga = new PopupMSpretraga(t, kom, brt, neto, razlika);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
		
			}
		});
		btnPbyDateMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDateMS = new GridBagConstraints();
		gbc_btnPbyDateMS.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyDateMS.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDateMS.gridwidth = 2;
		gbc_btnPbyDateMS.gridx = 0;
		gbc_btnPbyDateMS.gridy = 15;
		panelMServisi.add(btnPbyDateMS, gbc_btnPbyDateMS);
		btnSaveFontColorS.add(btnPbyDateMS);
		
		JButton btnPbyModelMS = new JButton("Pretraga po modelu");
		btnPbyModelMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		JTable t = new JTable();
				String searchBy = "Model";
				String search = comboModelMSp.getSelectedItem().toString();
				LocalDate dOd = dateMSod.getDate();
				LocalDate dDo = dateMSdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchMS(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupMSpretraga msPretraga =  new PopupMSpretraga(t, kom, brt, neto, razlika);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyModelMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyModelMS = new GridBagConstraints();
		gbc_btnPbyModelMS.fill = GridBagConstraints.BOTH;
		gbc_btnPbyModelMS.gridwidth = 2;
		gbc_btnPbyModelMS.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyModelMS.gridx = 0;
		gbc_btnPbyModelMS.gridy = 16;
		panelMServisi.add(btnPbyModelMS, gbc_btnPbyModelMS);
		btnSaveFontColorS.add(btnPbyModelMS);
		
		JButton btnPbyKupacMS = new JButton("Pretraga po kupcu");
		btnPbyKupacMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			JTable t = new JTable();
				String searchBy = "Kupac";
				String search = txtKupacMS.getText();
				LocalDate dOd = dateMSod.getDate();
				LocalDate dDo = dateMSdo.getDate();
				
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchMS(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupMSpretraga msPretraga =  new PopupMSpretraga(t, kom, brt, neto, razlika);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyKupacMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyKupacMS = new GridBagConstraints();
		gbc_btnPbyKupacMS.fill = GridBagConstraints.BOTH;
		gbc_btnPbyKupacMS.gridwidth = 2;
		gbc_btnPbyKupacMS.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyKupacMS.gridx = 0;
		gbc_btnPbyKupacMS.gridy = 17;
		panelMServisi.add(btnPbyKupacMS, gbc_btnPbyKupacMS);
		btnSaveFontColorS.add(btnPbyKupacMS);
		
		JButton btnPbyVoziloMS = new JButton("Pretraga po vozilu");
		btnPbyVoziloMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			JTable t = new JTable();
				String searchBy = "Vozilo";
				String search = txtVoziloMSp.getText();
				LocalDate dOd = dateMSod.getDate();
				LocalDate dDo = dateMSdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchMS(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupMSpretraga msPretraga =  new PopupMSpretraga(t, kom, brt, neto, razlika);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyVoziloMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyVoziloMS = new GridBagConstraints();
		gbc_btnPbyVoziloMS.fill = GridBagConstraints.BOTH;
		gbc_btnPbyVoziloMS.gridwidth = 2;
		gbc_btnPbyVoziloMS.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyVoziloMS.gridx = 0;
		gbc_btnPbyVoziloMS.gridy = 18;
		panelMServisi.add(btnPbyVoziloMS, gbc_btnPbyVoziloMS);
		btnSaveFontColorS.add(btnPbyVoziloMS);
		
		JButton btnPbyStatusMS = new JButton("Pretraga po statusu");
		btnPbyStatusMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			JTable t = new JTable();
				String searchBy = "Status";
				String search = comboStatusMSp.getSelectedItem().toString();
				LocalDate dOd = dateMSod.getDate();
				LocalDate dDo = dateMSdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchMS(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						
						PopupMSpretraga msPretraga =  new PopupMSpretraga(t, kom, brt, neto, razlika);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyStatusMS.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyStatusMS = new GridBagConstraints();
		gbc_btnPbyStatusMS.fill = GridBagConstraints.BOTH;
		gbc_btnPbyStatusMS.gridwidth = 2;
		gbc_btnPbyStatusMS.gridx = 0;
		gbc_btnPbyStatusMS.gridy = 19;
		panelMServisi.add(btnPbyStatusMS, gbc_btnPbyStatusMS);
		btnSaveFontColorS.add(btnPbyStatusMS);
		
		/////////////////////////////////////////////////////////////********  PANEL  P O P R A V K E
		JPanel panelPopravke = new JPanel();
		GridBagConstraints gbc_panelPopravke = new GridBagConstraints();
		gbc_panelPopravke.anchor = GridBagConstraints.NORTH;
		gbc_panelPopravke.insets = new Insets(0, 0, 5, 15);
		gbc_panelPopravke.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPopravke.gridx = 1;
		gbc_panelPopravke.gridy = 0;
		servisiPanel.add(panelPopravke, gbc_panelPopravke);			////******
		GridBagLayout gbl_panelPopravke = new GridBagLayout();
		gbl_panelPopravke.columnWidths = new int[] {155, 155};
		gbl_panelPopravke.rowHeights = new int[] {50, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 45, 45, 45, 45};
		gbl_panelPopravke.columnWeights = new double[]{1.0, 1.0};
		gbl_panelPopravke.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelPopravke.setLayout(gbl_panelPopravke);
		
		JButton btnPopravkaUnos = new JButton("Unesi Popravku");
		try {
			Image img = ImageIO.read(Servisi.class.getResource("/repair.jpg"));
			btnPopravkaUnos.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		btnPopravkaUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					
							PopupPopravkeUnos popravke = new PopupPopravkeUnos();
						} catch (Exception e2) {
						
						}	
			}
		});
		btnPopravkaUnos.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_btnPopravkaUnos = new GridBagConstraints();
		gbc_btnPopravkaUnos.fill = GridBagConstraints.BOTH;
		gbc_btnPopravkaUnos.gridwidth = 2;
		gbc_btnPopravkaUnos.insets = new Insets(0, 0, 5, 0);
		gbc_btnPopravkaUnos.gridx = 0;
		gbc_btnPopravkaUnos.gridy = 0;
		panelPopravke.add(btnPopravkaUnos, gbc_btnPopravkaUnos);
		btnSaveFontColorS.add(btnPopravkaUnos);
		
		JLabel lblPretragaPopravki = new JLabel("PRETRAGA POPRAVKI");
		lblPretragaPopravki.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretragaPopravki.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblPretragaPopravki = new GridBagConstraints();
		gbc_lblPretragaPopravki.gridwidth = 2;
		gbc_lblPretragaPopravki.insets = new Insets(0, 0, 5, 0);
		gbc_lblPretragaPopravki.gridx = 0;
		gbc_lblPretragaPopravki.gridy = 1;
		panelPopravke.add(lblPretragaPopravki, gbc_lblPretragaPopravki);
		lblServisi.add(lblPretragaPopravki);
		
		JLabel sp1 = new JLabel("");
		sp1.setOpaque(true);
		sp1.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp1 = new GridBagConstraints();
		gbc_sp1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp1.gridwidth = 2;
		gbc_sp1.insets = new Insets(0, 0, 5, 0);
		gbc_sp1.gridx = 0;
		gbc_sp1.gridy = 2;
		panelPopravke.add(sp1, gbc_sp1);
		lblServisi.add(sp1);
		
		JLabel lblDatumOdPop = new JLabel("Datum od");
		lblDatumOdPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumOdPop = new GridBagConstraints();
		gbc_lblDatumOdPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumOdPop.gridx = 0;
		gbc_lblDatumOdPop.gridy = 3;
		panelPopravke.add(lblDatumOdPop, gbc_lblDatumOdPop);
		lblServisi.add(lblDatumOdPop);
		
		DatePicker datePopod = new DatePicker(DatePickerNew.newDateSet());
		datePopod.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_datePopod = new GridBagConstraints();
		gbc_datePopod.insets = new Insets(0, 0, 5, 0);
		gbc_datePopod.fill = GridBagConstraints.BOTH;
		gbc_datePopod.gridx = 1;
		gbc_datePopod.gridy = 3;
		panelPopravke.add(datePopod, gbc_datePopod);
		
		JLabel sp2 = new JLabel("");
		sp2.setOpaque(true);
		sp2.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp2 = new GridBagConstraints();
		gbc_sp2.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp2.gridwidth = 2;
		gbc_sp2.insets = new Insets(0, 0, 5, 0);
		gbc_sp2.gridx = 0;
		gbc_sp2.gridy = 4;
		panelPopravke.add(sp2, gbc_sp2);
		lblServisi.add(sp2);
		
		JLabel lblDatumDoPop = new JLabel("Datum do");
		lblDatumDoPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumDoPop = new GridBagConstraints();
		gbc_lblDatumDoPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumDoPop.gridx = 0;
		gbc_lblDatumDoPop.gridy = 5;
		panelPopravke.add(lblDatumDoPop, gbc_lblDatumDoPop);
		lblServisi.add(lblDatumDoPop);
		
		DatePicker datePopdo = new DatePicker(DatePickerNew.newDateSet());
		datePopdo.setDateToToday();
		datePopdo.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_datePopdo = new GridBagConstraints();
		gbc_datePopdo.insets = new Insets(0, 0, 5, 0);
		gbc_datePopdo.fill = GridBagConstraints.BOTH;
		gbc_datePopdo.gridx = 1;
		gbc_datePopdo.gridy = 5;
		panelPopravke.add(datePopdo, gbc_datePopdo);
		
		JLabel sp3 = new JLabel("");
		sp3.setOpaque(true);
		sp3.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp3 = new GridBagConstraints();
		gbc_sp3.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp3.gridwidth = 2;
		gbc_sp3.insets = new Insets(0, 0, 5, 0);
		gbc_sp3.gridx = 0;
		gbc_sp3.gridy = 6;
		panelPopravke.add(sp3, gbc_sp3);
		lblServisi.add(sp3);
		
		JLabel lblModelPop = new JLabel("Model");
		lblModelPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblModelPop = new GridBagConstraints();
		gbc_lblModelPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelPop.gridx = 0;
		gbc_lblModelPop.gridy = 7;
		panelPopravke.add(lblModelPop, gbc_lblModelPop);
		lblServisi.add(lblModelPop);
		
		JComboBox comboModelPop = new JComboBox();
		comboModelPop.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W245", "Ostalo"}));
		comboModelPop.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboModelPop = new GridBagConstraints();
		gbc_comboModelPop.insets = new Insets(0, 0, 5, 0);
		gbc_comboModelPop.fill = GridBagConstraints.BOTH;
		gbc_comboModelPop.gridx = 1;
		gbc_comboModelPop.gridy = 7;
		panelPopravke.add(comboModelPop, gbc_comboModelPop);
		comboFontColorS.add(comboModelPop);
		
		JLabel sp4 = new JLabel("");
		sp4.setOpaque(true);
		sp4.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp4 = new GridBagConstraints();
		gbc_sp4.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp4.gridwidth = 2;
		gbc_sp4.insets = new Insets(0, 0, 5, 0);
		gbc_sp4.gridx = 0;
		gbc_sp4.gridy = 8;
		panelPopravke.add(sp4, gbc_sp4);
		lblServisi.add(sp4);
		
		JLabel lblKupacPop = new JLabel("Kupac");
		lblKupacPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupacPop = new GridBagConstraints();
		gbc_lblKupacPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupacPop.gridx = 0;
		gbc_lblKupacPop.gridy = 9;
		panelPopravke.add(lblKupacPop, gbc_lblKupacPop);
		lblServisi.add(lblKupacPop);
		
		JTextField txtKupacPop = new JTextField();
		txtKupacPop.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtKupacPop.setText("");
		txtKupacPop.setEnabled(true);
		txtKupacPop.setEditable(true);
		txtKupacPop.setColumns(10);
		GridBagConstraints gbc_txtKupacPop = new GridBagConstraints();
		gbc_txtKupacPop.insets = new Insets(0, 0, 5, 0);
		gbc_txtKupacPop.fill = GridBagConstraints.BOTH;
		gbc_txtKupacPop.gridx = 1;
		gbc_txtKupacPop.gridy = 9;
		panelPopravke.add(txtKupacPop, gbc_txtKupacPop);
		inputFontColorS.add(txtKupacPop);
		
		JLabel sp5 = new JLabel("");
		sp5.setOpaque(true);
		sp5.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp5 = new GridBagConstraints();
		gbc_sp5.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp5.gridwidth = 2;
		gbc_sp5.insets = new Insets(0, 0, 5, 0);
		gbc_sp5.gridx = 0;
		gbc_sp5.gridy = 10;
		panelPopravke.add(sp5, gbc_sp5);
		lblServisi.add(sp5);
		
		JLabel lblDeoPop = new JLabel("Delovi");
		lblDeoPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDeoPop = new GridBagConstraints();
		gbc_lblDeoPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeoPop.gridx = 0;
		gbc_lblDeoPop.gridy = 11;
		panelPopravke.add(lblDeoPop, gbc_lblDeoPop);
		lblServisi.add(lblDeoPop);
		
		JComboBox comboDeloviPop = new JComboBox();
		comboDeloviPop.setModel(new DefaultComboBoxModel(new String[] {"Moji delovi", "Doneo kupac", "MIX", "Bez delova"}));
		comboDeloviPop.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboDeloviPop = new GridBagConstraints();
		gbc_comboDeloviPop.insets = new Insets(0, 0, 5, 0);
		gbc_comboDeloviPop.fill = GridBagConstraints.BOTH;
		gbc_comboDeloviPop.gridx = 1;
		gbc_comboDeloviPop.gridy = 11;
		panelPopravke.add(comboDeloviPop, gbc_comboDeloviPop);
		comboFontColorS.add(comboDeloviPop);
		
		JLabel sp6 = new JLabel("");
		sp6.setOpaque(true);
		sp6.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp6 = new GridBagConstraints();
		gbc_sp6.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp6.gridwidth = 2;
		gbc_sp6.insets = new Insets(0, 0, 5, 0);
		gbc_sp6.gridx = 0;
		gbc_sp6.gridy = 12;
		panelPopravke.add(sp6, gbc_sp6);
		lblServisi.add(sp6);
		
		JLabel lblStatusPop = new JLabel("Status");
		lblStatusPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblStatusPop = new GridBagConstraints();
		gbc_lblStatusPop.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusPop.gridx = 0;
		gbc_lblStatusPop.gridy = 13;
		panelPopravke.add(lblStatusPop, gbc_lblStatusPop);
		lblServisi.add(lblStatusPop);
		
		JComboBox comboStatusPop = new JComboBox();
		comboStatusPop.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Ostalo"}));
		comboStatusPop.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboStatusPop = new GridBagConstraints();
		gbc_comboStatusPop.insets = new Insets(0, 0, 5, 0);
		gbc_comboStatusPop.fill = GridBagConstraints.BOTH;
		gbc_comboStatusPop.gridx = 1;
		gbc_comboStatusPop.gridy = 13;
		panelPopravke.add(comboStatusPop, gbc_comboStatusPop);
		comboFontColorS.add(comboStatusPop);
		
		JLabel sp7 = new JLabel("");
		sp7.setOpaque(true);
		sp7.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sp7 = new GridBagConstraints();
		gbc_sp7.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp7.gridwidth = 2;
		gbc_sp7.insets = new Insets(0, 0, 5, 0);
		gbc_sp7.gridx = 0;
		gbc_sp7.gridy = 14;
		panelPopravke.add(sp7, gbc_sp7);
		lblServisi.add(sp7);
		
		JButton btnPbyDatePop = new JButton("Pretraga po datumu");
		btnPbyDatePop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate dOd = datePopod.getDate();
				LocalDate dDo = datePopdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchByDatePopravke(dOd, dDo, t, kom, brt, neto, razlika);
						PopupPopravkePretraga popravkeP = new PopupPopravkePretraga(t, kom, brt, neto, razlika);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
		
			}
		});
		btnPbyDatePop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDatePop = new GridBagConstraints();
		gbc_btnPbyDatePop.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDatePop.gridwidth = 2;
		gbc_btnPbyDatePop.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyDatePop.gridx = 0;
		gbc_btnPbyDatePop.gridy = 15;
		panelPopravke.add(btnPbyDatePop, gbc_btnPbyDatePop);
		btnSaveFontColorS.add(btnPbyDatePop);
		
		JButton btnPbyModelPop = new JButton("Pretraga po modelu");
		btnPbyModelPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Model";
				String search = comboModelPop.getSelectedItem().toString();
				LocalDate dOd = datePopod.getDate();
				LocalDate dDo = datePopdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchPopravke(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupPopravkePretraga pop = new PopupPopravkePretraga(t, kom, brt, neto, razlika);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyModelPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyModelPop = new GridBagConstraints();
		gbc_btnPbyModelPop.fill = GridBagConstraints.BOTH;
		gbc_btnPbyModelPop.gridwidth = 2;
		gbc_btnPbyModelPop.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyModelPop.gridx = 0;
		gbc_btnPbyModelPop.gridy = 16;
		panelPopravke.add(btnPbyModelPop, gbc_btnPbyModelPop);
		btnSaveFontColorS.add(btnPbyModelPop);
		
		JButton btnPbyKupacPop = new JButton("Pretraga po kupcu");
		btnPbyKupacPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Kupac";
				String search = txtKupacPop.getText();
				LocalDate dOd = datePopod.getDate();
				LocalDate dDo = datePopdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchPopravke(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupPopravkePretraga pop = new PopupPopravkePretraga(t, kom, brt, neto, razlika);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyKupacPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyKupacPop = new GridBagConstraints();
		gbc_btnPbyKupacPop.fill = GridBagConstraints.BOTH;
		gbc_btnPbyKupacPop.gridwidth = 2;
		gbc_btnPbyKupacPop.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyKupacPop.gridx = 0;
		gbc_btnPbyKupacPop.gridy = 17;
		panelPopravke.add(btnPbyKupacPop, gbc_btnPbyKupacPop);
		btnSaveFontColorS.add(btnPbyKupacPop);
		
		JButton btnPbyStatusPop = new JButton("Pretraga po statusu");
		btnPbyStatusPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Status";
				String search = comboStatusPop.getSelectedItem().toString();
				LocalDate dOd = datePopod.getDate();
				LocalDate dDo = datePopdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchPopravke(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupPopravkePretraga pop = new PopupPopravkePretraga(t, kom, brt, neto, razlika);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyStatusPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyStatusPop = new GridBagConstraints();
		gbc_btnPbyStatusPop.fill = GridBagConstraints.BOTH;
		gbc_btnPbyStatusPop.gridwidth = 2;
		gbc_btnPbyStatusPop.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyStatusPop.gridx = 0;
		gbc_btnPbyStatusPop.gridy = 18;
		panelPopravke.add(btnPbyStatusPop, gbc_btnPbyStatusPop);
		btnSaveFontColorS.add(btnPbyStatusPop);
		
		JButton btnPbyDeoPop = new JButton("Pretraga po delu");
		btnPbyDeoPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Delovi";
				String search = comboDeloviPop.getSelectedItem().toString();
				LocalDate dOd = datePopod.getDate();
				LocalDate dDo = datePopdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchPopravke(dOd, dDo, searchBy, search, t, kom, brt, neto, razlika);
						PopupPopravkePretraga pop = new PopupPopravkePretraga(t, kom, brt, neto, razlika);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyDeoPop.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDeoPop = new GridBagConstraints();
		gbc_btnPbyDeoPop.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDeoPop.gridwidth = 2;
		gbc_btnPbyDeoPop.gridx = 0;
		gbc_btnPbyDeoPop.gridy = 19;
		panelPopravke.add(btnPbyDeoPop, gbc_btnPbyDeoPop);
		btnSaveFontColorS.add(btnPbyDeoPop);
		
		//////////////////////////////////*********************** PANEL VULKANIZIRANJE
		JPanel panelVulkaniziranje = new JPanel();
		GridBagConstraints gbc_panelVulkaniziranje = new GridBagConstraints();
		gbc_panelVulkaniziranje.anchor = GridBagConstraints.NORTH;
		gbc_panelVulkaniziranje.insets = new Insets(0, 0, 5, 15);
		gbc_panelVulkaniziranje.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelVulkaniziranje.gridx = 2;
		gbc_panelVulkaniziranje.gridy = 0;
		servisiPanel.add(panelVulkaniziranje, gbc_panelVulkaniziranje);			
		GridBagLayout gbl_panelVulkaniziranje = new GridBagLayout();
		gbl_panelVulkaniziranje.columnWidths = new int[] {155, 155};
		gbl_panelVulkaniziranje.rowHeights = new int[] {50, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 45, 45, 45, 45};
		gbl_panelVulkaniziranje.columnWeights = new double[]{1.0, 1.0};
		gbl_panelVulkaniziranje.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelVulkaniziranje.setLayout(gbl_panelVulkaniziranje);
		
		JButton btnVulkUnos = new JButton("Vulkanizerska Usluga");
		try {
			Image img = ImageIO.read(Servisi.class.getResource("/tyre1.jpg"));
			btnVulkUnos.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		btnVulkUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {										
					PopupVulkUnos vulk = new PopupVulkUnos();
				} catch (Exception e2) {
				
				}	
			}
		});
		btnVulkUnos.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_btnVulkUnos = new GridBagConstraints();
		gbc_btnVulkUnos.fill = GridBagConstraints.BOTH;
		gbc_btnVulkUnos.gridwidth = 2;
		gbc_btnVulkUnos.insets = new Insets(0, 0, 5, 0);
		gbc_btnVulkUnos.gridx = 0;
		gbc_btnVulkUnos.gridy = 0;
		panelVulkaniziranje.add(btnVulkUnos, gbc_btnVulkUnos);
		btnSaveFontColorS.add(btnVulkUnos);
		
		JLabel lblPretragaVulk = new JLabel("PRETRAGA VULK. USLUGA");
		lblPretragaVulk.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretragaVulk.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblPretragaVulk = new GridBagConstraints();
		gbc_lblPretragaVulk.insets = new Insets(0, 0, 5, 0);
		gbc_lblPretragaVulk.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPretragaVulk.gridwidth = 2;
		gbc_lblPretragaVulk.gridx = 0;
		gbc_lblPretragaVulk.gridy = 1;
		panelVulkaniziranje.add(lblPretragaVulk, gbc_lblPretragaVulk);
		lblServisi.add(lblPretragaVulk);
		
		JLabel sv1 = new JLabel("");
		sv1.setOpaque(true);
		sv1.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv1 = new GridBagConstraints();
		gbc_sv1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv1.insets = new Insets(0, 0, 5, 0);
		gbc_sv1.gridwidth = 2;
		gbc_sv1.gridx = 0;
		gbc_sv1.gridy = 2;
		panelVulkaniziranje.add(sv1, gbc_sv1);
		lblServisi.add(sv1);
		
		JLabel lblDatumOdV = new JLabel("Datum od");
		lblDatumOdV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumOdV = new GridBagConstraints();
		gbc_lblDatumOdV.fill = GridBagConstraints.VERTICAL;
		gbc_lblDatumOdV.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumOdV.gridx = 0;
		gbc_lblDatumOdV.gridy = 3;
		panelVulkaniziranje.add(lblDatumOdV, gbc_lblDatumOdV);
		lblServisi.add(lblDatumOdV);
		
		DatePicker dateVod = new DatePicker(DatePickerNew.newDateSet());
		dateVod.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_dateVod = new GridBagConstraints();
		gbc_dateVod.insets = new Insets(0, 0, 5, 0);
		gbc_dateVod.fill = GridBagConstraints.BOTH;
		gbc_dateVod.gridx = 1;
		gbc_dateVod.gridy = 3;
		panelVulkaniziranje.add(dateVod, gbc_dateVod);
		
		JLabel sv2 = new JLabel("");
		sv2.setOpaque(true);
		sv2.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv2 = new GridBagConstraints();
		gbc_sv2.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv2.gridwidth = 2;
		gbc_sv2.insets = new Insets(0, 0, 5, 0);
		gbc_sv2.gridx = 0;
		gbc_sv2.gridy = 4;
		panelVulkaniziranje.add(sv2, gbc_sv2);
		lblServisi.add(sv2);
		
		JLabel lblDatumDoV = new JLabel("Datum do");
		lblDatumDoV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumDoV = new GridBagConstraints();
		gbc_lblDatumDoV.fill = GridBagConstraints.VERTICAL;
		gbc_lblDatumDoV.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumDoV.gridx = 0;
		gbc_lblDatumDoV.gridy = 5;
		panelVulkaniziranje.add(lblDatumDoV, gbc_lblDatumDoV);
		lblServisi.add(lblDatumDoV);
		
		DatePicker dateVdo = new DatePicker(DatePickerNew.newDateSet());
		dateVdo.setDateToToday();
		dateVdo.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_dateVdo = new GridBagConstraints();
		gbc_dateVdo.insets = new Insets(0, 0, 5, 0);
		gbc_dateVdo.fill = GridBagConstraints.BOTH;
		gbc_dateVdo.gridx = 1;
		gbc_dateVdo.gridy = 5;
		panelVulkaniziranje.add(dateVdo, gbc_dateVdo);
		
		JLabel sv3 = new JLabel("");
		sv3.setOpaque(true);
		sv3.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv3 = new GridBagConstraints();
		gbc_sv3.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv3.gridwidth = 2;
		gbc_sv3.insets = new Insets(0, 0, 5, 0);
		gbc_sv3.gridx = 0;
		gbc_sv3.gridy = 6;
		panelVulkaniziranje.add(sv3, gbc_sv3);
		lblServisi.add(sv3);
		
		JLabel lblVelicina = new JLabel("Velicina");
		lblVelicina.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVelicina = new GridBagConstraints();
		gbc_lblVelicina.fill = GridBagConstraints.VERTICAL;
		gbc_lblVelicina.insets = new Insets(0, 0, 5, 5);
		gbc_lblVelicina.gridx = 0;
		gbc_lblVelicina.gridy = 7;
		panelVulkaniziranje.add(lblVelicina, gbc_lblVelicina);
		lblServisi.add(lblVelicina);
		
		JComboBox comboVelicina = new JComboBox();
		comboVelicina.setModel(new DefaultComboBoxModel(new String[] {"R13", "R14", "R15", "R16", "R17", "R18", "R19", "R20", "R21", "R22", "R23", "R24"}));
		comboVelicina.setSelectedIndex(2);
		comboVelicina.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboVelicina = new GridBagConstraints();
		gbc_comboVelicina.insets = new Insets(0, 0, 5, 0);
		gbc_comboVelicina.fill = GridBagConstraints.BOTH;
		gbc_comboVelicina.gridx = 1;
		gbc_comboVelicina.gridy = 7;
		panelVulkaniziranje.add(comboVelicina, gbc_comboVelicina);
		comboFontColorS.add(comboVelicina);
		
		JLabel sv4 = new JLabel("");
		sv4.setOpaque(true);
		sv4.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv4 = new GridBagConstraints();
		gbc_sv4.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv4.insets = new Insets(0, 0, 5, 0);
		gbc_sv4.gridwidth = 2;
		gbc_sv4.gridx = 0;
		gbc_sv4.gridy = 8;
		panelVulkaniziranje.add(sv4, gbc_sv4);
		lblServisi.add(sv4);
		
		JLabel lblUsluga = new JLabel("Usluga");
		lblUsluga.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblUsluga = new GridBagConstraints();
		gbc_lblUsluga.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsluga.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsluga.gridx = 0;
		gbc_lblUsluga.gridy = 9;
		panelVulkaniziranje.add(lblUsluga, gbc_lblUsluga);
		lblServisi.add(lblUsluga);
		
		JComboBox comboUsluga = new JComboBox();
		comboUsluga.setModel(new DefaultComboBoxModel(new String[] {"Montaza", "Demontaza", "Balans", "Mont+Dem", "Komplet", "Cepovanje", "Ostalo"}));
		comboUsluga.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboUsluga = new GridBagConstraints();
		gbc_comboUsluga.insets = new Insets(0, 0, 5, 0);
		gbc_comboUsluga.fill = GridBagConstraints.BOTH;
		gbc_comboUsluga.gridx = 1;
		gbc_comboUsluga.gridy = 9;
		panelVulkaniziranje.add(comboUsluga, gbc_comboUsluga);
		comboFontColorS.add(comboUsluga);
		
		JLabel sv5 = new JLabel("");
		sv5.setOpaque(true);
		sv5.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv5 = new GridBagConstraints();
		gbc_sv5.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv5.gridwidth = 2;
		gbc_sv5.insets = new Insets(0, 0, 5, 0);
		gbc_sv5.gridx = 0;
		gbc_sv5.gridy = 10;
		panelVulkaniziranje.add(sv5, gbc_sv5);
		lblServisi.add(sv5);
		
		JLabel lblKupacV = new JLabel("Kupac");
		lblKupacV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupacV = new GridBagConstraints();
		gbc_lblKupacV.fill = GridBagConstraints.VERTICAL;
		gbc_lblKupacV.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupacV.gridx = 0;
		gbc_lblKupacV.gridy = 11;
		panelVulkaniziranje.add(lblKupacV, gbc_lblKupacV);
		lblServisi.add(lblKupacV);
		
		JTextField txtKupacV = new JTextField();
		txtKupacV.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtKupacV.setText("");
		txtKupacV.setEnabled(true);
		txtKupacV.setEditable(true);
		txtKupacV.setColumns(10);
		GridBagConstraints gbc_txtKupacV = new GridBagConstraints();
		gbc_txtKupacV.insets = new Insets(0, 0, 5, 0);
		gbc_txtKupacV.fill = GridBagConstraints.BOTH;
		gbc_txtKupacV.gridx = 1;
		gbc_txtKupacV.gridy = 11;
		panelVulkaniziranje.add(txtKupacV, gbc_txtKupacV);
		inputFontColorS.add(txtKupacV);
		
		JLabel sv6 = new JLabel("");
		sv6.setOpaque(true);
		sv6.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv6 = new GridBagConstraints();
		gbc_sv6.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv6.gridwidth = 2;
		gbc_sv6.insets = new Insets(0, 0, 5, 0);
		gbc_sv6.gridx = 0;
		gbc_sv6.gridy = 12;
		panelVulkaniziranje.add(sv6, gbc_sv6);
		lblServisi.add(sv6);
		
		JLabel lblCenaV = new JLabel("Cena");
		lblCenaV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCenaV = new GridBagConstraints();
		gbc_lblCenaV.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenaV.gridx = 0;
		gbc_lblCenaV.gridy = 13;
		panelVulkaniziranje.add(lblCenaV, gbc_lblCenaV);
		lblServisi.add(lblCenaV);
		
		txtCenaV = new JTextField();
		txtCenaV.setText("Gratis");
		txtCenaV.setFont(new Font("SansSerif", Font.ITALIC, 20));
		txtCenaV.setEnabled(true);
		txtCenaV.setEditable(false);
		txtCenaV.setColumns(10);
		GridBagConstraints gbc_txtCenaV = new GridBagConstraints();
		gbc_txtCenaV.insets = new Insets(0, 0, 5, 0);
		gbc_txtCenaV.fill = GridBagConstraints.BOTH;
		gbc_txtCenaV.gridx = 1;
		gbc_txtCenaV.gridy = 13;
		panelVulkaniziranje.add(txtCenaV, gbc_txtCenaV);
		inputFontColorS.add(txtCenaV);
		
		JLabel sv7 = new JLabel("");
		sv7.setOpaque(true);
		sv7.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sv7 = new GridBagConstraints();
		gbc_sv7.fill = GridBagConstraints.HORIZONTAL;
		gbc_sv7.gridwidth = 2;
		gbc_sv7.insets = new Insets(0, 0, 5, 5);
		gbc_sv7.gridx = 0;
		gbc_sv7.gridy = 14;
		panelVulkaniziranje.add(sv7, gbc_sv7);
		lblServisi.add(sv7);
		
		JButton btnPbyDateV = new JButton("Pretraga po datumu");
		btnPbyDateV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LocalDate dOd = dateVod.getDate();
				LocalDate dDo = dateVdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchByDateVulk(dOd, dDo, t, kom, brt);
						PopupVulkPretraga v = new PopupVulkPretraga(t, kom, brt);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnPbyDateV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDateV = new GridBagConstraints();
		gbc_btnPbyDateV.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDateV.gridwidth = 2;
		gbc_btnPbyDateV.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyDateV.gridx = 0;
		gbc_btnPbyDateV.gridy = 15;
		panelVulkaniziranje.add(btnPbyDateV, gbc_btnPbyDateV);
		btnSaveFontColorS.add(btnPbyDateV);
		
		JButton btnPbyVelicinaV = new JButton("Pretraga po velicini");
		btnPbyVelicinaV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Velicina";
				String search = comboVelicina.getSelectedItem().toString();
				LocalDate dOd = dateVod.getDate();
				LocalDate dDo = dateVdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchVulk(dOd, dDo, searchBy, search, t, kom, brt);
						PopupVulkPretraga vul = new PopupVulkPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyVelicinaV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyVelicinaV = new GridBagConstraints();
		gbc_btnPbyVelicinaV.fill = GridBagConstraints.BOTH;
		gbc_btnPbyVelicinaV.gridwidth = 2;
		gbc_btnPbyVelicinaV.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyVelicinaV.gridx = 0;
		gbc_btnPbyVelicinaV.gridy = 16;
		panelVulkaniziranje.add(btnPbyVelicinaV, gbc_btnPbyVelicinaV);
		btnSaveFontColorS.add(btnPbyVelicinaV);
		
		JButton btnPbyUslugaV = new JButton("Pretraga po usluzi");
		btnPbyUslugaV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Usluga";
				String search = comboUsluga.getSelectedItem().toString();
				LocalDate dOd = dateVod.getDate();
				LocalDate dDo = dateVdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchVulk(dOd, dDo, searchBy, search, t, kom, brt);
						PopupVulkPretraga vul = new PopupVulkPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyUslugaV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyUslugaV = new GridBagConstraints();
		gbc_btnPbyUslugaV.fill = GridBagConstraints.BOTH;
		gbc_btnPbyUslugaV.gridwidth = 2;
		gbc_btnPbyUslugaV.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyUslugaV.gridx = 0;
		gbc_btnPbyUslugaV.gridy = 17;
		panelVulkaniziranje.add(btnPbyUslugaV, gbc_btnPbyUslugaV);
		btnSaveFontColorS.add(btnPbyUslugaV);
		
		JButton btnPbyKupcuV = new JButton("Pretraga po kupcu");
		btnPbyKupcuV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Kupac";
				String search = txtKupacV.getText();
				LocalDate dOd = dateVod.getDate();
				LocalDate dDo = dateVdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchVulk(dOd, dDo, searchBy, search, t, kom, brt);
						PopupVulkPretraga vul = new PopupVulkPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyKupcuV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyKupcuV = new GridBagConstraints();
		gbc_btnPbyKupcuV.fill = GridBagConstraints.BOTH;
		gbc_btnPbyKupcuV.gridwidth = 2;
		gbc_btnPbyKupcuV.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyKupcuV.gridx = 0;
		gbc_btnPbyKupcuV.gridy = 18;
		panelVulkaniziranje.add(btnPbyKupcuV, gbc_btnPbyKupcuV);
		btnSaveFontColorS.add(btnPbyKupcuV);
		
		JButton btnPbyGratisV = new JButton("Pretraga gratis usluga");
		btnPbyGratisV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchBy = "Cena";				
				LocalDate dOd = dateVod.getDate();
				LocalDate dDo = dateVdo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchVulkGratis(dOd, dDo, searchBy, t, kom, brt);
						PopupVulkPretraga vul = new PopupVulkPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyGratisV.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyGratisV = new GridBagConstraints();
		gbc_btnPbyGratisV.fill = GridBagConstraints.BOTH;
		gbc_btnPbyGratisV.gridwidth = 2;
		gbc_btnPbyGratisV.gridx = 0;
		gbc_btnPbyGratisV.gridy = 19;
		panelVulkaniziranje.add(btnPbyGratisV, gbc_btnPbyGratisV);
		btnSaveFontColorS.add(btnPbyGratisV);
		
							///////////////*******************************PANEL NABAVKA
		
		JPanel panelNabavka = new JPanel();
		GridBagConstraints gbc_panelNabavka = new GridBagConstraints();
		gbc_panelNabavka.anchor = GridBagConstraints.NORTH;
		gbc_panelNabavka.insets = new Insets(0, 0, 5, 0);
		gbc_panelNabavka.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNabavka.gridx = 3;
		gbc_panelNabavka.gridy = 0;
		servisiPanel.add(panelNabavka, gbc_panelNabavka);				///**********
		GridBagLayout gbl_panelNabavka = new GridBagLayout();
		gbl_panelNabavka.columnWidths = new int[] {155, 155};
		gbl_panelNabavka.rowHeights = new int[] {50, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 3, 45, 45, 45, 45, 45};
		gbl_panelNabavka.columnWeights = new double[]{1.0, 1.0};
		gbl_panelNabavka.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelNabavka.setLayout(gbl_panelNabavka);
		
		JButton btnNabavkaUnos = new JButton("Unesi Nabavku");
		try {
			Image img = ImageIO.read(Servisi.class.getResource("/supply.jpg"));
			btnNabavkaUnos.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		btnNabavkaUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PopupNabavkaUnos pN = new PopupNabavkaUnos();
			}
		});
		btnNabavkaUnos.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_btnNabavkaUnos = new GridBagConstraints();
		gbc_btnNabavkaUnos.fill = GridBagConstraints.BOTH;
		gbc_btnNabavkaUnos.gridwidth = 2;
		gbc_btnNabavkaUnos.insets = new Insets(0, 0, 5, 0);
		gbc_btnNabavkaUnos.gridx = 0;
		gbc_btnNabavkaUnos.gridy = 0;
		panelNabavka.add(btnNabavkaUnos, gbc_btnNabavkaUnos);
		btnSaveFontColorS.add(btnNabavkaUnos);
		
		JLabel lblPretragaNabavke = new JLabel("PRETRAGA NABAVKE");
		lblPretragaNabavke.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretragaNabavke.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblPretragaNabavke = new GridBagConstraints();
		gbc_lblPretragaNabavke.insets = new Insets(0, 0, 5, 0);
		gbc_lblPretragaNabavke.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPretragaNabavke.gridwidth = 2;
		gbc_lblPretragaNabavke.gridx = 0;
		gbc_lblPretragaNabavke.gridy = 1;
		panelNabavka.add(lblPretragaNabavke, gbc_lblPretragaNabavke);
		lblServisi.add(lblPretragaNabavke);
		
		JLabel sn1 = new JLabel("");
		sn1.setOpaque(true);
		sn1.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn1 = new GridBagConstraints();
		gbc_sn1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn1.insets = new Insets(0, 0, 5, 0);
		gbc_sn1.gridwidth = 2;
		gbc_sn1.gridx = 0;
		gbc_sn1.gridy = 2;
		panelNabavka.add(sn1, gbc_sn1);
		lblServisi.add(sn1);
		
		JLabel lblDatumOdN = new JLabel("Datum od");
		lblDatumOdN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumOdN = new GridBagConstraints();
		gbc_lblDatumOdN.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumOdN.gridx = 0;
		gbc_lblDatumOdN.gridy = 3;
		panelNabavka.add(lblDatumOdN, gbc_lblDatumOdN);
		lblServisi.add(lblDatumOdN);
		
		DatePicker dateOdN = new DatePicker(DatePickerNew.newDateSet());
		dateOdN.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_dateOdN = new GridBagConstraints();
		gbc_dateOdN.insets = new Insets(0, 0, 5, 0);
		gbc_dateOdN.fill = GridBagConstraints.BOTH;
		gbc_dateOdN.gridx = 1;
		gbc_dateOdN.gridy = 3;
		panelNabavka.add(dateOdN, gbc_dateOdN);
		
		JLabel sn2 = new JLabel("");
		sn2.setOpaque(true);
		sn2.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn2 = new GridBagConstraints();
		gbc_sn2.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn2.insets = new Insets(0, 0, 5, 0);
		gbc_sn2.gridwidth = 2;
		gbc_sn2.gridx = 0;
		gbc_sn2.gridy = 4;
		panelNabavka.add(sn2, gbc_sn2);
		lblServisi.add(sn2);
		
		JLabel lblDatumDoN = new JLabel("Datum do");
		lblDatumDoN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumDoN = new GridBagConstraints();
		gbc_lblDatumDoN.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumDoN.gridx = 0;
		gbc_lblDatumDoN.gridy = 5;
		panelNabavka.add(lblDatumDoN, gbc_lblDatumDoN);
		lblServisi.add(lblDatumDoN);
		
		DatePicker dateDoN = new DatePicker(DatePickerNew.newDateSet());
		dateDoN.setDateToToday();
		dateDoN.getComponentDateTextField().setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_dateDoN = new GridBagConstraints();
		gbc_dateDoN.insets = new Insets(0, 0, 5, 0);
		gbc_dateDoN.fill = GridBagConstraints.BOTH;
		gbc_dateDoN.gridx = 1;
		gbc_dateDoN.gridy = 5;
		panelNabavka.add(dateDoN, gbc_dateDoN);
		
		JLabel sn3 = new JLabel("");
		sn3.setOpaque(true);
		sn3.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn3 = new GridBagConstraints();
		gbc_sn3.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn3.insets = new Insets(0, 0, 5, 0);
		gbc_sn3.gridwidth = 2;
		gbc_sn3.gridx = 0;
		gbc_sn3.gridy = 6;
		panelNabavka.add(sn3, gbc_sn3);
		lblServisi.add(sn3);
		
		JLabel lblVrstaN = new JLabel("Vrsta");
		lblVrstaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVrstaN = new GridBagConstraints();
		gbc_lblVrstaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblVrstaN.gridx = 0;
		gbc_lblVrstaN.gridy = 7;
		panelNabavka.add(lblVrstaN, gbc_lblVrstaN);
		lblServisi.add(lblVrstaN);
		
		JComboBox comboVrstaN = new JComboBox();
		comboVrstaN.setModel(new DefaultComboBoxModel(new String[] {"Novo", "Polovno", "Vulk. Pribor", "Ostalo"}));
		comboVrstaN.setFont(new Font("SansSerif", Font.PLAIN, 20));
		GridBagConstraints gbc_comboVrstaN = new GridBagConstraints();
		gbc_comboVrstaN.insets = new Insets(0, 0, 5, 0);
		gbc_comboVrstaN.fill = GridBagConstraints.BOTH;
		gbc_comboVrstaN.gridx = 1;
		gbc_comboVrstaN.gridy = 7;
		panelNabavka.add(comboVrstaN, gbc_comboVrstaN);
		comboFontColorS.add(comboVrstaN);
		
		JLabel sn4 = new JLabel("");
		sn4.setOpaque(true);
		sn4.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn4 = new GridBagConstraints();
		gbc_sn4.gridwidth = 2;
		gbc_sn4.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn4.insets = new Insets(0, 0, 5, 5);
		gbc_sn4.gridx = 0;
		gbc_sn4.gridy = 8;
		panelNabavka.add(sn4, gbc_sn4);
		lblServisi.add(sn4);
		
		JLabel lblNazivN = new JLabel("Naziv");
		lblNazivN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblNazivN = new GridBagConstraints();
		gbc_lblNazivN.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazivN.gridx = 0;
		gbc_lblNazivN.gridy = 9;
		panelNabavka.add(lblNazivN, gbc_lblNazivN);
		lblServisi.add(lblNazivN);
		
		JTextField txtNazivN = new JTextField();
		txtNazivN.setText("");
		txtNazivN.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtNazivN.setEnabled(true);
		txtNazivN.setEditable(true);
		txtNazivN.setColumns(10);
		GridBagConstraints gbc_txtNazivN = new GridBagConstraints();
		gbc_txtNazivN.insets = new Insets(0, 0, 5, 0);
		gbc_txtNazivN.fill = GridBagConstraints.BOTH;
		gbc_txtNazivN.gridx = 1;
		gbc_txtNazivN.gridy = 9;
		panelNabavka.add(txtNazivN, gbc_txtNazivN);
		inputFontColorS.add(txtNazivN);
		
		JLabel sn5 = new JLabel("");
		sn5.setOpaque(true);
		sn5.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn5 = new GridBagConstraints();
		gbc_sn5.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn5.insets = new Insets(0, 0, 5, 0);
		gbc_sn5.gridwidth = 2;
		gbc_sn5.gridx = 0;
		gbc_sn5.gridy = 10;
		panelNabavka.add(sn5, gbc_sn5);
		lblServisi.add(sn5);
		
		JLabel lblKomadaN = new JLabel("Komada");
		lblKomadaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKomadaN = new GridBagConstraints();
		gbc_lblKomadaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblKomadaN.gridx = 0;
		gbc_lblKomadaN.gridy = 11;
		panelNabavka.add(lblKomadaN, gbc_lblKomadaN);
		lblServisi.add(lblKomadaN);
		
		JTextField txtKomadaN = new JTextField();
		txtKomadaN.setText("");
		txtKomadaN.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtKomadaN.setEnabled(true);
		txtKomadaN.setEditable(true);
		txtKomadaN.setColumns(10);
		GridBagConstraints gbc_txtKomadaN = new GridBagConstraints();
		gbc_txtKomadaN.insets = new Insets(0, 0, 5, 0);
		gbc_txtKomadaN.fill = GridBagConstraints.BOTH;
		gbc_txtKomadaN.gridx = 1;
		gbc_txtKomadaN.gridy = 11;
		panelNabavka.add(txtKomadaN, gbc_txtKomadaN);
		inputFontColorS.add(txtKomadaN);
		
		JLabel sn6 = new JLabel("");
		sn6.setOpaque(true);
		sn6.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn6 = new GridBagConstraints();
		gbc_sn6.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn6.insets = new Insets(0, 0, 5, 0);
		gbc_sn6.gridwidth = 2;
		gbc_sn6.gridx = 0;
		gbc_sn6.gridy = 12;
		panelNabavka.add(sn6, gbc_sn6);
		lblServisi.add(sn6);
		
		JLabel lblDobavljacN = new JLabel("Dobavljac");
		lblDobavljacN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDobavljacN = new GridBagConstraints();
		gbc_lblDobavljacN.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobavljacN.gridx = 0;
		gbc_lblDobavljacN.gridy = 13;
		panelNabavka.add(lblDobavljacN, gbc_lblDobavljacN);
		lblServisi.add(lblDobavljacN);
		
		JTextField txtDobavljacN = new JTextField();
		txtDobavljacN.setText("");
		txtDobavljacN.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtDobavljacN.setEnabled(true);
		txtDobavljacN.setEditable(true);
		txtDobavljacN.setColumns(10);
		GridBagConstraints gbc_txtDobavljacN = new GridBagConstraints();
		gbc_txtDobavljacN.insets = new Insets(0, 0, 5, 0);
		gbc_txtDobavljacN.fill = GridBagConstraints.BOTH;
		gbc_txtDobavljacN.gridx = 1;
		gbc_txtDobavljacN.gridy = 13;
		panelNabavka.add(txtDobavljacN, gbc_txtDobavljacN);
		inputFontColorS.add(txtDobavljacN);
		
		JLabel sn7 = new JLabel("");
		sn7.setOpaque(true);
		sn7.setBackground(new Color(0, 153, 255));
		GridBagConstraints gbc_sn7 = new GridBagConstraints();
		gbc_sn7.insets = new Insets(0, 0, 5, 0);
		gbc_sn7.fill = GridBagConstraints.HORIZONTAL;
		gbc_sn7.gridwidth = 2;
		gbc_sn7.gridx = 0;
		gbc_sn7.gridy = 14;
		panelNabavka.add(sn7, gbc_sn7);
		lblServisi.add(sn7);
		
		JButton btnPbyDateN = new JButton("Pretraga po datumu");
		btnPbyDateN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate dOd = dateOdN.getDate();
				LocalDate dDo = dateDoN.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchByDateNab(dOd, dDo, t, kom, brt);
						PopupNabavkaPretraga nP = new PopupNabavkaPretraga(t, kom, brt);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyDateN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDateN = new GridBagConstraints();
		gbc_btnPbyDateN.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDateN.gridwidth = 2;
		gbc_btnPbyDateN.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyDateN.gridx = 0;
		gbc_btnPbyDateN.gridy = 15;
		panelNabavka.add(btnPbyDateN, gbc_btnPbyDateN);
		btnSaveFontColorS.add(btnPbyDateN);
		
		JButton btnPbyVrstaN = new JButton("Pretraga po vrsti");
		btnPbyVrstaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Vrsta";
				String search = comboVrstaN.getSelectedItem().toString();
				LocalDate dOd = dateOdN.getDate();
				LocalDate dDo = dateDoN.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchNabavka(dOd, dDo, searchBy, search, t, kom, brt);
						PopupNabavkaPretraga nP = new PopupNabavkaPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyVrstaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyVrstaN = new GridBagConstraints();
		gbc_btnPbyVrstaN.fill = GridBagConstraints.BOTH;
		gbc_btnPbyVrstaN.gridwidth = 2;
		gbc_btnPbyVrstaN.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyVrstaN.gridx = 0;
		gbc_btnPbyVrstaN.gridy = 16;
		panelNabavka.add(btnPbyVrstaN, gbc_btnPbyVrstaN);
		btnSaveFontColorS.add(btnPbyVrstaN);
		
		JButton btnPbyNazivN = new JButton("Pretraga po nazivu");
		btnPbyNazivN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Naziv";
				String search = txtNazivN.getText();
				LocalDate dOd = dateOdN.getDate();
				LocalDate dDo = dateDoN.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchNabavka(dOd, dDo, searchBy, search, t, kom, brt);
						PopupNabavkaPretraga nP = new PopupNabavkaPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyNazivN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyNazivN = new GridBagConstraints();
		gbc_btnPbyNazivN.fill = GridBagConstraints.BOTH;
		gbc_btnPbyNazivN.gridwidth = 2;
		gbc_btnPbyNazivN.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyNazivN.gridx = 0;
		gbc_btnPbyNazivN.gridy = 17;
		panelNabavka.add(btnPbyNazivN, gbc_btnPbyNazivN);
		btnSaveFontColorS.add(btnPbyNazivN);
		
		JButton btnPbyDobavljacN = new JButton("Pretraga po dobavljacu");
		btnPbyDobavljacN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchBy = "Dobavljac";
				String search = txtDobavljacN.getText();
				LocalDate dOd = dateOdN.getDate();
				LocalDate dDo = dateDoN.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						Warehouse.checkDateformat(dateTo);
						Search.searchNabavka(dOd, dDo, searchBy, search, t, kom, brt);
						PopupNabavkaPretraga nP = new PopupNabavkaPretraga(t, kom, brt);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan! Unesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPbyDobavljacN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_btnPbyDobavljacN = new GridBagConstraints();
		gbc_btnPbyDobavljacN.fill = GridBagConstraints.BOTH;
		gbc_btnPbyDobavljacN.gridwidth = 2;
		gbc_btnPbyDobavljacN.insets = new Insets(0, 0, 5, 0);
		gbc_btnPbyDobavljacN.gridx = 0;
		gbc_btnPbyDobavljacN.gridy = 18;
		panelNabavka.add(btnPbyDobavljacN, gbc_btnPbyDobavljacN);
		btnSaveFontColorS.add(btnPbyDobavljacN);
		tp.setEnabledAt(4, true);	
	}
}
