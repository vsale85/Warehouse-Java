package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import main.Baza;
import main.Delovi;
import main.Korisnik;
import main.Warehouse;
import net.proteanit.sql.DbUtils;

public class Search extends Baza{

	/**
	 * @wbp.parser.entryPoint
	 */							//////////****************** PRETRAGA KUPOVINE PO KRITERIJUMIMA
	public static void searchK(LocalDate dOd,LocalDate dDo ,String searchBy,String search,JTable t,JLabel lU,JLabel lK) {

		double sum = 0;
		if (dOd==null) {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
			
			String naredba = "SELECT [id], [Datum kupovine], [Model],[Vrsta goriva],"
					+" [Godiste], [Levi/Desni Volan],[Prodavac], [Cena], [Napomena] from KupljenaVozila "
					+ "WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]<='"+datumDo+"' ORDER BY [Datum kupovine] DESC";
			try {
				Statement stm = konekcioniObj.createStatement();
				ResultSet rs1 = stm.executeQuery(naredba);							
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stm.executeQuery("SELECT SUM(Cena) as ukupno FROM KupljenaVozila WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]<='"+datumDo+"'");
				
				while (rs2.next()) {
					double c = rs2.getDouble("ukupno");
					sum = sum + c;
					String suma = Double.toString(sum);
					lU.setText("Ukupno = "+suma);
				}
				rs2 = stm.executeQuery("SELECT COUNT(*) as count FROM KupljenaVozila WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]<='"+datumDo+"'");
				rs2.next();
				int count1 = rs2.getInt("count");
				String count = Integer.toString(count1);
				lK.setText("Ukupno komada = "+count);								
			} catch (Exception e2) {

			}
		}else {				
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
	
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT [id], [Datum kupovine], [Model],[Vrsta goriva],[Godiste], [Levi/Desni Volan],[Prodavac], [Cena], [Napomena]"
					+"  FROM KupljenaVozila WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]>='"+datumOd+"' AND [Datum kupovine]<='"+datumDo+"' ORDER BY [Datum kupovine] DESC";
			try {
				Statement stm = konekcioniObj.createStatement();
				ResultSet rs1 = stm.executeQuery(naredba);
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stm.executeQuery("SELECT SUM(Cena) as ukupno FROM KupljenaVozila "
						+ "WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]>='"+datumOd+"' AND [Datum kupovine]<='"+datumDo+"'");
				while (rs2.next()) {
					double c = rs2.getDouble("ukupno");
					sum = sum + c;
					String suma = Double.toString(sum);
					lU.setText("Ukupno = "+suma);
				}
				rs2 = stm.executeQuery("SELECT COUNT(*) as count FROM KupljenaVozila "
						+ "WHERE " + searchBy + " = '"+search+"' AND [Datum kupovine]>='"+datumOd+"' AND [Datum kupovine]<='"+datumDo+"'");
				rs2.next();
				int count1 = rs2.getInt("count");
				String count = Integer.toString(count1);
				lK.setText("Ukupno komada = "+count);
			} catch (Exception e2) {
			
			}
		}

	}
						//////////////////************************ PRETRAGA PRODAJE PO KRITERIJUMIMA
	public static void searchP(LocalDate dOd, LocalDate dDo, String searchBy, String search, JTable t, JLabel lU, JLabel lK) {
			
		double sum = 0;	
		
		if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
					
		String naredba = "SELECT [id], [naziv], [datum],[kupac], [cena], [model],[status], [napomena] from Delovi "
				+ " where "+searchBy+" LIKE '%"+search+"%' AND datum <='"+datumDo+"' ORDER BY datum DESC";	// search by containing word
		try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));	

			ResultSet rs2 = stmt.executeQuery("SELECT sum(cena) as ukupnoProd from Delovi WHERE "+searchBy+" LIKE '%"+search+"%'AND datum <='"+datumDo+"'");
			while(rs2.next()) {
				double c = rs2.getDouble("ukupnoProd");
				sum = sum + c;
				String suma = Double.toString(sum);
				lU.setText("Ukupno = "+suma);
			}
			if (searchBy.equals("naziv")||searchBy.equals("model")||searchBy.equals("kupac")) {
				rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM DELOVI WHERE "+searchBy+" LIKE '%"+search+"%'AND datum <='"+datumDo+"'");
				rs2.next();
				int count1 = rs2.getInt("countNum");
				String count = Integer.toString(count1);
				lK.setText("Ukupno komada= "+count);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 				
		}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	

			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT [id], [naziv], [datum],[kupac],[cena],[model],[status],[napomena] from Delovi "
					+ " where "+searchBy+" LIKE '%"+search+"%' AND datum >= '"+datumOd+"' AND datum <='"+datumDo+"' ORDER BY datum DESC";	// search by containing word
			try {
				Statement stmt = konekcioniObj.createStatement();
				ResultSet rs1 = stmt.executeQuery(naredba);						
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stmt.executeQuery("SELECT sum(cena) as ukupnoProd from Delovi"
						+ " where "+searchBy+" LIKE '%"+search+"%' AND datum >= '"+datumOd+"' AND datum <='"+datumDo+"'");
				while(rs2.next()) {
					double c = rs2.getDouble("ukupnoProd");
					sum = sum + c;
					String suma = Double.toString(sum);
					lU.setText("Ukupno = "+suma);
				}
				if (searchBy.equals("naziv")||searchBy.equals("model")||searchBy.equals("kupac")) {
					rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM DELOVI WHERE "+searchBy+" LIKE '%"+search+"%' AND datum >= '"+datumOd+"' AND datum <='"+datumDo+"'");
					rs2.next();
					int count1 = rs2.getInt("countNum");
					String count = Integer.toString(count1);
					lK.setText("Ukupno komada= "+count);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 	
		}
	}
						//////////////////////////////***************  PRETRAGA PRODAJE SAMO PO DATUMIMA
	public static void searchByDateP(LocalDate dOd,LocalDate dDo,JTable t,JLabel lU,JLabel lK) {
		
		double sum = 0;
		
		if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
					
		String naredba = "SELECT [id], [naziv], [datum],[kupac], [cena], [model],[status], [napomena] from Delovi WHERE datum <='"+datumDo+"' ORDER BY datum DESC";				
		try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));					
			ResultSet rs2 = stmt.executeQuery("SELECT sum(cena) as ukupnoProd from Delovi WHERE datum <='"+datumDo+"'");
			while(rs2.next()) {
				double c = rs2.getDouble("ukupnoProd");
				sum = sum + c;
				String suma = Double.toString(sum);
				lU.setText("Ukupno = "+suma);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 				
		}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
	
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT [id], [naziv], [datum],[kupac],[cena],[model],[status],[napomena] from Delovi"
					+ " where datum >= '"+datumOd+"' AND datum <='"+datumDo+"' ORDER BY datum DESC";				
			try {
				Statement stmt = konekcioniObj.createStatement();
				ResultSet rs1 = stmt.executeQuery(naredba);						
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stmt.executeQuery("SELECT sum(cena) as ukupnoProd from Delovi"
						+ " where datum >= '"+datumOd+"' AND datum <='"+datumDo+"'");
				while(rs2.next()) {
					double c = rs2.getDouble("ukupnoProd");
					sum = sum + c;
					String suma = Double.toString(sum);
					lU.setText("Ukupno = "+suma);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 	
		}
	}
	
										//////////////////////////////***************  PRETRAGA KUPOVINE SAMO PO DATUMIMA
	public static void searchByDateK(LocalDate dOd,LocalDate dDo,JTable t,JLabel lU,JLabel lK) {
		
		double sum = 0;
		
		if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
			
		String naredba = "SELECT [id], [Datum kupovine], [Model],[Vrsta goriva],[Godiste], [Levi/Desni Volan],[Prodavac], [Cena], [Napomena] FROM KupljenaVozila  "
				+ "WHERE [Datum Kupovine] <='"+datumDo+"' ORDER BY [Datum kupovine] DESC";				
		try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));					
			ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as ukupnoKup from KupljenaVozila WHERE [Datum Kupovine] <='"+datumDo+"'");
			while(rs2.next()) {
				double c = rs2.getDouble("ukupnoKup");
				sum = sum + c;
				String suma = Double.toString(sum);
				lU.setText("Ukupno = "+suma);
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM KupljenaVozila WHERE [Datum Kupovine] <='"+datumDo+"'");      
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			lK.setText("Ukupno komada = "+count);	
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 				
		}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
	
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT [id], [Datum kupovine], [Model],[Vrsta goriva],[Godiste], [Levi/Desni Volan],[Prodavac], [Cena], [Napomena] FROM KupljenaVozila"
					+ " where [Datum Kupovine] >= '"+datumOd+"' AND [Datum Kupovine] <='"+datumDo+"' ORDER BY [Datum kupovine] DESC";				
			try {
				Statement stmt = konekcioniObj.createStatement();
				ResultSet rs1 = stmt.executeQuery(naredba);						
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as ukupnoKup from KupljenaVozila"
						+ " where [Datum Kupovine] >= '"+datumOd+"' AND [Datum Kupovine] <='"+datumDo+"'");
				while(rs2.next()) {
					double c = rs2.getDouble("ukupnoKup");
					sum = sum + c;
					String suma = Double.toString(sum);
					lU.setText("Ukupno = "+suma);
				}
				rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM KupljenaVozila WHERE [Datum Kupovine] >= '"+datumOd+"' AND [Datum Kupovine] <='"+datumDo+"'");
				rs2.next();
				int count1 = rs2.getInt("count");
				String count = Integer.toString(count1);
				lK.setText("Ukupno komada = "+count);	
			} catch (SQLException e1) {

				e1.printStackTrace();
			} 	
		}
		
	}	
	
	//////////////////////////////***************  PRETRAGA MALIH SERVISA SAMO PO DATUMIMA
			public static void searchByDateMS(LocalDate dOd,LocalDate dDo,JTable t, JLabel kom, JLabel brt, JLabel neto, JLabel razlika) {        
			
				double sumN = 0;	
				double sumB = 0;
				double sumR = 0;
			
			if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
			
			String naredba = "SELECT * FROM MaliServis  "
			+ "WHERE Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));					
			ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from MaliServis WHERE Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				double n = rs2.getDouble("neto");
				sumB = sumB + b;
				sumN = sumN + n;
				
				String sumaB = Double.toString(sumB);
				String sumaN = Double.toString(sumN);
				
				brt.setText("Ukupno bruto = "+sumaB);
				neto.setText("Ukupno neto = "+sumaN);
				sumR = sumB - sumN;
				razlika.setText("Ukupno razlika = "+sumR);	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM MaliServis WHERE Datum <='"+datumDo+"'");      
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);
			} catch (SQLException e1) {
			e1.printStackTrace();
			} 				
			}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
			
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT * FROM MaliServis"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));
			ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from MaliServis"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				double n = rs2.getDouble("neto");
				sumB = sumB + b;
				sumN = sumN + n;
				
				String sumaB = Double.toString(sumB);
				String sumaN = Double.toString(sumN);
				
				brt.setText("Ukupno bruto = "+sumaB);
				neto.setText("Ukupno neto = "+sumaN);
				sumR = sumB - sumN;
				razlika.setText("Ukupno razlika = "+sumR);	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM MaliServis WHERE Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);			
			} catch (SQLException e1) {

			e1.printStackTrace();
			} 	
			}
			
}	
										////////////************** PRETRAGA MALIH SERVISA PO KRITERIJUMIMA
			
			public static void searchMS(LocalDate dOd, LocalDate dDo, String searchBy, String search, JTable t, JLabel kom, JLabel brt, JLabel neto, JLabel razlika) {  
				
				double sumN = 0;	
				double sumB = 0;
				double sumR = 0;
				if(dOd==null){	
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumDo = dateF.format(dDo);	
							
				String naredba = "SELECT * from MaliServis "
						+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
				try {
					Statement stmt = konekcioniObj.createStatement();
					ResultSet rs1 = stmt.executeQuery(naredba);						
					t.setModel(DbUtils.resultSetToTableModel(rs1));	
					ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from MaliServis WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
					while(rs2.next()) {
						double b = rs2.getDouble("bruto");
						double n = rs2.getDouble("neto");
						sumB = sumB + b;
						sumN = sumN + n;
						
						String sumaB = Double.toString(sumB);
						String sumaN = Double.toString(sumN);
						
						brt.setText("Ukupno bruto = "+sumaB);
						neto.setText("Ukupno neto = "+sumaN);
						sumR = sumB - sumN;
						razlika.setText("Ukupno razlika = "+sumR);												
					}
						rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM MaliServis WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
						rs2.next();
						int count1 = rs2.getInt("countNum");
						String count = Integer.toString(count1);
						kom.setText("Ukupno komada = "+count);
				
								
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 				
				}else {
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumOd = dateF.format(dOd);	
					String datumDo = dateF.format(dDo);	

					Warehouse.checkDateformat(datumOd);
					Warehouse.checkDateformat(datumDo);
					String naredba = "SELECT * from MaliServis "
							+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
					try {
						Statement stmt = konekcioniObj.createStatement();
						ResultSet rs1 = stmt.executeQuery(naredba);						
						t.setModel(DbUtils.resultSetToTableModel(rs1));
						ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from MaliServis"
								+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
						while(rs2.next()) {
							double b = rs2.getDouble("bruto");
							double n = rs2.getDouble("neto");
							sumB = sumB + b;
							sumN = sumN + n;
							
							String sumaB = Double.toString(sumB);
							String sumaN = Double.toString(sumN);
							
							brt.setText("Ukupno bruto = "+sumaB);
							neto.setText("Ukupno neto = "+sumaN);
							sumR = sumB - sumN;
							razlika.setText("Ukupno razlika = "+sumR);	
						}
						
							rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM MaliServis WHERE "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
							rs2.next();
							int count1 = rs2.getInt("countNum");
							String count = Integer.toString(count1);
							kom.setText("Ukupno komada = "+count);
									
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 	
				}
			}
			
			/////////////////////////////////////////******************** PRETRAGA POPRAVKI PO DATUMU
			
			public static void searchByDatePopravke(LocalDate dOd,LocalDate dDo,JTable t, JLabel kom, JLabel brt, JLabel neto, JLabel razlika) {        
				
				double sumN = 0;	
				double sumB = 0;
				double sumR = 0;
			
			if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
			
			String naredba = "SELECT * FROM Popravke  "
			+ "WHERE Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));					
			ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from Popravke WHERE Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				double n = rs2.getDouble("neto");
				sumB = sumB + b;
				sumN = sumN + n;
				
				String sumaB = Double.toString(sumB);
				String sumaN = Double.toString(sumN);
				
				brt.setText("Ukupno bruto = "+sumaB);
				neto.setText("Ukupno neto = "+sumaN);
				sumR = sumB - sumN;
				razlika.setText("Ukupno razlika = "+sumR);	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Popravke WHERE Datum <='"+datumDo+"'");      
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);
			} catch (SQLException e1) {
			e1.printStackTrace();
			} 				
			}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
			
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT * FROM Popravke"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));
			ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from Popravke"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				double n = rs2.getDouble("neto");
				sumB = sumB + b;
				sumN = sumN + n;
				
				String sumaB = Double.toString(sumB);
				String sumaN = Double.toString(sumN);
				
				brt.setText("Ukupno bruto = "+sumaB);
				neto.setText("Ukupno neto = "+sumaN);
				sumR = sumB - sumN;
				razlika.setText("Ukupno razlika = "+sumR);	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Popravke WHERE Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);			
			} catch (SQLException e1) {

			e1.printStackTrace();
			} 	
			}
			
			}	
			
			public static void searchPopravke(LocalDate dOd, LocalDate dDo, String searchBy, String search, JTable t, JLabel kom, JLabel brt, JLabel neto, JLabel razlika) {  
				
				double sumN = 0;	
				double sumB = 0;
				double sumR = 0;
				if(dOd==null){	
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					String datumDo = dateF.format(dDo);	
							
				String naredba = "SELECT * from Popravke "
						+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
				try {
					Statement stmt = konekcioniObj.createStatement();
					ResultSet rs1 = stmt.executeQuery(naredba);						
					t.setModel(DbUtils.resultSetToTableModel(rs1));	
					ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from Popravke WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
					while(rs2.next()) {
						double b = rs2.getDouble("bruto");
						double n = rs2.getDouble("neto");
						sumB = sumB + b;
						sumN = sumN + n;
						
						String sumaB = Double.toString(sumB);
						String sumaN = Double.toString(sumN);
						
						brt.setText("Ukupno bruto = "+sumaB);
						neto.setText("Ukupno neto = "+sumaN);
						sumR = sumB - sumN;
						razlika.setText("Ukupno razlika = "+sumR);												
					}
						rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Popravke WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
						rs2.next();
						int count1 = rs2.getInt("countNum");
						String count = Integer.toString(count1);
						kom.setText("Ukupno komada = "+count);
				
								
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 				
				}else {
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumOd = dateF.format(dOd);	
					String datumDo = dateF.format(dDo);	

					Warehouse.checkDateformat(datumOd);
					Warehouse.checkDateformat(datumDo);
					String naredba = "SELECT * from Popravke "
							+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
					try {
						Statement stmt = konekcioniObj.createStatement();
						ResultSet rs1 = stmt.executeQuery(naredba);						
						t.setModel(DbUtils.resultSetToTableModel(rs1));
						ResultSet rs2 = stmt.executeQuery("SELECT sum([Cena bruto]) as bruto, sum([Cena neto]) as neto from Popravke"
								+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
						while(rs2.next()) {
							double b = rs2.getDouble("bruto");
							double n = rs2.getDouble("neto");
							sumB = sumB + b;
							sumN = sumN + n;
							
							String sumaB = Double.toString(sumB);
							String sumaN = Double.toString(sumN);
							
							brt.setText("Ukupno bruto = "+sumaB);
							neto.setText("Ukupno neto = "+sumaN);
							sumR = sumB - sumN;
							razlika.setText("Ukupno razlika = "+sumR);	
						}
						
							rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Popravke WHERE "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
							rs2.next();
							int count1 = rs2.getInt("countNum");
							String count = Integer.toString(count1);
							kom.setText("Ukupno komada = "+count);
									
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 	
				}
			}
			
			public static void searchByDateVulk(LocalDate dOd,LocalDate dDo,JTable t, JLabel kom, JLabel brt) {         
					
				double sumB = 0;
			
			if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
			
			String naredba = "SELECT * FROM Vulkaniziranje  "
			+ "WHERE Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));					
			ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto from Vulkaniziranje WHERE Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				sumB = sumB + b;				
				String sumaB = Double.toString(sumB);
				
				brt.setText("Ukupno bruto = "+sumaB);
	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Vulkaniziranje WHERE Datum <='"+datumDo+"'");      
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);
			} catch (SQLException e1) {
			e1.printStackTrace();
			} 				
			}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	
			
			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT * FROM Vulkaniziranje"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";				
			try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));
			ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto from Vulkaniziranje"
			+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");
				sumB = sumB + b;
				String sumaB = Double.toString(sumB);
				
				brt.setText("Ukupno bruto = "+sumaB);
	
			}
			rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Vulkaniziranje WHERE Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
			rs2.next();
			int count1 = rs2.getInt("count");
			String count = Integer.toString(count1);
			kom.setText("Ukupno komada = "+count);			
			} catch (SQLException e1) {

			e1.printStackTrace();
			} 	
			}
			
			}	
			
			
