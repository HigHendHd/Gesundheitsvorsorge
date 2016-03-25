package gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Details;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class Adresse {
    private String strasse;
    private String nummer;
    private String plz;
    private String ort;

    public Adresse(String strasse, String nummer, String plz, String ort) {
        this.strasse = strasse;
        this.nummer = nummer;
        this.plz = plz;
        this.ort = ort;
    }

    @Override
    public String toString() {
        return strasse + " " + nummer + ", " + plz + " " + ort;
    }
}
