/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class ConfiguracionDelSistemaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    CerrarVentana cerrar = new CerrarVentana();
    AbrirVentana abrir = new AbrirVentana();
    
    @FXML
    public javafx.scene.control.Button volver;
    public javafx.scene.control.Button guardar;
    
    @FXML
    private void cancelar(ActionEvent event) throws IOException{
       cerrar.cerrar(volver);
       abrir.abrirVentana("Admin.fxml");
    }
    
    @FXML
    private void guardar()throws IOException{
        JOptionPane.showMessageDialog(null, "Configuración guardada");
        cerrar.cerrar(guardar);
        abrir.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
