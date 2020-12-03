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
public enum Direccion {
    N(0),
    S(1),
    E(2),
    O(3),
    NO(4),
    NE(5),
    SO(6),
    SE(7);
    
    final int index;
    
    private Direccion(int index){this.index = index;}
    
    public static Direccion getDireccion(int index){
        for(Direccion dir : Direccion.values()){
            if(dir.index == index){
                return dir;
            }
        }
        return null;
    }
}
