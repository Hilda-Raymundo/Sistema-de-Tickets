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
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionarPermisoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button crearPermiso;
    public javafx.scene.control.Button modificarPermiso;
    public javafx.scene.control.Button eliminarPermiso;
    public TextField nombrePermisoSeleccionado;
    public TextField nombrePermiso;
    public TextArea descripcionPermiso;
    
    Administrador admin = new Administrador("", "", "", "", "", "");
    
    @FXML
    public void crearPermiso() throws IOException{
        admin.crearPermisos(crearPermiso, nombrePermiso.getText(), descripcionPermiso.getText());
    }
    
    @FXML
    public void modificarPermiso() throws Exception{
        admin.modificarPermisos(modificarPermiso, descripcionPermiso.getText());
    }
    
    @FXML
    public void eliminarPermiso() throws Exception{
        admin.eliminarPermisos(eliminarPermiso, nombrePermisoSeleccionado.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
