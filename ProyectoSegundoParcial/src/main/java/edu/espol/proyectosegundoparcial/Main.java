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
        
        boolean encontrado=false;
        
        ArrayList<ArrayList<String>> listaTodasRespuestas= new ArrayList<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/respuestas.txt"))){
            String linea;
            while((linea=bufferedReader.readLine())!=null){
                
                
                String[] resp=linea.split(",");
                
                ArrayList<String> arrRespAnimal=new ArrayList<>();
                for(String s:resp){
                    arrRespAnimal.add(s);
                }
                listaTodasRespuestas.add(arrRespAnimal);
            }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
        
        
        for(ArrayList<String> arr:listaTodasRespuestas){
            List<String> arrResp=arr.subList(1,arr.size());
;
            if(arrResp.equals(respuestas)){
                System.out.println("El animal en el que has pensado es "+arr.get(0));
                encontrado=true;
                
            }
            
        }
        
        if(encontrado==false){
            System.out.println("Animal no encontrado");  
        }
        
    }
    
    public static void main(String[] args){
         
        ArrayList<AVL<String>> listaPreguntas= new ArrayList<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/preguntas.txt"))){
            String linea;
            while((linea=bufferedReader.readLine())!=null){
                String[] preguntas=linea.split(",");
                
                AVL<String> newArbol= new AVL<>(preguntas[1]);
                newArbol.numPosicion=Integer.valueOf(preguntas[0]);
                
                listaPreguntas.add(newArbol);
                        
                }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
        
        
        AVL<String> arbol=new AVL<>(listaPreguntas.get(0).data);
        arbol.numPosicion=listaPreguntas.get(0).numPosicion;
        List<AVL<String>> listaPregunta= listaPreguntas.subList(1, listaPreguntas.size());
        
        
        for(AVL<String> arb: listaPregunta){
         
            arbol=arbol.insertar(arb, arbol);
        
        }
        
        mostrarPreguntas(arbol);
            
     }
    
}
