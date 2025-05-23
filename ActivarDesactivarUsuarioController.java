/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class ActivarDesactivarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Administrador admin = new Administrador("", "", "", "", "", "");
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button guardar;
    @FXML
    public TableView<DatosTableView> tablaUsuarios;
    @FXML
    public TableColumn<DatosTableView, String> nombre_usuario;
    @FXML
    public TableColumn<DatosTableView, Boolean> estado;
    @FXML
    public TextField usuarioSeleccionado;
    @FXML
    public CheckBox activado;
    @FXML
    public CheckBox desactivado;
    private int nuevoEstado;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    
    @FXML
    private void activar(){
        nuevoEstado = 1;
        desactivado.setSelected(false);
    }
    
    @FXML
    private void desactivar(){
        nuevoEstado = 2;
        activado.setSelected(false);
    }
    
    @FXML
    private void seleccionarUsuario(){
        DatosTableView dato = tablaUsuarios.getSelectionModel().getSelectedItem();
            if(dato!= null){
                usuarioSeleccionado.setText(dato.getNombre());
            }
    }
    
    @FXML
    private void cancelar() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionUsuarios.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    private void guardar() throws Exception{
        if(!usuarioSeleccionado.getText().equals("")){
            if(activado.isSelected() || desactivado.isSelected()){
                admin.activarDesactivarUsuario(guardar, usuarioSeleccionado.getText(), nuevoEstado);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un checbox");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
        }
//        JOptionPane.showMessageDialog(null, "Se guardaron los cambios exitosamente");
//        operaciones.abrirVentana("GestionUsuarios.fxml");
//        operaciones.cerrar(guardar);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {  
            estado.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            estado.setCellFactory(CheckBoxTableCell.forTableColumn(estado));
            nombre_usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            estado.setEditable(false);
            tablaUsuarios.setItems(conectado.obtenerListadoYAsignarCheckbox("SELECT u.nombre_usuario, (SELECT id_estado FROM estados_usuarios t WHERE t.id_estado = u.id_estado) FROM usuarios u;", "nombre_usuario" , "id_estado", "id_estado_usuario"));
            tablaUsuarios.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
