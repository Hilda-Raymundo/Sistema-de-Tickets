/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public javafx.scene.control.Button atras;
    public ImageView logo;
    LocalDate fecha = LocalDate.now();
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectar = new conection();
    
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
        operaciones.abrirVentana("ModificarEstado.fxml");
    }
    
    public void agregarNotaATicket() throws Exception{
        operaciones.cerrar(agregarNotaATicket);
        operaciones.abrirVentana("Notas.fxml");
    }
    
    public void adjuntarDocumentacion() throws Exception{
        operaciones.cerrar(adjuntarDocumentacion);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    public void gestionarSolicitudDeTicket() throws Exception{
        operaciones.cerrar(gestionarSolicitudDeTicket);
        operaciones.abrirVentana("TicketsPendientes.fxml");
    }
    
    @FXML
    public void atras(){
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de salir?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            try {
                operaciones.abrirVentana("Principal.fxml");
                operaciones.cerrar(atras);
                int id= conectar.getIdUsuario();                
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "El usuario con id: " + id + " salio del sistema ", ""+ id +"");
            } catch (IOException ex) {
                Logger.getLogger(TecnicoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TecnicoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> listado = new ArrayList<>();
        try {
            conection conectado = new conection();
            listado = conectado.consultaListados("SELECT * FROM configuracion_sistema", "logo_empresa", "");
            Image image = new Image("file:/C:/Users/hraym/OneDrive/Documentos/NetBeansProjects/SistemaDeTickets/src/sistemadetickets/imagenes/"+listado.get(0));
            logo.setImage(image);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
