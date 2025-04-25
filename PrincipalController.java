/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class PrincipalController implements Initializable {

    public TextField usuario;
    public TextField password;
    //Datos temporales
    private String[] usuarios = new String[5];
    private String[] passwords = new String[5];
    private String[] roles = new String[5];
    private boolean usuarioEncontrado = false;
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public Button loguearse;
    
    public void login() throws IOException{
        for(int i = 0; i<= usuarios.length; i++){
            if(usuario.getText().equals(usuarios[i])){
                if(password.getText().equals(passwords[i])){
                    if(roles[i].equals("admin")){
                        JOptionPane.showMessageDialog(null, "Bienvenido Administrador!");
                        abrir.abrirVentana("Admin.fxml");
                        cerrar.cerrar(loguearse);
                        i = usuarios.length +1;
                        usuarioEncontrado=true;
                        boolean conectado = conection.probarConexion();
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText(conectado ? "¡Conectado a Neon PostgreSQL!" : "Error de conexión.");
                        alert.showAndWait();
                    }else if(roles[i].equals("tecnico")){   
                        JOptionPane.showMessageDialog(null, "Bienvenido Tecnico!");
                        abrir.abrirVentana("Tecnico.fxml");
                        cerrar.cerrar(loguearse);
                        i = usuarios.length +1;
                        usuarioEncontrado=true;
                    }else if(roles[i].equals("usuario")){
                        JOptionPane.showMessageDialog(null, "Bienvenido Usuario!");
                        abrir.abrirVentana("Usuario.fxml");
                        cerrar.cerrar(loguearse);
                        i = usuarios.length +1;
                        usuarioEncontrado=true;
                    }
                }
            }else{
                if(i>=usuarios.length-1){
                    JOptionPane.showMessageDialog(null, "Revise los datos ingresados");
                    i = usuarios.length+1;
                }
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Informacion temporal
        usuarios[0] = "Hilda";
        passwords[0] = "admin" ;
        roles[0] = "admin";
        usuarios[1] = "Aileen";
        passwords[1] = "tecnico";
        roles[1] = "tecnico";
        usuarios[2] = "Raymundo";
        passwords[2] = "usuario";
        roles[2] = "usuario";
    }    
    
}
