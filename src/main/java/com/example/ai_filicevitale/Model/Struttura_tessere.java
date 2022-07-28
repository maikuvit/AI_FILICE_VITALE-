package com.example.ai_filicevitale.Model;

import com.example.ai_filicevitale.Controller.TesseraController;
import com.example.ai_filicevitale.solver;

import java.util.ArrayList;
import java.util.LinkedList;

public class Struttura_tessere {

    private final ArrayList<Layer> Layers = new ArrayList();

    static Struttura_tessere instance;

    static public Struttura_tessere getInstance(){
        if(instance == null) instance = new Struttura_tessere();
        return instance;
    }

    private Struttura_tessere(){

        int layer = 0;
        //genero il mazzo ...
        LinkedList<Tessera> mazzo = Deck_generator.mazzoDiGioco();

        //divido il mazzo in cinque queue dalle quali posso poi piazzarle nei Layer ...
        LinkedList<Tessera> mazzo_per_layer = new LinkedList<>();

        for(int i = 0; i < 8*9; i++)
            mazzo_per_layer.add(mazzo.pop());

        //Layer 1
        Layers.add(new Layer(8,9,0,0,false,mazzo_per_layer,layer++));

        mazzo_per_layer.clear();
        for(int i = 0; i < 6*7; i++)
            mazzo_per_layer.add(mazzo.pop());

        //Layer 2
        Layers.add(new Layer(6,7,1,1,false,mazzo_per_layer,layer++));

        mazzo_per_layer.clear();
        for(int i = 0; i < 4*5; i++)
            mazzo_per_layer.add(mazzo.pop());

        //Layer 3
        Layers.add(new Layer(4,5,2,2,false,mazzo_per_layer,layer++));

        mazzo_per_layer.clear();
        for(int i = 0; i < 4*3 - 4; i++)
            mazzo_per_layer.add(mazzo.pop());

        //Layer 4
        Layers.add(new Layer(4,3,2,3,true,mazzo_per_layer,layer++));

        mazzo_per_layer.clear();
        for(int i = 0; i < 2; i++)
            mazzo_per_layer.add(mazzo.pop());

        Layers.add(new Layer(2,1,3,4,false,mazzo_per_layer,layer++));

    }

    public Layer getLayer(int index){
        return Layers.get(index);
    }

    public void removeTessera(TesseraController t){
        Layers.get(t.getTsr().z).remove(t.getTsr().x, t.getTsr().y);
    }

    public void checkSbloccabili() throws Exception {
        // devo controllare se ho una tessera sopra ...
        boolean[][] tempMat = new boolean[8][9];

        //controlla partendo dal primo layer (si scorrono al contrario) ...
        for(int k = Layers.size(); k > 0; k-- ) {
            Layer tempLay = Layers.get(k - 1);
            int tempOffsetX = tempLay.getOffsX();
            int tempOffsetY = tempLay.getOffsY();

            for(int x = 0; x < tempLay.getDimX(); x++){
                for(int y = 0; y < tempLay.getDimY(); y++) {
                    //se ho una tessera in un punto ...
                    if(!tempMat[x + tempOffsetX][y + tempOffsetY] && tempLay.getTessera(x,y) != null) {
                        //segno di avere una tessera ...
                        tempMat[x + tempOffsetX][y + tempOffsetY] = true;


                        Tessera temptessera = tempLay.getTessera(x,y);
                        //se ho una tessera sbloccabile ...
                        if(temptessera != null && temptessera.getLibera()){
                            //qui ho una tessera che non è nulla ed è libera ...

                            //controllo se a destra ho qualcosa ...
                            if(y + 1 < tempLay.getDimY() && tempLay.getTessera(x,y + 1) != null && !tempMat[x][y + 1]){
                                //se trovo qualcosa, quella tessera a destra è sbloccabile ...
                                if(!tempLay.getTessera(x, y + 1).getLibera())
                                    solver.addFactSbloccabile(temptessera,tempLay.getTessera(x,y + 1));
                            }

                            //controllo se a sinistra ho qualcosa ...
                            if(y - 1 > 0 && tempLay.getTessera(x, y - 1) != null && !tempMat[x][y - 1]){
                                //se a sinistra trovo qualcosa, quella è sbloccabile ...
                                if(!tempLay.getTessera(x, y - 1).getLibera())
                                    solver.addFactSbloccabile(temptessera,tempLay.getTessera(x,y - 1));
                            }
                        }
                    }
                }
            }
        }
    }



    public void checkLibere() throws Exception {
        //TODO CONTROLLA GLI OFFSET!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        boolean[][] tempMat = new boolean[8][9];
        //controlla partendo dal primo layer (si scorrono al contrario) ...
        for(int k = Layers.size(); k > 0; k-- ){
            Layer tempLay = Layers.get(k - 1);
            int tempOffsetX = tempLay.getOffsX();
            int tempOffsetY = tempLay.getOffsY();

            for(int x = 0; x < tempLay.getDimX(); x++){
                for(int y = 0; y < tempLay.getDimY(); y++){
                    if(!tempMat[x + tempOffsetX][y + tempOffsetY] && tempLay.getTessera(x,y) != null) {
                        //segno di avere una tessera ...
                        tempMat[x + tempOffsetX][y + tempOffsetY] = true;

                        // le celle a destra e sinistra sono sempre libere  ...
                        if(y == 0 || y == tempLay.getDimY() - 1) {
                            tempLay.getTessera(x,y).setLibera();
                            solver.addFactTessera(tempLay.getTessera(x,y));
                        }
                        else{
                            if(tempLay.getTessera(x,y - 1) == null || tempLay.getTessera(x,y + 1) == null){
                                tempLay.getTessera(x,y).setLibera();
                                solver.addFactTessera(tempLay.getTessera(x,y));
                            }
                        }
                    }
                }
            }
        }
    }
}
