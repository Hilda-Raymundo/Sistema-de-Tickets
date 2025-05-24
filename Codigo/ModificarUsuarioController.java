/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.awt.event.MouseEvent;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class ModificarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Administrador admin = new Administrador("", "", "", "", "", "");
    conection conectado = new conection();
    
    private static String id_usuario;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaUsuarios;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_usuario;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> correo_usuario;
    @FXML
    public javafx.scene.control.Button cancelar;
    @FXML
    public javafx.scene.control.Button modificarUsuario;
    @FXML
    public TextField nombreCompletoUsuario;
    @FXML
    public TextField correoUsuario;
    @FXML
    public TextField nombreUsuario;
    @FXML
    public TextField contraseniaUsuario;
    @FXML
    public ComboBox<String> roles;
    @FXML
    public ComboBox<String> departamentos;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    private void cancelar() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionUsuarios.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    private void seleccionarRol() throws Exception{
        if(roles.getValue().equals("tecnico")){
            ArrayList<String> lista_departamento = conectado.consultaListados("SELECT * FROM departamentos", "nombre_departamento", "");
            ObservableList<String> comboDepartamentos = FXCollections.observableArrayList(lista_departamento);
            departamentos.setItems(comboDepartamentos);
        }else{
            departamentos.getItems().clear();
            departamentos.getItems().add("no aplica");
        }
    }
    
    @FXML
    private void seleccionarUsuario(){        
        DatosTableViewSinCheckbox usuario = tablaUsuarios.getSelectionModel().getSelectedItem();
            if(usuario!= null){
            try {
                nombreUsuario.setText(usuario.getDato1());
                correoUsuario.setText(usuario.getDato2());
                ArrayList<String> parametros = new ArrayList<>();
                parametros = conectado.consultaListados("SELECT * FROM usuarios WHERE id_usuario = (SELECT id_usuario FROM usuarios WHERE nombre_usuario = '"+ nombreUsuario.getText() +"' AND correo_usuario = '"+ correoUsuario.getText() +"')", "nombre_completo", "contrasenia,id_rol,id_departamento,id_usuario");
                nombreCompletoUsuario.setText(parametros.get(0));
                contraseniaUsuario.setText(parametros.get(1));
                ArrayList<String> rolDelUsuario = new ArrayList<>();
                rolDelUsuario = conectado.consultaListados("SELECT nombre_rol FROM roles WHERE id_rol = "+ parametros.get(2) +" ", "nombre_rol", "");
                roles.setValue(rolDelUsuario.get(0));
                ArrayList<String> depaUsuario = new ArrayList<>();
                depaUsuario = conectado.consultaListados("SELECT nombre_departamento FROM departamentos WHERE id_departamento ="+ parametros.get(3) +" ", "nombre_departamento", "");
                departamentos.setValue(depaUsuario.toString());
                id_usuario = parametros.get(4);
            } catch (SQLException ex) {
                Logger.getLogger(ModificarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
    
    @FXML
    private void modificarUsuario() throws Exception{
        if(roles.getValue() == null){
            JOptionPane.showMessageDialog(null, "Seleccione un ROL");
        }else{
            if(roles.getValue().equals("tecnico") && departamentos.getValue() == null){
                JOptionPane.showMessageDialog(null, "Seleccione un DEPARTAMENTO");
            }else{
                admin.modificarUsuarios(modificarUsuario, nombreCompletoUsuario.getText(), correoUsuario.getText(), nombreUsuario.getText(), contraseniaUsuario.getText(), roles.getValue(), departamentos.getValue(), id_usuario);
            }
        }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.consultarUsuarios(tablaUsuarios, nombre_usuario, correo_usuario, correo_usuario);
        try {
            ArrayList<String> roles_lista = conectado.consultaListados("SELECT * FROM roles", "nombre_rol", "");
            ObservableList<String> comboRoles = FXCollections.observableArrayList(roles_lista);
            roles.setItems(comboRoles);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
