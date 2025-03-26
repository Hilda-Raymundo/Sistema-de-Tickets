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
public class EliminarDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button eliminarDepartamento;
    
    @FXML
    public void eliminarDepartamento() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el departamento exitosamente");
        cerrar.cerrar(eliminarDepartamento);
        abrir.abrirVentana("GestionDepartamento.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
