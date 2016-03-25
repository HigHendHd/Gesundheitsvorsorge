package gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Details;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class Sportstaette {
    private String name;
    private String kategorie;
    private int bewertung;
    private String bildUrl;
    private String info;
    private String telefonnummer;
    private String oeffnungszeiten;
    private Adresse adresse;
    private String weitereinfos;
    private int id;

    public String getAdressString() {
        return adresse.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getBewertung() {
        return bewertung;
    }

    public void setBewertung(int bewertung) {
        this.bewertung = bewertung;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public void setOeffnungszeiten(String oeffnungszeiten) {
        this.oeffnungszeiten = oeffnungszeiten;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getWeitereinfos() {
        return weitereinfos;
    }

    public void setWeitereinfos(String weitereinfos) {
        this.weitereinfos = weitereinfos;
    }
}
