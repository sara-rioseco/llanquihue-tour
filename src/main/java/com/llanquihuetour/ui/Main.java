package com.llanquihuetour.ui;

import com.llanquihuetour.data.GestorDatos;
import com.llanquihuetour.model.Tour;

import java.io.IOException;
import java.util.ArrayList;

import static com.llanquihuetour.model.Color.*;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
            System.out.println("🌎 Bienvenido al Sistema de " + CYAN.getColor() + "Llanquihue Tour" + RESET.getColor());
            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

            // === GESTIÓN DE TOURS ===
            System.out.println(PURPLE.getColor() + "🗺️ CATÁLOGO DE TOURS DISPONIBLES" + RESET.getColor());
            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());
            System.out.println();

            // Crear gestor y cargar datos
            GestorDatos gestor = new GestorDatos();
            ArrayList<Tour> tours = gestor.cargarTours();

            // 1. Mostrar todos los elementos de la colección
            System.out.println(CYAN.getColor() + "📋 LISTA COMPLETA DE TOURS:" + RESET.getColor());
            System.out.println(BLUE.getColor() + "----------------------------------------------------" + RESET.getColor());

            for (Tour tour : tours) {
                System.out.println(tour);
            }

            System.out.println(GREEN.getColor() + "Total de tours disponibles: " + tours.size() + RESET.getColor());
            System.out.println();

            // 2. Filtrar según condición (precio > 40000)
            int precioMinimo = 40000;
            ArrayList<Tour> toursFiltrados = new ArrayList<>();

            for (Tour tour : tours) {
                if (tour.getPrecio() > precioMinimo) {
                    toursFiltrados.add(tour);
                }
            }

            // 3. Imprimir resultados filtrados
            System.out.println(YELLOW.getColor() + "🔍 TOURS PREMIUM (precio mayor a $" + String.format("%,d", precioMinimo) + " CLP):" + RESET.getColor());
            System.out.println(BLUE.getColor() + "----------------------------------------------------" + RESET.getColor());

            if (toursFiltrados.isEmpty()) {
                System.out.println(RED.getColor() + "No se encontraron tours que cumplan el criterio." + RESET.getColor());
            } else {
                for (Tour tour : toursFiltrados) {
                    System.out.println(tour);
                }
                System.out.println(GREEN.getColor() + "Total de tours premium: " + toursFiltrados.size() + RESET.getColor());
            }

            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

        } catch (IOException e) {
            System.out.println(RED.getColor() + "ERROR al cargar datos: " + e.getMessage() + RESET.getColor());
        }
    }
}