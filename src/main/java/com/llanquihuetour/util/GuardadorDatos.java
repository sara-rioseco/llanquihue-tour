package com.llanquihuetour.util;

import com.llanquihuetour.data.GestorEntidades;
import com.llanquihuetour.data.GestorServicios;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.PaseoLacustre;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.RutaGastronomica;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria que persiste las entidades del sistema en los
 * archivos .txt de {@code src/main/resources/data}, usando el mismo
 * formato (separador {@code ;} y comentarios con {@code #}) que lee
 * {@link CargadorDatos}.
 *
 * Cada guardado reescribe los archivos completos a partir de las
 * colecciones en memoria, de modo que su contenido siempre refleja
 * el estado actual del sistema.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public final class GuardadorDatos {

    private static final String SEPARADOR = ";";
    private static final Path CARPETA_DATOS = Path.of("src", "main", "resources", "data");

    private GuardadorDatos() {
        // Clase utilitaria: no se instancia.
    }

    /**
     * Guarda todas las entidades del sistema (personas, vehículos y
     * servicios turísticos) en sus respectivos archivos .txt.
     *
     * @param gestorEntidades gestor de personas y vehículos
     * @param gestorServicios gestor de servicios turísticos
     * @return {@code true} si los tres archivos se guardaron correctamente
     */
    public static boolean guardarTodo(GestorEntidades gestorEntidades, GestorServicios gestorServicios) {
        // Se usa & (y no &&) para intentar guardar los tres archivos
        // aunque alguno falle.
        return guardarPersonas(gestorEntidades.getPersonas())
                & guardarVehiculos(gestorEntidades.getVehiculos())
                & guardarServicios(gestorServicios.getServicios());
    }

    private static boolean guardarPersonas(List<Persona> personas) {
        List<String> lineas = new ArrayList<>(List.of(
                "# Personas registradas en Llanquihue Tour",
                "# Formato: TIPO;nombre;apellido;rut;calle;ciudad;region;<campos específicos>",
                "#   CLIENTE      -> ...;tipoCliente",
                "#   EMPLEADO     -> ...;cargo",
                "#   GUIA         -> ...;idioma;aniosExperiencia",
                "#   COLABORADOR  -> ...;empresa;tipoServicio"));

        for (Persona persona : personas) {
            lineas.add(formatearPersona(persona));
        }
        return escribir("personas.txt", lineas);
    }

    private static boolean guardarVehiculos(List<Vehiculo> vehiculos) {
        List<String> lineas = new ArrayList<>(List.of(
                "# Vehículos de la agencia Llanquihue Tour",
                "# Formato: patente;marca;modelo;capacidad"));

        for (Vehiculo vehiculo : vehiculos) {
            lineas.add(String.join(SEPARADOR,
                    vehiculo.getPatente(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    String.valueOf(vehiculo.getCapacidad())));
        }
        return escribir("vehiculos.txt", lineas);
    }

    private static boolean guardarServicios(List<ServicioTuristico> servicios) {
        List<String> lineas = new ArrayList<>(List.of(
                "# Servicios turísticos ofrecidos por Llanquihue Tour",
                "# Formato: TIPO;nombre;destino;precio;duracionHoras;<campo específico>",
                "#   RUTA      -> ...;numeroDeParadas",
                "#   PASEO     -> ...;tipoEmbarcacion",
                "#   EXCURSION -> ...;lugarHistorico"));

        for (ServicioTuristico servicio : servicios) {
            lineas.add(formatearServicio(servicio));
        }
        return escribir("servicios.txt", lineas);
    }

    /**
     * Convierte una persona en su línea de personas.txt, usando el
     * prefijo de tipo que espera {@link CargadorDatos}.
     */
    private static String formatearPersona(Persona persona) {
        String base = String.join(SEPARADOR,
                persona.getNombre(),
                persona.getApellido(),
                persona.getRut().getNumero(),
                persona.getDireccion().getCalle(),
                persona.getDireccion().getCiudad(),
                persona.getDireccion().getRegion());

        return switch (persona) {
            case Cliente cliente -> String.join(SEPARADOR, "CLIENTE", base, cliente.getTipoCliente());
            case Empleado empleado -> String.join(SEPARADOR, "EMPLEADO", base, empleado.getCargo());
            case GuiaTuristico guia -> String.join(SEPARADOR, "GUIA", base,
                    guia.getIdioma(), String.valueOf(guia.getAniosExperiencia()));
            case ColaboradorExterno colaborador -> String.join(SEPARADOR, "COLABORADOR", base,
                    colaborador.getEmpresa(), colaborador.getTipoServicio());
            default -> throw new IllegalArgumentException(
                    "Tipo de persona desconocido: " + persona.getClass().getSimpleName());
        };
    }

    /**
     * Convierte un servicio en su línea de servicios.txt, usando el
     * prefijo de tipo que espera {@link CargadorDatos}.
     */
    private static String formatearServicio(ServicioTuristico servicio) {
        String base = String.join(SEPARADOR,
                servicio.getNombre(),
                servicio.getDestino(),
                String.valueOf(servicio.getPrecio()),
                String.valueOf(servicio.getDuracionHoras()));

        return switch (servicio) {
            case RutaGastronomica ruta -> String.join(SEPARADOR, "RUTA", base,
                    String.valueOf(ruta.getNumeroDeParadas()));
            case PaseoLacustre paseo -> String.join(SEPARADOR, "PASEO", base, paseo.getTipoEmbarcacion());
            case ExcursionCultural excursion -> String.join(SEPARADOR, "EXCURSION", base,
                    excursion.getLugarHistorico());
            default -> throw new IllegalArgumentException(
                    "Tipo de servicio desconocido: " + servicio.getClass().getSimpleName());
        };
    }

    private static boolean escribir(String nombreArchivo, List<String> lineas) {
        try {
            Files.createDirectories(CARPETA_DATOS);
            Files.write(CARPETA_DATOS.resolve(nombreArchivo), lineas, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            System.err.println("⚠ No se pudo guardar " + nombreArchivo + ": " + e.getMessage());
            return false;
        }
    }
}
