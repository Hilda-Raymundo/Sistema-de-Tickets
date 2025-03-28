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
public class CrearNuevoFlujoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crearFlujoTrabajo;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void cancelar() throws Exception{
        abrir.abrirVentana("GestionFlujosDeTrabajo.fxml");
        cerrar.cerrar(cancelar);
    }
    
    @FXML
    private void crearFlujoTrabajo() throws Exception{
        JOptionPane.showMessageDialog(null, "Se creó el flujo de trabajo exitosamente");
        abrir.abrirVentana("GestionFlujosDeTrabajo.fxml");
        cerrar.cerrar(crearFlujoTrabajo);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
