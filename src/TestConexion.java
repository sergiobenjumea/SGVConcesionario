import config.ConexionDB;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   PRUEBA DE CONEXIÓN A MYSQL");
        System.out.println("========================================\n");
        
        // Intentar conectar
        Connection con = ConexionDB.getConexion();
        
        if (con != null) {
            System.out.println("\n✅ ¡CONEXIÓN EXITOSA!");
            System.out.println("✅ Base de datos: concesionario_db");
            System.out.println("✅ Todo listo para trabajar\n");
            
            // Cerrar
            ConexionDB.cerrarConexion();
            
        } else {
            System.out.println("\n❌ NO SE PUDO CONECTAR");
            System.out.println("❌ Revisa los mensajes de error\n");
        }
        
        System.out.println("========================================");
    }
}
