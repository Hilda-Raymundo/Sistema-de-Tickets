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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionRolesPermisosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button crearRol;
    public javafx.scene.control.Button modificarRol;
    public javafx.scene.control.Button eliminarRol;
    public javafx.scene.control.Button gestionarPermiso;    
    public javafx.scene.control.Button atras;
    @FXML
    public TableView<String> tablaRoles;
    @FXML
    public TableColumn<String, String> nombreRol;
    @FXML
    public TableView<String> tablaPermisos;
    @FXML
    public TableColumn<String, String> nombrePermiso;
    
    
    OperacionesVentana operaciones = new OperacionesVentana();
        
    @FXML
    private void crearRol() throws Exception{
        operaciones.abrirVentana("CrearRol.fxml");
        operaciones.cerrar(crearRol);
    }
    
    @FXML
    private void modificarRol() throws Exception{
        operaciones.abrirVentana("ModificarRol.fxml");
        operaciones.cerrar(modificarRol);
    }
    
    @FXML
    private void eliminarRol() throws Exception{
        operaciones.abrirVentana("EliminarRol.fxml");
        operaciones.cerrar(eliminarRol);
    }
    
    @FXML
    private void gestionarPermiso() throws Exception{
        operaciones.abrirVentana("GestionarPermiso.fxml");
        operaciones.cerrar(gestionarPermiso);
    }
    
    @FXML
    private void atras() throws Exception{
        operaciones.abrirVentana("Admin.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.consultarRoles();
        admin.consultarPermisos();
    }    
    
}
