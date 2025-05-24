/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class HistorialTickets extends Historial{
    
    private String numeroTicket;

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        if(numeroTicket.equals("")){
            System.out.println("El campo NUMERO DE TICKET está vacío");
        }else{
            this.numeroTicket = numeroTicket;
        }
    }    

    public HistorialTickets(LocalDate fechaModificacion, String descripcion, String usuario) throws SQLException {
        super(fechaModificacion, usuario, descripcion);
        try{
            conection conectado = new conection();
            conectado.consultaDML("INSERT INTO historial_tickets(fecha_accion, descripcion, id_usuario) VALUES('"+ fechaModificacion +"', '"+ descripcion +"', "+ usuario +")");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ha habido un error en la conexion");
        }
    }   
    
    @Override
    public void mostrarHistorial(){
    
    }
    
}
