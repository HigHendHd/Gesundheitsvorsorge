package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 20.02.2016.
 */
public class RestaurantsDataSource {

    private static final String LOG_TAG = RestaurantsDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private RestaurantsDbHelper dbHelper;

    private String[] columns = {
            RestaurantsDbHelper.COLUMN_ID,
            RestaurantsDbHelper.COLUMN_NAME,
            RestaurantsDbHelper.COLUMN_ADRESSE,
            RestaurantsDbHelper.COLUMN_BILD,
            RestaurantsDbHelper.COLUMN_INFO,
            RestaurantsDbHelper.COLUMN_KATEGORIE,
            RestaurantsDbHelper.COLUMN_OEFFNUNGSZEITEN,
            RestaurantsDbHelper.COLUMN_TELEFONNR,
            RestaurantsDbHelper.COLUMN_WEITEREINFOS
    };

    public RestaurantsDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den DbHelper.");
        dbHelper = new RestaurantsDbHelper(context);
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
    public Restaurants createRestaurants(String name, String adresse, String bild, String info, String kategorie,
                                         String oeffnungszeiten, int telefonnr, String weitereinfos) {
        ContentValues values = new ContentValues();
        values.put(RestaurantsDbHelper.COLUMN_NAME, name);
        values.put(RestaurantsDbHelper.COLUMN_ADRESSE, adresse);
        values.put(RestaurantsDbHelper.COLUMN_BILD, bild);
        values.put(RestaurantsDbHelper.COLUMN_INFO, info);
        values.put(RestaurantsDbHelper.COLUMN_KATEGORIE, kategorie);
        values.put(RestaurantsDbHelper.COLUMN_OEFFNUNGSZEITEN, oeffnungszeiten);
        values.put(RestaurantsDbHelper.COLUMN_TELEFONNR, telefonnr);
        values.put(RestaurantsDbHelper.COLUMN_WEITEREINFOS, weitereinfos);

        long insertId = database.insert(RestaurantsDbHelper.TABLE_RESTAURANT, null, values);

        Cursor cursor = database.query(RestaurantsDbHelper.TABLE_RESTAURANT,
                columns, RestaurantsDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Restaurants restaurants = cursorToRestaurants(cursor);
        cursor.close();

        return restaurants;
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


        Restaurants restaurants = new Restaurants(id, name, kategorie, bild, info, telefonnr, oeffnungszeiten, adresse, weitereinfos);

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
