package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 06.12.2015.
 */
public class Rezept {
    private String name;
    private int id;
    private String bildUrl;
    private int anzahlportionen;
    private String kochdauer;
    private String schwierigkeitsgrad;
    private List<Zutat> zutaten = new ArrayList<>();
    private List<Schritt> schritte = new ArrayList<>();
    private String tipp;

    public List<Schritt> getSchritte() {
        return schritte;
    }

    public void setSchritte(List<Schritt> schritte) {
        this.schritte = schritte;
    }

    public void addSchritt(Schritt s) {
        schritte.add(s);
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public void addZutat(Zutat z) {
        zutaten.add(z);
    }

    public int getAnzahlportionen() {
        return anzahlportionen;
    }

    public void setAnzahlportionen(int anzahlportionen) {
        this.anzahlportionen = anzahlportionen;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKochdauer() {
        return kochdauer;
    }

    public void setKochdauer(String kochdauer) {
        this.kochdauer = kochdauer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
