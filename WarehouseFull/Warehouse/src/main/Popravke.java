package main;


public class Popravke {
	private final int id;
	private String datum;
	private String model;
	private String kupac;
	private String status;
	private double cenaBrt;
	private double cenaNeto;
	private String delovi;
	private String napomena;
	
	public Popravke(int id, String datum, String model, String kupac, String status, double cenaBrt, double cenaNeto,
			String delovi, String napomena) {
		super();
		this.id = id;
		this.datum = datum;
		this.model = model;
		this.kupac = kupac;
		this.status = status;
		this.cenaBrt = cenaBrt;
		this.cenaNeto = cenaNeto;
		this.delovi = delovi;
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

	public String getMojiDelovi() {
		return delovi;
	}

	public void setMojiDelovi(String mojiDelovi) {
		this.delovi = mojiDelovi;
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
		return "Popravke [id=" + id + ", datum=" + datum + ", model=" + model + ", kupac=" + kupac + ", status="
				+ status + ", cenaBrt=" + cenaBrt + ", cenaNeto=" + cenaNeto + ", delovi=" + delovi
				+ ", napomena=" + napomena+"]";
				
	}
	
		
}
