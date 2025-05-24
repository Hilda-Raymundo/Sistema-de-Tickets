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
public class FlujosDeTrabajo {
    
    private String nombre;
    private String[] estadosInvolucrados;
    private String[] transicionesPermitidas;
    private String[] reglasTransicion;
    private String[] accionesAutomaticas;
    private boolean vacio = true;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "El campo NOMBRE está vacío");
        }else{
            this.nombre = nombre;
        }
    }

    public String[] getEstadosInvolucrados() {
        return estadosInvolucrados;
    }

    public void setEstadosInvolucrados(String[] estadosInvolucrados) {
        for(int i=0; i<= estadosInvolucrados.length; i++){
            if(vacio==true){
                if(estadosInvolucrados[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.estadosInvolucrados[i] = estadosInvolucrados[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo ESTADOS INVOLUCRADOS está vacío");
            for(int i = 0; i<estadosInvolucrados.length;i++){
                this.estadosInvolucrados[i] =  "";
            }
        }else{
            vacio = true;
        }
    }

    public String[] getTransicionesPermitidas() {
        return transicionesPermitidas;
    }

    public void setTransicionesPermitidas(String[] transicionesPermitidas) {
        for(int i=0; i<= transicionesPermitidas.length; i++){
            if(vacio==true){
                if(transicionesPermitidas[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.transicionesPermitidas[i] = transicionesPermitidas[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo TRANSICIONES PERMITIDAS está vacío");
            for(int i = 0; i<transicionesPermitidas.length;i++){
                this.transicionesPermitidas[i] =  "";
            }
        }else{
            vacio = true;
        }
    }

    public String[] getReglasTransicion() {
        return reglasTransicion;
    }

    public void setReglasTransicion(String[] reglasTransicion) {
        for(int i=0; i<= reglasTransicion.length; i++){
            if(vacio==true){
                if(reglasTransicion[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.reglasTransicion[i] = reglasTransicion[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo REGLAS DE TRANSICIÓN está vacío");
            for(int i = 0; i<reglasTransicion.length;i++){
                this.reglasTransicion[i] =  "";
            }
        }else{
            vacio = true;
        }
    }

    public String[] getAccionesAutomaticas() {
        return accionesAutomaticas;
    }

    public void setAccionesAutomaticas(String[] accionesAutomaticas) {
        for(int i=0; i<= accionesAutomaticas.length; i++){
            if(vacio==true){
                if(accionesAutomaticas[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.accionesAutomaticas[i] = accionesAutomaticas[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo ACCIONES AUTOMATICAS está vacío");
            for(int i = 0; i<accionesAutomaticas.length;i++){
                this.accionesAutomaticas[i] =  "";
            }
        }else{
            vacio = true;
        }
    }    
    
    public void consultarTickets(){
    
    }
    
}
