package Ejercicio_2;

import java.util.Objects;

public class Libro {
    private int id;
    private String titulo;
    private String isbn;
    private String categoria;

    public Libro(int id, String titulo, String isbn, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.categoria = categoria;
    }

    // Getters y setters omitidos por brevedad


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id && Objects.equals(titulo, libro.titulo) && Objects.equals(isbn, libro.isbn) && Objects.equals(categoria, libro.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, isbn, categoria);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}