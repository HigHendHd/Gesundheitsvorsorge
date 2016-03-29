package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.DetailActivity;
import gvapp.diplomprojekt.at.gv_appandroid.PickerFragments.TimePickerFragment;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class TrinkerinnerugSettingActivity extends DetailActivity implements
        TimePickerFragment.TimeSetter {

    Button selectedView, etStartTime, etEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinkerinnerug_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TrinkerinnerungSettingSaver saver = new TrinkerinnerungSettingSaver(this);

        etStartTime = (Button) findViewById(R.id.etStartTime);
        etStartTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openTimePicker(v);
                }
            }
        });

        etEndTime = (Button) findViewById(R.id.etEndTime);
        etEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openTimePicker(v);
                }
            }
        });
    }

    public void openTimePicker(View v) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        selectedView = (Button) v;
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setTimeSetter(this);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void timeReturned(String time) {
        selectedView.setText(time);

        String[] startTime = (etStartTime.getText() + "").split(":");
        String[] endTime = (etEndTime.getText() + "").split(":");

        try {
            Log.v("Tag", "StartTime[0]: " + startTime[0]);
            Log.v("Tag", "StartTime[1]: " + startTime[1]);
            Log.v("Tag", "EndTime[0]: " + endTime[0]);
            Log.v("Tag", "EndTime[1]: " + endTime[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (Integer.parseInt(startTime[0]) >= Integer.parseInt(endTime[0]) &&
                    Integer.parseInt(startTime[1]) >= Integer.parseInt(endTime[1])) {
                selectedView.setText("");
                Snackbar.make(selectedView, getString(R.string.fehleruhrzeit),
                        Snackbar.LENGTH_SHORT).show();
            } else {
                selectedView.setText(time);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            selectedView.setText(time);
        }
    }
}
