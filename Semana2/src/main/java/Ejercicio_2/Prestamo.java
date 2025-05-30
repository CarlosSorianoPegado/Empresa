package Ejercicio_2;

import java.sql.Date;
import java.util.Objects;

public class Prestamo {
    private int id;
    private int idLibro;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(int id, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaDevolucion) {
        this.id = id;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }
    // Getters y setters omitidos por brevedad


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", idLibro=" + idLibro +
                ", idUsuario=" + idUsuario +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return id == prestamo.id && idLibro == prestamo.idLibro && idUsuario == prestamo.idUsuario && Objects.equals(fechaPrestamo, prestamo.fechaPrestamo) && Objects.equals(fechaDevolucion, prestamo.fechaDevolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idLibro, idUsuario, fechaPrestamo, fechaDevolucion);
    }
}