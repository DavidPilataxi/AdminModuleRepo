/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariomodule.InventarioBLL;

import inventariomodule.InventarioDAL.MedicamentoVista;
import inventariomodule.InventarioDAL.MedicamentoVistaDAO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ConductorVistaService {
    
    private MedicamentoVistaDAO dao;

    public ConductorVistaService() {
        dao = new MedicamentoVistaDAO();
    }

    // Método para obtener todos los terminales desde la vista
    public List<MedicamentoVista> obtenerTodosLosConductores() {
        return dao.obtenerTodosLosConductores();
    }

    // Aquí puedes agregar más lógica de negocio, validaciones, filtros, etc.
}
