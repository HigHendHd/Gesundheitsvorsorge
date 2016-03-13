package gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten.Details;

/**
 * Created by deathkid535 on 3/13/16.
 */
public class Neuigkeit {
    private String bildUrl;
    private String text;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
