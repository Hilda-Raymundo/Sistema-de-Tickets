/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class UsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    public javafx.scene.control.Button crearTicket;
    public javafx.scene.control.Button consultarTicket;
    public javafx.scene.control.Button agregarNota;
    public javafx.scene.control.Button cancelarTicket;
    public javafx.scene.control.Button atras;
    public ImageView logo;
        
    @FXML
    private void crearTicket() throws IOException{
        operaciones.abrirVentana("CrearTicket.fxml");
        operaciones.cerrar(crearTicket);
    }
    
    @FXML
    private void consultarTicket() throws IOException{
        operaciones.abrirVentana("MisTickets.fxml");
        operaciones.cerrar(consultarTicket);
    }
    
    @FXML
    private void agregarNota() throws IOException{
        operaciones.abrirVentana("Notas.fxml");
        operaciones.cerrar(agregarNota);
    }
    
    @FXML
    private void cancelarTicket() throws IOException{
        operaciones.abrirVentana("SolicitudCancelacionTicket.fxml");
        operaciones.cerrar(cancelarTicket);
    }
    
    @FXML
    private void atras() throws IOException{
        operaciones.abrirVentana("Principal.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> listado = new ArrayList<>();
        try {
            // TODO
            conection conectado = new conection();
            listado = conectado.consultaListados("SELECT * FROM configuracion_sistema", "logo_empresa");
            Image image = new Image("file:/C:/Users/hraym/OneDrive/Documentos/NetBeansProjects/SistemaDeTickets/src/sistemadetickets/imagenes/"+listado.get(0));
            logo.setImage(image);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