public static void searchVulk(LocalDate dOd, LocalDate dDo, String searchBy, String search, JTable t, JLabel kom, JLabel brt) {  
					
				double sumB = 0;

				if(dOd==null){	
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumDo = dateF.format(dDo);	
							
				String naredba = "SELECT * from Vulkaniziranje "
						+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
				try {
					Statement stmt = konekcioniObj.createStatement();
					ResultSet rs1 = stmt.executeQuery(naredba);						
					t.setModel(DbUtils.resultSetToTableModel(rs1));	
					ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto from Vulkaniziranje WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
					while(rs2.next()) {
						double b = rs2.getDouble("bruto");
						sumB = sumB + b;						
						String sumaB = Double.toString(sumB);
						
						brt.setText("Ukupno bruto = "+sumaB);
											
					}
						rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Vulkaniziranje WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
						rs2.next();
						int count1 = rs2.getInt("countNum");
						String count = Integer.toString(count1);
						kom.setText("Ukupno komada = "+count);
				
								
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 				
				}else {
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumOd = dateF.format(dOd);	
					String datumDo = dateF.format(dDo);	

					Warehouse.checkDateformat(datumOd);
					Warehouse.checkDateformat(datumDo);
					String naredba = "SELECT * from Vulkaniziranje "

							+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
					try {
						Statement stmt = konekcioniObj.createStatement();
						ResultSet rs1 = stmt.executeQuery(naredba);						
						t.setModel(DbUtils.resultSetToTableModel(rs1));
						ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto from Vulkaniziranje"
								+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
						while(rs2.next()) {
							double b = rs2.getDouble("bruto");
							sumB = sumB + b;							
							String sumaB = Double.toString(sumB);
							
							brt.setText("Ukupno bruto = "+sumaB);

						}
						
							rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Vulkaniziranje WHERE "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
							rs2.next();
							int count1 = rs2.getInt("countNum");
							String count = Integer.toString(count1);
							kom.setText("Ukupno komada = "+count);
									
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 	
				}
			}
		
public static void searchVulkGratis(LocalDate dOd, LocalDate dDo, String searchBy, JTable t, JLabel kom, JLabel brt) {  
			
		double sumB = 0;	
		if(dOd==null){	
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumDo = dateF.format(dDo);	
					
		String naredba = "SELECT * from Vulkaniziranje "
				+ " WHERE " +searchBy+ " = 0.0 AND Datum <='"+datumDo+"' ORDER BY Datum DESC"; 
				
		try {
			Statement stmt = konekcioniObj.createStatement();
			ResultSet rs1 = stmt.executeQuery(naredba);						
			t.setModel(DbUtils.resultSetToTableModel(rs1));	
			ResultSet rs2 = stmt.executeQuery("SELECT sum(cena) as bruto from Vulkaniziranje WHERE "+searchBy+"= 0.0 AND Datum <='"+datumDo+"'");
			
			while(rs2.next()) {
				double b = rs2.getDouble("bruto");		
				sumB = sumB + b;		
				String sumaB = Double.toString(sumB);				
				brt.setText("Ukupno bruto = "+sumaB);
												
			}
				rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Vulkaniziranje WHERE "+searchBy+" = 0.0 AND Datum <='"+datumDo+"'");
				rs2.next();
				int count1 = rs2.getInt("countNum");
				String count = Integer.toString(count1);
				kom.setText("Ukupno komada = "+count);		
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 				
		}else {
			DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String datumOd = dateF.format(dOd);	
			String datumDo = dateF.format(dDo);	

			Warehouse.checkDateformat(datumOd);
			Warehouse.checkDateformat(datumDo);
			String naredba = "SELECT * from Vulkaniziranje "
					+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' AND "+searchBy+"= 0.0 ORDER BY Datum DESC";	
					
			try {
				Statement stmt = konekcioniObj.createStatement();
				ResultSet rs1 = stmt.executeQuery(naredba);						
				t.setModel(DbUtils.resultSetToTableModel(rs1));
				ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto from Vulkaniziranje"
						+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'AND "+searchBy+"= 0.0");
						
				while(rs2.next()) {
					double b = rs2.getDouble("bruto");		
					sumB = sumB + b;								
					String sumaB = Double.toString(sumB);								
					brt.setText("Ukupno bruto = "+sumaB);
			
				}
				
			
					rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Vulkaniziranje WHERE "+searchBy+"= 0.0 AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
					rs2.next();
					int count1 = rs2.getInt("countNum");
					String count = Integer.toString(count1);
					kom.setText("Ukupno komada = "+count);
							
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 	
		}
	}

			public static void searchByDateNab(LocalDate dOd,LocalDate dDo,JTable t, JLabel kom, JLabel brt) {       
						int sumK = 0;
						double sumB = 0;					
					
					if(dOd==null){	
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");					
					String datumDo = dateF.format(dDo);	
					
					String naredba = "SELECT * FROM Nabavka "
							+ "WHERE Datum <='"+datumDo+"' ORDER BY Datum DESC";				
					try {
					Statement stmt = konekcioniObj.createStatement();
					ResultSet rs1 = stmt.executeQuery(naredba);						
					t.setModel(DbUtils.resultSetToTableModel(rs1));					
					ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto, sum(Komada) as kom from Nabavka WHERE Datum <='"+datumDo+"'");
					while(rs2.next()) {
						double b = rs2.getDouble("bruto");
						sumB = sumB + b;						
						String sumaB = Double.toString(sumB);
						int k = rs2.getInt("kom");
						sumK = sumK + k;
						String sumaK = Integer.toString(sumK);
						
						brt.setText("Ukupno bruto = "+sumaB);
						kom.setText("Ukupno komada = "+sumaK);
						
				
					}
			/*		rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Nabavka WHERE Datum <='"+datumDo+"'");      
					rs2.next();
					int count1 = rs2.getInt("count");
					String count = Integer.toString(count1);
					kom.setText("Ukupno komada = "+count);		*/
					} catch (SQLException e1) {
					e1.printStackTrace();
					} 				
					}else {
					DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String datumOd = dateF.format(dOd);	
					String datumDo = dateF.format(dDo);	
					
					Warehouse.checkDateformat(datumOd);
					Warehouse.checkDateformat(datumDo);
					String naredba = "SELECT * FROM Nabavka"
					+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";				
					try {
					Statement stmt = konekcioniObj.createStatement();
					ResultSet rs1 = stmt.executeQuery(naredba);						
					t.setModel(DbUtils.resultSetToTableModel(rs1));
					ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto, sum(Komada) as kom from Nabavka"
															+ " where Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
					while(rs2.next()) {
						double b = rs2.getDouble("bruto");
						sumB = sumB + b;						
						String sumaB = Double.toString(sumB);
						int k = rs2.getInt("kom");
						sumK = sumK + k;
						String sumaK = Integer.toString(sumK);
						
						brt.setText("Ukupno bruto = "+sumaB);
						kom.setText("Ukupno komada = "+sumaK);
						
			
					}
			/*		rs2 = stmt.executeQuery("SELECT COUNT(*) as count FROM Nabavka WHERE Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
					rs2.next();
					int count1 = rs2.getInt("count");
					String count = Integer.toString(count1);
					kom.setText("Ukupno komada = "+count);			*/
					} catch (SQLException e1) {

					e1.printStackTrace();
					} 	
			}
					
		}	
			
			public static void searchNabavka(LocalDate dOd, LocalDate dDo, String searchBy, String search, JTable t, JLabel kom, JLabel brt) {  
					int sumK = 0;
					double sumB = 0;
					
					if(dOd==null){	
						DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String datumDo = dateF.format(dDo);	
								
					String naredba = "SELECT * from Nabavka "
							+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
					try {
						Statement stmt = konekcioniObj.createStatement();
						ResultSet rs1 = stmt.executeQuery(naredba);						
						t.setModel(DbUtils.resultSetToTableModel(rs1));	
						ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto, sum(Komada) as kom from Nabavka WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
						while(rs2.next()) {
							double b = rs2.getDouble("bruto");
							sumB = sumB + b;							
							String sumaB = Double.toString(sumB);
							int k = rs2.getInt("kom");
							sumK = sumK + k;
							String sumaK = Integer.toString(sumK);
							
							brt.setText("Ukupno bruto = "+sumaB);
							kom.setText("Ukupno komada = "+sumaK);
							
															
						}
					/*		rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Nabavka WHERE "+searchBy+" LIKE '%"+search+"%'AND Datum <='"+datumDo+"'");
							rs2.next();
							int count1 = rs2.getInt("countNum");
							String count = Integer.toString(count1);
							kom.setText("Ukupno komada = "+count);		*/
					
									
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 				
					}else {
						DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String datumOd = dateF.format(dOd);	
						String datumDo = dateF.format(dDo);	

						Warehouse.checkDateformat(datumOd);
						Warehouse.checkDateformat(datumDo);
						String naredba = "SELECT * from Nabavka "
								+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"' ORDER BY Datum DESC";	// search by containing word
						try {
							Statement stmt = konekcioniObj.createStatement();
							ResultSet rs1 = stmt.executeQuery(naredba);						
							t.setModel(DbUtils.resultSetToTableModel(rs1));
							ResultSet rs2 = stmt.executeQuery("SELECT sum(Cena) as bruto, sum(Komada) as kom from Nabavka"
									+ " where "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
							while(rs2.next()) {
								double b = rs2.getDouble("bruto");
								sumB = sumB + b;							
								String sumaB = Double.toString(sumB);
								int k = rs2.getInt("kom");
								sumK = sumK + k;
								String sumaK = Integer.toString(sumK);
								
								brt.setText("Ukupno bruto = "+sumaB);
								kom.setText("Ukupno komada = "+sumaK);
							}
							
					/*	rs2 = stmt.executeQuery("SELECT COUNT(*) as countNum  FROM Nabavka WHERE "+searchBy+" LIKE '%"+search+"%' AND Datum >= '"+datumOd+"' AND Datum <='"+datumDo+"'");
						rs2.next();
						int count1 = rs2.getInt("countNum");
						String count = Integer.toString(count1);
						kom.setText("Ukupno komada = "+count);		*/
										
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 	
					}
				}
	
}
