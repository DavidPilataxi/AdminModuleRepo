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

public class ProveedorDAO {
        public List<ProveedorVista> obtenerTodosLosProveedores() {
        List<ProveedorVista> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProveedorVista Proveedor = new ProveedorVista(
                    rs.getInt("id_proveedor"),
                    rs.getString("ruc"),
                    rs.getString("nombre")
                );
                lista.add(Proveedor);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la vista: " + e.getMessage());
        }

        System.out.println("Total proveedores obtenidos: " + lista.size());
    return lista;
    }
}
