package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen.TrinkerinnerungSettingSaver;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class AlarmManagerBroadcastReciever extends BroadcastReceiver {

    TrinkerinnerungSettingSaver saver;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR_TAG");
        //Den Lock bekommen
        wl.acquire();

        //Hier wird das Processing erledigt
        saver = new TrinkerinnerungSettingSaver(context);
        TrinkNotification trinkNotification = new TrinkNotification(saver);
        trinkNotification.makeNotification(context);

        //Wakelock wieder loslassen
        wl.release();
    }

    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReciever.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                saver.calcTimeFrame(), pi);
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReciever.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
