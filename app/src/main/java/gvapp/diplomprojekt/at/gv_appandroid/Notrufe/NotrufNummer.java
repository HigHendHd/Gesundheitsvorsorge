package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import android.content.Context;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufNummer extends ListenEintrag {

    public NotrufNummer() {

    }

    public NotrufNummer addItem(int nameRes, String nummer, Context ctx) {
        lisTitel = ctx.getResources().getString(nameRes);
        lisUntertitel = nummer;
        return this;
    }
}
