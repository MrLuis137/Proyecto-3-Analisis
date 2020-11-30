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
public class Abeja {
    
    Color colorFav;
    Direccion direccionFav;
    int orden;
    int distMax;
    
    Abeja(){
        
    }
    
    Abeja(Color ColorFav, Direccion DirecciónFav, int orden, int distMax){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax; 
    }
    
    
}
