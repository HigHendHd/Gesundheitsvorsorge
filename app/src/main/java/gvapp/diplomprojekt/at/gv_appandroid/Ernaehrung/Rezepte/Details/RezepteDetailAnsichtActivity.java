package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadImageTask;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class RezepteDetailAnsichtActivity extends AppCompatActivity implements DownloadXmlTask.XmlDownloader {

    Rezept rezept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezepte_detail_ansicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new DownloadXmlTask(this).execute(Constants.URL_REZEPTE_BASE + Constants.URL_REZEPT_AKTUELL);
    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            try {
                rezept = new RezepteXmlParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            getSupportActionBar().setTitle(rezept.getName() + "");

            if (rezept.getBildUrl() != null) {
                new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)))
                        .execute(rezept.getBildUrl());
            }

            LinearLayout llPortionen = (LinearLayout) findViewById(R.id.llPortionen);

            TextView tvPortionen = new TextView(this);
            tvPortionen.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tvPortionen.setText(" " + rezept.getAnzahlportionen());

            llPortionen.addView(tvPortionen);

            LinearLayout llKochdauer = (LinearLayout) findViewById(R.id.llKochdauer);

            TextView tvKochdauer = new TextView(this);
            tvKochdauer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tvKochdauer.setText(" " + rezept.getKochdauer());

            llKochdauer.addView(tvKochdauer);
        }
    }
}
