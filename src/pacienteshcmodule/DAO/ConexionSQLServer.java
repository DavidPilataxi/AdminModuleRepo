package pacienteshcmodule.DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    public static Connection conectar() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://VID\\SQLEXPRESS;"
                   + "databaseName=polisalud;"
                   + "encrypt=true;"
                   + "trustServerCertificate=true;"
                   + "loginTimeout=30";
        String user = "sa";
        String password = "P@ssw0rd";
        return DriverManager.getConnection(url, user, password);
    }
}



