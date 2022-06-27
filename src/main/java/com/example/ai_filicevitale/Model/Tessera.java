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
    private SETTINGS.SEMI seme;

    TesseraController ctr;
    boolean tesseraFree = false;

    public Tessera(){}

    public Tessera(int val, SETTINGS.SEMI seme, int x, int y, int z) {
        this.val = val;
        this.seme = seme;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getVal() {
        return val;
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
        switch (seme) {
            case 0: this.seme = SETTINGS.SEMI.CER;
                break;
            case 1: this.seme = SETTINGS.SEMI.BAM;
                break;
            case 2: this.seme = SETTINGS.SEMI.CAR;
                break;

            case 3: this.seme = SETTINGS.SEMI.WND;
                break;
            case 4: this.seme = SETTINGS.SEMI.DRA;
                break;
            case 5: this.seme = SETTINGS.SEMI.FIO;
                break;
            case 6: this.seme = SETTINGS.SEMI.STA;
                break;
        }
    }

    public int getSeme() {
        if(seme == SETTINGS.SEMI.CER) return 0;
        if(seme == SETTINGS.SEMI.BAM) return 1;
        if(seme == SETTINGS.SEMI.CAR) return 2;
        if(seme == SETTINGS.SEMI.WND) return 3;
        if(seme == SETTINGS.SEMI.DRA) return 4;
        if(seme == SETTINGS.SEMI.FIO) return 5;
        return 6;
    }

    public SETTINGS.SEMI getSemeEnum(){
        return this.seme;
    }

    @Override
    public String toString() {
        return val + "" + seme;
    }

    public void Observe(TesseraController ctr){
        this.ctr = ctr;
    }
}
