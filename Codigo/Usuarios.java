/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;

/**
 *
 * @author hraym
 */
public class Usuarios extends Persona{
    

    public Usuarios(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) throws IOException {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
    }
    
    public void crearTicket(){
    
    }
    
    public void cancelarTicket(){
    
    }
    
    @Override
    public void consultarTickets(){
        
    }
    
}
