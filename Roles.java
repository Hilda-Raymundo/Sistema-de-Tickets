/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Roles extends ParametrosSistema{
    
    private String[] permisosAsignados;
    private boolean vacio = true;

    public String[] getPermisosAsignados() {
        return permisosAsignados;
    }

    public void setPermisosAsignados(String[] permisosAsignados) {
        for(int i=0; i<= permisosAsignados.length; i++){
            if(vacio==true){
                if(permisosAsignados[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.permisosAsignados[i] = permisosAsignados[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo ROLES ASIGNADOS está vacío");
            for(int i = 0; i<permisosAsignados.length;i++){
                this.permisosAsignados[i] =  "";
            }
        }else{
            vacio = true;
        }
    }
    
    public Roles(String identificador, String nombre, String descripcion) {
        super(identificador, nombre, descripcion);
    }
    
    public void asignarUsuario(){
        
    }
    
    public void asignarPermiso(){
    
    }
    
    @Override
    public void consultarTickets(){
    
    }
    
}
