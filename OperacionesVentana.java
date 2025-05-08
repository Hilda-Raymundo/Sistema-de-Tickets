/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hraym
 */
public class OperacionesVentana {
    
    @FXML
    public void abrirVentana(String direccion) throws IOException{
        Stage formulario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(direccion));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }
    
    @FXML
    public void cerrar(javafx.scene.control.Button volver) throws IOException{
       Stage formulario = (Stage) volver.getScene().getWindow();
       formulario.close();
    }
    
    public void usuarioQueIngreso(int idUsuario){
        System.out.println(idUsuario);
    }
    
}
