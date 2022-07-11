package com.example.ai_filicevitale.View;

import com.example.ai_filicevitale.Controller.TesseraController;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class tessereGrafiche {

    // mi serve una struttura 8x9 fatta da stacks ...
    //in questo modo ho una lista di liste di stacks ... (BRUTTISSIMA)
    List<List<Stack<TesseraController>>> tsr = new ArrayList<>();

    public tessereGrafiche(){
        for(int k = 0; k < 9; k++){
            List<Stack<TesseraController>> temp = new ArrayList<>();

            for(int p = 0; p < 8; p++){
                temp.add(new Stack<>());
            }
            tsr.add(temp);
        }
    }

    public void insertTesseraController(TesseraController t, int x, int y){
        tsr.get(y).get(x).push(t);
    }

    public void clickTesseraController(int x, int y) throws Exception {

        tsr.get(y).get(x).pop().ClickTessera(null);
    }

}
