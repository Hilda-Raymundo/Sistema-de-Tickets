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
public class EliminarDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    public TextField nombreDepartamento;
    @FXML
    public TextArea descripcionDepartamento;
    @FXML
    public javafx.scene.control.Button eliminarDepartamento;
    @FXML
    public javafx.scene.control.Button cancelar;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaDepartamentos;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_departamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_departamento;
    
    @FXML
    public void eliminarDepartamento() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.eliminarDepartamento(eliminarDepartamento, nombreDepartamento.getText(), descripcionDepartamento.getText());        
    }
    
    @FXML
    public void seleccionarDepartamento(){
        DatosTableViewSinCheckbox dato = tablaDepartamentos.getSelectionModel().getSelectedItem();
            if(dato!= null){
                nombreDepartamento.setText(dato.getDato1());
                descripcionDepartamento.setText(dato.getDato2());
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
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.consultarDepartamentos(tablaDepartamentos, nombre_departamento, descripcion_departamento, descripcion_departamento);
    }    
    
}
