/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Luis Diego Alemán
 */


import logica.Direccion;
import logica.Flor;
import java.util.ArrayList;

public class Abeja {
    //====== IRRELEVANTE PARA LA BUSQUEDA, CRUCE ETC..=============
    static private int count = 0;
    static private int globalgen = 0;
    String id = "";
    //====== IRRELEVANTE PARA LA BUSQUEDA, CRUCE ETC..=============
    int puntoInicioX = 50;
    int puntoInicioY = 50;
    
    private int angulo;//Angulo de desviacion 
    
    boolean seAleja;
    boolean mutada = false;
    private double factorMutacion = 0.8;
    private int colorFav;
    private Direccion direccionFav;
    private int orden;
    private int distMax;
    private float distanciaRecorrida = 0;
    private int generacion;
    private Abeja[] ancestros;
    private ArrayList<Flor> floresVisitadas = new ArrayList<Flor>(); 
    private int posX;
    private int posY;
    private float puntaje = -1;
    
    //------------CODIGO TEMPORAL---------------
    //------------------------------------------
    public int busquedaTemporal(){
        if(distanciaRecorrida >= distMax){return 0;}
        for(int i = 0; i < 100; i++){
            Flor f = Main.FloresActuales.get(i);
            if( f.getColor()== this.colorFav && !floresVisitadas.contains(f)){
                floresVisitadas.add(f);
                float tempX  = this.posX - f.getPosX();
                float tempY = this.posY - f.getPosY();
                float dist = (float) Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
                distanciaRecorrida += dist;
                
                f.agregarFlores(floresVisitadas);
                this.posX = f.getPosX();
                this.posY = f.getPosY();
                
                return 1;
            }
        }
        return 0;
    }
    //------------------------------------------
    //------------CODIGO TEMPORAL---------------

    public float getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
    
    public Abeja(){
        //Generación random de datos
        id= "AB-0-" + count;
        count++;
        this.orden = (int)(Math.random() * 3);
        //System.out.println(this.orden);
        this.distMax  = (int) (Math.random() * (100 - 10) + 10);
        this.angulo= (int) (Math.random() * (25 - 10) + 25);
        this.colorFav = (int) (Math.random() * 7);
        this.direccionFav = Direccion.getDireccion((int) Math.random() * 7);
        this.generacion = 0;
        
        if( (int)(Math.random() * 2)==1){seAleja=true;}
        else{seAleja=false;}
                
        
        /*if((int)(Math.random()*1) == 0){
           posX = puntoInicioX;
           posY = puntoInicioY;
        }
        else{getRadomPos();}
        this.ancestros = null;*/   
    }
    
    public Abeja(String ADN, Abeja ancestro1, Abeja ancestro2){
        // ADN = color + dir + orden + distMax 
        colorFav = Integer.parseInt(ADN.substring(0, 3), 2);
        direccionFav = Direccion.getDireccion( Integer.parseInt(ADN.substring(3, 6), 2) );
        orden = Integer.parseInt(ADN.substring(6, 8), 2);
        distMax = Integer.parseInt(ADN.substring(8, 16), 2);
        //System.out.println(ADN.length());
        /*posX = puntoInicioX  = Integer.parseInt(ADN.substring(16, 24), 2);
        posY = puntoInicioY = Integer.parseInt(ADN.substring(24, 32), 2);*/
        this.generacion = ancestro1.generacion + 1;
        ancestros = new Abeja[]{ancestro1, ancestro2};
        if(this.generacion > globalgen){
            count = 0; globalgen++;
        }
        id = "AB-" + globalgen + "-" + count;
        count++;
    }

    public String getId() {
        return id;
    }
    
    
    public Abeja(int ColorFav, Direccion DirecciónFav, int orden, int distMax, Abeja ancestro1, Abeja ancestro2, int generacion){
        this.colorFav = colorFav;
        this.direccionFav = direccionFav;
        this.orden = orden;
        this.distMax = distMax;
        this.ancestros[1] = ancestro1;
        this.ancestros[2] = ancestro2;
    }
    
    public void recorrerCampo(){
        //------------CODIGO TEMPORAL---------------
         int k = busquedaTemporal();
        //------------CODIGO TEMPORAL---------------
        double dist = Math.sqrt((double)( (posX - Main.TamanioCampo/2)^2 + (posY - Main.TamanioCampo/2)^2 ));
        //System.out.println(posX + "" + posY);
        if(k == 1 && dist <= distMax && distanciaRecorrida <= (distMax^2)){recorrerCampo();}
    }
    
