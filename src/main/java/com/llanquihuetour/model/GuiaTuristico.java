package com.llanquihuetour.model;

/**
 * Representa un guía turístico de la agencia Llanquihue Tour.
 * Hereda de {@link Persona} y agrega el idioma principal
 * y los años de experiencia del guía.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GuiaTuristico extends Persona {

    private String idioma;
    private int aniosExperiencia;

    /**
     * Crea un nuevo guía turístico.
     *
     * @param nombre nombre del guía
     * @param apellido apellido del guía
     * @param rut rut del guía
     * @param direccion dirección del guía
     * @param idioma idioma principal en el que atiende
     * @param aniosExperiencia años de experiencia como guía
     */
    public GuiaTuristico(
            String nombre,
            String apellido,
            Rut rut,
            Direccion direccion,
            String idioma,
            int aniosExperiencia) {

        super(nombre, apellido, rut, direccion);
        setIdioma(idioma);
        setAniosExperiencia(aniosExperiencia);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            throw new IllegalArgumentException("El idioma no puede estar vacío.");
        }
        this.idioma = idioma;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String mostrarResumen() {
        return String.format("Guía Turístico: %s %s | Idioma: %s | Experiencia: %d años",
                getNombre(), getApellido(), idioma, aniosExperiencia);
    }

    @Override
    public String toString() {
        return String.format("""
            Datos del Guía Turístico:
            Nombre: %s %s
            RUT: %s
            Idioma: %s
            Años de experiencia: %d

            %s
            """,
                getNombre(),
                getApellido(),
                getRut(),
                idioma,
                aniosExperiencia,
                getDireccion()
        );
    }
}
