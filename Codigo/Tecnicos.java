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
public class Tecnicos extends Persona{
    
    private String departamento;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if(departamento == null || departamento.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo DEPARTAMENTO");
        }else{
            this.departamento = departamento;
        }
    }
        
    public Tecnicos(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
    }
    
    public void aprobarCancelacionTicket(){
        
    }
    
    public void resolverTicket(){
    
    }
    
    public void adjuntarDocumentacion(){
        
    }
    
    public void cambiarEstadoTicket(){
        
    }
    
    public void asignarTicket(){
        
    }
    
    @Override
    public void consultarTickets(){
        
    }
    
}
