/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author lalem
 */
public class Color {
    int r = 0;
    int g = 0;
    int b = 0;
    
    Color(int r, int g, int b){
        this.r = r; this.g = g; this.b = b;
    }

    @Override
    public String toString() {
        return "Color" + "R:" + r + ", G:" + g + ", B:" + b;
    }
    
    
    
}
