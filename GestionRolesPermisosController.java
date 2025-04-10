/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionRolesPermisosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String[][] roles;
    private String[][] permisos;
    
    public javafx.scene.control.Button crearRol;
    public javafx.scene.control.Button modificarRol;
    public javafx.scene.control.Button eliminarRol;
    public javafx.scene.control.Button gestionarPermiso;    
    public javafx.scene.control.Button atras;
    public TableView tablaRoles;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public GestionRolesPermisosController(/*String[][] roles, String[][] permisos*/){
//        this.roles = new String[roles.length][2];
//        for(int i=0; i<this.roles.length; i++){
//            for(int e=0; e<2; e++){
//                this.roles[i][e] = roles[i][e];
//            }
//        }
//        this.permisos = new String[permisos.length][2];
//        for(int i=0; i<this.permisos.length; i++){
//            for(int e=0; e<2; e++){
//                this.permisos[i][e] = permisos[i][e];
//            };
//        }
    }

    public String[][] getRoles() {
        return roles;
    }

    public void setRoles(String[][] roles) {
        this.roles = roles;
    }

    public String[][] getPermisos() {
        return permisos;
    }

    public void setPermisos(String[][] permisos) {
        this.permisos = permisos;
    }
    
    
    
    @FXML
    private void crearRol() throws Exception{
        abrir.abrirVentana("CrearRol.fxml");
        cerrar.cerrar(crearRol);
    }
    
    @FXML
    private void modificarRol() throws Exception{
        abrir.abrirVentana("ModificarRol.fxml");
        cerrar.cerrar(modificarRol);
    }
    
    @FXML
    private void eliminarRol() throws Exception{
        abrir.abrirVentana("EliminarRol.fxml");
        cerrar.cerrar(eliminarRol);
    }
    
    @FXML
    private void gestionarPermiso() throws Exception{
        abrir.abrirVentana("GestionarPermiso.fxml");
        cerrar.cerrar(gestionarPermiso);
    }
    
    @FXML
    private void atras() throws Exception{
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        tablaRoles = new TableView<String>();
//        tablaRoles.getItems().add("hola");
    }    
    
}
