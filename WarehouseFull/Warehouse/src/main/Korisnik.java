package main;

public class Korisnik {
    private final int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String brojTelefona;
    private String username;
    private String password;
    private String uloga;

    public Korisnik(int id, String ime, String prezime, String jmbg, String brTelefona, String username, String password, String uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.brojTelefona = brTelefona;
        this.username = username;
        this.password = password;
        this.uloga = uloga;
    }
    
    public int getid(){
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBrTelefona() {
        return brojTelefona;
    }

    public void setBrTelefona(String brTelefona) {
        this.brojTelefona = brTelefona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", brTelefona=" + brojTelefona + ", username=" + username + ", password=" + password + ", uloga=" + uloga + '}';
    }
    
    
    
}
