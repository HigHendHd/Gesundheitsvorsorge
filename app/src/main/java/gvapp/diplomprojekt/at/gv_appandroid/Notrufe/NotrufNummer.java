package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import android.content.Context;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufNummer extends ListenEintrag {

    public NotrufNummer() {

    }

    public static NotrufNummer createNotrufNummern(Context ctx) {
        NotrufNummer retNummer = new NotrufNummer();

        retNummer.addItem(ctx.getResources().getString(R.string.feuerwehr), "122");
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

        return retNummer;
    }
}
