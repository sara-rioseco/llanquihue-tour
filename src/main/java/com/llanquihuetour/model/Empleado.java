package com.llanquihuetour.model;

import com.llanquihuetour.util.Validador;

/**
 * Representa un empleado de la agencia de Turismo Llanquihue Tour.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Empleado extends Persona {

    private String cargo;

    /**
     * Crea un nuevo empleado.
     *
     * @param nombre nombre del empleado
     * @param apellido apellido del empleado
     * @param rut rut del empleado
     * @param direccion dirección del empleado
     * @param cargo cargo del empleado
     */
    public Empleado(
            String nombre,
            String apellido,
            Rut rut,
            Direccion direccion,
            String cargo) {

        super(nombre, apellido, rut, direccion);
        setCargo(cargo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = Validador.soloLetras(cargo, "Cargo");
    }

    @Override
    public String mostrarResumen() {
        return String.format("Empleado: %s %s | Cargo: %s | RUT: %s",
                getNombre(), getApellido(), cargo, getRut());
    }

    @Override
    public String toString() {
        return String.format("""
            Datos del Empleado:
            Nombre: %s %s
            RUT: %s
            Cargo: %s
            
            %s
            """,
                getNombre(),
                getApellido(),
                getRut(),
                cargo,
                getDireccion()
        );
    }
}