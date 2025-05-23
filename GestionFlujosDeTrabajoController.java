/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemadetickets;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hraym
 */
public class GestionFlujosDeTrabajoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public javafx.scene.control.Button nuevoFlujo;
    public javafx.scene.control.Button modificarFlujo;
    public javafx.scene.control.Button eliminarFlujo;
    public javafx.scene.control.Button atras;
    public javafx.scene.control.Button aniadir;
    public javafx.scene.control.Button quitar;
    
    OperacionesVentana operaciones = new OperacionesVentana();
    Administrador admin = new Administrador("", "", "", "", "", "");            
    conection conectado = new conection();
    LocalDate fecha = LocalDate.now();
    int id= conectado.getIdUsuario();

    @FXML
    public ComboBox flujos;
    @FXML
    public TextField flujoSeleccionado;
    @FXML
    public ListView<String> estadosDisponibles;
    @FXML
    private TreeView<String> estados_seleccionados;
    
    @FXML
    private void quitar(){
       TreeItem<String> itemSeleccionado = estados_seleccionados.getSelectionModel().getSelectedItem();
       TreeItem<String> itemAQuitar = itemSeleccionado.getParent();
       itemAQuitar.getChildren().remove(itemSeleccionado);
       estadosDisponibles.getItems().add(itemSeleccionado.getValue());
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
    private void nuevoFlujo() throws Exception{
        operaciones.abrirVentana("CrearNuevoFlujo.fxml");
        operaciones.cerrar(nuevoFlujo);
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
    private void seleccionDeFlujo(){
        try {
            flujoSeleccionado.setText((String) flujos.getValue());
            
            ArrayList<String> estados = new ArrayList<>();
            estados = conectado.consultaListados("SELECT DISTINCT et.nombre_estado FROM flujo_trabajo ft INNER JOIN estados_flujo ef ON ft.id_flujo = ef.id_flujo INNER JOIN estados_ticket et ON ef.estado_actual = et.id_estado WHERE ft.nombre_flujo = '"+ flujoSeleccionado.getText() +"' UNION SELECT DISTINCT et.nombre_estado FROM flujo_trabajo ft INNER JOIN estados_flujo ef ON ft.id_flujo = ef.id_flujo INNER JOIN estados_ticket et ON ef.estado_siguiente = et.id_estado WHERE ft.nombre_flujo = '"+ flujoSeleccionado.getText() +"';", "nombre_estado", "");
            ObservableList<String> listado = FXCollections.observableArrayList(estados);            
            estadosDisponibles.setItems(listado);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFlujosDeTrabajoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void modificarFlujo() throws Exception{
        if(flujos.getValue() == null){
            JOptionPane.showMessageDialog(null, "Seleccione un flujo");
        }else{
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
            System.out.println(flujoSeleccionado.getText() + estadoPrincipal + estadosTransiciones);
            
            if(estadoPrincipal.size()>0){
                conectado.consultaDML("DELETE FROM estados_flujo WHERE id_flujo = (SELECT id_flujo FROM flujo_trabajo WHERE nombre_flujo ='"+ flujoSeleccionado.getText() +"' )");
                admin.modificarFlujosTrabajo(modificarFlujo, flujoSeleccionado.getText(), estadoPrincipal, estadosTransiciones);
            }else{
                JOptionPane.showMessageDialog(null, "Se guardaron los mismos datos");
            }
            
            operaciones.abrirVentana("Admin.fxml");
            operaciones.cerrar(modificarFlujo);
        }
    }
    
    @FXML
    private void eliminarFlujo() throws Exception{
        if(flujos.getValue() == null){
            JOptionPane.showMessageDialog(null, "Seleccione un Flujo");
        }else{
            int dato = conectado.buscar("SELECT * FROM estados_flujo WHERE id_flujo =(SELECT id_flujo FROM flujo_trabajo WHERE nombre_flujo ='"+ flujoSeleccionado.getText() +"' ) LIMIT 1");
            if(dato>0){
                JOptionPane.showMessageDialog(null, "El flujo esta asignado a tickets, modifique antes de eliminar");
            }else{
                conectado.consultaDML("DELETE FROM estados_flujo WHERE id_flujo = (SELECT id_flujo FROM flujo_trabajo WHERE nombre_flujo = '"+ flujoSeleccionado.getText() +"' )");
                conectado.consultaDML("DELETE FROM flujo_trabajo WHERE nombre_flujo ='"+ flujoSeleccionado.getText() +"' ");
                JOptionPane.showMessageDialog(null, "Se eliminó el flujo exitosamente");
                operaciones.abrirVentana("Admin.fxml");
                operaciones.cerrar(eliminarFlujo);
                HistorialConfiguracionesSistema historial = new HistorialConfiguracionesSistema(fecha, "Se elimino el flujo(nombre = "+ flujoSeleccionado.getText() +") ", "" + id);
            }
            
        }
    }
    
    @FXML
    private void atras() throws Exception{
        operaciones.abrirVentana("Admin.fxml");
        operaciones.cerrar(atras);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try {
            TreeItem<String> root = new TreeItem<>("Flujo");
            root.setExpanded(true);
            estados_seleccionados.setRoot(root);
                                    
            ArrayList<String> flujosExistentes = new ArrayList<>();
            flujosExistentes = conectado.consultaListados("SELECT nombre_flujo FROM flujo_trabajo", "nombre_flujo", "");
            ObservableList<String> listadoFlujos = FXCollections.observableArrayList(flujosExistentes);            
            flujos.setItems(listadoFlujos);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFlujosDeTrabajoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
