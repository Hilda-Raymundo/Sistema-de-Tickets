/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.*;
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
    
    OperacionesVentana operaciones = new OperacionesVentana();
    
    private String sql;
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button guardar;
    public TextField nombreEmpresa;
    public ComboBox<String> idiomas;
    public ComboBox<String> zonaHoraria;
    public Spinner<Integer> diasVencimiento;
    public ImageView logo; 
    ConfiguracionSistema parametro = new ConfiguracionSistema();
    @FXML
    public TableView<DatosTableView> tablaPrioridades;
    @FXML
    public TableColumn<DatosTableView, String> nombrePrioridad;
    @FXML
    public TableColumn<DatosTableView, Boolean> estadoPrioridad;
    
    @FXML
    public void cargarLogo() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivo = fileChooser.showOpenDialog(null);

            if (archivo != null) {

                long pesoImagen = archivo.length();
                if(pesoImagen<= 2097152){
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
            }else{
            JOptionPane.showMessageDialog(null, "La imagen pesa más de 2MB");
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
    public void guardar() throws IOException, SQLException{
        parametro.setNombreEmpresa(nombreEmpresa.getText());
        parametro.setIdioma(idiomas.getValue());
        parametro.setZonaHoraria(zonaHoraria.getValue());
        parametro.setTiempoVencimientoTicketsInactivos(diasVencimiento.getValue());
        
        ObservableList<DatosTableView> prioridades = tablaPrioridades.getItems();
        ArrayList<String> prioridadesSeleccionadas = new ArrayList<>();

        for (DatosTableView prioridad : prioridades) {
            if (prioridad.getCheckbox()) {
                prioridadesSeleccionadas.add(prioridad.getNombre());
            }
        }
        
        parametro.setNivelesPrioridad(prioridadesSeleccionadas);
        parametro.guardarConfiguracion();                    
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
        ArrayList<String> imagen = new ArrayList<>();
        SpinnerValueFactory<Integer> listaDiasVencimiento = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365);
        listaDiasVencimiento.setValue(1);
        diasVencimiento.setValueFactory(listaDiasVencimiento);
        
        conection conectado = new conection();
        sql = "SELECT * FROM configuracion_sistema";
        
        try{
            conectado.consulta(sql);
            nombreEmpresa.setText(conectado.getNombreEmpresa());
            imagen = conectado.consultaListados("SELECT * FROM configuracion_sistema", "logo_empresa");
            Image image = new Image("file:/C:/Users/hraym/OneDrive/Documentos/NetBeansProjects/SistemaDeTickets/src/sistemadetickets/imagenes/"+imagen.get(0));
            logo.setImage(image);
            parametro.setLogo(imagen.get(0));
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
            
            estadoPrioridad.setCellValueFactory(cellData -> cellData.getValue().checkboxProperty());
            estadoPrioridad.setCellFactory(CheckBoxTableCell.forTableColumn(estadoPrioridad));
            nombrePrioridad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            
            tablaPrioridades.setItems(conectado.obtenerListado("SELECT u.nombre_prioridad, (SELECT id_prioridad FROM prioridades_configuracion_sistema t WHERE t.id_prioridad = u.id_prioridad) FROM prioridades u;", "nombre_prioridad" , "id_prioridad"));
            tablaPrioridades.setEditable(true);
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erroresss: " + e.toString());
        }
        
        
    }
    
    
    
    
}
