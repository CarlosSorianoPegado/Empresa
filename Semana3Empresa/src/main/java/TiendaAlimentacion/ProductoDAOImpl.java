package TiendaAlimentacion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductoDAOImpl implements ProductoDAO {
    private List<Producto> productos = new ArrayList<>();
    private static int contadorId = 1;

    @Override
    public void agregarProducto(Producto producto) {
        producto.setId(contadorId++);
        productos.add(producto);
    }

    @Override
    public Producto obtenerPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> obtenerStockBajo() {
        return productos.stream()
                .filter(Producto::isStockBajo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> obtenerProximosACaducar() {
        return productos.stream()
                .filter(Producto::isProximoACaducar)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> obtenerPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarProducto(Producto producto) {
        Producto existente = obtenerPorId(producto.getId());
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setCantidad(producto.getCantidad());
            existente.setPrecio(producto.getPrecio());
            existente.setCategoria(producto.getCategoria());
            existente.setFechaCaducidad(producto.getFechaCaducidad());
            existente.setPerecedero(producto.isPerecedero());
        }
    }

    @Override
    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
    }

    @Override
    public double calcularValorTotalInventario() {
        return productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();
    }

    @Override
    public Map<String, Integer> obtenerResumenCategorias() {
        Map<String, Integer> resumen = new HashMap<>();
        for (Producto p : productos) {
            resumen.merge(p.getCategoria(), 1, Integer::sum);
        }
        return resumen;
    }
}