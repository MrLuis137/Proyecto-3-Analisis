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
    private Flor ancestros[];
    private ArrayList<Flor> flores = new ArrayList<Flor>();//Flores de las cuales obtuvo polen.

    
    Flor(){
        //this.pos =(int) (Math.random() * tamaño de la ventana);
        this.posX = (int) (Math.random() * 100);
        this.posY = (int) (Math.random() * 100);
        this.color = (int) (Math.random() * 7);
    }
    
    Flor(int posX, int posY, int color, Flor ancestro1, Flor ancestro2){
        this.posX=posX;
        this.posY=posY;
        this.color=color;
        this.ancestros[0]=ancestro1;
        this.ancestros[1]=ancestro2;   
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
    
    public ArrayList<Flor> getFlores() {
        return flores;
    }

    public Flor[] getAncestros() {
        return ancestros;
    }
    
}
