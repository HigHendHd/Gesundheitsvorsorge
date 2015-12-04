package gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten;

import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenAdapter;
import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NeuigkeitenAdapter extends ListenAdapter {

    // Provide a suitable constructor (depends on the kind of dataset)
    public NeuigkeitenAdapter(List<ListenEintrag> dataset) {
        super(dataset);
    }
}
