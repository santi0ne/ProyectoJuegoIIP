/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import ec.edu.espol.tda.DecisionTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class VentanaJuegoController {

    @FXML
    private Label pregunta;
    @FXML
    private Button respSi;
    @FXML
    private Button respNo;
    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private ImageView icon;
    @FXML
    private Label txtTitle;
    
    static private ArrayList<String> respuestas = new ArrayList<>(); 
    DecisionTree<String> arbolPregunta;
    DecisionTree<String> preguntaActual;
    static List<String> listaPreguntas= new ArrayList<>();
    

    public void initialize() throws IOException {
        
        respuestas.removeAll(respuestas);
        
        listaPreguntas=listaPreguntas.subList(0, InicioController.numPreguntasSelecc);
        
        arbolPregunta=new DecisionTree<>(listaPreguntas.get(0));
        listaPreguntas.remove(listaPreguntas.get(0));
        
        
        for(String pregunta: listaPreguntas){
            arbolPregunta=arbolPregunta.insertar(pregunta, arbolPregunta);
        }
        
        preguntaActual=arbolPregunta;
        pregunta.setText(arbolPregunta.data);
        
        cerrarVentana();
        moverVentana();
        miniVentana();
        colocarImagenBoton();
    }  

    public static ArrayList<String> getRespuestas() {
        return respuestas;
    }
    
    
    @FXML
    public void respuestaSi() throws IOException{
        
        if(preguntaActual.izquierdo== null || preguntaActual.derecho==null){
           respuestas.add("si"); 
           App.setRoot("resultados");
           
        }
       
        else{
            respuestas.add("si");
            preguntaActual=preguntaActual.izquierdo;
            pregunta.setText(preguntaActual.data);
        }   

    }
    
    @FXML
    public void respuestaNo() throws IOException{
        
        if(preguntaActual.izquierdo== null || preguntaActual.derecho==null){
           respuestas.add("no"); 
           App.setRoot("resultados");
        }
       
        else{
            respuestas.add("no");
            preguntaActual=preguntaActual.derecho;
            pregunta.setText(preguntaActual.data);
        }  
        
    }
    
    public void cerrarVentana(){
        close.setOnMouseClicked(e->{
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Ya te vas??");
        alerta.setHeaderText("Estás seguro de salir del juego?");
        alerta.setContentText("Nos estamos divirtiendo mucho :(");
        Optional<ButtonType> result=alerta.showAndWait();
         
        if(result.get()==ButtonType.OK){
        Stage stage = (Stage) this.close.getScene().getWindow();
        stage.close();
        }});
    }
    
    public void moverVentana(){
        txtTitle.setOnMouseDragged(eh->{
            Stage stage = (Stage) this.txtTitle.getScene().getWindow();
            stage.setX(eh.getScreenX());
            stage.setY(eh.getScreenY());
        });
    }
    
    public void miniVentana(){
        min.setOnMouseClicked(eh->{
            Stage stage = (Stage) this.txtTitle.getScene().getWindow();
            stage.setIconified(true);
        });
    }
    
    public void colocarImagenBoton(){
        URL linkSi = getClass().getResource("/ec/edu/espol/images/me-gusta.png");
        URL linkNo = getClass().getResource("/ec/edu/espol/images/disgusto.png");
        Image imgSi = new Image(linkSi.toString(), 50, 50, false, true);
        Image imgNo = new Image(linkNo.toString(), 50, 50, false, true);
        respSi.setGraphic(new ImageView(imgSi));
        respNo.setGraphic(new ImageView(imgNo));
    }
    
}
