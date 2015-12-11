package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class AerzteDetailActivity extends AppCompatActivity implements DownloadXmlTask.XmlDownloader {

    Arzt arzt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aerzte_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_REZEPTE_BASE +
                getIntent().getStringExtra(Constants.INTENT_REZEPTE_URL));
    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            try {
                arzt = new AerzteXmlParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
