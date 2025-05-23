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
public class RegistrarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crear;
    public TextField nombre;
    public TextField correo;
    public TextField nombreUsuario;
    public TextField contrasenia;
    public ComboBox<String> rol;
    public ComboBox<String> departamento;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    
    @FXML
    private void cancelar() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionUsuarios.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    private void crearUsuario() throws Exception{
        if(rol.getValue() == null){
            JOptionPane.showMessageDialog(null, "Seleccione un ROL");
        }else{
            if(rol.getValue().equals("tecnico") && departamento.getValue() == null){
                JOptionPane.showMessageDialog(null, "Seleccione un DEPARTAMENTO");
            }else{
                
                Administrador admin = new Administrador("", "", "", "", "", "");
                admin.crearUsuarios(crear, nombre.getText(), correo.getText(), nombreUsuario.getText(), contrasenia.getText(), rol.getValue(), departamento.getValue());
            }
        }        
    }
    
    @FXML
    private void seleccionarTecnico() throws Exception{
        if(rol.getValue().equals("tecnico")){
            ArrayList<String> departamentos = conectado.consultaListados("SELECT * FROM departamentos", "nombre_departamento", "");
            ObservableList<String> comboDepartamentos = FXCollections.observableArrayList(departamentos);
            departamento.setItems(comboDepartamentos);
        }else{
            departamento.getItems().clear();
            departamento.getItems().add("No aplica");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<String> roles = conectado.consultaListados("SELECT * FROM roles", "nombre_rol", "");
            ObservableList<String> comboRoles = FXCollections.observableArrayList(roles);
            rol.setItems(comboRoles);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
