package gvapp.diplomprojekt.at.gv_appandroid.Sport.Uebungen.Details;

/**
 * Created by Dennis on 04.04.2016.
 */
public class Uebung {
    private String name;
    private String muskelgruppe;
    private String bildUrl;
    private String beschreibung;

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public String getMuskelgruppe() {
        return muskelgruppe;
    }

    public void setMuskelgruppe(String muskelgruppe) {
        this.muskelgruppe = muskelgruppe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
