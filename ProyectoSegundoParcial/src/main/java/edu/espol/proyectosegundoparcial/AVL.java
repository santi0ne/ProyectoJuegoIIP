/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package edu.espol.proyectosegundoparcial;

/**
 *
 * @author Guillermo Arévalo
 * @param <E>
 */
public class AVL<E>{
    
       public E data;
       public AVL<E>  izquierdo,derecho;

       public AVL(E data){
       this.data=data;
       izquierdo=derecho=null;
       }
       
       
       
       public boolean estaVacio(){
           if(data==null) return true;
           if(izquierdo==null && derecho==null) return true;
       
           return false;
        }
       
       public void recorridoInfijo(){
            if(izquierdo!=null) izquierdo.recorridoInfijo();
            System.out.println(data);
            if(derecho!=null) derecho.recorridoInfijo();
        }
       
       
       
       public void recorridoPosfijo(){
            if(izquierdo!=null) izquierdo.recorridoPosfijo();
            if(derecho!=null) derecho.recorridoPosfijo();
            System.out.println(data);
        }
       
       


    
           
     
      
         
}
