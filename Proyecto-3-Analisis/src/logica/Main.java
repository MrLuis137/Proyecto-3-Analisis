/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import gui.CampoFlores;
import gui.MainWindow;
import java.io.FileWriter;
import java.io.PrintWriter;
import processing.core.PApplet;
import logica.Flor;
import java.util.ArrayList;

/**
 *
 * @author lalem
 */
public class Main extends PApplet{
    
    public static final int cantidadFlores = 100;
    public static final int cantidadAbejas = 1000;
    public static final int TamanioCampo = 100;
    public static int maxIteraciones = 1000;
    public static ArrayList<ArrayList<Flor>> HistorialFlores = new ArrayList<ArrayList<Flor>>(); 
    public static ArrayList<Flor> FloresActuales = new ArrayList<Flor>();
    public static ArrayList<Abeja> abejasActuales = new ArrayList<Abeja>();
    static boolean cambios = false;
    public static CampoFlores campo;
    public static MainWindow w;
    public static int convergenciaFlores = 0;
    public static int convergenciaAbejas = 0;
    
    
    
    public static void main(String[]Args){
        String[] appletArgs = new String[] { "logica.Main" };
        PApplet.main(appletArgs);
        
    }
    
    @Override
    public void settings(){
        size(TamanioCampo * 5,TamanioCampo * 5);
    }
    
