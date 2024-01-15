import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class System_Terminal{
    private static Map<String, String> usuarios = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            inicializarUsuarios();
            
            System.out.print("Ingrese su nombre de usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            if (verificarCredenciales(username, password)) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
                mostrarMenuProductos(scanner);
            } else {
                System.out.println("Credenciales incorrectas. Inténtelo nuevamente.");
            }
        } finally {
            // Cerrar el Scanner al finalizar
            scanner.close();
        }
    }

    private static void inicializarUsuarios() {
        // Puedes agregar más usuarios aquí (username, password)
        usuarios.put("Samuel", "43531090");
        usuarios.put("Lucas", "2995317981");

        // Inicializar el mapa de productos
        productos = new HashMap<>();
    }

    private static boolean verificarCredenciales(String username, String password) {
        // Verificar si las credenciales son correctas
        return usuarios.containsKey(username) && usuarios.get(username).equals(password);
    }   
 
     private static Map<String, Producto> productos;

    private static void mostrarMenuProductos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nMenú de Ingreso de Productos:");
            System.out.println("1. Añadir Producto");
            System.out.println("2. Ver Lista de Productos");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Editar Producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (opcion) {
                case 1:
                    agregarProducto(scanner);
                    break;
                case 2:
                    verListaProductos();
                    break;
                case 3:
                    eliminarProducto(scanner);
                    break;
                case 4:
                    editarProducto(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del Menú de Ingreso de Productos.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void agregarProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea después de nextDouble()

        productos.put(nombre.toLowerCase(), new Producto(nombre, precio));
        System.out.println("Producto añadido exitosamente.");
    }

    private static void verListaProductos() {
        System.out.println("\nLista de Productos:");
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    private static void eliminarProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();

        if (productos.containsKey(nombre.toLowerCase())) {
            productos.remove(nombre.toLowerCase());
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void editarProducto(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a editar: ");
        String nombreAntiguo = scanner.nextLine();

        if (productos.containsKey(nombreAntiguo.toLowerCase())) {
            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nuevoNombre = scanner.nextLine();

            System.out.print("Ingrese el nuevo precio del producto: ");
            String nuevoPrecioStr = scanner.nextLine();

            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);

                // Eliminar el antiguo producto y agregar uno nuevo con el nuevo nombre y precio
                Producto productoEditado = new Producto(nuevoNombre, nuevoPrecio);
                productos.remove(nombreAntiguo.toLowerCase());
                productos.put(nuevoNombre.toLowerCase(), productoEditado);

                System.out.println("Producto editado exitosamente.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido para el precio.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Precio: $" + precio;
    }
}