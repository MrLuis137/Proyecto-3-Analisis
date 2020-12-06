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
import java.util.ArrayList;

public class Abeja {
    
    private double factorMutacion = 0.5;
    private int colorFav;
    private Direccion direccionFav;
    private int orden;
    private int distMax;
    private int generacion;
    private Abeja[] ancestros;
    private ArrayList<Flor> floresVisitadas = new ArrayList<Flor>(); 
    private int posX;
    private int posY;
    
    public Abeja(){
        //Generación random de datos
        this.orden = (int)(Math.random() * 3);
        System.out.println(this.orden);
        this.distMax  = (int) (Math.random() * 100 + 10);
        this.colorFav = (int) (Math.random() * 7);
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 7);
        this.generacion = 0;
        // Falta agregar la posicion inicial, la cual es el centro
        this.ancestros = null;   
    }
    
    public Abeja(String ADN, Abeja ancestro1, Abeja ancestro2){
        // ADN = color + dir + orden + distMax 
        colorFav = Integer.parseInt(ADN.substring(0, 3), 2);
        direccionFav = Direccion.getDireccion( Integer.parseInt(ADN.substring(3, 6), 2) );
        orden = Integer.parseInt(ADN.substring(6, 8), 2);
        distMax = Integer.parseInt(ADN.substring(8, 16), 2);
        this.ancestros = new Abeja[]{ancestro1, ancestro2}; 
    }
    
    
    public Abeja(int ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2, int generacion){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax;
        this.ancestros[1] = ancestro1;
        this.ancestros[2] = ancestro2;
    }
    
    public void agregarFlor(Flor f){
        floresVisitadas.add(f);
    }
    
    public double getFactorMutacion() {
        return factorMutacion;
    }

    public ArrayList<Flor> getFloresVisitadas() {
        return floresVisitadas;
    }

    public void setFactorMutacion(double factorMutacion) {
        this.factorMutacion = factorMutacion;
    }
    

    public int getGeneracion() {
        return generacion;
    }
    
    public void buscarFlor(){
        
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
    
    public Abeja cruzarAbeja(Abeja abeja2 ){
        //obtiene los ADN
        String ADN1 = this.getADN();
        String ADN2 = abeja2.getADN();
        //Posición de corte random
        int corte = (int) (Math.random() * 14);
        //Obtiene las partes del ADN
        String parte1 = ADN1.substring(0, corte);
        String parte2 = ADN2.substring(corte, 16);
        //Lo reconvina
        String nuevoADN = parte1 + parte2;
         System.out.println(nuevoADN);
         //genera la mutación
        if((Math.random()* 1) < factorMutacion){
            System.out.println("La abeja ha mutado");
           int indice = (int)(Math.random()* 15 + 1);
           char mutacion = (nuevoADN.charAt(indice) == '0')? '1':'0';
           String mit1 = nuevoADN.substring(0, indice );
           String mit2 = nuevoADN.substring(indice + 1 , nuevoADN.length() );
           nuevoADN = mit1 + mutacion + mit2; 
        }
        
        return new Abeja(nuevoADN, this, abeja2);
    }
    
    public String getADN(){
       // ADN = color + dir + orden + distMax 
       //Convierte el número a binario
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
        return ADN;
    }

    @Override
    public String toString() {
        return "Abeja{" + "\n colorFav=" + colorFav + "\n,direccionFav=" + direccionFav + "\n,orden=" + orden + "\n,distMax=" + distMax + "\n,ancestros=" + ancestros + "\n,Flores visitadas=" + floresVisitadas.size() + "\n}";
    }
    
    
    
    
    
}