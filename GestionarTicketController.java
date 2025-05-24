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
public class GestionarTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button cambiarEstado;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button adjuntarDocumentacion;
    public javafx.scene.control.Button reasignarTicket;
    public javafx.scene.control.Button verReporte;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    
    public void atras() throws Exception{
        operaciones.cerrar(atras);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    public void cambiarEstado() throws Exception{
        operaciones.cerrar(cambiarEstado);
        operaciones.abrirVentana("ModificarEstado.fxml");
    }
    
    public void agregarNota() throws Exception{
        operaciones.cerrar(agregarNota);
        operaciones.abrirVentana("AgregarNota.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        JOptionPane.showMessageDialog(null, "Se guardaron los cambios exitosamente");
        operaciones.cerrar(atras);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    public void reasignarTicket() throws Exception{
        JOptionPane.showMessageDialog(null, "Se guardaron los cambios exitosamente");
        operaciones.cerrar(reasignarTicket);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    public void verReporte() throws Exception{
        operaciones.cerrar(verReporte);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
