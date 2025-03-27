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
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public void cambiarEstado() throws Exception{
        cerrar.cerrar(cambiarEstado);
        abrir.abrirVentana("ModificarEstado.fxml");
    }
    
    public void agregarNota() throws Exception{
        JOptionPane.showMessageDialog(null, "Se agregó la nota exitosamente");
        cerrar.cerrar(agregarNota);
        abrir.abrirVentana("Tecnico.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        JOptionPane.showMessageDialog(null, "Se agregó la documentación exitosamente");
        cerrar.cerrar(adjuntarDocumentacion);
        abrir.abrirVentana("Tecnico.fxml");
    }
    
    public void atras() throws Exception{
        cerrar.cerrar(atras);
        abrir.abrirVentana("Tecnico.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
