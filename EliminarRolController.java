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
public class EliminarRolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button eliminar;
    
    @FXML
    public void eliminarRol() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el Rol exitosamente");
        cerrar.cerrar(eliminar);
        abrir.abrirVentana("GestionRolesPermisos.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
