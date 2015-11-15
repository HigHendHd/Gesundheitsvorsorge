package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.KFA_Rechner;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KfaFragment extends Fragment {

    TextView tvFalte1, tvFalte2, tvFalte3, tvAlter, tvKfa, tvFalte1Id, tvFalte2Id, tvFalte3Id;
    SeekBar sbFalte1, sbFalte2, sbFalte3, sbAlter;
    RadioButton rbMaennlich, rbWeiblich;
    boolean weiblich;

    public KfaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kfa, container, false);

        weiblich = true;

        tvFalte1 = (TextView) v.findViewById(R.id.tvFalte1);
        tvFalte2 = (TextView) v.findViewById(R.id.tvFalte2);
        tvFalte3 = (TextView) v.findViewById(R.id.tvFalte3);
        tvAlter = (TextView) v.findViewById(R.id.tvAlter);
        tvKfa = (TextView) v.findViewById(R.id.tvKfa);
        tvFalte1Id = (TextView) v.findViewById(R.id.tvFalte1Identifier);
        tvFalte2Id = (TextView) v.findViewById(R.id.tvFalte2Identifier);
        tvFalte3Id = (TextView) v.findViewById(R.id.tvFalte3Identifier);

        sbFalte1 = (SeekBar) v.findViewById(R.id.sbFalte1);
        sbFalte2 = (SeekBar) v.findViewById(R.id.sbFalte2);
        sbFalte3 = (SeekBar) v.findViewById(R.id.sbFalte3);
        sbAlter = (SeekBar) v.findViewById(R.id.sbAlter);

        rbMaennlich = (RadioButton) v.findViewById(R.id.rbMaennlich);
        rbWeiblich = (RadioButton) v.findViewById(R.id.rbWeiblich);

        SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvFalte1.setText(sbFalte1.getProgress() + "");
                tvFalte2.setText(sbFalte2.getProgress() + "");
                tvFalte3.setText(sbFalte3.getProgress() + "");
                tvAlter.setText(sbAlter.getProgress() + "");
                tvKfa.setText(berechneKfa(sbFalte1.getProgress(), sbFalte2.getProgress(), sbFalte3
                        .getProgress(), sbAlter.getProgress()) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sbAlter.setOnSeekBarChangeListener(sbListener);
        sbFalte3.setOnSeekBarChangeListener(sbListener);
        sbFalte2.setOnSeekBarChangeListener(sbListener);
        sbFalte1.setOnSeekBarChangeListener(sbListener);

        View.OnClickListener rbListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == rbWeiblich) {
                    weiblich = true;
                    tvFalte1Id.setText(R.string.trizezps);
                    tvFalte2Id.setText(R.string.bauchfalte);
                    tvFalte3Id.setText(R.string.hueftfalte);
                } else {
                    weiblich = false;
                    tvFalte1Id.setText(R.string.brustfalte);
                    tvFalte2Id.setText(R.string.bauchfalte);
                    tvFalte3Id.setText(R.string.oberschenkelfalte);
                }
            }
        };

        rbWeiblich.setOnClickListener(rbListener);
        rbMaennlich.setOnClickListener(rbListener);

        return v;
    }

    private double berechneKfa(int falte1, int falte2, int falte3, int alter) {
        double x = 0;

        if (weiblich) {
            x = 1.0994921 - 0.0009929 * (falte1 + falte2 + falte3) + 0.0000023 *
                    (falte1 + falte2 + falte3) * (falte1 + falte2 + falte3) - 0.0001392 * alter;
        } else {
            x = 1.1093800 - 0.0008267 * (falte1 + falte2 + falte3) + 0.0000016 *
                    (falte1 + falte2 + falte3) * (falte1 + falte2 + falte3) - 0.0002574 * alter;
        }

        double retVal = 495 / x - 450;

        return retVal;
    }
}
