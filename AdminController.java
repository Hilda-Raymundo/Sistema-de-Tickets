/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button configuracionDelSistema;
    public javafx.scene.control.Button abrirGestionRolesPermisos;
    public javafx.scene.control.Button abrirGestionDepartamento;
    public javafx.scene.control.Button abrirGestionUsuarios;
    public javafx.scene.control.Button abrirGestionEstadosTicket;
    public javafx.scene.control.Button abrirGestionFlujosDeTrabajo;
    public javafx.scene.control.Button abrirGestionDeTicket;
        
    @FXML
    private void abrirConfiguracionDelSistema() throws IOException{
        abrir.abrirVentana("ConfiguracionDelSistema.fxml");
        cerrar.cerrar(configuracionDelSistema);
    }
    
    @FXML
    private void abrirGestionRolesPermisos() throws IOException{
        abrir.abrirVentana("GestionRolesPermisos.fxml");
        cerrar.cerrar(abrirGestionRolesPermisos);
    }
    
    @FXML
    private void abrirGestionDepartamento() throws IOException{
        abrir.abrirVentana("GestionDepartamento.fxml");
        cerrar.cerrar(abrirGestionDepartamento);
    }
    
    @FXML
    private void abrirGestionUsuarios() throws IOException{
        abrir.abrirVentana("GestionUsuarios.fxml");
        cerrar.cerrar(abrirGestionUsuarios);
    }
    
    @FXML
    private void abrirGestionEstadosTicket() throws IOException{
        abrir.abrirVentana("GestionEstadosTicket.fxml");
        cerrar.cerrar(abrirGestionEstadosTicket);
    }
    
    @FXML
    private void abrirGestionFlujosDeTrabajo() throws IOException{
        abrir.abrirVentana("GestionFlujosDeTrabajo.fxml");
        cerrar.cerrar(abrirGestionFlujosDeTrabajo);
    }
    
    @FXML
    private void abrirGestionDeTicket() throws IOException{
        abrir.abrirVentana("GestionarTicket.fxml");
        cerrar.cerrar(abrirGestionDeTicket);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
        
}
