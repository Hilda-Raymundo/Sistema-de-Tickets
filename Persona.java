/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

/**
 *
 * @author hraym
 */
public class Persona {
    
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
    
    public void consultarTickets(){
        
    }
    
    public void agregarNota(){
    
    }
    
}
