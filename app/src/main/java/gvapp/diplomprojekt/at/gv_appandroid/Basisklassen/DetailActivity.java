package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by deathkid535 on 3/12/16.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
