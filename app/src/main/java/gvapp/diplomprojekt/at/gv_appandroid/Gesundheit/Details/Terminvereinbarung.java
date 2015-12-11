package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

/**
 * Created by Dennis on 11.12.2015.
 */
public class Terminvereinbarung {
    private String tag;
    private String start;
    private String ende;

    public Terminvereinbarung(String ende, String start, String tag) {

        this.ende = ende;
        this.start = start;
        this.tag = tag;
    }

    public String getEnde() {
        return ende;
    }

    public String getStart() {
        return start;
    }

    public String getTag() {
        return tag;
    }
}
