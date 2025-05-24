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
public class HistorialNotas extends HistorialTickets{
    
    public HistorialNotas(LocalDate fechaModificacion, String usuario, String descripcion) throws SQLException {
        super(fechaModificacion, usuario, descripcion);
    }
      
}
