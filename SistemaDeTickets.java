/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemadetickets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hraym
 */
public class SistemaDeTickets extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage formulario) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        formulario.setTitle("Login");
        formulario.setScene(new Scene(root));
        formulario.show();
    }    
        
}
