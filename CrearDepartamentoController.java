/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class CrearDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public javafx.scene.control.Button crearDepartamento;
    public javafx.scene.control.Button cancelar;
    public TextField nombreDepartamento;
    public TextArea descripcionDepartamento;
    
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaDepartamentos;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombre_Departamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcion_Departamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> tecnicos_asignados;
     @FXML
    public TableView<DatosTableView> tablaTecnicos;
    @FXML
    public TableColumn<DatosTableView, String> nombre_tecnico;
    @FXML
    public TableColumn<DatosTableView, Boolean> seleccion;
    
    Administrador admin = new Administrador("", "", "", "", "", "");
    OperacionesVentana operaciones = new OperacionesVentana();
    
    @FXML
    public void crearDepartamento() throws Exception{
        Administrador admin = new Administrador("", "", "", "", "", "");
        
        ObservableList<DatosTableView> tecnicos = tablaTecnicos.getItems();
        ArrayList<String> tecnicosSeleccionados = new ArrayList<>();

        for (DatosTableView tecnico : tecnicos) {
            if (tecnico.getCheckbox()) {
                tecnicosSeleccionados.add(tecnico.getNombre());
            }
        }
        admin.crearDepartamento(crearDepartamento, nombreDepartamento.getText(), descripcionDepartamento.getText(), tecnicosSeleccionados);
                
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
        admin.consultarDepartamentos(tablaDepartamentos, nombre_Departamento, descripcion_Departamento, tecnicos_asignados);
        admin.consultarTecnicos(tablaTecnicos, nombre_tecnico, seleccion);
    }    
    
}
