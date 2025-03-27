/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class CrearTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crear;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public void cancelar() throws Exception{
        cerrar.cerrar(cancelar);
        abrir.abrirVentana("GestionarTicket.fxml");
        abrir.abrirVentana("Usuario.fxml");
    }
    
    public void crear() throws Exception{
        JOptionPane.showMessageDialog(null, "Se creó el ticket exitosamente");
        cerrar.cerrar(crear);
        abrir.abrirVentana("GestionarTicket.fxml");
        abrir.abrirVentana("Usuario.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
