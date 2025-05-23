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
import javafx.beans.property.SimpleStringProperty;
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
public class ModificarDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TextField nombreDepartamento;
    @FXML
    public TextArea descripcionDepartamento;
    @FXML
    public javafx.scene.control.Button modificarDepartamento;
    @FXML
    public javafx.scene.control.Button cancelar;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaDepartamentos;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_Departamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_Departamento;
    @FXML
    public TableView<DatosTableView> tablaTecnicos;
    @FXML
    public TableColumn<DatosTableView, String> nombre_tecnico;
    @FXML
    public TableColumn<DatosTableView, Boolean> seleccion;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    Administrador admin = new Administrador("", "", "", "", "", "");

    @FXML
    public void modificarDepartamento() throws Exception{
        ObservableList<DatosTableView> tecnicos = tablaTecnicos.getItems();
        ArrayList<String> tecnicosSeleccionados = new ArrayList<>();
        ArrayList<String> tecnicosNoSeleccionados = new ArrayList<>();

        for (DatosTableView tecnico : tecnicos) {
            if (tecnico.getCheckbox()) {
                tecnicosSeleccionados.add(tecnico.getNombre());
            }
        }
        
        for(DatosTableView tecnico: tecnicos){
            if(!tecnico.getCheckbox()){
                tecnicosNoSeleccionados.add(tecnico.getNombre());
            }
        }
        admin.modificarDepartamento(modificarDepartamento, nombreDepartamento.getText(), descripcionDepartamento.getText(), tecnicosSeleccionados, tecnicosNoSeleccionados);        
    }
    
    @FXML
    public void seleccionarDepartamento(){
        try {
            DatosTableViewSinCheckbox dato = tablaDepartamentos.getSelectionModel().getSelectedItem();
            conection conectado = new conection();
            if(dato!= null){
                nombreDepartamento.setText(dato.getDato1());
                descripcionDepartamento.setText(dato.getDato2());
            }
            seleccion.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            seleccion.setCellFactory(CheckBoxTableCell.forTableColumn(seleccion));
            nombre_tecnico.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            
            tablaTecnicos.setItems(conectado.obtenerListadoYAsignarCheckbox  ("SELECT nombre_usuario, id_usuario FROM usuarios WHERE id_departamento IS NULL OR id_departamento = (select id_departamento from departamentos where nombre_departamento = '"+ nombreDepartamento.getText() +"' and descripcion_departamento = '"+ descripcionDepartamento.getText() +"');", "nombre_usuario" , "", ""));
            tablaTecnicos.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(ModificarRolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cancelar() throws IOException{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionDepartamento.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.consultarDepartamentos(tablaDepartamentos, nombre_Departamento, descripcion_Departamento, descripcion_Departamento);
    }    
    
}
