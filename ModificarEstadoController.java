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
public class ModificarEstadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button modificar;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public void atras() throws Exception{
        cerrar.cerrar(atras);
        abrir.abrirVentana("GestionarTicket.fxml");
    }
    
    public void modificar() throws Exception{
        JOptionPane.showMessageDialog(null, "Se modificó el estado exitosamente");
        cerrar.cerrar(modificar);
        abrir.abrirVentana("GestionarTicket.fxml");
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
