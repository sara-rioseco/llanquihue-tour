package com.llanquihuetour.model;

/**
 * Subclase que representa un paseo lacustre.
 * Hereda de {@link ServicioTuristico} y agrega el tipo
 * de embarcación utilizada en el paseo.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class PaseoLacustre extends ServicioTuristico {

    private String tipoEmbarcacion;

    /**
     * Crea un nuevo paseo lacustre.
     *
     * @param nombre nombre del servicio
     * @param destino ciudad o lugar de destino del servicio
     * @param precio precio del servicio en pesos chilenos
     * @param duracionHoras duración del servicio en horas
     * @param tipoEmbarcacion tipo de embarcación utilizada
     */
    public PaseoLacustre(String nombre, String destino, int precio, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, destino, precio, duracionHoras);
        setTipoEmbarcacion(tipoEmbarcacion);
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        if (tipoEmbarcacion == null || tipoEmbarcacion.isBlank()) {
            throw new IllegalArgumentException("El tipo de embarcación no puede estar vacío.");
        }
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return String.format("""
            Paseo Lacustre:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            Duración: %d horas
            Tipo de embarcación: %s
            """,
                getNombre(),
                getDestino(),
                getPrecio(),
                getDuracionHoras(),
                tipoEmbarcacion
        );
    }
}
