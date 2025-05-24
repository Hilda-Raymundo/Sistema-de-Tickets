/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
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
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button cambiarEstado;
    public javafx.scene.control.Button verReporte;
    
    @FXML
    public TableView<Tabla> tablaTickets;
    @FXML
    public TableColumn<Tabla, String> ticket;
    @FXML
    public TableColumn<Tabla, String> estado;
    @FXML
    public TableColumn<Tabla, String> fechaCreacion;
    @FXML
    public TableColumn<Tabla, String> departamento;
    @FXML
    public TableColumn<Tabla, String> prioridad;
    @FXML
    public TableColumn<Tabla, String> flujo;
    @FXML
    public TextField noTicket;
    @FXML
    public ComboBox<String> estados;
    @FXML
    public ComboBox<String> prioridades;
    @FXML
    public ComboBox<String> departamentos;
    @FXML
    public DatePicker fecha;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectar = new conection();
    ModificarEstadoController datosEstado = new ModificarEstadoController();
    
    @FXML
    public void filtrar(){
        
    }
    
    public void atras() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
             operaciones.cerrar(atras);
            operaciones.abrirVentana("Admin.fxml");
        }
       
    }
    
    public void cambiarEstado() throws Exception{
        Tabla dato = tablaTickets.getSelectionModel().getSelectedItem();
            if(dato!= null){
                JOptionPane.showMessageDialog(null, "Se ha seleccionado el ticket: " + dato.getDato1() + dato.getDato2() + dato.getDato6());
                datosEstado.obtenerDatos(dato.getDato1(), dato.getDato2(), dato.getDato6());
                operaciones.cerrar(cambiarEstado);
                operaciones.abrirVentana("ModificarEstado.fxml");
                }else{
                JOptionPane.showMessageDialog(null, "Seleccione un ticket");
            }
    }

    public void generarDocumento(ArrayList<String> lista, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(lista);
            JOptionPane.showMessageDialog(null, "Se creó el archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void verReporte() throws Exception{    
        ArrayList<String> listaTickets = new ArrayList<>();
        listaTickets = conectar.consultaListados("SELECT id_ticket || ' - ' || titulo || ': ' || descripcion || ', fecha:' || fecha_creacion || ', estado:' || estado || ', usuario: ' || usuario || ', departamento: ' || departamento AS info_completa FROM tickets;", "info_completa", "");
        File carpeta = new File("archivos");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        String rutaArchivo = "archivos/reporteTickets.ser";
        generarDocumento(listaTickets, rutaArchivo);
        
        operaciones.cerrar(verReporte);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {            
            ticket.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            fechaCreacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato3()));
            departamento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato4()));
            prioridad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato5()));
            flujo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato6()));
            tablaTickets.setItems(conectar.asignarDatosATabla("SELECT t.id_ticket, d.nombre_estado, t.fecha_creacion, e.nombre_departamento, u.nombre_prioridad, v.nombre_flujo FROM tickets t INNER JOIN departamentos e ON t.departamento = e.id_departamento INNER JOIN prioridades u ON t.prioridad = u.id_prioridad INNER JOIN estados_ticket d ON t.estado = d.id_estado INNER JOIN flujo_trabajo v ON t.flujo = v.id_flujo", "id_ticket", "nombre_estado", "fecha_creacion", "nombre_departamento", "nombre_prioridad", "nombre_flujo"));
            
//            ArrayList<String> listaEstados = new ArrayList<>();
//            listaEstados = conectar.consultaListados("select nombre_estado from estados_ticket", "nombre_estado", "");
//            for(int i = 0; i<listaEstados.size(); i++){
//                estados.getItems().add(listaEstados.get(i));
//            }
//            
//            ArrayList<String> listaPrioridades = new ArrayList<>();
//            listaPrioridades = conectar.consultaListados("select nombre_prioridad from prioridades", "nombre_prioridad", "");
//            for(int i = 0; i<listaPrioridades.size(); i++){
//                prioridades.getItems().add(listaPrioridades.get(i));
//            }
//            
//            ArrayList<String> listaDepartamentos = new ArrayList<>();
//            listaDepartamentos = conectar.consultaListados("select nombre_departamento from departamentos", "nombre_departamento", "");
//            for(int i = 0; i<listaDepartamentos.size(); i++){
//                departamentos.getItems().add(listaDepartamentos.get(i));
//            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
