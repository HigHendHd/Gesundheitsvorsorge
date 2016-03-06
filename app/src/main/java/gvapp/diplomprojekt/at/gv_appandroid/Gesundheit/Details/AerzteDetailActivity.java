package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadImageTask;
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

        new DownloadXmlTask(this).execute(Constants.URL_BASE + Constants.URL_AERZTE_BASE +
                getIntent().getStringExtra(Constants.INTENT_AERZTE_URL));
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

            getSupportActionBar().setTitle(arzt.getName() + "");

            if (arzt.getBild() != null) {
                new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)),
                        ((ProgressBar) findViewById(R.id.pbProgress)))
                        .execute(arzt.getBild());
            }

            ((TextView) findViewById(R.id.tvFachgebiet)).setText(arzt.getFachgebiet() + "");

            LinearLayout llMainLayout = (LinearLayout) findViewById(R.id.llMainLayout);

            //TextView tvAdresse = (TextView) findViewById(R.id.tvAdresse);
            //tvAdresse.setText(arzt.getAdressString());

            LinearLayout llOrdinationszeiten = (LinearLayout) findViewById(R.id.llOrdinationszeiten);
            for (Ordinationszeit o : arzt.getOrdinationszeiten()) {
                LinearLayout llOrdinationszeit = new LinearLayout(this);
                llOrdinationszeit.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                llOrdinationszeit.setOrientation(LinearLayout.HORIZONTAL);
                llOrdinationszeiten.addView(llOrdinationszeit);

                TextView tvTag = new TextView(this);
                tvTag.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvTag.setPadding(0, 0, 0, 10);
                tvTag.setText(o.getTag() + ": ");
                llOrdinationszeit.addView(tvTag);

                TextView tvZeit = new TextView(this);
                tvZeit.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                tvZeit.setText(o.getStart() + "-" + o.getEnde());
                tvZeit.setTypeface(null, Typeface.BOLD);
                llOrdinationszeit.addView(tvZeit);
            }


            LinearLayout llDiplom = (LinearLayout) findViewById(R.id.llDiplom);
            for (String d : arzt.getDiplome()) {
                LinearLayout llDiplome = new LinearLayout(this);
                llDiplome.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                llDiplom.setOrientation(LinearLayout.VERTICAL);
                llDiplom.addView(llDiplome);


                TextView tvDiplom = new TextView(this);
                tvDiplom.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvDiplom.setPadding(0, 0, 0, 10);
                tvDiplom.setText(d);
                llDiplome.addView(tvDiplom);

            }


            LinearLayout llAngebot = (LinearLayout) findViewById(R.id.llAngebot);
            for (String a : arzt.getAngebote()) {
                LinearLayout llAngebote = new LinearLayout(this);
                llAngebote.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                llAngebot.setOrientation(LinearLayout.VERTICAL);
                llAngebot.addView(llAngebote);

                TextView tvAngebote = new TextView(this);
                tvAngebote.setText("Angebote: \n");
                TextView tvAngebot = new TextView(this);
                tvAngebot.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tvAngebot.setPadding(0, 0, 0, 10);
                tvAngebot.setText(a);
                llAngebote.addView(tvAngebot);

            }


            TextView tvKrankenkassa = (TextView) findViewById(R.id.tvKrankenkassa);
            tvKrankenkassa.setText(arzt.getKrankenkassen() + "");

            TextView tvFremdsprachen = (TextView) findViewById(R.id.tvFremdsprachen);
            tvFremdsprachen.setText(arzt.getFremdsprachen() + "");












            ((Button) findViewById(R.id.bAdresse)).setText(arzt.getAdressString() + "");
            ((Button) findViewById(R.id.bTelNummer)).setText(arzt.getErreichbarkeit().getTelefon() + "");
        }
    }

    public void openMaps(View v) {
        Uri uri = Uri.parse("geo:0,0?q=" + arzt.getAdressString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void dial(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + arzt.getErreichbarkeit().getTelefon()));
        startActivity(intent);
    }
}
