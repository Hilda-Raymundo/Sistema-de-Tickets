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
public class TecnicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button tomarTicket;
    public javafx.scene.control.Button ticketsPendientes;
    public javafx.scene.control.Button resolverTicket;
    public javafx.scene.control.Button agregarNotaATicket;
    public javafx.scene.control.Button adjuntarDocumentacion;
    public javafx.scene.control.Button gestionarSolicitudDeTicket;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public void tomarTicket() throws Exception{
        operaciones.cerrar(tomarTicket);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void ticketsPendientes() throws Exception{
        operaciones.cerrar(ticketsPendientes);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void resolverTicket() throws Exception{
        operaciones.cerrar(resolverTicket);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void agregarNotaATicket() throws Exception{
        operaciones.cerrar(agregarNotaATicket);
        operaciones.abrirVentana("AgregarNota.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        operaciones.cerrar(adjuntarDocumentacion);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void gestionarSolicitudDeTicket() throws Exception{
        operaciones.cerrar(gestionarSolicitudDeTicket);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
