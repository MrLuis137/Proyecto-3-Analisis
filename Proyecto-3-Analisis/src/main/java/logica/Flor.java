/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.util.ArrayList;

/**
 *
 * @author Jacob Picado García
 */
public class Flor {
    
    private int posX;
    private int posY;
    private int color;
    private int generacion;
    private Flor ancestros[];
    private ArrayList<Flor> flores = new ArrayList<Flor>();//Flores de las cuales obtuvo polen.

    
    public Flor(){
        //this.pos =(int) (Math.random() * tamaño de la ventana);
        this.posX = (int) (Math.random() * 100);
        this.posY = (int) (Math.random() * 100);
        this.color = (int) (Math.random() * 8);
        this.generacion=0; 
    }
    
    public Flor(int posX, int posY, int color, Flor ancestro1, Flor ancestro2){
        this.posX=posX;
        this.posY=posY;
        this.color=color;
        this.generacion=ancestro1.getGeneracion()+1;
        this.ancestros = new Flor[]{ancestro1, ancestro2}; 
    }
    
    public void agregarFlor(Flor flor){
        flores.add(flor);
        //flores.addAll(flores)//Si lo que va agregar de una un arraylist entero
    }
    
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosY() {
        return posY;
    }
    
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getGeneracion() {
        return generacion;
    }
        
    public ArrayList<Flor> getFlores() {
        return flores;
    }

    public Flor[] getAncestros() {
        return ancestros;
    }
    
    public String completarBits(String numero, int tipo){
        if(tipo==0)while(numero.length()<3)numero="0"+numero;  
        else while(numero.length()<8)numero="0"+numero;
        return numero;    
    }
    
    public String obtenerCadena(Flor flor){
        String cadena="";
        //Posicion
        cadena.concat(completarBits(Integer.toBinaryString(flor.getPosX()),1));
        cadena.concat(completarBits(Integer.toBinaryString(flor.getPosY()),1));
        //Color
        cadena.concat(completarBits(Integer.toBinaryString(flor.getColor()),0));
        return cadena; 
    }
    public Flor generarFlor(String cadena){
        
        return null;
    }
            
    public Flor cruzarFlores(){
        int indice = (int)(Math.random()*(flores.size()));
        
        String individuo1=obtenerCadena(this);
        String individuo2=obtenerCadena(flores.get(indice));
        
        int corte=(int)(Math.random()*19);
        
        String nuevo= individuo1.substring(0, corte)+individuo2.substring(corte, individuo2.length());
        
        //Mutación

        return new Flor(Integer.parseInt(nuevo.substring(0, 8)),Integer.parseInt(nuevo.substring(8, 16)),
                Integer.parseInt(nuevo.substring(16, 19)),this,flores.get(indice));
    }
}
