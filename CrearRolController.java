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
public class CrearRolController implements Initializable {

    /**
     * Initializes the controller class.
     */    
    public javafx.scene.control.Button crear;
    public javafx.scene.control.Button cancelar;
    public TextField nombreRol;
    public TextArea descripcionRol;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaRoles;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_rol;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_rol;
    @FXML
    public TableView<DatosTableView> tablaPermisos;
    @FXML
    public TableColumn<DatosTableView, String> nombrePermiso;
    @FXML
    public TableColumn<DatosTableView, Boolean> asignacionPermiso;
    
    @FXML
    public void crearRol() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        
        ObservableList<DatosTableView> permisos = tablaPermisos.getItems();
        ArrayList<String> permisosSeleccionados = new ArrayList<>();

        for (DatosTableView permiso : permisos) {
            if (permiso.getCheckbox()) {
                permisosSeleccionados.add(permiso.getNombre());
            }
        }
        admin.crearRoles(crear, nombreRol.getText(), descripcionRol.getText(), permisosSeleccionados);
    }
    
    @FXML
    public void cancelar() throws IOException{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionRolesPermisos.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Administrador admin = new Administrador("", "", "", "", "", "");
            admin.consultarRoles(tablaRoles, nombre_rol, descripcion_rol);
            conection conectado = new conection();
            asignacionPermiso.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            asignacionPermiso.setCellFactory(CheckBoxTableCell.forTableColumn(asignacionPermiso));
            nombrePermiso.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            
            tablaPermisos.setItems(conectado.obtenerListadoYAsignarCheckbox  ("SELECT nombre_permiso, id_permiso FROM permisos;", "nombre_permiso" , ""));
            tablaPermisos.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(CrearRolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
