package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufListe extends Liste {

    private NotrufNummer notrufNummer;
    private NotrufAdapter notrufAdapter;

    public NotrufListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        notrufNummer = NotrufNummer.createNotrufNummern(getActivity());
        notrufAdapter = new NotrufAdapter(notrufNummer);
        super.setmAdapter(notrufAdapter);
        notrufAdapter.setClickListener(this);

        return v;
    }

    @Override
    public void itemClicked(View v, int position) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + notrufNummer.getUntertitel(position)));
        startActivity(intent);
    }
}
