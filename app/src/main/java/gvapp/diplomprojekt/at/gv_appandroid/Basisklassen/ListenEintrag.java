package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected List<String> lisTitel, lisUntertitel;
    protected List<URL> lisURL;
    private int workingInt = 0;

    public ListenEintrag() {
        lisTitel = new ArrayList<>();
        lisUntertitel = new ArrayList<>();
        lisURL = new ArrayList<>();
    }

    public void addItem(String titel, String untertitel) {
        lisTitel.add(titel);
        lisUntertitel.add(untertitel);
        workingInt++;
    }

    public void addUrl(URL url) {
        lisURL.add(url);
    }

    public String getTitel(int position) {
        return lisTitel.get(position);
    }

    public String getUntertitel(int position) {
        return lisUntertitel.get(position);
    }

    public int getLenght() {
        return lisTitel.size();
    }
}
