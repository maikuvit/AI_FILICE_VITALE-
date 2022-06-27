package com.example.ai_filicevitale.Model;

import java.util.Collections;
import java.util.LinkedList;

public class Deck_generator {

    private static LinkedList<Tessera> generaDeck(){
        LinkedList<Tessera> out = new LinkedList<>();

        for(int x = 0; x < 4; x++) {

            //genero quelle da nove ...
            for (int i = 1; i < 10; i++) {
                out.add(new Tessera(i, 0,0,0,0));
                out.add(new Tessera(i, 1,0,0,0));
                out.add(new Tessera(i, 2,0,0,0));
            }

            //genero i venti ...
            for (int i = 1; i < 5; i++) {
                out.add(new Tessera(i, 3,0,0,0));
            }

            //genero i draghi ...
            for (int i = 1; i < 4; i++) {
                out.add(new Tessera(i, 4,0,0,0));
            }

        }

        //aggiungo onori supremi (fiori e stagioni) ...

        for(int i = 1; i < 5; i++){
            out.add(new Tessera(i, 5,0,0,0));
            out.add(new Tessera(i, 6,0,0,0));

        }

        return out;
    }

    public static LinkedList<Tessera> mazzoDiGioco(){
        LinkedList<Tessera> out = generaDeck();
        Collections.shuffle(out);
        return out;
    }

}
