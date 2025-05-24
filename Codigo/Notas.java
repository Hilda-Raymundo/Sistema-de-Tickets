/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Notas {
    
    private int numeroTicket;
    private String descripcion;
    private File[] documentacion;
    private boolean vacio = true;

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        if(numeroTicket<0){
            System.out.println("El numero de ticket es invalido");
        }else{
            this.numeroTicket = numeroTicket;
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

    public File[] getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(File[] documentacion) {
        for(int i=0; i<= documentacion.length; i++){
            if(vacio==true){
                if(documentacion[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.documentacion[i] = documentacion[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo DOCUMENTACION está vacío");
        }else{
            vacio = true;
        }
    }
    
    public void consultarTickets(){
    
    }
    
    public void notificarTecnicos(){
    
    }
    
    public void notificarUsuarios(){
    
    }
    
}
