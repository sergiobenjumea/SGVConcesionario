package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionDB {
    
    private static final String DB_NAME = "concesionario_db";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // ⚠️ REVISA TU CONTRASEÑA
    
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName(DRIVER);
                conexion = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                System.out.println("✅ Conexión exitosa a: " + DB_NAME);
            }
            return conexion;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: Driver MySQL no encontrado.");
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return null;
        }
    }
    
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}