/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.*;
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
     
    public void consulta(String script){
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
        }
        
    }
    
    public Connection conectar() {
        // Datos de conexión a Neon PostgreSQL
        String url = "jdbc:postgresql://ep-summer-bar-a4h60bj0-pooler.us-east-1.aws.neon.tech/sistema_tickets?sslmode=require";
        String user = "sistema_tickets_owner";
        String pass = "npg_sjqrm9zFRP0f";

        try {
            connect = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "conexion exitosa! ");
            // Si la conexión es exitosa
        } catch (Exception e) {
            // Si hay un error en la conexión
            JOptionPane.showMessageDialog(null, "ERRO:" + e.toString());
        }
        
        return connect;
    }

    
}
