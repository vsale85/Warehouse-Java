package main;


public class Nabavka {
	private final int id;
	private String datum;
	private String vrsta;
	private String naziv;
	private String komada;
	private String dobavljac;
	private double cena;
	private String napomena;
	
	
	public Nabavka(int id, String datum, String vrsta, String naziv, String komada, String dobavljac, double cena,
			String napomena) {
		super();
		this.id = id;
		this.datum = datum;
		this.vrsta = vrsta;
		this.naziv = naziv;
		this.komada = komada;
		this.dobavljac = dobavljac;
		this.cena = cena;
		this.napomena = napomena;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public String getVrsta() {
		return vrsta;
	}


	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getKomada() {
		return komada;
	}


	public void setKomada(String komada) {
		this.komada = komada;
	}


	public String getDobavljac() {
		return dobavljac;
	}


	public void setDobavljac(String dobavljac) {
		this.dobavljac = dobavljac;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public String getNapomena() {
		return napomena;
	}


	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}


	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Nabavka [id=" + id + ", datum=" + datum + ", vrsta=" + vrsta + ", naziv=" + naziv + ", komada=" + komada
				+ ", dobavljac=" + dobavljac + ", cena=" + cena + ", napomena=" + napomena + "]";
	}
	
	
}
