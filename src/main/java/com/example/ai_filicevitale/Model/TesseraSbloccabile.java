package com.example.ai_filicevitale.Model;

import com.example.ai_filicevitale.Controller.TesseraController;
import com.example.ai_filicevitale.SETTINGS;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("tesseraSbloccabile")
public class TesseraSbloccabile implements java.io.Serializable{
// tesseraBloccata(X,Y,Z,V,S,XB,YB,ZB,VB,SB)
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

    @Param(5)
    int xs;
    @Param(6)
    int ys;
    @Param(7)
    int zs;

    @Param(8)
    private int vals;

    @Param(9)
    private int semes;

    TesseraController ctr;
    boolean tesseraFree = false;

    public TesseraSbloccabile(){}

    public TesseraSbloccabile(TesseraGlobal t1, TesseraGlobal s1) {
         x  = t1.getX();
         y = t1.getY();
         z = t1.getZ();
         val= t1.getVal();
         seme = t1.getSeme();

        xs = s1.getX();

        ys = s1.getY();
        zs = s1.getZ();

        vals = s1.getVal();
        semes = s1.getSeme();
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
    }

    public boolean getLibera(){
        return tesseraFree;
    }


    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public int getYs() {
        return ys;
    }

    public void setYs(int ys) {
        this.ys = ys;
    }

    public int getZs() {
        return zs;
    }

    public void setZs(int zs) {
        this.zs = zs;
    }

    public int getVals() {
        return vals;
    }

    public void setVals(int vals) {
        this.vals = vals;
    }

    public int getSemes() {
        return semes;
    }

    public void setSemes(int semes) {
        this.semes = semes;
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
        return String.format("x %d y %d z %d val %d%s", x,y,z,val,getSemeEnum());
    }
}
