/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author hraym
 */
public class CambiosEstado extends HistorialTickets{
    
    public CambiosEstado(int numeroTicket, LocalDate fechaModificacion, String usuario, String descripcion) throws SQLException {
        super(numeroTicket, fechaModificacion, usuario, descripcion);
    }
    
}
