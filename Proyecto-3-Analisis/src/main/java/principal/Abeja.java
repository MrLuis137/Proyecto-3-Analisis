/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Luis Diego Alemán
 */

import principal.Color;
import principal.Direccion;
import java.util.Random;

public class Abeja {
    
    Color colorFav;
    Direccion direccionFav;
    int orden;
    int distMax;
    Abeja[] ancestros;
    
    Abeja(){
        this.orden = (int)Math.random() * 2;
        this.distMax  = (int) Math.random() * 100 + 10;
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 15);
        //this.colorFav
        
    }
    
    Abeja(Color ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax;
        this.ancestros[1] = ancestro1;
        this.ancestros[2] = ancestro2;
    }
    
    public void buscarFlor(){}
    
    public Abeja cruzarAbeja(){
        return null;
    }
    
    
    
    
}
