/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
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
public class SolicitudCancelacionTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button solicitarCancelacion;
    public javafx.scene.control.Button atras;
        
    @FXML
    private void solicitarCancelacion() throws IOException{
        JOptionPane.showMessageDialog(null, "Se envió la solicitud");
        abrir.abrirVentana("Usuario.fxml");
        cerrar.cerrar(solicitarCancelacion);
    }
    
    @FXML
    private void atras() throws IOException{
        abrir.abrirVentana("Usuario.fxml");
        cerrar.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
