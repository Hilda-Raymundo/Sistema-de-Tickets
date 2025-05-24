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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class AgregarNotaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button agregarNota;
        
    @FXML
    private void atras() throws IOException{
        abrir.abrirVentana("Login.fxml");
        cerrar.cerrar(atras);
    }
    
    @FXML
    private void agregarNota() throws IOException{
        JOptionPane.showMessageDialog(null, "Se agregó la nota exitosamente");
        abrir.abrirVentana("Login.fxml");
        cerrar.cerrar(agregarNota);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
