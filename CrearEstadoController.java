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
public class CrearEstadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crearEstado;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void cancelar() throws Exception{
        abrir.abrirVentana("GestionEstadosTicket.fxml");
        cerrar.cerrar(cancelar);
    }
    
    @FXML
    private void crearEstado() throws Exception{
        JOptionPane.showMessageDialog(null, "Se creó el estado exitosamente");
        abrir.abrirVentana("GestionEstadosTicket.fxml");
        cerrar.cerrar(crearEstado);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
