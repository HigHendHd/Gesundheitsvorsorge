package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



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
        values.put(RezepteDbHelper.COLUMN_NAME, name);
        values.put(RezepteDbHelper.COLUMN_BILD, bild);
        //   values.put(RestaurantsDbHelper.COLUMN_KOCHDAUER, kochdauer);
        //   values.put(RestaurantsDbHelper.COLUMN_SCHWIERIGKEITSGRAD, schwierigkeitsgrad);
        //   values.put(RestaurantsDbHelper.COLUMN_TIPP, tipp);

        long insertId = database.insert(RezepteDbHelper.TABLE_REZEPTE, null, values);

        Cursor cursor = database.query(RezepteDbHelper.TABLE_REZEPTE,
                columns, RezepteDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Rezepte rezepte = cursorToRezepte(cursor);
        cursor.close();

     /*   return restaurants; */
        return null;

    }
    private Rezepte cursorToRezepte(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(RezepteDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(RezepteDbHelper.COLUMN_NAME);
        int idBild = cursor.getColumnIndex(RezepteDbHelper.COLUMN_BILD);
        int idKochdauer = cursor.getColumnIndex(RezepteDbHelper.COLUMN_KOCHDAUER);
        int idSchwierigkeitsgrad = cursor.getColumnIndex(RezepteDbHelper.COLUMN_SCHWIERIGKEITSGRAD);
        int idR_id = cursor.getColumnIndex(RezepteDbHelper.COLUMN_R_ID);
        int idTipp = cursor.getColumnIndex(RezepteDbHelper.COLUMN_TIPP);
        int idText = cursor.getColumnIndex(RezepteDbHelper.COLUMN_TEXT);
        int idEinheit = cursor.getColumnIndex(RezepteDbHelper.COLUMN_EINHEIT);

        long id = cursor.getLong(idIndex);
        String name = cursor.getString(idName);
        String bild = cursor.getString(idBild);
        String kochdauer = cursor.getString(idKochdauer);
        String schwierigkeitsgrad = cursor.getString(idSchwierigkeitsgrad);
        long r_id = cursor.getLong(idR_id);
        String tipp = cursor.getString(idTipp);
        int text = cursor.getInt(idText);
        String einheit = cursor.getString(idEinheit);


        Rezepte rezepte = new Rezepte(id, name, bild, kochdauer, schwierigkeitsgrad,
                r_id, tipp, text, einheit);

        return rezepte;
    }
    public List<Rezepte> getAllRezepte() {
        List<Rezepte> rezepteList = new ArrayList<>();

        Cursor cursor = database.query(RezepteDbHelper.TABLE_REZEPTE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Rezepte rezepte;

        while(!cursor.isAfterLast()) {
            rezepte = cursorToRezepte(cursor);
            rezepteList.add(rezepte);
            Log.d(LOG_TAG, "ID: " + rezepte.getId() + ", Inhalt: " + rezepte.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return rezepteList;
    }
}
