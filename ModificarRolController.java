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
public class ModificarRolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button modificar;
    public TextField rolSeleccionado;
    public TextField nombreRol;
    public TextArea descripcionRol;
    public TableView permisos;
    
    @FXML
    public void modificarRol() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.modificarRoles(modificar, descripcionRol.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
