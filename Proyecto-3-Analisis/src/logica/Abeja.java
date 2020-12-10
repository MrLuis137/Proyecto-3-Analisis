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
    //====== IRRELEVANTE PARA LA BUSQUEDA, CRUCE ETC..=============
    static private int count = 0;
    static private int globalgen = 0;
    String id = "";
    //====== IRRELEVANTE PARA LA BUSQUEDA, CRUCE ETC..=============
    int puntoInicioX = 50;
    int puntoInicioY = 50;
    boolean mutada = false;
    private double factorMutacion = 0.5;
    private int colorFav;
    private Direccion direccionFav;
    private int orden;
    private int distMax;
    private float distanciaRecorrida = 0;
    private int generacion;
    private Abeja[] ancestros;
    private ArrayList<Flor> floresVisitadas = new ArrayList<Flor>(); 
    private int posX;
    private int posY;
    private float puntaje = -1; 
    
    //------------CODIGO TEMPORAL---------------
    //------------------------------------------
    public void busquedaTemporal(){
        if(distanciaRecorrida >= distMax){return;}
        for(int i = 0; i < 100; i++){
            Flor f = Main.FloresActuales.get(i);
            if( f.getColor()== this.colorFav && !floresVisitadas.contains(f)){
                floresVisitadas.add(f);
                float tempX  = this.posX - f.getPosX();
                float tempY = this.posY - f.getPosY();
                float dist = (float) Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
                distanciaRecorrida += dist;
                
                
                this.posX = f.getPosX();
                this.posY = f.getPosY();
                
            }
        }
    }
    //------------------------------------------
    //------------CODIGO TEMPORAL---------------
    
    public Abeja(){
        //Generación random de datos
        id= "AB-0-" + count;
        count++;
        this.orden = (int)(Math.random() * 3);
        System.out.println(this.orden);
        this.distMax  = (int) (Math.random() * (100 - 10) + 10);
        this.colorFav = (int) (Math.random() * 7);
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 7);
        this.generacion = 0;
        if((int)(Math.random()*1) == 0){
           posX = puntoInicioX;
           posY = puntoInicioY;
        }
        else{getRadomPos();}
        this.ancestros = null;   
    }
    
    public Abeja(String ADN, Abeja ancestro1, Abeja ancestro2){
        // ADN = color + dir + orden + distMax 
        colorFav = Integer.parseInt(ADN.substring(0, 3), 2);
        direccionFav = Direccion.getDireccion( Integer.parseInt(ADN.substring(3, 6), 2) );
        orden = Integer.parseInt(ADN.substring(6, 8), 2);
        distMax = Integer.parseInt(ADN.substring(8, 16), 2);
        System.out.println(ADN.length());
        posX = puntoInicioX  = Integer.parseInt(ADN.substring(16, 24), 2);
        posY = puntoInicioY = Integer.parseInt(ADN.substring(24, 32), 2);
        this.generacion = ancestro1.generacion + 1;
        ancestros = new Abeja[]{ancestro1, ancestro2};
        if(this.generacion > globalgen){
            count = 0; globalgen++;
        }
        id = "AB-" + globalgen + "-" + count;
        count++;
    }

    public String getId() {
        return id;
    }
    
    
    public Abeja(int ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2, int generacion){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax;
        this.ancestros[1] = ancestro1;
        this.ancestros[2] = ancestro2;
    }
    
    private void getRadomPos(){
        int x = 0;
        int y = 0;
        int mitad = Main.TamanioCampo / 2;
        switch(direccionFav){
            case N:
                y = (int) Math.random() * (this.distMax - mitad) + mitad; 
                break;
                
            case NE:
                do{
                    y = (int) Math.random() * (this.distMax - mitad) + mitad;
                    x = (int) Math.random() *  mitad;
                }
                while(Math.sqrt((double)( x^2 + y^2 )) >= distMax);
                break;
                
            case E:
                    x = (int) Math.random() *  mitad;
                break;
                
            case SE:
                do{
                    y = (int) Math.random() *  mitad;
                    x = (int) Math.random() *  mitad;
                }
                while(Math.sqrt((double)( x^2 + y^2 )) >= distMax);
                break;
                
            case S:
                y = (int) Math.random() *  mitad;
                break;
                
            case SO:
                do{
                    y = (int) Math.random() *  mitad;
                    x = (int) Math.random() * (this.distMax - mitad) + mitad;
                }
                while(Math.sqrt((double)( x^2 + y^2 )) >= distMax);
                break;
                
            case O:
                x = (int) Math.random() * (this.distMax - mitad) + mitad;
                break;
            case NO:
                break;
        }
        this.posX = x;
        this.posY = y;
    }
    
    public void recorrerCampo(){
        //------------CODIGO TEMPORAL---------------
        busquedaTemporal();
        //------------CODIGO TEMPORAL---------------
        if(distanciaRecorrida < distMax){recorrerCampo();}
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
    
    public boolean isMutada(){return mutada;}
    
    public void setMutada(boolean isMutada){mutada = isMutada;}
    

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
        float floresVisitadas = 0;
        String ADN1 = this.getADN();
        String ADN2 = abeja2.getADN();
        //Posición de corte random
        int corte = (int) (Math.random() * 30);
        //Obtiene las partes del ADN
        String parte1 = ADN1.substring(0, corte);
        String parte2 = ADN2.substring(corte, 32);
        //Lo reconvina
        String nuevoADN = parte1 + parte2;
         System.out.println(nuevoADN);
         //genera la mutación
         boolean muta = false;
        if((Math.random()* 1) < factorMutacion){
            System.out.println("La abeja ha mutado");
           muta = true;
           int indice = (int)(Math.random()* 31 + 1);
           char mutacion = (nuevoADN.charAt(indice) == '0')? '1':'0';
           String mit1 = nuevoADN.substring(0, indice );
           String mit2 = nuevoADN.substring(indice + 1 , nuevoADN.length() );
           nuevoADN = mit1 + mutacion + mit2; 
        }
        Abeja a = new Abeja(nuevoADN, this, abeja2);
        a.setMutada(muta);
        return a;
    }
    
    public String getADN(){
       // ADN = color + dir + orden + distMax + inicioX + inicioY
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
       
       String iniX =Integer.toBinaryString(distMax);
       if(iniX.length() < 8){
           int dif = 8 - iniX .length();
           for(int i = 0; i < dif; i++){
               iniX  = "0" + iniX ;
           }
       }
       
       String iniY =Integer.toBinaryString(distMax);
       if(iniY.length() < 8){
           int dif = 8 - iniY.length();
           for(int i = 0; i < dif; i++){
               iniY = "0" + iniY;
           }
       }
       
        String ADN = bColor + bDir + bOrd + bDist + iniX + iniY;
        
        return ADN;
    }
    
    public float CalcularCalificacion(){
        this.puntaje = floresVisitadas.size()/distanciaRecorrida * 1000;
        return puntaje;
    }

    public float getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        return "Abeja{" + "\n colorFav=" + colorFav + "\n,direccionFav=" + direccionFav + "\n,orden=" + orden + "\n,distMax=" + distMax + "\n,ancestros=" + ancestros + "\n,Flores visitadas=" + floresVisitadas.size() + "\n}";
    }
    
    
    
    
    
}
