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


public class PopupMSunos extends JFrame{
	private static JTextField txtLitara;
	private static JTextField txtKupac;
	private static JTextField txtVozilo;
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
	 private final ButtonGroup gUlja = new ButtonGroup();
	 private final ButtonGroup gGoriva = new ButtonGroup();
	 private final ButtonGroup gVazduha = new ButtonGroup();
	 private final ButtonGroup gKabine = new ButtonGroup();
	 private JTextField txtKilometraza;
	 private JTextField txtSlZamena;

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
	    
	    public static void justNumbersAndLetters(KeyEvent evtN) {
			// restrict txtField from special characters/ only numbers
			char c = evtN.getKeyChar();
			if(!Character.isDigit(c) && !Character.isLetter(c)) {					// only numbers and letters
		
				evtN.consume();
			}
		}
	    private boolean zamenjenoCheck() {
	    	ButtonModel bU = gUlja.getSelection();
	    	ButtonModel bG = gGoriva.getSelection();
	    	ButtonModel bV = gVazduha.getSelection();
	    	ButtonModel bK = gKabine.getSelection();
	    	if (bU == null||bG==null||bV == null||bK == null) {
	    		JOptionPane.showMessageDialog(null, "Polja DA ili NE su obavezna!\n Izaberite DA ili NE...");
	    		return false;
			
			}return true;
					
	    }
	    
