package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Trinkerinnerung;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_trinkerinnerung, container, false);
    }

}
