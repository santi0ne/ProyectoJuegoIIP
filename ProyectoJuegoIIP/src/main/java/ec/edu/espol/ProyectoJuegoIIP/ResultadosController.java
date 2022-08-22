/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ProyectoJuegoIIP;

import java.io.BufferedReader;
import java.io.File;
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
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
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
    private ImageView imagenAnimal;
    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private ImageView icon;
    @FXML
    private Label txtTitle;
    @FXML
    private TilePane animalesEncontrados;
    @FXML
    private Label respuestaTitulo;

    
    private ArrayList<ArrayList<String>> listaTodasRespuestas= new ArrayList<>();
    

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
            
            if(InicioController.numPreguntasSelecc.equals(arr.size())){
                if(arrResp.equals(respuestas)){
                    Label animal=new Label(arr.get(0));
                    animal.setFont(new Font("System",18));
                    animalesEncontrados.getChildren().add(animal);
                    
                  
                    URL link = getClass().getResource("/ec/edu/espol/images/animales/Tortuga.png");
                    Image img = new Image(link.toString(), 50, 50, false, true);
                    imagenAnimal.setImage(img);
                    
                    encontrado=true;
       

                } 
            }
            
            else{
                
                if(arr.subList(1,InicioController.numPreguntasSelecc+1).equals(respuestas)){
                    Label animal=new Label(arr.get(0));
                    animal.setFont(new Font("System",18));
                    animalesEncontrados.getChildren().add(animal);
                    encontrado=true;

                } 
                
            }
        }
        
        if(animalesEncontrados.getChildren().size()==0){
            respuestaTitulo.setText("Preguntas agotadas!!!");
            Label lb=new Label("No pude adivinar el animal que tienes en mente");
            lb.setFont(new Font("System",24));
            lb.setStyle("-fx-font-weight: bold");
            animalesEncontrados.getChildren().add(lb);
        }
        
        if(animalesEncontrados.getChildren().size()>1){
            respuestaTitulo.setText("Preguntas agotadas!!! Lista de posibles respuestas:");
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
        volverJugar.setGraphic(new ImageView(imgHouse));
    }
    
}
