/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class MisTicketsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    OperacionesVentana operaciones = new OperacionesVentana();
    Administrador admin = new Administrador("", "", "", "", "", "");
    conection conectado = new conection();
    
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button verDetalles;
    public javafx.scene.control.Button atras;
    @FXML
    public TableView<DatosTableViewSinCheckbox> misTickets;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> numero_ticket;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> estado_ticket;
    @FXML
    public ComboBox<String> tickets;
        
    @FXML
    private void agregarNota() throws IOException{
        JOptionPane.showMessageDialog(null, "Se agregó la nota");
        //abrir.abrirVentana("MisTickets.fxml");
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(agregarNota);
    }
    
    @FXML
    private void verDetalles() throws IOException{
        //abrir.abrirVentana("MisTickets.fxml");
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(verDetalles);
    }

    
    @FXML
    private void atras() throws IOException{
        operaciones.abrirVentana("Usuario.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            admin.consultarTicketsTabla(misTickets, numero_ticket, estado_ticket);
            ArrayList<String> listaEstados = conectado.consultaListados("SELECT * FROM estados_ticket", "nombre_estado", "");
            ObservableList<String> comboEstados = FXCollections.observableArrayList(listaEstados);
            tickets.setItems(comboEstados);
        } catch (SQLException ex) {
            Logger.getLogger(MisTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
