package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Diaeten.Details;

/**
 * Created by deathkid535 on 3/12/16.
 */
public class Diaet {
    private String name;
    private String info;
    private String erklaerung;
    private String vorteile;
    private String nachteile;
    private String fazit;
    private String bildUrl;

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getErklaerung() {
        return erklaerung;
    }

    public void setErklaerung(String erklaerung) {
        this.erklaerung = erklaerung;
    }

    public String getVorteile() {
        return vorteile;
    }

    public void setVorteile(String vorteile) {
        this.vorteile = vorteile;
    }

    public String getNachteile() {
        return nachteile;
    }

    public void setNachteile(String nachteile) {
        this.nachteile = nachteile;
    }

    public String getFazit() {
        return fazit;
    }

    public void setFazit(String fazit) {
        this.fazit = fazit;
    }
}
