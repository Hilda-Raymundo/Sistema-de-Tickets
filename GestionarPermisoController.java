/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionarPermisoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button crearPermiso;
    public javafx.scene.control.Button modificarPermiso;
    public javafx.scene.control.Button eliminarPermiso;
    
    @FXML
    public void crearPermiso() throws Exception{
        JOptionPane.showMessageDialog(null, "Se creó el permiso exitosamente");
        cerrar.cerrar(crearPermiso);
        abrir.abrirVentana("GestionRolesPermisos.fxml");
    }
    
    @FXML
    public void modificarPermiso() throws Exception{
        JOptionPane.showMessageDialog(null, "Se modificó el permiso exitosamente");
        cerrar.cerrar(modificarPermiso);
        abrir.abrirVentana("GestionRolesPermisos.fxml");
    }
    
    @FXML
    public void eliminarPermiso() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el permiso exitosamente");
        cerrar.cerrar(eliminarPermiso);
        abrir.abrirVentana("GestionRolesPermisos.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
