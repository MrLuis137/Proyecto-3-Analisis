/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import java.util.ArrayList;

/**
 *
 * @author Jacob Picado García
 */
public class Flor {
    
    private int posX;
    private int posY;
    private Color colorFav;
    private Flor ancestros[];
    private ArrayList<Flor> flores = new ArrayList<Flor>();//Flores de las cuales obtuvo polen.

    
    Flor(){
        //this.pos =(int) (Math.random() * tamaño de la ventana);
        this.posX = (int) (Math.random() * 100);
        this.posY = (int) (Math.random() * 100);
        this.colorFav = new Color();
    }
    
    Flor(int posX, int posY, Color colorFav, Flor ancestro1, Flor ancestro2){
        this.posX=posX;
        this.posY=posY;
        this.colorFav=colorFav;
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
    
    public Color getColorFav() {
        return colorFav;
    }
    public void setColorFav(Color colorFav) {
        this.colorFav = colorFav;
    }
    
    public ArrayList<Flor> getFlores() {
        return flores;
    }

    public Flor[] getAncestros() {
        return ancestros;
    }
    
}
