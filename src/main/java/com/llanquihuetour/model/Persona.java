package com.llanquihuetour.model;

/**
 * Clase base representa una persona
 * relacionada a la agencia de turismo Llanquihue Tour.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Persona {

    private String nombre;
    private String apellido;
    private Rut rut;
    private Direccion direccion;

    /**
     * Crea una instancia de Persona.
     *
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param rut rut de la persona
     * @param direccion dirección de la persona
     */
    public Persona(
            String nombre,
            String apellido,
            Rut rut,
            Direccion direccion) {

        setNombre(nombre);
        setApellido(apellido);
        setRut(rut);
        setDireccion(direccion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarTexto(nombre, "Nombre");
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        validarTexto(apellido, "Apellido");
        this.apellido = apellido;
    }

    public Rut getRut() {
        return rut;
    }

    public void setRut(Rut rut) {
        if (rut == null) {
            throw new IllegalArgumentException(
                    "RUT no puede ser nulo"
            );
        }
        this.rut = rut;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException(
                    "Dirección no puede ser nulo"
            );
        }
        this.direccion = direccion;
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    campo + " no puede estar vacío."
            );
        }
    }

    @Override
    public String toString() {
        return String.format("""
            Nombre: %s %s
            RUT: %s
            
            %s
            """,
                nombre,
                apellido,
                rut,
                direccion
        );
    }
}