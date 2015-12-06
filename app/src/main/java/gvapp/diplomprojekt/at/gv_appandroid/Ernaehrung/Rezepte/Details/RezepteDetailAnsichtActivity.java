package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
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

            ((TextView) findViewById(R.id.tvTitel)).setText(rezept.getName() + "");

            if (rezept.getBildUrl() != null) {
                new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)))
                        .execute(rezept.getBildUrl());
            }
        }
    }
}
