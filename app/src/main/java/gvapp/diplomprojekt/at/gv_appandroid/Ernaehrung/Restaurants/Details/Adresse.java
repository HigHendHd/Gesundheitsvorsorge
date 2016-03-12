package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Details;


public class Adresse {
    private String Strasse;
    private String nummer;
    private String plz;
    private String ort;

    public Adresse(String nummer, String plz, String ort, String strasse) {

        this.nummer = nummer;
        this.plz = plz;
        this.ort = ort;
        Strasse = strasse;
    }

    public String getNummer() {
        return nummer;
    }

    public String getPlz() {
        return plz;
    }

    public String getStrasse() {
        return Strasse;
    }

    public String getOrt() {
        return ort;
    }

}
