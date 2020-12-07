/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import gui.CampoFlores;
import gui.MainWindow;
import processing.core.PApplet;
import logica.Flor;
import java.util.ArrayList;

/**
 *
 * @author lalem
 */
public class Main extends PApplet{
    
    public static final int cantidadFlores = 100;
    public static final int cantidadAbejas = 100;
    public static final int TamanioCampo = 100;
    
    public static ArrayList<Flor> FloresActuales = new ArrayList<Flor>();
    public static ArrayList<Abeja> AbejasActuales = new ArrayList<Abeja>();
    boolean cambios = false;
    CampoFlores campo;
    
    public static void main(String[]Args){
        String[] appletArgs = new String[] { "logica.Main" };
        PApplet.main(appletArgs);
        new MainWindow().setVisible(true);
        
    }
    
    @Override
    public void settings(){
        size(TamanioCampo * 5,TamanioCampo * 5);
    }
    
    @Override
    public void setup(){
        campo = new CampoFlores(this);
        for(int i = 0; i < cantidadAbejas; i++ ){
            FloresActuales.add(new Flor());
        }
        for(int i = 0; i < cantidadFlores; i++){
            AbejasActuales.add(new Abeja());
        }
        cambios = true;
        //AbejasActuales.get(0).cruzarAbeja(AbejasActuales.get(2));
    }
    
    @Override
    public void draw(){
        if(cambios){
            campo.run(FloresActuales);
            cambios = false;
        }
    }
}
