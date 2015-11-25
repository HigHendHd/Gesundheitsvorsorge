package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;
import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class RezepteListe extends Liste {

    public RezepteListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ListenEintrag le = new ListenEintrag();
        le.addItem("Breaking News", "Sack Reis umgefallen");
        super.setmAdapter(new RezepteAdapter(le));

        createData(v);

        return v;
    }

    private void createData(View v) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Snackbar.make(v, getActivity().getString(R.string.keininternet),
                    Snackbar.LENGTH_INDEFINITE);
        } else {
            Snackbar.make(v, getActivity().getString(R.string.keininternet),
                    Snackbar.LENGTH_INDEFINITE);
        }
    }
}
