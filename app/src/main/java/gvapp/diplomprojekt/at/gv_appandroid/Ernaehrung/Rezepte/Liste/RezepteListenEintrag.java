package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Liste;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 04.12.2015.
 */
public class RezepteListenEintrag extends ListenEintrag {

    String bildUrl;
    int id;

    public RezepteListenEintrag(String lisTitel, String lisUntertitel, String lisURL) {
        super(lisTitel, lisUntertitel, lisURL);
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        super.setThumbnailUrl(bildUrl);
        this.bildUrl = bildUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
