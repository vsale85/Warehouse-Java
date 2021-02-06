package main;

public class KupljenaVozila {
    private final int id;
    private String datum;
    private String model;
    private String vrstaGoriva;
    private String godiste;
    private String leviDesniVolan;   
    private String prodavac;
    private double cena;
    private String napomena;
    private Korisnik korisnik;

    public KupljenaVozila(int id, String datum, String model, String vrstaGoriva, String godiste, String leviDesniVolan, String prodavac, double cena, String napomena, Korisnik korisnik) {
        this.id = id;
        this.datum = datum;
        this.model = model;
        this.vrstaGoriva = vrstaGoriva;
        this.godiste = godiste;
        this.leviDesniVolan = leviDesniVolan;
        this.prodavac = prodavac;
        this.cena = cena;
        this.napomena = napomena;
        this.korisnik = korisnik;
    }
    public int getid(){
        return id;
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

    public String getVrstaGoriva() {
        return vrstaGoriva;
    }

    public void setVrstaGoriva(String vrstaGoriva) {
        this.vrstaGoriva = vrstaGoriva;
    }

    public String getGodiste() {
        return godiste;
    }

    public void setGodiste(String godiste) {
        this.godiste = godiste;
    }

    public String getLeviDesniVolan() {
        return leviDesniVolan;
    }

    public void setLeviDesniVolan(String leviDesniVolan) {
        this.leviDesniVolan = leviDesniVolan;
    }

    public String getProdavac() {
        return prodavac;
    }

    public void setProdavac(String prodavac) {
        this.prodavac = prodavac;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "KupljenaVozila{" + "id=" + id + ", datum=" + datum + ", model=" + model + ", vrstaGoriva=" + vrstaGoriva + ", godiste=" + godiste + ", leviDesniVolan=" + leviDesniVolan + ", prodavac=" + prodavac + ", cena=" + cena + ", napomena=" + napomena + ", korisnik=" + korisnik + '}';
    }
   
    
}
