/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

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
    
    Color(){
        int indice = (int) (Math.random() * 8);
        switch(indice){
            case 0:
                r=255;g=0;b=0;//Rojo
                break;
            case 1:
                r=0;g=255;b=0;//verde
                break;
            case 2:
                r=0;g=0;b=255;//Azul
                break;
            case 3:
                r=255;g=255;b=0;//Amarillo
                break;
            case 4:
                r=160;g=0;b=160;//Morado
                break;
            case 5:
                r=0;g=255;b=255;//Celeste
                break;
            case 6:
                r=255;g=128;b=0;//Naranja
                break;
            case 7:
                r=255;g=0;b=128;//Rosa
                break;
        }
    }
    
}
