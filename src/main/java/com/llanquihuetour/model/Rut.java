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

        if (!numero.matches("[0-9]+-[0-9kK]")) {
            throw new RutInvalidException(
                    "Formato RUT inválido. Expected XXXXXXXX-X"
            );
        }

        this.numero = numero;
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