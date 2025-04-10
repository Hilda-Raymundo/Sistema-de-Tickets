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
public class HistorialTickets extends Historial{
    
    private int numeroTicket;

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        if(numeroTicket<0){
            System.out.println("El campo NUMERO DE TICKET está vacío");
        }else{
            this.numeroTicket = numeroTicket;
        }
    }    

    public HistorialTickets(int numeroTicket, int numeroModificacion, Date fechaModificacion, String usuario, String descripcion) {
        super(numeroModificacion, fechaModificacion, usuario, descripcion);
        this.numeroTicket = numeroTicket;
    }   
    
}
