package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

/**
 * Created by Dennis on 21.02.2016.
 */
public class Zutaten {

    private long r_id;
    private String name;
    private int anzahl;
    private String einheit;

    public Zutaten(long r_id, String name, int anzahl, String einheit) {
        this.r_id = r_id;
        this.name = name;
        this.anzahl = anzahl;
        this.einheit = einheit;
    }

    public long getR_id() {
        return r_id;
    }

    public void setR_id(long r_id) {
        this.r_id = r_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }
}
