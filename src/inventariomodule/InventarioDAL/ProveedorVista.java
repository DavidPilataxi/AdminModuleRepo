/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariomodule.InventarioDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Johann
 */

public class ProveedorVista {
    private int idProveedor;
    private String nombre;
    private String ruc;

    // Constructor
    public ProveedorVista(int idProveedor, String nombre, String ruc) {
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        this.nombre = nombre;
        
    }

    // Getters y setters
    public int getIdProveedor() {
        return idProveedor;
    }

//    public void setIdProveedor(int idProveedor) {
//        this.idProveedor = idProveedor;
//    }

    public String getNombre() {
        return nombre;
    }

//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }

    public String getRuc() {
        return ruc;
    }

//    public void setRuc(String ruc) {
//        this.ruc = ruc;
//    }
}