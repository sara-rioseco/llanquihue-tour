package com.llanquihuetour.model;

import com.llanquihuetour.exception.RutInvalidException;

/**
 * Representa un RUT Chileno.
 * Esta clase valida el formato del RUT
 * antes de guardarlo.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class Rut {

    private final String numero;

    /**
     * Crea un nuevo RUT.
     *
     * @param numero número de rut
     * @throws RutInvalidException cuando el formato es inválido
     */
    public Rut(String numero) throws RutInvalidException {

        if (numero == null) {
            throw new RutInvalidException("Rut no puede ser nulo");
        }

        if (!numero.matches("[0-9]+-[0-9kK]")) {
            throw new RutInvalidException("Formato de RUT inválido. Formato esperado: 12345678-9");
        }

        if (!validarRut(numero)) {
            throw new RutInvalidException("Rut inválido.");
        }

        this.numero = numero;
    }

    public static boolean validarRut(String rutCompleto) {
        if (rutCompleto == null || rutCompleto.isBlank()) {
            return false;
        }

        // Eliminar puntos y convertir a mayúsculas
        rutCompleto = rutCompleto.replace(".", "").toUpperCase();

        String[] partes = rutCompleto.split("-");

        if (partes.length != 2) {
            return false;
        }

        String numeroRut = partes[0];
        String dvIngresado = partes[1];

        String dvCalculado = calcularDV(numeroRut);

        return dvCalculado.equals(dvIngresado);
    }

    private static String calcularDV(String numeroRut) {
        int suma = 0;
        int multiplicador = 2;

        for (int i = numeroRut.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroRut.charAt(i));

            suma += digito * multiplicador;

            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int resultado = 11 - (suma % 11);

        if (resultado == 11) {
            return "0";
        }

        if (resultado == 10) {
            return "K";
        }

        return String.valueOf(resultado);
    }

    /**
     * Retorna el número de rut.
     *
     * @return número de rut
     */
    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }
}