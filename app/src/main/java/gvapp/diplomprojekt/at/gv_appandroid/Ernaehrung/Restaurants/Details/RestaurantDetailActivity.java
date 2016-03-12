package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class RestaurantDetailActivity extends AppCompatActivity implements DownloadXmlTask.XmlDownloader {

    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
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
                restaurant = new RestaurantXmlParser().parse(result);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getSupportActionBar().setTitle(restaurant.getName() + "");

            if (restaurant.getBild() != null) {
                new DownloadImageTask(((ImageView) findViewById(R.id.ivImage)),
                        ((ProgressBar) findViewById(R.id.pbProgress)))
                        .execute(restaurant.getBild());
            }
            ((TextView) findViewById(R.id.tvKategorie)).setText(restaurant.getKategorie() + "");

            LinearLayout llMainLayout = (LinearLayout) findViewById(R.id.llMainLayout);


            TextView tvOeffnungszeiten = (TextView) findViewById(R.id.tvOeffnungszeiten);
            tvOeffnungszeiten.setText(restaurant.getOeffnungszeiten());

            TextView tvInfo = (TextView) findViewById(R.id.tvInfo);
            tvInfo.setText(restaurant.getInfo());

            TextView tvWeitereInfo = (TextView) findViewById(R.id.tvWeitereInfo);
            tvWeitereInfo.setText(restaurant.getWeiterinfos());


        }
    }


    public void openMaps(View v) {
        Uri uri = Uri.parse("geo:0,0?q=" + restaurant.getAdressString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void dial(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + restaurant.getTelefonnr()));
        startActivity(intent);
    }
}
