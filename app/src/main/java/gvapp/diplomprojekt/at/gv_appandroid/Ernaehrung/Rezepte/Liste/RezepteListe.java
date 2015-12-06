package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Liste;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details.RezepteDetailAnsichtActivity;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class RezepteListe extends Liste {

    RezepteAdapter rezeptAdapter;

    public RezepteListe() {
        super();
    }

    @Override
    public void itemClicked(View v, int position) {
        Constants.selected_rezepte_id = ((RezeptListenEintrag) eintraege.get(position)).getId();
        Constants.URL_REZEPT_AKTUELL = eintraege.get(position).getLisURL();
        Intent intent = new Intent(getActivity(), RezepteDetailAnsichtActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        rezeptAdapter = new RezepteAdapter(eintraege);
        super.setmAdapter(rezeptAdapter);
        rezeptAdapter.setClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createData();
    }

    private void createData() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new DownloadXmlTask(this).execute(Constants.URL_REZEPTE_BASE + Constants.URL_REZEPTE_LISTE);

        } else {
            Snackbar.make(getView(), getActivity().getString(R.string.keininternet),
                    Snackbar.LENGTH_INDEFINITE).setAction(getActivity().getResources()
                    .getString(R.string.nochmalversuchen), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createData();
                }
            }).show();
        }
    }

    @Override
    public void setEintraege(InputStream result) {
        if (result != null) {
            eintraege.clear();
            try {
                eintraege.addAll(new RezepteListenParser().parse(result));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        } else {
            Snackbar.make(getView(), "Fehler", Snackbar.LENGTH_LONG).show();
        }
    }
}
