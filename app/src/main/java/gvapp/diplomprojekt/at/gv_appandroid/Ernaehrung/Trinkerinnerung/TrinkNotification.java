package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen.TrinkerinnerungSettingSaver;
import gvapp.diplomprojekt.at.gv_appandroid.MainActivity;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class TrinkNotification {

    TrinkerinnerungSettingSaver saver;

    public TrinkNotification(TrinkerinnerungSettingSaver saver) {
        this.saver = saver;
    }

    public void makeNotification(Context context) {
        int currHour = new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
        if (currHour >= Integer.parseInt(saver.getStart().split(":")[0])
                && currHour <= Integer.parseInt(saver.getEnd().split(":")[0])) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_local_drink_black_24dp)
                            .setContentTitle("Trink wos")
                            .setContentText("Oida")
                            .setAutoCancel(true);

            Intent resultIntent = new Intent(context, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

            stackBuilder.addParentStack(MainActivity.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(5, mBuilder.build());

            saver.setGetrunken(saver.getGetrunken() + ((double) saver.getGlasgroesse()) / 1000);
            Log.d("Log", saver.getGetrunken() + "");
        } else {
            saver.setGetrunken(0);
        }
    }
}
