package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import java.net.URL;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected String lisTitel;
    protected String lisUntertitel;
    protected URL lisURL;

    public ListenEintrag(String lisTitel, String lisUntertitel, URL lisURL) {
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

    public URL getLisURL() {
        return lisURL;
    }
}
