package TiendaAlimentacion;



public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String categoria;
    private String fechaCaducidad;
    private boolean perecedero;

    public Producto(String nombre, int cantidad, double precio, String categoria, String fechaCaducidad, boolean perecedero) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaCaducidad = fechaCaducidad;
        this.perecedero = perecedero;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(String fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
    public boolean isPerecedero() { return perecedero; }
    public void setPerecedero(boolean perecedero) { this.perecedero = perecedero; }

    // Método para verificar si el stock está bajo
    public boolean isStockBajo() {
        return cantidad < 10;
    }

    // Método para verificar si el producto está próximo a caducar (simplificado)
    public boolean isProximoACaducar() {
        return perecedero && fechaCaducidad != null; // Implementación real necesitaría parsear la fecha
    }

    @Override
    public String toString() {
        String stockStatus = isStockBajo() ? " (STOCK BAJO)" : "";
        String caducidadStatus = isProximoACaducar() ? " (PRÓXIMO A CADUCAR)" : "";
        return String.format("ID: %d | %s | Cantidad: %d%s | Precio: %.2f€ | Categoría: %s | Caducidad: %s%s",
                id, nombre, cantidad, stockStatus, precio, categoria, fechaCaducidad, caducidadStatus);
    }
}