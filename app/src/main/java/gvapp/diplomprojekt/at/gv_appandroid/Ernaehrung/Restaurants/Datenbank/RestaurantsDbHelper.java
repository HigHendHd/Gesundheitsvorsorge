package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dennis on 20.02.2016.
 */
public class RestaurantsDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = RestaurantsDbHelper.class.getSimpleName();

    public static final String DB_NAME = "restaurants.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_RESTAURANT = "restaurants_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_KATEGORIE = "kategorie";
    public static final String COLUMN_BILD = "bild";
    public static final String COLUMN_INFO = "info";
    public static final String COLUMN_TELEFONNR = "telefonnr";
    public static final String COLUMN_OEFFNUNGSZEITEN = "oeffnungszeiten";
    public static final String COLUMN_ADRESSE = "adresse";
    public static final String COLUMN_WEITEREINFOS = "weitereinfos";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_RESTAURANT +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_KATEGORIE + " TEXT NOT NULL, " +
                    COLUMN_BILD + " TEXT NOT NULL, " +
                    COLUMN_INFO + " TEXT NOT NULL, " +
                    COLUMN_TELEFONNR + " INTEGER NOT NULL, " +
                    COLUMN_OEFFNUNGSZEITEN + " TEXT NOT NULL, " +
                    COLUMN_ADRESSE + " TEXT NOT NULL, " +
                    COLUMN_WEITEREINFOS + " TEXT NOT NULL);";


    public RestaurantsDbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
