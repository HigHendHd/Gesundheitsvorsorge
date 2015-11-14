package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenEintrag {
    protected List<String> lisTitel, lisUntertitel = new ArrayList<String>();

    public ListenEintrag(String titel, String untertitel) {
        lisTitel.add(titel);
        lisUntertitel.add(untertitel);
    }
}