	    private boolean nullFields() {
	    	
	    	txtKupac.setText("");
	    	txtVozilo.setText("");
	    	txtCenabrt.setText("");
	    	txtCenaNeto.setText("");
	    	txtLitara.setText("");
	    		    	
	    	gUlja.clearSelection();
	    	gGoriva.clearSelection();
	    	gVazduha.clearSelection();
	    	gKabine.clearSelection();
	    	
	    	txtKilometraza.setText("");
	    	txtSlZamena.setText("");
	    	txtNapomena.setText("");
	    	
	    	return true;
	    }
	    
	    
	public PopupMSunos() {
		
		setTitle("Unesi Mali Servis");
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
		
		JLabel lblVozilo = new JLabel("Vozilo");
		lblVozilo.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVozilo = new GridBagConstraints();
		gbc_lblVozilo.gridwidth = 2;
		gbc_lblVozilo.insets = new Insets(0, 0, 5, 5);
		gbc_lblVozilo.gridx = 0;
		gbc_lblVozilo.gridy = 3;
		panel.add(lblVozilo, gbc_lblVozilo);
		
		txtVozilo = new JTextField();
		txtVozilo.setDocument(new FieldLimit(8));
		txtVozilo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				justNumbersAndLetters(arg0);
			}
		});
		txtVozilo.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtVozilo.setColumns(10);
		GridBagConstraints gbc_txtVozilo = new GridBagConstraints();
		gbc_txtVozilo.gridwidth = 2;
		gbc_txtVozilo.insets = new Insets(0, 0, 5, 0);
		gbc_txtVozilo.fill = GridBagConstraints.BOTH;
		gbc_txtVozilo.gridx = 2;
		gbc_txtVozilo.gridy = 3;
		panel.add(txtVozilo, gbc_txtVozilo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 4;
		panel.add(lblStatus, gbc_lblStatus);
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije placeno", "Ostalo"}));
		comboStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboStatus = new GridBagConstraints();
		gbc_comboStatus.gridwidth = 2;
		gbc_comboStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboStatus.fill = GridBagConstraints.BOTH;
		gbc_comboStatus.gridx = 2;
		gbc_comboStatus.gridy = 4;
		panel.add(comboStatus, gbc_comboStatus);
		
		JLabel lblCenaBruto = new JLabel("Cena bruto");
		lblCenaBruto.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCenaBruto = new GridBagConstraints();
		gbc_lblCenaBruto.gridwidth = 2;
		gbc_lblCenaBruto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenaBruto.gridx = 0;
		gbc_lblCenaBruto.gridy = 5;
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
		gbc_txtCenabrt.gridy = 5;
		panel.add(txtCenabrt, gbc_txtCenabrt);
		
		JLabel lblCenaNeto = new JLabel("Cena neto");
		lblCenaNeto.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCenaNeto = new GridBagConstraints();
		gbc_lblCenaNeto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenaNeto.gridwidth = 2;
		gbc_lblCenaNeto.gridx = 0;
		gbc_lblCenaNeto.gridy = 6;
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
		gbc_txtCenaNeto.gridy = 6;
		panel.add(txtCenaNeto, gbc_txtCenaNeto);
		
		JLabel lblZamenjeno = new JLabel("Zamenjeno");
		lblZamenjeno.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblZamenjeno = new GridBagConstraints();
		gbc_lblZamenjeno.insets = new Insets(0, 0, 5, 0);
		gbc_lblZamenjeno.gridwidth = 4;
		gbc_lblZamenjeno.gridx = 0;
		gbc_lblZamenjeno.gridy = 7;
		panel.add(lblZamenjeno, gbc_lblZamenjeno);
		
		JLabel lblUljevrsta = new JLabel("Ulje(vrsta)");
		lblUljevrsta.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblUljevrsta = new GridBagConstraints();
		gbc_lblUljevrsta.insets = new Insets(0, 0, 5, 5);
		gbc_lblUljevrsta.anchor = GridBagConstraints.EAST;
		gbc_lblUljevrsta.gridx = 0;
		gbc_lblUljevrsta.gridy = 8;
		panel.add(lblUljevrsta, gbc_lblUljevrsta);
		
		JComboBox comboUljeVrsta = new JComboBox();
		comboUljeVrsta.setModel(new DefaultComboBoxModel(new String[] {"10w 40", "5w 40"}));
		comboUljeVrsta.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_comboUljeVrsta = new GridBagConstraints();
		gbc_comboUljeVrsta.insets = new Insets(0, 0, 5, 5);
		gbc_comboUljeVrsta.fill = GridBagConstraints.BOTH;
		gbc_comboUljeVrsta.gridx = 1;
		gbc_comboUljeVrsta.gridy = 8;
		panel.add(comboUljeVrsta, gbc_comboUljeVrsta);
		
		JLabel lblLitara = new JLabel("Litara");
		lblLitara.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblLitara = new GridBagConstraints();
		gbc_lblLitara.insets = new Insets(0, 0, 5, 5);
		gbc_lblLitara.anchor = GridBagConstraints.EAST;
		gbc_lblLitara.gridx = 2;
		gbc_lblLitara.gridy = 8;
		panel.add(lblLitara, gbc_lblLitara);
		
		txtLitara = new JTextField();
		txtLitara.setDocument(new FieldLimit(4));
		txtLitara.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				justNumbersAndDot(e);
			}
		});
		txtLitara.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_txtLitara = new GridBagConstraints();
		gbc_txtLitara.insets = new Insets(0, 0, 5, 0);
		gbc_txtLitara.fill = GridBagConstraints.BOTH;
		gbc_txtLitara.gridx = 3;
		gbc_txtLitara.gridy = 8;
		panel.add(txtLitara, gbc_txtLitara);
		txtLitara.setColumns(10);
		
		JLabel lblFilter = new JLabel("Filter -");
		lblFilter.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 0;
		gbc_lblFilter.gridy = 9;
		panel.add(lblFilter, gbc_lblFilter);
		
		JLabel lblUlja = new JLabel("Ulja");
		lblUlja.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblUlja = new GridBagConstraints();
		gbc_lblUlja.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlja.gridx = 1;
		gbc_lblUlja.gridy = 9;
		panel.add(lblUlja, gbc_lblUlja);
		
		JRadioButton rbUljeDa = new JRadioButton("DA");
		rbUljeDa.setActionCommand("DA");
		gUlja.add(rbUljeDa);
		rbUljeDa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbUljeDa = new GridBagConstraints();
		gbc_rbUljeDa.fill = GridBagConstraints.VERTICAL;
		gbc_rbUljeDa.insets = new Insets(0, 0, 5, 5);
		gbc_rbUljeDa.gridx = 2;
		gbc_rbUljeDa.gridy = 9;
		panel.add(rbUljeDa, gbc_rbUljeDa);
		
		JRadioButton rbUljeNe = new JRadioButton("NE");
		rbUljeNe.setActionCommand("NE");
		gUlja.add(rbUljeNe);
		rbUljeNe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbUljeNe = new GridBagConstraints();
		gbc_rbUljeNe.insets = new Insets(0, 0, 5, 0);
		gbc_rbUljeNe.gridx = 3;
		gbc_rbUljeNe.gridy = 9;
		panel.add(rbUljeNe, gbc_rbUljeNe);
		
		JLabel lblGoriva = new JLabel("Goriva");
		lblGoriva.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblGoriva = new GridBagConstraints();
		gbc_lblGoriva.insets = new Insets(0, 0, 5, 5);
		gbc_lblGoriva.gridx = 1;
		gbc_lblGoriva.gridy = 10;
		panel.add(lblGoriva, gbc_lblGoriva);
		
		JRadioButton rbGorDa = new JRadioButton("DA");
		rbGorDa.setActionCommand("DA");
		gGoriva.add(rbGorDa);
		rbGorDa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbGorDa = new GridBagConstraints();
		gbc_rbGorDa.insets = new Insets(0, 0, 5, 5);
		gbc_rbGorDa.gridx = 2;
		gbc_rbGorDa.gridy = 10;
		panel.add(rbGorDa, gbc_rbGorDa);
		
		JRadioButton rbGorNe = new JRadioButton("NE");
		rbGorNe.setActionCommand("NE");
		gGoriva.add(rbGorNe);
		rbGorNe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbGorNe = new GridBagConstraints();
		gbc_rbGorNe.insets = new Insets(0, 0, 5, 0);
		gbc_rbGorNe.gridx = 3;
		gbc_rbGorNe.gridy = 10;
		panel.add(rbGorNe, gbc_rbGorNe);
		
		JLabel lblVazduha = new JLabel("Vazduha");
		lblVazduha.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblVazduha = new GridBagConstraints();
		gbc_lblVazduha.insets = new Insets(0, 0, 5, 5);
		gbc_lblVazduha.gridx = 1;
		gbc_lblVazduha.gridy = 11;
		panel.add(lblVazduha, gbc_lblVazduha);
		
		JRadioButton rbVazDa = new JRadioButton("DA");
		rbVazDa.setActionCommand("DA");
		gVazduha.add(rbVazDa);
		rbVazDa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbVazDa = new GridBagConstraints();
		gbc_rbVazDa.insets = new Insets(0, 0, 5, 5);
		gbc_rbVazDa.gridx = 2;
		gbc_rbVazDa.gridy = 11;
		panel.add(rbVazDa, gbc_rbVazDa);
		
		JRadioButton rbVazNe = new JRadioButton("NE");
		rbVazNe.setActionCommand("NE");
		gVazduha.add(rbVazNe);
		rbVazNe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbVazNe = new GridBagConstraints();
		gbc_rbVazNe.insets = new Insets(0, 0, 5, 0);
		gbc_rbVazNe.gridx = 3;
		gbc_rbVazNe.gridy = 11;
		panel.add(rbVazNe, gbc_rbVazNe);
		
		JLabel lblKabine = new JLabel("Kabine");
		lblKabine.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblKabine = new GridBagConstraints();
		gbc_lblKabine.insets = new Insets(0, 0, 5, 5);
		gbc_lblKabine.gridx = 1;
		gbc_lblKabine.gridy = 12;
		panel.add(lblKabine, gbc_lblKabine);
		
		JRadioButton rbKabDa = new JRadioButton("DA");
		rbKabDa.setActionCommand("DA");
		gKabine.add(rbKabDa);
		rbKabDa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbKabDa = new GridBagConstraints();
		gbc_rbKabDa.insets = new Insets(0, 0, 5, 5);
		gbc_rbKabDa.gridx = 2;
		gbc_rbKabDa.gridy = 12;
		panel.add(rbKabDa, gbc_rbKabDa);
		
		JRadioButton rbKabNe = new JRadioButton("NE");
		rbKabNe.setActionCommand("NE");
		gKabine.add(rbKabNe);
		rbKabNe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GridBagConstraints gbc_rbKabNe = new GridBagConstraints();
		gbc_rbKabNe.insets = new Insets(0, 0, 5, 0);
		gbc_rbKabNe.gridx = 3;
		gbc_rbKabNe.gridy = 12;
		panel.add(rbKabNe, gbc_rbKabNe);
		
		JLabel lblKilometraza = new JLabel("Kilometraza");
		lblKilometraza.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKilometraza = new GridBagConstraints();
		gbc_lblKilometraza.anchor = GridBagConstraints.EAST;
		gbc_lblKilometraza.insets = new Insets(0, 0, 5, 5);
		gbc_lblKilometraza.gridx = 0;
		gbc_lblKilometraza.gridy = 13;
		panel.add(lblKilometraza, gbc_lblKilometraza);
		
		txtKilometraza = new JTextField();
		txtKilometraza.setDocument(new FieldLimit(7));
		txtKilometraza.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_txtKilometraza = new GridBagConstraints();
		gbc_txtKilometraza.insets = new Insets(0, 0, 5, 5);
		gbc_txtKilometraza.fill = GridBagConstraints.BOTH;
		gbc_txtKilometraza.gridx = 1;
		gbc_txtKilometraza.gridy = 13;
		panel.add(txtKilometraza, gbc_txtKilometraza);
		txtKilometraza.setColumns(10);
		
		JLabel lblSlZamena = new JLabel("Sl. zamena");
		lblSlZamena.setFont(new Font("SansSerif", Font.ITALIC, 20));
		GridBagConstraints gbc_lblSlZamena = new GridBagConstraints();
		gbc_lblSlZamena.anchor = GridBagConstraints.EAST;
		gbc_lblSlZamena.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlZamena.gridx = 2;
		gbc_lblSlZamena.gridy = 13;
		panel.add(lblSlZamena, gbc_lblSlZamena);
		
		txtSlZamena = new JTextField();
		txtSlZamena.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtSlZamena.setDocument(new FieldLimit(7));
		GridBagConstraints gbc_txtSlZamena = new GridBagConstraints();
		gbc_txtSlZamena.insets = new Insets(0, 0, 5, 0);
		gbc_txtSlZamena.fill = GridBagConstraints.BOTH;
		gbc_txtSlZamena.gridx = 3;
		gbc_txtSlZamena.gridy = 13;
		panel.add(txtSlZamena, gbc_txtSlZamena);
		txtSlZamena.setColumns(10);
		
		JLabel lblNapomena = new JLabel("  Napomena");
		lblNapomena.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNapomena = new GridBagConstraints();
		gbc_lblNapomena.anchor = GridBagConstraints.WEST;
		gbc_lblNapomena.gridwidth = 2;
		gbc_lblNapomena.insets = new Insets(0, 0, 5, 5);
		gbc_lblNapomena.gridx = 0;
		gbc_lblNapomena.gridy = 14;
		panel.add(lblNapomena, gbc_lblNapomena);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 15;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		txtNapomena = new JTextArea();
		scrollPane_1.setViewportView(txtNapomena);
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

		
		JButton btnSacuvaj = new JButton("Sacuvaj");
		btnSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {		
				LocalDate datum1 = datePicker.getDate();					
				DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumPr = datum1.format(datumFo);							
				checkDateformat(datumPr);
				
				if (txtKupac.getText().isEmpty() && txtVozilo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenaNeto, "Unesite polje *Kupac* ili polje *Vozilo*!");	
					
				}else if (txtCenabrt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenabrt, "Unesite Bruto cenu!");
					
				}else if (txtCenaNeto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenaNeto, "Unesite Neto cenu!");
				}else if(txtLitara.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCenaNeto, "Unesite polje *Litara*!");
					
				}else if(zamenjenoCheck()){	
					LocalDate datum12 = datePicker.getDate();					
					DateTimeFormatter datumFo2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumPr2 = datum12.format(datumFo2);
						    	
					String datum = datumPr;					
	            	String model = comboModel.getSelectedItem().toString();	            
	            	String kupac = txtKupac.getText();	            	
	            	String vozilo = txtVozilo.getText();	            	
	            	String status = comboStatus.getSelectedItem().toString();	            	
	            	String cenaB = txtCenabrt.getText();	            	
	            	double cenaBrt = Double.parseDouble(cenaB);	            	
	            	String cenaN = txtCenaNeto.getText();	            	
	            	double cenaNeto = Double.parseDouble(cenaN);	            	
	            	String uljeVrsta = comboUljeVrsta.getSelectedItem().toString();	            	
	            	String uljeL = txtLitara.getText();
	            	String fUlje = gUlja.getSelection().getActionCommand();
					String fGorivo = gGoriva.getSelection().getActionCommand();
					String fVazduha = gVazduha.getSelection().getActionCommand();
					String fKabine = gKabine.getSelection().getActionCommand();	    
	            	double uljeLitara = Double.parseDouble(uljeL);	            	
	            	String kilometraza = txtKilometraza.getText();	            	
	            	String slZamena =  txtSlZamena.getText();	            	
	            	String napomena = txtNapomena.getText();
	            	
					 boolean provera;					
						try {
									provera = Baza.DodavanjeMaliServis(datum, model, kupac, vozilo, status, cenaBrt, cenaNeto, uljeVrsta, uljeLitara, 
											fUlje, fGorivo, fVazduha, fKabine, kilometraza, slZamena, napomena);
									
									nullFields();
						    if(provera == false){
						        JOptionPane.showMessageDialog(null, "Mali Servis nije sacuvan!", "Greska!", JOptionPane.ERROR_MESSAGE);
						    }else{
						    	JOptionPane.showMessageDialog(null, "Mali Servis je uspesno sacuvan!");	
						    				    	
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
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		GridBagConstraints gbc_btnSacuvaj = new GridBagConstraints();
		gbc_btnSacuvaj.fill = GridBagConstraints.BOTH;
		gbc_btnSacuvaj.gridwidth = 2;
		gbc_btnSacuvaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnSacuvaj.gridx = 2;
		gbc_btnSacuvaj.gridy = 14;
		panel.add(btnSacuvaj, gbc_btnSacuvaj);
		getRootPane().setDefaultButton(btnSacuvaj);
		
		setVisible(true);
	}

}
