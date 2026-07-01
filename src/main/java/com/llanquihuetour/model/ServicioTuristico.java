package com.llanquihuetour.model;

/**
 * Superclase que representa un servicio turístico ofrecido
 * por la agencia Llanquihue Tour. Define los atributos comunes
 * a todos los servicios: nombre, destino, precio y duración en horas.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class ServicioTuristico {

    private String nombre;
    private String destino;
    private int precio;
    private int duracionHoras;

    /**
     * Crea un nuevo servicio turístico.
     *
     * @param nombre nombre del servicio
     * @param destino ciudad o lugar de destino del servicio
     * @param precio precio del servicio en pesos chilenos
     * @param duracionHoras duración del servicio en horas
     */
    public ServicioTuristico(String nombre, String destino, int precio, int duracionHoras) {
        setNombre(nombre);
        setDestino(destino);
        setPrecio(precio);
        setDuracionHoras(duracionHoras);
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

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0 horas.");
        }
        this.duracionHoras = duracionHoras;
    }

    public void mostrarInformacion() {
        System.out.printf("""
            Servicio Turístico:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            Duración: %d horas
            %n""",
                nombre,
                destino,
                precio,
                duracionHoras
        );
    }

    @Override
    public String toString() {
        return String.format("""
            Servicio Turístico:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            Duración: %d horas
            """,
                nombre,
                destino,
                precio,
                duracionHoras
        );
    }
}
