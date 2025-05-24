/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class NotasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    OperacionesVentana operaciones = new OperacionesVentana();
    conection conectado = new conection();
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button guardar;
    @FXML
    public ComboBox<String> notas;
    @FXML
    public TextArea nota;
    LocalDate fecha = LocalDate.now();
    int id= conectado.getIdUsuario();
    
    @FXML
    public void cancelar(){
         int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar la configuración?", "Cancelar",JOptionPane.YES_NO_OPTION);
            if(opcionSeleccionada == JOptionPane.YES_OPTION){
                try {
                    operaciones.abrirVentana("Principal.fxml");
                    operaciones.cerrar(cancelar);
                } catch (IOException ex) {
                    Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    @FXML
    public void guardar() throws SQLException{
        if(notas.getValue()==null){
            JOptionPane.showMessageDialog(null, "Seleccione un ticket");
        }else{
            try {
                conectado.consultaDML("INSERT INTO documentacion(nota, id_ticket) VALUES('"+ nota.getText() +"', (SELECT id_ticket FROM tickets WHERE titulo = '"+ notas.getValue() +"'))");
                JOptionPane.showMessageDialog(null, "Se agregó la nota");
                HistorialTickets historial = new HistorialTickets(fecha, "Se agrego una nota al ticket(nombre = "+ notas.getValue() +") ",""+ id);
                operaciones.abrirVentana("Principal.fxml");
                operaciones.cerrar(guardar);
            } catch (IOException ex) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<String> listado2 = new ArrayList<>();
            listado2 = conectado.consultaListados("select titulo FROM tickets WHERE usuario = "+ id +" OR tecnico ='"+ id +"' ", "titulo", "");
            for(int i = 0; i<listado2.size(); i++){
                notas.getItems().add(listado2.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
