package com.example.ai_filicevitale.Controller;

import com.example.ai_filicevitale.Model.MossaManager;
import com.example.ai_filicevitale.Model.Struttura_tessere;
import com.example.ai_filicevitale.Model.Tessera;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class TesseraController
{
    @FXML
    private ImageView TesseraImg;

    private AnchorPane pane;

    private Tessera tsr;
    private int x;
    private int y;
    private int layer;

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Tessera getTsr() {
        return tsr;
    }

    public void setTsr(Tessera tsr,int x, int y, int layer) {
        this.tsr = tsr;
        this.x = x;
        this.y = y;
        this.layer = layer;
        tsr.Observe(this);
        setSprite();
    }

    public void setSprite(){
        String path = "/tiles/" + tsr.getSemeEnum() + "_" + tsr.getVal() + ".png";
        Image img = new Image (Objects.requireNonNull(getClass().getResourceAsStream(path)));
        TesseraImg.setImage(img);
    }

    public void notify(Tessera tsr){
        this.tsr = tsr;
    }

    @FXML
    void ClickTessera(MouseEvent event) throws Exception {
        Struttura_tessere.getInstance().checkLibere();
        System.out.println("CLICCATO TESSERA " + this.x + " - " + this.y);
        if(!tsr.getLibera()) return;
        highlight();
        MossaManager.getInstance().inserisciTessera(this);
    }

    public void highlight(){
        DropShadow borderGlow = new DropShadow();

        borderGlow.setColor(Color.RED);
        borderGlow.setOffsetX(2f);
        borderGlow.setOffsetY(2f);

        TesseraImg.setEffect(borderGlow);
    }

    public void deseleziona(){
        TesseraImg.setEffect(null);
    }

    @Override
    public String toString() {
        return tsr.toString();
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public AnchorPane getPane(){
        return pane;
    }
}
