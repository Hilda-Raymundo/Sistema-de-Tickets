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
public class CrearTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crear;
    LocalDate fecha = LocalDate.now();
    Tickets ticket = new Tickets();
    @FXML
    public TextField titulo;
    @FXML
    public TextArea descripcion;
    @FXML
    public ComboBox departamento;
    @FXML
    public ComboBox solicitud;
    @FXML
    public ComboBox prioridad;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    Administrador usuario = new Administrador("", "", "", "", "", "");
    
    public void cancelar() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
           operaciones.cerrar(cancelar);
            operaciones.abrirVentana("Principal.fxml");
        }
    }
    
    public void crear() throws Exception{
        ArrayList<String> correos = new ArrayList<>();
        ticket.setTitulo(titulo.getText());
        ticket.setDescripcion(descripcion.getText());
        ticket.setFechaCreacion(fecha);
        
        if(!titulo.getText().equals("") && departamento.getValue() != null && !descripcion.getText().equals("") && prioridad.getValue() != null && solicitud.getValue() != null){
            usuario.crearTickets(crear, ticket.getTitulo(), ticket.getDescripcion(), (String)departamento.getValue(), (String)solicitud.getValue(), (String)prioridad.getValue());
            
            correos = conectado.consultaListados("SELECT correo_usuario FROM usuarios WHERE id_departamento = (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ departamento.getValue() +"' )", "correo_usuario", "");
            for(String correo: correos){
                usuario.enviarNotificaciones(correo, "Se ha creado un nuevo ticket", "El ticket es: '"+ ticket.getTitulo() +"' ");
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione valores de los comboBox");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<String> departamentosExistentes = new ArrayList<>();
            departamentosExistentes = conectado.consultaListados("SELECT nombre_departamento FROM departamentos", "nombre_departamento", "");
            ObservableList<String> listaDepa = FXCollections.observableArrayList(departamentosExistentes);
            departamento.setItems(listaDepa);
            
            ArrayList<String> solicitudesExistentes = new ArrayList<>();
            solicitudesExistentes = conectado.consultaListados("SELECT nombre_flujo FROM flujo_trabajo", "nombre_flujo", "");
            ObservableList<String> listaSolicitud = FXCollections.observableArrayList(solicitudesExistentes);
            solicitud.setItems(listaSolicitud);
            
            
            ArrayList<String> prioridadesExistentes = new ArrayList<>();
            prioridadesExistentes = conectado.consultaListados("SELECT DISTINCT p.nombre_prioridad FROM prioridades p INNER JOIN prioridades_configuracion_sistema pcs ON p.id_prioridad = pcs.id_prioridad WHERE pcs.id_configuracion_sistema = 1;", "nombre_prioridad", "");
            ObservableList<String> listaPrioridades = FXCollections.observableArrayList(prioridadesExistentes);
            prioridad.setItems(listaPrioridades);
        } catch (SQLException ex) {
            Logger.getLogger(CrearTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
