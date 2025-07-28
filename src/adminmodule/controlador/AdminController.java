
package adminmodule.controlador;

import adminmodule.dao.*;
import adminmodule.modelo.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final DoctorDAO doctorDAO;

    public AdminController() {
        this.doctorDAO = new DoctorDAO();
    }

    public boolean registrarDoctor(String cedula, String nombres, String apellidos,
                                 Date fechaNacimiento, String sexo, String correo,
                                 String contrasena, String especialidad) {
        Doctor nuevoDoctor = new Doctor(
            cedula, nombres, apellidos, fechaNacimiento,
            sexo, correo, contrasena, "doctor", especialidad
        );
        return doctorDAO.guardar(nuevoDoctor);
    }
    
    public boolean actualizarDoctor(String cedula, String nombres, String apellidos,
                                   Date fechaNacimiento, String sexo, String correo,
                                   String contrasena, String especialidad) {
    
        Doctor doctorExistente = doctorDAO.obtenerPorCedula(cedula);
        if (doctorExistente == null) {
            System.out.println("Error: No existe un médico con esa cédula.");
            return false;
        }

        doctorExistente.setNombres(nombres);
        doctorExistente.setApellidos(apellidos);
        doctorExistente.setFechaNacimiento(fechaNacimiento);
        doctorExistente.setSexo(sexo);
        doctorExistente.setCorreo(correo);
        doctorExistente.setContrasena(contrasena);
        doctorExistente.setEspecialidad(especialidad);

        return doctorDAO.actualizar(doctorExistente);
    }
    
    public boolean eliminarDoctorConConfirmacion(String cedula) {
        Scanner scanner = new Scanner(System.in);
        if (!doctorDAO.existe(cedula)) {
            System.out.println("El doctor con cédula " + cedula + " no está registrado en el sistema.");
            return false;
        }

        System.out.println("¿Está seguro de que desea eliminar al doctor con cédula " + cedula + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Eliminación cancelada por el usuario.");
            return false;
        }

        boolean eliminado = doctorDAO.eliminar(cedula);
        if (eliminado) {
            System.out.println("Doctor eliminado correctamente del sistema.");
            return true;
        } else {
            System.out.println("Hubo un error al intentar eliminar al doctor.");
            return false;
        }
    }

    public List<Doctor> obtenerTodosDoctores() {
        return doctorDAO.obtenerTodos();
    }

    public List<Paciente> obtenerTodosPacientes() {
        return new PacienteDAO().obtenerTodos();
    }
}