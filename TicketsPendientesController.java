/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    @FXML
    public TableView<DatosTableViewSinCheckbox> misTickets;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> numero_ticket;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> estado_ticket;
    
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
        admin.consultarTicketsTablaEstados(misTickets, numero_ticket, estado_ticket);
    }    
    
}
