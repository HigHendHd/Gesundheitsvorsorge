package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Liste;

import android.content.Context;
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
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class AerzteListe extends Liste implements DownloadXmlTask.XmlDownloader {

    public AerzteListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        //eintraege.add(new ListenEintrag("Breaking News", "Sack Reis umgefallen", null));
        super.setmAdapter(new AerzteAdapter(eintraege));

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

            new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_AERZTE_BASE + Constants.URL_AERZTE_LISTE);

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
    public void itemClicked(View v, int position) {

    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            eintraege.clear();
            try {
                eintraege.addAll(new AerzteListenParser().parse(result));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        } else {
            Snackbar.make(getView(), "Fehler", Snackbar.LENGTH_LONG).show();
        }
        super.xmlDownloaded(result);
    }
}