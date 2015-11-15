package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected List<String> lisTitel, lisUntertitel;

    public ListenEintrag() {
        lisTitel = new ArrayList<>();
        lisUntertitel = new ArrayList<>();
    }

    public void addItem(String titel, String untertitel) {
        lisTitel.add(titel);
        lisUntertitel.add(untertitel);
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
