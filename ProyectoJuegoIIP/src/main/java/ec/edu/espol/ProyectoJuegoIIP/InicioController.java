/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import static ec.edu.espol.ProyectoJuegoIIP.VentanaJuegoController.listaPreguntas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class InicioController  {

    @FXML
    private Button btonSi;
    @FXML
    private Button btonNo;
    @FXML
    private Spinner<Integer> numPreguntas;
    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private Label txtTitle;
    @FXML
    private ImageView icon;
    @FXML
    private Button cargaArchivos;
    
    static Integer numPreguntasSelecc;
    int numPreguntasCargadas;
   

 
    public void initialize() {
        
        listaPreguntas.removeAll(listaPreguntas);
        numPreguntasCargadas=0;
        
        int contador=0;
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/preguntas.txt"))){
            String linea;
            while((linea=bufferedReader.readLine())!=null){
                VentanaJuegoController.listaPreguntas.add(linea);    
                contador++;
                }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
        
        numPreguntasCargadas=VentanaJuegoController.listaPreguntas.size();
        
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, numPreguntasCargadas);
        intFactory.setValue(numPreguntasCargadas);
        numPreguntas.setValueFactory(intFactory);
        cerrarVentana();
        moverVentana();
        miniVentana();
    } 
    
    

    @FXML
    private void botonSi() throws IOException {
        numPreguntasSelecc=numPreguntas.getValue();
        App.setRoot("ventanaJuego");
    }

    @FXML
    private void botonNo() {
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Ya te vas??");
        alerta.setHeaderText("Estás seguro de salir del juego?");
        alerta.setContentText("Puedes empezar el juego cuando estés listo");
        Optional<ButtonType> result=alerta.showAndWait();
        
        if(result.get()==ButtonType.OK){
            Stage stage = (Stage) this.btonNo.getScene().getWindow();
            stage.close();
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
    
    public void cargarArchivos() throws IOException{
        App.setRoot("cargaArchivos");
    }
}
