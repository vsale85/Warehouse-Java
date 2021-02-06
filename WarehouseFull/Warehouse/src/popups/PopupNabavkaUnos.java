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


public class PopupNabavkaUnos extends JFrame{
	private static JTextField txtCenaN;
	private static JTextField txtDobavljac;
	private static JTextArea txtNapomenaN;
	
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
	 private JTextField txtKomN;
	 private JTextField txtNazivN;
	
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
	    	
	    	txtNazivN.setText("");
	    	txtKomN.setText("");
	    	txtDobavljac.setText("");
	    	txtCenaN.setText("");
	    	txtNapomenaN.setText("");
	    	
	    	return true;
	    }
	    
	    private boolean validateFields() {
			if (txtNazivN.getText().isEmpty()) {
				JOptionPane.showMessageDialog(txtNazivN, "Unesite naziv!");
				return false;
			}else if (txtKomN.getText().isEmpty()) {
				JOptionPane.showMessageDialog(txtKomN, "Unesite broj Komada!");
				return false;
			}else if (txtCenaN.getText().isEmpty()) {
				JOptionPane.showMessageDialog(txtCenaN, "Unesite Cenu!");
				return false;
			}
				return true;			
		}
	    
	    
	public PopupNabavkaUnos() {
		
		setTitle("Unesi Nabavku");
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
		
		JLabel lblDatumN = new JLabel("Datum");
		lblDatumN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblDatumN = new GridBagConstraints();
		gbc_lblDatumN.gridwidth = 2;
		gbc_lblDatumN.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumN.gridx = 0;
		gbc_lblDatumN.gridy = 1;
		panel.add(lblDatumN, gbc_lblDatumN);
		
		DatePicker dateNabavka = new DatePicker(DatePickerNew.newDateSet());
		dateNabavka.setDateToToday();
		dateNabavka.getComponentDateTextField().setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_dateNabavka = new GridBagConstraints();
		gbc_dateNabavka.gridwidth = 2;
		gbc_dateNabavka.insets = new Insets(0, 0, 5, 0);
		gbc_dateNabavka.fill = GridBagConstraints.BOTH;
		gbc_dateNabavka.gridx = 2;
		gbc_dateNabavka.gridy = 1;
		panel.add(dateNabavka, gbc_dateNabavka);
		
		JLabel lblVrstaN = new JLabel("Vrsta");
		lblVrstaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblVrstaN = new GridBagConstraints();
		gbc_lblVrstaN.gridwidth = 2;
		gbc_lblVrstaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblVrstaN.gridx = 0;
		gbc_lblVrstaN.gridy = 2;
		panel.add(lblVrstaN, gbc_lblVrstaN);
		
		JComboBox comboVrstaN = new JComboBox();
		comboVrstaN.setModel(new DefaultComboBoxModel(new String[] {"Novo", "Polovno", "Vulk. Pribor", "Ostalo"}));
		comboVrstaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboVrstaN.setSelectedIndex(0);
		GridBagConstraints gbc_comboVrstaN = new GridBagConstraints();
		gbc_comboVrstaN.gridwidth = 2;
		gbc_comboVrstaN.insets = new Insets(0, 0, 5, 0);
		gbc_comboVrstaN.fill = GridBagConstraints.BOTH;
		gbc_comboVrstaN.gridx = 2;
		gbc_comboVrstaN.gridy = 2;
		panel.add(comboVrstaN, gbc_comboVrstaN);
		
		JLabel lblNazivN = new JLabel("Naziv");
		lblNazivN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblNazivN = new GridBagConstraints();
		gbc_lblNazivN.gridwidth = 2;
		gbc_lblNazivN.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazivN.gridx = 0;
		gbc_lblNazivN.gridy = 3;
		panel.add(lblNazivN, gbc_lblNazivN);
		
		txtDobavljac = new JTextField();
		txtDobavljac.setDocument(new FieldLimit(30));
		txtDobavljac.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtDobavljac.setColumns(10);
		
		txtNazivN = new JTextField();
		txtNazivN.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtNazivN.setColumns(10);
		GridBagConstraints gbc_txtNazivN = new GridBagConstraints();
		gbc_txtNazivN.gridwidth = 2;
		gbc_txtNazivN.insets = new Insets(0, 0, 5, 0);
		gbc_txtNazivN.fill = GridBagConstraints.BOTH;
		gbc_txtNazivN.gridx = 2;
		gbc_txtNazivN.gridy = 3;
		panel.add(txtNazivN, gbc_txtNazivN);
		
		JLabel lblKomadaN = new JLabel("Komada");
		lblKomadaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKomadaN = new GridBagConstraints();
		gbc_lblKomadaN.gridwidth = 2;
		gbc_lblKomadaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblKomadaN.gridx = 0;
		gbc_lblKomadaN.gridy = 4;
		panel.add(lblKomadaN, gbc_lblKomadaN);
		
		txtKomN = new JTextField();
		txtKomN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {
				char c = evtN.getKeyChar();
				if(!Character.isDigit(c)) {					// only numbers 
		
					evtN.consume();
				}
			}
		});
		txtKomN.setDocument(new FieldLimit(5));
		txtKomN.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtKomN.setColumns(10);
		GridBagConstraints gbc_txtKomN = new GridBagConstraints();
		gbc_txtKomN.gridwidth = 2;
		gbc_txtKomN.insets = new Insets(0, 0, 5, 0);
		gbc_txtKomN.fill = GridBagConstraints.BOTH;
		gbc_txtKomN.gridx = 2;
		gbc_txtKomN.gridy = 4;
		panel.add(txtKomN, gbc_txtKomN);
		
		JLabel lblKupac = new JLabel("Dobavljac");
		lblKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblKupac = new GridBagConstraints();
		gbc_lblKupac.gridwidth = 2;
		gbc_lblKupac.insets = new Insets(0, 0, 5, 5);
		gbc_lblKupac.gridx = 0;
		gbc_lblKupac.gridy = 5;
		panel.add(lblKupac, gbc_lblKupac);
		
		GridBagConstraints gbc_txtDobavljac = new GridBagConstraints();
		gbc_txtDobavljac.gridwidth = 2;
		gbc_txtDobavljac.insets = new Insets(0, 0, 5, 0);
		gbc_txtDobavljac.fill = GridBagConstraints.BOTH;
		gbc_txtDobavljac.gridx = 2;
		gbc_txtDobavljac.gridy = 5;
		panel.add(txtDobavljac, gbc_txtDobavljac);
		
		JLabel lblCena = new JLabel("Cena ");
		lblCena.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_lblCena = new GridBagConstraints();
		gbc_lblCena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCena.gridwidth = 2;
		gbc_lblCena.gridx = 0;
		gbc_lblCena.gridy = 6;
		panel.add(lblCena, gbc_lblCena);
		
		
		
		txtCenaN = new JTextField();
		txtCenaN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {
				justNumbersAndDot(evtN);
			}
		});
		txtCenaN.setDocument(new FieldLimit(7));
		txtCenaN.setFont(new Font("SansSerif", Font.BOLD, 20));
		GridBagConstraints gbc_txtCenaN = new GridBagConstraints();
		gbc_txtCenaN.gridwidth = 2;
		gbc_txtCenaN.insets = new Insets(0, 0, 5, 0);
		gbc_txtCenaN.fill = GridBagConstraints.BOTH;
		gbc_txtCenaN.gridx = 2;
		gbc_txtCenaN.gridy = 6;
		panel.add(txtCenaN, gbc_txtCenaN);
		txtCenaN.setColumns(10);
		
		JLabel lblNapomenaN = new JLabel("  Napomena");
		lblNapomenaN.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNapomenaN = new GridBagConstraints();
		gbc_lblNapomenaN.anchor = GridBagConstraints.WEST;
		gbc_lblNapomenaN.gridwidth = 2;
		gbc_lblNapomenaN.insets = new Insets(0, 0, 5, 5);
		gbc_lblNapomenaN.gridx = 0;
		gbc_lblNapomenaN.gridy = 9;
		panel.add(lblNapomenaN, gbc_lblNapomenaN);		
		
		txtNapomenaN = new JTextArea();
		GridBagConstraints gbc_txtNapomenaN = new GridBagConstraints();
		gbc_txtNapomenaN.fill = GridBagConstraints.BOTH;
		gbc_txtNapomenaN.gridheight = 4;
		gbc_txtNapomenaN.gridwidth = 4;
		gbc_txtNapomenaN.insets = new Insets(0, 0, 5, 0);
		gbc_txtNapomenaN.gridx = 0;
		gbc_txtNapomenaN.gridy = 10;
		panel.add(txtNapomenaN, gbc_txtNapomenaN);
		txtNapomenaN.setWrapStyleWord(true);
		txtNapomenaN.setLineWrap(true);
		txtNapomenaN.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNapomenaN.setRows(5);
		txtNapomenaN.setDocument(new FieldLimit(500));
		txtNapomenaN.addKeyListener(new KeyAdapter()
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
		
		JButton btnSacuvajN = new JButton("Sacuvaj");
		btnSacuvajN.setFont(new Font("SansSerif", Font.BOLD, 20));		
		try {
			Image img = ImageIO.read(getClass().getResource("/save.png"));
			btnSacuvajN.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnSacuvajN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {		
				LocalDate datum1 = dateNabavka.getDate();					
				DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumPr = datum1.format(datumFo);							
				checkDateformat(datumPr);
				
				if (validateFields()) {
					
					String datum = datumPr;						            	                        	
	            	String vrsta = comboVrstaN.getSelectedItem().toString();
	            	String naziv = txtNazivN.getText();
	            	String komada = txtKomN.getText();
	            	String dobavljac = txtDobavljac.getText();
	            	String cenaN = txtCenaN.getText();
	            	double cena = Double.parseDouble(cenaN);	            		            	            	           	           	
	            	String napomena = txtNapomenaN.getText();
	            		            	
					 boolean provera;					
						try {
									provera = Baza.DodavanjeNabavka(datum, vrsta, naziv, komada, dobavljac, cena, napomena);
									
									nullFields();
						    if(provera == false){
						        JOptionPane.showMessageDialog(null, "Nabavka nije sacuvana!", "Greska!", JOptionPane.ERROR_MESSAGE);
						    }else{
						    	JOptionPane.showMessageDialog(null, "Nabavka je uspesno sacuvana!");	
						    				    	
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
		GridBagConstraints gbc_btnSacuvajN = new GridBagConstraints();
		gbc_btnSacuvajN.fill = GridBagConstraints.BOTH;
		gbc_btnSacuvajN.gridwidth = 2;
		gbc_btnSacuvajN.insets = new Insets(0, 0, 5, 0);
		gbc_btnSacuvajN.gridx = 2;
		gbc_btnSacuvajN.gridy = 9;
		panel.add(btnSacuvajN, gbc_btnSacuvajN);
		getRootPane().setDefaultButton(btnSacuvajN);
		
		
		setVisible(true);
	}

}
