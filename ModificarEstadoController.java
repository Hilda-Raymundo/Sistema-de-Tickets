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
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public void atras() throws Exception{
        operaciones.cerrar(atras);
        operaciones.abrirVentana("Principal.fxml");
    }
    
    public void modificar() throws Exception{
        JOptionPane.showMessageDialog(null, "Se modificó el estado exitosamente");
        operaciones.cerrar(modificar);
        operaciones.abrirVentana("Principal.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
