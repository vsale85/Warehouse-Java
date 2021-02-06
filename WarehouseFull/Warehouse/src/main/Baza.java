package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Baza {

    public static HashMap<Integer, Korisnik> korisnici = new HashMap<Integer, Korisnik>();
    public static HashMap<Integer, Delovi> delovi = new HashMap<Integer, Delovi>();
    public static HashMap<Integer, KupljenaVozila> kupljeno = new HashMap<Integer, KupljenaVozila>();
    public static HashMap<Integer, Delovi> nadjeniDelovi = new HashMap<Integer, Delovi>();
    public static HashMap<Integer, MaliServis> maliServis = new HashMap<Integer, MaliServis>();
    public static HashMap<Integer, Popravke> popravke = new HashMap<Integer, Popravke>();
    public static HashMap<Integer, Vulkaniziranje> vulkaniziranje = new HashMap<Integer, Vulkaniziranje>();
    public static HashMap<Integer, Nabavka> nabavka = new HashMap<Integer, Nabavka>();
    
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
            System.out.println("Uspena konekcija!");
        } catch (SQLException ex) {

        }
        return conn;
    }

    public static Connection konekcioniObj;

    static {
        try {
            konekcioniObj = KonekcijaKaBazi();

            PopunjavanjeKorisnika();
            PopunjavanjeDelovi();
            PopunjavanjeKupljeno();
            PopunjavanjeMaliServis();
            PopunjavanjePopravke();
            PopunjavanjeVulk();
            PopunjavanjeNabavka();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Korisnik ProveraKorisnika(String username, String password) {
        for (Korisnik kor : korisnici.values()) {
            if (username.equals(kor.getUsername()) && password.equals(kor.getPassword())) {
                return kor;
            }
        }
        return null;
    }

    public static void PopunjavanjeKorisnika() throws SQLException {
        String naredba = "Select * from Korisnik";
        Statement stmt = konekcioniObj.createStatement();
        try (ResultSet rs = stmt.executeQuery(naredba)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if (korisnici.containsKey(id)) {
                 //   continue;
                }
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String jmbg = rs.getString(4);
                String brojTelefona = rs.getString(5);
                String username = rs.getString(6);
                String password = rs.getString(7);
                String uloga = rs.getString(8);
                Korisnik korisnik = new Korisnik(id, ime, prezime, jmbg, brojTelefona,
                        username, password, uloga);
                korisnici.put(id, korisnik);
            }
            rs.close();
        }
    }

    public static boolean DodavanjeKorisnika(String ime, String prezime,
            String jmbg, String brojTelefona, String username, String pass1, String pass2, String uloga) throws SQLException {
        if (!pass1.equals(pass2)) {
            return false;
        }

        if (!korisnici.values().stream().noneMatch((kor) -> (kor.getUsername().equals(username)))) {
            return false;
        }

        String naredba = "Insert into Korisnik values("
                + "'" + ime + "','"
                + prezime + "','"
                + jmbg + "','"
                + brojTelefona + "','"
                + username + "','"
                + pass1 + "','" + uloga + "')";
        System.out.println(naredba);
        Statement stmt = konekcioniObj.createStatement();
        stmt.executeUpdate(naredba);
        PopunjavanjeKorisnika();
        return true;

    }

    public static void PopunjavanjeDelovi() throws SQLException {
        String naredba = "Select * from Delovi";
        Statement stmt = konekcioniObj.createStatement();
        try (ResultSet rs = stmt.executeQuery(naredba)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if (delovi.containsKey(id)) {
                    continue;
                }
                String naziv = rs.getString(2);
                String datum = rs.getString(3);
                String kupac = rs.getString(4);
                double cena = rs.getDouble(5);
                String model = rs.getString(6);
                String status = rs.getString(7);
                String napomena = rs.getString(8);
                int idKorisnika = rs.getInt(9);
                Korisnik kor = korisnici.get(idKorisnika);

                Delovi noviDeo = new Delovi(id, naziv, datum, kupac, cena, model, status, napomena, kor);
                delovi.put(id, noviDeo);
            }
            rs.close();
        }
    }

    public static boolean DodavanjeDelovi(String naziv, String datum, String kupac, double cena, String model, String status, String napomena, String ulogovani) throws SQLException {
    				
		
        String naredba = "Insert into Delovi values("
                + "'" + naziv + "','"
                + datum + "','"
                + kupac + "','"
                + cena + "','"
                + model + "','"
                + status + "','"
                + napomena + "','"
          //      + ulogovani.getid() + "')";
                + ulogovani + "')";

        Statement stmt = konekcioniObj.createStatement();
        stmt.executeUpdate(naredba);
        PopunjavanjeDelovi();
    	
    	return true;
    	
    }

    public static void PopunjavanjeKupljeno() throws SQLException {
        String naredba = "Select * from kupljenaVozila";
        Statement stmt = konekcioniObj.createStatement();
        try (ResultSet rs = stmt.executeQuery(naredba)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if (kupljeno.containsKey(id)) {
                    continue;
                }
                String datum = rs.getString(2);
                String model = rs.getString(3);
                String vrstaGoriva = rs.getString(4);
                String godiste = rs.getString(5);
                String leviDesniVolan = rs.getString(6);
                String prodavac = rs.getString(7);
                double cena = rs.getDouble(8);
                String napomena = rs.getString(9);
                int idKorisnika = rs.getInt(10);
                Korisnik kor = korisnici.get(idKorisnika);

                KupljenaVozila novoKupljeno = new KupljenaVozila(id, datum, model, vrstaGoriva, godiste, leviDesniVolan, prodavac, cena, napomena, kor);
                kupljeno.put(id, novoKupljeno);
            }
            rs.close();
        }
    }

    public static boolean DodavanjeKupljeno(String datum, String model, String vrstaGoriva, String godiste, String leviDesniVolan, String prodavac, double cena, String napomena, String ulogovani) throws SQLException {

        String naredba = "Insert into kupljenaVozila values("
                + "'" + datum + "','"
                + model + "','"
                + vrstaGoriva + "','"
                + godiste + "','"
                + leviDesniVolan + "','"
                + prodavac + "','"
                + cena + "','"
                + napomena + "','"
           //     + ulogovani.getid() + "')";
       			+ ulogovani + "')";

        System.out.println(naredba);
        Statement stmt = konekcioniObj.createStatement();
        stmt.executeUpdate(naredba);
        PopunjavanjeKupljeno();
        return true;

    }
    public static void BrisanjeKupljeno(String idKup) throws SQLException {
    	String naredba = "DELETE KupljenaVozila where id = "+idKup;
    	Statement stKupljeno = konekcioniObj.createStatement();
    	stKupljeno.executeUpdate(naredba);
    	kupljeno.remove(idKup);
    	PopunjavanjeKupljeno();
    }

    public static void BrisanjeKorisnika(String idKor) throws SQLException {
        String naredba = "Delete Korisnik where id = " + idKor;
        Statement stKorisnik = konekcioniObj.createStatement();
        stKorisnik.executeUpdate(naredba);
        korisnici.remove(idKor);
        PopunjavanjeKorisnika();

    }
    
    public static void BrisanjeDelovi(String idD) throws SQLException {
        String naredba = "Delete Delovi where id = " + idD;
        Statement stDelovi = konekcioniObj.createStatement();
        stDelovi.executeUpdate(naredba);
        Baza.delovi.remove(idD);
        Baza.nadjeniDelovi.remove(idD);
        PopunjavanjeDelovi();
    }
    
    public static boolean IzmenaDelovi(int deoZaIzmenu,String naziv, String datum, String kupac, String cena, String model, String status, String napomena, String ulogovani) throws SQLException {
        String naredba = "UPDATE Delovi SET naziv = '"+naziv+"',"
                + " datum = '"+datum+"',"
                + "kupac = '"+kupac+"',"
                + "cena = '"+cena+"',"
                + "model = '"+model+"',"
                + "status = '"+status+"',"
                + "napomena = '"+napomena+"',"
//                + "idKorisnika = '"+ ulogovani.getid() +"' WHERE id = '"+ deoZaIzmenu.getid()+"'";
                 + "idKorisnika = '"+ ulogovani+"' WHERE id = '"+ deoZaIzmenu+"'";
               

        System.out.println(naredba);
        Statement stmt = konekcioniObj.createStatement();
        stmt.executeUpdate(naredba);
        PopunjavanjeDelovi();
        return true;
        
    }
    
    public static void PopunjavanjeMaliServis() throws SQLException {
        String naredba = "Select * from MaliServis";
        Statement stmt = konekcioniObj.createStatement();
        try (ResultSet rs = stmt.executeQuery(naredba)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if (maliServis.containsKey(id)) {
                    continue;
                }
                String datum = rs.getString(2);
            	String model = rs.getString(3);
            	String kupac = rs.getString(4);
            	String vozilo = rs.getString(5);
            	String status = rs.getString(6);
            	double cenaBrt = rs.getDouble(7);
            	double cenaNeto = rs.getDouble(8);
            	String uljeVrsta = rs.getString(9);
            	double uljeLitara = rs.getDouble(10);
            	String fUlje = rs.getString(11);
            	String fGorivo = rs.getString(12);
                String fVazduha = rs.getString(13);
            	String fKabine = rs.getString(14);
            	String kilometraza = rs.getString(15);
            	String slZamena = rs.getString(16); 
            	String napomena = rs.getString(17);
            	
               
            	MaliServis noviServis = new MaliServis(id, datum, model, kupac, vozilo, status, cenaBrt, cenaNeto,
            			uljeVrsta, uljeLitara, fUlje, fGorivo, fVazduha, fKabine, kilometraza, slZamena, napomena);
            			
                
                maliServis.put(id, noviServis);
            }
            rs.close();
        }
    }
    
