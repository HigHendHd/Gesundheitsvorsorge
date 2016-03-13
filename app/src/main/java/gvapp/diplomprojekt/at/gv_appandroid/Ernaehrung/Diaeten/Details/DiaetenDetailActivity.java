package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Diaeten.Details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.DetailActivity;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class DiaetenDetailActivity extends DetailActivity implements DownloadXmlTask.XmlDownloader {

    Diaet diaet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaeten_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.v("Test", Constants.URL_BASE + Constants.URL_DIAETEN_BASE +
                getIntent().getStringExtra(Constants.INTENT_DIAETEN_URL));
        new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_DIAETEN_BASE +
                getIntent().getStringExtra(Constants.INTENT_DIAETEN_URL));
    }


    @Override
    public void xmlDownloaded(InputStream result) {
        /*if (result != null) {
            try {
                diaet = new DiaetenXmlParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getSupportActionBar().setTitle(diaet.getName() + "");

            if (diaet.getBildUrl() != null) {
                new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)),
                        ((ProgressBar) findViewById(R.id.pbProgress)))
                        .execute(diaet.getBildUrl());
            }

            ((TextView) findViewById(R.id.tvInfo)).setText(diaet.getInfo());

            ((TextView) findViewById(R.id.tvErklaerung)).setText(diaet.getErklaerung());

            ((TextView) findViewById(R.id.tvVorteile)).setText(diaet.getVorteile());

            ((TextView) findViewById(R.id.tvNachteile)).setText(diaet.getNachteile());

            ((TextView) findViewById(R.id.tvFazit)).setText(diaet.getFazit());
        }*/
    }

    @Override
    public void fillData() {

    }
}
