/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariomodule.InventarioBLL;

import inventariomodule.InventarioDAL.ProveedorVista;
import inventariomodule.InventarioDAL.ProveedorDAO;
import java.util.List;

/**
 *
 * @author Johann
 */
public class ProveedorVistaService {
    private ProveedorDAO dao;
    
    public ProveedorVistaService(){
        dao = new ProveedorDAO();
    }
    
    public List<ProveedorVista> obtenerTodosLosProveedores(){
        return dao.obtenerTodosLosProveedores();
    }
}
