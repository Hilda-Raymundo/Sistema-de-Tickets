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
public class Administrador extends Persona{
    
    private Roles parametros = new Roles("", "", "");
    
    public Administrador(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
    }    
    
    public void establecerParametrosSistema(){
    
    }
    
    public void modificarParametrosSistema(){
    
    }
    
    public void crearRoles(Button cerrar, String nombre, String descripcion) throws IOException{
        parametros.setNombre(nombre);
        parametros.setDescripcion(descripcion);
        if(nombre.equals("") || descripcion.equals("")){
        }else{
        JOptionPane.showMessageDialog(null, "¡Creación exitosa!");
        cerrar(cerrar);
        abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void modificarRoles(Button modificar, String descripcion) throws IOException{
        parametros.setDescripcion(descripcion);
        if(descripcion.equals("")){
        }else{
        JOptionPane.showMessageDialog(null, "¡Modificación exitosa!");
        cerrar(modificar);
        abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void eliminarRoles(Button modificar, String rolSeleccionado) throws IOException{
        if(rolSeleccionado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo ROL está vacío");
        }else{
        JOptionPane.showMessageDialog(null, "¡Eliminación exitosa!");
        cerrar(modificar);
        abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void consultarRoles(){
        
    }
    
    public void asignarRoles(){
    
    }
    
    public void crearPermisos(Button cerrar, String nombrePermiso, String descripcionPermiso) throws IOException{
        parametros.setNombre(nombrePermiso);
        parametros.setDescripcion(descripcionPermiso);
        if(nombrePermiso.equals("") || descripcionPermiso.equals("")){
        }else{
        JOptionPane.showMessageDialog(null, "¡Creación exitosa!");
        cerrar(cerrar);
        abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void modificarPermisos(Button modificar, String descripcionPermiso) throws IOException{
        parametros.setDescripcion(descripcionPermiso);
        if(descripcionPermiso.equals("")){
        }else{
            JOptionPane.showMessageDialog(null, "¡Modificación Exitosa!");
            cerrar(modificar);
            abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void eliminarPermisos(Button eliminar, String permisoSeleccionado) throws IOException{
        if(permisoSeleccionado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo PERMISO está vacío");
        }else{
            JOptionPane.showMessageDialog(null, "¡Eliminación Exitosa!");
            cerrar(eliminar);
            abrirVentana("GestionRolesPermisos.fxml");
        }
    }
    
    public void asignarPermisos(){
    
    }
    
    public void consultarPermisos(){
    
    }
    
    public void crearTickets(){
    
    }
    
    public void editarTickets(){
    
    }
    
    public void eliminarTickets(){
        
    }
    
    public void cancelarTickets(){
    
    }
    
    public void asignarTickets(){
    
    }
    
    public void cambiarEstadoTickets(){
    
    }
    
    public void verReporteTickets(){
    
    }
    
    public void crearUsuarios(){
    
    }
    
    public void modificarUsuarios(){
    
    }
    
    public void eliminarUsuarios(){
    
    }
    
    public void consultarUsuarios(){
    
    }
    
    public void activarUsuario(){
    
    }
    
    public void desactivarUsuario(){
    
    }
    
    public void crearDepartamento(Button cerrar, String nombreDepartamento, String descripcionDepartamento) throws IOException{
        parametros.setNombre(nombreDepartamento);
        parametros.setDescripcion(descripcionDepartamento);
        if(nombreDepartamento.equals("") || descripcionDepartamento.equals("")){
        }else{
            JOptionPane.showMessageDialog(null, "¡Creación Exitosa!");
            cerrar(cerrar);
            abrirVentana("GestionDepartamento.fxml");
        }
    }
    
    public void modificarDepartamento(Button cerrar, String departamentoSeleccionado,String nombreDepartamento,String descripcionDepartamento) throws IOException{
        parametros.setNombre(nombreDepartamento);
        parametros.setDescripcion(descripcionDepartamento);
        if(nombreDepartamento.equals("") || descripcionDepartamento.equals("")){
        }else{
            JOptionPane.showMessageDialog(null, "Se modificó el departamento exitosamente");
            cerrar(cerrar);
            abrirVentana("GestionDepartamento.fxml");
        }
    }
    
    public void eliminarDepartamento(Button cerrar, String nombreDepartamentoSeleccionado) throws IOException{
        parametros.setNombre(nombreDepartamentoSeleccionado);
        if(nombreDepartamentoSeleccionado.equals("")){
        
        }else{
            JOptionPane.showMessageDialog(null, "Se eliminó el departamento exitosamente");
            cerrar(cerrar);
            abrirVentana("GestionDepartamento.fxml");
        }        
    }
    
    public void consultarDepartamentos(){
    
    }
    
    public void asignarTecnicos(){
    
    }
    
    public void crearFlujosTrabajo(){
    
    }
    
    public void modificarFlujosTrabajo(){
    
    }
    
    public void eliminarFlujosTrabajo(){
    
    }
    
    public void asignarFlujosTrabajo(){
    
    }
    
    public void crearEstado(){
    
    }
    
    public void modificarEstado(){
        
    }
    
    public void eliminarEstado(){
    
    }
    
    public void consultarEstado(){
    
    }
    
    @Override
    public void consultarTickets(){
        
    }
    
}
