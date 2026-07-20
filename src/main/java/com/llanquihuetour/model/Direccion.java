package com.llanquihuetour.model;

import com.llanquihuetour.util.Validador;

/**
 * Representa la dirección física asociada a una persona.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Direccion {

    private String calle;
    private String ciudad;
    private String region;

    /**
     * Crea una nueva dirección.
     *
     * @param calle nombre de la calle y número
     * @param ciudad nombre de la ciudad
     * @param region nombre de la región
     */
    public Direccion(String calle, String ciudad, String region) {
        setCalle(calle);
        setCiudad(ciudad);
        setRegion(region);
    }

    /**
     * Retorna la calle.
     *
     * @return nombre de la calle y número
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Actualiza la calle.
     *
     * @param calle nuevo nombre de la calle y número
     */
    public void setCalle(String calle) {
        this.calle = Validador.requerido(calle, "Calle");
    }

    /**
     * Retorna la ciudad.
     *
     * @return nombre de la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Actualiza la ciudad.
     *
     * @param ciudad nuevo nombre de la ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = Validador.soloLetras(ciudad, "Ciudad");
    }

    /**
     * Retorna la región.
     *
     * @return nombre de la región
     */
    public String getRegion() {
        return region;
    }

    /**
     * Actualiza la región.
     *
     * @param region nuevo nombre de la región
     */
    public void setRegion(String region) {
        this.region = Validador.soloLetras(region, "Región");
    }

    @Override
    public String toString() {
        return String.format("""
            Datos de Dirección:
            Calle: %s
            Ciudad: %s
            Región: %s
            """,
                calle,
                ciudad,
                region
        );
    }
}