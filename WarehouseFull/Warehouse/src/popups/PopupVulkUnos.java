package popups;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.github.lgooddatepicker.components.DatePicker;

import main.Baza;
import model.DatePickerNew;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class PopupVulkUnos extends JFrame{
	
	  
	 
	private static JTextField txtCena;
	private static JTextField txtKupac;
	private static JTextArea txtNapomena;
	
	public static Connection KonekcijaKaBazi() throws ClassNotFoundException {
        String username = "sa";
        String password = "111111";
        String dbName = "GoranoParts";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;instanceName=SQLEXPRESS;"
                + "DatabaseName=" + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
           
        } catch (SQLException ex) {

        }
        return conn;
    }
	
	 public static Connection konekcioniObj;
	
	    static {
	        try {
	            konekcioniObj = KonekcijaKaBazi();
	            
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(null).log(Level.SEVERE, null, ex);
	        }
	    }
	    @SuppressWarnings("serial")
		public class FieldLimit extends PlainDocument {
			   private int limit;
			   public FieldLimit(int limit) {
			      super();
			      this.limit = limit;
			   }
			   FieldLimit(int limit, boolean upper) {
			      super();
			      this.limit = limit;
			   }
			   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			      if (str == null)
			         return;
			      if ((getLength() + str.length()) <= limit) {
			         super.insertString(offset, str, attr);
			      }
			   }
			}
	    public static boolean checkDateformat(String dateToCheck){   
	  	  /*      String rex="([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-1]{1})"+
	  	        \/([0]{1}[1-9]{1}|[1]{1}[0-2]{2})"+
	  	        "\/([1]{1}[9]{1}[5-9]{1}[0-9]{1}|[2]{1}[0]{1}([0-4]{1}"+
	  	        "[0-9]{1}|[5]{1}[0]{1}))";				*/
	  		 String rex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
	  	        return(dateToCheck.matches(rex));
	  	    }
	    public static void justNumbersAndDot(KeyEvent evtN) {
			// restrict txtField from letters/ only numbers and dot
			char c = evtN.getKeyChar();
			if(!Character.isDigit(c) && c!='.') {					// only numbers and "."
		//	if (Character.isLetter(c) && !evtN.isAltDown()) {		// only numbers and special characters
				evtN.consume();
			}
		}	  
	    
	    private boolean nullFields() {
	    	
	    	txtCena.setText("");
	    	
	    	txtKupac.setText("");
	    	
	    	txtNapomena.setText("");
	    	
	    	return true;
	    }
	    
	    
	public PopupVulkUnos() {
		
		setTitle("Unesi Vulkanizersku Uslugu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setSize(500,(int) (height*0.9));
		
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 650));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {100, 100, 100, 100};
		gbl_panel.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 45, 40, 60};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane(panel,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
	    scrollPane.setViewportView(panel);
	    scrollPane.setOpaque(true);
	    panel.revalidate();
		getContentPane().add(scrollPane);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatum = new GridBagConstraints();
		gbc_lblDatum.gridwidth = 2;
		gbc_lblDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatum.gridx = 0;
		gbc_lblDatum.gridy = 1;
		panel.add(lblDatum, gbc_lblDatum);
		
		DatePicker dateVulk = new DatePicker(DatePickerNew.newDateSet());
		dateVulk.setDateToToday();
		dateVulk.getComponentDateTextField().setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_dateVulk = new GridBagConstraints();
		gbc_dateVulk.gridwidth = 2;
		gbc_dateVulk.insets = new Insets(0, 0, 5, 0);
		gbc_dateVulk.fill = GridBagConstraints.BOTH;
		gbc_dateVulk.gridx = 2;
		gbc_dateVulk.gridy = 1;
		panel.add(dateVulk, gbc_dateVulk);
		
		JLabel lblVelicina = new JLabel("Velicina (R)");
		lblVelicina.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVelicina = new GridBagConstraints();
		gbc_lblVelicina.gridwidth = 2;
		gbc_lblVelicina.insets = new Insets(0, 0, 5, 5);
		gbc_lblVelicina.gridx = 0;
		gbc_lblVelicina.gridy = 2;
		panel.add(lblVelicina, gbc_lblVelicina);
		
		JComboBox comboVelicina = new JComboBox();
		comboVelicina.setModel(new DefaultComboBoxModel(new String[] {"R13", "R14", "R15", "R16", "R17", "R18", "R19", "R20", "R21", "R22", "R23", "R24"}));
		comboVelicina.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboVelicina.setSelectedIndex(2);
		GridBagConstraints gbc_comboVelicina = new GridBagConstraints();
		gbc_comboVelicina.gridwidth = 2;
		gbc_comboVelicina.insets = new Insets(0, 0, 5, 0);
		gbc_comboVelicina.fill = GridBagConstraints.BOTH;
		gbc_comboVelicina.gridx = 2;
		gbc_comboVelicina.gridy = 2;
		panel.add(comboVelicina, gbc_comboVelicina);
		
		JLabel lblkomada = new JLabel("Komada");
		lblkomada.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblkomada = new GridBagConstraints();
		gbc_lblkomada.gridwidth = 2;
		gbc_lblkomada.insets = new Insets(0, 0, 5, 5);
		gbc_lblkomada.gridx = 0;
		gbc_lblkomada.gridy = 3;
		panel.add(lblkomada, gbc_lblkomada);
		
		JComboBox comboKomada = new JComboBox();
		comboKomada.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboKomada.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboKomada = new GridBagConstraints();
		gbc_comboKomada.gridwidth = 2;
		gbc_comboKomada.insets = new Insets(0, 0, 5, 0);
		gbc_comboKomada.fill = GridBagConstraints.BOTH;
		gbc_comboKomada.gridx = 2;
		gbc_comboKomada.gridy = 3;
		panel.add(comboKomada, gbc_comboKomada);
		
		txtKupac = new JTextField();
		txtKupac.setDocument(new FieldLimit(30));
		txtKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtKupac.setColumns(10);
		
		JLabel lblUsluga = new JLabel("Usluga");
		lblUsluga.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblUsluga = new GridBagConstraints();
		gbc_lblUsluga.gridwidth = 2;
		gbc_lblUsluga.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsluga.gridx = 0;
		gbc_lblUsluga.gridy = 4;
		panel.add(lblUsluga, gbc_lblUsluga);
		
		JComboBox comboUsluga = new JComboBox();
		comboUsluga.setModel(new DefaultComboBoxModel(new String[] {"Montaza", "Demontaza", "Balans", "Mont+Dem", "Komplet", "Cepovanje", "Ostalo"}));
		comboUsluga.setSelectedIndex(3);
		comboUsluga.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboUsluga = new GridBagConstraints();
		gbc_comboUsluga.gridwidth = 2;
		gbc_comboUsluga.insets = new Insets(0, 0, 5, 0);
		gbc_comboUsluga.fill = GridBagConstraints.BOTH;
		gbc_comboUsluga.gridx = 2;
		gbc_comboUsluga.gridy = 4;
		panel.add(comboUsluga, gbc_comboUsluga);
		
		JLabel lblKupac = new JLabel("Kupac");
		lblKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupac = new GridBagConstraints();
		gbc_lblKupac.gridwidth = 2;
		gbc_lblKupac.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupac.gridx = 0;
		gbc_lblKupac.gridy = 5;
		panel.add(lblKupac, gbc_lblKupac);
		
		GridBagConstraints gbc_txtKupac = new GridBagConstraints();
		gbc_txtKupac.gridwidth = 2;
		gbc_txtKupac.insets = new Insets(0, 0, 5, 0);
		gbc_txtKupac.fill = GridBagConstraints.BOTH;
		gbc_txtKupac.gridx = 2;
		gbc_txtKupac.gridy = 5;
		panel.add(txtKupac, gbc_txtKupac);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 6;
		panel.add(lblStatus, gbc_lblStatus);
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Ostalo"}));
		comboStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboStatus = new GridBagConstraints();
		gbc_comboStatus.gridwidth = 2;
		gbc_comboStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboStatus.fill = GridBagConstraints.BOTH;
		gbc_comboStatus.gridx = 2;
		gbc_comboStatus.gridy = 6;
		panel.add(comboStatus, gbc_comboStatus);
		
		JLabel lblCena = new JLabel("Cena ");
		lblCena.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCena = new GridBagConstraints();
		gbc_lblCena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCena.gridwidth = 2;
		gbc_lblCena.gridx = 0;
		gbc_lblCena.gridy = 7;
		panel.add(lblCena, gbc_lblCena);
		
		txtCena = new JTextField();
		txtCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {
				justNumbersAndDot(evtN);
			}
		});
		txtCena.setDocument(new FieldLimit(7));
		txtCena.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_txtCena = new GridBagConstraints();
		gbc_txtCena.gridwidth = 2;
		gbc_txtCena.insets = new Insets(0, 0, 5, 0);
		gbc_txtCena.fill = GridBagConstraints.BOTH;
		gbc_txtCena.gridx = 2;
		gbc_txtCena.gridy = 7;
		panel.add(txtCena, gbc_txtCena);
		txtCena.setColumns(10);
		
		JButton btnSacuvaj = new JButton("Sacuvaj");
		try {
			Image img = ImageIO.read(getClass().getResource("/save.png"));
			btnSacuvaj.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {		
				LocalDate datum1 = dateVulk.getDate();					
				DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumPr = datum1.format(datumFo);							
				checkDateformat(datumPr);
				
				if (txtCena.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCena, "Unesite polje *Cena*!");	
					
				
				}else {						
						    	
					String datum = datumPr;					
	            	String velicina = comboVelicina.getSelectedItem().toString();	            	            	           		            		            	
	            	String komada = comboKomada.getSelectedItem().toString();	 
	            	String usluga = comboUsluga.getSelectedItem().toString();
	            	String kupac = txtKupac.getText();
	            	String status = comboStatus.getSelectedItem().toString();	                        	
	            	String cenaN = txtCena.getText();	            	
	            	double cena = Double.parseDouble(cenaN);	            		            	            	           	           	
	            	String napomena = txtNapomena.getText();
	            		            	
					 boolean provera;					
						try {
									provera = Baza.DodavanjeVulk(datum, velicina, komada, usluga, kupac, status, cena, napomena);
									
									nullFields();
						    if(provera == false){
						        JOptionPane.showMessageDialog(null, "Vulk. usluga nije sacuvana!", "Greska!", JOptionPane.ERROR_MESSAGE);
						    }else{
						    	JOptionPane.showMessageDialog(null, "Vulk. usluga je uspesno sacuvana!");	
						    				    	
						    }
							} catch (SQLException e) {
									
									e.printStackTrace();
								}	
							
				}				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Datum nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
			}
			
			
			}
		});
		
		JLabel lblNapomena = new JLabel("  Napomena");
		lblNapomena.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNapomena = new GridBagConstraints();
		gbc_lblNapomena.anchor = GridBagConstraints.WEST;
		gbc_lblNapomena.gridwidth = 2;
		gbc_lblNapomena.insets = new Insets(0, 0, 5, 5);
		gbc_lblNapomena.gridx = 0;
		gbc_lblNapomena.gridy = 9;
		panel.add(lblNapomena, gbc_lblNapomena);
		btnSacuvaj.setFont(new Font("SansSerif", Font.BOLD, 20));		
		
		GridBagConstraints gbc_btnSacuvaj = new GridBagConstraints();
		gbc_btnSacuvaj.fill = GridBagConstraints.BOTH;
		gbc_btnSacuvaj.gridwidth = 2;
		gbc_btnSacuvaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnSacuvaj.gridx = 2;
		gbc_btnSacuvaj.gridy = 9;
		panel.add(btnSacuvaj, gbc_btnSacuvaj);
		
		
		
		txtNapomena = new JTextArea();
		GridBagConstraints gbc_txtNapomena = new GridBagConstraints();
		gbc_txtNapomena.fill = GridBagConstraints.BOTH;
		gbc_txtNapomena.gridheight = 4;
		gbc_txtNapomena.gridwidth = 4;
		gbc_txtNapomena.insets = new Insets(0, 0, 5, 0);
		gbc_txtNapomena.gridx = 0;
		gbc_txtNapomena.gridy = 10;
		panel.add(txtNapomena, gbc_txtNapomena);
		txtNapomena.setWrapStyleWord(true);
		txtNapomena.setLineWrap(true);
		txtNapomena.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNapomena.setRows(5);
		txtNapomena.setDocument(new FieldLimit(500));
		txtNapomena.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_TAB)
                {
                    e.consume();
                    KeyboardFocusManager.
                        getCurrentKeyboardFocusManager().focusNextComponent();
                }
            }
        });
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 16;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		getRootPane().setDefaultButton(btnSacuvaj);
		
		setVisible(true);
	}

}
