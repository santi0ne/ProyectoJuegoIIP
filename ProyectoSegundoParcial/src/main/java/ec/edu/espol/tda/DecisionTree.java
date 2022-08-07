/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ec.edu.espol.tda;

import java.util.Comparator;

/**
 *
 * @author Guillermo Arévalo
 * @param <E>
 */
public class DecisionTree<E> implements Comparator{
    
       public E data;
       public DecisionTree<E>  izquierdo,derecho;
       public int numPosicion;

       public DecisionTree(E data){
       this.data=data;
       izquierdo=derecho=null;
       }
       
       @Override
        public int compare(Object o1, Object o2) {
        
            DecisionTree<String> arbol1=(DecisionTree<String>) o1;
            DecisionTree<String> arbol2=(DecisionTree<String>) o2;

            if(arbol1.numPosicion<arbol2.numPosicion) return -1;

            else if (arbol1.numPosicion>arbol2.numPosicion) return 1;

            else  return 0;
        }

       
       public void recorridoPrefijo(){
            System.out.println(data);
            if(izquierdo!=null) izquierdo.recorridoPrefijo();
            if(derecho!=null) derecho.recorridoPrefijo();    
        }
       
       
       public static DecisionTree<String> insertar(DecisionTree<String> pregunta, DecisionTree<String> arbol){
            
           if(arbol.data==null) return arbol;
           
           if(pregunta.data.equals(arbol.data)){
               System.out.println("El elemento ya está insertado");
               return arbol;
           }
           
        
            if(arbol.compare(pregunta, arbol)==-1){
               if(arbol.izquierdo==null){
                  arbol.izquierdo=pregunta;   
               }
               
               else{
                  arbol.izquierdo=insertar(pregunta,arbol.izquierdo);
               }    
            }
            
            if(arbol.compare(pregunta, arbol)==1){
               if(arbol.derecho==null){
                  arbol.derecho=pregunta; 
    
               }
               
               else{
                  arbol.derecho=insertar(pregunta,arbol.derecho); 
               }    
            }
            return arbol;
        }

        
              
}
