/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    OperacionesVentana operacion = new OperacionesVentana();
    conection conectar = new conection();
    
    public javafx.scene.control.Button configuracionDelSistema;
    public javafx.scene.control.Button gestionRolesPermisos;
    public javafx.scene.control.Button gestionDepartamentos;
    public javafx.scene.control.Button gestionUsuarios;
    public javafx.scene.control.Button gestionEstadosTicket;
    public javafx.scene.control.Button gestionFlujosTrabajo;
    public javafx.scene.control.Button gestionTickets;
    public javafx.scene.control.Button salir;
            
    @FXML
    private void configuracionDelSistema() throws IOException{
         operacion.abrirVentana("ConfiguracionDelSistema.fxml");
         operacion.cerrar(configuracionDelSistema);
    }
    
    @FXML
    private void gestionRolesPermisos() throws IOException{
        operacion.abrirVentana("GestionRolesPermisos.fxml");
        operacion.cerrar(gestionRolesPermisos);
    }
    
    @FXML
    private void gestionDepartamentos() throws IOException{
        operacion.abrirVentana("GestionDepartamento.fxml");
        operacion.cerrar(gestionDepartamentos);
    }
    
    @FXML
    private void gestionUsuarios() throws IOException{
        operacion.abrirVentana("GestionUsuarios.fxml");
        operacion.cerrar(gestionUsuarios);
    }
    
    @FXML
    private void gestionEstadosTicket() throws IOException{
        operacion.abrirVentana("GestionEstadosTicket.fxml");
        operacion.cerrar(gestionEstadosTicket);
    }
    
    @FXML
    private void gestionFlujosTrabajo() throws IOException{
        operacion.abrirVentana("GestionFlujosDeTrabajo.fxml");
        operacion.cerrar(gestionFlujosTrabajo);
    }
    
    @FXML
    private void gestionTickets() throws IOException{
        operacion.abrirVentana("GestionarTicket.fxml");
        operacion.cerrar(gestionTickets);
    }
    
    @FXML
    private void salir() throws IOException, SQLException{
         operacion.abrirVentana("Principal.fxml");
         operacion.cerrar(salir);
         LocalDate fecha = LocalDate.now();
         
         int id= conectar.getIdUsuario();
         HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "El usuario con id: " + id + " salio del sistema ", ""+ id +"");                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
        
}
