/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package inventariomodule.InventarioDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Johann
 */
public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://VLADIMIRJON;"
                + "databaseName=INVENTARIOS;"
                + "encrypt=true;"
                +"trustServerCertificate=true";
    
    private static final String USER = "sa";
    private static final String PASSWORD = "admin";
    
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    
    
    
}
