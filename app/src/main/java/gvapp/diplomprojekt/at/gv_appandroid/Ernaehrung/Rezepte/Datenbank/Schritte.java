package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

/**
 * Created by Dennis on 21.02.2016.
 */
public class Schritte {

    private long r_id;
    private int nummer;
    private String text;

    public Schritte(long r_id, int nummer, String text) {
        this.r_id = r_id;
        this.nummer = nummer;
        this.text = text;
    }

    public long getR_id() {
        return r_id;
    }

    public void setR_id(long r_id) {
        this.r_id = r_id;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
