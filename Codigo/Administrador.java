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
    private Tecnicos tecnico = new Tecnicos("", "", "", "","", "");
    private EstadosTicket estadosTicket = new EstadosTicket("", "");
    FlujosDeTrabajo flujo = new FlujosDeTrabajo();
    conection conectar = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectar.getIdUsuario();
    
    public Administrador(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        super(nombreCompleto, correo, nombreUsuario, contrasenia, rol, estado);
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
            tabla.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void editarTickets(){
    
    }
    
    public void eliminarTickets(){
        
    }
    
    public void cancelarTickets(){
    
    }
    
    public void asignarTickets(){
    
    }
    
    public void cambiarEstadoTickets(Button cerrar, String consulta){
        try {
            conectar.consultaDML(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verReporteTickets(){
    
    }
    
    public void crearUsuarios(Button cerrar, String nombre, String correo, String nombreUsuario, String contrasenia, String rol, String departamento) throws IOException{
        tecnico.setNombreCompleto(nombre);
        tecnico.setCorreo(correo);
        tecnico.setNombreUsuario(nombreUsuario);
        tecnico.setContrasenia(contrasenia);
        tecnico.setRol(rol);
        tecnico.setDepartamento(departamento);
        
        if(tecnico.getDatosValidos()==true) {  
            try {                
                int buscarCorreo = conectar.buscar("select * from usuarios where correo_usuario = '"+ tecnico.getCorreo() +"' LIMIT 1");
                int buscarNombreUsuario = conectar.buscar("select * from usuarios where nombre_usuario = '"+ tecnico.getNombreUsuario() +"' LIMIT 1");
                
                if(buscarCorreo>0){
                    JOptionPane.showMessageDialog(null, "El correo ya está registrado");
                }else if(buscarNombreUsuario>0){
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado");
                }else{
                    if(departamento.equals("") || departamento.equals("no aplica") || departamento.equals(null)){
                        conectar.consultaDML("insert into usuarios(nombre_completo, correo_usuario, nombre_usuario, contrasenia, id_rol, id_estado) VALUES('"+ tecnico.getNombreCompleto()+"', '"+ tecnico.getCorreo() +"', '"+ tecnico.getNombreUsuario() +"', '"+ tecnico.getContrasenia() +"', (SELECT id_rol FROM roles WHERE nombre_rol = '"+ tecnico.getRol() +"'), 1)");
                    }else{
                        conectar.consultaDML("insert into usuarios(nombre_completo, correo_usuario, nombre_usuario, contrasenia, id_rol, id_estado, id_departamento) VALUES('"+ tecnico.getNombreCompleto()+"', '"+ tecnico.getCorreo() +"', '"+ tecnico.getNombreUsuario() +"', '"+ tecnico.getContrasenia() +"', (SELECT id_rol FROM roles WHERE nombre_rol = '"+ tecnico.getRol() +"'), 1, (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ tecnico.getDepartamento() +"'))");
                    }
                    JOptionPane.showMessageDialog(null, "Operación exitosa!");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se creó el usuario(nombre = "+ tecnico.getNombreUsuario()+", correo = "+ tecnico.getCorreo()+") ", "" + id);
                    cerrar(cerrar);
                    abrirVentana("GestionUsuarios.fxml");
                    enviarNotificaciones(tecnico.getCorreo(), "Estas son sus credenciales \n El nombre del usuario es: " + nombreUsuario,  " La contrasenia es: " + contrasenia);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modificarUsuarios(Button cerrar, String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String departamento, String id){
        try {
            tecnico.setNombreCompleto(nombreCompleto);
            tecnico.setCorreo(correo);
            tecnico.setNombreUsuario(nombreUsuario);
            tecnico.setContrasenia(contrasenia);
            tecnico.setRol(rol);
            tecnico.setDepartamento(departamento);
            
            if(tecnico.getDatosValidos() == true){
                int buscarCorreo = conectar.buscar("select * from usuarios where correo_usuario = '"+ tecnico.getCorreo()+"' LIMIT 1");
                int buscarNombreUsuario = conectar.buscar("select * from usuarios where nombre_usuario = '"+ tecnico.getNombreUsuario() +"' LIMIT 1");
                
                if(buscarCorreo>0){
                    JOptionPane.showMessageDialog(null, "El correo ya está registrado");
                }else if(buscarNombreUsuario>0){
                    JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado");
                }else{
                    if(departamento.equals("") || departamento.equals("no aplica") || departamento == (null)){
                        conectar.consultaDML("UPDATE usuarios SET nombre_completo = '"+ tecnico.getNombreCompleto()+"', correo_usuario = '"+ tecnico.getCorreo() +"', nombre_usuario = '"+ tecnico.getNombreUsuario() +"', contrasenia = '"+ tecnico.getContrasenia() +"', id_rol = (SELECT id_rol FROM roles WHERE nombre_rol ='"+ tecnico.getRol() +"'), id_departamento = null WHERE id_usuario = '"+ id +"'");
                    }else{
                        conectar.consultaDML("UPDATE usuarios SET nombre_completo = '"+ tecnico.getNombreCompleto()+"', correo_usuario = '"+ tecnico.getCorreo() +"', nombre_usuario = '"+ tecnico.getNombreUsuario() +"', contrasenia = '"+ tecnico.getContrasenia() +"', id_rol = (SELECT id_rol FROM roles WHERE nombre_rol ='"+ tecnico.getRol() +"'), id_departamento = (SELECT id_departamento FROM departamentos WHERE nombre_departamento ='"+ tecnico.getDepartamento()+"') WHERE id_usuario = '"+ id +"'");
                    }
                    JOptionPane.showMessageDialog(null, "Operación exitosa!");
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modificó el usuario(nombre = "+ tecnico.getNombreUsuario()+", correo = "+ tecnico.getCorreo()+") ", "" + id);
                    cerrar(cerrar);
                    abrirVentana("GestionUsuarios.fxml");
                }                
            }else{
                System.out.println("error");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarUsuarios(){
    
    }
    
    public void consultarUsuarios(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2, TableColumn<DatosTableViewSinCheckbox, String> columna3){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
            tabla.setItems(conectar.obtenerListado("SELECT * FROM usuarios;", "nombre_usuario", "correo_usuario", "", ""));
            tabla.setEditable(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void activarDesactivarUsuario(Button cerrar, String nombreUsuario, int nuevoEstado){
        try {
            conectar.consultaDML("UPDATE usuarios SET id_estado = "+ nuevoEstado +" WHERE nombre_usuario = '"+ nombreUsuario +"' ");
            HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modificó el usuario(nombre = "+ nombreUsuario +", estado = "+ nuevoEstado +") ", "" + id);
            JOptionPane.showMessageDialog(null, "Operacion exitosa!");
            cerrar(cerrar);
            abrirVentana("GestionUsuarios.fxml");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
            tabla.setItems(conectar.obtenerListadoYAsignarCheckbox("select * from usuarios where id_rol = 2 and id_departamento is null;", "nombre_usuario" , "", ""));
            tabla.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarTecnicos(){
    
    }
    
    public void crearFlujosTrabajo(Button cerrar, String nombreFlujo, ArrayList<String> estadoInicial, ArrayList<String> estadoSiguiente){
        try {
            if(!nombreFlujo.equals("") && estadoInicial.size()!=0){
                flujo.setNombre(nombreFlujo);
                
                int buscarNombre = conectar.buscar("SELECT * FROM flujo_trabajo WHERE nombre_flujo = '"+ flujo.getNombre() +"' ");
                if(buscarNombre>0){
                    JOptionPane.showMessageDialog(null, "El nombre de flujo ya esta registrado");
                }else{
                
                    conectar.consultaDML("INSERT INTO flujo_trabajo(nombre_flujo) VALUES('"+ flujo.getNombre() +"')");

                    int idEstado = conectar.buscar("SELECT * FROM estados_ticket WHERE nombre_estado = '"+ estadoInicial.get(0)+"' AND estado_final = 1");
                    System.out.println(idEstado);
                    if(idEstado == 0){
                        for(String estado: estadoInicial){
                            if(estadoSiguiente.size()>0){
                                for(String siguiente: estadoSiguiente){
                                        int dato = conectar.buscar("SELECT id_estado_transicion FROM estados_transicion WHERE estado_siguiente = (SELECT id_estado FROM estados_ticket WHERE nombre_estado ='"+ siguiente +"') AND estado_inicial = (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"') LIMIT 1");
                                        System.out.println(dato);
                                        if(dato>0){
                                            conectar.consultaDML("INSERT INTO estados_flujo(id_flujo, estado_actual, estado_siguiente) VALUES((SELECT MAX(id_flujo) FROM flujo_trabajo), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"' ), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ siguiente +"') )");
                                        }else{
                                            System.out.println("El sistema descartara estados que no pueden transicionar entre si");
                                        }
                                    }
                            }else{
                                conectar.consultaDML("INSERT INTO estados_flujo(id_flujo, estado_actual) VALUES((SELECT MAX(id_flujo) FROM flujo_trabajo), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"'))");
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Se creó el flujo de trabajo exitosamente");
                        abrirVentana("GestionFlujosDeTrabajo.fxml"); 
                        cerrar(cerrar);
                    }else{
                       conectar.consultaDML("INSERT INTO estados_flujo(id_flujo, estado_actual) VALUES((SELECT MAX(id_flujo) FROM flujo_trabajo), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estadoInicial.get(0) +"'))");
                       JOptionPane.showMessageDialog(null, "Se creó");
                        abrirVentana("GestionFlujosDeTrabajo.fxml"); 
                        cerrar(cerrar);
                    }
                }
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se creo el flujo(nombre = "+ flujo.getNombre() +") ", "" + id);
            }else{
                JOptionPane.showMessageDialog(null, "Revise los datos ingresaos");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarFlujosTrabajo(Button cerrar, String nombreFlujo, ArrayList<String> estadoInicial, ArrayList<String> estadoSiguiente){
        try {
            if(!nombreFlujo.equals("") && estadoInicial.size()!=0){
                flujo.setNombre(nombreFlujo);
                
                for(String estado: estadoInicial){
                            if(estadoSiguiente.size()>0){
                                for(String siguiente: estadoSiguiente){
                                        conectar.consultaDML("INSERT INTO estados_flujo(id_flujo, estado_actual, estado_siguiente) VALUES((SELECT id_flujo FROM flujo_trabajo WHERE nombre_flujo = '"+ flujo.getNombre() +"'), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"' ), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ siguiente +"') )");
                                    }
                            }else{
                                conectar.consultaDML("INSERT INTO estados_flujo(id_flujo, estado_actual) VALUES((SELECT MAX(id_flujo) FROM flujo_trabajo), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"'))");
                            }
                        }
                JOptionPane.showMessageDialog(null, "modificacion exitosa!");
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modifico el flujo(nombre = "+ flujo.getNombre() +") ", "" + id);
            }else{
                JOptionPane.showMessageDialog(null, "Revise los datos ingresaos");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarFlujosTrabajo(){
    
    }
    
    public void asignarFlujosTrabajo(){
    
    }
    
    public void crearEstado(Button cerrar, String nombre, String descripcion, int es_final, ArrayList<String> estadosSiguientes){
        try {
            if(nombre.length()>2 && nombre.length()<51){
                estadosTicket.setNombre(nombre);
                estadosTicket.setDescripcion(descripcion);
                if(estadosTicket.getDatosValidos() == true){
                    int dato = conectar.buscar("SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estadosTicket.getNombre() +"'");
                    if(dato>0){
                        JOptionPane.showMessageDialog(null, "Ya existe un estado con ese nombre");
                    }else{
                        conectar.consultaDML("INSERT INTO estados_ticket(nombre_estado, descripcion_estado, estado_final) VALUES('"+ estadosTicket.getNombre() +"', '"+ estadosTicket.getDescripcion() +"', "+ es_final +")");
                            for(String estado: estadosSiguientes){
                                conectar.consultaDML("INSERT INTO estados_transicion(estado_inicial, estado_siguiente) VALUES((SELECT MAX(id_estado) FROM estados_ticket), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"' ))");
                            }
                        JOptionPane.showMessageDialog(null, "Se creó el estado exitosamente");
                        abrirVentana("GestionEstadosTicket.fxml");
                        cerrar(cerrar);
                        HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se creo el estado(nombre = "+ estadosTicket.getNombre() +", descripcion = "+ estadosTicket.getDescripcion() +") ", "" + id);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Revise la cantidad de caracteres en NOMBRE");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarEstado(Button cerrar, String nombre, String descripcion, int estadoFinal, ArrayList<String> estadosSiguientes, String idEstados){
        try {
                estadosTicket.setNombre(nombre);
                estadosTicket.setDescripcion(descripcion);
                if(!nombre.equals("") && !descripcion.equals("")){
                    conectar.consultaDML("UPDATE estados_ticket SET nombre_estado = '"+ estadosTicket.getNombre() +"', descripcion_estado = '"+ estadosTicket.getDescripcion() +"', estado_final = "+ estadoFinal +" WHERE nombre_estado = '"+ idEstados +"' ");
                    conectar.consultaDML("DELETE FROM estados_transicion WHERE estado_inicial = (SELECT id_estado FROM estados_TICKET WHERE nombre_estado = '"+ idEstados +"') ");

                    if(estadoFinal>1){
                        for(String estado: estadosSiguientes){
                            conectar.consultaDML("INSERT INTO estados_transicion(estado_inicial, estado_siguiente) VALUES((SELECT id_estado FROM estados_ticket WHERE nombre_estado ='"+ idEstados +"' ), (SELECT id_estado FROM estados_ticket WHERE nombre_estado = '"+ estado +"' ))");
                        }
                    }
                }
                    JOptionPane.showMessageDialog(null, "Se modificó el estado exitosamente");
                    abrirVentana("GestionEstadosTicket.fxml");
                    cerrar(cerrar);            
                    HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se modifico el estado(nombre = "+ estadosTicket.getNombre() +", descripcion = "+ estadosTicket.getDescripcion() +") ", "" + id);
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEstado(Button cerrar, String parametro){
        try {
            conectar.consultaDML("DELETE FROM estados_ticket WHERE nombre_estado = '"+ parametro +"'");
            JOptionPane.showMessageDialog(null, "Se eliminó el estado exitosamente");
            abrirVentana("GestionEstadosTicket.fxml");
            cerrar(cerrar);
            HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se elimino el estado(nombre = "+ parametro +") ", "" + id);
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarEstado(TableView<DatosTableView> tabla, TableColumn<DatosTableView, String> columna1, TableColumn<DatosTableView, Boolean> columna2){
        try {  
            columna2.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            columna2.setCellFactory(CheckBoxTableCell.forTableColumn(columna2));
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            
            tabla.setItems(conectar.obtenerListadoYAsignarCheckbox("select * from estados_ticket;", "nombre_estado" , "", ""));
            tabla.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarTicketsTabla(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
                
            tabla.setItems(conectar.obtenerListado("SELECT t.titulo, e.nombre_estado FROM tickets t INNER JOIN estados_ticket e ON t.estado = e.id_estado  WHERE usuario = 3", "titulo", "nombre_estado", "", ""));
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarTicketsTablaEstados(TableView<DatosTableViewSinCheckbox> tabla, TableColumn<DatosTableViewSinCheckbox, String> columna1, TableColumn<DatosTableViewSinCheckbox, String> columna2){
        try {            
            columna1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato1()));
            columna2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDato2()));
                
            tabla.setItems(conectar.obtenerListado("SELECT t.titulo, e.nombre_prioridad FROM tickets t INNER JOIN prioridades e ON t.prioridad = e.id_prioridad WHERE departamento = (SELECT id_departamento FROM usuarios WHERE id_usuario = "+ id +")", "titulo", "nombre_prioridad", "", ""));
        } catch (SQLException ex) {
            Logger.getLogger(GestionRolesPermisosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void consultarTickets() {
    }
    
}
