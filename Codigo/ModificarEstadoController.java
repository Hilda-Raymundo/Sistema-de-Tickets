/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class ModificarEstadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button modificar;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectado.getIdUsuario();
    
    @FXML
    public TextField noTicket;
    @FXML
    public TextField estadoActual;
    @FXML
    public ComboBox<String> estadosDisponibles;
    @FXML
    public TextArea comentario;
    private static String numeroDeTicket, estadoDeTicket, flujoDeTicket;
    Administrador admin = new Administrador("", "", "", "", "", "");
    
    public void obtenerDatos(String numeroTicket, String estado, String flujo){
        numeroDeTicket = numeroTicket;
        estadoDeTicket = estado;
        flujoDeTicket = flujo;
    }
    
    public void atras() throws Exception{
         int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.cerrar(atras);
            operaciones.abrirVentana("Principal.fxml");
        }
    }
    
    public void modificar() throws Exception{
        
        if(estadosDisponibles.getValue()!= null){
            if(comentario.getText().equals("")){
                admin.cambiarEstadoTickets(modificar, "UPDATE tickets SET estado = (SELECT id_estado FROM estados_ticket WHERE nombre_estado ='"+ estadosDisponibles.getValue() +"' )");
                HistorialTickets historial = new HistorialTickets(fecha, "Se modifico el estado del ticket (nombre = "+ numeroDeTicket +") ",""+ id);
            }else{
                HistorialTickets historial = new HistorialTickets(fecha, "Se modifico el estado del ticket (nombre = "+ numeroDeTicket +", comentario = "+ comentario +") ",""+ id);
            }
            JOptionPane.showMessageDialog(null, "Se modificó el estado exitosamente");
            operaciones.cerrar(modificar);
            operaciones.abrirVentana("Principal.fxml");
            
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un nuevo estado");
        }        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        noTicket.setText(numeroDeTicket);
        estadoActual.setText(estadoDeTicket);
        try {
            ArrayList<String> listaEstados = new ArrayList<>();
            
            int dato = conectado.buscar("SELECT et.nombre_estado AS estado_siguiente FROM estados_flujo ef INNER JOIN flujo_trabajo ft ON ef.id_flujo = ft.id_flujo INNER JOIN estados_ticket et ON ef.estado_siguiente = et.id_estado WHERE ft.nombre_flujo = 'prueba sencilla' AND ef.estado_actual = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = 'prueba') LIMIT 1;");
            if(dato>0){
                listaEstados = conectado.consultaListados("SELECT et.nombre_estado FROM estados_flujo ef INNER JOIN flujo_trabajo ft ON ef.id_flujo = ft.id_flujo INNER JOIN estados_ticket et ON ef.estado_siguiente = et.id_estado WHERE ft.nombre_flujo = '"+ flujoDeTicket +"' AND ef.estado_actual = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estadoDeTicket +"');", "nombre_estado", "");
                for(int i = 0; i<listaEstados.size(); i++){
                    estadosDisponibles.getItems().add(listaEstados.get(i));
                }
            }else{
                listaEstados = conectado.consultaListados("SELECT DISTINCT et.nombre_estado FROM estados_flujo ef INNER JOIN flujo_trabajo ft ON ef.id_flujo = ft.id_flujo INNER JOIN estados_ticket et ON et.id_estado IN (ef.estado_actual, ef.estado_siguiente) WHERE ft.nombre_flujo = '"+ flujoDeTicket +"';", "nombre_estado", "");
                for(int i = 0; i<listaEstados.size(); i++){
                    estadosDisponibles.getItems().add(listaEstados.get(i));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ModificarEstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
