package TiendaAlimentacion;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestorInventario {
    private ProductoDAO productoDAO;
    private Scanner scanner;

    public GestorInventario() {
        this.productoDAO = new ProductoDAOImpl();
        this.scanner = new Scanner(System.in);
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        productoDAO.agregarProducto(new Producto("Leche Entera", 50, 1.20, "Lácteos", "2025-07-15", true));
        productoDAO.agregarProducto(new Producto("Pan Integral", 8, 2.50, "Panadería", "2025-06-05", true));
        productoDAO.agregarProducto(new Producto("Arroz Blanco", 30, 1.80, "Granos", "2026-01-10", false));
        productoDAO.agregarProducto(new Producto("Atún en Lata", 15, 3.40, "Conservas", "2027-03-20", false));
        productoDAO.agregarProducto(new Producto("Yogur Natural", 5, 0.90, "Lácteos", "2025-06-10", true));
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO - TIENDA DE ALIMENTACIÓN ===");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Consultar inventario completo");
            System.out.println("3. Productos con stock bajo");
            System.out.println("4. Productos próximos a caducar");
            System.out.println("5. Buscar producto");
            System.out.println("6. Actualizar stock/producto");
            System.out.println("7. Resumen por categorías");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: registrarProducto(); break;
                case 2: consultarInventario(); break;
                case 3: mostrarStockBajo(); break;
                case 4: mostrarProximosACaducar(); break;
                case 5: buscarProducto(); break;
                case 6: actualizarProducto(); break;
                case 7: mostrarResumenCategorias(); break;
                case 8: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida.");
            }

            if (opcion != 8) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 8);
    }

    private void registrarProducto() {
        System.out.println("\n--- REGISTRAR NUEVO PRODUCTO ---");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Fecha de caducidad (YYYY-MM-DD): ");
        String fechaCaducidad = scanner.nextLine();

        System.out.print("¿Es perecedero? (s/n): ");
        boolean perecedero = scanner.nextLine().equalsIgnoreCase("s");

        Producto nuevoProducto = new Producto(nombre, cantidad, precio, categoria, fechaCaducidad, perecedero);
        productoDAO.agregarProducto(nuevoProducto);

        System.out.println("\nProducto registrado exitosamente:");
        System.out.println(nuevoProducto);
    }

    private void consultarInventario() {
        System.out.println("\n--- INVENTARIO COMPLETO ---");
        List<Producto> productos = productoDAO.obtenerTodos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        System.out.printf("Total de productos: %d\n", productos.size());
        System.out.printf("Valor total del inventario: %.2f€\n", productoDAO.calcularValorTotalInventario());
        System.out.println("----------------------------------");

        productos.forEach(System.out::println);
    }

    private void mostrarStockBajo() {
        System.out.println("\n--- PRODUCTOS CON STOCK BAJO (<10 unidades) ---");
        List<Producto> stockBajo = productoDAO.obtenerStockBajo();

        if (stockBajo.isEmpty()) {
            System.out.println("No hay productos con stock bajo.");
            return;
        }

        stockBajo.forEach(System.out::println);
        System.out.println("Total: " + stockBajo.size() + " productos");
    }

    private void mostrarProximosACaducar() {
        System.out.println("\n--- PRODUCTOS PRÓXIMOS A CADUCAR ---");
        List<Producto> proximosACaducar = productoDAO.obtenerProximosACaducar();

        if (proximosACaducar.isEmpty()) {
            System.out.println("No hay productos próximos a caducar.");
            return;
        }

        proximosACaducar.forEach(System.out::println);
        System.out.println("Total: " + proximosACaducar.size() + " productos");
    }

    private void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.print("Ingrese nombre o ID del producto: ");
        String busqueda = scanner.nextLine();

        List<Producto> resultados;
        try {
            int id = Integer.parseInt(busqueda);
            Producto producto = productoDAO.obtenerPorId(id);
            resultados = producto != null ? List.of(producto) : List.of();
        } catch (NumberFormatException e) {
            resultados = productoDAO.buscarPorNombre(busqueda);
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            System.out.println("Resultados de la búsqueda:");
            resultados.forEach(System.out::println);
        }
    }

    private void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        System.out.print("Ingrese ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = productoDAO.obtenerPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto actual:");
        System.out.println(producto);

        System.out.println("\nIngrese nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nombre [" + producto.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) producto.setNombre(nombre);

        System.out.print("Cantidad [" + producto.getCantidad() + "]: ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) producto.setCantidad(Integer.parseInt(cantidadStr));

        System.out.print("Precio [" + producto.getPrecio() + "]: ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) producto.setPrecio(Double.parseDouble(precioStr));

        System.out.print("Categoría [" + producto.getCategoria() + "]: ");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) producto.setCategoria(categoria);

        System.out.print("Fecha caducidad [" + producto.getFechaCaducidad() + "]: ");
        String fechaCaducidad = scanner.nextLine();
        if (!fechaCaducidad.isEmpty()) producto.setFechaCaducidad(fechaCaducidad);

        System.out.print("¿Es perecedero? [" + (producto.isPerecedero() ? "s" : "n") + "]: ");
        String perecederoStr = scanner.nextLine();
        if (!perecederoStr.isEmpty()) producto.setPerecedero(perecederoStr.equalsIgnoreCase("s"));

        productoDAO.actualizarProducto(producto);
        System.out.println("\nProducto actualizado:");
        System.out.println(producto);
    }

    private void mostrarResumenCategorias() {
        System.out.println("\n--- RESUMEN POR CATEGORÍAS ---");
        Map<String, Integer> resumen = productoDAO.obtenerResumenCategorias();

        if (resumen.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        System.out.println("Categoría\t\tCantidad de Productos");
        System.out.println("----------------------------------");
        resumen.forEach((categoria, cantidad) ->
                System.out.printf("%-20s\t%d\n", categoria, cantidad));

        System.out.println("\nTotal categorías: " + resumen.size());
    }
}