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
    
    private float posX;
    private float posY;
    private Color colorFav;
    private ArrayList<Flor> flores = new ArrayList<Flor>();//Flores de las cuales obtuvo polen.

    
    Flor(float posX, float posY, Color colorFav){
        this.posX=posX;
        this.posY=posY;
        this.colorFav=colorFav;
    }
    
    public void agregarFlor(Flor flor){
        flores.add(flor);
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
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
    
}
