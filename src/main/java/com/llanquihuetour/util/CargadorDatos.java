package com.llanquihuetour.util;

import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.PaseoLacustre;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.RutaGastronomica;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria que lee los archivos .txt de datos (ubicados en
 * {@code src/main/resources/data}) y los convierte en objetos del modelo.
 *
 * Cada línea de los archivos usa el separador {@code ;}. Las líneas en
 * blanco o que comienzan con {@code #} se ignoran, y las líneas con
 * errores de formato se reportan por consola sin detener la carga.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public final class CargadorDatos {

    private static final String SEPARADOR = ";";

    private CargadorDatos() {
        // Clase utilitaria: no se instancia.
    }

    /**
     * Carga las personas (clientes, empleados, guías y colaboradores)
     * desde el archivo indicado.
     *
     * @param rutaArchivo ruta del recurso, por ejemplo {@code /data/personas.txt}
     * @return lista de personas leídas correctamente
     */
    public static List<Persona> cargarPersonas(String rutaArchivo) {
        List<Persona> personas = new ArrayList<>();

        for (String[] campos : leerLineas(rutaArchivo)) {
            try {
                personas.add(crearPersona(campos));
            } catch (RutInvalidException | IllegalArgumentException
                     | ArrayIndexOutOfBoundsException e) {
                reportarLineaInvalida(rutaArchivo, campos, e);
            }
        }
        return personas;
    }

    /**
     * Carga los vehículos de la agencia desde el archivo indicado.
     *
     * @param rutaArchivo ruta del recurso, por ejemplo {@code /data/vehiculos.txt}
     * @return lista de vehículos leídos correctamente
     */
    public static List<Vehiculo> cargarVehiculos(String rutaArchivo) {
        List<Vehiculo> vehiculos = new ArrayList<>();

        for (String[] campos : leerLineas(rutaArchivo)) {
            try {
                vehiculos.add(new Vehiculo(
                        campos[0], campos[1], campos[2], Integer.parseInt(campos[3])));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                reportarLineaInvalida(rutaArchivo, campos, e);
            }
        }
        return vehiculos;
    }

    /**
     * Carga los servicios turísticos desde el archivo indicado.
     *
     * @param rutaArchivo ruta del recurso, por ejemplo {@code /data/servicios.txt}
     * @return lista de servicios leídos correctamente
     */
    public static List<ServicioTuristico> cargarServicios(String rutaArchivo) {
        List<ServicioTuristico> servicios = new ArrayList<>();

        for (String[] campos : leerLineas(rutaArchivo)) {
            try {
                servicios.add(crearServicio(campos));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                reportarLineaInvalida(rutaArchivo, campos, e);
            }
        }
        return servicios;
    }

    /**
     * Convierte una línea de personas.txt en la subclase de {@link Persona}
     * indicada por el primer campo (CLIENTE, EMPLEADO, GUIA o COLABORADOR).
     */
    private static Persona crearPersona(String[] c) throws RutInvalidException {
        String tipo = c[0].toUpperCase();
        Rut rut = new Rut(c[3]);
        Direccion direccion = new Direccion(c[4], c[5], c[6]);

        return switch (tipo) {
            case "CLIENTE" -> new Cliente(c[1], c[2], rut, direccion, c[7]);
            case "EMPLEADO" -> new Empleado(c[1], c[2], rut, direccion, c[7]);
            case "GUIA" -> new GuiaTuristico(c[1], c[2], rut, direccion, c[7], Integer.parseInt(c[8]));
            case "COLABORADOR" -> new ColaboradorExterno(c[1], c[2], rut, direccion, c[7], c[8]);
            default -> throw new IllegalArgumentException("Tipo de persona desconocido: " + tipo);
        };
    }

    /**
     * Convierte una línea de servicios.txt en la subclase de
     * {@link ServicioTuristico} indicada por el primer campo
     * (RUTA, PASEO o EXCURSION).
     */
    private static ServicioTuristico crearServicio(String[] c) {
        String tipo = c[0].toUpperCase();
        String nombre = c[1];
        String destino = c[2];
        int precio = Integer.parseInt(c[3]);
        int duracion = Integer.parseInt(c[4]);

        return switch (tipo) {
            case "RUTA" -> new RutaGastronomica(nombre, destino, precio, duracion, Integer.parseInt(c[5]));
            case "PASEO" -> new PaseoLacustre(nombre, destino, precio, duracion, c[5]);
            case "EXCURSION" -> new ExcursionCultural(nombre, destino, precio, duracion, c[5]);
            default -> throw new IllegalArgumentException("Tipo de servicio desconocido: " + tipo);
        };
    }

    /**
     * Lee el recurso indicado y retorna las líneas de datos ya separadas
     * en campos, descartando comentarios y líneas en blanco.
     */
    private static List<String[]> leerLineas(String rutaArchivo) {
        List<String[]> lineas = new ArrayList<>();
        InputStream entrada = abrirArchivo(rutaArchivo);

        if (entrada == null) {
            System.err.println("⚠ No se encontró el archivo de datos: " + rutaArchivo);
            return lineas;
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(entrada, StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.strip();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                lineas.add(linea.split(SEPARADOR));
            }
        } catch (IOException e) {
            System.err.println("⚠ Error al leer " + rutaArchivo + ": " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Abre el archivo de datos indicado. Prefiere la copia editable de
     * {@code src/main/resources} (donde {@link GuardadorDatos} persiste
     * los cambios) y, si no existe, recurre al recurso del classpath.
     */
    private static InputStream abrirArchivo(String rutaArchivo) {
        Path archivoLocal = Path.of("src/main/resources" + rutaArchivo);
        if (Files.exists(archivoLocal)) {
            try {
                return Files.newInputStream(archivoLocal);
            } catch (IOException e) {
                System.err.println("⚠ Error al abrir " + archivoLocal + ": " + e.getMessage());
            }
        }
        return CargadorDatos.class.getResourceAsStream(rutaArchivo);
    }

    private static void reportarLineaInvalida(String archivo, String[] campos, Exception e) {
        System.err.printf("⚠ Línea inválida en %s (%s): %s%n",
                archivo, String.join(SEPARADOR, campos), e.getMessage());
    }
}
