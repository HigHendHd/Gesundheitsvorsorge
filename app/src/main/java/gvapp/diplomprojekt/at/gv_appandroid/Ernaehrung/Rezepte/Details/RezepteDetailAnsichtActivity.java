package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.graphics.Typeface;
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

            LinearLayout llSchwierigkeitsgrad = (LinearLayout) findViewById(R.id.llSchwierigkeitsgrad);
            TextView tvSchwierigkeitsgrad = new TextView(this);
            tvSchwierigkeitsgrad.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tvSchwierigkeitsgrad.setText(" " + rezept.getSchwierigkeitsgrad());
            llSchwierigkeitsgrad.addView(tvSchwierigkeitsgrad);

            LinearLayout llZutaten = (LinearLayout) findViewById(R.id.llZutaten);
            for (Zutat z : rezept.getZutaten()) {
                LinearLayout llZutat = new LinearLayout(this);
                llZutat.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                llZutaten.addView(llZutat);

                TextView tvAnzahl = new TextView(this);
                tvAnzahl.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvAnzahl.setText("" + z.getAnzahl());
                llZutat.addView(tvAnzahl);

                TextView tvEinheit = new TextView(this);
                tvEinheit.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvEinheit.setText(" " + z.getEinheit());
                llZutat.addView(tvEinheit);

                TextView tvZname = new TextView(this);
                tvZname.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvZname.setText(" " + z.getZname());
                llZutat.addView(tvZname);
            }

            LinearLayout llSchritte = (LinearLayout) findViewById(R.id.llSchritte);
            for (Schritt s : rezept.getSchritte()) {
                LinearLayout llSchritt = new LinearLayout(this);
                llSchritt.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                llSchritt.setPadding(0, 20, 0, 20);
                llSchritt.setOrientation(LinearLayout.VERTICAL);
                llSchritte.addView(llSchritt);

                TextView tvSchrittNr = new TextView(this);
                tvSchrittNr.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvSchrittNr.setPadding(0, 10, 0, 10);
                tvSchrittNr.setTypeface(null, Typeface.BOLD);
                tvSchrittNr.setText(getResources().getString(R.string.schritt) + " " + s.getNummer() + ":");
                llSchritt.addView(tvSchrittNr);

                TextView tvSchritt = new TextView(this);
                tvSchritt.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvSchritt.setText("" + s.getText());
                llSchritt.addView(tvSchritt);
            }

            if (rezept.getTipp() != null) {
                ((TextView) findViewById(R.id.tvTippInfo)).setText(getResources().getString(R.string.tipp));
                LinearLayout llTipp = (LinearLayout) findViewById(R.id.llTipp);
                TextView tvTipp = new TextView(this);
                tvTipp.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvTipp.setText(rezept.getTipp() + "");
                llTipp.addView(tvTipp);
            }
        }
    }
}
