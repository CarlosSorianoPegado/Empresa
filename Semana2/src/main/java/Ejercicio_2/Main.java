package Ejercicio_2;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrestamoDAO dao = new PrestamoDAO();

        // Registrar un nuevo préstamo (ID se generará automáticamente)
        Prestamo nuevo = new Prestamo(0, 5, 1, Date.valueOf("2025-05-30"), null);
        dao.registrarPrestamo(nuevo);

        // Mostrar préstamos activos
        List<String> activos = dao.obtenerPrestamosActivos();
        for (String p : activos) {
            System.out.println(p);
        }
    }
}