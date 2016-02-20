package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank;

/**
 * Created by Dennis Chou on 20.02.2016.
 */
public class Restaurants {

    private long id;
    private String name;
    private String kategorie;
    private String bild;
    private String info;
    private int telefonnr;
    private String oeffnungszeiten;
    private String adresse;
    private String weitereinfos;

    public Restaurants(long id, String name, String kategorie, String bild, String info, int telefonnr, String oeffnungszeiten, String adresse, String weitereinfos) {
        this.id = id;
        this.name = name;
        this.kategorie = kategorie;
        this.bild = bild;
        this.info = info;
        this.telefonnr = telefonnr;
        this.oeffnungszeiten = oeffnungszeiten;
        this.adresse = adresse;
        this.weitereinfos = weitereinfos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(int telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public void setOeffnungszeiten(String oeffnungszeiten) {
        this.oeffnungszeiten = oeffnungszeiten;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getWeitereinfos() {
        return weitereinfos;
    }

    public void setWeitereinfos(String weitereinfos) {
        this.weitereinfos = weitereinfos;
    }
}
