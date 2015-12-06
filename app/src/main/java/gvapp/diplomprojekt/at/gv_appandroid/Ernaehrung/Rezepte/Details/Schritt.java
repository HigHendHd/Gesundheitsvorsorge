package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

/**
 * Created by Dennis on 06.12.2015.
 */
public class Schritt {
    private int nummer;
    private String text;

    public Schritt(int nummer, String text) {
        this.nummer = nummer;
        this.text = text;
    }

    public int getNummer() {
        return nummer;
    }

    public String getText() {
        return text;
    }
}
