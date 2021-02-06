package main;


public class Vulkaniziranje {
	private final int id;
	private String datum;
	private String velicina;
	private String komada;
	private String usluga;
	private String kupac;
	private String status;
	private double cena;
	private String napomena;
	
	
	public Vulkaniziranje(int id, String datum, String velicina, String komada, String usluga, String kupac,
			String status, double cena, String napomena) {
		super();
		this.id = id;
		this.datum = datum;
		this.velicina = velicina;
		this.komada = komada;
		this.usluga = usluga;
		this.kupac = kupac;
		this.status = status;
		this.cena = cena;
		this.napomena = napomena;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public String getVelicina() {
		return velicina;
	}


	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}


	public String getKomada() {
		return komada;
	}


	public void setKomada(String komada) {
		this.komada = komada;
	}


	public String getUsluga() {
		return usluga;
	}


	public void setUsluga(String usluga) {
		this.usluga = usluga;
	}


	public String getKupac() {
		return kupac;
	}


	public void setKupac(String kupac) {
		this.kupac = kupac;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
		return "Vulkaniziranje [id=" + id + ", datum=" + datum + ", velicina=" + velicina + ", komada=" + komada
				+ ", usluga=" + usluga + ", kupac=" + kupac + ", status=" + status + ", cena=" + cena + ", napomena="
				+ napomena + "]";
	}
	
	
}
