/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
public class GestionarPermisoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public javafx.scene.control.Button crearPermiso;
    public javafx.scene.control.Button modificarPermiso;
    public javafx.scene.control.Button eliminarPermiso;
    public javafx.scene.control.Button cancelar;
    @FXML
    public TextField nombrePermiso;
    @FXML
    public TextArea descripcionPermiso;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaPermisos;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_Permiso;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_permiso;
    
    Administrador admin = new Administrador("", "", "", "", "", "");
    
    @FXML
    public void crearPermiso() throws IOException{
        admin.crearPermisos(crearPermiso, nombrePermiso.getText(), descripcionPermiso.getText());
    }
    
    @FXML
    public void modificarPermiso() throws Exception{
        admin.modificarPermisos(modificarPermiso, nombrePermiso.getText(), descripcionPermiso.getText());
    }
    
    @FXML
    public void eliminarPermiso() throws Exception{
        admin.eliminarPermisos(eliminarPermiso, nombrePermiso.getText(), descripcionPermiso.getText());
    }
    
    @FXML
    public void cancelar() throws IOException{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionRolesPermisos.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    public void seleccionarPermiso(){
        DatosTableViewSinCheckbox dato = tablaPermisos.getSelectionModel().getSelectedItem();
        if(dato!= null){
                nombrePermiso.setText(dato.getDato1());
                descripcionPermiso.setText(dato.getDato2());
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.consultarPermisos(tablaPermisos, nombre_Permiso, descripcion_permiso);
    }    
    
}
