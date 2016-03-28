package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.MainActivity;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class TrinkNotification {

    private int startTime = 8; //Stunde
    private int endTime = 20; //Stunde
    private int glasgroesse = 250; //Milliliter
    private double trinkmenge = 2.5; //Liter
    private Context ctx;

    public TrinkNotification(Context ctx) {
        this.ctx = ctx;
    }

    public int calcTimeFrame() {
        //int diff = endTime - startTime;
        //int anz = (1000 * trinkmenge) / glasgroesse;
        //int abstand = (diff / anz) * 1000 * 60 * 60;

        readVals();
        return (int) ((endTime - startTime) / ((1000 * trinkmenge) / glasgroesse)) * 1000 * 60 * 60;
    }

    private void readVals() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "file.txt");
        StringBuilder text = new StringBuilder();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
        }

        String[] vals = text.toString().split(";");

        startTime = Integer.parseInt(vals[0]);
        endTime = Integer.parseInt(vals[1]);
        glasgroesse = Integer.parseInt(vals[2]);
        trinkmenge = Double.parseDouble(vals[3]);
    }

    public void writeVals(int startTime, int endTime, int glasgroesse, double trinkmenge) {
        try {
            ctx.deleteFile(Constants.TRINKERINNERUNG_FILENAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String string = startTime + ";" + endTime + ";" + glasgroesse + ";" + trinkmenge + ";";
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(Constants.TRINKERINNERUNG_FILENAME, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void makeNotification(Context context) {
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
