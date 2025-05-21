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
public class Departamentos extends ParametrosSistema{
    
    private ArrayList<String> tecnicosAsignados;

    public ArrayList<String> getTecnicosAsignados() {
        return tecnicosAsignados;
    }
    
    public void setTecnicosAsignados(ArrayList<String> tecnicosAsignados) {
        if(tecnicosAsignados.size()<1){
            JOptionPane.showMessageDialog(null, "Seleccione tecnicos a asignar");
        }else{
            this.tecnicosAsignados = tecnicosAsignados;
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
