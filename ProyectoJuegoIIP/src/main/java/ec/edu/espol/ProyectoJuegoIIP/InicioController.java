/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

 
    public void initialize() {
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        intFactory.setValue(20);
        numPreguntas.setValueFactory(intFactory);
      
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
    
}
