package com.llanquihuetour.model;

/**
 * Representa un tour ofrecido por la agencia Llanquihue Tour.
 * Incluye información sobre nombre, destino y precio.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Tour {

    private String nombre;
    private String destino;
    private int precio;

    /**
     * Crea un nuevo tour.
     *
     * @param nombre nombre del tour
     * @param destino destino del tour
     * @param precio precio del tour en pesos chilenos
     */
    public Tour(String nombre, String destino, int precio) {
        setNombre(nombre);
        setDestino(destino);
        setPrecio(precio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        this.destino = destino;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("""
            Tour:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            """,
                nombre,
                destino,
                precio
        );
    }
}
