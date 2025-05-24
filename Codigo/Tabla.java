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
public class Tabla {
    
    private final StringProperty dato1;
    private final StringProperty dato2;
    private final StringProperty dato3;
    private final StringProperty dato4;
    private final StringProperty dato5;
    private final StringProperty dato6;

    public Tabla(String dato1, String dato2, String dato3, String dato4, String dato5, String dato6) {
        this.dato1 = new SimpleStringProperty(dato1);
        this.dato2 = new SimpleStringProperty(dato2);
        this.dato3 = new SimpleStringProperty(dato3);
        this.dato4 = new SimpleStringProperty(dato4);
        this.dato5 = new SimpleStringProperty(dato5);
        this.dato6 = new SimpleStringProperty(dato6);
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
    
    public String getDato4() {
        return dato4.get();
    }

    public String getDato5() {
        return dato5.get();
    }
    
    public String getDato6() {
        return dato6.get();
    }
    
}
