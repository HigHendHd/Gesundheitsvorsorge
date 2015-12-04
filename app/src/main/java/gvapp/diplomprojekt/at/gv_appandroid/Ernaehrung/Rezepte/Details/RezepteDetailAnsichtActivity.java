package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.DownloadImageTask;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Liste.RezeptListenEintrag;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class RezepteDetailAnsichtActivity extends AppCompatActivity {

    RezeptListenEintrag rezept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezepte_detail_ansicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rezept = Constants.rez;

        ((TextView) findViewById(R.id.tvTitel)).setText(rezept.getLisTitel() + "");

        if (rezept.getBildUrl() != null) {
            new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)))
                    .execute(rezept.getBildUrl());
        }
    }
}
