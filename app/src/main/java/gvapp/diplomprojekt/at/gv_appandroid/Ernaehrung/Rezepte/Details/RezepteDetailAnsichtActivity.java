package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import gvapp.diplomprojekt.at.gv_appandroid.R;

public class RezepteDetailAnsichtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezepte_detail_ansicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().setTitle("Schnitzel");

        /*rezept = Constants.rez;

        ((TextView) findViewById(R.id.tvTitel)).setText(rezept.getLisTitel() + "");

        if (rezept.getBildUrl() != null) {
            new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)))
                    .execute(rezept.getBildUrl());
        }*/
    }
}
