package com.example.ai_filicevitale.Model;

import java.util.LinkedList;

public class Layer {
    private int dimX;
    private int dimY;
    private int offsX;
    private int offsY;
    private final boolean tagliato;
    Tessera[][] tessere;


    public int getDimX() {
        return dimX;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }

    public int getOffsX() {
        return offsX;
    }

    public void setOffsX(int offsX) {
        this.offsX = offsX;
    }

    public int getOffsY() {
        return offsY;
    }

    public void setOffsY(int offsY) {
        this.offsY = offsY;
    }

    public Layer(int dimX, int dimY, int offsX, int offsY, boolean tagliato, LinkedList<Tessera> tessere, int numLayer) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.offsX = offsX;
        this.offsY = offsY;
        this.tagliato = tagliato;
        this.tessere = new Tessera[dimX][dimY];

        for(int r= 0; r < dimX; r++){
            for(int c=0; c < dimY; c++){
                if(!tagliato || ( tagliato && !inAngolo(r,c,dimX, dimY)) ) {
                    this.tessere[r][c] = tessere.pop();
                    this.tessere[r][c].setX(r);
                    this.tessere[r][c].setY(c);
                    this.tessere[r][c].setZ(numLayer);
                }
            }
        }
    }

    public Tessera getTessera(int x, int y){
        //System.out.println("PROVO A GETTESSERA CON " + x + " " + y);
        return tessere[x][y];
    }

    private boolean inAngolo(int r, int c, int dimX, int dimY) {
        if(r==0 && c==0) return true;
        if (r == dimX - 1 && c == dimY - 1) return true;
        if (r == dimX - 1 && c == 0) return true;
        return r == 0 && c == dimY - 1;
    }

    public void remove(int x, int y){
        tessere[x][y] = null;
    }
}
