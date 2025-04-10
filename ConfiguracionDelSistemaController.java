/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class ConfiguracionDelSistemaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AbrirVentana abrir = new AbrirVentana();
    CerrarVentana cerrar = new CerrarVentana();
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button guardar;
    public TextField nombreEmpresa;
    public ComboBox<String> idiomas;
    public ComboBox<String> zonaHoraria;
    public Spinner<Integer> diasVencimiento;
    public CheckBox nivel1;
    public CheckBox nivel2;
    public CheckBox nivel3;
    public CheckBox nivel4;
    public CheckBox nivel5;
    public String[] listaPrioridades;
    private int indice = 0;
    public ImageView logo;
    ConfiguracionSistema parametro = new ConfiguracionSistema();
    
    @FXML
    public void cargarLogo() throws IOException{
        FileChooser cargar = new FileChooser();
        FileChooser.ExtensionFilter filtroJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter filtroPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        cargar.getExtensionFilters().addAll(filtroJPG, filtroPNG);
        File file = cargar.showOpenDialog(null);
        BufferedImage bi = ImageIO.read(file);
        WritableImage img = SwingFXUtils.toFXImage(bi, null);
        logo.setImage(img);
    }
    
    @FXML
    public void cancelar() throws IOException{
        parametro.cancelarConfiguracion();
        abrir.abrirVentana("Admin.fxml");
        cerrar.cerrar(cancelar);
    }
    
    @FXML
    public void guardar() throws IOException{
        if(nombreEmpresa.getText().equals("") || diasVencimiento.getValue().equals("") || idiomas.getSelectionModel().getSelectedItem().toString().equals("") || zonaHoraria.getSelectionModel().getSelectedItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacíos");
        }else{
            parametro.setNombreEmpresa(nombreEmpresa.getText());
            parametro.setTiempoVencimientoTicketsInactivos(diasVencimiento.getValue());
            parametro.setIdioma(idiomas.getSelectionModel().getSelectedItem().toString());
            parametro.setZonaHoraria(zonaHoraria.getSelectionModel().getSelectedItem().toString());
            
            if(nivel1.isSelected()){
            parametro.setNivelesPrioridad(nivel1.getText(), indice);
            indice++;
            } 
            if(nivel2.isSelected()){
                parametro.setNivelesPrioridad(nivel2.getText(), indice);
                indice++;
            }
            if(nivel3.isSelected()){
                parametro.setNivelesPrioridad(nivel3.getText(), indice);
                indice++;
            } 
            if(nivel4.isSelected()){
                parametro.setNivelesPrioridad(nivel4.getText(), indice);
                indice++;
            } 
            if(nivel5.isSelected()){
                parametro.setNivelesPrioridad(nivel5.getText(), indice);
                indice++;
            }
            
            if(indice>2){
                parametro.guardarConfiguracion();
                abrir.abrirVentana("Admin.fxml");
                cerrar.cerrar(guardar);
            }else{
                JOptionPane.showMessageDialog(null, "Se requieren al menos 3 niveles de prioridad");
                indice = 0;
            }
        }        
    }
    
    @FXML
    public void comboBoxIdiomas() throws IOException{
        idiomas.getItems().add("Inglés");
        idiomas.getItems().add("Español");
    }
    
    @FXML
    public void comboBoxZonaHoraria() throws IOException{
        zonaHoraria.getItems().add("GMT-6");
        zonaHoraria.getItems().add("UTC-05");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> listaDiasVencimiento = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365);
        listaDiasVencimiento.setValue(1);
        diasVencimiento.setValueFactory(listaDiasVencimiento);
    }
    
}
