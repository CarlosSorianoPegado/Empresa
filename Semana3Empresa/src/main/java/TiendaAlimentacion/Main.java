package TiendaAlimentacion;


public class Main {
    public static void main(String[] args) {
        System.out.println("SISTEMA DE GESTIÓN DE INVENTARIO - TIENDA DE ALIMENTACIÓN");
        System.out.println("=========================================================");

        GestorInventario gestor = new GestorInventario();
        gestor.mostrarMenu();
    }
}