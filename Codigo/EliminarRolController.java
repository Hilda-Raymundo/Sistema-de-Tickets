/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class EliminarRolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public javafx.scene.control.Button eliminar;
    public javafx.scene.control.Button cancelar;
    @FXML
    public TextField nombreRol;
    @FXML
    public TextArea descripcionRol;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaRoles;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_rol;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_rol;
    
    @FXML
    public void seleccionarRol() throws Exception{
        DatosTableViewSinCheckbox dato = tablaRoles.getSelectionModel().getSelectedItem();
            if(dato!= null){
                nombreRol.setText(dato.getDato1());
                descripcionRol.setText(dato.getDato2());
            }
    }
    
    @FXML
    public void eliminarRol() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.eliminarRoles(eliminar, nombreRol.getText(), descripcionRol.getText());
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
       Administrador admin = new Administrador("", "", "", "", "", "");
       admin.consultarRoles(tablaRoles, nombre_rol, descripcion_rol);
    }    
    
}
