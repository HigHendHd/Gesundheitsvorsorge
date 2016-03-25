package gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.DetailActivity;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadImageTask;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class SportstaettenDetailActivity extends DetailActivity implements DownloadXmlTask.XmlDownloader {

    Sportstaette sportstaette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportstaetten_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_SPORTSTAETTEN_BASE +
                getIntent().getStringExtra(Constants.INTENT_AERZTE_URL));
    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            try {
                sportstaette = new SportstaettenDetailParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fillData() {
        getSupportActionBar().setTitle(sportstaette.getName() + "");

        if (sportstaette.getBildUrl() != null) {
            new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)),
                    ((ProgressBar) findViewById(R.id.pbProgress)))
                    .execute(sportstaette.getBildUrl());
        }

        ((TextView) findViewById(R.id.tvKategorie)).setText(sportstaette.getKategorie() + "");

        ((RatingBar) findViewById(R.id.rbRating)).setRating(sportstaette.getBewertung());

        ((TextView) findViewById(R.id.tvInfo)).setText(sportstaette.getInfo() + "");

        ((TextView) findViewById(R.id.tvWeitereInfos)).setText(sportstaette.getWeitereinfos() + "");

        ((Button) findViewById(R.id.bAdresse)).setText(sportstaette.getAdressString() + "");
        ((Button) findViewById(R.id.bTelNummer)).setText(sportstaette.getTelefonnummer() + "");
    }

    public void openMaps(View v) {
        Uri uri = Uri.parse("geo:0,0?q=" + sportstaette.getAdressString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void dial(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + sportstaette.getTelefonnummer()));
        startActivity(intent);
    }
}
