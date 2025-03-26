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
public class GestionFlujosDeTrabajoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button nuevoFlujo;
    public javafx.scene.control.Button modificarFlujo;
    public javafx.scene.control.Button eliminarFlujo;
    public javafx.scene.control.Button atras;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void nuevoFlujo() throws Exception{
        abrir.abrirVentana("CrearNuevoFlujo.fxml");
        cerrar.cerrar(nuevoFlujo);
    }
    
    @FXML
    private void modificarFlujo() throws Exception{
        abrir.abrirVentana("ModificarFlujo.fxml");
        cerrar.cerrar(modificarFlujo);
    }
    
    @FXML
    private void eliminarFlujo() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el flujo exitosamente");
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(eliminarFlujo);
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
