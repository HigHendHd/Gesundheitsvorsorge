package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;


import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen.TrinkerinnerungSettingActivity;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung.Einstellungen.TrinkerinnerungSettingSaver;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrinkerinnerungFragment extends Fragment {

    public TrinkerinnerungFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_trinkerinnerung, container, false);

        TrinkerinnerungSettingSaver saver = new TrinkerinnerungSettingSaver(getActivity());

        ((TextView) view.findViewById(R.id.tvProzent)).setText((saver.getGetrunken()
                / saver.getTrinkmenge()) * 100 + "%");

        ((TextView) view.findViewById(R.id.tvFortschritt)).setText(saver.getGetrunken() + " von " +
                saver.getTrinkmenge() + " getrunken!");

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, (int)
                ((saver.getGetrunken() / saver.getTrinkmenge()) * 100)); // see this max value coming back here, we animate towards that value
        animation.setDuration(2000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Log", item.getItemId() + " - Is ID");
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getActivity(), TrinkerinnerungSettingActivity.class);
                getActivity().startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