    public void agregarFlor(Flor f){
        floresVisitadas.add(f);
    }
    
    public double getFactorMutacion() {
        return factorMutacion;
    }

    public ArrayList<Flor> getFloresVisitadas() {
        return floresVisitadas;
    }

    public void setFactorMutacion(double factorMutacion) {
        this.factorMutacion = factorMutacion;
    }
    
    public boolean isMutada(){return mutada;}
    
    public void setMutada(boolean isMutada){mutada = isMutada;}
    
    public int getAngulo() {return angulo;}
    
    public int getGeneracion() {
        return generacion;
    }
    
    public void buscarFlor(){
        
    }
    
    public int getColorFav() {
        return colorFav;
    }

    public void setColorFav(int colorFav) {
        this.colorFav = colorFav;
    }

    public Direccion getDireccionFav() {
        return direccionFav;
    }

    public void setDireccionFav(Direccion direccionFav) {
        this.direccionFav = direccionFav;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getDistMax() {
        return distMax;
    }

    public void setDistMax(int distMax) {
        this.distMax = distMax;
    }

    public Abeja[] getAncestros() {
        return ancestros;
    }

    public void setAncestros(Abeja[] ancestros) {
        this.ancestros = ancestros;
    }
    
    public Abeja cruzarAbeja(Abeja abeja2 ){
        //obtiene los ADN
        float floresVisitadas = 0;
        String ADN1 = this.getADN();
        String ADN2 = abeja2.getADN();
        //Posición de corte random
        int corte = (int) (Math.random() * 15);
        //Obtiene las partes del ADN
        String parte1 = ADN1.substring(0, corte);
        String parte2 = ADN2.substring(corte, 16);
        //Lo reconvina
        String nuevoADN = parte1 + parte2;
         //System.out.println(nuevoADN);
         //genera la mutación
         boolean muta = false;
        if((Math.random()* 1) < factorMutacion){
            //System.out.println("La abeja ha mutado");
           muta = true;
           int indice = (int)(Math.random()* 15 + 1);
           char mutacion = (nuevoADN.charAt(indice) == '0')? '1':'0';
           String mit1 = nuevoADN.substring(0, indice );
           String mit2 = nuevoADN.substring(indice + 1 , nuevoADN.length() );
           nuevoADN = mit1 + mutacion + mit2; 
        }
        Abeja a = new Abeja(nuevoADN, this, abeja2);
        a.setMutada(muta);
        this.floresVisitadas = null;
        return a;
    }
    
    public String getADN(){
       // ADN = color + dir + orden + distMax + inicioX + inicioY
       //Convierte el número a binario
       String bColor = Integer.toBinaryString(colorFav);
       //Rellena de 0 para que la cantidad de bits sea constante
        if(bColor.length() < 3){
           int dif = 3 - bColor.length();
           for(int i = 0; i < dif; i++){
               bColor = "0" + bColor;
           }
        }
       String bDir = Integer.toBinaryString(direccionFav.index);
       if(bDir.length() < 3){
           int dif = 3 - bDir.length();
           for(int i = 0; i < dif; i++){
               bDir = "0" + bDir;
           }
       }
       String bOrd = Integer.toBinaryString(orden);
       if(bOrd.length() < 2){
           int dif = 2 - bOrd.length();
           for(int i = 0; i < dif; i++){
               bOrd = "0" + bOrd;
           }
       }
       String bDist =Integer.toBinaryString(distMax);
       if(bDist.length() < 8){
           int dif = 8 - bDist.length();
           for(int i = 0; i < dif; i++){
               bDir = "0" + bDir;
           }
       }
       
       /*String iniX =Integer.toBinaryString(distMax);
       if(iniX.length() < 8){
           int dif = 8 - iniX .length();
           for(int i = 0; i < dif; i++){
               iniX  = "0" + iniX ;
           }
       }
       
       String iniY =Integer.toBinaryString(distMax);
       if(iniY.length() < 8){
           int dif = 8 - iniY.length();
           for(int i = 0; i < dif; i++){
               iniY = "0" + iniY;
           }
       }*/
       
        String ADN = bColor + bDir + bOrd + bDist; //+ iniX + iniY;
        
        return ADN;
    }
    
    public float CalcularCalificacion(){
        
        puntaje = 0;
        if(distanciaRecorrida != 0){
            this.puntaje = floresVisitadas.size()/distanciaRecorrida * 1000;
        }
        
        return puntaje;
    }

    public float getPuntaje() {
        return puntaje;
    }
    
    //_______________________BUSQUEDA_______________________
    //______________________________________________________
    public int distanciaInt(int x1, int y1, int x2, int y2){
        return (int) Math.round(Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)));
    }
    public double distanciaFloat(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    }

    //__________________________Obtener punto final
    //Norte
    public int pointN(int y, int dist){ return y-dist;}
    //Sur
    public int pointS(int y, int dist){ return y+dist;}
    //Este
    public int pointE(int x, int dist){ return x+dist;}
    //Oeste
    public int pointO(int x, int dist){ return x-dist;}
    
    //Norteste
    public int pointNE(int x, int dist){
        int a=x;
        while(true){
            if(distanciaInt(x,x,a+1,x-((a+1)-x))>dist) return a;
            a+=1;
        }
    }
    //Sureste
    public int pointSE(int x, int dist){
        int a=x;
        while(true){
            if(distanciaInt(x,x,a+1,a+1)>dist) return a;
            a+=1;
        }
    }       
    //Noroeste
    public int pointNO(int x, int dist){
        int a=x;
        while(true){
            if(distanciaInt(x,x,a-1,a-1)>dist) return a;
            a-=1;
        }
    }
    //Suroeste
    public int pointSO(int x, int dist){ 
        int a=x;
        while(true){
            if(distanciaInt(x,x,x-((a+1)-x),a+1)>dist) return a;
            a+=1;
        }
    }     
    //Obtener puntos secundarios
    public int [] getRotatePoint(int pivoteX, int pivoteY, int finalX, int finalY, int angle){
        //System.out.println("ENTRO GETPOINT");
        float newX,newY,x,y;
        x=finalX-pivoteX;y=finalY-pivoteY;
        
        float newAngle = (float) (angle*(Math.PI/180)); 
        float cos=(float) Math.cos(newAngle);
        float sen=(float) Math.sin(newAngle);
        
        newX=(x*cos)-(y*sen);newY=(y*cos)+(x*sen);
        newX+=pivoteX; newY+=pivoteY;
        
        if(newX<0)newX=0;
        else if (newX>100)newX=100;
        
        if(newY<0)newY=0;
        else if (newY>100)newY=100;
         
        int[] posicion = {Math.round(newX), Math.round(newY)}; 
        //System.out.println("TERMINÓ GETPPOINT");
        return posicion;
    }    
    
    private static double calculateAngle(int P1X, int P1Y, int P2X, int P2Y, int P3X, int P3Y){
        double numerator = P2Y*(P1X-P3X) + P1Y*(P3X-P2X) + P3Y*(P2X-P1X); 
        double denominator = (P2X-P1X)*(P1X-P3X) + (P2Y-P1Y)*(P1Y-P3Y); 
        double ratio = numerator/denominator; 
        double angleRad = Math.atan(ratio); 
        double angleDeg = (angleRad*180)/Math.PI; 
        if(angleDeg<0){ 
            angleDeg = 180+angleDeg;
        }else if(angleDeg==0.0){
            angleDeg=180.0;
        } 
        return angleDeg; } 
    
    public static boolean dentro(int fx,int fy,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4){
        double total=0;
        //Triangulo f,1,2
        total+=calculateAngle(fx, fy, x1, y1, x2, y2);//System.out.println(calculateAngle(fx, fy, x1, y1, x2, y2));
        //Triangulo f,2,3
        total+=calculateAngle(fx, fy, x2, y2, x3, y3);//System.out.println(calculateAngle(fx, fy, x2, y2, x3, y3));
        //Triangulo f,3,4
        total+=calculateAngle(fx, fy, x3, y3, x4, y4);//System.out.println(calculateAngle(fx, fy, x3, y3, x4, y4));
        //Triangulo f,4,1
        total+=calculateAngle(fx, fy, x4, y4, x1, y1);//System.out.println(calculateAngle(fx, fy, x4, y4, x1, y1));
        //System.out.println(total);
        if(Double.isNaN(total)){
            return true;
        }
        return total==360.0;
    }
    
    public boolean posibilidad(){return (int) (Math.random() * 100)<5;}//SI VISITA UNA FLOR DE DIFERENTE COLOR
    
    public void recorrido(ArrayList<Integer> posibles){
        
        //System.out.println("ENTRO RECORRIDO");//PRINT DE PRUEBA
        
        boolean derecha=false;
        switch (this.getDireccionFav()) {
            case N: 
                derecha=true;break;
            case S:
                derecha=true;break;
            case E:
                derecha=true;break;
            case NE:
                derecha=true;break;
            case SE:
                derecha=true;break;}
        int cont;
        double dist;
        Flor flower;
        this.posX=this.puntoInicioX;this.posY=this.puntoInicioY;
        if (derecha){
                if (seAleja){
                    cont=0;
                    while (cont<posibles.size()){
                        flower=Main.FloresActuales.get(posibles.get(cont));
                        dist=(distanciaFloat(this.posX,this.posY,flower.getPosX(),flower.getPosY())+this.distanciaRecorrida);
                        if (dist>this.getDistMax()){
                            cont++;continue;
                        }this.distanciaRecorrida=(float) dist;

                        for(Flor a: this.floresVisitadas){flower.agregarFlor(a);}
                        this.agregarFlor(flower);
                        this.posX=flower.getPosX();this.posY=flower.getPosY();
                        Main.FloresActuales.set(posibles.get(cont), flower);
                        cont++;}
                }else{
                    cont=posibles.size()-1;
                    while (cont>-1){
                        flower=Main.FloresActuales.get(posibles.get(cont));
                        dist=(distanciaFloat(this.posX,this.posY,flower.getPosX(),flower.getPosY())+this.distanciaRecorrida);
                        if (dist>this.getDistMax()){
                            cont--;continue;
                        }this.distanciaRecorrida=(float) dist;

                        //for(Flor a: this.floresVisitadas){flower.agregarFlor(a);}
                        //System.out.println(floresVisitadas.size());
                        flower.agregarFlores(floresVisitadas);
                        this.agregarFlor(flower);
                        this.posX=flower.getPosX();this.posY=flower.getPosY();
                        Main.FloresActuales.set(posibles.get(cont), flower);
                        cont--;}   
                }
            }else{
                if (seAleja){                    
                    cont=posibles.size()-1;
                    while (cont>-1){
                        flower=Main.FloresActuales.get(posibles.get(cont));
                        dist=(int) (distanciaFloat(this.posX,this.posY,flower.getPosX(),flower.getPosY())+this.distanciaRecorrida);
                        if (dist>this.getDistMax()){
                            cont--;continue;
                        }this.distanciaRecorrida=(float) dist;

                        for(Flor a: this.floresVisitadas){flower.agregarFlor(a);}
                        this.agregarFlor(flower);
                        this.posX=flower.getPosX();this.posY=flower.getPosY();
                        Main.FloresActuales.set(posibles.get(cont), flower);
                        cont--;}
                }else{
                    cont=0;
                    while (cont<posibles.size()){
                        flower=Main.FloresActuales.get(posibles.get(cont));
                        dist=(distanciaFloat(this.posX,this.posY,flower.getPosX(),flower.getPosY())+this.distanciaRecorrida);
                        if (dist>this.getDistMax()){
                            cont++;continue;
                        }this.distanciaRecorrida=(float) dist;
                        for(Flor a: this.floresVisitadas){flower.agregarFlor(a);}
                        this.agregarFlor(flower);
                        this.posX=flower.getPosX();this.posY=flower.getPosY();
                        Main.FloresActuales.set(posibles.get(cont), flower);
                        cont++;}     
                }
            }
        //System.out.println("TERMINÓ RECORRIDO");//PRINT DE PRUEBA
    }
    
    public void busqueda(){
        //Obtener el punto max
        //Punto max
        
        //System.out.println("ENTRO BUSQUEDA");//PRINT DE PRUEBA
        
        int maxX=0,maxY=0;
        //MasAngle
        int masX=0, masY=0;  
        //MenosAngle
        int menosX=0,menosY=0;
        
        switch (this.getDireccionFav()) {
            case N: 
                maxX=this.puntoInicioX;maxY=pointN(this.puntoInicioY,this.distMax);break;
            case S:
                maxX=this.puntoInicioX;maxY=pointS(this.puntoInicioY,this.distMax);break;
            case E:
                maxY=this.puntoInicioY;maxX=pointE(this.puntoInicioX,this.distMax);break;
            case O:
                maxY=this.puntoInicioY;maxX=pointO(this.puntoInicioX,this.distMax);break;
            case NE:
                maxX=pointNE(this.puntoInicioX,this.distMax);maxY=(this.puntoInicioX-(maxX-this.puntoInicioX));break;
            case SE:
                maxX=maxY=pointSE(this.puntoInicioX,this.distMax);break;
            case NO:
                maxX=maxY=pointNO(this.puntoInicioX,this.distMax);
                break;
            case SO:
                maxY=pointNE(this.puntoInicioY,this.distMax);maxX=this.puntoInicioY-(maxY-this.puntoInicioY);break;
        }
        
        ArrayList<Integer> posibles = new ArrayList<>();
        
        masX=getRotatePoint(this.puntoInicioX, this.puntoInicioY, maxX, maxY, this.getAngulo())[0];
        masY=getRotatePoint(this.puntoInicioX, this.puntoInicioY, maxX, maxY, this.getAngulo())[1];
        menosX=getRotatePoint(this.puntoInicioX, this.puntoInicioY, maxX, maxY, -this.getAngulo())[0];
        menosY=getRotatePoint(this.puntoInicioX, this.puntoInicioY, maxX, maxY, -this.getAngulo())[1];
        Integer a;
        
        if (this.getOrden()!=2){
            if (this.orden==0){//Anchura
                for(Flor flower: Main.FloresActuales){
                    if(dentro(flower.getPosX(),flower.getPosY(),this.puntoInicioX,this.puntoInicioY,
                    masX,masY,maxX,maxY,menosX,menosY) && (flower.getColor()==this.getColorFav()||posibilidad())){
                        
                        /* SABER SI ENTRÓ UNA FLOR QUE NO ES DEL MISMO COLOR
                        if(flower.getColor()!=this.getColorFav()){System.out.println("NO ES DEL MISMO COLOR");}//*/
                        
                        if(posibles.isEmpty()){posibles.add(Main.FloresActuales.indexOf(flower)); continue;}
                        for (int i = 0; i < posibles.size(); i++) {
                            a=posibles.get(i);
                            if(flower.getPosY()<Main.FloresActuales.get(a).getPosY()){
                                posibles.add(posibles.indexOf(a), Main.FloresActuales.indexOf(flower));
                            }else posibles.add(posibles.indexOf(a)+1, Main.FloresActuales.indexOf(flower));
                            break;}  
                    }}    
            }else{//Profundidad
                for(Flor flower: Main.FloresActuales){
                    if(dentro(flower.getPosX(),flower.getPosY(),this.puntoInicioX,this.puntoInicioY,
                    masX,masY,maxX,maxY,menosX,menosY) && (flower.getColor()==this.getColorFav()||posibilidad())){
                       
                        /* SABER SI ENTRÓ UNA FLOR QUE NO ES DEL MISMO COLOR
                        if(flower.getColor()!=this.getColorFav()){System.out.println("NO ES DEL MISMO COLOR");}//*/
                        
                        if(posibles.isEmpty()){posibles.add(Main.FloresActuales.indexOf(flower)); continue;}
                        for (int i = 0; i < posibles.size(); i++) {
                            a=posibles.get(i);
                            if(flower.getPosX()<Main.FloresActuales.get(a).getPosX()){
                                posibles.add(posibles.indexOf(a), Main.FloresActuales.indexOf(flower));
                            }else posibles.add(posibles.indexOf(a)+1, Main.FloresActuales.indexOf(flower));
                            break;}       
                    }}
            }            
        }else{
            int x,y;
            for (int i = 0; i < 10; i++) {
                x=(int)(Math.random() * Main.FloresActuales.size());
                if(dentro(Main.FloresActuales.get(x).getPosX(),Main.FloresActuales.get(x).getPosY(),
                this.puntoInicioX,this.puntoInicioY,masX,masY,maxX,maxY,menosX,menosY)){posibles.add(x);} 
            }
        }
        /* PRINTS PARA PRUEBA
        System.out.println("TERMINÓ BUSQUEDA");
        System.out.println(posibles.size());//*/
        recorrido(posibles);
    }

    @Override
    public String toString() {
        return "Abeja{" + "\n ID=" + id  + "\n Generacion=" + generacion + "\n colorFav=" + colorFav + "\n,direccionFav=" + direccionFav + "\n,orden=" + orden + "\n,distMax=" + distMax + "\n,ancestros=" + ancestros + "\n,Mutada=" + mutada  +"\n,Flores visitadas=" + floresVisitadas.size() + "\n}";
    }
    
    
    
    
    
}
