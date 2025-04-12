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
abstract class Persona extends OperacionesVentana{
    
    private String nombreCompleto;
    private String correo;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private String estado;

    public Persona(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.estado = estado;
    }   

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if(nombreCompleto.equals("")){
            JOptionPane.showMessageDialog(null, "El campo NOMBRE está vacío");
        }else{
            this.nombreCompleto = nombreCompleto;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo.equals("")){
            JOptionPane.showMessageDialog(null, "El campo correo está vacío");
        }else{
            this.correo = correo;
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        if(nombreUsuario.equals("")){
            JOptionPane.showMessageDialog(null, "El campo NOMBRE USUARIO está vacío");
        }else{
            this.nombreUsuario = nombreUsuario;
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if(contrasenia.equals("")){
            JOptionPane.showMessageDialog(null, "El campo CONTRASEÑA está vacío");
        }else{
            this.contrasenia = contrasenia;
        }
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if(rol.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ROL está vacío");
        }else{
        this.rol = rol;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(estado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ESTADO está vacío");
        }else{
            this.estado = estado;
        }
    }
        
    public abstract void consultarTickets();
    
    public void agregarNota(){
    
    }
    
}
