/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button crearDepartamento;
    public javafx.scene.control.Button modificarDepartamento;
    public javafx.scene.control.Button eliminarDepartamento;
    public javafx.scene.control.Button atras;
    
    @FXML
    public void crearDepartamento() throws Exception{
        cerrar.cerrar(crearDepartamento);
        abrir.abrirVentana("CrearDepartamento.fxml");
    }
    
    @FXML
    public void modificarDepartamento() throws Exception{
        cerrar.cerrar(modificarDepartamento);
        abrir.abrirVentana("ModificarDepartamento.fxml");
    }
    
    @FXML
    public void eliminarDepartamento() throws Exception{
        cerrar.cerrar(eliminarDepartamento);
        abrir.abrirVentana("EliminarDepartamento.fxml");
    }
    
    @FXML
    public void atras() throws Exception{
        cerrar.cerrar(atras);
        abrir.abrirVentana("Admin.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
