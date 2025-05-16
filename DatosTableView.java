/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.*;

/**
 *
 * @author hraym
 */
public class DatosTableView {
    
    private final BooleanProperty checkbox;
    private final StringProperty nombre;

    public DatosTableView(Boolean checkbox, String nombre) {
        this.checkbox = new SimpleBooleanProperty(checkbox);
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public boolean getCheckbox(){
        return checkbox.get();
    }
    
    public void setCheckbox(boolean checkbox){
        this.checkbox.set(checkbox);
    }
    
    public BooleanProperty checkboxProperty(){
        return checkbox;
    }
    
    public String getNombre(){
        return nombre.get();
    }

}
