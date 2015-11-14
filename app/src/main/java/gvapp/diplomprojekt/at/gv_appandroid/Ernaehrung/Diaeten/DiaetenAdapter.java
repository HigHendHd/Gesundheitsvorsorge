package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Diaeten;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class DiaetenAdapter extends ListenAdapter {

    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DiaetenAdapter(String[] myDataset) {
        super(myDataset);
    }
}
