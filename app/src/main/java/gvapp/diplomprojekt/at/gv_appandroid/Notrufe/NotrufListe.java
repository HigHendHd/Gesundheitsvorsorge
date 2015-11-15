package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufListe extends Liste {

    public NotrufListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        super.setmAdapter(new NotrufAdapter(NotrufNummer.createNotrufNummern(getActivity())));

        return v;
    }
}
