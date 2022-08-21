/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.IOException;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

 
    public void initialize() {
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        intFactory.setValue(20);
        numPreguntas.setValueFactory(intFactory);
        cerrarVentana();
        moverVentana();
        miniVentana();
    }    

    @FXML
    private void botonSi() throws IOException {
         App.setRoot("ventanaJuego");
    }

    @FXML
    private void botonNo() {
        Stage stage = (Stage) this.btonNo.getScene().getWindow();
        stage.close();
    }
    
    public void cerrarVentana(){
        close.setOnMouseClicked(e->{
            Stage stage = (Stage) this.close.getScene().getWindow();
            stage.close();
        });
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
