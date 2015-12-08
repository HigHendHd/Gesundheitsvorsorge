package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected String lisTitel;
    protected String lisUntertitel;
    protected String lisURL;
    protected String thumbnailUrl;
    protected int id;

    public ListenEintrag() {

    }

    public ListenEintrag(String lisTitel, String lisUntertitel, String lisURL, int id, String thumbnailUrl) {
        this.lisTitel = lisTitel;
        this.lisUntertitel = lisUntertitel;
        this.lisURL = lisURL;
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
