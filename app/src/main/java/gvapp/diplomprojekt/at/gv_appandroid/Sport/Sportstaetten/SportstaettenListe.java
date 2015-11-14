package gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;
import gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten.NeuigkeitenAdapter;

/**
 * Created by Dennis on 14.11.2015.
 */
public class SportstaettenListe extends Liste {

    public SportstaettenListe() {
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
