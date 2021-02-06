package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import com.github.lgooddatepicker.components.DatePicker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tabbs.Servisi;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import model.BackgroundPanel;
import model.DatabaseConnection;
import model.DatePickerNew;
import model.Search;
import popups.PopupUpdateP;

import javax.swing.ScrollPaneConstants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;


public class Warehouse  {

	protected static JFrame frmPartsWarehouse;
	private static JTextField txtProdato;
	private static JTextField txtCena;
	private static JTextField txtKupac;
	private JTextField txtGodina;
	private JTextField txtCenaK;
	private JTextField txtProdavac;
	private JTextField txtPretProdP;
	private JTextField txtCenaPretP;
	private JTable tableP;
	private JTextField txtKupacPretP;
	private JTextField txtPretKupProdavac;
	private JTextField txtPretKupGod;
	private JTable tableK;
	private JPopupMenu popupLookChange;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
	        UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
	
	    } catch (Exception e) {
	         System.out.println("Look and feel not started");
	         JOptionPane.showMessageDialog(null, "Look and feel didn't set ");
	    }
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warehouse window = new Warehouse();
					window.frmPartsWarehouse.setVisible(true);				
					
				} catch (Exception e) {
					 JOptionPane.showMessageDialog(null, "void run exception");
					e.printStackTrace();
				}
			}
		});			
	}					
		
	public void setJFrameVisible(boolean visible)
	{
	    frmPartsWarehouse.setVisible(visible);
	}
	
	/**
	 * Create the application.
	 */
	public Warehouse() {
		initialize();
		
	}	
	
	public static void justNumbers(KeyEvent evtN) {
		// restrict txtField from letters/ only numbers
		char c = evtN.getKeyChar();
		if(!Character.isDigit(c) && c!='.') {					// only numbers and "."
	//	if (Character.isLetter(c) && !evtN.isAltDown()) {		// only numbers and special characters
			evtN.consume();
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
	public static boolean validateFieldsP() {
		if (txtProdato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtProdato, "Unesite naziv prodatog dela!");
			return false;
		}else if (txtCena.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtCena, "Unesite prodajnu cenu!");
			return false;
		}else if (txtKupac.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtKupac, "Unesite naziv kupca!");
			return false;
		}
			return true;			
	}
	
	private boolean validateFieldsK() {
		if (txtGodina.getText().isEmpty()|| txtGodina.getText().length()<4) {
			JOptionPane.showMessageDialog(txtProdato, "Unesite godinu proizvodnje!");
			return false;
		}else if (txtProdavac.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtCena, "Unesite naziv/tel. prodavca!");
			return false;
		}else if (txtCenaK.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtKupac, "Unesite cenu!");
			return false;
		}
			return true;			
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

	public void showMessage(Component comp, String message) {
        JTextArea ta = new JTextArea(message, 1, 20);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setOpaque(false);
        ta.setBorder(null);
        ta.setEditable(false);
        ta.setFocusable(false);
        JOptionPane.showMessageDialog(comp, ta);
    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		frmPartsWarehouse = new JFrame();
		frmPartsWarehouse.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		frmPartsWarehouse.setTitle("Parts Warehouse");		
		frmPartsWarehouse.setExtendedState(Frame.MAXIMIZED_BOTH);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();  
		frmPartsWarehouse.setMaximizedBounds(env.getMaximumWindowBounds());  
		frmPartsWarehouse.setSize((int)(width*0.8),(int) (height*0.8));	
		frmPartsWarehouse.setResizable(true);
		frmPartsWarehouse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPartsWarehouse.getContentPane().setLayout(new GridLayout(0, 1,40,40));	
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setForeground(new Color(0, 153, 255));
		tabPane.setBorder(null);
		tabPane.setPreferredSize(new Dimension(1327,740));
		tabPane.setFont(new Font("SansSerif", Font.PLAIN, 14));		
		
		JScrollPane scroll = new JScrollPane(tabPane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmPartsWarehouse.getContentPane().add(scroll);
				
		popupLookChange = new JPopupMenu();	
		 
		 tabPane.addMouseListener(new MouseAdapter() {
			 public void mouseReleased(MouseEvent me) {
				 if (me.isPopupTrigger()) {
					 
					 popupLookChange.show(me.getComponent(),me.getX(),me.getY());
				}
				 	      
		         }
	              
	      });
		 scroll.add(popupLookChange); 
			///////////////////////************************************* WAREHOUSE Lists for color changing
			
			ArrayList<JLabel> lblStyle = new ArrayList<>();	
			ArrayList<JComboBox<String>> inputComboFontColor = new ArrayList<>();
			ArrayList<JTextField> inputTxtFontColor = new ArrayList<>();
			ArrayList<JTextArea> inputTxtAreaFontColor = new ArrayList<>();
			ArrayList<JTable> tableFontColor = new ArrayList<>();
			ArrayList<JButton> saveBtnFontColor = new ArrayList<>();
						 
	
		////////////////////////////////*********************  PANEL PRODAJE
		
	           
	           
		Image imgP = new ImageIcon(this.getClass().getResource("/logo1.jpg")).getImage(); 
		BackgroundPanel prodajaPanel = new BackgroundPanel(imgP); 	
		prodajaPanel.setForeground(new Color(0, 153, 255));
		prodajaPanel.setBackground(new Color(204, 204, 204));
		prodajaPanel.setOpaque(false);
		prodajaPanel.setLayout(null);
		tabPane.addTab("Prodaja Delova", null, prodajaPanel, null);
		tabPane.setEnabledAt(0, true);
		tabPane.setSelectedIndex(0);
		
		
		JLabel lblDatum = new JLabel("  Datum");
		lblDatum.setForeground(new Color(0, 153, 255));
		lblDatum.setBackground(Color.DARK_GRAY);
		lblDatum.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatum.setBounds(120, 45, 170, 45);
		prodajaPanel.add(lblDatum);		
		lblStyle.add(lblDatum);
		
		DatePicker datumP = new DatePicker(DatePickerNew.newDateSet());
		datumP.setDateToToday();
		datumP.setBounds(320, 45, 200, 45);
		prodajaPanel.add(datumP);
				
		JLabel lblStyleP1 = new JLabel("");
		lblStyleP1.setOpaque(true);
		lblStyleP1.setBackground(new Color(255, 0, 0));
		lblStyleP1.setBounds(120, 92, 400, 2);
		prodajaPanel.add(lblStyleP1);
		lblStyle.add(lblStyleP1);
		
		JLabel lblProdato = new JLabel("  Prodato");
		lblProdato.setForeground(new Color(0, 153, 255));
		lblProdato.setBackground(Color.DARK_GRAY);
		lblProdato.setOpaque(false);
		lblProdato.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblProdato.setBounds(120, 110, 170, 45);
		prodajaPanel.add(lblProdato);	
		lblStyle.add(lblProdato);		
		
		txtProdato = new JTextField();
		txtProdato.setForeground(new Color(0, 153, 255));
		txtProdato.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtProdato.setColumns(10);
		txtProdato.setBounds(320, 110, 200, 45);
		txtProdato.setDocument(new FieldLimit(100));
		txtProdato.setOpaque(false);
		prodajaPanel.add(txtProdato);
		inputTxtFontColor.add(txtProdato);
		
		JLabel lblStyleP2 = new JLabel("");
		lblStyleP2.setOpaque(true);
		lblStyleP2.setBackground(new Color(255, 0, 0));
		lblStyleP2.setBounds(120, 157, 400, 2);
		prodajaPanel.add(lblStyleP2);
		lblStyle.add(lblStyleP2);
		
		JLabel lblCena = new JLabel("  Cena");
		lblCena.setForeground(new Color(0, 153, 255));
		lblCena.setBackground(Color.DARK_GRAY);
		lblCena.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblCena.setBounds(120, 175, 170, 45);
		prodajaPanel.add(lblCena);
		lblStyle.add(lblCena);
		
		txtCena = new JTextField();
		txtCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {					
				justNumbers(evtN);
			}
		});				
		txtCena.setForeground(new Color(0, 153, 255));
		txtCena.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCena.setColumns(10);
		txtCena.setBounds(320, 175, 200, 45);
		txtCena.setDocument(new FieldLimit(10));
		prodajaPanel.add(txtCena);
		inputTxtFontColor.add(txtCena);
		
		JLabel lblStyleP3 = new JLabel("");
		lblStyleP3.setOpaque(true);
		lblStyleP3.setBackground(new Color(255, 0, 0));
		lblStyleP3.setBounds(120, 222, 400, 2);
		prodajaPanel.add(lblStyleP3);
		lblStyle.add(lblStyleP3);
		
		JLabel lblModel = new JLabel("  Model");
		lblModel.setForeground(new Color(0, 153, 255));
		lblModel.setBackground(Color.DARK_GRAY);
		lblModel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblModel.setBounds(120, 240, 170, 45);
		prodajaPanel.add(lblModel);
		lblStyle.add(lblModel);
		
		JComboBox txtModel = new JComboBox();
		txtModel.setForeground(new Color(0, 153, 255));
		txtModel.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		txtModel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		txtModel.setBounds(320, 240, 200, 45);
		prodajaPanel.add(txtModel);
		inputComboFontColor.add(txtModel);
		
		JLabel lblStyleP4 = new JLabel("");
		lblStyleP4.setOpaque(true);
		lblStyleP4.setBackground(new Color(255, 0, 0));
		lblStyleP4.setBounds(120, 287, 400, 2);
		prodajaPanel.add(lblStyleP4);
		lblStyle.add(lblStyleP4);
		
		JLabel lblStatus = new JLabel("  Status");
		lblStatus.setForeground(new Color(0, 153, 255));
		lblStatus.setBackground(Color.DARK_GRAY);
		lblStatus.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblStatus.setBounds(120, 305, 170, 45);
		prodajaPanel.add(lblStatus);
		lblStyle.add(lblStatus);
		
		JComboBox txtStatus = new JComboBox();
		txtStatus.setForeground(new Color(0, 153, 255));
		txtStatus.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Vraceno", "Zamena", "Depozit", "Proba"}));
		txtStatus.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		txtStatus.setBounds(320, 305, 200, 45);
		prodajaPanel.add(txtStatus);
		inputComboFontColor.add(txtStatus);
		
		JLabel lblStyleP5 = new JLabel("");
		lblStyleP5.setOpaque(true);
		lblStyleP5.setBackground(new Color(255, 0, 0));
		lblStyleP5.setBounds(120, 352, 400, 2);
		prodajaPanel.add(lblStyleP5);
		lblStyle.add(lblStyleP5);
		
		JLabel lblKupac = new JLabel("  Kupac");
		lblKupac.setForeground(new Color(0, 153, 255));
		lblKupac.setBackground(Color.DARK_GRAY);
		lblKupac.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblKupac.setBounds(120, 370, 170, 45);
		prodajaPanel.add(lblKupac);
		lblStyle.add(lblKupac);
		
		txtKupac = new JTextField();
		txtKupac.setForeground(new Color(0, 153, 255));
		txtKupac.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtKupac.setColumns(10);
		txtKupac.setBounds(320, 370, 200, 45);
		txtKupac.setDocument(new FieldLimit(100));
		prodajaPanel.add(txtKupac);
		inputTxtFontColor.add(txtKupac);
		
		JLabel lblStyleP6 = new JLabel("");
		lblStyleP6.setOpaque(true);
		lblStyleP6.setBackground(new Color(255, 0, 0));
		lblStyleP6.setBounds(120, 417, 400, 2);
		prodajaPanel.add(lblStyleP6);
		lblStyle.add(lblStyleP6);
		
		JLabel lblNapomena = new JLabel("  Napomena :");
		lblNapomena.setForeground(new Color(0, 153, 255));
		lblNapomena.setHorizontalAlignment(SwingConstants.LEFT);
		lblNapomena.setBackground(Color.DARK_GRAY);
		lblNapomena.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblNapomena.setBounds(680, 45, 200, 45);
		prodajaPanel.add(lblNapomena);
		lblStyle.add(lblNapomena);
		
		JLabel lblStyleP7 = new JLabel("");
		lblStyleP7.setOpaque(true);
		lblStyleP7.setBackground(new Color(255, 0, 0));
		lblStyleP7.setBounds(680, 92, 300, 2);
		prodajaPanel.add(lblStyleP7);
		lblStyle.add(lblStyleP7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(680, 102, 300, 172);
		prodajaPanel.add(scrollPane_1);
		
		JTextArea txtNapomenaUnos = new JTextArea();
		scrollPane_1.setViewportView(txtNapomenaUnos);
		txtNapomenaUnos.setLineWrap(true);
		txtNapomenaUnos.setWrapStyleWord(true);
		txtNapomenaUnos.setDocument(new FieldLimit(1000));
		txtNapomenaUnos.setToolTipText("");
		txtNapomenaUnos.setForeground(new Color(0, 153, 255));
		txtNapomenaUnos.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtNapomenaUnos.addKeyListener(new KeyAdapter() {
       
            public void keyPressed(KeyEvent e) {
           
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
               
                    e.consume();
                    KeyboardFocusManager.
                        getCurrentKeyboardFocusManager().focusNextComponent();
                }
            }
        });
		inputTxtAreaFontColor.add(txtNapomenaUnos);
		
		JButton btnSacuvajP = new JButton("Sacuvaj");
		btnSacuvajP.setForeground(new Color(0, 153, 255));
		btnSacuvajP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnSacuvajP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						
		
			try {		
					LocalDate datum1 = datumP.getDate();					
					DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumPr = datum1.format(datumFo);							
					 checkDateformat(datumPr);
							
			if (validateFieldsP()) {			
				try {		 
					 String naziv = txtProdato.getText();
				
					 LocalDate datum = datumP.getDate();
					 DateTimeFormatter datumFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 String datumP = datum.format(datumFormatter);
					 
	   				 String kupac = txtKupac.getText();
					 String cena = txtCena.getText();
					 double cenaPr = Double.parseDouble(cena);
					 String model = (String) txtModel.getSelectedItem();			
					 String status = (String) txtStatus.getSelectedItem();			
					 String napomena = txtNapomenaUnos.getText();
					 String ulogovani = "1";
		    //       Korisnik ulogovani = (Korisnik) sesija.getAttribute("ulogovani");
				
				    boolean provera;					
						try {
									provera = Baza.DodavanjeDelovi(naziv, datumP, kupac, cenaPr, model, status,napomena, ulogovani);										
						    if(provera == false){
						        JOptionPane.showMessageDialog(null, "Deo NIJE unet u bazu!", "Greska!", JOptionPane.ERROR_MESSAGE);
						    }else{
						    	JOptionPane.showMessageDialog(null, "Ubaceno u bazu");
						    	txtProdato.setText("");
								txtCena.setText("");		
								txtKupac.setText("");				  
						    	txtNapomenaUnos.setText("");				    	
						    }
							} catch (SQLException e) {
									
									e.printStackTrace();
								}						
							} catch (Exception e) {					
								JOptionPane.showMessageDialog(null, "Deo NIJE unet u bazu!", "Greska!", JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							}  						
				}	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Datum nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		btnSacuvajP.setBounds(120, 480, 400, 45);
		frmPartsWarehouse.getRootPane().setDefaultButton(btnSacuvajP);
		prodajaPanel.add(btnSacuvajP);	
		saveBtnFontColor.add(btnSacuvajP);				
		
		//////////////////////////////*************************  PANEL KUPOVINE
		
		Image imgK = new ImageIcon(this.getClass().getResource("/logo1.jpg")).getImage(); 
		BackgroundPanel kupovinaPanel = new BackgroundPanel(imgK); 
		kupovinaPanel.setForeground(new Color(0, 153, 255));
		kupovinaPanel.setLayout(null);
		kupovinaPanel.setBackground(Color.LIGHT_GRAY);		
		tabPane.addTab("Kupovina Delova", null, kupovinaPanel, null);
		tabPane.setBackgroundAt(1, Color.WHITE);
		tabPane.setEnabledAt(1, true);
		
		JLabel lblStyleK1 = new JLabel("");
		lblStyleK1.setOpaque(true);
		lblStyleK1.setBackground(new Color(255, 102, 0));
		lblStyleK1.setBounds(120, 92, 400, 2);
		kupovinaPanel.add(lblStyleK1);
		lblStyle.add(lblStyleK1);
		
		JLabel lblStyleK2 = new JLabel("");
		lblStyleK2.setOpaque(true);
		lblStyleK2.setBackground(new Color(255, 102, 0));
		lblStyleK2.setBounds(120, 157, 400, 2);
		kupovinaPanel.add(lblStyleK2);
		lblStyle.add(lblStyleK2);
		
		JLabel lblStyleK3 = new JLabel("");
		lblStyleK3.setOpaque(true);
		lblStyleK3.setBackground(new Color(255, 102, 0));
		lblStyleK3.setBounds(120, 222, 400, 2);
		kupovinaPanel.add(lblStyleK3);
		lblStyle.add(lblStyleK3);
		
		JLabel lblStyleK4 = new JLabel("");
		lblStyleK4.setOpaque(true);
		lblStyleK4.setBackground(new Color(255, 102, 0));
		lblStyleK4.setBounds(120, 287, 400, 2);
		kupovinaPanel.add(lblStyleK4);
		lblStyle.add(lblStyleK4);
		
		JLabel lblStyleK5 = new JLabel("");
		lblStyleK5.setOpaque(true);
		lblStyleK5.setBackground(new Color(255, 102, 0));
		lblStyleK5.setBounds(120, 352, 400, 2);
		kupovinaPanel.add(lblStyleK5);
		lblStyle.add(lblStyleK5);
		
		JLabel lblStyleK6 = new JLabel("");
		lblStyleK6.setOpaque(true);
		lblStyleK6.setBackground(new Color(255, 102, 0));
		lblStyleK6.setBounds(120, 417, 400, 2);
		kupovinaPanel.add(lblStyleK6);
		lblStyle.add(lblStyleK6);
		
		JLabel lblStyleK7 = new JLabel("");
		lblStyleK7.setOpaque(true);
		lblStyleK7.setBackground(new Color(255, 102, 0));
		lblStyleK7.setBounds(120, 482, 400, 2);
		kupovinaPanel.add(lblStyleK7);
		lblStyle.add(lblStyleK7);
		
		JLabel lblStyleK8 = new JLabel("");
		lblStyleK8.setOpaque(true);
		lblStyleK8.setBackground(new Color(255, 102, 0));
		lblStyleK8.setBounds(680, 92, 300, 2);
		kupovinaPanel.add(lblStyleK8);
		lblStyle.add(lblStyleK8);
		
		JLabel lblDatumK = new JLabel("  Datum");
		lblDatumK.setForeground(new Color(0, 153, 255));
		lblDatumK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatumK.setBackground(Color.DARK_GRAY);
		lblDatumK.setBounds(120, 45, 170, 45);
		kupovinaPanel.add(lblDatumK);
		lblStyle.add(lblDatumK);
		
		DatePicker datumK = new DatePicker(DatePickerNew.newDateSet());
		datumK.setDateToToday();
		datumK.setBounds(320, 45, 200, 45);
		kupovinaPanel.add(datumK);
		
		JLabel lblModelK = new JLabel("  Model");
		lblModelK.setForeground(new Color(0, 153, 255));
		lblModelK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblModelK.setBackground(Color.DARK_GRAY);
		lblModelK.setBounds(120, 110, 170, 45);
		kupovinaPanel.add(lblModelK);
		lblStyle.add(lblModelK);
		
		JComboBox txtModelK = new JComboBox();		
		txtModelK.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		txtModelK.setForeground(new Color(0, 153, 255));
		txtModelK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		txtModelK.setBounds(320, 110, 200, 45);
		kupovinaPanel.add(txtModelK);
		inputComboFontColor.add(txtModelK);
		
		JLabel lblVrstaGoriva = new JLabel("  Vrsta Goriva");
		lblVrstaGoriva.setForeground(new Color(0, 153, 255));
		lblVrstaGoriva.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblVrstaGoriva.setBackground(Color.DARK_GRAY);
		lblVrstaGoriva.setBounds(120, 175, 170, 45);
		kupovinaPanel.add(lblVrstaGoriva);
		lblStyle.add(lblVrstaGoriva);
		
		JComboBox txtVrstaGoriva = new JComboBox();		
		txtVrstaGoriva.setModel(new DefaultComboBoxModel(new String[] {"Benzin", "Dizel", "Benzin/Gas"}));
		txtVrstaGoriva.setForeground(new Color(0, 153, 255));
		txtVrstaGoriva.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		txtVrstaGoriva.setBounds(320, 175, 200, 45);
		kupovinaPanel.add(txtVrstaGoriva);
		inputComboFontColor.add(txtVrstaGoriva);
		
		JLabel lblGodiste = new JLabel("  God. Proizvodnje");
		lblGodiste.setForeground(new Color(0, 153, 255));
		lblGodiste.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
		lblGodiste.setBackground(Color.DARK_GRAY);
		lblGodiste.setBounds(120, 240, 170, 45);
		kupovinaPanel.add(lblGodiste);
		lblStyle.add(lblGodiste);
		
		txtGodina = new JTextField();
		txtGodina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				justNumbers(e);
			}
		});
		txtGodina.setForeground(new Color(0, 153, 255));
		txtGodina.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtGodina.setColumns(10);
		txtGodina.setBounds(320, 240, 200, 45);
		txtGodina.setDocument(new FieldLimit(4));
		kupovinaPanel.add(txtGodina);
		inputTxtFontColor.add(txtGodina);
		
		JLabel lblVolan = new JLabel("  Levi/Desni Volan");
		lblVolan.setForeground(new Color(0, 153, 255));
		lblVolan.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
		lblVolan.setBackground(Color.DARK_GRAY);
		lblVolan.setBounds(120, 305, 170, 45);
		kupovinaPanel.add(lblVolan);
		lblStyle.add(lblVolan);
		
		JComboBox txtVolan = new JComboBox();		
		txtVolan.setModel(new DefaultComboBoxModel(new String[] {"Levi", "Desni"}));
		txtVolan.setForeground(new Color(0, 153, 255));
		txtVolan.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		txtVolan.setBounds(320, 305, 200, 45);
		kupovinaPanel.add(txtVolan);
		inputComboFontColor.add(txtVolan);
		
		JLabel lblProdavac = new JLabel("  Prodavac");
		lblProdavac.setForeground(new Color(0, 153, 255));
		lblProdavac.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblProdavac.setBackground(Color.DARK_GRAY);
		lblProdavac.setBounds(120, 370, 170, 45);
		kupovinaPanel.add(lblProdavac);
		lblStyle.add(lblProdavac);
		
		txtProdavac = new JTextField();
		txtProdavac.setForeground(new Color(0, 153, 255));
		txtProdavac.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtProdavac.setColumns(10);
		txtProdavac.setBounds(320, 370, 200, 45);
		txtProdavac.setDocument(new FieldLimit(50));
		kupovinaPanel.add(txtProdavac);
		inputTxtFontColor.add(txtProdavac);
		
		JLabel lblCenaK = new JLabel("  Cena");
		lblCenaK.setForeground(new Color(0, 153, 255));
		lblCenaK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblCenaK.setBackground(Color.DARK_GRAY);
		lblCenaK.setBounds(120, 435, 170, 45);
		kupovinaPanel.add(lblCenaK);
		lblStyle.add(lblCenaK);
		
		txtCenaK = new JTextField();
		txtCenaK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evtN) {					
				justNumbers(evtN);				
			}
		});
		txtCenaK.setForeground(new Color(0, 153, 255));
		txtCenaK.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCenaK.setColumns(10);
		txtCenaK.setBounds(320, 435, 200, 45);
		txtCenaK.setDocument(new FieldLimit(10));
		kupovinaPanel.add(txtCenaK);
		inputTxtFontColor.add(txtCenaK);
		
		JLabel lblNapomenaK = new JLabel("  Napomena :");
		lblNapomenaK.setHorizontalAlignment(SwingConstants.LEFT);
		lblNapomenaK.setForeground(new Color(0, 153, 255));
		lblNapomenaK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblNapomenaK.setBackground(Color.DARK_GRAY);
		lblNapomenaK.setBounds(680, 45, 200, 45);
		kupovinaPanel.add(lblNapomenaK);
		lblStyle.add(lblNapomenaK);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(680, 102, 300, 172);
		kupovinaPanel.add(scrollPane);
		JTextArea txtNapomenaK = new JTextArea();		
		txtNapomenaK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				  if (e.getKeyCode() == KeyEvent.VK_TAB) {
		               
	                    e.consume();
	                    KeyboardFocusManager.
	                        getCurrentKeyboardFocusManager().focusNextComponent();
				  }
			}
		});
		scrollPane.setViewportView(txtNapomenaK);
		txtNapomenaK.setWrapStyleWord(true);
		txtNapomenaK.setLineWrap(true);
		txtNapomenaK.setDocument(new FieldLimit(1500));
		txtNapomenaK.setToolTipText("Unesi Napomenu");
		txtNapomenaK.setForeground(new Color(0, 153, 255));
		txtNapomenaK.setFont(new Font("SansSerif", Font.PLAIN, 18));
		inputTxtAreaFontColor.add(txtNapomenaK);

		JButton btnSacuvajK = new JButton("Sacuvaj");
		int tabSelectedK = tabPane.getSelectedIndex();	
		if (tabSelectedK==1) {
			frmPartsWarehouse.getRootPane().setDefaultButton(btnSacuvajK);
		}
		btnSacuvajK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {					
					LocalDate datum1 = datumK.getDate();					
					DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumKup = datum1.format(datumFo);		
					checkDateformat(datumKup);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datum nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
				if (validateFieldsK()) {
					try {	
						LocalDate datum = datumK.getDate();					
						DateTimeFormatter datumFo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String datumK = datum.format(datumFo);								
						String model = (String) txtModelK.getSelectedItem();
						String gorivo = (String) txtVrstaGoriva.getSelectedItem();
						String godina = txtGodina.getText();
						String volan = (String) txtVolan.getSelectedItem();
						String prodavac = txtProdavac.getText();
						String cenaK = txtCenaK.getText();
						double cena = Double.parseDouble(cenaK);
						String napomenaK = txtNapomenaK.getText();
						String ulogovani = "1";
						
						try {
							boolean provera = 
									Baza.DodavanjeKupljeno(datumK, model, gorivo, godina, volan, prodavac, cena, napomenaK, ulogovani);
							 if(provera == false){
							        JOptionPane.showMessageDialog(null, "Kupljeno NIJE uneto u bazu!", "Greska!", JOptionPane.ERROR_MESSAGE);
							    }else{
							    	JOptionPane.showMessageDialog(null, "Kupljeni deo je sacuvan u bazu!");
							    	txtGodina.setText("");
									txtProdavac.setText("");		
									txtCenaK.setText("");				  
							    	txtNapomenaK.setText("");				    	
							    }
						} catch (Exception e) {							
						}						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Kupljeno NIJE uneto u bazu!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}												
				}				
			}
		});
		btnSacuvajK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnSacuvajK.setForeground(new Color(0, 153, 255));
		btnSacuvajK.setBounds(120, 550, 400, 50);
		kupovinaPanel.add(btnSacuvajK);
		saveBtnFontColor.add(btnSacuvajK);
		///////////////////////////////////********************* kreiranje default-nog dugmeta za cuvanje u zavisnosti na trenutno otvoren tab
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if (tabPane.getSelectedIndex()==0) {
					
					frmPartsWarehouse.getRootPane().setDefaultButton(btnSacuvajP );
				}else if (tabPane.getSelectedIndex()==1) {
					frmPartsWarehouse.getRootPane().setDefaultButton(btnSacuvajK);
				}else {
					frmPartsWarehouse.getRootPane().setDefaultButton(null);
				}
			}
		});		
		//////////////////////////////////********************** panel pretrage prodaje

		Image imgPP = new ImageIcon(this.getClass().getResource("/logo1.jpg")).getImage(); 
		BackgroundPanel pretragaPanel = new BackgroundPanel(imgPP); 
		pretragaPanel.setLayout(null);
		pretragaPanel.setForeground(new Color(0, 153, 255));
		pretragaPanel.setBackground(Color.LIGHT_GRAY);		
		tabPane.addTab("Pretraga prodaje", null, pretragaPanel, null);		
		tabPane.setEnabledAt(2, true);
		
		JLabel lblStylePP1 = new JLabel("");
		lblStylePP1.setOpaque(true);
		lblStylePP1.setBackground(new Color(3, 255, 94));
		lblStylePP1.setBounds(47, 56, 380, 2);
		pretragaPanel.add(lblStylePP1);
		lblStyle.add(lblStylePP1);
		
		JLabel lblStylePP2 = new JLabel("");
		lblStylePP2.setOpaque(true);
		lblStylePP2.setBackground(new Color(3, 255, 94));
		lblStylePP2.setBounds(47, 112, 380, 2);
		pretragaPanel.add(lblStylePP2);
		lblStyle.add(lblStylePP2);
		
		JLabel lblStylePP3 = new JLabel("");
		lblStylePP3.setOpaque(true);
		lblStylePP3.setBackground(new Color(3, 255, 94));
		lblStylePP3.setBounds(47, 168, 380, 2);
		pretragaPanel.add(lblStylePP3);
		lblStyle.add(lblStylePP3);
		
		JLabel lblStylePP4 = new JLabel("");
		lblStylePP4.setOpaque(true);
		lblStylePP4.setBackground(new Color(3, 255, 94));
		lblStylePP4.setBounds(47, 224, 380, 2);
		pretragaPanel.add(lblStylePP4);
		lblStyle.add(lblStylePP4);
		
		JLabel lblStylePP5 = new JLabel("");
		lblStylePP5.setOpaque(true);
		lblStylePP5.setBackground(new Color(3, 255, 94));
		lblStylePP5.setBounds(47, 280, 380, 2);
		pretragaPanel.add(lblStylePP5);
		lblStyle.add(lblStylePP5);
		
		JLabel lblStylePP6 = new JLabel("");
		lblStylePP6.setOpaque(true);
		lblStylePP6.setBackground(new Color(3, 255, 94));
		lblStylePP6.setBounds(47, 336, 380, 2);
		pretragaPanel.add(lblStylePP6);
		lblStyle.add(lblStylePP6);
		
		JLabel lblStylePP7 = new JLabel("");
		lblStylePP7.setOpaque(true);
		lblStylePP7.setBackground(new Color(3, 255, 94));
		lblStylePP7.setBounds(47, 392, 380, 2);
		pretragaPanel.add(lblStylePP7);
		lblStyle.add(lblStylePP7);
		
		JLabel lblStylePP8 = new JLabel("");
		lblStylePP8.setOpaque(true);
		lblStylePP8.setBackground(new Color(3, 255, 94));
		lblStylePP8.setBounds(525, 41, 800, 2);
		pretragaPanel.add(lblStylePP8);
		lblStyle.add(lblStylePP8);
		
		JLabel lblDatPretProdOd = new JLabel("  Datum          od");
		lblDatPretProdOd.setForeground(new Color(0, 153, 255));
		lblDatPretProdOd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatPretProdOd.setBackground(Color.DARK_GRAY);
		lblDatPretProdOd.setBounds(47, 11, 170, 45);
		pretragaPanel.add(lblDatPretProdOd);
		lblStyle.add(lblDatPretProdOd);
		
		DatePicker datPretProdOd = new DatePicker(DatePickerNew.newDateSet());
		datPretProdOd.setBounds(227, 11, 200, 45);
		pretragaPanel.add(datPretProdOd);
		
		JLabel lblDatPretProdDo = new JLabel("  Datum          do");
		lblDatPretProdDo.setForeground(new Color(0, 153, 255));
		lblDatPretProdDo.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatPretProdDo.setBackground(Color.DARK_GRAY);
		lblDatPretProdDo.setBounds(47, 67, 170, 45);
		pretragaPanel.add(lblDatPretProdDo);
		lblStyle.add(lblDatPretProdDo);
		
		DatePicker datPretProdDo = new DatePicker(DatePickerNew.newDateSet());
		datPretProdDo.setDateToToday();
		datPretProdDo.setBounds(227, 67, 200, 45);
		pretragaPanel.add(datPretProdDo);	
		
		JLabel lblProdatoPret = new JLabel("  Prodati deo");
		lblProdatoPret.setForeground(new Color(0, 153, 255));
		lblProdatoPret.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblProdatoPret.setBackground(Color.DARK_GRAY);
		lblProdatoPret.setBounds(47, 123, 170, 45);
		pretragaPanel.add(lblProdatoPret);
		lblStyle.add(lblProdatoPret);
		
		txtPretProdP = new JTextField();
		txtPretProdP.setForeground(new Color(0, 153, 255));
		txtPretProdP.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPretProdP.setColumns(10);
		txtPretProdP.setBounds(227, 123, 200, 45);
		pretragaPanel.add(txtPretProdP);
		inputTxtFontColor.add(txtPretProdP);
		
		JLabel lblCenaPretProd = new JLabel("  Cena");
		lblCenaPretProd.setForeground(new Color(0, 153, 255));
		lblCenaPretProd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblCenaPretProd.setBackground(Color.DARK_GRAY);
		lblCenaPretProd.setBounds(47, 179, 170, 45);
		pretragaPanel.add(lblCenaPretProd);
		lblStyle.add(lblCenaPretProd);
		
		txtCenaPretP = new JTextField();
		txtCenaPretP.setDocument(new FieldLimit(10));
		txtCenaPretP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				justNumbers(ev);
			}
		});
		txtCenaPretP.setForeground(new Color(0, 153, 255));
		txtCenaPretP.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCenaPretP.setColumns(10);
		txtCenaPretP.setBounds(227, 179, 200, 45);
		pretragaPanel.add(txtCenaPretP);		
		inputTxtFontColor.add(txtCenaPretP);
		
		JLabel lblModelPretProd = new JLabel("  Model");
		lblModelPretProd.setForeground(new Color(0, 153, 255));
		lblModelPretProd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblModelPretProd.setBackground(Color.DARK_GRAY);
		lblModelPretProd.setBounds(47, 235, 170, 45);
		pretragaPanel.add(lblModelPretProd);
		lblStyle.add(lblModelPretProd);
		
		JComboBox comboModelPretP = new JComboBox();		
		comboModelPretP.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		comboModelPretP.setForeground(new Color(0, 153, 255));
		comboModelPretP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		comboModelPretP.setBounds(227, 235, 200, 45);
		pretragaPanel.add(comboModelPretP);
		inputComboFontColor.add(comboModelPretP);
		
		JLabel lblStatusPretProd = new JLabel("  Status");
		lblStatusPretProd.setForeground(new Color(0, 153, 255));
		lblStatusPretProd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblStatusPretProd.setBackground(Color.DARK_GRAY);
		lblStatusPretProd.setBounds(47, 291, 170, 45);
		pretragaPanel.add(lblStatusPretProd);
		lblStyle.add(lblStatusPretProd);
		
		JComboBox comboStatusPretP = new JComboBox();		
		comboStatusPretP.setModel(new DefaultComboBoxModel(new String[] {"Placeno", "Nije Placeno", "Vraceno", "Zamena", "Depozit", "Proba"}));
		comboStatusPretP.setForeground(new Color(0, 153, 255));
		comboStatusPretP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		comboStatusPretP.setBounds(227, 291, 200, 45);
		pretragaPanel.add(comboStatusPretP);
		inputComboFontColor.add(comboStatusPretP);
		
		JLabel lblKupacPretProd = new JLabel("  Kupac");
		lblKupacPretProd.setForeground(new Color(0, 153, 255));
		lblKupacPretProd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblKupacPretProd.setBackground(Color.DARK_GRAY);
		lblKupacPretProd.setBounds(47, 347, 170, 45);
		pretragaPanel.add(lblKupacPretProd);
		lblStyle.add(lblKupacPretProd);
		
		txtKupacPretP = new JTextField();
		txtKupacPretP.setForeground(new Color(0, 153, 255));
		txtKupacPretP.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtKupacPretP.setColumns(10);
		txtKupacPretP.setBounds(227, 347, 200, 45);
		pretragaPanel.add(txtKupacPretP);
		inputTxtFontColor.add(txtKupacPretP);
		
		JLabel lblUkupnoP = new JLabel("");
		lblUkupnoP.setForeground(new Color(0, 153, 255));
		lblUkupnoP.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblUkupnoP.setBackground(Color.GRAY);
		lblUkupnoP.setBounds(525, 11, 230, 30);
		pretragaPanel.add(lblUkupnoP);
		lblStyle.add(lblUkupnoP);
		
		JLabel lblPretKomP = new JLabel("");
		lblPretKomP.setForeground(new Color(0, 153, 255));
		lblPretKomP.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblPretKomP.setBackground(Color.GRAY);
		lblPretKomP.setBounds(765, 11, 230, 30);
		pretragaPanel.add(lblPretKomP);
		lblStyle.add(lblPretKomP);
		
		JScrollPane scrollTableP = new JScrollPane();
		scrollTableP.setBounds(525, 50, 800, 622);
		scrollTableP.setBackground(new Color(51, 51, 51));
		pretragaPanel.add(scrollTableP);
		
				
		tableP = new JTable();
		tableP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableP.setForeground(new Color(0, 255, 0));
		scrollTableP.setViewportView(tableP);					
		tableP.setFont(new Font("SansSerif", Font.BOLD, 14));
		tableP.setBackground(new Color(102, 102, 102));
		
		
		JPopupMenu popup = new JPopupMenu();
		JMenuItem info = new JMenuItem("Info");
		info.addActionListener(new ActionListener() {						
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableP.getSelectedRow();
				String column7 = tableP.getModel().getValueAt(row, 7).toString();
				 // create a JTextArea
			      JTextArea textArea = new JTextArea(8, 40);
			      textArea.setWrapStyleWord(true);
			      textArea.setLineWrap(true);
			      Font font = new Font("Sans Serif",Font.PLAIN, 15);
			      textArea.setFont(font);
			      textArea.setText(column7);
			      textArea.setEditable(false);
			      
			      // wrap a scrollpane around txtArea
			      JScrollPane scrollPane = new JScrollPane(textArea);						      
			      // display in a message dialog
			      JOptionPane.showMessageDialog(scrollTableP, scrollPane, "Napomena",JOptionPane.INFORMATION_MESSAGE);							
			}
		});
		popup.add(info);
		JMenuItem brisi = new JMenuItem("Obrisi");
		brisi.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableP.getSelectedRow();
				String id = tableP.getModel().getValueAt(row, 0).toString();
				
				int result = JOptionPane.showConfirmDialog(null, "Deo ce biti trajno obrisan!!!", "Potvrda Brisanja dela!", JOptionPane.YES_NO_OPTION);
			      if (result == JOptionPane.YES_OPTION) {			    	  
			    	  try {
			    		  Baza.BrisanjeDelovi(id);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			         
			      }
			}
		});
		popup.add(brisi);
		JMenuItem izmena = new JMenuItem("Izmeni");
		izmena.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					int row = tableP.getSelectedRow();
					String getId = tableP.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(getId);				
					String f1 = tableP.getModel().getValueAt(row, 1).toString();
					String f2 = tableP.getModel().getValueAt(row, 2).toString();
					String f3 = tableP.getModel().getValueAt(row, 3).toString();
					String f5 = tableP.getModel().getValueAt(row, 5).toString();
					String f6 = tableP.getModel().getValueAt(row, 6).toString();
					String f7 = tableP.getModel().getValueAt(row, 7).toString();
					String cena = tableP.getModel().getValueAt(row, 4).toString();
					String kor = "1";										
				try {					
					PopupUpdateP.popUp(id,f1, f2, f3, cena, f5, f6, f7, kor);					
				} catch (Exception e2) {
				
				}			
			}
		});
		popup.add(izmena);
		tableP.setComponentPopupMenu(popup);
		tableFontColor.add(tableP);
		
		JButton btnPretPoDeluP = new JButton("Pretraga po prodatom DELU");
		btnPretPoDeluP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPretKomP.setText("");				
				LocalDate dOd = datPretProdOd.getDate();
				LocalDate dDo = datPretProdDo.getDate();
				String searchBy = "naziv";
				String search = txtPretProdP.getText();
				if (search.length()<3) {
					JOptionPane.showMessageDialog(null, "Za Polje "+"\"Prodati deo\""+" je OBAVEZNO uneti min. 3 karaktera!", "Greska!", JOptionPane.ERROR_MESSAGE);
				
				}else if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchP(dOd, dDo, searchBy, search, tableP, lblUkupnoP, lblPretKomP);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}								
			}					
		});
		btnPretPoDeluP.setForeground(new Color(0, 153, 255));
		btnPretPoDeluP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoDeluP.setBounds(47, 405, 380, 45);
		pretragaPanel.add(btnPretPoDeluP);
		saveBtnFontColor.add(btnPretPoDeluP);
		
		JButton btnPretPoModP = new JButton("Pretraga po MODELU");
		btnPretPoModP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPretKomP.setText("");
				LocalDate dOd = datPretProdOd.getDate();
				LocalDate dDo = datPretProdDo.getDate();
				String searchBy = "model";
				String search = comboModelPretP.getSelectedItem().toString();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchP(dOd, dDo, searchBy, search, tableP, lblUkupnoP, lblPretKomP);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnPretPoModP.setForeground(new Color(0, 153, 255));
		btnPretPoModP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoModP.setBounds(47, 461, 380, 45);
		pretragaPanel.add(btnPretPoModP);
		saveBtnFontColor.add(btnPretPoModP);
		
		JButton btnPretPoKupcuP = new JButton("Pretraga po KUPCU");
		btnPretPoKupcuP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPretKomP.setText("");
				LocalDate dOd = datPretProdOd.getDate();
				LocalDate dDo = datPretProdDo.getDate();
				String searchBy = "kupac";
				String search = txtKupacPretP.getText();
				
				if (search.length()<3) {
					JOptionPane.showMessageDialog(null, "Za Polje "+"\"Kupac\""+" je OBAVEZNO uneti min. 3 karaktera!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}else if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchP(dOd, dDo, searchBy, search, tableP, lblUkupnoP, lblPretKomP);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		btnPretPoKupcuP.setForeground(new Color(0, 153, 255));
		btnPretPoKupcuP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoKupcuP.setBounds(47, 517, 380, 45);
		pretragaPanel.add(btnPretPoKupcuP);
		saveBtnFontColor.add(btnPretPoKupcuP);
		
		JButton btnPretPoStatusuP = new JButton("Pretraga po STATUSU");
		btnPretPoStatusuP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				lblPretKomP.setText("");
				LocalDate dOd = datPretProdOd.getDate();
				LocalDate dDo = datPretProdDo.getDate();
				String searchBy = "status";
				String search = comboStatusPretP.getSelectedItem().toString();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchP(dOd, dDo, searchBy, search, tableP, lblUkupnoP, lblPretKomP);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}					
			}
		});
		btnPretPoStatusuP.setForeground(new Color(0, 153, 255));
		btnPretPoStatusuP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoStatusuP.setBounds(47, 573, 380, 45);
		pretragaPanel.add(btnPretPoStatusuP);
		saveBtnFontColor.add(btnPretPoStatusuP);
		
		JButton btnPretPoDatP = new JButton("Pretraga po DATUMU ");
		btnPretPoDatP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPretKomP.setText("");
				LocalDate dOd = datPretProdOd.getDate();
				LocalDate dDo = datPretProdDo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchByDateP(dOd, dDo, tableP, lblUkupnoP, lblPretKomP);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}					
			}
		});
		btnPretPoDatP.setForeground(new Color(0, 153, 255));
		btnPretPoDatP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoDatP.setBounds(47, 629, 380, 45);
		pretragaPanel.add(btnPretPoDatP);
		saveBtnFontColor.add(btnPretPoDatP);
		
		JButton btnGrandP = new JButton("Click for Grand TOTAL");		
		btnGrandP.setForeground(new Color(0, 153, 255));
		btnGrandP.setBackground(Color.LIGHT_GRAY);
		btnGrandP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));		
		btnGrandP.addActionListener(new ActionListener() {
			boolean clicked = false;
			public void actionPerformed(ActionEvent arg0) {				/////////******** setting button for two action
				if (!clicked) {
					try {
						Statement stmt = DatabaseConnection.konekcioniObj.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT sum(cena) as ukupno  from Delovi");
						double total = 0;
						while (rs.next()) {
							double total1 = rs.getDouble("ukupno");
							total = total + total1;	
							String t = Double.toString(total);
							btnGrandP.setText("Total prodaja = "+t);
							btnGrandP.setForeground(new Color(0,255,0));
							clicked = true;
						}						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					btnGrandP.setText("Click for Grand TOTAL");
					btnGrandP.setForeground(new Color(0,153,255));
					btnGrandP.setOpaque(false);
					clicked = false;
				}
				
			}
		});
		btnGrandP.setBounds(1005, 11, 320, 30);
		pretragaPanel.add(btnGrandP);
		saveBtnFontColor.add(btnGrandP);
		
		///////////////////////////////////////********************** Panel pretrage kupovine
		
		BackgroundPanel pretragaPanelK = new BackgroundPanel(imgK); 
		pretragaPanelK.setLayout(null);
		pretragaPanelK.setForeground(new Color(0, 153, 255));
		pretragaPanelK.setBackground(Color.LIGHT_GRAY);
		tabPane.addTab("Pretraga Kupovine", null, pretragaPanelK, null);
		tabPane.setEnabledAt(3, true);
		
		JLabel lblStylePK1 = new JLabel("");
		lblStylePK1.setBounds(47, 56, 380, 2);
		lblStylePK1.setBackground(new Color(153, 0, 204));
		lblStylePK1.setOpaque(true);
		pretragaPanelK.add(lblStylePK1);
		lblStyle.add(lblStylePK1);
		
		JLabel lblStylePK2 = new JLabel("");
		lblStylePK2.setOpaque(true);
		lblStylePK2.setBackground(new Color(153, 0, 204));
		lblStylePK2.setBounds(47, 112, 380, 2);
		pretragaPanelK.add(lblStylePK2);
		lblStyle.add(lblStylePK2);
		
		JLabel lblStylePK3 = new JLabel("");
		lblStylePK3.setOpaque(true);
		lblStylePK3.setBackground(new Color(153, 0, 204));
		lblStylePK3.setBounds(47, 168, 380, 2);
		pretragaPanelK.add(lblStylePK3);
		lblStyle.add(lblStylePK3);
		
		JLabel lblStylePK4 = new JLabel("");
		lblStylePK4.setOpaque(true);
		lblStylePK4.setBackground(new Color(153, 0, 204));
		lblStylePK4.setBounds(47, 224, 380, 2);
		pretragaPanelK.add(lblStylePK4);
		lblStyle.add(lblStylePK4);
		
		JLabel lblStylePK5 = new JLabel("");
		lblStylePK5.setOpaque(true);
		lblStylePK5.setBackground(new Color(153, 0, 204));
		lblStylePK5.setBounds(47, 280, 380, 2);
		pretragaPanelK.add(lblStylePK5);
		lblStyle.add(lblStylePK5);
		
		JLabel lblStylePK6 = new JLabel("");
		lblStylePK6.setOpaque(true);
		lblStylePK6.setBackground(new Color(153, 0, 204));
		lblStylePK6.setBounds(47, 336, 380, 2);
		pretragaPanelK.add(lblStylePK6);
		lblStyle.add(lblStylePK6);
		
		JLabel lblStylePK7 = new JLabel("");
		lblStylePK7.setOpaque(true);
		lblStylePK7.setBackground(new Color(153, 0, 204));
		lblStylePK7.setBounds(525, 41, 800, 2);
		pretragaPanelK.add(lblStylePK7);
		lblStyle.add(lblStylePK7);
		
		JLabel lblDatPretKupOd = new JLabel("  Datum          od");
		lblDatPretKupOd.setOpaque(false);
		lblDatPretKupOd.setForeground(new Color(0, 153, 255));
		lblDatPretKupOd.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatPretKupOd.setBackground(Color.DARK_GRAY);
		lblDatPretKupOd.setBounds(47, 11, 170, 45);
		pretragaPanelK.add(lblDatPretKupOd);
		lblStyle.add(lblDatPretKupOd);
		
		DatePicker datPretKupOd = new DatePicker(DatePickerNew.newDateSet());
		datPretKupOd.setBounds(227, 11, 200, 45);
		pretragaPanelK.add(datPretKupOd);
		
		JLabel lblDatPretKupDo = new JLabel("  Datum          do");
		lblDatPretKupDo.setOpaque(false);
		lblDatPretKupDo.setForeground(new Color(0, 153, 255));
		lblDatPretKupDo.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblDatPretKupDo.setBackground(Color.DARK_GRAY);
		lblDatPretKupDo.setBounds(47, 67, 170, 45);
		pretragaPanelK.add(lblDatPretKupDo);
		lblStyle.add(lblDatPretKupDo);
		
		DatePicker datPretKupDo = new DatePicker(DatePickerNew.newDateSet());
		datPretKupDo.setDateToToday();
		datPretKupDo.setBounds(227, 67, 200, 45);
		pretragaPanelK.add(datPretKupDo);
		
		JLabel lblPretKupModel = new JLabel("  Model");
		lblPretKupModel.setForeground(new Color(0, 153, 255));
		lblPretKupModel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblPretKupModel.setBackground(Color.DARK_GRAY);
		lblPretKupModel.setBounds(47, 123, 170, 45);
		pretragaPanelK.add(lblPretKupModel);
		lblStyle.add(lblPretKupModel);
		
		JComboBox comboPretKupModel = new JComboBox();		
		comboPretKupModel.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		comboPretKupModel.setForeground(new Color(0, 153, 255));
		comboPretKupModel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		comboPretKupModel.setBounds(227, 123, 200, 45);
		pretragaPanelK.add(comboPretKupModel);
		inputComboFontColor.add(comboPretKupModel);
		
		JLabel lblPretKupGod = new JLabel("  God. Proizvodnje");
		lblPretKupGod.setForeground(new Color(0, 153, 255));
		lblPretKupGod.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
		lblPretKupGod.setBackground(Color.DARK_GRAY);
		lblPretKupGod.setBounds(47, 179, 170, 45);
		pretragaPanelK.add(lblPretKupGod);
		lblStyle.add(lblPretKupGod);
		
		txtPretKupGod = new JTextField();
		txtPretKupGod.setDocument(new FieldLimit(4));
		txtPretKupGod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				justNumbers(e);
			}
		});		
		txtPretKupGod.setForeground(new Color(0, 153, 255));
		txtPretKupGod.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPretKupGod.setColumns(10);
		txtPretKupGod.setBounds(227, 179, 200, 45);
		pretragaPanelK.add(txtPretKupGod);
		inputTxtFontColor.add(txtPretKupGod);
		
		JLabel lblPretKupVolan = new JLabel(" Strana Volana");
		lblPretKupVolan.setForeground(new Color(0, 153, 255));
		lblPretKupVolan.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblPretKupVolan.setBackground(Color.DARK_GRAY);
		lblPretKupVolan.setBounds(47, 235, 170, 45);
		pretragaPanelK.add(lblPretKupVolan);
		lblStyle.add(lblPretKupVolan);
		
		JComboBox comboPretKupVolan = new JComboBox();		
		comboPretKupVolan.setModel(new DefaultComboBoxModel(new String[] {"W168", "W169", "W176", "W177"}));
		comboPretKupVolan.setForeground(new Color(0, 153, 255));
		comboPretKupVolan.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		comboPretKupVolan.setBounds(227, 235, 200, 45);
		pretragaPanelK.add(comboPretKupVolan);
		inputComboFontColor.add(comboPretKupVolan);
		
		JLabel lblPretKupProdavac = new JLabel("  Prodavac");
		lblPretKupProdavac.setForeground(new Color(0, 153, 255));
		lblPretKupProdavac.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblPretKupProdavac.setBackground(Color.DARK_GRAY);
		lblPretKupProdavac.setBounds(47, 291, 170, 45);
		pretragaPanelK.add(lblPretKupProdavac);
		lblStyle.add(lblPretKupProdavac);
		
		txtPretKupProdavac = new JTextField();
		txtPretKupProdavac.setForeground(new Color(0, 153, 255));
		txtPretKupProdavac.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPretKupProdavac.setColumns(10);
		txtPretKupProdavac.setBounds(227, 291, 200, 45);
		pretragaPanelK.add(txtPretKupProdavac);
		inputTxtFontColor.add(txtPretKupProdavac);
		
		JLabel lblUkupnoK = new JLabel("");
		lblUkupnoK.setForeground(new Color(0, 153, 255));
		lblUkupnoK.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblUkupnoK.setBackground(Color.GRAY);
		lblUkupnoK.setBounds(525, 11, 230, 30);
		pretragaPanelK.add(lblUkupnoK);
		lblStyle.add(lblUkupnoK);
		
		JLabel lblPretKomK = new JLabel("");
		lblPretKomK.setForeground(new Color(0, 153, 255));
		lblPretKomK.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblPretKomK.setBackground(Color.GRAY);
		lblPretKomK.setBounds(765, 11, 230, 30);
		pretragaPanelK.add(lblPretKomK);	
		lblStyle.add(lblPretKomK);
		
		JButton btnPretPoGodistuK = new JButton("Pretraga po GODISTU");
		btnPretPoGodistuK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate dOd = datPretKupOd.getDate();
				LocalDate dDo = datPretKupDo.getDate();
				String searchBy = "Godiste";
				String search = txtPretKupGod.getText();
				if (search.equals("")) {
					JOptionPane.showMessageDialog(null, "Polje "+"\"God. Proizvodnje\""+" je OBAVEZNO!", "Greska!", JOptionPane.ERROR_MESSAGE);
				
				}else if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchK(dOd, dDo, searchBy, search, tableK, lblUkupnoK, lblPretKomK);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnPretPoGodistuK.setForeground(new Color(0, 153, 255));
		btnPretPoGodistuK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoGodistuK.setBounds(47, 405, 380, 45);
		pretragaPanelK.add(btnPretPoGodistuK);
		saveBtnFontColor.add(btnPretPoGodistuK);
		
		JButton btnPretPoModeluK = new JButton("Pretraga po MODELU");
		btnPretPoModeluK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate dOd = datPretKupOd.getDate();
				LocalDate dDo = datPretKupDo.getDate();
				String search = comboPretKupModel.getSelectedItem().toString();
				String searchBy = "Model";
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchK(dOd, dDo, searchBy, search, tableK, lblUkupnoK, lblPretKomK);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		btnPretPoModeluK.setForeground(new Color(0, 153, 255));
		btnPretPoModeluK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoModeluK.setBounds(47, 461, 380, 45);
		pretragaPanelK.add(btnPretPoModeluK);
		saveBtnFontColor.add(btnPretPoModeluK);
		
		JButton btnPretPoProdavacK = new JButton("Pretraga po PRODAVCU");
		btnPretPoProdavacK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate dOd = datPretKupOd.getDate();
				LocalDate dDo = datPretKupDo.getDate();
				String search = txtPretKupProdavac.getText();
				String searchBy = "Prodavac";
				if (search.equals("")) {
					JOptionPane.showMessageDialog(null, "Polje "+"\"Prodavac\""+" je OBAVEZNO!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}else if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchK(dOd, dDo, searchBy, search, tableK, lblUkupnoK, lblPretKomK);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnPretPoProdavacK.setForeground(new Color(0, 153, 255));
		btnPretPoProdavacK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoProdavacK.setBounds(47, 517, 380, 45);
		pretragaPanelK.add(btnPretPoProdavacK);
		saveBtnFontColor.add(btnPretPoProdavacK);
		
		JButton btnPretPoVolanK = new JButton("Pretraga po strani VOLANA");
		btnPretPoVolanK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate dOd = datPretKupOd.getDate();
				LocalDate dDo = datPretKupDo.getDate();
				String searchBy = "[Levi/Desni Volan]";
				String search = comboPretKupVolan.getSelectedItem().toString();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchK(dOd, dDo, searchBy, search, tableK, lblUkupnoK, lblPretKomK);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		btnPretPoVolanK.setForeground(new Color(0, 153, 255));
		btnPretPoVolanK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoVolanK.setBounds(47, 573, 380, 45);
		pretragaPanelK.add(btnPretPoVolanK);
		saveBtnFontColor.add(btnPretPoVolanK);
		
		JButton btnPretPoDatK = new JButton("Pretraga po DATUMU sve");
		btnPretPoDatK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate dOd = datPretKupOd.getDate();
				LocalDate dDo = datPretKupDo.getDate();
				if (dDo!=null) {
					try {
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String dateTo = df.format(dDo);
						checkDateformat(dateTo);
						Search.searchByDateK(dOd, dDo, tableK, lblUkupnoK, lblPretKomK);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(null, "\"DATUM DO\""+" nije validan!\nUnesite dd/MM/yyyy format datuma!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}								
			}
		});
		btnPretPoDatK.setForeground(new Color(0, 153, 255));
		btnPretPoDatK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 24));
		btnPretPoDatK.setBounds(47, 629, 380, 45);
		pretragaPanelK.add(btnPretPoDatK);
		saveBtnFontColor.add(btnPretPoDatK);
		
		JButton btnGrandK = new JButton("Click for Grand TOTAL");
		btnGrandK.setForeground(new Color(0, 153, 255));
		btnGrandK.addActionListener(new ActionListener() {
			boolean clicked = false;
			public void actionPerformed(ActionEvent e) {
				if (!clicked) {
					try {
						Statement stmt = DatabaseConnection.konekcioniObj.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT sum(cena) as ukupno  from KupljenaVozila");
						double total = 0;
						while (rs.next()) {
							double total1 = rs.getDouble("ukupno");
							total = total + total1;	
							String t = Double.toString(total);
							btnGrandK.setText("Total = "+t);
							btnGrandK.setForeground(new Color(0,255,0));
							clicked = true;
						}
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}else {
					btnGrandK.setText("Click for Grand TOTAL");
					btnGrandK.setForeground(new Color(0, 153, 255));
					btnGrandK.setOpaque(false);
					clicked = false;
				}
				
			}
		});
		btnGrandK.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 22));
		btnGrandK.setBackground(Color.LIGHT_GRAY);
		btnGrandK.setBounds(1005, 11, 320, 30);
		pretragaPanelK.add(btnGrandK);
		saveBtnFontColor.add(btnGrandK);
		
		JScrollPane scrollTableK = new JScrollPane();
		scrollTableK.setBounds(525, 50, 800, 622);
		scrollTableK.setBackground(new Color(51, 51, 51));
		scrollTableK.setOpaque(false);
		pretragaPanelK.add(scrollTableK);
		
		tableK = new JTable();
		tableK.setForeground(new Color(0, 255, 0));
		scrollTableK.setViewportView(tableK);
		tableK.setFont(new Font("SansSerif", Font.BOLD, 15));
		tableK.setBackground(new Color(102, 102, 102));
		
		JPopupMenu popupK = new JPopupMenu();
		JMenuItem infoK = new JMenuItem("Info");
		infoK.addActionListener(new ActionListener() {						
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableK.getSelectedRow();
				String column8 = tableK.getModel().getValueAt(row, 8).toString();
				 // create a JTextArea
			      JTextArea textArea = new JTextArea(8, 40);
			      textArea.setWrapStyleWord(true);
			      textArea.setLineWrap(true);
			      Font font = new Font("Sans Serif",Font.PLAIN, 15);
			      textArea.setFont(font);
			      textArea.setText(column8);
			      textArea.setEditable(false);
			      
			      // wrap a scrollpane around txtArea
			      JScrollPane scrollPane = new JScrollPane(textArea);						      
			      // display in a message dialog
			      JOptionPane.showMessageDialog(scrollTableK, scrollPane, "Napomena",JOptionPane.INFORMATION_MESSAGE);							
			}
		});
		popupK.add(infoK);
		JMenuItem brisiK = new JMenuItem("Obrisi");
		brisiK.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableK.getSelectedRow();
				String id = tableK.getModel().getValueAt(row, 0).toString();
				
		        int result = JOptionPane.showConfirmDialog(null, "Deo ce biti trajno obrisan!!!", "Potvrda Brisanja Kupljenog dela!", JOptionPane.YES_NO_OPTION);
			      if (result == JOptionPane.YES_OPTION) {			    	  
				    	  
			    	  	try {
				    		  Baza.BrisanjeKupljeno(id);
						} catch (SQLException ex) {
							
							ex.printStackTrace();
						}			         
			      }
			}
		});
		popupK.add(brisiK);
		JMenuItem izmenaK = new JMenuItem("Izmeni");
		izmenaK.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					int row = tableK.getSelectedRow();
					String getId = tableK.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(getId);									
				try {
					
				} catch (Exception e2) {
					
				}			
			}
		});
