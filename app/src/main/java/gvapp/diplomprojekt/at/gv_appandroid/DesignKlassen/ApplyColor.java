package gvapp.diplomprojekt.at.gv_appandroid.DesignKlassen;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.Drawer;

import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ApplyColor {

    public static void ApplyColorNews(Drawer result, Context ctx, int title) {
        ActionBar tb = ((AppCompatActivity) ctx).getSupportActionBar();
        tb.setTitle(ctx.getResources().getString(title));
        tb.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(ctx, R.color.Neuigkeiten500)));
        result.setStatusBarColor(ContextCompat.getColor(ctx, R.color.Neuigkeiten700));
        ctx.setTheme(R.style.AppThemeNeuigkeiten);
    }

    public static void ApplyColorGesundheit(Drawer result, Context ctx, int title) {
        ActionBar tb = ((AppCompatActivity) ctx).getSupportActionBar();
        tb.setTitle(ctx.getResources().getString(title));
        tb.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(ctx, R.color.Gesundheit500)));
        result.setStatusBarColor(ContextCompat.getColor(ctx, R.color.Gesundheit700));
        ctx.setTheme(R.style.AppThemeGesundheit);
    }
}
