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
public class TicketsPendientesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cambiarEstado;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button adjuntarDocumentacion;
    public javafx.scene.control.Button atras;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public void cambiarEstado() throws Exception{
        operaciones.cerrar(cambiarEstado);
        operaciones.abrirVentana("ModificarEstado.fxml");
    }
    
    public void agregarNota() throws Exception{
        JOptionPane.showMessageDialog(null, "Se agregó la nota exitosamente");
        operaciones.cerrar(agregarNota);
        operaciones.abrirVentana("Tecnico.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        JOptionPane.showMessageDialog(null, "Se agregó la documentación exitosamente");
        operaciones.cerrar(adjuntarDocumentacion);
        operaciones.abrirVentana("Tecnico.fxml");
    }
    
    public void atras() throws Exception{
        operaciones.cerrar(atras);
        operaciones.abrirVentana("Tecnico.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
