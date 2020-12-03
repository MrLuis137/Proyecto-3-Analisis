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


import java.nio.ByteBuffer;
import logica.Color;
import logica.Direccion;
import logica.Flor;

public class Abeja {
    
    private Color colorFav;
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
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 7);
        this.generacion = 0;
        this.ancestros = null;
        //this.colorFav
        
    }
    
    Abeja(Color ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2, int generacion){
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
    public Color getColorFav() {
        return colorFav;
    }

    public void setColorFav(Color colorFav) {
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
       // ADN = r + g + b + dir + orden + distMax 
        String ADN = Integer.toBinaryString(colorFav.r) +Integer.toBinaryString(colorFav.g) +Integer.toBinaryString(colorFav.b)
                + Integer.toBinaryString(direccionFav.index) + Integer.toBinaryString(orden) + Integer.toBinaryString(distMax);
        
    }

    @Override
    public String toString() {
        return "Abeja{" + "\n colorFav=" + colorFav + "\n,direccionFav=" + direccionFav + "\n, orden=" + orden + "\n, distMax=" + distMax + "\n, ancestros=" + ancestros + "\n, ultimaFlor=" + ultimaFlor + "\n}";
    }
    
    
    
    
    
}
