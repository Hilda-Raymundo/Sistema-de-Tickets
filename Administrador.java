/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Administrador extends Persona{
    
    private Roles parametros = new Roles("", "");
    conection conectar = new conection();
    
    public Administrador(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
    }    
    
    public void establecerParametrosSistema(){
    
    }
    
    public void modificarParametrosSistema(){
    
    }
    
    public void crearRoles(Button cerrar, String nombre, String descripcion, ArrayList<String> permisos) throws IOException{
        if(nombre.equals("") || descripcion.equals("") || permisos.size()<1){
            parametros.setNombre(nombre);
            parametros.setDescripcion(descripcion);
            parametros.setPermisosAsignados(permisos);
        }else{
            parametros.setNombre(nombre);
            parametros.setDescripcion(descripcion);
            try {
                conectar.insertarDatos("INSERT INTO roles(nombre_rol, descripcion_rol) VALUES( '"+ parametros.getNombre() +"', '"+ parametros.getDescripcion() +"' )");
                for(String permiso : permisos){
                    conectar.insertarDatos("INSERT INTO roles_permisos(id_rol, id_permiso) VALUES((SELECT MAX(id_rol) FROM roles), (SELECT id_permiso FROM permisos WHERE nombre_permiso= '"+ permiso +"'));");
                }
                JOptionPane.showMessageDialog(null, "Operacion exitosa!");
                cerrar(cerrar);
                abrirVentana("GestionRolesPermisos.fxml");
                LocalDate fecha = LocalDate.now();
                int id= conectar.getIdUsuario();
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se guardo el rol(nombre = "+ parametros.getNombre() +", descripcion = "+ parametros.getDescripcion() +", permisos asignados = "+ permisos +") ", "" + id);
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modificarRoles(Button modificar, String nombre, String descripcion, ArrayList<String> permisos) throws IOException{
        if(!descripcion.equals("") && !nombre.equals("")){
            parametros.setDescripcion(descripcion);
            parametros.setPermisosAsignados(permisos);
            try {
                conectar.actualizarDatos("update roles set descripcion_rol = '"+ parametros.getDescripcion() +"' where id_rol = (select id_rol from roles where nombre_rol = '"+ nombre + "')");
//                conectar.eliminarDatos("DELETE FROM roles_permisos WHERE id_rol IN (SELECT id_rol from roles where nombre_rol = '"+ nombre +"' ) ");
                for(String permiso : permisos){
                    conectar.insertarDatos("insert into roles_permisos(id_rol, id_permiso) VALUES((select id_rol from roles where nombre_rol = '"+ nombre +"'), (select id_permiso from permisos where nombre_permiso = '"+ permiso +"'))");
                }
                cerrar(modificar);
                abrirVentana("GestionRolesPermisos.fxml");
                LocalDate fecha = LocalDate.now();
                int id= conectar.getIdUsuario();
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modifico el rol(nombre = "+ nombre +", nueva descripcion = "+ parametros.getDescripcion() +", nuevos permisos = "+ permisos +") ", "" + id);
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            parametros.setDescripcion("");
            parametros.setNombre(nombre);
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
    
    public void consultarRoles(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2){
        try {
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            tabla.setItems(conectar.obtenerListado("SELECT * FROM roles;", "nombre_rol" , "descripcion_rol"));
            tabla.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void consultarPermisos(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            tabla.setItems(conectar.obtenerListado("SELECT * FROM permisos;", "nombre_permiso", "descripcion_permiso"));
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
