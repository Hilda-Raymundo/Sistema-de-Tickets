/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.util.Date;

/**
 *
 * @author hraym
 */
public class CambiosEstado extends HistorialTickets{
    
    public CambiosEstado(int numeroTicket, int numeroModificacion, Date fechaModificacion, String usuario, String descripcion) {
        super(numeroTicket, numeroModificacion, fechaModificacion, usuario, descripcion);
    }
    
}
