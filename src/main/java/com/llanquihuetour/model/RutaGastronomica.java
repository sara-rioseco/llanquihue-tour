package com.llanquihuetour.model;

/**
 * Subclase que representa una ruta gastronómica.
 * Hereda de {@link ServicioTuristico} y agrega el número
 * de paradas que incluye la ruta.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class RutaGastronomica extends ServicioTuristico {

    private int numeroDeParadas;

    /**
     * Crea una nueva ruta gastronómica.
     *
     * @param nombre nombre del servicio
     * @param destino ciudad o lugar de destino del servicio
     * @param precio precio del servicio en pesos chilenos
     * @param duracionHoras duración del servicio en horas
     * @param numeroDeParadas número de paradas de la ruta
     */
    public RutaGastronomica(String nombre, String destino, int precio, int duracionHoras, int numeroDeParadas) {
        super(nombre, destino, precio, duracionHoras);
        setNumeroDeParadas(numeroDeParadas);
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        if (numeroDeParadas <= 0) {
            throw new IllegalArgumentException("El número de paradas debe ser mayor a 0.");
        }
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public String toString() {
        return String.format("""
            Ruta Gastronómica:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            Duración: %d horas
            Número de paradas: %d
            """,
                getNombre(),
                getDestino(),
                getPrecio(),
                getDuracionHoras(),
                numeroDeParadas
        );
    }
}
