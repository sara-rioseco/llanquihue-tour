package com.llanquihuetour.ui;

import com.llanquihuetour.data.GestorServicios;
import com.llanquihuetour.model.ServicioTuristico;

import java.util.List;

import static com.llanquihuetour.util.Color.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println("🌎 Bienvenido al Sistema de " + CYAN.getColor() + "Llanquihue Tour" + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

        // === JERARQUÍA DE SERVICIOS TURÍSTICOS (HERENCIA) ===
        System.out.println(PURPLE.getColor() + "🧬 SERVICIOS TURÍSTICOS DISPONIBLES" + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
        System.out.println();

        // Crear gestor de servicios y generar objetos de prueba
        GestorServicios gestorServicios = new GestorServicios();
        List<ServicioTuristico> servicios = gestorServicios.crearServicios();

        // Mostrar cada servicio usando mostrarInformacion() (polimorfismo)
        for (ServicioTuristico servicio : servicios) {
            servicio.mostrarInformacion();
        }

        System.out.println(GREEN.getColor() + "Total de servicios turísticos: " + servicios.size() + RESET.getColor());
        System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
    }
}
