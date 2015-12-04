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

    private NotrufNummer notrufNummer;
    private NotrufAdapter notrufAdapter;

    public NotrufListe() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        eintraege.add(new NotrufNummer().addItem(R.string.feuerwehr, "122", getActivity()));

        retNummer.addItem(ctx.getResources().getString(R.string.polizei), "133");
        retNummer.addItem(ctx.getResources().getString(R.string.rettung), "144");
        retNummer.addItem(ctx.getResources().getString(R.string.euronotruf), "112");
        retNummer.addItem(ctx.getResources().getString(R.string.bergrettung), "140");
        retNummer.addItem(ctx.getResources().getString(R.string.notruffuergehoerlose), "0800 133 133");
        retNummer.addItem(ctx.getResources().getString(R.string.aerztefunkdienst), "141");
        retNummer.addItem(ctx.getResources().getString(R.string.orfkinderservice), "147");
        retNummer.addItem(ctx.getResources().getString(R.string.aerzteflugambulanz), "40 144");
        retNummer.addItem(ctx.getResources().getString(R.string.frauenhelpline), "0800 222 555");
        retNummer.addItem(ctx.getResources().getString(R.string.frauennotruf), "01/71 71 9");
        retNummer.addItem(ctx.getResources().getString(R.string.telefonseelsorge), "142");
        retNummer.addItem(ctx.getResources().getString(R.string.gasgebrechen), "128");
        retNummer.addItem(ctx.getResources().getString(R.string.arboe), "122");
        retNummer.addItem(ctx.getResources().getString(R.string.oeamtc), "123");
        retNummer.addItem(ctx.getResources().getString(R.string.kinderundjugendanwaltdesbundes), "0800-240264");
        retNummer.addItem(ctx.getResources().getString(R.string.vergiftungsinformation), "01/406 43 43");
        retNummer.addItem(ctx.getResources().getString(R.string.servicenummerderpolizei), "059 133");

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
