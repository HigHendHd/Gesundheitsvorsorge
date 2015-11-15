package gvapp.diplomprojekt.at.gv_appandroid.Sport.Trainingsplaene;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;
import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class TrainingsplanAdapter extends ListenAdapter {

    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrainingsplanAdapter(ListenEintrag dataset) {
        super(dataset);
    }
}
