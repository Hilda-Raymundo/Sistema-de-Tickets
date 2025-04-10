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
public class Permisos extends ParametrosSistema{
    
    private String[] rolesAsignados;
    private boolean vacio = true;

    public String[] getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(String[] rolesAsignados) {
        for(int i=0; i<= rolesAsignados.length; i++){
            if(vacio==true){
                if(rolesAsignados[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.rolesAsignados[i] = rolesAsignados[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo ROLES ASIGNADOS está vacío");
            for(int i = 0; i<rolesAsignados.length;i++){
                this.rolesAsignados[i] =  "";
            }
        }else{
            vacio = true;
        }
    }
    
    

    public Permisos(String identificador, String nombre, String descripcion) {
        super(identificador, nombre, descripcion);
    }
    
    public void asignarRoles(){
    
    }
    
}
