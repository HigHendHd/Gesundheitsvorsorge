package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;

/**
 * Created by Dennis on 29.03.2016.
 */
public class TrinkerinnerungSettingSaver {

    private boolean aktiv;
    private double trinkmenge, getrunken;
    private int glasgroesse;
    private Time start, end;
    private Context ctx;

    //Dateistruktur: aktiv[1(0)];trinkmenge[3];getrunken[0];glasgroesse[250];start[08:00];end[20:00]

    public TrinkerinnerungSettingSaver(Context ctx) {
        this.ctx = ctx;
        checkIfAlreadySet();
    }

    private void checkIfAlreadySet() {
        if (!readVals()) initialSetup();
    }

    private void initialSetup() {

    }

    private boolean readVals() {
        File sdcard = ctx.getFilesDir();
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
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        //Dateistruktur: 0:aktiv[1(0)];1:trinkmenge[3];2:getrunken[0];3:glasgroesse[250];4:start[08:00];5:end[20:00]
        String[] vals = text.toString().split(";");

        if (Integer.parseInt(vals[0]) == 0)
            aktiv = false;
        else if (Integer.parseInt(vals[0]) == 1)
            aktiv = true;

        try {
            trinkmenge = Double.parseDouble(vals[1]);
            getrunken = Double.parseDouble(vals[2]);
            glasgroesse = Integer.parseInt(vals[3]);
            start = Time.valueOf(vals[4]);
            end = Time.valueOf(vals[5]);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
