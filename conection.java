/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class conection {
    
    Connection connect = null;
    ConfiguracionDelSistemaController configuracion = new ConfiguracionDelSistemaController();
    private String idioma;
    private String nombreEmpresa;
    private String zonaHoraria;
    private int tiempoVencimientoTicketsInactivos;
    private String nivelesPrioridad;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();

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
        conectar();
        
        try {
            ps = connect.prepareStatement(script);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                setNombreEmpresa(rs.getString("nombreEmpresa"));
                setIdioma(rs.getString("idioma"));
                setZonaHoraria(rs.getString("zonaHoraria"));
                setTiempoVencimientoTicketsInactivos(rs.getInt("tiempoVencimientoTicketsInactivos"));
                setNivelesPrioridad(rs.getString("nivelesPrioridad"));
                JOptionPane.showMessageDialog(null, "EXITO");
            }
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRORES:" + e.toString());
        }finally{
            connect.close();
        }
        
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
                        ps2 = connect.prepareStatement("SELECT nombre_rol FROM roles INNER JOIN usuarios ON roles.id_rol = '"+ rs.getString("id_rol") +"' ");
                        ResultSet rs2 = ps2.executeQuery();
                        if(rs2.next()){
                             JOptionPane.showMessageDialog(null, "BIENVENIDO " + rs2.getString("nombre_rol"));
                             if(rs2.getString("nombre_rol").equals("administrador")){
                                 abrir.abrirVentana("Admin.fxml");
                             }
                             if(rs2.getString("nombre_rol").equals("tecnico")){
                                 abrir.abrirVentana("Tecnico.fxml");
                             }
                             if(rs2.getString("nombre_rol").equals("usuario")){
                                 abrir.abrirVentana("Usuario.fxml");
                             }
                        }
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
}
