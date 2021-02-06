package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class Login {
	

	private JFrame frameLogin;
	private JTextField username;
	private JPasswordField password;
	public static Connection konekcioniObj;
	
	public static Connection KonekcijaKaBazi() throws ClassNotFoundException {
		try {
	        UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
	    } catch (Exception e) {
	         System.out.println("Look and feel not started");
	    }
        String username = "sa";
        String password = "111111";
        String dbName = "GoranoParts";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;instanceName=SQLEXPRESS;"
                + "DatabaseName=" + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
           
            System.out.println("Uspena konekcija!");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Nema konekcije sa bazom!!!", "E R R O R !", JOptionPane.ERROR_MESSAGE);

        }
        return conn;
    }

	
    static {
        try {
            konekcioniObj = KonekcijaKaBazi();
            
        } catch (ClassNotFoundException ex) {
            
        }
    }   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}								

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		
	}				

	/**
	 * Initialize the contents of the frame.
	 */
		
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		frameLogin.setTitle("Please Login . . . ");
		frameLogin.setBounds(100, 100, 500, 340);
		frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);
		frameLogin.setResizable(false);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(0, 153, 255));
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblUsername.setBounds(60, 11, 150, 40);
		frameLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 153, 255));
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblPassword.setBounds(60, 69, 150, 40);
		frameLogin.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setToolTipText("Enter your Username");
		username.setForeground(new Color(0, 153, 255));
		username.setFont(new Font("SansSerif", Font.PLAIN, 22));
		username.setBounds(230, 11, 200, 40);
		frameLogin.getContentPane().add(username);
		username.setColumns(10);
		
		
		password = new JPasswordField();
		password.setToolTipText("Enter your password");
		password.setForeground(new Color(0, 153, 255));
		password.setFont(new Font("SansSerif", Font.PLAIN, 22));
		password.setBounds(230, 70, 200, 40);
		frameLogin.getContentPane().add(password);
		
		
		JButton btnLogin = new JButton("L o g i n");
		btnLogin.setForeground(new Color(0, 153, 255));
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 22));
		frameLogin.getRootPane().setDefaultButton(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///// test login bez baze
			/*	String user = "admin";
				String pass = "admin";
				
				if (username.getText().equals(user)&&password.getText().equals(pass)) {
					Warehouse warehouseMain = new Warehouse();
					warehouseMain.setJFrameVisible(true);
					frameLogin.dispose();
					JOptionPane.showMessageDialog(null, "Welcome Goran !");       
				}			*/	
	////////////****************************** 	
				 String naredba1 = "SELECT * from Korisnik where username=? and password=?";   
				
				
				try {
					
					PreparedStatement pst = konekcioniObj.prepareStatement(naredba1);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					
					ResultSet rs1 = pst.executeQuery();
				
					
					if (rs1.next()) {
						Warehouse warehouseMain = new Warehouse();
						warehouseMain.setJFrameVisible(true);
						/////////////////////////////////////  CODE za dodavanje imena korisnika u welcome poruci 
						String ime = null;
						Statement stmt2 = konekcioniObj.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT [ime] as ime "
															+ "from Korisnik where username ='"+username.getText()+"'"
															+ " and password='"+password.getText()+"'");
						while (rs2.next()) {
							ime = rs2.getString("ime");
												
						}
						frameLogin.dispose();					
						JOptionPane.showMessageDialog(null, "Welcome " +ime+ "!");       
						
					}else {
						JOptionPane.showMessageDialog(null, "Pogresan USERNAME ili PASSWORD !!!");
						username.setText("");
						password.setText("");
					}
				
				} catch (SQLException e1) {
			
					e1.printStackTrace();
				}			
		
	
			}												
		});
		btnLogin.setBounds(61, 235, 369, 45);
		frameLogin.getContentPane().add(btnLogin);
		
		JCheckBox showPass = new JCheckBox("Show password");
		showPass.setForeground(new Color(0, 153, 255));
		showPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
		showPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == showPass) {
		            if (showPass.isSelected()) {
		                password.setEchoChar((char) 0);
		            } else {
		                password.setEchoChar('*');
		            }
		 
		 
		        }
			}
		});
		showPass.setBounds(338, 203, 150, 25);
		frameLogin.getContentPane().add(showPass);
		
		
		
		
		JLabel s1 = new JLabel("");		
		s1.setBackground(Color.WHITE);
		s1.setBounds(60, 62, 370, 3);
		s1.setOpaque(false);
		frameLogin.getContentPane().add(s1);
		
		JLabel s2 = new JLabel("");
		s2.setOpaque(false);
		s2.setBackground(Color.WHITE);
		s2.setBounds(60, 120, 370, 3);
		frameLogin.getContentPane().add(s2);
		
		JLabel logBackground = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo-login.jpg")).getImage();
		logBackground.setIcon(new ImageIcon(img));
		logBackground.setBounds(0, 0, 495, 290);
		frameLogin.getContentPane().add(logBackground);
		
		
		frameLogin.setVisible(true);
	}
}
