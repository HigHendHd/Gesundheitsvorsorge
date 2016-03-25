package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import gvapp.diplomprojekt.at.gv_appandroid.MainActivity;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class TrinkNotification {

    public static int startTime = 8; //Stunde
    public static int endTime = 20; //Stunde
    public static int glasgroesse = 250; //Milliliter
    public static double trinkmenge = 2.5; //Liter

    public static int calcTimeFrame() {
        //int diff = endTime - startTime;
        //int anz = (1000 * trinkmenge) / glasgroesse;
        //int abstand = (diff / anz) * 1000 * 60 * 60;

        return (int) ((endTime - startTime) / ((1000 * trinkmenge) / glasgroesse)) * 1000 * 60 * 60;
    }

    public static void makeNotification(Context context) {
        Format formatter = new SimpleDateFormat("hh");
        int currHour = Integer.parseInt(formatter.format(new Date()));
        if (currHour >= startTime && currHour <= endTime) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.cast_ic_notification_connecting)
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
        }
    }
}
