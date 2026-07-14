package com.llanquihuetour.model;

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
        if (patente == null || patente.isBlank()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
        this.patente = patente.toUpperCase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
        this.capacidad = capacidad;
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
