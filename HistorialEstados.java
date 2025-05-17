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
public class HistorialEstados extends Historial{
    
    public HistorialEstados(LocalDate fechaModificacion, String descripcion, String usuario) throws SQLException {
        super(fechaModificacion, descripcion, usuario);
    }
    
    @Override
    public void mostrarHistorial(){
    
    }
    
}
