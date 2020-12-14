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
        sketch.clear();
        sketch.background(255);
        for(Flor flor: flores){
            switch(flor.getColor()){
                case 0:
                    sketch.fill(255,0,0);
                    break;
                case 1:
                    sketch.fill(0,255,0);
                    break;
                case 2:
                    sketch.fill(0,0,255);
                    break;
                case 3:
                    sketch.fill(255,255,0);
                    break;
                case 4:
                    sketch.fill(160,0,160);
                    break;
                case 5:
                    sketch.fill(0,255,255);
                    break;
                case 6:
                    sketch.fill(255,128,0);
                    break;
                case 7:
                    sketch.fill(255,0,128);
                    break;
            }
                sketch.rect(flor.getPosX()*5,flor.getPosY()*5,5,5);
        }
    }
}
