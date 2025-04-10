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
public class Historial {
    
    private int numeroModificacion;
    private Date fechaModificacion;
    private String usuario;
    private String descripcion;

    public Historial(int numeroModificacion, Date fechaModificacion, String usuario, String descripcion) {
        this.numeroModificacion = numeroModificacion;
        this.fechaModificacion = fechaModificacion;
        this.usuario = usuario;
        this.descripcion = descripcion;
    }
    
    
    
    public void mostrarHistorial(){
    
    }
    
}
