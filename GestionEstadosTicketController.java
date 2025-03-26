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
public class GestionEstadosTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button crearEstado;
    public javafx.scene.control.Button modificarEstado;
    public javafx.scene.control.Button eliminarEstado;
    public javafx.scene.control.Button atras;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    @FXML
    private void crearEstado() throws Exception{
        abrir.abrirVentana("CrearEstado.fxml");
        cerrar.cerrar(crearEstado);
    }
    
    @FXML
    private void modificarEstado() throws Exception{
        JOptionPane.showMessageDialog(null, "Se modificó el estado exitosamente");
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(modificarEstado);
    }
    
    @FXML
    private void eliminarEstado() throws Exception{
        JOptionPane.showMessageDialog(null, "Se eliminó el estado exitosamente");
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(eliminarEstado);
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
