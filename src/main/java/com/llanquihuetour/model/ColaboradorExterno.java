package com.llanquihuetour.model;

import com.llanquihuetour.util.Validador;

/**
 * Representa un colaborador externo que presta servicios
 * a la agencia Llanquihue Tour (por ejemplo, empresas de
 * transporte o restaurantes asociados).
 * Hereda de {@link Persona} y agrega la empresa que representa
 * y el tipo de servicio que ofrece.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class ColaboradorExterno extends Persona {

    private String empresa;
    private String tipoServicio;

    /**
     * Crea un nuevo colaborador externo.
     *
     * @param nombre nombre del colaborador
     * @param apellido apellido del colaborador
     * @param rut rut del colaborador
     * @param direccion dirección del colaborador
     * @param empresa empresa a la que representa
     * @param tipoServicio tipo de servicio que presta a la agencia
     */
    public ColaboradorExterno(
            String nombre,
            String apellido,
            Rut rut,
            Direccion direccion,
            String empresa,
            String tipoServicio) {

        super(nombre, apellido, rut, direccion);
        setEmpresa(empresa);
        setTipoServicio(tipoServicio);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = Validador.requerido(empresa, "Empresa");
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = Validador.soloLetras(tipoServicio, "Tipo de servicio");
    }

    @Override
    public String mostrarResumen() {
        return String.format("Colaborador Externo: %s %s | Empresa: %s | Servicio: %s",
                getNombre(), getApellido(), empresa, tipoServicio);
    }

    @Override
    public String toString() {
        return String.format("""
            Datos del Colaborador Externo:
            Nombre: %s %s
            RUT: %s
            Empresa: %s
            Tipo de servicio: %s

            %s
            """,
                getNombre(),
                getApellido(),
                getRut(),
                empresa,
                tipoServicio,
                getDireccion()
        );
    }
}
