/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import java.beans.Statement;
import java.sql.*;

/**
 *
 * @author hraym
 */
public class conection {
 
    public static boolean probarConexion() {
        // Datos de conexión a Neon PostgreSQL
        String url = "jdbc:postgresql://ep-summer-bar-a4h60bj0-pooler.us-east-1.aws.neon.tech/sistema_tickets?sslmode=require";
        String user = "sistema_tickets_owner";
        String pass = "npg_sjqrm9zFRP0f";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            // Si la conexión es exitosa
            return conn != null;
        } catch (SQLException e) {
            // Si hay un error en la conexión
            e.printStackTrace();
            return false;
        }
    }
}
