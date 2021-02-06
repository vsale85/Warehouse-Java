package model;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.sql.*;
public class DatabaseConnection {
	
	
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
     //       JOptionPane.showMessageDialog(null, "Uspesna konekcija ka bazi");
            System.out.println("Uspena konekcija. DB connection class!");
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

		
}
