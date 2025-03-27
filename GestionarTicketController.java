/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

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
public class GestionarTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button crearNuevoTicket;
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button cambiarEstado;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button adjuntarDocumentacion;
    public javafx.scene.control.Button reasignarTicket;
    public javafx.scene.control.Button verReporte;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public void crearNuevoTicket() throws Exception{
        cerrar.cerrar(crearNuevoTicket);
        abrir.abrirVentana("CrearTicket.fxml");
    }
    
    public void atras() throws Exception{
        cerrar.cerrar(atras);
        abrir.abrirVentana("Admin.fxml");
    }
    
    public void cambiarEstado() throws Exception{
        cerrar.cerrar(cambiarEstado);
        abrir.abrirVentana("ModificarEstado.fxml");
    }
    
    public void agregarNota() throws Exception{
        cerrar.cerrar(agregarNota);
        abrir.abrirVentana("AgregarNota.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        JOptionPane.showMessageDialog(null, "Se guardaron los cambios exitosamente");
        cerrar.cerrar(atras);
        abrir.abrirVentana("Admin.fxml");
    }
    
    public void reasignarTicket() throws Exception{
        JOptionPane.showMessageDialog(null, "Se guardaron los cambios exitosamente");
        cerrar.cerrar(reasignarTicket);
        abrir.abrirVentana("Admin.fxml");
    }
    
    public void verReporte() throws Exception{
        cerrar.cerrar(verReporte);
        abrir.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