//		popupK.add(izmenaK);
		tableK.setComponentPopupMenu(popupK);
		tableFontColor.add(tableK);
		
		
		///////////////////////////////////*********************** SERVISI PANEL
		
		Servisi.servisi(tabPane);
		
			/////*** creating lists from Servisi class, for color change 
		Servisi s = new Servisi();
		ArrayList<JLabel> lServisi = s.getlblServisi();
		ArrayList<JTable> tableFont = s.getTableFont();
		ArrayList<JTextField> inputFontColor = s.getinputFontColorS();
		ArrayList<JComboBox> comboFontColor = s.getComboFontColor();
		ArrayList<JButton> btnFontColor = s.getSaveFontColor();		
		//////////////////////////////////************************************************************************
		
		
						////////****** creating right click color change options
		
		JMenuItem mitmChooseBorderColor = new JMenuItem("Choose Border Color");
		mitmChooseBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		Color initialcolor = Color.RED; 				  
		        // color chooser Dialog Box 
		Color color = JColorChooser.showDialog(mitmChooseBorderColor, "Select a color",initialcolor);
			if (color!=null) {								
			    
				for (JLabel label : lblStyle) {
					label.setBackground(color);
				}	
				for (JLabel l : lServisi) {
					l.setBackground(color);
				}	
			    try {
			    	Properties properties = new Properties();
				    OutputStream outStream = new FileOutputStream("lblColorSettings.properties");
			
				    properties.setProperty("color", Integer.toString(color.getRGB()));
				    properties.store(outStream, "RGB kod za lblColor");				   
				} catch (Exception e) {
				
				}
			    	
			 }
		    		 
			}
		});
		mitmChooseBorderColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mitmChooseBorderColor.setForeground(new Color(0, 153, 255));
		mitmChooseBorderColor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupLookChange.add(mitmChooseBorderColor);
		
		JMenuItem mitmChooseInputFontColor = new JMenuItem("Choose Input Field Font Color");
		mitmChooseInputFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialcolor = new Color(0,153,255); 			
			      // color chooser Dialog Box 
				Color color = JColorChooser.showDialog(mitmChooseInputFontColor, "Select a color",initialcolor);
				if (color!=null) {
					for (JTextField txtField : inputTxtFontColor) {
						txtField.setForeground(color);
					}
					for (JComboBox<String> combo : inputComboFontColor) {
						combo.setForeground(color);
					}
					for (JTextArea area : inputTxtAreaFontColor) {
						area.setForeground(color);
					}
					for (JTextField txtField : inputFontColor) {
						txtField.setForeground(color);
					}
					for (JComboBox<String> combo : comboFontColor) {
						combo.setForeground(color);
					}			
					try {
						Properties p = new Properties();
						OutputStream outTxtFont = new FileOutputStream("txtFieldFontColor.properties");
						
						p.setProperty("txtFieldFontColor", Integer.toString(color.getRGB()));
						p.store(outTxtFont, "RGB for txtFontColor");
					} catch (Exception e2) {
						
					}
				}
			}
		});
		mitmChooseInputFontColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mitmChooseInputFontColor.setForeground(new Color(0, 153, 255));
		mitmChooseInputFontColor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupLookChange.add(mitmChooseInputFontColor);
		
		JMenuItem mitmChooseLabelFontColor = new JMenuItem("Choose Label Font Color");
		mitmChooseLabelFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialcolor = new Color(0,153,255); 				  
		        // color chooser Dialog Box 
		Color color = JColorChooser.showDialog(mitmChooseLabelFontColor, "Select a color",initialcolor);
			if (color!=null) {								
				
				for (JLabel label : lblStyle) {
					label.setForeground(color);
				}
				for (JLabel labelServisi : lServisi) {  	/////////*********servisi tab izmena boje
					labelServisi.setForeground(color);
				}
				tabPane.setForeground(color);
			    try {
			    	Properties properties = new Properties();
				    OutputStream outStream = new FileOutputStream("lblFontColorSet.properties");
			    	
				    properties.setProperty("lblFont", Integer.toString(color.getRGB()));
				    properties.store(outStream, "RGB kod za lblFontColor");					   
				} catch (Exception ex) {
				
				}
			    
			   
			 }
			}
		});
		mitmChooseLabelFontColor.setForeground(new Color(0, 153, 255));
		mitmChooseLabelFontColor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupLookChange.add(mitmChooseLabelFontColor);
		
		JMenuItem mitmChooseTableFontColor = new JMenuItem("Choose Table Font Color");
		mitmChooseTableFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialcolor = new Color(0,153,255); 				  

				Color color = JColorChooser.showDialog(mitmChooseTableFontColor, "Select a color",initialcolor);
				if (color!=null) {
					for (JTable table : tableFontColor) {
						table.setForeground(color);
					}
					for (JTable table : tableFont) {
						table.setForeground(color);
					}			
					try {
						Properties properties = new Properties();					
						OutputStream outStreamT = new FileOutputStream("tableFontColorSet.properties");
						
						 properties.setProperty("tableFont", Integer.toString(color.getRGB()));
						 properties.store(outStreamT, "RGB za tableFont");
					} catch (Exception ex) {					
						ex.printStackTrace();
					}
					
				}
			}
		});
		mitmChooseTableFontColor.setForeground(new Color(0, 153, 255));
		mitmChooseTableFontColor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupLookChange.add(mitmChooseTableFontColor);
		
		JMenuItem mitmChooseButtonFontColor = new JMenuItem("Choose Button Font Color");
		mitmChooseButtonFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color initialcolor = new Color(0,153,255); 				  
		 
				Color color = JColorChooser.showDialog(mitmChooseTableFontColor, "Select a color",initialcolor);
				if (color!=null) {
					for (JButton button : saveBtnFontColor) {
						button.setForeground(color);
					}
					for (JButton button : btnFontColor) {
						button.setForeground(color);
					}			
					try {
						Properties p = new Properties();
						OutputStream outButton = new FileOutputStream("btnFontColor.properties");
						
						p.setProperty("btnFontColor", Integer.toString(color.getRGB()));
						p.store(outButton, "RGB for button font color");
					} catch (Exception e2) {
						
					}
				}
				
			}
		});
		mitmChooseButtonFontColor.setForeground(new Color(0, 153, 255));
		mitmChooseButtonFontColor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		popupLookChange.add(mitmChooseButtonFontColor);
		

		try {
			InputStream inStyle = new FileInputStream("lblColorSettings.properties");	//// Color settings for Style labels
			InputStream inLblFont = new FileInputStream("lblFontColorSet.properties");	///  **************for Labels font 
			InputStream inTableFont = new FileInputStream("tableFontColorSet.properties"); ///*************for tables font
			InputStream inTxtFieldFont = new FileInputStream("txtFieldFontColor.properties"); ///**********for txtFields,txtArea,combo font
			InputStream inBtnFont = new FileInputStream("btnFontColor.properties");			  ///********** for buttons
			Properties p = new Properties();
			p.load(inStyle);
			p.load(inLblFont);
			p.load(inTableFont);
			p.load(inTxtFieldFont);
			p.load(inBtnFont);
			Color cStyle = new Color(Integer.parseInt(p.getProperty("color")));
			Color cLblFont = new Color(Integer.parseInt(p.getProperty("lblFont")));
			Color cTableFont = new Color(Integer.parseInt(p.getProperty("tableFont")));
			Color cTxtFieldFont = new Color(Integer.parseInt(p.getProperty("txtFieldFontColor")));
			Color cBtnFont = new Color(Integer.parseInt(p.getProperty("btnFontColor")));
			for (JLabel label : lblStyle) {
				label.setBackground(cStyle);
				label.setForeground(cLblFont);
			}
			for (JLabel label : lServisi) {		/// servisi style and label font color
				label.setBackground(cStyle);
				label.setForeground(cLblFont);
			}
			for (JTable table : tableFontColor) {
				table.setForeground(cTableFont);
			}
			for (JTable table : tableFont) {		/// servisi table font color
				table.setForeground(cTableFont);
			}
			for (JTextField txtField : inputTxtFontColor) {
				txtField.setForeground(cTxtFieldFont);
			}
			for (JTextField txtField : inputFontColor) {		/// servisi input font color
				txtField.setForeground(cTxtFieldFont);
			}
			for (JComboBox<String> combo : inputComboFontColor) {
				combo.setForeground(cTxtFieldFont);
			}
			for (JComboBox<String> combo : comboFontColor) {		/// servisi combo font color
				combo.setForeground(cTxtFieldFont);
			}
			for (JTextArea area : inputTxtAreaFontColor) {
				area.setForeground(cTxtFieldFont);
			}
			for (JButton button : saveBtnFontColor) {
				button.setForeground(cBtnFont);
			}
			for (JButton button : btnFontColor) {				/// servisi combo font color
				button.setForeground(cBtnFont);
			}
			tabPane.setForeground(cLblFont);
		} catch (Exception e2) {
			
		}
	}
}
