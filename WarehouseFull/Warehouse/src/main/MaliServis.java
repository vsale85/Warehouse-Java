package main;


public class MaliServis {
	private final int id;
	private String datum;
	private String model;
	private String kupac;
	private String vozilo;
	private String status;
	private double cenaBrt;
	private double cenaNeto;
	private String uljeVrsta;  
	private double uljeLitara;
	private String fUlje;
	private String fGorivo;
	private String fVazduha;
	private String fKabine;
	private String kilometraza;
	private String slZamena;
	private String napomena;
	
	public MaliServis(int id, String datum, String model, String kupac, String vozilo, String status, double cenaBrt,
			double cenaNeto, String uljeVrsta, double uljeLitara, String fUlje, String fGorivo, String fVazduha,
			String fKabine, String kilometraza, String slZamena, String napomena) {
		super();
		this.id = id;
		this.datum = datum;
		this.model = model;
		this.kupac = kupac;
		this.vozilo = vozilo;
		this.status = status;
		this.cenaBrt = cenaBrt;
		this.cenaNeto = cenaNeto;
		this.uljeVrsta = uljeVrsta;
		this.uljeLitara = uljeLitara;
		this.fUlje = fUlje;
		this.fGorivo = fGorivo;
		this.fVazduha = fVazduha;
		this.fKabine = fKabine;
		this.kilometraza = kilometraza;
		this.slZamena = slZamena;
		this.napomena = napomena;
	}		
	
	
	
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public String getVozilo() {
		return vozilo;
	}
	public void setVozilo(String vozilo) {
		this.vozilo = vozilo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getCenaBrt() {
		return cenaBrt;
	}
	public void setCenaBrt(double cenaBrt) {
		this.cenaBrt = cenaBrt;
	}
	public double getCenaNeto() {
		return cenaNeto;
	}
	public void setCenaNeto(double cenaNeto) {
		this.cenaNeto = cenaNeto;
	}
	public String getUljeVrsta() {
		return uljeVrsta;
	}
	public void setUljeVrsta(String uljeVrsta) {
		this.uljeVrsta = uljeVrsta;
	}
	public double getUljeLitara() {
		return uljeLitara;
	}
	public void setUljeLitara(double uljeLitara) {
		this.uljeLitara = uljeLitara;
	}
	public String getfUlje() {
		return fUlje;
	}
	public void setfUlje(String fUlje) {
		this.fUlje = fUlje;
	}
	public String getfGorivo() {
		return fGorivo;
	}
	public void setfGorivo(String fGorivo) {
		this.fGorivo = fGorivo;
	}
	public String getfVazduha() {
		return fVazduha;
	}
	public void setfVazduha(String fVazduha) {
		this.fVazduha = fVazduha;
	}
	public String getfKabine() {
		return fKabine;
	}
	public void setfKabine(String fKabine) {
		this.fKabine = fKabine;
	}
	public String getKilometraza() {
		return kilometraza;
	}
	public void setKilometraza(String kilometraza) {
		this.kilometraza = kilometraza;
	}
	public String getSlZamena() {
		return slZamena;
	}
	public void setSlZamena(String slZamena) {
		this.slZamena = slZamena;
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
		return "MaliServis [id=" + id + ", datum=" + datum + ", model=" + model + ", kupac=" + kupac + ", vozilo="
				+ vozilo + ", status=" + status + ", cenaBrt=" + cenaBrt + ", cenaNeto=" + cenaNeto + ", uljeVrsta="
				+ uljeVrsta + ", uljeLitara=" + uljeLitara + ", fUlje=" + fUlje + ", fGorivo=" + fGorivo + ", fVazduha="
				+ fVazduha + ", fKabine=" + fKabine + ", kilometraza=" + kilometraza + ", slZamena=" + slZamena
				+ ", napomena=" + napomena + "]";
	}
	
		
}
