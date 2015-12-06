package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

/**
 * Created by Dennis on 06.12.2015.
 */
public class Zutat {
    private String anzahl;
    private String einheit;
    private String zname;

    public Zutat(String anzahl, String einheit, String zname) {

        this.anzahl = anzahl;
        this.einheit = einheit;
        this.zname = zname;
    }

    public String getAnzahl() {
        return anzahl;
    }

    public String getEinheit() {
        return einheit;
    }

    public String getZname() {
        return zname;
    }
}
