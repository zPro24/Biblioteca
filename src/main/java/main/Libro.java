package main;

/**
 * Clase que representa un libro dentro de la biblioteca.
 */
public class Libro {
    private String codigo;
    private String titulo;

    public Libro(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Título: " + titulo;
    }
}