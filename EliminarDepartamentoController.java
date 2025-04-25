/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class EliminarDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public TextField departamentoSeleccionado;
    public TextField nombreDepartamento;
    public TextArea descripcionDepartamento;
    public javafx.scene.control.Button eliminarDepartamento;
    
    @FXML
    public void eliminarDepartamento() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.eliminarDepartamento(eliminarDepartamento, departamentoSeleccionado.getText());        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
