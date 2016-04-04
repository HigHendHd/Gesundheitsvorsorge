package gvapp.diplomprojekt.at.gv_appandroid.Sport.Trainingsplaene.Details;

import java.util.List;

/**
 * Created by Dennis on 04.04.2016.
 */
public class Trainingsplan {
    private String name;
    private String bildUrl;
    private String info;
    private String hinweis;
    private List<Tag> tage;

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public String getHinweis() {
        return hinweis;
    }

    public void setHinweis(String hinweis) {
        this.hinweis = hinweis;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTage() {
        return tage;
    }

    public void setTage(List<Tag> tage) {
        this.tage = tage;
    }
}
