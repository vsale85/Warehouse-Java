package popups;

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
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class PopupPopravkeUnos extends JFrame{
	private static JTextField txtKupac;
	private static JTextField txtCenabrt;
	private static JTextField txtCenaNeto;
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
	 private final ButtonGroup gDelovi = new ButtonGroup();
	

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
	    private boolean zamenjenoCheck() {
	    	ButtonModel bU = gDelovi.getSelection();
	    	
	    	if (bU == null) {
	    		JOptionPane.showMessageDialog(null, "Izaberi vrstu delova...");
	    		return false;
			
			}return true;
					
	    }
	    
	    private boolean nullFields() {
	    	
	    	txtKupac.setText("");
	    	
	    	txtCenabrt.setText("");
	    	txtCenaNeto.setText("");
	    	
	    		    	
	    	gDelovi.clearSelection();
	    	
	    	
	    	
	    	txtNapomena.setText("");
	    	
	    	return true;
	    }
	    
	    
	public PopupPopravkeUnos() {
		
		setTitle("Unesi Popravku");
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
		gbl_panel.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 45,40,60};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
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
		gbc_lblDatum.gridy = 0;
		panel.add(lblDatum, gbc_lblDatum);
		
		DatePicker datePicker = new DatePicker(DatePickerNew.newDateSet());
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.gridwidth = 2;
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.fill = GridBagConstraints.BOTH;
		gbc_datePicker.gridx = 2;
		gbc_datePicker.gridy = 0;
		panel.add(datePicker, gbc_datePicker);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.gridwidth = 2;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		panel.add(lblModel, gbc_lblModel);
		
		JComboBox comboModel = new JComboBox();
		comboModel.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W245", "Ostalo"}));
		comboModel.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboModel = new GridBagConstraints();
		gbc_comboModel.gridwidth = 2;
		gbc_comboModel.insets = new Insets(0, 0, 5, 0);
		gbc_comboModel.fill = GridBagConstraints.BOTH;
		gbc_comboModel.gridx = 2;
		gbc_comboModel.gridy = 1;
		panel.add(comboModel, gbc_comboModel);
		
		JLabel lblKupac = new JLabel("Kupac");
		lblKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupac = new GridBagConstraints();
		gbc_lblKupac.gridwidth = 2;
		gbc_lblKupac.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupac.gridx = 0;
		gbc_lblKupac.gridy = 2;
		panel.add(lblKupac, gbc_lblKupac);
		
		txtKupac = new JTextField();
		txtKupac.setDocument(new FieldLimit(30));
		txtKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_txtKupac = new GridBagConstraints();
		gbc_txtKupac.gridwidth = 2;
		gbc_txtKupac.insets = new Insets(0, 0, 5, 0);
		gbc_txtKupac.fill = GridBagConstraints.BOTH;
		gbc_txtKupac.gridx = 2;
		gbc_txtKupac.gridy = 2;
		panel.add(txtKupac, gbc_txtKupac);
		txtKupac.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 3;
		panel.add(lblStatus, gbc_lblStatus);
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije placeno", "Ostalo"}));
		comboStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboStatus = new GridBagConstraints();
		gbc_comboStatus.gridwidth = 2;
		gbc_comboStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboStatus.fill = GridBagConstraints.BOTH;
		gbc_comboStatus.gridx = 2;
		gbc_comboStatus.gridy = 3;
		panel.add(comboStatus, gbc_comboStatus);
		
		JLabel lblCenaBruto = new JLabel("Cena bruto");
		lblCenaBruto.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCenaBruto = new GridBagConstraints();
		gbc_lblCenaBruto.gridwidth = 2;
		gbc_lblCenaBruto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenaBruto.gridx = 0;
		gbc_lblCenaBruto.gridy = 4;
		panel.add(lblCenaBruto, gbc_lblCenaBruto);
		
		txtCenabrt = new JTextField();
		txtCenabrt.setDocument(new FieldLimit(6));
		txtCenabrt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				justNumbersAndDot(e);
			}
		});
		txtCenabrt.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCenabrt.setColumns(10);
		GridBagConstraints gbc_txtCenabrt = new GridBagConstraints();
		gbc_txtCenabrt.gridwidth = 2;
		gbc_txtCenabrt.insets = new Insets(0, 0, 5, 0);
		gbc_txtCenabrt.fill = GridBagConstraints.BOTH;
		gbc_txtCenabrt.gridx = 2;
		gbc_txtCenabrt.gridy = 4;
		panel.add(txtCenabrt, gbc_txtCenabrt);
		
		JLabel lblCenaNeto = new JLabel("Cena neto");
		lblCenaNeto.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCenaNeto = new GridBagConstraints();
		gbc_lblCenaNeto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenaNeto.gridwidth = 2;
		gbc_lblCenaNeto.gridx = 0;
		gbc_lblCenaNeto.gridy = 5;
		panel.add(lblCenaNeto, gbc_lblCenaNeto);
		
		txtCenaNeto = new JTextField();
		txtCenaNeto.setDocument(new FieldLimit(6));
		txtCenaNeto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				justNumbersAndDot(e);
			}
		});
		txtCenaNeto.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCenaNeto.setColumns(10);
		GridBagConstraints gbc_txtCenaNeto = new GridBagConstraints();
		gbc_txtCenaNeto.gridwidth = 2;
		gbc_txtCenaNeto.insets = new Insets(0, 0, 5, 0);
		gbc_txtCenaNeto.fill = GridBagConstraints.BOTH;
		gbc_txtCenaNeto.gridx = 2;
		gbc_txtCenaNeto.gridy = 5;
		panel.add(txtCenaNeto, gbc_txtCenaNeto);
		
		JLabel lblDelovi = new JLabel("Delovi -");
		lblDelovi.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDelovi = new GridBagConstraints();
		gbc_lblDelovi.gridwidth = 2;
		gbc_lblDelovi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelovi.gridx = 0;
		gbc_lblDelovi.gridy = 6;
		panel.add(lblDelovi, gbc_lblDelovi);
		
		JRadioButton rbMojiDelovi = new JRadioButton("Moji delovi");
		gDelovi.add(rbMojiDelovi);
		rbMojiDelovi.setActionCommand("Moji Delovi");
		rbMojiDelovi.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbMojiDelovi = new GridBagConstraints();
		gbc_rbMojiDelovi.anchor = GridBagConstraints.WEST;
		gbc_rbMojiDelovi.gridwidth = 2;
		gbc_rbMojiDelovi.fill = GridBagConstraints.VERTICAL;
		gbc_rbMojiDelovi.insets = new Insets(0, 0, 5, 5);
		gbc_rbMojiDelovi.gridx = 2;
		gbc_rbMojiDelovi.gridy = 6;
		panel.add(rbMojiDelovi, gbc_rbMojiDelovi);
		
		JRadioButton rbDoneoKupac = new JRadioButton("Doneo kupac");
		gDelovi.add(rbDoneoKupac);
		rbDoneoKupac.setActionCommand("Doneo kupac");
		rbDoneoKupac.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbDoneoKupac = new GridBagConstraints();
		gbc_rbDoneoKupac.anchor = GridBagConstraints.WEST;
		gbc_rbDoneoKupac.gridwidth = 2;
		gbc_rbDoneoKupac.insets = new Insets(0, 0, 5, 5);
		gbc_rbDoneoKupac.gridx = 2;
		gbc_rbDoneoKupac.gridy = 7;
		panel.add(rbDoneoKupac, gbc_rbDoneoKupac);
		
		JRadioButton rbMix = new JRadioButton("MIX");
		gDelovi.add(rbMix);
		rbMix.setActionCommand("MIX");
		rbMix.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbMix = new GridBagConstraints();
		gbc_rbMix.anchor = GridBagConstraints.WEST;
		gbc_rbMix.gridwidth = 2;
		gbc_rbMix.insets = new Insets(0, 0, 5, 5);
		gbc_rbMix.gridx = 2;
		gbc_rbMix.gridy = 8;
		panel.add(rbMix, gbc_rbMix);
		
		JRadioButton rbBezDelova = new JRadioButton("Bez delova");
		gDelovi.add(rbBezDelova);
		rbBezDelova.setActionCommand("Bez delova");
		rbBezDelova.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbBezDelova = new GridBagConstraints();
		gbc_rbBezDelova.anchor = GridBagConstraints.WEST;
		gbc_rbBezDelova.gridwidth = 2;
		gbc_rbBezDelova.insets = new Insets(0, 0, 5, 5);
		gbc_rbBezDelova.gridx = 2;
		gbc_rbBezDelova.gridy = 9;
		panel.add(rbBezDelova, gbc_rbBezDelova);
		
		JLabel lblNapomena = new JLabel("  Napomena");
		lblNapomena.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNapomena = new GridBagConstraints();
		gbc_lblNapomena.anchor = GridBagConstraints.WEST;
		gbc_lblNapomena.gridwidth = 2;
		gbc_lblNapomena.insets = new Insets(0, 0, 5, 5);
		gbc_lblNapomena.gridx = 0;
		gbc_lblNapomena.gridy = 10;
		panel.add(lblNapomena, gbc_lblNapomena);
		
		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {		
				LocalDate datum1 = datePicker.getDate();					
				DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumPr = datum1.format(datumFo);							
				checkDateformat(datumPr);
				
				if (txtKupac.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenaNeto, "Unesite polje *Kupac*!");	
					
				}else if (txtCenabrt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenabrt, "Unesite Bruto cenu!");
					
				}else if (txtCenaNeto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenaNeto, "Unesite Neto cenu!");
					
				}else if(zamenjenoCheck()){						
						    	
					String datum = datumPr;					
	            	String model = comboModel.getSelectedItem().toString();	            
	            	String kupac = txtKupac.getText();	            		            		            	
	            	String status = comboStatus.getSelectedItem().toString();	            	
	            	String cenaB = txtCenabrt.getText();	            	
	            	double cenaBrt = Double.parseDouble(cenaB);	            	
	            	String cenaN = txtCenaNeto.getText();	            	
	            	double cenaNeto = Double.parseDouble(cenaN);	            		            	
	            	String vrstaDelova = gDelovi.getSelection().getActionCommand();	            	           	
	            	String napomena = txtNapomena.getText();
	            		            	
					 boolean provera;					
						try {
									provera = Baza.DodavanjePopravke(datum, model, kupac, status, cenaBrt, cenaNeto, vrstaDelova, napomena);
									
									nullFields();
						    if(provera == false){
						        JOptionPane.showMessageDialog(null, "Popravka nije sacuvana!", "Greska!", JOptionPane.ERROR_MESSAGE);
						    }else{
						    	JOptionPane.showMessageDialog(null, "Popravka je uspesno sacuvana!");	
						    				    	
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
		btnSacuvaj.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		try {
			Image img = ImageIO.read(getClass().getResource("/save.png"));
			btnSacuvaj.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		GridBagConstraints gbc_btnSacuvaj = new GridBagConstraints();
		gbc_btnSacuvaj.fill = GridBagConstraints.BOTH;
		gbc_btnSacuvaj.gridwidth = 2;
		gbc_btnSacuvaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnSacuvaj.gridx = 2;
		gbc_btnSacuvaj.gridy = 10;
		panel.add(btnSacuvaj, gbc_btnSacuvaj);
		
		txtNapomena = new JTextArea();
		GridBagConstraints gbc_txtNapomena = new GridBagConstraints();
		gbc_txtNapomena.fill = GridBagConstraints.BOTH;
		gbc_txtNapomena.gridheight = 4;
		gbc_txtNapomena.gridwidth = 4;
		gbc_txtNapomena.insets = new Insets(0, 0, 5, 5);
		gbc_txtNapomena.gridx = 0;
		gbc_txtNapomena.gridy = 11;
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
		gbc_scrollPane_1.gridy = 15;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		getRootPane().setDefaultButton(btnSacuvaj);
		
		setVisible(true);
	}

}
