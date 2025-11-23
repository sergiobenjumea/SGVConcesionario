import config.ConexionDB;
import modelo.dao.ClienteDAO;
import modelo.dto.ClienteDTO;
import java.util.Date;
import java.util.List;

public class TestClienteDAO {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   PRUEBA DE ClienteDAO (CRUD)");
        System.out.println("========================================\n");
        
        ClienteDAO dao = new ClienteDAO();
        
        // ===== 1. CREAR un nuevo cliente =====
        System.out.println("1️⃣ CREAR nuevo cliente:");
        ClienteDTO nuevoCliente = new ClienteDTO();
        nuevoCliente.setTipoIdentificacion("CC");
        nuevoCliente.setIdentificacion("1001234567");
        nuevoCliente.setNombre("Pedro Sánchez");
        nuevoCliente.setFechaNacimiento(new Date(90, 0, 15)); // 1990-01-15
        nuevoCliente.setEdad(35);
        nuevoCliente.setEmail("pedro.sanchez@email.com");
        
        int resultado = dao.crear(nuevoCliente);
        System.out.println("Resultado: " + (resultado > 0 ? "EXITOSO" : "FALLÓ") + "\n");
        
        // ===== 2. LEER por identificación =====
        System.out.println("2️⃣ LEER cliente por identificación:");
        ClienteDTO clienteLeido = dao.leerPorIdentificacion("1001234567");
        if (clienteLeido != null) {
            System.out.println("   ID: " + clienteLeido.getIdCliente());
            System.out.println("   Nombre: " + clienteLeido.getNombre());
            System.out.println("   Email: " + clienteLeido.getEmail() + "\n");
        }
        
        // ===== 3. LEER TODOS =====
        System.out.println("3️⃣ LEER todos los clientes:");
        List<ClienteDTO> todos = dao.leerTodos();
        for (ClienteDTO c : todos) {
            System.out.println("   - " + c.getNombre() + " (" + c.getIdentificacion() + ")");
        }
        System.out.println();
        
        // ===== 4. ACTUALIZAR =====
        if (clienteLeido != null) {
            System.out.println("4️⃣ ACTUALIZAR cliente:");
            clienteLeido.setEmail("pedro.sanchez.nuevo@email.com");
            int resultadoUpdate = dao.actualizar(clienteLeido);
            System.out.println("Resultado: " + (resultadoUpdate > 0 ? "ACTUALIZADO" : "FALLÓ") + "\n");
        }
        
        // ===== 5. ELIMINAR (descomenta si quieres probarlo) =====
        // System.out.println("5️⃣ ELIMINAR cliente:");
        // int resultadoDelete = dao.eliminar(clienteLeido.getIdCliente());
        // System.out.println("Resultado: " + (resultadoDelete > 0 ? "ELIMINADO" : "FALLÓ") + "\n");
        
        System.out.println("========================================");
        System.out.println("   PRUEBA COMPLETADA");
        System.out.println("========================================");
        
        ConexionDB.cerrarConexion();
    }
}
