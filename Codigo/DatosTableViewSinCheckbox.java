/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hraym
 */
public class DatosTableViewSinCheckbox {
    
    private final StringProperty dato1;
    private final StringProperty dato2;
    private final StringProperty dato3;

    public DatosTableViewSinCheckbox(String dato1, String dato2, String dato3) {
        this.dato1 = new SimpleStringProperty(dato1);
        this.dato2 = new SimpleStringProperty(dato2);
        this.dato3 = new SimpleStringProperty(dato3);
    }    

    public String getDato1() {
        return dato1.get();
    }

    public String getDato2() {
        return dato2.get();
    }
       
    public String getDato3() {
        return dato3.get();
    }
}
