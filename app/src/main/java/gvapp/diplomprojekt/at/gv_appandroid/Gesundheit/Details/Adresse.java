package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

/**
 * Created by Dennis on 11.12.2015.
 */
public class Adresse {
    private String Strasse;
    private String nummer;
    private String postleitzahl;
    private String stadt;

    public Adresse(String nummer, String postleitzahl, String stadt, String strasse) {

        this.nummer = nummer;
        this.postleitzahl = postleitzahl;
        this.stadt = stadt;
        Strasse = strasse;
    }

    public String getNummer() {
        return nummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getStrasse() {
        return Strasse;
    }

    public String getStadt() {
        return stadt;
    }
}
