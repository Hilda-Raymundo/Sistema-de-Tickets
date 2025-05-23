/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class CrearEstadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crearEstado;
    @FXML
    public TextField nombreEstado;
    @FXML
    public TextArea descripcionEstado;
    @FXML
    public CheckBox es_final;
    @FXML
    public TableView<DatosTableView> tablaEstados;
    @FXML
    public TableColumn<DatosTableView, String> nombre_estado;
    @FXML
    public TableColumn<DatosTableView, Boolean> seleccion;
    
    Administrador admin = new Administrador("", "", "", "", "", "");
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    public void cancelar() throws IOException{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionEstadosTicket.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    public void seleccionarFinal(){
        if(es_final.isSelected()){
            tablaEstados.setEditable(false);
        }else{
            tablaEstados.setEditable(true);
        }
    }
    
    @FXML
    private void crearEstado() throws Exception{
        ObservableList<DatosTableView> estados = tablaEstados.getItems();
        ArrayList<String> estadosSeleccionados = new ArrayList<>();
        
        for (DatosTableView estado : estados) {
            if (estado.getCheckbox()) {
                estadosSeleccionados.add(estado.getNombre());
            }
        }
        
        if(estadosSeleccionados.size()>0){
            admin.crearEstado(crearEstado, nombreEstado.getText(), descripcionEstado.getText(), 2, estadosSeleccionados);
        }else{
            if(es_final.isSelected()){
                admin.crearEstado(crearEstado, nombreEstado.getText(), descripcionEstado.getText(), 1, estadosSeleccionados);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione algun estado");
            }
            
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.consultarEstado(tablaEstados, nombre_estado, seleccion);
    }    
    
}
