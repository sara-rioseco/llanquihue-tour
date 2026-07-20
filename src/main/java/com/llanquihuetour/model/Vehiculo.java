package com.llanquihuetour.model;

import com.llanquihuetour.util.Validador;

/**
 * Representa un vehículo utilizado por la agencia Llanquihue Tour
 * para trasladar a los pasajeros durante los distintos servicios
 * turísticos. No es una {@link Persona}, por lo que implementa
 * {@link Registrable} directamente.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Vehiculo implements Registrable {

    private String patente;
    private String marca;
    private String modelo;
    private int capacidad;

    /**
     * Crea un nuevo vehículo.
     *
     * @param patente patente del vehículo
     * @param marca marca del vehículo
     * @param modelo modelo del vehículo
     * @param capacidad capacidad de pasajeros del vehículo
     */
    public Vehiculo(String patente, String marca, String modelo, int capacidad) {
        setPatente(patente);
        setMarca(marca);
        setModelo(modelo);
        setCapacidad(capacidad);
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = Validador.patente(patente, "Patente");
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = Validador.requerido(marca, "Marca");
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = Validador.requerido(modelo, "Modelo");
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = Validador.positivo(capacidad, "Capacidad");
    }

    @Override
    public String mostrarResumen() {
        return String.format("Vehículo: %s %s | Patente: %s | Capacidad: %d pasajeros",
                marca, modelo, patente, capacidad);
    }

    @Override
    public String toString() {
        return String.format("""
            Datos del Vehículo:
            Patente: %s
            Marca: %s
            Modelo: %s
            Capacidad: %d pasajeros
            """,
                patente,
                marca,
                modelo,
                capacidad
        );
    }
}
