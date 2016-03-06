package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank.Restaurants;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank.RestaurantsDbHelper;


/**
 * Created by Dennis on 21.02.2016.
 */

public class RezepteDataSource {


    private static final String LOG_TAG = RezepteDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private RezepteDbHelper dbHelper;

    private String[] columns = {
            RezepteDbHelper.COLUMN_ID,
            RezepteDbHelper.COLUMN_NAME,
            RezepteDbHelper.COLUMN_BILD,
            RezepteDbHelper.COLUMN_KOCHDAUER,
            RezepteDbHelper.COLUMN_SCHWIERIGKEITSGRAD,
            RezepteDbHelper.COLUMN_NUMMER,
            RezepteDbHelper.COLUMN_ANZAHL,
            RezepteDbHelper.COLUMN_R_ID,
            RezepteDbHelper.COLUMN_TIPP,
            RezepteDbHelper.COLUMN_TEXT,
            RezepteDbHelper.COLUMN_EINHEIT,

    };

    public RezepteDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den DbHelper.");
        dbHelper = new RezepteDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }
    public Rezepte createRezepte(String name, String bild, String kochdauer, String schwierigkeitsgrad,
                                         String tipp) {
        ContentValues values = new ContentValues();
        values.put(RestaurantsDbHelper.COLUMN_NAME, name);
        values.put(RestaurantsDbHelper.COLUMN_BILD, bild);
        //   values.put(RestaurantsDbHelper.COLUMN_KOCHDAUER, kochdauer);
        //   values.put(RestaurantsDbHelper.COLUMN_SCHWIERIGKEITSGRAD, schwierigkeitsgrad);
        //   values.put(RestaurantsDbHelper.COLUMN_TIPP, tipp);

        long insertId = database.insert(RestaurantsDbHelper.TABLE_RESTAURANT, null, values);

        Cursor cursor = database.query(RestaurantsDbHelper.TABLE_RESTAURANT,
                columns, RestaurantsDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Restaurants restaurants = cursorToRestaurants(cursor);
        cursor.close();

     /*   return restaurants; */
        return null;

    }
    private Restaurants cursorToRestaurants(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_NAME);
        int idAdresse = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_ADRESSE);
        int idBild = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_BILD);
        int idInfo = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_INFO);
        int idKategorie = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_KATEGORIE);
        int idOeffnungszeiten = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_OEFFNUNGSZEITEN);
        int idTelefonnr = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_TELEFONNR);
        int idweitereinfos = cursor.getColumnIndex(RestaurantsDbHelper.COLUMN_WEITEREINFOS);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(idName);
        String bild = cursor.getString(idBild);
        String adresse = cursor.getString(idAdresse);
        String info = cursor.getString(idInfo);
        String kategorie = cursor.getString(idKategorie);
        String oeffnungszeiten = cursor.getString(idOeffnungszeiten);
        int telefonnr = cursor.getInt(idTelefonnr);
        String weitereinfos = cursor.getString(idweitereinfos);


        Restaurants restaurants = new Restaurants(id, name, kategorie, bild, info, telefonnr,
                oeffnungszeiten, adresse, weitereinfos);

        return restaurants;
    }
    public List<Restaurants> getAllRestaurants() {
        List<Restaurants> restaurantsList = new ArrayList<>();

        Cursor cursor = database.query(RestaurantsDbHelper.TABLE_RESTAURANT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Restaurants restaurants;

        while(!cursor.isAfterLast()) {
            restaurants = cursorToRestaurants(cursor);
            restaurantsList.add(restaurants);
            Log.d(LOG_TAG, "ID: " + restaurants.getId() + ", Inhalt: " + restaurants.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return restaurantsList;
    }
}
