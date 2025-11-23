package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase para manejar la conexión a MySQL
 * Proyecto: Sistema de Gestión de Ventas - Concesionario
 */
public class ConexionDB {
    
    // ========== CONFIGURACIÓN DE LA BASE DE DATOS ==========
    private static final String DB_NAME = "concesionario_db";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // ⚠️ PON TU CONTRASEÑA AQUÍ
    
    // URL completa de conexión
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // Objeto de conexión
    private static Connection conexion = null;
    
    /**
     * Método para obtener la conexión a la base de datos
     * @return Connection objeto de conexión o null si falla
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                // 1. Cargar el driver de MySQL
                Class.forName(DRIVER);
                
                // 2. Establecer la conexión
                conexion = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                
                System.out.println("✅ Conexión exitosa a: " + DB_NAME);
            }
            
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: Driver MySQL no encontrado");
            System.err.println("Verifica que mysql-connector-j.jar esté en Libraries");
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, 
                "Error: Driver MySQL no encontrado\n" +
                "Verifica que el archivo .jar esté agregado al proyecto", 
                "Error de Driver", 
                JOptionPane.ERROR_MESSAGE);
            
            return null;
            
        } catch (SQLException e) {
            System.err.println("❌ ERROR de conexión a la base de datos");
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, 
                "Error de conexión:\n" + e.getMessage() + "\n\n" +
                "Verifica:\n" +
                "1. MySQL está corriendo\n" +
                "2. Usuario y contraseña son correctos\n" +
                "3. La base de datos existe", 
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
            
            return null;
        }
    }
    
    /**
     * Método para cerrar la conexión
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✅ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}
