/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Roles extends ParametrosSistema{
    
    private ArrayList<String> permisosAsignados;

    public ArrayList<String> getPermisosAsignados() {
        return permisosAsignados;
    }

    public void setPermisosAsignados(ArrayList<String> permisosAsignados) {
        if(permisosAsignados.size()<1){
            JOptionPane.showMessageDialog(null, "Seleccione permisos a asignar");
        }else{
            this.permisosAsignados = permisosAsignados;
        }
    }
        
    public Roles(String nombre, String descripcion) {
        super(nombre, descripcion);
    }
    
    public void asignarUsuario(){
        
    }
    
    public void asignarPermiso(){
    
    }
    
    @Override
    public void consultarTickets(){
    
    }
    
}