public static boolean DodavanjeMaliServis(String datum, String model, String kupac, String vozilo, String status, double cenaBrt,
											double cenaNeto, String uljeVrsta, double uljeLitara, String fUlje, String fGorivo, String fVazduha,
													String fKabine, String kilometraza, String slZamena, String napomena) throws SQLException {
    				
		
        String naredba = "Insert into MaliServis values("
                + "'" + datum + "','"
                + model + "','"
                + kupac + "','"
                + vozilo + "','"
                + status + "','"
                + cenaBrt + "','"
                + cenaNeto + "','"
                + uljeVrsta + "','"
                + uljeLitara + "','"
                + fUlje + "','"
                + fGorivo + "','"
                + fVazduha + "','"
                + fKabine + "','"
                + kilometraza + "','"
                + slZamena + "','"
                + napomena + "')";

        Statement stmt = konekcioniObj.createStatement();
        stmt.executeUpdate(naredba);
        PopunjavanjeMaliServis();
    	
    	return true;
    	
    	}
		public static boolean IzmenaMS(int idMS,String kupac, String vozilo, String status, String napomena) throws SQLException {
		    String naredba = "UPDATE MaliServis SET Kupac = '"+kupac+"',"
		            + " Vozilo = '"+vozilo+"',"
		            + "Status = '"+status+"', Napomena = '"+napomena+"' WHERE id = '"+ idMS+"'";
		           		
		    System.out.println(naredba);
		    Statement stmt = konekcioniObj.createStatement();
		    stmt.executeUpdate(naredba);
		    PopunjavanjeMaliServis();
		    return true;
    
		}
		 public static void PopunjavanjePopravke() throws SQLException {
		        String naredba = "Select * from Popravke";
		        Statement stmt = konekcioniObj.createStatement();
		        try (ResultSet rs = stmt.executeQuery(naredba)) {
		            while (rs.next()) {
		                int id = rs.getInt(1);
		                if (popravke.containsKey(id)) {
		                    continue;
		                }
		                String datum = rs.getString(2);
		            	String model = rs.getString(3);
		            	String kupac = rs.getString(4);		         
		            	String status = rs.getString(5);
		            	double cenaBrt = rs.getDouble(6);
		            	double cenaNeto = rs.getDouble(7);		            	
		            	String delovi = rs.getString(8);	       		            	
		            	String napomena = rs.getString(9);
		            	
		               
		            	
		                Popravke novaPopravka = new Popravke(id, datum, model, kupac, status, cenaBrt, cenaNeto, delovi, napomena);
		                popravke.put(id, novaPopravka);
		            }
		            rs.close();
		        }
		    }
		 public static boolean DodavanjePopravke(String datum, String model, String kupac, String status, double cenaBrt,
					double cenaNeto, String delovi, String napomena) throws SQLException {

						
						String naredba = "Insert into Popravke values("
						+ "'" + datum + "','"
						+ model + "','"
						+ kupac + "','"						
						+ status + "','"
						+ cenaBrt + "','"
						+ cenaNeto + "','"						
						+ delovi + "','"											
						+ napomena + "')";
						
						Statement stmt = konekcioniObj.createStatement();
						stmt.executeUpdate(naredba);
						PopunjavanjePopravke();
						
						return true;
						
		 }
		 
		 public static boolean IzmenaPop(int idPop,String kupac, String status, String napomena) throws SQLException {
			    String naredba = "UPDATE Popravke SET Kupac = '"+kupac+"',"
			            + "Status = '"+status+"', Napomena = '"+napomena+"' WHERE id = '"+ idPop+"'";
			           		
			    System.out.println(naredba);
			    Statement stmt = konekcioniObj.createStatement();
			    stmt.executeUpdate(naredba);
			    PopunjavanjePopravke();
			    return true;
	    
			}
		 
		 public static void PopunjavanjeVulk() throws SQLException {
		        String naredba = "Select * from Vulkaniziranje";
		        Statement stmt = konekcioniObj.createStatement();
		        try (ResultSet rs = stmt.executeQuery(naredba)) {
		            while (rs.next()) {
		                int id = rs.getInt(1);
		                if (vulkaniziranje.containsKey(id)) {
		                    continue;
		                }
		                String datum = rs.getString(2);
		            	String velicina = rs.getString(3);
		            	String komada = rs.getString(4);
		            	String usluga = rs.getString(5);
		            	String kupac = rs.getString(6);
		            	String status = rs.getString(7);
		            	double cena = rs.getDouble(8);
		            	String napomena = rs.getString(9);
		            	
		               
		            	Vulkaniziranje v = new Vulkaniziranje(id, datum, velicina, komada, usluga, kupac, status, cena, napomena);
		            			
		                vulkaniziranje.put(id, v);
		            }
		            rs.close();
		        }
		    }
		 
		 public static boolean DodavanjeVulk(String datum, String velicina, String komada, String usluga, 
				 								String kupac,String status,double cena, String napomena) throws SQLException {

						
						String naredba = "Insert into Vulkaniziranje values("
						+ "'" + datum + "','"
						+ velicina + "','"
						+ komada + "','"						
						+ usluga + "','"
						+ kupac + "','"
						+ status + "','"						
						+ cena + "','"											
						+ napomena + "')";
						
						Statement stmt = konekcioniObj.createStatement();
						stmt.executeUpdate(naredba);
						PopunjavanjeVulk();
						
						return true;
						
		 }
		 
		 public static boolean IzmenaVulk(int idVul,String kupac, String status, String napomena) throws SQLException {
			    String naredba = "UPDATE Vulkaniziranje SET Status = '"+status+"', Napomena = '"+napomena+"' WHERE id = '"+ idVul+"'";
			           
			           		
			    System.out.println(naredba);
			    Statement stmt = konekcioniObj.createStatement();
			    stmt.executeUpdate(naredba);
			    PopunjavanjeVulk();
			    return true;
	    
			}
		 
		 public static void PopunjavanjeNabavka() throws SQLException {
		        String naredba = "Select * from Nabavka";
		        Statement stmt = konekcioniObj.createStatement();
		        try (ResultSet rs = stmt.executeQuery(naredba)) {
		            while (rs.next()) {
		                int id = rs.getInt(1);
		                if (nabavka.containsKey(id)) {
		                    continue;
		                }
		                String datum = rs.getString(2);
		            	String vrsta = rs.getString(3);
		            	String naziv = rs.getString(4);
		            	String komada = rs.getString(5);
		            	String dobavljac = rs.getString(6);		            	
		            	double cena = rs.getDouble(7);
		            	String napomena = rs.getString(8);
		            	
		               
		            	Nabavka n = new Nabavka(id, datum, vrsta, naziv, komada, dobavljac, cena, napomena);
		            			
		                nabavka.put(id, n);
		            }
		            rs.close();
		        }
		    }
		 
		 public static boolean DodavanjeNabavka(String datum, String vrsta, String naziv, String komada, 
					String dobavljac, double cena, String napomena) throws SQLException {


				String naredba = "Insert into Nabavka values("
				+ "'" + datum + "','"
				+ vrsta + "','"
				+ naziv + "','"						
				+ komada + "','"
				+ dobavljac + "','"										
				+ cena + "','"											
				+ napomena + "')";
				
				Statement stmt = konekcioniObj.createStatement();
				stmt.executeUpdate(naredba);
				PopunjavanjeNabavka();
				
				return true;
				
		}

		 
}
