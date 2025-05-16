/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class ConfiguracionSistema {
    
    OperacionesVentana operaciones = new OperacionesVentana();
        
    private String nombreEmpresa;
    private String logo;
    private String idioma;
    private String zonaHoraria;
    private int tiempoVencimientoTicketsInactivos;
    private ArrayList<String> nivelesPrioridad;

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        if(nombreEmpresa.equals("")){
            this.nombreEmpresa = "";
            JOptionPane.showMessageDialog(null, "El campo -Nombre de la empresa- está vacío");
        }else{
            if(nombreEmpresa.length()>=3 && nombreEmpresa.length()<=100){
                this.nombreEmpresa = nombreEmpresa;
            }else{
                this.nombreEmpresa = "";
                JOptionPane.showMessageDialog(null, "El nombre de la empresa no cumple con la cantidad de caracteres necesarios");
            }
        }
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        if(logo.equals("")){
            this.logo = "";
            JOptionPane.showMessageDialog(null, "el logo no fue seleccionado");
        }else{
            this.logo = logo;
        }
    }
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        if(idioma.equals("")){
            this.idioma = "";
            JOptionPane.showMessageDialog(null, "El camp IDIOMA está vacío");
        }else{
            this.idioma = idioma;
        }
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        if(zonaHoraria.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ZONA HORARIA está vacío");
        }else{
            this.zonaHoraria = zonaHoraria;
        }
    }

    public int getTiempoVencimientoTicketsInactivos() {
        return tiempoVencimientoTicketsInactivos;
    }

    public void setTiempoVencimientoTicketsInactivos(int tiempoVencimientoTicketsInactivos) {
        if(tiempoVencimientoTicketsInactivos>=1 && tiempoVencimientoTicketsInactivos<=365){
            this.tiempoVencimientoTicketsInactivos = tiempoVencimientoTicketsInactivos;
        }else{
            this.tiempoVencimientoTicketsInactivos = 0;
            JOptionPane.showMessageDialog(null, "El tiempo de vencimiento ingresado no se encuentra dentro del rango permitido");
        }
    }

    public ArrayList<String> getNivelesPrioridad() {
        return nivelesPrioridad;
    }

    public void setNivelesPrioridad(ArrayList<String> nivelesPrioridad) {
        if(nivelesPrioridad.size() > 2 ){
            this.nivelesPrioridad = nivelesPrioridad;
        }else{
            JOptionPane.showMessageDialog(null, "Se deben configurar al menos 3 niveles de prioridad");
        }
    }
    
    public void guardarConfiguracion() throws IOException{
        conection conectar = new conection();
        
        if(!this.nombreEmpresa.equals("") && !this.idioma.equals("") && !this.zonaHoraria.equals("") && this.tiempoVencimientoTicketsInactivos >0 && !this.logo.equals("") && this.nivelesPrioridad != null){
            try {
                conectar.actualizarDatos("UPDATE configuracion_sistema SET nombre_empresa = '"+ this.nombreEmpresa +"', idioma = (SELECT id_idioma FROM idiomas WHERE nombre_idioma = '"+ this.idioma +"'), zona_horaria = (SELECT id_zona_horaria FROM zonas_horarias WHERE nombre_zona_horaria = '"+ this.zonaHoraria +"'), tiempo_vencimiento_tickets_inactivos = "+ this.tiempoVencimientoTicketsInactivos +", logo_empresa = '"+ this.logo +"' WHERE id_configuracion_sistema = 1");
                
            } catch (SQLException ex) {
                Logger.getLogger(ConfiguracionSistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cancelarConfiguracion(){
        this.nombreEmpresa = "";
        this.idioma = "";
        this.zonaHoraria = "";
        
    }
    
    public void enviarNotificaciones(){
        
    }
}
