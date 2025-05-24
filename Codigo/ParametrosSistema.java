/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
abstract class ParametrosSistema extends OperacionesVentana{
    
    private String nombre;
    private String descripcion;
    private Boolean datosValidos = false;
    private Boolean nombreValido;
    private Boolean descripcionValida;

    public Boolean getDatosValidos() {
        if(nombreValido == true && descripcionValida == true){
            datosValidos = true;
        }else{
            datosValidos = false;
        }
        return datosValidos;
    }
    
    public ParametrosSistema(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "El campo NOMBRE está vacío");
            nombreValido = false;
        }else{
            nombreValido = true;
            this.nombre = nombre;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion.equals("")){
            JOptionPane.showMessageDialog(null, "El campo DESCRIPCION está vacío");
            descripcionValida = false;
        }else{
            this.descripcion = descripcion;
            descripcionValida = true;
        }
    }
        
    public abstract void consultarTickets();
   
}
