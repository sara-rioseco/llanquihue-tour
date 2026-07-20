package com.llanquihuetour.model;

import com.llanquihuetour.util.Validador;

/**
 * Clase base representa una persona
 * relacionada a la agencia de turismo Llanquihue Tour.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Persona implements Registrable {

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
        this.nombre = Validador.soloLetras(nombre, "Nombre");
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = Validador.soloLetras(apellido, "Apellido");
    }

    public Rut getRut() {
        return rut;
    }

    public void setRut(Rut rut) {
        this.rut = Validador.noNulo(rut, "RUT");
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = Validador.noNulo(direccion, "Dirección");
    }

    @Override
    public String mostrarResumen() {
        return String.format("Persona: %s %s | RUT: %s", nombre, apellido, rut);
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