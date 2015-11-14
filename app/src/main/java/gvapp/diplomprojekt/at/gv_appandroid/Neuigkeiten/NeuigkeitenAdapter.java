package gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Adapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NeuigkeitenAdapter extends Adapter {

    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NeuigkeitenAdapter(String[] myDataset) {
        super(myDataset);
    }
}
