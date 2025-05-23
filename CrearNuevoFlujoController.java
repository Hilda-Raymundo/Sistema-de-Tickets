/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class CrearNuevoFlujoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button cancelar;
    public javafx.scene.control.Button crearFlujoTrabajo;
    public javafx.scene.control.Button aniadir;
    public javafx.scene.control.Button desasignar;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    Administrador admin = new Administrador("", "", "", "", "", "");
    @FXML
    public ListView<String> estadosDisponibles;
    @FXML
    private TreeView<String> estados_seleccionados;
    @FXML
    private TextField nombreFlujo;
        
    @FXML
    private void cancelar() throws Exception{
        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar?", "Cancelar",JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.YES_OPTION){
            operaciones.abrirVentana("GestionFlujosDeTrabajo.fxml");
            operaciones.cerrar(cancelar);
        }
    }
    
    @FXML
    private void crearFlujoTrabajo() throws Exception{
        List<String> datos = guardar(estados_seleccionados.getRoot());
        ArrayList<String> estadoPrincipal = new ArrayList<>();
        ArrayList<String> estadosTransiciones = new ArrayList<>();
        
        for(String estado_principal : datos){
            if(estado_principal.startsWith("00")){
                estadosTransiciones.add(estado_principal.replace("0", ""));
            }else if(estado_principal.startsWith("0")){
                estadoPrincipal.add(estado_principal.replace("0", ""));
            }
        }
        admin.crearFlujosTrabajo(crearFlujoTrabajo, nombreFlujo.getText(), estadoPrincipal, estadosTransiciones);
    }
    
    @FXML
    private void asignar(){
        String estadoSeleccionado = estadosDisponibles.getSelectionModel().getSelectedItem();
        estadosDisponibles.getItems().remove(estadoSeleccionado);
        TreeItem<String> seleccionado = estados_seleccionados.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            TreeItem<String> nuevoNodo = new TreeItem<>(estadoSeleccionado);
            seleccionado.getChildren().add(nuevoNodo);
            seleccionado.setExpanded(true);
        } else {
            TreeItem<String> raiz = estados_seleccionados.getRoot();
            TreeItem<String> nuevoNodo = new TreeItem<>(estadoSeleccionado);
            raiz.getChildren().add(nuevoNodo);
            raiz.setExpanded(true);
        }
    }
    
    @FXML
    private List<String> guardar(TreeItem<String> nodoRaiz){
        List<String> lista = new ArrayList<>();
        recorrerNodos(nodoRaiz,"", lista);
        return lista;
    }
    
    private void recorrerNodos(TreeItem<String> nodo, String prefijo, List<String> salida) {
        int contador = 0;
        if (nodo != null) {
            salida.add(prefijo + nodo.getValue());
            for (TreeItem<String> hijo : nodo.getChildren()) {
                recorrerNodos(hijo, prefijo + "" + contador , salida);
            }
        }
    }
    
    @FXML
    private void quitar(){
       TreeItem<String> itemSeleccionado = estados_seleccionados.getSelectionModel().getSelectedItem();
       TreeItem<String> itemAQuitar = itemSeleccionado.getParent();
       itemAQuitar.getChildren().remove(itemSeleccionado);
       estadosDisponibles.getItems().add(itemSeleccionado.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TreeItem<String> root = new TreeItem<>("Flujo");
            root.setExpanded(true);
            estados_seleccionados.setRoot(root);
            conection conectado = new conection();
            ArrayList<String> estados = new ArrayList<>();
            estados = conectado.consultaListados("SELECT nombre_estado FROM estados_ticket", "nombre_estado", "");
            ObservableList<String> listado = FXCollections.observableArrayList(estados);            
            estadosDisponibles.setItems(listado);
        } catch (SQLException ex) {
            Logger.getLogger(CrearNuevoFlujoController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
    
}
