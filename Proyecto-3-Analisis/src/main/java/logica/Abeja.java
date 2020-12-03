/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Luis Diego Alemán
 */


import logica.Direccion;
import logica.Flor;

public class Abeja {
    
    private int colorFav;
    private Direccion direccionFav;
    private int orden;
    private int distMax;
    private int generacion;
    private Abeja[] ancestros;

    public Flor getUltimaFlor() {
        return ultimaFlor;
    }

    public void setUltimaFlor(Flor ultimaFlor) {
        this.ultimaFlor = ultimaFlor;
    }
    private Flor ultimaFlor;
    
    
    Abeja(){
        this.orden = (int)Math.random() * 2;
        this.distMax  = (int) Math.random() * 100 + 10;
        this.colorFav =this.orden = (int)Math.random() * 7;
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 7);
        this.generacion = 0;
        this.ancestros = null;
        
        
    }
    
    Abeja(int ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2, int generacion){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax;
        this.ancestros[1] = ancestro1;
        this.ancestros[2] = ancestro2;
    }

    public int getGeneracion() {
        return generacion;
    }
    
    public void buscarFlor(){
        
    }
    
    public Abeja cruzarAbeja(Abeja abeja1,Abeja abeja2 ){
        
        return null;
    }
    public int getColorFav() {
        return colorFav;
    }

    public void setColorFav(int colorFav) {
        this.colorFav = colorFav;
    }

    public Direccion getDireccionFav() {
        return direccionFav;
    }

    public void setDireccionFav(Direccion direccionFav) {
        this.direccionFav = direccionFav;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getDistMax() {
        return distMax;
    }

    public void setDistMax(int distMax) {
        this.distMax = distMax;
    }

    public Abeja[] getAncestros() {
        return ancestros;
    }

    public void setAncestros(Abeja[] ancestros) {
        this.ancestros = ancestros;
    }
    
    public void getADN(){
       // ADN = color + dir + orden + distMax 
       String bColor = Integer.toBinaryString(colorFav);
       //Rellena de 0 para que la cantidad de bits sea constante
        if(bColor.length() < 3){
           int dif = 3 - bColor.length();
           for(int i = 0; i < dif; i++){
               bColor = "0" + bColor;
           }
        }
       String bDir = Integer.toBinaryString(direccionFav.index);
       if(bDir.length() < 3){
           int dif = 3 - bDir.length();
           for(int i = 0; i < dif; i++){
               bDir = "0" + bDir;
           }
       }
       String bOrd = Integer.toBinaryString(orden);
       if(bOrd.length() < 2){
           int dif = 2 - bOrd.length();
           for(int i = 0; i < dif; i++){
               bOrd = "0" + bOrd;
           }
       }
       String bDist =Integer.toBinaryString(distMax);
       if(bDist.length() < 8){
           int dif = 8 - bDist.length();
           for(int i = 0; i < dif; i++){
               bDir = "0" + bDir;
           }
       }
       
        String ADN = bColor + bDir + bOrd + bDist;
    }

    @Override
    public String toString() {
        return "Abeja{" + "\n colorFav=" + colorFav + "\n,direccionFav=" + direccionFav + "\n, orden=" + orden + "\n, distMax=" + distMax + "\n, ancestros=" + ancestros + "\n, ultimaFlor=" + ultimaFlor + "\n}";
    }
    
    
    
    
    
}
