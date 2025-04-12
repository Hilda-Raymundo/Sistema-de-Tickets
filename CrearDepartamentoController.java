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
public class CrearDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button crearDepartamento;
    public TextField nombreDepartamento;
    public TextArea descripcionDepartamento;
    
    @FXML
    public void crearDepartamento() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.crearDepartamento(crearDepartamento, nombreDepartamento.getText(), descripcionDepartamento.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
