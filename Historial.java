/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
abstract class Historial {
    
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

    public int getNumeroModificacion() {
        return numeroModificacion;
    }

    public void setNumeroModificacion(int numeroModificacion) {
        if(numeroModificacion<0){
            System.out.println("El numero de modificacion es inválido");
        }else{
            this.numeroModificacion = numeroModificacion;
        }
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
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
