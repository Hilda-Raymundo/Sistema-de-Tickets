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
public class GestionUsuariosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button registrarNuevoUsuario;
    public javafx.scene.control.Button modificarUsuario;
    public javafx.scene.control.Button activarDesactivarUsuario;
    public javafx.scene.control.Button atras;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    private void registrarNuevoUsuario() throws Exception{
        operaciones.abrirVentana("RegistrarUsuario.fxml");
        operaciones.cerrar(registrarNuevoUsuario);
    }
    
    @FXML
    private void modificarUsuario() throws Exception{
        operaciones.abrirVentana("ModificarUsuario.fxml");
        operaciones.cerrar(modificarUsuario);
    }
    
    @FXML
    private void activarDesactivarUsuario() throws Exception{
        operaciones.abrirVentana("ActivarDesactivarUsuario.fxml");
        operaciones.cerrar(activarDesactivarUsuario);
    }
    
    @FXML
    private void atras() throws Exception{
        operaciones.abrirVentana("Admin.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
