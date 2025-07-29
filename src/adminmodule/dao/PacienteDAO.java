
package adminmodule.dao;

import adminmodule.modelo.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements UserDAO<Paciente> {
    @Override
    public boolean existe(String cedula) {
        String sql = "SELECT cedula FROM paciente WHERE cedula = ?";
        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Paciente obtenerPorCedula(String cedula) {
        String sql = "SELECT * FROM paciente WHERE cedula = ?";
        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                        rs.getString("cedula"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("sexo"),
                        rs.getString("correo"),
                        rs.getString("contrasena_paciente"),
                        rs.getString("rol"),
                        rs.getString("alergias"),
                        rs.getString("oxigenacion"),
                        rs.getString("id_antecedentes")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean guardar(Paciente paciente) {
        String sql = "INSERT INTO paciente (cedula, nombres, apellidos, fecha_nacimiento, " +
                    "sexo, correo, contrasena_paciente, alergias, oxigenacion, id_antecedetes, rol) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getCedula());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getApellidos());
            ps.setDate(4, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getCorreo());
            ps.setString(7, paciente.getContrasena());
            ps.setString(8, paciente.getAlergias());
            ps.setString(9, paciente.getOxigenacion());
            ps.setString(10, paciente.getIdAntecedentes());
            ps.setString(11, paciente.getRol());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Paciente paciente) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE paciente SET nombres = ?, apellidos = ?, fecha_nacimiento = ?, " +
             "sexo = ?, correo = ?, alergias = ?, oxigenacion = ?, id_antecedetes = ?");
        if (paciente.getContrasena() != null && !paciente.getContrasena().isEmpty()) {
            sqlBuilder.append(", contrasena_paciente = ?");
            }
            sqlBuilder.append(" WHERE cedula = ?");
            String sql = sqlBuilder.toString();

        try (Connection conn = ConexionSQL.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                int i = 1;
                ps.setString(i++, paciente.getNombres());
                ps.setString(i++, paciente.getApellidos());
                ps.setDate(i++, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
                ps.setString(i++, paciente.getSexo());
                ps.setString(i++, paciente.getCorreo());
                ps.setString(i++, paciente.getAlergias());
                ps.setString(i++, paciente.getOxigenacion());
                ps.setString(i++, paciente.getIdAntecedentes());
                if (paciente.getContrasena() != null && !paciente.getContrasena().isEmpty()) {
                    ps.setString(i++, paciente.getContrasena());
                }
                ps.setString(i++, paciente.getCedula());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM paciente WHERE cedula = ?";
        try (Connection conn = ConexionSQL.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } 

    public List<Paciente> obtenerTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        
        try (Connection conn = ConexionSQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pacientes.add(new Paciente(
                    rs.getString("cedula"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getString("correo"),
                    rs.getString("contrasena_paciente"),
                    rs.getString("rol"),
                    rs.getString("alergias"),
                    rs.getString("oxigenacion"),
                    rs.getString("id_antecedetes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
    
    
}