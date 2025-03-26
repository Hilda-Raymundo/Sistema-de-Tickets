/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionRolesPermisosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button crearRol;
    public javafx.scene.control.Button modificarRol;
    public javafx.scene.control.Button eliminarRol;
    public javafx.scene.control.Button gestionarPermiso;    
    public javafx.scene.control.Button atras;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void crearRol() throws Exception{
        abrir.abrirVentana("CrearRol.fxml");
        cerrar.cerrar(crearRol);
    }
    
    @FXML
    private void modificarRol() throws Exception{
        abrir.abrirVentana("ModificarRol.fxml");
        cerrar.cerrar(modificarRol);
    }
    
    @FXML
    private void eliminarRol() throws Exception{
        abrir.abrirVentana("EliminarRol.fxml");
        cerrar.cerrar(eliminarRol);
    }
    
    @FXML
    private void gestionarPermiso() throws Exception{
        abrir.abrirVentana("GestionarPermiso.fxml");
        cerrar.cerrar(gestionarPermiso);
    }
    
    @FXML
    private void atras() throws Exception{
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
