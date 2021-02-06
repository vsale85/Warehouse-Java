package popups;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import main.Baza;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class PopupMSpretraga extends JFrame{
		
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

		/**
		 * @wbp.parser.entryPoint
		 */
		public PopupMSpretraga(JTable tableMSunos, JLabel kom,JLabel brt, JLabel neto, JLabel razlika) {
			
			setTitle("Pretraga Malih Servisa");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) screenSize.getWidth();
			int height = (int) screenSize.getHeight();
			setSize((int) (width*0.8),(int) (height*0.9));
			getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
			
				JPanel panelMSunos = new JPanel();
		
		        getContentPane().add(panelMSunos);
				
						
			
				tableMSunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableMSunos.setFont(new Font("SansSerif", Font.BOLD, 14));
				tableMSunos.setBackground(new Color(102, 102, 102));
				panelMSunos.setLayout(new BorderLayout(0, 0));
				
				JPopupMenu popup = new JPopupMenu();
				JMenuItem info = new JMenuItem("Napomena");
				info.addActionListener(new ActionListener() {						
					@Override
					public void actionPerformed(ActionEvent e) {
						int row = tableMSunos.getSelectedRow();
						String column7 = tableMSunos.getModel().getValueAt(row, 16).toString();
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
					      JOptionPane.showMessageDialog(tableMSunos, scrollPane, "Napomena",JOptionPane.INFORMATION_MESSAGE);							
					}
				});
				popup.add(info);
				JMenuItem brisi = new JMenuItem("Obrisi");
				brisi.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						int row = tableMSunos.getSelectedRow();
						String id = tableMSunos.getModel().getValueAt(row, 0).toString();
						
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
			//	popup.add(brisi);
				JMenuItem izmena = new JMenuItem("Izmena");
				izmena.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
							int row = tableMSunos.getSelectedRow();
							String getId = tableMSunos.getModel().getValueAt(row, 0).toString();
							int id = Integer.parseInt(getId);				
							String kup = tableMSunos.getModel().getValueAt(row, 3).toString();
							String voz = tableMSunos.getModel().getValueAt(row, 4).toString();
							String stat = tableMSunos.getModel().getValueAt(row, 5).toString();
							String nap = tableMSunos.getModel().getValueAt(row, 16).toString();
															
						try {					
							PopupUpdateMS.popUp(id, kup, voz, stat, nap);		
						} catch (Exception e2) {
						
						}
						JOptionPane.showMessageDialog(null, "Promene su uspesno sacuvane!");	
					}
				});
				popup.add(izmena);
				tableMSunos.setComponentPopupMenu(popup);
				
				JScrollPane scrollTableMSunos = new JScrollPane();
				scrollTableMSunos.setBounds(525, 50, 800, 622);
				scrollTableMSunos.setBackground(new Color(51, 51, 51));
				panelMSunos.add(scrollTableMSunos, BorderLayout.CENTER);
				scrollTableMSunos.setViewportView(tableMSunos);
				
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(0, 4, 0, 0));
				panelMSunos.add(panel, BorderLayout.NORTH);
				
				panel.add(kom);			
				panel.add(brt);		
				panel.add(neto);		
				panel.add(razlika);
								
				
				setVisible(true);
		}
}
