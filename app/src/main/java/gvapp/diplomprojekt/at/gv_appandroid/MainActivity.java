package gvapp.diplomprojekt.at.gv_appandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import gvapp.diplomprojekt.at.gv_appandroid.DesignKlassen.ApplyColor;
import gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten.NeuigkeitenListe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context ctx = this;

        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.neuigkeiten),
                        new PrimaryDrawerItem().withName(R.string.neuigkeiten) //pos: 1
                                .withIcon(GoogleMaterial.Icon.gmd_info_outline),

                        new SectionDrawerItem().withName(R.string.gesundheit),
                        new PrimaryDrawerItem().withName(R.string.aerzteverzeichnis) //pos: 3
                                .withIcon(GoogleMaterial.Icon.gmd_hospital),
                        new PrimaryDrawerItem().withName(R.string.aerztefinder) //pos: 4
                                .withIcon(GoogleMaterial.Icon.gmd_hospital),

                        new SectionDrawerItem().withName(R.string.ernaehrung),
                        new PrimaryDrawerItem().withName(R.string.restaurantsuche) //pos:6
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining),
                        new PrimaryDrawerItem().withName(R.string.rezepte) //pos: 7
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining),
                        new PrimaryDrawerItem().withName(R.string.diaeten) //pos: 8
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining),
                        new PrimaryDrawerItem().withName(R.string.trinkerinnerung) //pos: 9
                                .withIcon(GoogleMaterial.Icon.gmd_local_drink),
                        new PrimaryDrawerItem().withName(R.string.bmirechner) //pos: 10
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining),
                        new PrimaryDrawerItem().withName(R.string.kfarechner) //pos: 11
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining),

                        new SectionDrawerItem().withName(R.string.sport),
                        new PrimaryDrawerItem().withName(R.string.uebungen) //pos: 13
                                .withIcon(GoogleMaterial.Icon.gmd_run),
                        new PrimaryDrawerItem().withName(R.string.trainingsplaene) //pos: 14
                                .withIcon(GoogleMaterial.Icon.gmd_run),
                        new PrimaryDrawerItem().withName(R.string.sportstaettensuche) //pos: 15
                                .withIcon(GoogleMaterial.Icon.gmd_run),
                        new PrimaryDrawerItem().withName(R.string.schrittzaehler) //pos: 16
                                .withIcon(GoogleMaterial.Icon.gmd_run),
                        new PrimaryDrawerItem().withName(R.string.wettbewerb) //pos: 17
                                .withIcon(GoogleMaterial.Icon.gmd_run),
                        new PrimaryDrawerItem().withName(R.string.bestenliste) //pos: 18
                                .withIcon(GoogleMaterial.Icon.gmd_run),

                        new SectionDrawerItem().withName(R.string.notruf),
                        new PrimaryDrawerItem().withName(R.string.notrufe) //pos: 20
                                .withIcon(GoogleMaterial.Icon.gmd_phone_in_talk)
                )
                .build();

        result.setOnDrawerItemClickListener((new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                // do something with the clicked item :D

                switch (position) {
                    case 1:
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        NeuigkeitenListe fragment = new NeuigkeitenListe();
                        ApplyColor.ApplyColorNews(result, ctx, R.string.neuigkeiten);
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();
                }

                return false;
            }
        }));

        result.setSelection(2, true);

        //disable scrollbar :D it's ugly
        result.getRecyclerView().setVerticalScrollBarEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
