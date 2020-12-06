/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author lalem
 */

import java.util.ArrayList;
import logica.Flor;
import processing.core.PApplet;

 

public class CampoFlores {
    
    PApplet sketch;
    
    public CampoFlores (PApplet sketch) {
        this.sketch = sketch;
    }
    
    public void run(ArrayList<Flor> flores){
        for(Flor flor: flores){
            sketch.fill(0,0,0);
            sketch.rect(flor.getPosX()*10,flor.getPosY()*10,10,10);
        }
    }
}
