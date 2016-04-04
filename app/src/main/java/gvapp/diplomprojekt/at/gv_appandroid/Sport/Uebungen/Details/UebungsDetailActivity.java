package gvapp.diplomprojekt.at.gv_appandroid.Sport.Uebungen.Details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.DetailActivity;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadImageTask;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class UebungsDetailActivity extends DetailActivity implements DownloadXmlTask.XmlDownloader {

    Uebung uebung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungs_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_UEBUNGEN_BASE +
                getIntent().getStringExtra(Constants.INTENT_UEBUNGEN_URL));
    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            try {
                uebung = new UebungsDetailParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fillData() {
        getSupportActionBar().setTitle(uebung.getName() + "");

        if (uebung.getBildUrl() != null) {
            new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)),
                    ((ProgressBar) findViewById(R.id.pbProgress)))
                    .execute(uebung.getBildUrl());
        }

        ((TextView) findViewById(R.id.tvMuskelgruppe)).setText(uebung.getMuskelgruppe() + "");

        ((TextView) findViewById(R.id.tvBeschreibung)).setText(uebung.getBeschreibung() + "");
    }
}
