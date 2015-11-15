package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.BMI_Rechner;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BmiFragment extends Fragment {

    TextView tvGewicht, tvGroesse, tvAlter, tvBMI;
    SeekBar sbGewicht, sbGroesse, sbAlter;

    public BmiFragment() {
        // Required empty public constructor
    }

    private double calcBMI(int gewicht, int groesse) {
        double groesseInMeter = ((double) groesse) / 100;
        double bmi = gewicht / (groesseInMeter * groesseInMeter);

        bmi = Math.floor(bmi * 100) / 100;

        return bmi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmi, container, false);

        tvAlter = (TextView) v.findViewById(R.id.tvAlter);
        tvBMI = (TextView) v.findViewById(R.id.tvBmi);
        tvGewicht = (TextView) v.findViewById(R.id.tvGewicht);
        tvGroesse = (TextView) v.findViewById(R.id.tvGroesse);

        sbAlter = (SeekBar) v.findViewById(R.id.sbAlter);
        sbGewicht = (SeekBar) v.findViewById(R.id.sbGewicht);
        sbGroesse = (SeekBar) v.findViewById(R.id.sbGroesse);

        SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAlter.setText(sbAlter.getProgress() + "");
                tvGewicht.setText((sbGewicht.getProgress() + 40) + "");
                tvGroesse.setText((sbGroesse.getProgress() + 110) + "");
                tvBMI.setText(calcBMI(sbGewicht.getProgress() + 40, sbGroesse.getProgress() + 110) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sbAlter.setOnSeekBarChangeListener(sbListener);
        sbGroesse.setOnSeekBarChangeListener(sbListener);
        sbGewicht.setOnSeekBarChangeListener(sbListener);

        return v;
    }


}
