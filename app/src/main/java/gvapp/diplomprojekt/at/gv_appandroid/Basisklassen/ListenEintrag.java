package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected String lisTitel;
    protected String lisUntertitel;
    protected String lisURL;

    public ListenEintrag(String lisTitel, String lisUntertitel, String lisURL) {
        this.lisTitel = lisTitel;
        this.lisUntertitel = lisUntertitel;
        this.lisURL = lisURL;
    }

    public ListenEintrag() {

    }

    public String getLisTitel() {
        return lisTitel;
    }

    public String getLisUntertitel() {
        return lisUntertitel;
    }

    public String getLisURL() {
        return lisURL;
    }
}
