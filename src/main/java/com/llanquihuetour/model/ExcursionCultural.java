package com.llanquihuetour.model;

/**
 * Subclase que representa una excursión cultural.
 * Hereda de {@link ServicioTuristico} y agrega el lugar
 * histórico que se visita en la excursión.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class ExcursionCultural extends ServicioTuristico {

    private String lugarHistorico;

    /**
     * Crea una nueva excursión cultural.
     *
     * @param nombre nombre del servicio
     * @param destino ciudad o lugar de destino del servicio
     * @param precio precio del servicio en pesos chilenos
     * @param duracionHoras duración del servicio en horas
     * @param lugarHistorico lugar histórico que se visita
     */
    public ExcursionCultural(String nombre, String destino, int precio, int duracionHoras, String lugarHistorico) {
        super(nombre, destino, precio, duracionHoras);
        setLugarHistorico(lugarHistorico);
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        if (lugarHistorico == null || lugarHistorico.isBlank()) {
            throw new IllegalArgumentException("El lugar histórico no puede estar vacío.");
        }
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public String toString() {
        return String.format("""
            Excursión Cultural:
            Nombre: %s
            Destino: %s
            Precio: $%,d CLP
            Duración: %d horas
            Lugar histórico: %s
            """,
                getNombre(),
                getDestino(),
                getPrecio(),
                getDuracionHoras(),
                lugarHistorico
        );
    }
}
