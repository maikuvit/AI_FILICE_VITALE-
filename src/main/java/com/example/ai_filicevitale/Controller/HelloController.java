package com.example.ai_filicevitale.Controller;

import com.example.ai_filicevitale.HelloApplication;
import com.example.ai_filicevitale.Model.Layer;
import com.example.ai_filicevitale.Model.Struttura_tessere;
import com.example.ai_filicevitale.Model.Tessera;
import com.example.ai_filicevitale.View.tessereGrafiche;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private GridPane tesserePane;

    private tessereGrafiche tGrafiche = new tessereGrafiche();

    public GridPane getTesseraContainer(){
        return  tesserePane;
    }
    public void setStarting() throws IOException {

        FXMLLoader loader;

        //scorriamo tutti i layer a partire da quello più in basso ...
        for(int lay = 0; lay < 5; lay++){
            //prendiamo il layer ...
            Layer temp = Struttura_tessere.getInstance().getLayer(lay);

            for(int xL = 0; xL < temp.getDimX(); xL++){
                for(int yL = 0; yL < temp.getDimY(); yL++){

                    Tessera tempTsr = temp.getTessera(xL,yL);
                    //se non mi trovo in una tessera fantasma o svuotata ...
                    if(tempTsr != null) {
                        //carico la tessera e aggiungo degli offset per dare effetto 3d ...
                        loader = new FXMLLoader(HelloApplication.class.getResource("tessera.fxml"));
                        AnchorPane pane = loader.load();
                        pane.setPadding(new Insets(0,0, lay*10,lay*6));

                        //prendo il controller e setto la tessera nella view abbinata ...
                        TesseraController controller = loader.getController();

                        controller.setPane(pane);

                        controller.setTsr(tempTsr, xL, yL, lay);

                        //aggiungo la tessera alla grid sul campo da gioco ...
                        tesserePane.add(pane, yL + temp.getOffsY(), xL + temp.getOffsX());
                        tGrafiche.insertTesseraController(controller,xL + temp.getOffsX(),yL + temp.getOffsY() );
                    }
                }
            }
        }
    }



    public void execMossaGrafica(int x, int y) throws Exception {
        tGrafiche.clickTesseraController(x,y);
    }

    public void removeTessera(TesseraController t) {
        tesserePane.getChildren().remove(t.getPane());
    }
}