    @Override
    public void setup(){
        w = new MainWindow();
        w.setVisible(true);
        campo = new CampoFlores(this);
        for(int i = 0; i < cantidadFlores; i++ ){
            FloresActuales.add(new Flor());
        }
        for(int i = 0; i < cantidadAbejas; i++){
            abejasActuales.add(new Abeja());
        }
        w.actualizarListaAbejas();
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
    
     public static void escribirInfo(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String ruta = "D:\\prueba.txt";
        int iteracion = 0;
        try{
            if (iteracion==0){
                fichero = new FileWriter(ruta);
            }else{
                fichero = new FileWriter(ruta,true);
            }
            
            iteracion++;
            pw = new PrintWriter(fichero);
            pw.println("\n_____________________________________________________________Iteración: "+iteracion+"_____________________________________________________________\n");
            
            pw.println("\t\t\t\t\t_____________________Abejas_____________________");
            pw.println("\t\t\t\t\t************************************************");
            for(Abeja bee: abejasActuales){pw.println(bee);}
            
            pw.println("\t\t\t\t\t_____________________Flores_____________________");
            pw.println("\t\t\t\t\t************************************************");
            //System.out.println("_________________________________________________________________________________-");
            for(Flor flower: FloresActuales){
                //System.out.println(flower.cantidadPolen());
                //System.out.println(flower);
                pw.println(flower);}
            pw.println("");
   
        }catch (Exception e){e.printStackTrace();}finally {
            try {if (null != fichero)fichero.close();
            }catch (Exception e2) {e2.printStackTrace();}
        }
    }
    
    public static void run(){
        for(int i = 0; i < maxIteraciones; i++){
            System.out.println(i);
            runIteration();
            cambios = true;
            short haConvergido = haConvergido();
            switch (haConvergido){
                case 10:
                    convergenciaAbejas =(convergenciaAbejas == 0 )? i: convergenciaAbejas ;
                case 01:
                    convergenciaFlores = (convergenciaFlores == 0 )? i : convergenciaFlores;
                case 11:
                    convergenciaAbejas =(convergenciaAbejas == 0 )? i: convergenciaAbejas ;
                    convergenciaFlores = (convergenciaFlores == 0 )? i : convergenciaFlores;
                    return;
            }
            
        }
    }
    
    public static void runIteration(){
        ArrayList<float[]> puntajes = new ArrayList<float[]>();
        float puntajeGlobal = 0;
        float i = 0;
        escribirInfo();
        for(Abeja abeja: abejasActuales){
            //abeja.recorrerCampo();
            abeja.busqueda();
            float[] pts = {i,abeja.CalcularCalificacion()};
            puntajes.add(pts);
            puntajeGlobal+= abeja.getPuntaje();
            i++;
            
            /*                 CODIGO PARA PRUEBAS PARA LAS ABEJAS
            System.out.println("DISTANCIA MAX: "+abeja.getDistMax());
            System.out.println("DISTANCIA RECORRIDA: "+abeja.getDistanciaRecorrida());
            System.out.println("______________________________________________________________________________");//*/
        }
        /*                 CODIGO PARA PRUEBAS PARA LAS FLORES
        for (Flor flor: FloresActuales){
            if(!flor.getFlores().isEmpty()){
                System.out.println("_________________________________________________________________________");
                System.out.println("_____________________________PRINCIPAL___________________________________");
                System.out.println("*************************************************************************");
                flor.mostrarADN(0);
                System.out.println("CANTIDAD DE POLEN: "+flor.getFlores().size());
                System.out.println("_________________________________________________________________________");
                System.out.println("----------------------------------POLEN----------------------------------");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");                
                for(Flor a: flor.getFlores()){
                a.mostrarADN(0);}}}//*/
        
        if(puntajeGlobal != 0){
        for(float[] puntaje : puntajes){ puntaje[1] = puntaje[1] / puntajeGlobal;}
        
        }
        if(HistorialFlores.size() == 0 ){
            HistorialFlores.add(Main.FloresActuales);
        }
        realiazarCruce(puntajes);
        HistorialFlores.add(Main.FloresActuales);
        
        
    }
    
    public static short haConvergido(){
        short ca = 1;
        short cf = 1;
        String temp = abejasActuales.get(0).getADN();
         for(Abeja a: abejasActuales){
            if(!a.getADN().equals(temp)){
                ca = 0;
            }
        }
        
        Flor fl = FloresActuales.get(0);
        temp = fl.obtenerCadena(fl);
        for(Flor f: FloresActuales){
            if(!f.obtenerCadena(f).equals(temp)){
                cf = 0;
            }
        }
        return (short) (ca * 10 + cf);
    }
    
    private static void realiazarCruce(ArrayList<float[]> puntajes){
        ArrayList<Abeja> nuevasAbejas = new ArrayList<Abeja>();
        ArrayList<Flor> nuevasFlores = new ArrayList<Flor>();
        for(float[] puntaje: puntajes){
            //System.out.println(puntaje[1]);
        }
        ordenarPuntajes(puntajes, 0, puntajes.size() - 1);
        //System.out.println("******");
        for(int i = 0; i < (cantidadAbejas / 2); i++ ){
            float min =  puntajes.get(0)[1];
            float max = puntajes.get(puntajes.size() - 1)[1];
            double n1 = Math.random() * (max - min) +  min;
            double n2 = Math.random() * (max - min) +  min;
            Abeja a1 = null;
            Abeja a2 = null;
            for(int j  = 0; j < puntajes.size(); j++){
                if(j == 0){
                    if(n1 <= puntajes.get(j)[1] && a1 == null){
                    a1 = abejasActuales.get((int)puntajes.get(j)[0]);
                    }
                    if(n2 <= puntajes.get(j)[1] && a2 == null){
                        a2 = abejasActuales.get((int)puntajes.get(j)[0]);
                    }
                    continue;
                }
                if(n1 <= puntajes.get(j)[1] && n1 >= puntajes.get(j-1)[1] && a1 == null){
                    a1 = abejasActuales.get((int)puntajes.get(j)[0]);
                }
                if(n2 <= puntajes.get(j)[1] && n2 >= puntajes.get(j-1)[1] -1 && a2 == null){
                    a2 = abejasActuales.get((int)puntajes.get(j)[0]);
                }
                if(a1 != null && a2 != null){break;}
            }
            //System.out.println(n1);
            //System.out.println(n2);
            nuevasAbejas.add(a1.cruzarAbeja(a2));
            nuevasAbejas.add(a2.cruzarAbeja(a1));
        }
        abejasActuales = nuevasAbejas;
        //FLORES
        for(Flor f: FloresActuales ){
            //System.out.println(f.getFlores().size());
            Flor flor = f.cruzarFlores();
            //System.out.println(flor.getPosX());
            //System.out.println(flor.getPosY());
            //System.out.println(flor.getColor());
            nuevasFlores.add(flor);
        }
        //System.out.println("---");
        FloresActuales = nuevasFlores;
        cambios = true;
    }
    
    
    private static void ordenarPuntajes(ArrayList<float[]> A, int izq, int der) {

        float[] pivote = A.get(izq); // tomamos primer elemento como pivote
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        float[] aux;

        while(i < j){                          // mientras no se crucen las búsquedas                                   
           while(A.get(i)[1] <= pivote[1] && i < j) i++; // busca elemento mayor que pivote
           while(A.get(j)[1] > pivote[1]) j--;           // busca elemento menor que pivote
           if (i < j) {                        // si no se han cruzado                      
               aux= A.get(i);                      // los intercambia
               A.set(i, A.get(j));
               A.set(j, aux);
           }
         }

        A.set(izq, A.get(j));
        A.set(j, pivote);

         if(izq < j-1)
            ordenarPuntajes(A,izq,j-1);          // ordenamos subarray izquierdo
         if(j+1 < der)
            ordenarPuntajes(A,j+1,der);          // ordenamos subarray derecho

      }
    
    public static void verCampo(int n){
        FloresActuales = HistorialFlores.get(n);
        cambios = true;
        //campo.run(HistorialFlores.get(n));
    }
}
