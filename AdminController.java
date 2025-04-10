/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    public javafx.scene.control.Button gestionRolesPermisos;
    public javafx.scene.control.Button gestionDepartamentos;
    public javafx.scene.control.Button gestionUsuarios;
    public javafx.scene.control.Button gestionEstadosTicket;
    public javafx.scene.control.Button gestionFlujosTrabajo;
    public javafx.scene.control.Button gestionTickets;
            
    @FXML
    private void configuracionDelSistema() throws IOException{
        abrir.abrirVentana("ConfiguracionDelSistema.fxml");
        cerrar.cerrar(configuracionDelSistema);
    }
    
    @FXML
    private void gestionRolesPermisos() throws IOException{
        abrir.abrirVentana("GestionRolesPermisos.fxml");
        cerrar.cerrar(gestionRolesPermisos);
    }
    
    @FXML
    private void gestionDepartamentos() throws IOException{
        abrir.abrirVentana("GestionDepartamento.fxml");
        cerrar.cerrar(gestionDepartamentos);
    }
    
    @FXML
    private void gestionUsuarios() throws IOException{
        abrir.abrirVentana("GestionUsuarios.fxml");
        cerrar.cerrar(gestionUsuarios);
    }
    
    @FXML
    private void gestionEstadosTicket() throws IOException{
        abrir.abrirVentana("GestionEstadosTicket.fxml");
        cerrar.cerrar(gestionEstadosTicket);
    }
    
    @FXML
    private void gestionFlujosTrabajo() throws IOException{
        abrir.abrirVentana("GestionFlujosDeTrabajo.fxml");
        cerrar.cerrar(gestionFlujosTrabajo);
    }
    
    @FXML
    private void gestionTickets() throws IOException{
        abrir.abrirVentana("GestionarTicket.fxml");
        cerrar.cerrar(gestionTickets);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
        
}
