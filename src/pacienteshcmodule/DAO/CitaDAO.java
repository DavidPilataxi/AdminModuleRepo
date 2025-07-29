package pacienteshcmodule.DAO;

import pacienteshcmodule.DAO.ConexionSQLServer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    public static Map<String, String> obtenerPacienteConCitaMasProxima(String cedulaDoctor) {
        Map<String, String> datosPaciente = new HashMap<>();
        String sql = "SELECT TOP 1 p.cedula, p.nombres, p.apellidos,p.estado_civil,p.telefono, p.fecha_nacimiento, p.sexo, p.edad ,p.correo, p.alergias, c.fecha, c.hora " +
                     "FROM Cita c " +
                     "INNER JOIN Paciente p ON c.id_paciente = p.cedula " +
                     "WHERE c.id_doctor = ? " + // cedula del doctor que inició sesión
                     "AND (c.fecha > CAST(GETDATE() AS DATE) OR (c.fecha = CAST(GETDATE() AS DATE) AND c.hora >= CAST(GETDATE() AS TIME))) " +
                     "ORDER BY c.fecha ASC, c.hora ASC"; 
        
        try (Connection conn = ConexionSQLServer.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cedulaDoctor);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaNacimiento = "";
                if (rs.getDate("fecha_nacimiento") != null) {
                    fechaNacimiento = sdf.format(rs.getDate("fecha_nacimiento"));
                }
                
                datosPaciente.put("cedula", rs.getString("cedula"));
                datosPaciente.put("nombres", rs.getString("nombres"));
                datosPaciente.put("apellidos", rs.getString("apellidos"));
                datosPaciente.put("fecha_nacimiento", fechaNacimiento);
                datosPaciente.put("sexo", rs.getString("sexo"));
                datosPaciente.put("correo", rs.getString("correo"));
                datosPaciente.put("alergias", rs.getString("alergias"));
                datosPaciente.put("edad", rs.getString("edad"));
                datosPaciente.put("telefono", rs.getString("telefono"));
                datosPaciente.put("estado_civil", rs.getString("estado_civil"));
               
                if (rs.getDate("fecha") != null) {
                    datosPaciente.put("fecha_cita", sdf.format(rs.getDate("fecha")));
                }
                if (rs.getTime("hora") != null) {
                    datosPaciente.put("hora_cita", rs.getTime("hora").toString());
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error en la consulta SQL: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "Error: Driver JDBC no encontrado", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        System.out.println("*******************");
        System.out.println("Datos encontrados: " + datosPaciente); // Mensaje de depuración mejorado
        return datosPaciente;
        
    }
    // En el archivo CitaDAO.java

public static List<ConsultaPrevia> obtenerConsultasPrevias(String cedulaPaciente) {
    List<ConsultaPrevia> consultas = new ArrayList<>();
    
    // CORRECCIÓN: Se selecciona d.especialidad y se elimina el JOIN a la tabla Tipo.
    String sql = "SELECT c.id_cita, c.fecha, d.especialidad, d.nombres + ' ' + d.apellidos AS nombre_doctor " +
                 "FROM Cita c " +
                 "INNER JOIN Doctor d ON c.id_doctor = d.cedula " +
                 "WHERE c.id_paciente = ? AND c.fecha < GETDATE() " +
                 "ORDER BY c.fecha DESC";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, cedulaPaciente);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // CORRECCIÓN: Se llama al constructor con los 4 argumentos, incluyendo la especialidad.
            consultas.add(new ConsultaPrevia(
                rs.getInt("id_cita"),
                rs.getDate("fecha"),
                rs.getString("especialidad"),
                rs.getString("nombre_doctor")
            ));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al consultar consultas previas: " + e.getMessage());
        e.printStackTrace();
    }
    return consultas;
}
public static List<Map<String, String>> obtenerEvolucion(String cedulaPaciente) {
    List<Map<String, String>> citas = new ArrayList<>();
    
    String sql = "SELECT " +
                 "c.fecha, " +
                 "c.hora, " +
                 "d.nombres + ' ' + d.apellidos AS nombre_doctor, " +
                 "d.especialidad, " +
                 "c.diagnostico, " +  // Nuevo campo diagnóstico
                 "e.pronostico " +     // Pronóstico de la evolución
                 "FROM Cita c " +
                 "INNER JOIN Doctor d ON c.id_doctor = d.cedula " +
                 "LEFT JOIN Evolucion e ON c.id_cita = e.id_cita " +
                 "WHERE c.id_paciente = ? " +
                 "AND c.fecha < CAST(GETDATE() AS DATE) " +
                 "ORDER BY c.fecha DESC, c.hora DESC";
    
    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, cedulaPaciente);
        ResultSet rs = pstmt.executeQuery();
        
        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        
        while (rs.next()) {
            Map<String, String> cita = new HashMap<>();
            
            // Formatear fecha y hora
            if (rs.getDate("fecha") != null) {
                cita.put("fecha", sdfFecha.format(rs.getDate("fecha")));
            }
            if (rs.getTime("hora") != null) {
                cita.put("hora", sdfHora.format(rs.getTime("hora")));
            }
            
            // Datos del doctor y evolución
            cita.put("doctor", rs.getString("nombre_doctor"));
            cita.put("especialidad", rs.getString("especialidad"));
            cita.put("diagnostico", rs.getString("diagnostico")); // Nuevo campo
            cita.put("pronostico", rs.getString("pronostico"));
            
            citas.add(cita);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, 
            "Error al obtener evolución: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, 
            "Error: Driver JDBC no encontrado", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    System.out.println("aaaaaaaaaaaaaaaaaa");
    System.out.println(citas);
    return citas;
}




}