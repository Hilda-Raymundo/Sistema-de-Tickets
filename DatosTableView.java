/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hraym
 */
public class DatosTableView {
    
    public final SimpleStringProperty dato1;
    public final SimpleBooleanProperty checkbox;

    public DatosTableView(String dato1) {
        this.dato1 = new SimpleStringProperty(dato1);
        this.checkbox = new SimpleBooleanProperty();
    }
    
    public String getDato1(){
        return dato1.get();
    }
    
    public boolean seleccionCheckbox(){
        return checkbox.get();
    }
    
    public SimpleStringProperty dato1() {
        return dato1;
    }
    
    public SimpleBooleanProperty checkbox(){
        return checkbox;
    }
    
    public void setCheckbox(boolean checkbox){
        this.checkbox.set(checkbox);
        
    }
    
}
