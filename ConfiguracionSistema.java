/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.awt.Image;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class ConfiguracionSistema {
    
    OperacionesVentana operaciones = new OperacionesVentana();
        
    private String nombreEmpresa;
    private Image logo;
    private String idioma;
    private String zonaHoraria;
    private int tiempoVencimientoTicketsInactivos;
    private String[] nivelesPrioridad = new String[6];
    private int cantidadNivelesPrioridad = 0;

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        if(nombreEmpresa.equals("")){
            System.out.println("El campo -Nombre de la empresa- está vacío");
        }else{
            this.nombreEmpresa = nombreEmpresa;
        }
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        if(idioma.equals("")){
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
            System.out.println("El tiempo de vencimiento ingresado no se encuentra dentro del rango permitido");
        }
    }

    public String[] getNivelesPrioridad() {
        return nivelesPrioridad;
    }

    public void setNivelesPrioridad(String nivelesPrioridad, int indice) {
       this.nivelesPrioridad[indice] = nivelesPrioridad; 
       this.cantidadNivelesPrioridad = indice + 1;
    }
    
    public void guardarConfiguracion() throws IOException{
            JOptionPane.showMessageDialog(null, "Se guardaron los parametros: \n"
                        + "Nombre de la empresa: " + this.nombreEmpresa + "\n"
                        + "Logo de la empresa: " + this.logo + "\n"
                        + "Idioma: " + this.idioma + "\n"
                        + "Zona horaria: " + this.zonaHoraria + "\n"
                        + "Tiempo días vencimiento: " + this.tiempoVencimientoTicketsInactivos + "\n"
                        + "Catidad de Niveles Prioridad: " + this.cantidadNivelesPrioridad);
    }
    
    public void cancelarConfiguracion(){
        this.nombreEmpresa = "";
        this.idioma = "";
        this.zonaHoraria = "";
        for(int i=0;i<nivelesPrioridad.length;i++){
            this.nivelesPrioridad[i] = "";
        }
    }
    
    public void enviarNotificaciones(){
        
    }
}
