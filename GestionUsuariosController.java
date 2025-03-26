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
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void registrarNuevoUsuario() throws Exception{
        abrir.abrirVentana("RegistrarUsuario.fxml");
        cerrar.cerrar(registrarNuevoUsuario);
    }
    
    @FXML
    private void modificarUsuario() throws Exception{
        abrir.abrirVentana("ModificarUsuario.fxml");
        cerrar.cerrar(modificarUsuario);
    }
    
    @FXML
    private void activarDesactivarUsuario() throws Exception{
        abrir.abrirVentana("ActivarDesactivarUsuario.fxml");
        cerrar.cerrar(activarDesactivarUsuario);
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
