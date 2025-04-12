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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class EliminarRolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button eliminar;
    public TextField rolSeleccionado;
    public TextField nombreRol;
    public TextArea descripcionRol;
    
    @FXML
    public void eliminarRol() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.eliminarRoles(eliminar, rolSeleccionado.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
