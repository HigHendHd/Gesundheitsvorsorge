package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.Liste;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufListe extends Liste {

    public NotrufListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        eintraege.add(new NotrufNummer().addItem(R.string.feuerwehr, "122", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.polizei, "133", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.rettung, "144", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.euronotruf, "112", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.bergrettung, "140", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.notruffuergehoerlose, "0800 133 133", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.aerztefunkdienst, "141", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.orfkinderservice, "147", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.aerzteflugambulanz, "40 144", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.frauenhelpline, "0800 222 555", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.frauennotruf, "01/71 71 9", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.telefonseelsorge, "142", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.gasgebrechen, "128", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.arboe, "123", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.oeamtc, "120", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.kinderundjugendanwaltdesbundes, "0800-240264", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.vergiftungsinformation, "01/406 43 43", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.servicenummerderpolizei, "059 133", getActivity()));
        eintraege.add(new NotrufNummer().addItem(R.string.hotlinefuervermisstekinder, "116000", getActivity()));

        super.xmlDownloaded(null);

        return v;
    }

    @Override
    public void itemClicked(View v, int position) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + eintraege.get(position).getLisUntertitel()));
        startActivity(intent);
    }
}
