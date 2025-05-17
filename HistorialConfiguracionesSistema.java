/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author hraym
 */
public class HistorialConfiguracionesSistema extends Historial{
    
    public HistorialConfiguracionesSistema(LocalDate fechaModificacion, String descripcion, String usuario) throws SQLException {
        super(fechaModificacion, descripcion, usuario);
        
        try{
            conection conectado = new conection();
            conectado.insertarDatos("INSERT INTO historial(fecha_accion, descripcion, id_usuario) VALUES('"+ fechaModificacion +"', '"+ descripcion +"', '"+ usuario +"')");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ha habido un error en la conexion");
        }
    }
    
    @Override
    public void mostrarHistorial(){
    
    }
    
}
