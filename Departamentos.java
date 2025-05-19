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
public class Departamentos extends ParametrosSistema{
    private String[] tecnicosAsignados;
    private String[] ticketsAsignados;
    private boolean vacio = true;

    public String[] getTecnicosAsignados() {
        return tecnicosAsignados;
    }

    public void setTecnicosAsignados(String[] tecnicosAsignados) {
        for(int i=0; i<= tecnicosAsignados.length; i++){
            if(vacio==true){
                if(tecnicosAsignados[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.tecnicosAsignados[i] = tecnicosAsignados[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo TECNICOS ASIGNADOS está vacío");
            for(int i = 0; i<tecnicosAsignados.length;i++){
                this.tecnicosAsignados[i] =  "";
            }
        }else{
            vacio = true;
        }
    }

    public String[] getTicketsAsignados() {
        return ticketsAsignados;
    }

    public void setTicketsAsignados(String[] ticketsAsignados) {
        for(int i=0; i<= ticketsAsignados.length; i++){
            if(vacio==true){
                if(ticketsAsignados[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.ticketsAsignados[i] = ticketsAsignados[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo TICKETS ASIGNADOS está vacío");
            for(int i = 0; i<ticketsAsignados.length;i++){
                this.ticketsAsignados[i] =  "";
            }
        }else{
            vacio = true;
        }
    }
    
    public Departamentos(String nombre, String descripcion) {
        super(nombre, descripcion);
    }
    
    public void crearColaAtencion(){
        
    }
    
    @Override
    public void consultarTickets(){
    
    }
}
