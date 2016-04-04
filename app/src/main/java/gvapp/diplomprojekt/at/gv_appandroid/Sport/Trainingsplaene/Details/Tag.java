package gvapp.diplomprojekt.at.gv_appandroid.Sport.Trainingsplaene.Details;

import java.util.List;

/**
 * Created by Dennis on 04.04.2016.
 */
public class Tag {
    private String tagnummer;
    private List<Uebung> uebungen;

    public String getTagnummer() {
        return tagnummer;
    }

    public void setTagnummer(String tagnummer) {
        this.tagnummer = tagnummer;
    }

    public List<Uebung> getUebungen() {
        return uebungen;
    }

    public void setUebungen(List<Uebung> uebungen) {
        this.uebungen = uebungen;
    }
}
