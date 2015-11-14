package gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeuigkeitenListe extends Liste {

    public NeuigkeitenListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        super.setmAdapter(new NeuigkeitenAdapter(new String[10]));
        return v;
    }
}
