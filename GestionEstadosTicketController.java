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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionEstadosTicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button crearEstado;
    public javafx.scene.control.Button modificarEstado;
    public javafx.scene.control.Button eliminarEstado;
    public javafx.scene.control.Button atras;
    
    @FXML
    public TableView<DatosTableView> tablaEstados;
    @FXML
    public TableColumn<DatosTableView, String> nombre_estado;
    @FXML
    public TableColumn<DatosTableView, Boolean> seleccion;
    @FXML
    public ComboBox<String> estados;
    @FXML
    public TextField nombreEstado;
    @FXML
    public TextField descripcionEstado;
    @FXML
    public CheckBox es_final;
            
    Administrador admin = new Administrador("", "", "", "", "", "");
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    
    @FXML
    private void crearEstado() throws Exception{
        operaciones.abrirVentana("CrearEstado.fxml");
        operaciones.cerrar(crearEstado);
    }
    
    @FXML
    private void modificarEstado() throws Exception{   
        System.out.println(estados.getValue());
        ObservableList<DatosTableView> estadosTabla = tablaEstados.getItems();
        
          if(estados.getValue() != null){
            ArrayList<String> estadosSeleccionados = new ArrayList<>();
                for (DatosTableView estado : estadosTabla) {
                        if (estado.getCheckbox()) {
                        estadosSeleccionados.add(estado.getNombre());
                    }
                }
            if(es_final.isSelected()){
                if(estadosSeleccionados.size()>0){
                    JOptionPane.showMessageDialog(null, "Revise los campos FINAL o ESTADOS");
                }else{
                    admin.modificarEstado(modificarEstado, nombreEstado.getText(), descripcionEstado.getText(), 1, estadosSeleccionados, estados.getValue());
                }
            }else{
                if(estadosSeleccionados.size()>0 && !es_final.isSelected()){
                    admin.modificarEstado(modificarEstado, nombreEstado.getText(), descripcionEstado.getText(), 2, estadosSeleccionados, estados.getValue());            
                }else{
                    JOptionPane.showMessageDialog(null, "Si es FINAL no puede tener estados siguientes");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un estado");
        }        
    }
    
    @FXML
    private void eliminarEstado() throws Exception{
        if(estados.getValue() != null){            
            int dato = conectado.buscar("SELECT * FROM estados_flujo WHERE estado_actual = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estados.getValue() +"') ");
            if(dato>0){
                JOptionPane.showMessageDialog(null,"El estado pertenece a un flujo de trabajo, modifique el flujo antes de eliminar el estado");
            }else{
                int dato2 = conectado.buscar("SELECT * FROM estados_transicion WHERE estado_inicial = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estados.getValue() +"') OR estado_siguiente = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estados.getValue() +"' )  ");
                    if(dato2>0){
                        JOptionPane.showMessageDialog(null, "El estado pertenece a transiciones de estados, modifiquelo antes de eliminar");
                    }else{
                        admin.eliminarEstado(eliminarEstado, estados.getValue());
                    }
                        
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado");
        }
        
    }
    
    @FXML
    private void atras() throws Exception{
        operaciones.abrirVentana("Admin.fxml");
        operaciones.cerrar(atras);
    }
    
    @FXML
    private void seleccionaFinal() throws Exception{
        if(es_final.isSelected()){
            tablaEstados.setEditable(false);
        }else{
            tablaEstados.setEditable(true);
        }
    }
    
    @FXML
    public void seleccionarEstado(){
        try {
            ArrayList<String> parametros = new ArrayList<>();
            parametros = conectado.consultaListados("SELECT * FROM estados_ticket WHERE nombre_estado = '"+ estados.getValue() +"'", "nombre_estado", "descripcion_estado,estado_final");
            nombreEstado.setText(parametros.get(0));
            descripcionEstado.setText(parametros.get(1));
            if(parametros.get(2).equals("2")){
                es_final.setSelected(false);
                seleccion.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
                seleccion.setCellFactory(CheckBoxTableCell.forTableColumn(seleccion));
                nombre_estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

                tablaEstados.setItems(conectado.obtenerListadoYAsignarCheckbox("SELECT u.nombre_estado, (SELECT estado_final FROM estados_transicion t WHERE t.estado_siguiente = u.id_estado AND t.estado_inicial = (SELECT id_estado FROM estados_ticket WHERE nombre_estado ='"+ estados.getValue() +"' )) FROM estados_ticket u WHERE nombre_estado != '"+ estados.getValue() +"' ;", "nombre_estado" , "estado_final", ""));
                tablaEstados.setEditable(true);
                
            }else{
                seleccion.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
                seleccion.setCellFactory(CheckBoxTableCell.forTableColumn(seleccion));
                nombre_estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

                tablaEstados.setItems(conectado.obtenerListadoYAsignarCheckbox("SELECT u.nombre_estado, (SELECT estado_final FROM estados_transicion t WHERE t.estado_siguiente = u.id_estado AND t.estado_inicial = (SELECT id_estado FROM estados_ticket WHERE nombre_estado ='"+ estados.getValue() +"' )) FROM estados_ticket u WHERE nombre_estado != '"+ estados.getValue() +"' ;", "nombre_estado" , "", ""));
                tablaEstados.setEditable(true);
                tablaEstados.setEditable(false);
                es_final.setSelected(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionEstadosTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         admin.consultarEstado(tablaEstados, nombre_estado, seleccion);
         try {
            ArrayList<String> lista_estados = conectado.consultaListados("SELECT * FROM estados_ticket", "nombre_estado", "");
            ObservableList<String> comboEstados = FXCollections.observableArrayList(lista_estados);
            estados.setItems(comboEstados);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
