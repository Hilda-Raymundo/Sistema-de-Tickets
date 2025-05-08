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
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    private void nuevoFlujo() throws Exception{
        operaciones.abrirVentana("CrearNuevoFlujo.fxml");
        operaciones.cerrar(nuevoFlujo);
    }
    
    @FXML
    private void modificarFlujo() throws Exception{
        operaciones.abrirVentana("ModificarFlujo.fxml");
        operaciones.cerrar(modificarFlujo);
    }
    
    @FXML
    private void eliminarFlujo() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el flujo exitosamente");
        operaciones.abrirVentana("Admin.fxml");
        operaciones.cerrar(eliminarFlujo);
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
