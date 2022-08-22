/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.IOException;
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
    
    static Integer numPreguntasSelecc;

 
    public void initialize() {
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9);
        intFactory.setValue(9);
        numPreguntas.setValueFactory(intFactory);
        cerrarVentana();
        moverVentana();
        miniVentana();
    } 

    public static Integer getNumPreguntas() {
        return numPreguntasSelecc;
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
}
