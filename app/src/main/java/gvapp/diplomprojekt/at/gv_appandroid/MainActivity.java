package gvapp.diplomprojekt.at.gv_appandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Karte;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.DesignKlassen.ApplyColor;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.BMI_Rechner.BmiFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Diaeten.Liste.DiaetenListe;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.KFA_Rechner.KfaFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Karte.RestaurantFinderFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Liste.RestaurantListe;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Liste.RezepteListe;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.TrinkerinnerungFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Karte.AerzteFinderFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Liste.AerzteListe;
import gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten.Liste.NeuigkeitenListe;
import gvapp.diplomprojekt.at.gv_appandroid.Notrufe.NotrufListe;
import gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Karte.SportstaettenFinderFragment;
import gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Liste.SportstaettenListe;
import gvapp.diplomprojekt.at.gv_appandroid.Sport.Trainingsplaene.TrainingsplanListe;
import gvapp.diplomprojekt.at.gv_appandroid.Sport.Uebungen.UebungsListe;

public class MainActivity extends AppCompatActivity {

    private int mapCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context ctx = this;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NeuigkeitenListe fragment = new NeuigkeitenListe();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.neuigkeiten),
                        new PrimaryDrawerItem().withName(R.string.neuigkeiten) //pos: 1
                                .withIcon(GoogleMaterial.Icon.gmd_info_outline)
                                .withSelectedTextColorRes(R.color.Neuigkeiten500)
                                .withSelectedIconColorRes(R.color.Neuigkeiten500),

                        new SectionDrawerItem().withName(R.string.gesundheit),
                        new PrimaryDrawerItem().withName(R.string.aerzteverzeichnis) //pos: 3
                                .withIcon(GoogleMaterial.Icon.gmd_hospital)
                                .withSelectedIconColorRes(R.color.Gesundheit500)
                                .withSelectedTextColorRes(R.color.Gesundheit500),
                        new PrimaryDrawerItem().withName(R.string.aerztefinder) //pos: 4
                                .withIcon(GoogleMaterial.Icon.gmd_hospital)
                                .withSelectedIconColorRes(R.color.Gesundheit500)
                                .withSelectedTextColorRes(R.color.Gesundheit500),

                        new SectionDrawerItem().withName(R.string.ernaehrung),
                        new PrimaryDrawerItem().withName(R.string.restaurantsuche) //pos:6
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.restaurantfinder) //pos:7
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.rezepte) //pos: 8
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.diaeten) //pos: 9
                                .withIcon(GoogleMaterial.Icon.gmd_local_dining)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.trinkerinnerung) //pos: 10
                                .withIcon(GoogleMaterial.Icon.gmd_local_drink)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.bmirechner) //pos: 11
                                .withIcon(GoogleMaterial.Icon.gmd_keyboard)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),
                        new PrimaryDrawerItem().withName(R.string.kfarechner) //pos: 12
                                .withIcon(GoogleMaterial.Icon.gmd_keyboard)
                                .withSelectedIconColorRes(R.color.Ernaehrung500)
                                .withSelectedTextColorRes(R.color.Ernaehrung500),

                        new SectionDrawerItem().withName(R.string.sport),
                        new PrimaryDrawerItem().withName(R.string.uebungen) //pos: 14
                                .withIcon(GoogleMaterial.Icon.gmd_run)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.trainingsplaene) //pos: 15
                                .withIcon(GoogleMaterial.Icon.gmd_run)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.sportstaettensuche) //pos: 16
                                .withIcon(GoogleMaterial.Icon.gmd_run)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.sportstaettenfinder) //pos: 17
                                .withIcon(GoogleMaterial.Icon.gmd_run)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.schrittzaehler) //pos: 18
                                .withIcon(GoogleMaterial.Icon.gmd_directions_run)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.wettbewerb) //pos: 19
                                .withIcon(GoogleMaterial.Icon.gmd_assignment)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),
                        new PrimaryDrawerItem().withName(R.string.bestenliste) //pos: 20
                                .withIcon(GoogleMaterial.Icon.gmd_nature_people)
                                .withSelectedIconColorRes(R.color.Sport500)
                                .withSelectedTextColorRes(R.color.Sport500),

                        new SectionDrawerItem().withName(R.string.notruf),
                        new PrimaryDrawerItem().withName(R.string.notrufe) //pos: 22
                                .withIcon(GoogleMaterial.Icon.gmd_phone)
                                .withSelectedIconColorRes(R.color.Notruf500)
                                .withSelectedTextColorRes(R.color.Notruf700)
                )
                .build();

        result.setOnDrawerItemClickListener((new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                // do something with the clicked item :D

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (position == 1) {
                    NeuigkeitenListe fragment = new NeuigkeitenListe();
                    ApplyColor.ApplyColorNews(result, ctx, R.string.neuigkeiten);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 3) {
                    AerzteListe fragment = new AerzteListe();
                    ApplyColor.ApplyColorGesundheit(result, ctx, R.string.aerzte);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 4) {
                    Karte mMapFragment = AerzteFinderFragment.newInstance(
                            Constants.URL_BASE + Constants.URL_AERZTE_BASE +
                                    Constants.URL_AERZTE_LISTE, Constants.DATA_TYPE_AERZTE);
                    ApplyColor.ApplyColorGesundheit(result, ctx, R.string.aerztefinder);
                    fragmentTransaction.replace(R.id.fragment_container, mMapFragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 6) {
                    RestaurantListe fragment = new RestaurantListe();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.restaurants);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 7) {
                    Karte fragment = RestaurantFinderFragment.newInstance(
                            Constants.URL_BASE + Constants.URL_RESTAURANT_BASE +
                                    Constants.URL_RESTAURANT_LISTE, Constants.DATA_TYPE_RESTAURANTS
                    );
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.restaurantfinder);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 8) {
                    RezepteListe fragment = new RezepteListe();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.rezepte);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 9) {
                    DiaetenListe fragment = new DiaetenListe();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.diaeten);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 10) {
                    TrinkerinnerungFragment fragment = new TrinkerinnerungFragment();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.trinkerinnerung);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 11) {
                    BmiFragment fragment = new BmiFragment();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.bmirechner);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 12) {
                    KfaFragment fragment = new KfaFragment();
                    ApplyColor.ApplyColorErnaehrung(result, ctx, R.string.kfarechner);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 14) {
                    UebungsListe fragment = new UebungsListe();
                    ApplyColor.ApplyColorSport(result, ctx, R.string.uebungen);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 15) {
                    TrainingsplanListe fragment = new TrainingsplanListe();
                    ApplyColor.ApplyColorSport(result, ctx, R.string.trainingsplaene);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 16) {
                    SportstaettenListe fragment = new SportstaettenListe();
                    ApplyColor.ApplyColorSport(result, ctx, R.string.sportstaetten);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 17) {
                    Karte fragment = SportstaettenFinderFragment.newInstance(
                            Constants.URL_BASE + Constants.URL_SPORTSTAETTEN_BASE +
                                    Constants.URL_SPORTSTAETTEN_LISTE, Constants.DATA_TYPE_SPORTSTAETTEN
                    );
                    ApplyColor.ApplyColorSport(result, ctx, R.string.sportstaettenfinder);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                } else if (position == 22) {
                    NotrufListe fragment = new NotrufListe();
                    ApplyColor.ApplyColorNotruf(result, ctx, R.string.notrufe);
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();

                    return false;
                }

                return false;
            }
        }));

        result.setSelection(1, true);

        //disable scrollbar :D it's ugly
        result.getRecyclerView().setVerticalScrollBarEnabled(false);
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
