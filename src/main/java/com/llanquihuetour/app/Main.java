package com.llanquihuetour.app;

import com.llanquihuetour.data.GestorEntidades;
import com.llanquihuetour.data.GestorServicios;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.ui.VentanaPrincipal;
import com.llanquihuetour.util.CargadorDatos;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import static com.llanquihuetour.util.Color.*;

/**
 * Punto de entrada del sistema Llanquihue Tour.
 *
 * Carga los datos iniciales desde los archivos .txt de
 * {@code src/main/resources/data}, muestra por consola un resumen
 * polimórfico de servicios y entidades, y luego abre la interfaz
 * gráfica principal del sistema.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public class Main {

    private static final String SEPARADOR =
            "====================================================";

    public static void main(String[] args) {

        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());
        System.out.println("🌎 Bienvenido al Sistema de " + CYAN.getColor() + "Llanquihue Tour" + RESET.getColor());
        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());

        // === CARGA DE DATOS DESDE ARCHIVOS .TXT ===
        GestorServicios gestorServicios =
                new GestorServicios(CargadorDatos.cargarServicios("/data/servicios.txt"));

        GestorEntidades gestorEntidades = new GestorEntidades();
        gestorEntidades.agregar(CargadorDatos.cargarPersonas("/data/personas.txt"));
        gestorEntidades.agregar(CargadorDatos.cargarVehiculos("/data/vehiculos.txt"));

        // === SERVICIOS TURÍSTICOS (HERENCIA + POLIMORFISMO) ===
        System.out.println(PURPLE.getColor() + "🧬 SERVICIOS TURÍSTICOS DISPONIBLES" + RESET.getColor());
        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());
        System.out.println();

        gestorServicios.mostrarServicios();

        System.out.println(GREEN.getColor() + "Total de servicios turísticos: "
                + gestorServicios.getServicios().size() + RESET.getColor());
        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());
        System.out.println();

        // === ENTIDADES DE LA AGENCIA (INTERFAZ Registrable + instanceof) ===
        System.out.println(PURPLE.getColor() + "🪪 ENTIDADES REGISTRADAS EN LA AGENCIA" + RESET.getColor());
        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());

        gestorEntidades.mostrarResumenes();

        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());
        System.out.println();

        // === BÚSQUEDAS Y FILTROS SOBRE LAS COLECCIONES ===
        System.out.println(PURPLE.getColor() + "🔎 BÚSQUEDAS Y FILTROS" + RESET.getColor());
        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());

        Persona encontrada = gestorEntidades.buscarPersona("12345678-5");
        System.out.println("Búsqueda por RUT 12345678-5 (HashMap): "
                + (encontrada != null ? encontrada.mostrarResumen() : "no encontrada"));

        System.out.println("\nServicios con destino Frutillar [filtrar(String)]:");
        for (ServicioTuristico servicio : gestorServicios.filtrar("Frutillar")) {
            System.out.println("  - " + servicio.mostrarResumen());
        }

        System.out.println("\nServicios de hasta $30.000 CLP [filtrar(int), sobrecarga]:");
        for (ServicioTuristico servicio : gestorServicios.filtrar(30000)) {
            System.out.println("  - " + servicio.mostrarResumen());
        }

        System.out.println(BLUE.getColor() + SEPARADOR + RESET.getColor());
        System.out.println();

        // === INTERFAZ GRÁFICA PRINCIPAL ===
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si el look and feel del sistema no está disponible se usa el estándar.
            }
            new VentanaPrincipal(gestorEntidades, gestorServicios).setVisible(true);
        });
    }
}
