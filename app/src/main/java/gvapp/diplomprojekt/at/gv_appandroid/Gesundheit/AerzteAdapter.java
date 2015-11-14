package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Adapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class AerzteAdapter
        extends Adapter {

    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AerzteAdapter(String[] myDataset) {
        super(myDataset);
    }
}
