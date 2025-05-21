/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class Administrador extends Persona{
    
    private Roles parametros = new Roles("", "");
    private Departamentos parametrosDepartamento = new Departamentos("", "");
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
                conectar.consultaDML("INSERT INTO roles(nombre_rol, descripcion_rol) VALUES( '"+ parametros.getNombre() +"', '"+ parametros.getDescripcion() +"' )");
                for(String permiso : permisos){
                    conectar.consultaDML("INSERT INTO roles_permisos(id_rol, id_permiso) VALUES((SELECT MAX(id_rol) FROM roles), (SELECT id_permiso FROM permisos WHERE nombre_permiso= '"+ permiso +"'));");
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
                conectar.consultaDML("update roles set descripcion_rol = '"+ parametros.getDescripcion() +"' where id_rol = (select id_rol from roles where nombre_rol = '"+ nombre + "')");
                conectar.consultaDML("delete from roles_permisos where id_rol = (select id_rol from roles where nombre_rol = '"+ nombre +"');");
                for(String permiso : permisos){
                    conectar.consultaDML("insert into roles_permisos(id_rol, id_permiso) VALUES((select id_rol from roles where nombre_rol = '"+ nombre +"'), (select id_permiso from permisos where nombre_permiso = '"+ permiso +"'))");
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
                    conectar.consultaDML("DELETE FROM roles WHERE nombre_rol = '"+ rolSeleccionado +"' AND descripcion_rol = '"+ descripcion +"' ");
                    conectar.consultaDML("delete from roles_permisos where id_rol = (select id_rol from roles where nombre_rol = '"+ rolSeleccionado +"' and descripcion_rol = '"+ descripcion +"' );");
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
            tabla.setItems(conectar.obtenerListado("SELECT * FROM roles;", "nombre_rol" , "descripcion_rol", "", ""));
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
                    conectar.consultaDML("INSERT INTO permisos(nombre_permiso, descripcion_permiso) VALUES('"+ parametros.getNombre() +"', '"+ parametros.getDescripcion() +"')");
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
                conectar.consultaDML("UPDATE permisos SET descripcion_permiso = '"+ parametros.getDescripcion() +"' WHERE nombre_permiso = '"+ nombrePermiso +"' ");
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
                conectar.consultaDML("DELETE FROM roles_permisos WHERE id_permiso = (SELECT id_permiso FROM permisos WHERE nombre_permiso = '"+ nombrePermiso +"' AND descripcion_permiso = '"+ descripcionPermiso +"')");
                conectar.consultaDML("DELETE FROM permisos WHERE nombre_permiso = '"+ nombrePermiso +"' AND descripcion_permiso = '"+ descripcionPermiso +"' ");
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
            tabla.setItems(conectar.obtenerListado("SELECT * FROM permisos;", "nombre_permiso", "descripcion_permiso", "", ""));
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
    
    public void crearDepartamento(Button cerrar, String nombreDepartamento, String descripcionDepartamento, ArrayList<String> tecnicos) throws IOException{        
        if(nombreDepartamento.equals("") || descripcionDepartamento.equals("") || tecnicos.size()<1){
            parametrosDepartamento.setNombre(nombreDepartamento);
            parametrosDepartamento.setDescripcion(descripcionDepartamento);
            parametrosDepartamento.setTecnicosAsignados(tecnicos);
        }else{
            parametrosDepartamento.setNombre(nombreDepartamento);
            parametrosDepartamento.setDescripcion(descripcionDepartamento);
            parametrosDepartamento.setTecnicosAsignados(tecnicos);
            try {
                int dato = conectar.buscar("SELECT * FROM departamentos WHERE nombre_departamento = '"+ parametrosDepartamento.getNombre()+"' ");
                if(dato>0){
                    JOptionPane.showMessageDialog(null, "Existe un departamento con el mismo nombre");
                }else{
                    conectar.consultaDML("INSERT INTO departamentos(nombre_departamento, descripcion_departamento) VALUES( '"+ parametrosDepartamento.getNombre() +"', '"+ parametrosDepartamento.getDescripcion() +"' )");
                    for(String tecnico : tecnicos){
                        conectar.consultaDML("UPDATE usuarios SET id_departamento = (SELECT MAX(id_departamento) FROM departamentos) WHERE nombre_usuario ='"+ tecnico +"' ");
                    }
                    int idDepartamento = conectar.buscarId("SELECT id_departamento FROM departamentos");
                    String nombreColaAtencion = "public.cola_de_atencion_" + idDepartamento;
                    conectar.consultaDDL("CREATE TABLE IF NOT EXISTS "+ nombreColaAtencion +" (id SERIAL PRIMARY KEY, id_ticket INT, id_tecnico INT, id_estado_ticket INT);");
                    JOptionPane.showMessageDialog(null, "Operacion exitosa!");
                    cerrar(cerrar);
                    abrirVentana("GestionDepartamento.fxml");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se creo el departamento(nombre = "+ parametrosDepartamento.getNombre() +", descripcion = "+ parametrosDepartamento.getDescripcion() +", permisos asignados = "+ parametros.getPermisosAsignados() +") ", "" + id);
                    }
                } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modificarDepartamento(Button cerrar, String nombre, String descripcion, ArrayList<String> tecnicos, ArrayList<String> tecnicosNoSeleccionados) throws IOException{
        if(nombre.equals("") || descripcion.equals("")|| tecnicos.size()<1){
            parametrosDepartamento.setNombre(nombre);
            parametrosDepartamento.setDescripcion(descripcion);
            parametrosDepartamento.setTecnicosAsignados(tecnicos);
        }else{
            parametrosDepartamento.setNombre(nombre);
            parametrosDepartamento.setDescripcion(descripcion);
            parametrosDepartamento.setTecnicosAsignados(tecnicos);
            
            try {
                conectar.consultaDML("update departamentos set descripcion_departamento = '"+ parametrosDepartamento.getDescripcion() +"' where id_departamento = (select id_departamento from departamentos where nombre_departamento = '"+ nombre + "');");
                for(String tecnico : tecnicos){
                    conectar.consultaDML("update usuarios set id_departamento = (select id_departamento from departamentos where nombre_departamento = '"+ parametrosDepartamento.getNombre() +"' and descripcion_departamento = '"+ parametrosDepartamento.getDescripcion() +"') where nombre_usuario = '"+ tecnico +"'");
                }
                for(String tecnico : tecnicosNoSeleccionados){
                    conectar.consultaDML("update usuarios set id_departamento = NULL where id_departamento = (select id_departamento from departamentos where nombre_departamento = '"+ parametrosDepartamento.getNombre() +"' and descripcion_departamento = '"+ parametrosDepartamento.getDescripcion() +"') AND nombre_usuario = '"+ tecnico +"'");
                }
                cerrar(cerrar);
                abrirVentana("GestionDepartamento.fxml");
                JOptionPane.showMessageDialog(null, "Operacion exitosa!");
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modifico el departamento(nombre = "+ nombre +", nueva descripcion = "+ parametrosDepartamento.getDescripcion() +", nuevos tecnicos = "+ tecnicos +") ", "" + id);
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarDepartamento(Button cerrar, String departamentoSeleccionado, String descripcion) throws IOException{
        if(departamentoSeleccionado.equals("") || descripcion.equals("")){
            parametrosDepartamento.setNombre(departamentoSeleccionado);
            parametrosDepartamento.setDescripcion(descripcion);
        }else{
            try {
                parametrosDepartamento.setNombre(departamentoSeleccionado);
                parametrosDepartamento.setDescripcion(descripcion);
                
                int dato = conectar.buscarId("SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ departamentoSeleccionado +"' AND descripcion_departamento = '"+ descripcion +"'");
                String tablename = "cola_de_atencion_" + dato;
                
                int tabla = conectar.buscar("SELECT tablename FROM pg_catalog.pg_tables WHERE tablename = '"+ tablename +"';");
                if(tabla>0){
                    JOptionPane.showMessageDialog(null, "Existe una cola de tickets relacionadas al sistema");
                }else{
                    conectar.consultaDML("UPDATE usuarios set id_departamento = null WHERE id_departamento = (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ parametrosDepartamento.getNombre() +"' AND descripcion_departamento = '"+ parametrosDepartamento.getDescripcion() +"') ");
                    conectar.consultaDML("DELETE FROM departamentos WHERE id_departamento = (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ parametrosDepartamento.getNombre() +"' AND descripcion_departamento = '"+ parametrosDepartamento.getDescripcion() +"')");
                    JOptionPane.showMessageDialog(null, "Se eliminó el departamento exitosamente");
                    cerrar(cerrar);
                    abrirVentana("GestionDepartamento.fxml");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se elimino el departamento(nombre = "+ departamentoSeleccionado +", nueva descripcion = "+ parametrosDepartamento.getDescripcion() +") ", "" + id);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void consultarDepartamentos(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2, TableColumn<DatosTableViewSinCheckbox, String> columna3){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            
            if(columna3.getText().equals("Tecnicos")){
                columna3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato3()));
                tabla.setItems(conectar.obtenerListado("SELECT * FROM departamentos;", "nombre_departamento", "descripcion_departamento", "select nombre_usuario from usuarios where id_rol = (select id_rol from roles where nombre_rol = 'tecnico');", "nombre_usuario"));
               }else{
                tabla.setItems(conectar.obtenerListado("SELECT * FROM departamentos;", "nombre_departamento", "descripcion_departamento", "", ""));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarTecnicos(TableView<DatosTableView> tabla, TableColumn<DatosTableView, String> columna1, TableColumn<DatosTableView, Boolean> columna2){
        try {  
            columna2.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            columna2.setCellFactory(CheckBoxTableCell.forTableColumn(columna2));
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            
                tabla.setItems(conectar.obtenerListadoYAsignarCheckbox("select * from usuarios where id_departamento IS NULL and id_estado = 1;", "nombre_usuario" , ""));
            tabla.setEditable(true);
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
