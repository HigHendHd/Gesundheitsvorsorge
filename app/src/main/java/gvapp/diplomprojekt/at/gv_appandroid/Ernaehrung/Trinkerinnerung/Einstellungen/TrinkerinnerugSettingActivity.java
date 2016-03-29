package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.DetailActivity;
import gvapp.diplomprojekt.at.gv_appandroid.PickerFragments.TimePickerFragment;
import gvapp.diplomprojekt.at.gv_appandroid.R;

public class TrinkerinnerugSettingActivity extends DetailActivity implements
        TimePickerFragment.TimeSetter {

    Button selectedView, etStartTime, etEndTime;
    Switch swActive;
    EditText etTrinkmenge, etGlasgroesse;
    TrinkerinnerungSettingSaver saver;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinkerinnerug_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saver = new TrinkerinnerungSettingSaver(this);

        swActive = (Switch) findViewById(R.id.swTrinkerinnerungOnOff);
        etTrinkmenge = (EditText) findViewById(R.id.etTrinkmenge);
        etGlasgroesse = (EditText) findViewById(R.id.etGlasgroesse);

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

        swActive.setChecked(saver.isAktiv());
        etTrinkmenge.setText(saver.getTrinkmenge() + "");
        etGlasgroesse.setText(saver.getGlasgroesse() + "");
        etStartTime.setText(saver.getStart());
        etEndTime.setText(saver.getEnd());

        swActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saver.setAktiv(isChecked);
            }
        });

        etTrinkmenge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    saver.setTrinkmenge(Double.parseDouble(s + ""));
                } catch (Exception ex) {

                }
            }
        });

        etGlasgroesse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    saver.setGlasgroesse(Integer.parseInt(s + ""));
                } catch (Exception ex) {

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

        boolean flag = false;
        String[] startTime = (etStartTime.getText() + "").split(":");
        String[] endTime = (etEndTime.getText() + "").split(":");

        try {
            Log.v("Tag", "StartTime[0]: " + startTime[0]);
            Log.v("Tag", "StartTime[1]: " + startTime[1]);
            Log.v("Tag", "EndTime[0]: " + endTime[0]);
            Log.v("Tag", "EndTime[1]: " + endTime[1]);
            flag = true;
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
                if (flag) {
                    saver.setStart(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
                    saver.setEnd(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            selectedView.setText(time);
        }
    }
}
