package com.example.ai_filicevitale.Model;


import com.example.ai_filicevitale.Controller.HelloController;
import com.example.ai_filicevitale.Controller.TesseraController;
import com.example.ai_filicevitale.SETTINGS;

public class MossaManager {
    //singleton che mi genera la mossa ...
    private static MossaManager instance;

    private TesseraController tesseraTemp;
    private static HelloController mainApp;

    private MossaManager(){}

    public static MossaManager getInstance() {
        if(instance == null) instance =new MossaManager();
        return instance;
    }

    public static void setup(HelloController main){
        mainApp = main;
    }
    public void inserisciTessera(TesseraController t){
        if(tesseraTemp == null) tesseraTemp = t;
        else{
            if(checkMossaValida(tesseraTemp.getTsr(), t.getTsr())) {
                //aggiorna il tabellone togliendo le tessere ...
                System.out.println("MOSSA FATTA");
                System.out.println(tesseraTemp);
                System.out.println(t);

                //rimuovo la tessera dalla grafica ...
                mainApp.removeTessera(tesseraTemp);
                mainApp.removeTessera(t);

                //rimuovo la tessera dalla logica ...
                Struttura_tessere.getInstance().removeTessera(tesseraTemp);
                Struttura_tessere.getInstance().removeTessera(t);

                t.deseleziona();
                tesseraTemp.deseleziona();
                tesseraTemp = null;
            }
            else{
                tesseraTemp.deseleziona();
                t.deseleziona();
                tesseraTemp = null;
            }

        }
    }

    private boolean checkMossaValida(Tessera a, Tessera b) {
        if(a.getX() == b.getX() && a.getY() == b.getY() && a.getZ() == b.getZ())
            return false;
        //semi diversi,mossa mai valida ...
        if(a.getSemeEnum() != b.getSemeEnum()) return false;
        // sono quelle
        if(a.getSemeEnum() == SETTINGS.SEMI.CER ||
                a.getSemeEnum() == SETTINGS.SEMI.BAM ||
                a.getSemeEnum() == SETTINGS.SEMI.CAR)
            return a.getVal() == b.getVal();

        return true;
    }

}

