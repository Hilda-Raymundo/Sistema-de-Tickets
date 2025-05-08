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
public class ModificarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button modificarUsuario;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    private void cancelar() throws Exception{
        operaciones.abrirVentana("GestionUsuarios.fxml");
        operaciones.cerrar(cancelar);
    }
    
    @FXML
    private void modificarUsuario() throws Exception{
        JOptionPane.showMessageDialog(null, "Se modificó el usuario exitosamente");
        operaciones.abrirVentana("GestionUsuarios.fxml");
        operaciones.cerrar(modificarUsuario);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
