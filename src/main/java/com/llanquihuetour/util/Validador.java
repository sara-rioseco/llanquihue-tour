package com.llanquihuetour.util;

/**
 * Clase utilitaria con las validaciones de atributos comunes a todo
 * el modelo. Centraliza las reglas para evitar duplicar código en los
 * setters y mantener mensajes de error consistentes.
 *
 * Todos los métodos lanzan {@link IllegalArgumentException} cuando el
 * valor no cumple la regla, y retornan el valor validado (sin espacios
 * en los extremos en el caso de los textos).
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public final class Validador {

    /** Letras (incluye acentos y ñ), espacios, puntos, apóstrofes y guiones. */
    private static final String PATRON_SOLO_LETRAS = "[\\p{L}][\\p{L} .'-]*";

    /** Grupos de letras mayúsculas y/o dígitos separados por guiones (ej: PVCT-24). */
    private static final String PATRON_PATENTE = "[A-Z0-9]{2,4}(-[A-Z0-9]{2,4}){1,2}";

    private Validador() {
        // Clase utilitaria: no se instancia.
    }

    /**
     * Valida que un texto no sea nulo ni esté en blanco.
     *
     * @param valor texto a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return el texto sin espacios en los extremos
     */
    public static String requerido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.strip();
    }

    /**
     * Valida que un texto contenga solo letras (con acentos), espacios
     * y signos propios de nombres (punto, apóstrofe, guion). Se usa en
     * nombres de personas, ciudades, regiones e idiomas.
     *
     * @param valor texto a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return el texto sin espacios en los extremos
     */
    public static String soloLetras(String valor, String campo) {
        String texto = requerido(valor, campo);
        if (!texto.matches(PATRON_SOLO_LETRAS)) {
            throw new IllegalArgumentException(
                    campo + " solo puede contener letras (ingresado: \"" + texto + "\").");
        }
        return texto;
    }

    /**
     * Valida que una patente tenga un formato razonable: grupos de
     * letras y/o números separados por guion (ej: PVCT-24, AB-CD-12).
     *
     * @param valor patente a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return la patente en mayúsculas y sin espacios en los extremos
     */
    public static String patente(String valor, String campo) {
        String texto = requerido(valor, campo).toUpperCase();
        if (!texto.matches(PATRON_PATENTE)) {
            throw new IllegalArgumentException(
                    campo + " no tiene un formato válido (ej: PVCT-24).");
        }
        return texto;
    }

    /**
     * Valida que un número sea mayor a cero (precios de servicios,
     * capacidades, duraciones, cantidades).
     *
     * @param valor número a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return el mismo número si es válido
     */
    public static int positivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor a 0.");
        }
        return valor;
    }

    /**
     * Valida que un número no sea negativo (años de experiencia,
     * precios promocionales que pueden ser cero).
     *
     * @param valor número a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return el mismo número si es válido
     */
    public static int noNegativo(int valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo.");
        }
        return valor;
    }

    /**
     * Valida que un objeto no sea nulo.
     *
     * @param <T> tipo del objeto
     * @param valor objeto a validar
     * @param campo nombre del campo (para el mensaje de error)
     * @return el mismo objeto si es válido
     */
    public static <T> T noNulo(T valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " no puede ser nulo.");
        }
        return valor;
    }
}
