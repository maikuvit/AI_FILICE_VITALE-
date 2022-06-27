package com.example.ai_filicevitale.Model;

import com.example.ai_filicevitale.Controller.TesseraController;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

import com.example.ai_filicevitale.SETTINGS;

@Id("tessera")
public class Tessera implements java.io.Serializable{

    @Param(0)
    int x;
    @Param(1)
    int y;
    @Param(2)
    int z;

    @Param(3)
    private int val;

	@Param(4)
    private int seme;

    TesseraController ctr;
    boolean tesseraFree = false;

    public Tessera(){}

    public Tessera(int val, int seme, int x, int y, int z) {
        this.val = val;
        this.seme = seme;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLibera(){
        tesseraFree = true;
        ctr.notify(this);
    }

    public boolean getLibera(){
        return tesseraFree;
    }

    /*
        0 CER ,
        1 BAM ,
        2 CAR ,

        3 WND ,
        4 DRA ,
        5 FIO ,
        6 STA

         */
    public void setSeme(int seme) {
        this.seme = seme;
    }

    public int getSeme() {
        return seme;
    }

    public SETTINGS.SEMI getSemeEnum(){
        if(seme == 0) return SETTINGS.SEMI.CER;
        if(seme == 1) return SETTINGS.SEMI.BAM;
        if(seme == 2) return SETTINGS.SEMI.CAR;
        if(seme == 3) return SETTINGS.SEMI.WND;
        if(seme == 4) return SETTINGS.SEMI.DRA;
        if(seme == 5) return SETTINGS.SEMI.FIO;
        return SETTINGS.SEMI.STA;
    }

    @Override
    public String toString() {
        return val + "" + seme;
    }

    public void Observe(TesseraController ctr){
        this.ctr = ctr;
    }
}
