/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void abrirConfiguracionDelSistema() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ConfiguracionDelSistema.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionRolesPermisos() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionRolesPermisos.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionDepartamento() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionDepartamento.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionUsuarios() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionUsuarios.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionEstadosTicket() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionEstadosTicket.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionFlujosDeTrabajo() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionFlujosDeTrabajo.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    private void abrirGestionTicket() throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GestionarTicket.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    
    
}
