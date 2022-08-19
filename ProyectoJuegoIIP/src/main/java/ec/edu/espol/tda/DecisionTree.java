/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ec.edu.espol.tda;

/**
 *
 * @author Guillermo Arévalo
 * @param <E>
 */
public class DecisionTree<E> {
    
       public E data;
       public DecisionTree<E>  izquierdo,derecho;
       public int numPosicion;

       public DecisionTree(E data){
       this.data=data;
       izquierdo=derecho=null;
       }
         
       public void recorridoPrefijo(){
            System.out.println(data);
            if(izquierdo!=null) izquierdo.recorridoPrefijo();
            if(derecho!=null) derecho.recorridoPrefijo();    
        }
       
       
       public static DecisionTree<String> insertar(String pregunta, DecisionTree<String> raiz){
        
        if(raiz.derecho==null || raiz.izquierdo==null){
            raiz.derecho=new DecisionTree<>(pregunta);
            raiz.izquierdo=new DecisionTree<>(pregunta);
        }
        
        else{
            raiz.derecho=insertar(pregunta,raiz.derecho);
            raiz.izquierdo=insertar(pregunta,raiz.izquierdo);
        }
        
        return raiz;
    }

        
              
}
