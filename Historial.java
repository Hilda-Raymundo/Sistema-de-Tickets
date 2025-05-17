/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
abstract class Historial {
    
    private LocalDate fechaModificacion;
    private String usuario;
    private String descripcion;

    public Historial(LocalDate fechaModificacion, String descripcion, String usuario) throws SQLException {
        this.fechaModificacion = fechaModificacion;
        this.usuario = usuario;
        this.descripcion = descripcion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if(usuario.equals("")){
            JOptionPane.showMessageDialog(null, "El campo USUARIO está vacío");
        }else{
            this.usuario = usuario;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion.equals("")){
            JOptionPane.showMessageDialog(null, "El campo DESCRIPCION está vacío");
        }else{
            this.descripcion = descripcion;
        }
    }
        
    public abstract void mostrarHistorial();
    
}
