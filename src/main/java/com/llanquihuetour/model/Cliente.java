package com.llanquihuetour.model;

/**
 * Representa un cliente de la agencia de turismo Llanquihue Tour.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Cliente extends Persona {

    private String tipoCliente;

    /**
     * Crea un nuevo cliente.
     *
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param rut rut del cliente
     * @param direccion dirección del cliente
     * @param tipoCliente tipo de cliente
     */
    public Cliente(
            String nombre,
            String apellido,
            Rut rut,
            Direccion direccion,
            String tipoCliente) {

        super(nombre, apellido, rut, direccion);
        setTipoCliente(tipoCliente);
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {

        if (tipoCliente == null || tipoCliente.isBlank()) {
            throw new IllegalArgumentException(
                    "El tipo de cliente no puede estar vacío."
            );
        }

        this.tipoCliente = tipoCliente;
    }

    @Override
    public String mostrarResumen() {
        return String.format("Cliente: %s %s | Tipo: %s | RUT: %s",
                getNombre(), getApellido(), tipoCliente, getRut());
    }

    @Override
    public String toString() {
        return String.format("""
            Datos del Cliente:
            Nombre: %s %s
            RUT: %s
            Tipo de Cliente: %s
            
            %s
            """,
                getNombre(),
                getApellido(),
                getRut(),
                tipoCliente,
                getDireccion()
        );
    }
}