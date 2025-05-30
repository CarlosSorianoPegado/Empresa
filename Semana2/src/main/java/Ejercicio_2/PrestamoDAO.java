package Ejercicio_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public void registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamo (id_libro, id_usuario, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setDate(3, prestamo.getFechaPrestamo());
            stmt.setDate(4, prestamo.getFechaDevolucion());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating loan failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    prestamo.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating loan failed, no ID obtained.");
                }
            }

            System.out.println("Préstamo registrado exitosamente con ID: " + prestamo.getId());

        } catch (SQLException e) {
            System.err.println("Error al registrar préstamo: " + e.getMessage());
        }
    }

    public List<String> obtenerPrestamosActivos() {
        List<String> prestamos = new ArrayList<>();
        String sql = "SELECT p.id_prestamo, u.nombre, l.titulo, p.fecha_prestamo, DATEDIFF(CURDATE(), p.fecha_prestamo) AS dias_prestado " +
                "FROM Prestamo p " +
                "JOIN Usuario u ON p.id_usuario = u.id_usuario " +
                "JOIN Libro l ON p.id_libro = l.id_libro " +
                "WHERE p.fecha_devolucion IS NULL";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String linea = String.format("Préstamo ID: %d - Libro: %s prestado a %s el %s (%d días)",
                        rs.getInt("id_prestamo"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_prestamo"),
                        rs.getInt("dias_prestado"));
                prestamos.add(linea);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener préstamos activos: " + e.getMessage());
        }

        return prestamos;
    }
}