package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit;

import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;
import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class AerzteAdapter extends ListenAdapter {

    // Provide a suitable constructor (depends on the kind of dataset)
    public AerzteAdapter(List<ListenEintrag> dataset) {
        super(dataset);
    }
}
