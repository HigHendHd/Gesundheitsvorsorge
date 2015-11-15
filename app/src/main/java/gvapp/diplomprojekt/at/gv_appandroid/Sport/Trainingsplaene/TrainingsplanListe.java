package gvapp.diplomprojekt.at.gv_appandroid.Sport.Trainingsplaene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;
import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class TrainingsplanListe extends Liste {

    public TrainingsplanListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ListenEintrag le = new ListenEintrag();
        le.addItem("Breaking News", "Sack Reis umgefallen");
        super.setmAdapter(new TrainingsplanAdapter(le));

        return v;
    }
}
