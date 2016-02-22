package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dennis on 21.02.2016.
 */
public class RezepteDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = RezepteDbHelper.class.getSimpleName();

    public static final String DB_NAME = "rezepte.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_REZEPTE = "rezepte_list";
    public static final String TABLE_SCHRITTE = "schritte_list";
    public static final String TABLE_ZUTATEN = "_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BILD = "bild";
    public static final String COLUMN_KOCHDAUER = "kochdauer";
    public static final String COLUMN_SCHWIERIGKEITSGRAD = "schwierigkeitsgrad";
    public static final String COLUMN_TIPP = "tipp";
    public static final String COLUMN_R_ID = "r_id";
    public static final String COLUMN_ANZAHL = "anzahl";
    public static final String COLUMN_EINHEIT = "einheit";
    public static final String COLUMN_NUMMER = "nummer";
    public static final String COLUMN_TEXT = "text";

    public static final String SQL_CREATEREZEPTE =
            "CREATE TABLE " + TABLE_REZEPTE +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_BILD + " TEXT NOT NULL, " +
                    COLUMN_KOCHDAUER + " TEXT NOT NULL, " +
                    COLUMN_SCHWIERIGKEITSGRAD + " TEXT NOT NULL, " +
                    COLUMN_TIPP + " INTEGER NOT NULL);";

    public static final String SQL_CREATESCHRITTE =
            "CREATE TABLE " + TABLE_ZUTATEN +
                    "(" + COLUMN_R_ID + "INTEGER PRIMARY KEY, " +
                    COLUMN_NUMMER + " INTEGER PRIMARY KEY, " +
                    COLUMN_TEXT + " TEXT NOT NULL, " +
                    "FOREIGN KEY(" + COLUMN_R_ID + ") REFERENCES " + TABLE_REZEPTE + "(" + COLUMN_ID + ");";

    public static final String SQL_CREATEZUTATEN =
            "CREATE TABLE " + TABLE_ZUTATEN +
                    "(" + COLUMN_R_ID + "INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT PRIMARY KEY, " +
                    COLUMN_ANZAHL + " INTEGER NOT NULL, " +
                    COLUMN_EINHEIT + " TEXT NOT NULL, " +
                    "FOREIGN KEY(" + COLUMN_R_ID + ") REFERENCES " + TABLE_REZEPTE + "(" + COLUMN_ID + ");";


    public RezepteDbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATEREZEPTE + " angelegt.");
            db.execSQL(SQL_CREATEREZEPTE);
            db.execSQL(SQL_CREATESCHRITTE);
            db.execSQL(SQL_CREATEZUTATEN);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
