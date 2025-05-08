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
public class MisTicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button verDetalles;
    public javafx.scene.control.Button atras;
        
    @FXML
    private void agregarNota() throws IOException{
        JOptionPane.showMessageDialog(null, "Se agregó la nota");
        //abrir.abrirVentana("MisTickets.fxml");
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(agregarNota);
    }
    
    @FXML
    private void verDetalles() throws IOException{
        //abrir.abrirVentana("MisTickets.fxml");
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(verDetalles);
    }
    
    @FXML
    private void atras() throws IOException{
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
