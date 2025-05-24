/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import com.sun.jdi.connect.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.mail.Transport;

/**
 *
 * @author hraym
 */
abstract class Persona extends OperacionesVentana{
    
    private static String email = "hraymundod@miumg.edu.gt";
    private static String pass = "brxp mdaf typi bnrd";
    private String destino;
    private String asunto;
    private String contenido;
    private Properties p;
    private Session sesion;
    private MimeMessage mensajeCorreo;
    conection conectar = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectar.getIdUsuario();
    private String nombreCompleto;
    private String correo;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private String estado;
    
    private Boolean datosValidos;
    private Boolean nombre_completo_valido = false;
    private Boolean correo_valido = false;
    private Boolean nombre_usuario_valido = false;
    private Boolean contrasenia_valida = false;
    private Boolean rol_valido= false;

    public Boolean getDatosValidos() {
        if(nombre_completo_valido == true && correo_valido == true && nombre_usuario_valido == true && contrasenia_valida == true && rol_valido == true){
            datosValidos = true;
        }else{
            datosValidos = false;
        }        
        return datosValidos;
    }

    public Persona(String nombreCompleto, String correo, String nombreUsuario, String contrasenia, String rol, String estado) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.estado = estado;
    }   

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if(nombreCompleto.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo NOMBRE");
            nombre_completo_valido = false;
        }else{
            if(nombreCompleto.length()>2 && nombreCompleto.length()<101){
                this.nombreCompleto = nombreCompleto;
                nombre_completo_valido = true;
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad validaen NOMBRE");
                nombre_completo_valido = false;
            }
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo CORREO");
            correo_valido = false;
        }else{
            if(correo.contains("@") && (correo.contains("gmail") || correo.contains("miumg") || correo.contains(".com"))){
                this.correo = correo;
                correo_valido = true;
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un correo valido");
                correo_valido = false;
            }
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        if(nombreUsuario.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo NOMBRE USUARIO");
            nombre_usuario_valido = false;
        }else{
            if(nombreUsuario.length()>4 && nombreUsuario.length()<31){
                this.nombreUsuario = nombreUsuario;
                nombre_usuario_valido = true;
            }else{
                nombre_usuario_valido = false;
                JOptionPane.showMessageDialog(null, "Revise la cantidad de caracteres en NOMBRE USUARIO");
            }
            
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if(contrasenia.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo CONTRASEÑA");
            contrasenia_valida = false;
        }else{
            if(contrasenia.length()>7){
                this.contrasenia = contrasenia;
                contrasenia_valida = true;
            }else{
                JOptionPane.showMessageDialog(null, "Revise la cantidad de caracteres en CONTRASEÑA");
                contrasenia_valida = false;
            }
        }
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if(rol.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo ROL");
            rol_valido = false;
        }else{
            this.rol = rol;
            rol_valido = true;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(estado.equals("")){
            JOptionPane.showMessageDialog(null, "Revise el campo ESTADO");
        }else{
            this.estado = estado;
        }
    }
        
    public void enviarNotificaciones(String correoDestino, String nombre_usuario, String pass_usuario){
        try {
            p = new Properties();
            destino = correoDestino;
            asunto = "CREDENCIALES";
            contenido = "Estas son sus credenciales: nombre de usuario: " + nombre_usuario + ", contraseña: " + pass_usuario;
            
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", email);
            p.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            p.setProperty("mail.smtp.auth", "true");
            
            sesion = Session.getDefaultInstance(p);
            mensajeCorreo = new MimeMessage(sesion);
            mensajeCorreo.setFrom(new InternetAddress(email));
            mensajeCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            mensajeCorreo.setSubject(asunto);
            mensajeCorreo.setText(contenido, "ISO-8859-1", "html");
            
            Transport enviar = (Transport) sesion.getTransport("smtp");
            enviar.connect(email, pass);
            enviar.sendMessage(mensajeCorreo, mensajeCorreo.getRecipients(Message.RecipientType.TO));
            enviar.close();
            JOptionPane.showMessageDialog(null, "Se enviaron las credenciales al correo: " + correoDestino);
        } catch (AddressException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearTickets(Button cerrar, String nombre, String descripcion, String departamento, String solicitud, String prioridad){
        try {
            String sql = "insert into tickets(titulo, descripcion, fecha_creacion, estado, prioridad, usuario, departamento, flujo) VALUES('"+ nombre +"', '"+ descripcion +"', '"+ fecha +"', (SELECT et.id_estado FROM estados_ticket et WHERE et.id_estado = (SELECT ef.estado_actual FROM estados_flujo ef INNER JOIN flujo_trabajo ft ON ef.id_flujo = ft.id_flujo WHERE ft.nombre_flujo = '"+ solicitud +"' ORDER BY ef.id_flujo ASC LIMIT 1)), (SELECT id_prioridad FROM prioridades WHERE nombre_prioridad = '"+ prioridad +"'), "+ id +", (SELECT id_departamento FROM departamentos WHERE nombre_departamento = '"+ departamento +"'), (SELECT id_flujo FROM flujo_trabajo WHERE nombre_flujo ='"+ solicitud +"' ) )";
            conectar.consultaDML(sql);
           
            ArrayList<String> numeroDepa = new ArrayList<>();
            numeroDepa = conectar.consultaListados("SELECT id_departamento FROM departamentos WHERE nombre_departamento= '"+ departamento +"' LIMIT 1", "id_departamento", "");
            String numDepa = "%" + numeroDepa.get(0);
            ArrayList<String> nombreColaAtencion = new ArrayList<>();
            nombreColaAtencion = conectar.consultaListados("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_name LIKE '"+ numDepa +"'", "table_name", "");
            conectar.consultaDML("INSERT INTO " + nombreColaAtencion.get(0) + " (id_ticket, id_estado_ticket) VALUES((SELECT MAX(id_ticket) FROM tickets), (SELECT estado FROM tickets ORDER BY id_ticket DESC LIMIT 1))");
            JOptionPane.showMessageDialog(null, "Se creó el ticket exitosamente");
            cerrar(cerrar);
            abrirVentana("Principal.fxml");
            HistorialTickets historial = new HistorialTickets(fecha, "Se creo el ticket(nombre = "+ nombre +", descripcion = "+ descripcion +") ",""+ id);
        } catch (IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void consultarTickets();
    
    public void agregarNota(){
    
    }
    
}
