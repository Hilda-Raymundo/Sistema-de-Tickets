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
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public void tomarTicket() throws Exception{
        cerrar.cerrar(tomarTicket);
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void ticketsPendientes() throws Exception{
        cerrar.cerrar(ticketsPendientes);
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void resolverTicket() throws Exception{
        cerrar.cerrar(resolverTicket);
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void agregarNotaATicket() throws Exception{
        cerrar.cerrar(agregarNotaATicket);
        abrir.abrirVentana("AgregarNota.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        cerrar.cerrar(adjuntarDocumentacion);
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void gestionarSolicitudDeTicket() throws Exception{
        cerrar.cerrar(gestionarSolicitudDeTicket);
        abrir.abrirVentana("TicketsPendientes.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
