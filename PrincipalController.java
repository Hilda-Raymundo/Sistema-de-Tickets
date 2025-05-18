/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class PrincipalController implements Initializable {

    public TextField usuario;
    public TextField password;
    //Datos temporales

    OperacionesVentana operaciones = new OperacionesVentana();
    
    public Button loguearse;
    
    conection conectarse = new conection();
    
    public void login() throws IOException, SQLException{
        conectarse.loguearse(usuario.getText(), password.getText(), loguearse);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Informacion temporal
        
    }    
    
}
