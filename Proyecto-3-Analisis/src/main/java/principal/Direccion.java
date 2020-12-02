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
public enum Direccion {
    N,
    S,
    E,
    O,
    NO,
    NE,
    SO,
    SE;
    
    private final int index;
    
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
