/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
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
    Administrador admin = new Administrador("", "", "", "", "", "");
    conection conectado = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectado.getIdUsuario();
    @FXML
    public TableView<DatosTableViewSinCheckbox> misTickets;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> numero_ticket;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> estado_ticket;
    @FXML
    public CheckBox cancelacion;
    
    public javafx.scene.control.Button cambiarEstado;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button adjuntarDocumentacion;
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button aceptarCancelacion;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public void cambiarEstado() throws Exception{
        DatosTableViewSinCheckbox dato = misTickets.getSelectionModel().getSelectedItem();
            if(dato!= null){
                int datoABuscar = conectado.buscar("SELECT * FROM tickets where titulo = '"+ dato.getDato1() +"' and prioridad = (SELECT id_prioridad FROM prioridades WHERE nombre_prioridad = '"+ dato.getDato2() +"') and tecnico IS NOT NULL");
                    if(datoABuscar>0){
                        JOptionPane.showMessageDialog(null, "El ticket ya tiene asignado un tecnico");
                    }else{
                        conectado.consultaDML("UPDATE tickets SET tecnico ='"+ id +"' WHERE titulo ='"+ dato.getDato1() +"' and prioridad =(SELECT id_prioridad FROM prioridades WHERE nombre_prioridad ='"+ dato.getDato2()+"' )  ");
                        JOptionPane.showMessageDialog(null, "Se ha seleccionado el ticket: " + dato.getDato1() + ", " + dato.getDato2() );
                         operaciones.cerrar(cambiarEstado);
                         operaciones.abrirVentana("Tecnico.fxml");
                    }
                }else{
                JOptionPane.showMessageDialog(null, "Seleccione un ticket");
            }
        
    }
    
    @FXML
    public void solicitudCancelacion(){
        if(cancelacion.isSelected()){
                try {            
                    numero_ticket.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
                    estado_ticket.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));

                    misTickets.setItems(conectado.obtenerListado("SELECT t.titulo, e.nombre_prioridad FROM tickets t INNER JOIN prioridades e ON t.prioridad = e.id_prioridad WHERE departamento = (SELECT id_departamento FROM usuarios WHERE id_usuario = 2) AND solicitud_cancelacion IS NOT NULL", "titulo", "nombre_prioridad", "", ""));
                } catch (SQLException ex) {
                    Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            admin.consultarTicketsTablaEstados(misTickets, numero_ticket, estado_ticket);
        }
    }
    
    public void aceptarCancelacion(){
        DatosTableViewSinCheckbox dato = misTickets.getSelectionModel().getSelectedItem();
            if(dato!= null){
            try {
                int datoABuscar = conectado.buscar("SELECT * FROM tickets where titulo = '"+ dato.getDato1() +"' and prioridad = (SELECT id_prioridad FROM prioridades WHERE nombre_prioridad = '"+ dato.getDato2() +"') and solicitud_cancelacion IS NOT NULL");
                if(datoABuscar>0){
                    conectado.consultaDML("DELETE FROM documentacion WHERE id_ticket = (SELECT id_ticket FROM tickets where titulo ='"+ dato.getDato1() +"' )");
                    conectado.consultaDML("DELETE FROM tickets WHERE titulo ='"+ dato.getDato1() +"' AND prioridad= (SELECT id_prioridad FROM prioridades WHERE nombre_prioridad= '"+ dato.getDato2() +"')");
                    JOptionPane.showMessageDialog(null, "Se acepto la cancelacion");
                    HistorialTickets historial = new HistorialTickets(fecha, "Se cancelo el ticket(nombre = "+ dato.getDato1() +") ",""+ id);
                }else{
                    JOptionPane.showMessageDialog(null, "No tiene solicitud de cancelacion");
                }
                operaciones.cerrar(aceptarCancelacion);
                operaciones.abrirVentana("Tecnico.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(TicketsPendientesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TicketsPendientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }else{
                JOptionPane.showMessageDialog(null, "Seleccione un ticket");
            }
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
        admin.consultarTicketsTablaEstados(misTickets, numero_ticket, estado_ticket);
    }    
    
}
