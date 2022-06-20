package com.example.ai_filicevitale.Model;

import com.example.ai_filicevitale.SETTINGS;

import java.util.Collections;
import java.util.LinkedList;

public class Deck_generator {

    private static LinkedList<Tessera> generaDeck(){
        LinkedList<Tessera> out = new LinkedList<>();

        for(int x = 0; x < 4; x++) {

            //genero quelle da nove ...
            for (int i = 1; i < 10; i++) {
                out.add(new Tessera(i, SETTINGS.SEMI.CER,0,0,0));
                out.add(new Tessera(i, SETTINGS.SEMI.BAM,0,0,0));
                out.add(new Tessera(i, SETTINGS.SEMI.CAR,0,0,0));
            }

            //genero i venti ...
            for (int i = 1; i < 5; i++) {
                out.add(new Tessera(i, SETTINGS.SEMI.WND,0,0,0));
            }

            //genero i draghi ...
            for (int i = 1; i < 4; i++) {
                out.add(new Tessera(i, SETTINGS.SEMI.DRA,0,0,0));
            }

        }

        //aggiungo onori supremi (fiori e stagioni) ...

        for(int i = 1; i < 5; i++){
            out.add(new Tessera(i, SETTINGS.SEMI.FIO,0,0,0));
            out.add(new Tessera(i, SETTINGS.SEMI.STA,0,0,0));

        }

        return out;
    }

    public static LinkedList<Tessera> mazzoDiGioco(){
        LinkedList<Tessera> out = generaDeck();
        Collections.shuffle(out);
        return out;
    }

}
