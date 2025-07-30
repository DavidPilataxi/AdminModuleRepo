/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariomodule.InventarioDAL;

/**
 *
 * @author Jorge
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoVistaDAO {
        public List<MedicamentoVista> obtenerTodosLosConductores() {
        List<MedicamentoVista> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MedicamentoVista Medicamento = new MedicamentoVista(
                    rs.getInt("id_medicamento"),
                    rs.getString("CUM"),
                    rs.getString("proveedor"),
                    rs.getString("nombre_generico"),
                    rs.getString("nombre_comercial"),
                    rs.getInt("cantidad"),
                    rs.getString("forma_farmaceutica"),
                    rs.getString("concentracion"),
                    rs.getString("presentacion")
                );
                lista.add(Medicamento);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la vista: " + e.getMessage());
        }

        return lista;
    }
}
