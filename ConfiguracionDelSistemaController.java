/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
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
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    private String sql;
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button guardar;
    public TextField nombreEmpresa;
    public ComboBox<String> idiomas;
    public ComboBox<String> zonaHoraria;
    public Spinner<Integer> diasVencimiento;
    private int indice = 0;
    public ImageView logo; 
    ConfiguracionSistema parametro = new ConfiguracionSistema();
    @FXML
    public TableView<DatosTableView> tablaPrioridades;
    @FXML
    public TableColumn<DatosTableView, String> nombrePrioridad;
    @FXML
    public TableColumn<DatosTableView, Boolean> estadoPrioridad;
    @FXML
    public TableView<DatosTableView> tablaPrioridadesPrevias;
    @FXML
    public TableColumn<DatosTableView, String> nombrePrioridadPrevia;
    
    @FXML
    public void cargarLogo() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivo = fileChooser.showOpenDialog(null);

        if (archivo != null) {
            BufferedImage bi = ImageIO.read(archivo);
            WritableImage img = SwingFXUtils.toFXImage(bi, null);
            logo.setImage(img);

            File carpetaDestino = new File("C:\\Users\\hraym\\OneDrive\\Documentos\\NetBeansProjects\\SistemaDeTickets\\src\\sistemadetickets\\imagenes");
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
            }

            File destino = new File(carpetaDestino, archivo.getName());

            try {
                Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                parametro.setLogo(archivo.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void cancelar() throws IOException{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar la configuración?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            parametro.cancelarConfiguracion();
            operaciones.abrirVentana("Admin.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    public void guardar() throws IOException{
        parametro.setNombreEmpresa(nombreEmpresa.getText());
        parametro.setIdioma(idiomas.getValue());
        parametro.setZonaHoraria(zonaHoraria.getValue());
        parametro.setTiempoVencimientoTicketsInactivos(diasVencimiento.getValue());
        
    }
   
    @FXML
    public void comboBoxIdiomas() throws IOException{        
    }
    
    @FXML
    public void comboBoxZonaHoraria() throws IOException{
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> listaDiasVencimiento = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365);
        listaDiasVencimiento.setValue(1);
        diasVencimiento.setValueFactory(listaDiasVencimiento);
        
        conection conectado = new conection();
        sql = "SELECT * FROM configuracion_sistema";
        
        try{
            conectado.consulta(sql);
            nombreEmpresa.setText(conectado.getNombreEmpresa());
            idiomas.setValue(conectado.getIdioma());
            zonaHoraria.setValue(conectado.getZonaHoraria());
            diasVencimiento.getValueFactory().setValue(conectado.getTiempoVencimientoTicketsInactivos());
            ArrayList<String> listado = new ArrayList<>();
            listado = conectado.consultaListados("select nombre_idioma from idiomas order by nombre_idioma", "nombre_idioma");
            for(int i = 0; i<listado.size(); i++){
                idiomas.getItems().add(listado.get(i));
            }
            ArrayList<String> listado2 = new ArrayList<>();
            listado2 = conectado.consultaListados("select nombre_zona_horaria from zonas_horarias order by nombre_zona_horaria", "nombre_zona_horaria");
            for(int i = 0; i<listado2.size(); i++){
                zonaHoraria.getItems().add(listado2.get(i));
            }
            nombrePrioridad.setCellValueFactory(cellData->cellData.getValue().dato1());
            estadoPrioridad.setCellValueFactory(cellData->cellData.getValue().checkbox());
            estadoPrioridad.setCellFactory(CheckBoxTableCell.forTableColumn(estadoPrioridad));  
            estadoPrioridad.setEditable(true);
            tablaPrioridades.setEditable(true);
            tablaPrioridades.setItems(conectado.obtenerListado("SELECT nombre_prioridad FROM prioridades", "nombre_prioridad"));
            nombrePrioridadPrevia.setCellValueFactory(cellData->cellData.getValue().dato1());
            tablaPrioridadesPrevias.setItems(conectado.obtenerListado("select nombre_prioridad from prioridades inner join prioridades_configuracion_sistema on prioridades.id_prioridad = prioridades_configuracion_sistema.id_prioridad inner join configuracion_sistema on prioridades_configuracion_sistema.id_configuracion_sistema = configuracion_sistema.id_configuracion_sistema and configuracion_sistema.seleccionada = 'si'", "nombre_prioridad"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erroresss: " + e.toString());
        }
        
        
    }
    
    
    
    
}
