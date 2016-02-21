package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

/**
 * Created by Dennis on 21.02.2016.
 */
public class Rezepte {

    private long id;
    private String name;
    private String bild;
    private String kochdauer;
    private String schwierigkeitsgrad;
    private String tipp;

    public Rezepte(long id, String name, String bild, String kochdauer, String schwierigkeitsgrad, String tipp) {
        this.id = id;
        this.name = name;
        this.bild = bild;
        this.kochdauer = kochdauer;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.tipp = tipp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public String getKochdauer() {
        return kochdauer;
    }

    public void setKochdauer(String kochdauer) {
        this.kochdauer = kochdauer;
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public String getTipp() {
        return tipp;
    }

    public void setTipp(String tipp) {
        this.tipp = tipp;
    }
}
