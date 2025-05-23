/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Tickets {
    private int numeroTicket;
    private String titulo;
    private String descripcion;
    private String departamento;
    private String prioridad;
    private String solicitud;
    private File[] documentos;
    private LocalDate fechaCreacion;
    private String estado;
    private String[] notas;
    private String tecnicoAsignado;
    private String flujoDeTrabajo;
    private String usuarioCreador;
    private File[] documentacion;
    private boolean vacio = true;

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        if(solicitud.equals("")){
            JOptionPane.showMessageDialog(null, "El campo SOLICITUD está vacío");
        }else{
            this.solicitud = solicitud;
            
        }
    }

    
    
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo.equals("")){
            JOptionPane.showMessageDialog(null, "El campo TITULO está vacío");
        }else{
            this.titulo = titulo;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if(departamento.equals("")){
            JOptionPane.showMessageDialog(null, "El campo DEPARTAMENTO está vacío");
        }else{
            this.departamento = departamento;
        }
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        if(prioridad.equals("")){
            JOptionPane.showMessageDialog(null, "El campo PRIORIDAD está vacío");
        }else{
            this.prioridad = prioridad;
        }
    }

    public File[] getDocumentos() {
        return documentos;
    }

    public void setDocumentos(File[] documentos) {
        for(int i=0; i<= documentos.length; i++){
            if(vacio==true){
                if(documentos[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.documentos[i] = documentos[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo DOCUMENTOS está vacío");
        }else{
            vacio = true;
        }
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        if(fechaCreacion.equals("")){
            System.out.println("Fecha creacion está vacío");;
        }else{
            this.fechaCreacion = fechaCreacion;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(estado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ESTADO está vacío");
        }else{
            this.estado = estado;
        }
    }

    public String[] getNotas() {
        return notas;
    }

    public void setNotas(String[] notas) {
        for(int i=0; i<= notas.length; i++){
            if(vacio==true){
                if(notas[i].equals("")){
                    vacio = true;
                }else{
                    vacio = false;
                }
            }
            this.notas[i] = notas[i];
        }
        
        if(vacio==true){
            JOptionPane.showMessageDialog(null, "El campo NOTAS está vacío");
        }else{
            vacio = true;
        }
    }

    public String getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(String tecnicoAsignado) {
        if(tecnicoAsignado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo TECNICO ASIGNADO está vacío");
        }else{
            this.tecnicoAsignado = tecnicoAsignado;
        }
    }

    public String getFlujoDeTrabajo() {
        return flujoDeTrabajo;
    }

    public void setFlujoDeTrabajo(String flujoDeTrabajo) {
        if(flujoDeTrabajo.equals("")){
            JOptionPane.showMessageDialog(null, "El campo FLLUJO DE TRABAJO está vacío");
        }else{
            this.flujoDeTrabajo = flujoDeTrabajo;
        }
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        if(usuarioCreador.equals("")){
            JOptionPane.showMessageDialog(null, "El campo USUARIO CREADOR está vacío");
        }else{
            this.usuarioCreador = usuarioCreador;
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
    
    public void crearTicket(){
    
    }
    
    public void notificarUsuario(){
    
    }
    
    public void notificarTecnicos(){
    
    }
    
}
