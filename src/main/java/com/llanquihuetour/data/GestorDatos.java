package com.llanquihuetour.data;

import com.llanquihuetour.model.Tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Gestor encargado de leer y procesar datos de tours
 * desde archivos de texto.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GestorDatos {

    private static final String ARCHIVO_DATOS = "tours.txt";
    private static final String SEPARADOR = ";";

    /**
     * Lee el archivo de datos y crea una lista de objetos Tour.
     *
     * @return ArrayList con los tours cargados
     * @throws IOException si ocurre un error al leer el archivo
     */
    public ArrayList<Tour> cargarTours() throws IOException {
        ArrayList<Tour> tours = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_DATOS);

        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el archivo: " + ARCHIVO_DATOS);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String linea;
            int numeroLinea = 0;

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;

                // Saltar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                try {
                    Tour tour = procesarLinea(linea);
                    tours.add(tour);
                } catch (Exception e) {
                    System.err.println("Error en línea " + numeroLinea + ": " + e.getMessage());
                }
            }
        }

        return tours;
    }

    /**
     * Procesa una línea del archivo y crea un objeto Tour.
     *
     * @param linea línea de texto a procesar
     * @return objeto Tour creado
     * @throws IllegalArgumentException si el formato de la línea es inválido
     */
    private Tour procesarLinea(String linea) {
        String[] datos = linea.split(SEPARADOR);

        if (datos.length != 3) {
            throw new IllegalArgumentException(
                    "Formato inválido. Se esperaban 3 campos separados por ';'"
            );
        }

        String nombre = datos[0].trim();
        String destino = datos[1].trim();
        int precio;

        try {
            precio = Integer.parseInt(datos[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El precio debe ser un número válido: " + datos[2]
            );
        }

        return new Tour(nombre, destino, precio);
    }
}