/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
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
public class CargaArchivosController{

    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private ImageView icon;
    @FXML
    private Label txtTitle;
    @FXML
    private Button btonCancelar;
    @FXML
    private Button cargar;
    @FXML
    private Button cargaPreguntas;
    @FXML
    private Button cargaRespuestas;
    @FXML
    private Label nombrePreg;
    @FXML
    private Label nombreResp;
    
    String rutaPreg=null;
    String rutaResp=null;
    

    public void initialize() {
        cerrarVentana();
        moverVentana();
        miniVentana();
        colocarImagenBoton();
    } 
    
    @FXML
    public void cargarPregunta(){
        Scanner entrada = null;
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter(".txt ", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.showOpenDialog(fileChooser);
        try {
            rutaPreg = fileChooser.getSelectedFile().getAbsolutePath();
            nombrePreg.setText("Archivo seleccionado: "+rutaPreg);
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        
        }
        
    }
    
    @FXML
    public void cargarRespuesta(){
        
        Scanner entrada = null;
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter(".txt ", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.showOpenDialog(fileChooser);
        try {
            rutaResp = fileChooser.getSelectedFile().getAbsolutePath(); 
            nombreResp.setText("Archivo seleccionado: "+rutaResp);
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        
        }
        
    }
    
    @FXML
    public void cargarArchivos(){
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Estás seguro de continuar??");
        alerta.setHeaderText("Asegúrate de comprobar el formato de los archivos");
        alerta.setContentText("Para que no existan errores en el juego");
        Optional<ButtonType> result=alerta.showAndWait();
        
        if(result.get()==ButtonType.OK){
        
            Path path1p=Paths.get(rutaPreg);
            Path path2p=Paths.get("archivos//preguntas.txt");

            try {
                Files.copy(path1p, path2p, REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Path path1r=Paths.get(rutaResp);
            Path path2r=Paths.get("archivos//respuestas.txt");

            try {
                Files.copy(path1r, path2r, REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            try {
                App.setRoot("inicio");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }
    }
     
    @FXML
    public void reiniciar() throws IOException{
        App.setRoot("inicio");
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
        URL linkHouse = getClass().getResource("/ec/edu/espol/images/casa.png");
        Image imgHouse = new Image(linkHouse.toString(), 50, 50, false, true);
        btonCancelar.setGraphic(new ImageView(imgHouse));
    }
    
}
