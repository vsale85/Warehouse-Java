package main;
public class Delovi {
    private final int id;
    private String naziv;
    private String datum;
    private String kupac;
    private double cena;
    private String model;
    private String status;
    private String napomena;
    private Korisnik korisnik;

    public Delovi(int id, String naziv, String datum, String kupac, double cena, String model, String status,Korisnik korisnik) {
        this.id = id;
        this.naziv = naziv;
        this.datum = datum;
        this.kupac = kupac;
        this.cena = cena;
        this.model = model;
        this.status = status;
        
    }

    public Delovi(int id, String naziv, String datum, String kupac, double cena, String model, String status, String napomena, Korisnik korisnik) {
        this.id = id;
        this.naziv = naziv;
        this.datum = datum;
        this.kupac = kupac;
        this.cena = cena;
        this.model = model;
        this.status = status;
        this.napomena = napomena;
        this.korisnik = korisnik;
    }
     public int getid(){
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Delovi{" + "id=" + id + ", naziv=" + naziv + ", datum=" + datum + ", kupac=" + kupac + ", cena=" + cena + ", model=" + model + ", status=" + status + ", napomena=" + napomena + ", korisnik=" + korisnik + '}';
    }
    
    
   

   
    
}
