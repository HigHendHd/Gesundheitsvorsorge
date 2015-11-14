package gvapp.diplomprojekt.at.gv_appandroid;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.Drawer;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ApplyColor {

    public static void ApplyColorNews(Drawer result, Context ctx, int title) {
        ((AppCompatActivity) ctx).getSupportActionBar().setTitle(ctx.getResources().getString(title));
        ((AppCompatActivity) ctx).getSupportActionBar().setBackgroundDrawable
                (new ColorDrawable(ContextCompat.getColor(ctx, R.color.Neuigkeiten500)));
        result.setStatusBarColor(ContextCompat.getColor(ctx, R.color.Neuigkeiten700));
    }
}
