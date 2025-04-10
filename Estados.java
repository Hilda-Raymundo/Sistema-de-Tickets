/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemadetickets;

/**
 *
 * @author hraym
 */
public class Estados extends ParametrosSistema{
    
    private String estadoFinal;
    private String[] estadosPermitidosSiguientes;

    public Estados(String identificador, String nombre, String descripcion) {
        super(identificador, nombre, descripcion);
    }
    
}
