/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class conection extends OperacionesVentana{
    
    private String idioma;
    private String nombreEmpresa;
    private String zonaHoraria;
    private int tiempoVencimientoTicketsInactivos;
    private static int idUsuario;

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }
        
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public int getTiempoVencimientoTicketsInactivos() {
        return tiempoVencimientoTicketsInactivos;
    }

    public void setTiempoVencimientoTicketsInactivos(int tiempoVencimientoTicketsInactivos) {
        this.tiempoVencimientoTicketsInactivos = tiempoVencimientoTicketsInactivos;
    }
     
    public void consulta(String sql) throws SQLException{
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                setNombreEmpresa(rs.getString("nombre_empresa"));
                setTiempoVencimientoTicketsInactivos(rs.getInt("tiempo_vencimiento_tickets_inactivos"));
                
                try (PreparedStatement ps2 = conn.prepareStatement("SELECT nombre_idioma FROM idiomas INNER JOIN configuracion_sistema ON idiomas.id_idioma = '"+ rs.getString("idioma") +"' ");
                    ResultSet rs2 = ps2.executeQuery()) {
                    if(rs2.next()){
                        setIdioma(rs2.getString("nombre_idioma"));
                     }
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }
                
                try (PreparedStatement ps3 = conn.prepareStatement("SELECT nombre_zona_horaria FROM zonas_horarias INNER JOIN configuracion_sistema ON zonas_horarias.id_zona_horaria = '"+ rs.getString("zona_horaria") +"' ");
                    ResultSet rs3 = ps3.executeQuery()) {
                    if(rs3.next()){
                        setZonaHoraria(rs3.getString("nombre_zona_horaria"));
                     }
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }
                                                               
            }else{
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.getMessage());
        }
    }
    
    public ArrayList consultaListados(String sql, String parametroEspecifico, String parametro2) throws SQLException{
        ArrayList<String> listado = new ArrayList<>();
            try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                listado.add(rs.getString(parametroEspecifico));
                if(!parametro2.equals("")){
                    String[] parametros = parametro2.split(",");
                    for(int i=0; i<parametros.length; i++) {
                        listado.add(rs.getString(parametros[i]));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }
        return listado;
    }
    
    public ArrayList obtenerEstados(String sql, String parametroEspecifico){
        ArrayList<String> listado = new ArrayList<>();
            try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                listado.add(rs.getString(parametroEspecifico));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }
        return listado;
    }
    
    public Connection conectar() {
        String url = "jdbc:postgresql://ep-summer-bar-a4h60bj0-pooler.us-east-1.aws.neon.tech/sistema_tickets?sslmode=require";
        String user = "sistema_tickets_owner";
        String pass = "npg_sjqrm9zFRP0f";

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO:" + e.getMessage());
            return null;
        }
    }
    
    public void loguearse(String usuario, String contrasenia, Button boton) throws SQLException{
        boolean usuarioEncontrado = false;
        
        if(!usuario.equals("") && !contrasenia.equals("")){
            try (Connection conn = conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios");
                ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    if(usuario.equals(rs.getString("nombre_usuario"))){
                        if(contrasenia.equals(rs.getString("contrasenia"))){
                            if(rs.getString("id_usuario").equals("1")){
                                abrirVentana("Admin.fxml");
                            }
                            if(rs.getString("id_usuario").equals("2")){
                                abrirVentana("Tecnico.fxml");
                            }
                            if(rs.getString("id_usuario").equals("3")){
                                abrirVentana("Usuario.fxml");
                            }
                            usuarioEncontrado = true;
                            cerrar(boton);
                            LocalDate fecha = LocalDate.now();
                            HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "El usuario " + rs.getString("nombre_usuario") + " ingreso al sistema " + rs.getString("id_usuario"), rs.getString("id_usuario"));
                            ConfiguracionDelSistemaController configuracion = new ConfiguracionDelSistemaController();
                            setIdUsuario(rs.getInt("id_usuario"));
                        }else{
                            usuarioEncontrado = true;
                            JOptionPane.showMessageDialog(null,"contrasenia incorrecta");
                        }
                    }       
                }  
                
                if(usuarioEncontrado == false){
                    JOptionPane.showMessageDialog(null, "No se encontró al usuario");
                }
            } catch (Exception e) {
                // Si hay un error en la conexión
                JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }
        
        
    }

    public ObservableList<DatosTableView> obtenerListadoYAsignarCheckbox(String sql, String parametro1, String parametro2, String condicion) throws SQLException{
        ObservableList<DatosTableView> listado = FXCollections.observableArrayList();
        listado.clear();

        boolean check = false;
        
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){      
                String nombre = rs.getString(parametro1);
                if(parametro2.equals("")){
                    listado.add(new DatosTableView(false, nombre));
                }else{
                    int id = rs.getInt(parametro2);
                        if(id > 0){
                            check = true;
                            if(!condicion.equals("")){
                                if(id>1){
                                    check = false;
                                }
                            }
                        }else{
                            check = false;
                        }
                        listado.add(new DatosTableView(check, nombre));
                }
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.getMessage());
        }
        return listado;
    }  
    
    public ObservableList<DatosTableViewSinCheckbox> obtenerListado(String sql, String parametro1, String parametro2, String parametro3, String parametro4) throws SQLException{
        ObservableList<DatosTableViewSinCheckbox> listado = FXCollections.observableArrayList();
        listado.clear();
        
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){      
                if(parametro3.equals("")){
                    listado.add(new DatosTableViewSinCheckbox(rs.getString(parametro1), rs.getString(parametro2), ""));
                }else{
                    ArrayList<String> lista = consultaListados("select nombre_usuario from usuarios where id_rol = (select id_rol from roles where nombre_rol = 'tecnico' and id_departamento = (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ rs.getString(parametro1) +"'));", parametro4, "");
                    String dato = lista.toString();
                    listado.add(new DatosTableViewSinCheckbox(rs.getString(parametro1), rs.getString(parametro2), dato));
                }
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.getMessage());
        }
        return listado;
    }  
    
    public void consultaDML(String consulta) throws SQLException{
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(consulta)){
            ps.executeUpdate();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES al ejecutar la consulta:" + e.getMessage());
        }
    }
    
    public void consultaDDL(String consulta) throws SQLException{
    
        try (Connection conn = conectar();
            Statement ps = conn.createStatement()) {
            
            ps.execute(consulta);
            
        } catch (SQLException e) {
             e.printStackTrace();
            System.err.println("Error de consulta: " + e.getMessage());
        }

        
    }
    
    public int buscar(String consulta)throws SQLException{
        int datoEncontrado = 0;
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(consulta)){
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                datoEncontrado++;
            }
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.getMessage());
        }
        return datoEncontrado;
    }
    
    public int buscarId(String consulta)throws SQLException{
        int datoEncontrado = 0;
        try (Connection conn = conectar();
            PreparedStatement ps = conn.prepareStatement(consulta)){
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                datoEncontrado = rs.getInt("id_departamento");
            }
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.getMessage());
        }
        return datoEncontrado;
    }
}
