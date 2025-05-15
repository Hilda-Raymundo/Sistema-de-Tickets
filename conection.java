/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class conection extends OperacionesVentana{
    
    Connection connect = null;
    ConfiguracionSistema configuracion = new ConfiguracionSistema();
    private String idioma;
    private String nombreEmpresa;
    private String zonaHoraria;
    private int tiempoVencimientoTicketsInactivos;
    private String nivelesPrioridad;
    private String dato;
    
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

    public String getNivelesPrioridad() {
        return nivelesPrioridad;
    }

    public void setNivelesPrioridad(String nivelesPrioridad) {
        this.nivelesPrioridad = nivelesPrioridad;
    }
     
    public void consulta(String script) throws SQLException{
        connect = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        conectar();
        
        try {
            ps = connect.prepareStatement(script);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                setNombreEmpresa(rs.getString("nombre_empresa"));
                setTiempoVencimientoTicketsInactivos(rs.getInt("tiempo_vencimiento_tickets_inactivos"));
                
                ps2 = connect.prepareStatement("SELECT nombre_idioma FROM idiomas INNER JOIN configuracion_sistema ON idiomas.id_idioma = '"+ rs.getString("idioma") +"' ");
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                   setIdioma(rs2.getString("nombre_idioma"));
                }
                
                ps3 = connect.prepareStatement("SELECT nombre_zona_horaria FROM zonas_horarias INNER JOIN configuracion_sistema ON zonas_horarias.id_zona_horaria = '"+ rs.getString("zona_horaria") +"' ");
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()){
                   setZonaHoraria(rs3.getString("nombre_zona_horaria"));
                }
                                
                ps4 = connect.prepareStatement("SELECT nombre_prioridad FROM prioridades INNER JOIN prioridades_configuracion_sistema ON prioridades.id_prioridad = prioridades_configuracion_sistema.id_prioridad");
                ResultSet rs4 = ps4.executeQuery();
                ArrayList<String> prioridades = new ArrayList<>();
                while(rs4.next()){
                   prioridades.add(rs4.getString("nombre_prioridad"));
                }
            }else{
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }finally{
            connect.close();
        }
    }
    
    public ArrayList consultaListados(String sql, String parametroEspecifico) throws SQLException{
        ArrayList<String> listado = new ArrayList<>();
        connect = null;
        PreparedStatement ps = null;
        conectar();
        
        try {
            ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listado.add(rs.getString(parametroEspecifico));
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }finally{
            connect.close();
        }
        return listado;
    }
    
    public Connection conectar() {
        // Datos de conexión a Neon PostgreSQL
        String url = "jdbc:postgresql://ep-summer-bar-a4h60bj0-pooler.us-east-1.aws.neon.tech/sistema_tickets?sslmode=require";
        String user = "sistema_tickets_owner";
        String pass = "npg_sjqrm9zFRP0f";

        try {
            connect = DriverManager.getConnection(url, user, pass);
//            JOptionPane.showMessageDialog(null, "conexion exitosa! ");
            // Si la conexión es exitosa
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRO:" + e.toString());
        }
        return connect;
    }
    
    public void loguearse(String usuario, String contrasenia) throws SQLException{
        connect = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        conectar();
        
        try {
            ps = connect.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
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
                        setIdUsuario(rs.getInt("id_usuario"));
                    }else{
                        System.out.println("contrasenia incorrecta");
                    }
                }                
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }finally{
            connect.close();
        }
    }

    public ObservableList<DatosTableView> obtenerListado(String sql, String parametroEspecifico) throws SQLException{
        ObservableList<DatosTableView> listado = FXCollections.observableArrayList();
        connect = null;
        PreparedStatement ps=null;
        conectar();
            try {
                ps = connect.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   String dato = rs.getString(parametroEspecifico);
                   listado.add(new DatosTableView(dato));
                }
            } catch (SQLException ex) {
                Logger.getLogger(conection.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                conectar().close();
            }
            
        return listado;
    }
    
}
