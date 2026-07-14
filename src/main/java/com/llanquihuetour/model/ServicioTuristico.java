package com.llanquihuetour.model;

/**
 * Superclase abstracta que representa un servicio turístico ofrecido
 * por la agencia Llanquihue Tour. Define los atributos comunes
 * a todos los servicios: nombre, destino, precio y duración en horas.
 *
 * Es abstracta porque no existe un servicio turístico "genérico":
 * cada servicio concreto debe ser una de sus subclases y está obligado
 * a definir su propia versión de {@link #mostrarInformacion()}.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public abstract class ServicioTuristico implements Registrable {

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

    @Override
    public String mostrarResumen() {
        return String.format("Servicio Turístico: %s | Destino: %s | Precio: $%,d CLP | Duración: %d horas",
                nombre, destino, precio, duracionHoras);
    }

    /**
     * Muestra por consola la información específica del servicio.
     * Cada subclase concreta está obligada a implementar su propia versión.
     */
    public abstract void mostrarInformacion();

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
