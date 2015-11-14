package gvapp.diplomprojekt.at.gv_appandroid.Sport.Uebungen;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class UebungsAdapter extends ListenAdapter {

    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public UebungsAdapter(String[] myDataset) {
        super(myDataset);
    }
}
