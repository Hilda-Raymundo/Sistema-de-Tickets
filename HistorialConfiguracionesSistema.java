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
public class HistorialConfiguracionesSistema extends Historial{
    
    public HistorialConfiguracionesSistema(int numeroModificacion, Date fechaModificacion, String usuario, String descripcion) {
        super(numeroModificacion, fechaModificacion, usuario, descripcion);
    }
    
    @Override
    public void mostrarHistorial(){
    
    }
    
}
