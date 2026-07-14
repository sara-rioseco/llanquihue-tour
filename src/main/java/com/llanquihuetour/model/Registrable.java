package com.llanquihuetour.model;

/**
 * Contrato común para las entidades del sistema que pueden
 * registrarse y mostrarse de forma resumida (guías turísticos,
 * vehículos, colaboradores externos, etc.).
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public interface Registrable {

    /**
     * Genera un resumen textual con los datos principales de la entidad.
     *
     * @return resumen de la entidad
     */
    String mostrarResumen();
}
