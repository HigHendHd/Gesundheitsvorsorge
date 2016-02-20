package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Datenbank;

import android.database.sqlite.SQLiteDatabase;

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
            RestaurantsDbHelper.COLUMN_ADRESSE
    };

}
