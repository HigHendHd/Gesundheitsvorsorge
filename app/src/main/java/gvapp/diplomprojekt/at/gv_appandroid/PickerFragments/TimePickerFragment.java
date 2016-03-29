package gvapp.diplomprojekt.at.gv_appandroid.PickerFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Dennis on 29.03.2016.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private TimeSetter timeSetter;

    public void setTimeSetter(TimeSetter timeSetter) {
        this.timeSetter = timeSetter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (timeSetter != null) {
            timeSetter.timeReturned(String.format("%02d", hourOfDay) + ":" +
                    String.format("%02d", minute), hourOfDay, minute);
        }
    }

    public interface TimeSetter {
        void timeReturned(String time, int hours, int minutes);
    }
}
