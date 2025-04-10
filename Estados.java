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
public class Estados extends ParametrosSistema{
    
    private String estadoFinal;
    private String[] estadosPermitidosSiguientes;
    private boolean vacio = true;

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        if(estadoFinal.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ESTADO FINAL está vacío");
        }else{
            this.estadoFinal = estadoFinal;
        }
    }

    public String[] getEstadosPermitidosSiguientes() {
        return estadosPermitidosSiguientes;
    }

    public void setEstadosPermitidosSiguientes(String[] estadosPermitidosSiguientes) {
        for(int i=0; i<= estadosPermitidosSiguientes.length; i++){
            if(vacio==true){
                if(estadosPermitidosSiguientes[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.estadosPermitidosSiguientes[i] = estadosPermitidosSiguientes[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo TECNICOS ASIGNADOS está vacío");
            for(int i = 0; i<estadosPermitidosSiguientes.length;i++){
                this.estadosPermitidosSiguientes[i] =  "";
            }
        }else{
            vacio = true;
        }
    }    

    public Estados(String identificador, String nombre, String descripcion) {
        super(identificador, nombre, descripcion);
    }
    
}
