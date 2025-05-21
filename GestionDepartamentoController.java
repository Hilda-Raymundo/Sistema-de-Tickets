/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public javafx.scene.control.Button crearDepartamento;
    public javafx.scene.control.Button modificarDepartamento;
    public javafx.scene.control.Button eliminarDepartamento;
    public javafx.scene.control.Button atras;
    @FXML
    public TableView<DatosTableViewSinCheckbox> tablaDepartamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> nombreDepartamento;
    @FXML
    public TableColumn<DatosTableViewSinCheckbox, String> descripcionDepartamento;
    
    @FXML
    public void crearDepartamento() throws Exception{
        operaciones.cerrar(crearDepartamento);
        operaciones.abrirVentana("CrearDepartamento.fxml");
    }
    
    @FXML
    public void modificarDepartamento() throws Exception{
        operaciones.cerrar(modificarDepartamento);
        operaciones.abrirVentana("ModificarDepartamento.fxml");
    }
    
    @FXML
    public void eliminarDepartamento() throws Exception{
        operaciones.cerrar(eliminarDepartamento);
        operaciones.abrirVentana("EliminarDepartamento.fxml");
    }
    
    @FXML
    public void atras() throws Exception{
        operaciones.cerrar(atras);
        operaciones.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Administrador admin = new Administrador("", "", "", "", "", "");
        admin.consultarDepartamentos(tablaDepartamento, nombreDepartamento, descripcionDepartamento);
    }    
    
}
