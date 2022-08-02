/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.proyectosegundoparcial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author g_are
 */
public class Main {
    
    static int i=0;
    
    public static void cargarPreguntas(ArrayList<String> pregunta, AVL<String> arbol){
            arbol.data=pregunta.get(i);
            i++;
            if(arbol.izquierdo!=null) cargarPreguntas(pregunta,arbol.izquierdo);
            if(arbol.derecho!=null) cargarPreguntas(pregunta,arbol.derecho);    
    }
    
    static Scanner sc=new Scanner(System.in);
    
    static ArrayList<String> respuestas=new ArrayList<>();
    
    public static void mostrarPreguntas(AVL<String> arbol){
    
        System.out.println("\n"+arbol.data);
        
        String respuesta="";
        while(!respuesta.toLowerCase().equals("si") && !respuesta.toLowerCase().equals("no")){
            System.out.println("Conteste si o no");
            respuesta=sc.nextLine();
        }
        
        
        if(arbol.izquierdo!= null || arbol.derecho!=null){

            if(respuesta.toLowerCase().equals("si")){
                respuestas.add("si");
                mostrarPreguntas(arbol.izquierdo);            
            }
        
            else if(respuesta.toLowerCase().equals("no")){
                respuestas.add("no");
                mostrarPreguntas(arbol.derecho);    
            } 
        }
        
        else{
            respuestas.add(respuesta.toLowerCase());
            System.out.println("Preguntas terminadas \n");
            obtenerRespuestas(respuestas);
        }
        
    }

    public static void obtenerRespuestas(ArrayList<String> respuestas){
        
        ArrayList<ArrayList<String>> listaRespuestas= new ArrayList<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/respuestas.txt"))){
            String linea;
            while((linea=bufferedReader.readLine())!=null){
                
                String[] texto=linea.split(";");
                String[] resp=texto[1].split(",");
                
                ArrayList<String> arr=new ArrayList<>();
                arr.add(texto[0]);
                for(String s:resp){
                    arr.add(s);
                }
                listaRespuestas.add(arr);
            }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
        
        boolean encontrado=false;
        
        for(ArrayList<String> arr:listaRespuestas){
            List<String> arrResp=arr.subList(1,arr.size());
;
            if(arrResp.equals(respuestas)){
                System.out.println("El animal en el que has pensado es "+arr.get(0));
                encontrado=true;
                
            }
            
        }
        
        if(encontrado=false){
            System.out.println("Animal no encontrado");  
        }
        
    }
    
    public static void main(String[] args){
         
        ArrayList<String> listaPreguntas= new ArrayList<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/preguntas.txt"))){
            String linea;
            while((linea=bufferedReader.readLine())!=null){
                listaPreguntas.add(linea);
                }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
    
        
    
         
        AVL<String> arbol= new AVL<>("1");
        arbol.izquierdo=new AVL<>("2");
        arbol.izquierdo.izquierdo=new AVL<>("3");
        arbol.izquierdo.derecho=new AVL<>("4");
        arbol.izquierdo.derecho.izquierdo=new AVL<>("5");
        arbol.izquierdo.derecho.derecho=new AVL<>("6");
        arbol.derecho=new AVL<>("7");
        arbol.derecho.izquierdo=new AVL<>("8");
        
        int i=0;
         
        cargarPreguntas(listaPreguntas, arbol);
         
        mostrarPreguntas(arbol);
        
         
         
         
     }
    
}
