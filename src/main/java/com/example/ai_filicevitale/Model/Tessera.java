package com.example.ai_filicevitale.Model;

import com.example.ai_filicevitale.Controller.TesseraController;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

import com.example.ai_filicevitale.SETTINGS;

@Id("tess")
public class Tessera {
	
	@Param(0)
    private int val;

	@Param(1)
    private SETTINGS.SEMI seme;
    
	@Param(2)
    int x;
	@Param(3)
    int y; 
	@Param(4)
    int z;
	
    TesseraController ctr;
    boolean tesseraFree = false;

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

    public SETTINGS.SEMI getSeme() {
        return seme;
    }

    public void setLibera(){
        tesseraFree = true;
        ctr.notify(this);
    }

    public boolean getLibera(){
        return tesseraFree;
    }

    public void setSeme(SETTINGS.SEMI seme) {
        this.seme = seme;
    }

    @Override
    public String toString() {
        return val + "" + seme;
    }

    public void Observe(TesseraController ctr){
        this.ctr = ctr;
    }
}
