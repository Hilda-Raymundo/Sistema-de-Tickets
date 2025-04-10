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

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class UsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button crearTicket;
    public javafx.scene.control.Button consultarTicket;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button cancelarTicket;
    public javafx.scene.control.Button atras;
        
    @FXML
    private void crearTicket() throws IOException{
        abrir.abrirVentana("CrearTicket.fxml");
        cerrar.cerrar(crearTicket);
    }
    
    @FXML
    private void consultarTicket() throws IOException{
        abrir.abrirVentana("MisTickets.fxml");
        cerrar.cerrar(consultarTicket);
    }
    
    @FXML
    private void agregarNota() throws IOException{
        abrir.abrirVentana("Notas.fxml");
        cerrar.cerrar(agregarNota);
    }
    
    @FXML
    private void cancelarTicket() throws IOException{
        abrir.abrirVentana("SolicitudCancelacionTicket.fxml");
        cerrar.cerrar(cancelarTicket);
    }
    
    @FXML
    private void atras() throws IOException{
        abrir.abrirVentana("Principal.fxml");
        cerrar.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
