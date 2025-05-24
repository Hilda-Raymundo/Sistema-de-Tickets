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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class SolicitudCancelacionTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectar = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectar.getIdUsuario();
    
    public javafx.scene.control.Button solicitarCancelacion;
    public javafx.scene.control.Button atras;
    @FXML
    public ComboBox<String> tickets;
        
    @FXML
    private void solicitarCancelacion() throws IOException{
        
        if(tickets.getValue() == null){
            JOptionPane.showMessageDialog(null, "Seleccione un ticket");
        }else{
            try {
                int datoABuscar = conectar.buscar("SELECT * FROM tickets where titulo = '"+ tickets.getValue() +"' and tecnico IS NOT NULL");
                if(datoABuscar>0){
                    JOptionPane.showMessageDialog(null, "Se envió la solicitud");
                    conectar.consultaDML("UPDATE tickets SET solicitud_cancelacion ="+ 1 +" WHERE titulo = '"+ tickets.getValue() +"'");
                    operaciones.abrirVentana("Usuario.fxml");
                    operaciones.cerrar(solicitarCancelacion);
                    HistorialTickets historial = new HistorialTickets(fecha, "Se solicito la cancelacion del ticket(nombre = "+ tickets.getValue() +") ",""+ id);
                }else{
                    conectar.consultaDML("DELETE FROM documentacion WHERE id_ticket = (SELECT id_ticket FROM tickets where titulo ='"+ tickets.getValue() +"' )");
                    conectar.consultaDML("DELETE FROM tickets WHERE titulo ='"+ tickets.getValue() +"' ");
                    HistorialTickets historial = new HistorialTickets(fecha, "Se elimino el ticket y su documentacion(nombre = "+ tickets.getValue() +") ",""+ id);
                    JOptionPane.showMessageDialog(null, "El ticket se cancelo exitosamente");
                      }
                operaciones.cerrar(solicitarCancelacion);
                operaciones.abrirVentana("Usuario.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(SolicitudCancelacionTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void atras() throws IOException{
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<String> listado2 = new ArrayList<>();
            listado2 = conectar.consultaListados("select titulo FROM tickets WHERE usuario = "+ id +" OR tecnico ='"+ id +"' ", "titulo", "");
            for(int i = 0; i<listado2.size(); i++){
                tickets.getItems().add(listado2.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
