package TiendaAlimentacion;



import java.util.List;
import java.util.Map;

public interface ProductoDAO {
    void agregarProducto(Producto producto);
    Producto obtenerPorId(int id);
    List<Producto> obtenerTodos();
    List<Producto> buscarPorNombre(String nombre);
    List<Producto> obtenerStockBajo();
    List<Producto> obtenerProximosACaducar();
    List<Producto> obtenerPorCategoria(String categoria);
    void actualizarProducto(Producto producto);
    void eliminarProducto(int id);
    double calcularValorTotalInventario();
    Map<String, Integer> obtenerResumenCategorias();
}