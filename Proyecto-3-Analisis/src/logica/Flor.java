/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
import java.util.ArrayList;



public class Flor {
    
    static private int count = 0;
    static private int globalgen = 0;
    private String id = "";
    
    private final double factorMutacion = 5.0;
    private int posX;
    private int posY;
    private int color;
    private int generacion;
    private ArrayList<Flor> ancestros= new ArrayList<>();
    private ArrayList<Flor> flores = new ArrayList<>();//Flores de las cuales obtuvo polen.

    
    public Flor(){
        id= "FL-0-" + count;
        //this.pos =(int) (Math.random() * tamaño de la ventana);
        this.posX = (int) (Math.random() * 100);
        this.posY = (int) (Math.random() * 100);
        this.color = (int) (Math.random() * 8);
        this.generacion=0;
        count++;
    }
    
    public Flor(int posX, int posY, int color, Flor ancestro1, Flor ancestro2){
        this.posX=posX;
        this.posY=posY;
        this.color=color;
        this.generacion=ancestro1.getGeneracion()+1;
        this.ancestros.add(ancestro1);this.ancestros.add(ancestro2);  
        if(this.generacion > globalgen){
            count = 0; globalgen++;
        }
        id = "FL-" + globalgen + "-" + count;
        count++;
    
    }
    
    public void agregarFlor(Flor flor){
        flores.add(flor);
        //flores.addAll(flores)//Si lo que va agregar de una un arraylist entero
    }
    
    public void agregarFlores(ArrayList<Flor> polen){
        //System.out.println(flores.size());
        flores.addAll(polen);
    }

    public String getId() {
        return id;
    }
    
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosY() {
        return posY;
    }
    
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getGeneracion() {
        return generacion;
    }
    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }
    
        
    public ArrayList<Flor> getFlores() {
        return flores;
    }

    public ArrayList<Flor> getAncestros() {
        return ancestros;
    }
    
    public String completarBits(String numero, int tipo){
        if(tipo==0)while(numero.length()<3)numero="0"+numero;  
        else while(numero.length()<7)numero="0"+numero;
        return numero;    
    }
    
    public void mostrarADN(int deph){
        if(deph<2){
            System.out.println("________________INFORMACION________________");
            System.out.print("GENERATION: ");
            System.out.println(this.generacion);
            System.out.print("POSX,POSY: ");
            System.out.print(this.posX+","+this.posY);
            System.out.println(" --- Binario: "+Integer.toBinaryString(this.posX)+","+Integer.toBinaryString(this.posY));
            System.out.print("COLOR: ");
            System.out.print(this.color);
            System.out.println(" --- Binario: "+Integer.toBinaryString(this.color)+"\n");
            
            if(this.getAncestros().size()>0){
                System.out.println("____________ANCESTROS____________");
                System.out.println("_________Ancestro 1");
                this.getAncestros().get(0).mostrarADN(deph+1);
                System.out.println("_________Ancestro 2");
                this.getAncestros().get(1).mostrarADN(deph+1);    
            }  
        }         
    }
    
    public String obtenerCadena(Flor flor){
        String cadena="";
        //Posicion
        cadena=cadena+completarBits(Integer.toBinaryString(flor.getPosX()),1);
        //System.out.println(cadena);
        
        cadena=cadena+completarBits(Integer.toBinaryString(flor.getPosY()),1);
        //System.out.println(cadena);
        
        //Color
        cadena=cadena+completarBits(Integer.toBinaryString(flor.getColor()),0);
        //System.out.println(cadena);
        return cadena; 
    }
    public Flor generarFlor(String cadena){
        
        return null;
    }
            
    public Flor cruzarFlores(){
        //System.out.println("Wenas");
        if (flores.size()>0){
            int indice = (int)(Math.random()*(flores.size()));

            String individuo1=obtenerCadena(this);
            String individuo2=obtenerCadena(flores.get(indice));

            int corte=(int)(Math.random()*17);

            String nuevo= individuo1.substring(0, corte);
            String nuevo1= individuo2.substring(corte, individuo2.length());
            nuevo=nuevo+nuevo1;

            //Mutación
            if((Math.random()* 1) < factorMutacion){
                int i=(int)(Math.random()*17);
                char mutacion = (nuevo.charAt(i) == '0')? '1':'0';
                String mit1 = nuevo.substring(0, i );
                String mit2 = nuevo.substring(i + 1 , nuevo.length() );
                nuevo = mit1 + mutacion + mit2; 
            }
            /*
            System.out.println(corte+" "+nuevo);
            System.out.println(nuevo.substring(0, 8)+" "+nuevo.substring(8, 16)+" "+ nuevo.substring(16, 19));
            */
            Flor f = new Flor(Integer.parseInt(nuevo.substring(0, 7),2),Integer.parseInt(nuevo.substring(7, 14),2),
                    Integer.parseInt(nuevo.substring(14, 17),2),this,flores.get(indice));
            this.flores = null;
            return f;
        }
        else{
            Flor newFlor = new Flor();
            newFlor.setGeneracion(this.getGeneracion()+1);
            this.flores = null;
            return newFlor;
        }
    }
    
    @Override
    public String toString() {
        String ancestro1;
        String ancestro2;
        if(ancestros.size() == 0 ){
            ancestro1 = "NA";
            ancestro2 = "NA";
        }
        else{
            ancestro1 = ancestros.get(0).getId();
            ancestro2 = ancestros.get(1).getId();
        }
        
        return "Flor{" + "\n\tID: " + id + 
                "\n\tGeneracion: " + generacion + 
                "\n\tColor: " + color + 
                "\n\tPosicion X: " + posX + 
                "\n\tPosicion Y: " + posY + 
                "\n\tAncestro1:" + ancestro1 + 
                "\n\tAncestro2:" + ancestro2 + 
                "\n}\n";
    }
}
