/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author hraym
 */
public class CerrarVentana {
    
    @FXML
    public void cerrar(javafx.scene.control.Button volver) throws IOException{
       Stage formulario = (Stage) volver.getScene().getWindow();
       formulario.close();
    }
    
}
