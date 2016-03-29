package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.AlarmManagerBroadcastReciever;

/**
 * Created by Dennis on 29.03.2016.
 */
public class TrinkerinnerungSettingSaver {

    private boolean aktiv;
    private double trinkmenge, getrunken;
    private int glasgroesse;
    private Calendar start = new GregorianCalendar(), end = new GregorianCalendar();
    private Context ctx;

    //Dateistruktur: 0:aktiv[1(0)];1:trinkmenge[3];2:getrunken[0];3:glasgroesse[250];4:start[08:00];5:end[20:00]

    public TrinkerinnerungSettingSaver(Context ctx) {
        this.ctx = ctx;
        checkIfAlreadySet();
    }

    private void checkIfAlreadySet() {
        if (!readVals()) initialSetup();
    }

    private void initialSetup() {
        aktiv = true;
        trinkmenge = 3;
        getrunken = 0;
        glasgroesse = 250;
        start.set(Calendar.HOUR_OF_DAY, 8);
        start.set(Calendar.MINUTE, 0);
        end.set(Calendar.HOUR_OF_DAY, 20);
        end.set(Calendar.MINUTE, 0);
        writeVals();
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
        writeVals();
        if (aktiv) new AlarmManagerBroadcastReciever().setAlarm(ctx.getApplicationContext());
        else new AlarmManagerBroadcastReciever().cancelAlarm(ctx.getApplicationContext());
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
        writeVals();
    }

    public double getGetrunken() {
        return getrunken;
    }

    public void setGetrunken(double getrunken) {
        this.getrunken = getrunken;
        writeVals();
    }

    public int getGlasgroesse() {
        return glasgroesse;
    }

    public void setGlasgroesse(int glasgroesse) {
        this.glasgroesse = glasgroesse;
        writeVals();
    }

    public double getTrinkmenge() {
        return trinkmenge;
    }

    public void setTrinkmenge(double trinkmenge) {
        this.trinkmenge = trinkmenge;
        writeVals();

    }

    private void writeVals() {
        //Dateistruktur: 0:aktiv[1(0)];1:trinkmenge[3];2:getrunken[0];3:glasgroesse[250];4:start[08:00];5:end[20:00]

        try {
            ctx.deleteFile(Constants.TRINKERINNERUNG_FILENAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String buffer;
        if (!aktiv) buffer = "0";
        else buffer = "1";

        String string = buffer + Constants.FILE_SEPERATOR + trinkmenge + Constants.FILE_SEPERATOR +
                getrunken + Constants.FILE_SEPERATOR + glasgroesse + Constants.FILE_SEPERATOR +
                start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE) +
                Constants.FILE_SEPERATOR + end.get(Calendar.HOUR_OF_DAY) + ":" +
                end.get(Calendar.MINUTE);

        Log.v("tag", string);

        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(Constants.TRINKERINNERUNG_FILENAME,
                    Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getEnd() {
        return String.format("%02d", end.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", end.get(Calendar.MINUTE));
    }

    public void setEnd(int hours, int minutes) {
        end.set(Calendar.HOUR_OF_DAY, hours);
        end.set(Calendar.MINUTE, minutes);
        writeVals();
    }

    public String getStart() {
        return String.format("%02d", start.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", start.get(Calendar.MINUTE));
    }

    public void setStart(int hours, int minutes) {
        start.set(Calendar.HOUR_OF_DAY, hours);
        start.set(Calendar.MINUTE, minutes);
        writeVals();
    }

    public int calcTimeFrame() {
        int retVal = (int) ((end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY) +
                Math.abs(end.get(Calendar.MINUTE) - start.get(Calendar.MONTH))) /
                ((1000 * trinkmenge) / glasgroesse)) * 1000 * 60 * 60;
        Log.v("TimeFrame:", retVal + "");
        return retVal;
    }

    private boolean readVals() {
        File sdcard = ctx.getFilesDir();
        File file = new File(sdcard, Constants.TRINKERINNERUNG_FILENAME);
        StringBuilder text = new StringBuilder();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        //Dateistruktur: 0:aktiv[1(0)];1:trinkmenge[3];2:getrunken[0];3:glasgroesse[250];4:start[08:00];5:end[20:00]
        Log.v("line", text.toString());
        String[] vals = text.toString().split(Constants.FILE_SEPERATOR);

        if (Integer.parseInt(vals[0]) == 0)
            aktiv = false;
        else if (Integer.parseInt(vals[0]) == 1)
            aktiv = true;

        try {
            trinkmenge = Double.parseDouble(vals[1]);
            getrunken = Double.parseDouble(vals[2]);
            glasgroesse = Integer.parseInt(vals[3]);
            start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vals[4].split(":")[0]));
            start.set(Calendar.MINUTE, Integer.parseInt(vals[4].split(":")[1]));
            end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vals[5].split(":")[0]));
            end.set(Calendar.MINUTE, Integer.parseInt(vals[5].split(":")[1]));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
