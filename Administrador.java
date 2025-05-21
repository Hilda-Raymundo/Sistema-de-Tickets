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
    LocalDate fecha = LocalDate.now();
    int id= conectar.getIdUsuario();
    
    public Administrador(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
    }    
    
    public void establecerParametrosSistema(){
    
    }
    
    public void modificarParametrosSistema(){
    
    }
    
    public void crearRoles(Button crear, String nombre, String descripcion, ArrayList<String> permisos) throws IOException{
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
                cerrar(crear);
                abrirVentana("GestionRolesPermisos.fxml");
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
                conectar.eliminarDatos("delete from roles_permisos where id_rol = (select id_rol from roles where nombre_rol = '"+ nombre +"');");
                for(String permiso : permisos){
                    conectar.insertarDatos("insert into roles_permisos(id_rol, id_permiso) VALUES((select id_rol from roles where nombre_rol = '"+ nombre +"'), (select id_permiso from permisos where nombre_permiso = '"+ permiso +"'))");
                }
                cerrar(modificar);
                abrirVentana("GestionRolesPermisos.fxml");
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modifico el rol(nombre = "+ nombre +", nueva descripcion = "+ parametros.getDescripcion() +", nuevos permisos = "+ permisos +") ", "" + id);
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            parametros.setDescripcion("");
            parametros.setNombre(nombre);
        }
    }
    
    public void eliminarRoles(Button eliminar, String rolSeleccionado, String descripcion) throws IOException{
        if(rolSeleccionado.equals("")){
            JOptionPane.showMessageDialog(null, "El campo Nombre está vacío");
        }else{
            try {
                int rol = conectar.buscar("select * from usuarios where id_rol = (select id_rol from roles where nombre_rol = '"+ rolSeleccionado +"');");
                
                if(rol>0){
                    JOptionPane.showMessageDialog(null, "Existen usuarios con este rol, ¡modifique el rol de los usuarios antes de eliminar el rol!");
                }else{
                    conectar.eliminarDatos("DELETE FROM roles WHERE nombre_rol = '"+ rolSeleccionado +"' AND descripcion_rol = '"+ descripcion +"' ");
                    conectar.eliminarDatos("delete from roles_permisos where id_rol = (select id_rol from roles where nombre_rol = '"+ rolSeleccionado +"' and descripcion_rol = '"+ descripcion +"' );");
                    JOptionPane.showMessageDialog(null, "¡Eliminación exitosa!");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se eliminó el rol(nombre = "+ rolSeleccionado +", descripcion = "+ descripcion +") ", "" + id);
                }
                cerrar(eliminar);
                abrirVentana("GestionRolesPermisos.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        
    public void crearPermisos(Button cerrar, String nombrePermiso, String descripcionPermiso) throws IOException{
        if(nombrePermiso.equals("") || descripcionPermiso.equals("")){
            parametros.setNombre(nombrePermiso);
            parametros.setDescripcion(descripcionPermiso);
        }else{
            try {
                parametros.setNombre(nombrePermiso);
                parametros.setDescripcion(descripcionPermiso);
                int dato = conectar.buscar("SELECT * FROM permisos WHERE nombre_permiso = '"+ parametros.getNombre() +"' ");
                if(dato>0){
                    JOptionPane.showMessageDialog(null, "Existe un permiso con el mismo nombre");
                }else{
                    conectar.insertarDatos("INSERT INTO permisos(nombre_permiso, descripcion_permiso) VALUES('"+ parametros.getNombre() +"', '"+ parametros.getDescripcion() +"')");
                    JOptionPane.showMessageDialog(null, "¡Creación exitosa!");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se creó el permiso(nombre = "+ parametros.getNombre() +", descripcion = "+ parametros.getDescripcion() +") ", "" + id);
                    cerrar(cerrar);
                    abrirVentana("GestionRolesPermisos.fxml");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modificarPermisos(Button modificar, String nombrePermiso, String descripcionPermiso) throws IOException{
        if(descripcionPermiso.equals("")){
            parametros.setNombre(nombrePermiso);
            parametros.setDescripcion(descripcionPermiso);
        }else{
            try {
                parametros.setDescripcion(descripcionPermiso);
                conectar.actualizarDatos("UPDATE permisos SET descripcion_permiso = '"+ parametros.getDescripcion() +"' WHERE nombre_permiso = '"+ nombrePermiso +"' ");
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modificó el permiso(nombre = "+ parametros.getNombre() +", descripcion = "+ parametros.getDescripcion() +") ", "" + id);
                cerrar(modificar);
                abrirVentana("GestionRolesPermisos.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarPermisos(Button eliminar, String nombrePermiso, String descripcionPermiso) throws IOException{
        if(nombrePermiso.equals("") || descripcionPermiso.equals("")){
            parametros.setNombre(nombrePermiso);
            parametros.setDescripcion(descripcionPermiso);
        }else{
            try {
                conectar.eliminarDatos("DELETE FROM roles_permisos WHERE id_permiso = (SELECT id_permiso FROM permisos WHERE nombre_permiso = '"+ nombrePermiso +"' AND descripcion_permiso = '"+ descripcionPermiso +"')");
                conectar.eliminarDatos("DELETE FROM permisos WHERE nombre_permiso = '"+ nombrePermiso +"' AND descripcion_permiso = '"+ descripcionPermiso +"' ");
                JOptionPane.showMessageDialog(null, "Operación exitosa!");
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se eliminó el permiso(nombre = "+ parametros.getNombre() +", descripcion = "+ parametros.getDescripcion() +") ", "" + id);
                cerrar(eliminar);
                abrirVentana("GestionRolesPermisos.fxml");
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void consultarDepartamentos(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            tabla.setItems(conectar.obtenerListado("SELECT * FROM departamentos;", "nombre_departamento", "descripcion_departamento"));
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
