package com.llanquihuetour.ui;

import com.llanquihuetour.data.GestorEntidades;
import com.llanquihuetour.data.GestorServicios;
import com.llanquihuetour.exception.RutInvalidException;

import javax.swing.SwingUtilities;

import static com.llanquihuetour.util.Color.*;

public class Main {

    public static void main(String[] args) throws RutInvalidException {

        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println("🌎 Bienvenido al Sistema de " + CYAN.getColor() + "Llanquihue Tour" + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

        // === JERARQUÍA DE SERVICIOS TURÍSTICOS (HERENCIA) ===
        System.out.println(PURPLE.getColor() + "🧬 SERVICIOS TURÍSTICOS DISPONIBLES" + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println();

        // Crear gestor de servicios (carga los objetos de prueba) y
        // mostrar cada servicio usando mostrarInformacion() (polimorfismo)
        GestorServicios gestorServicios = new GestorServicios();
        gestorServicios.mostrarServicios();

        System.out.println(GREEN.getColor() + "Total de servicios turísticos: " + gestorServicios.getServicios().size() + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println();

        // === ENTIDADES DE LA AGENCIA (INTERFAZ Registrable + instanceof) ===
        System.out.println(PURPLE.getColor() + "🪪 ENTIDADES REGISTRADAS EN LA AGENCIA" + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

        GestorEntidades gestorEntidades = new GestorEntidades();
        gestorEntidades.cargarEntidadesDeEjemplo();
        gestorEntidades.mostrarResumenes();

        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println();

        // === INTERFAZ GRÁFICA PARA INGRESAR Y VISUALIZAR ENTIDADES ===
        SwingUtilities.invokeLater(() -> {
            GestorEntidadesFrame frame = new GestorEntidadesFrame(gestorEntidades);
            frame.setVisible(true);
        });
    }
}
