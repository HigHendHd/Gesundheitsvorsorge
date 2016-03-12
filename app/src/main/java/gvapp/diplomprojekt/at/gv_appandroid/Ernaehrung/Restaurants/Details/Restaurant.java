package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Details;


public class Restaurant {
    private int id;
    private String bild;
    private String name;
    private String kategorie;
    private String oeffnungszeiten;
    private String telefonnr;
    private Adresse adresse;
    private String info;
    private String weiterinfos;


    public String getAdressString() {

        return getAdresse().getStrasse() + " " + getAdresse().getNummer() +
                ", " + getAdresse().getPlz() + " " + getAdresse().getOrt();
    }


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
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

    public String getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public void setOeffnungszeiten(String oeffnungszeiten) {
        this.oeffnungszeiten = oeffnungszeiten;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWeiterinfos() {
        return weiterinfos;
    }

    public void setWeiterinfos(String weiterinfos) {
        this.weiterinfos = weiterinfos;
    }


}
