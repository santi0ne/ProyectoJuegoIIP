/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class ResultadosController{

    @FXML
    private Button volverJugar;
    @FXML
    private Label respuesta;
    @FXML
    private ImageView imagenAnimal;
    
    private ArrayList<ArrayList<String>> listaTodasRespuestas= new ArrayList<>();
    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private ImageView icon;
    @FXML
    private Label txtTitle;


    public void initialize() {
        
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
        
        obtenerRespuestas(VentanaJuegoController.getRespuestas());
        cerrarVentana();
        moverVentana();
        miniVentana();
        colocarImagenBoton();
    } 
    
    public void obtenerRespuestas(ArrayList<String> respuestas){
        
        boolean encontrado=false;

        for(ArrayList<String> arr:listaTodasRespuestas){
            List<String> arrResp=arr.subList(1,arr.size());
            if(arrResp.equals(respuestas)){
                respuesta.setText(arr.get(0));
                encontrado=true;
                
            }     
        }
        
        if(encontrado==false){
            respuesta.setText("Animal no encontrado");  
        }
        
    }
    
    
    @FXML
    public void reiniciar() throws IOException{
        App.setRoot("inicio");
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
    
    public void colocarImagenBoton(){
        URL linkHouse = getClass().getResource("/ec/edu/espol/images/casa.png");
        Image imgHouse = new Image(linkHouse.toString(), 50, 50, false, true);
        volverJugar.setGraphic(new ImageView(imgHouse));
    }
    
}
