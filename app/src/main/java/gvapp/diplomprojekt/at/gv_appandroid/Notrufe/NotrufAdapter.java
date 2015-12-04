package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufAdapter extends ListenAdapter {

    // Provide a suitable constructor (depends on the kind of dataset)
    public NotrufAdapter(List<NotrufNummer> liste) {
        super(liste);
    }
}
