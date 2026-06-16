package com.llanquihuetour.ui;

import com.llanquihuetour.data.GestorDatos;
import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.Tour;

import java.io.IOException;
import java.util.ArrayList;

import static com.llanquihuetour.model.Color.*;

public class Main {

    public static void main(String[] args) {

        try {

            Direccion direccion1 = new Direccion("Av. Costanera 123", "Llanquihue", "Los Lagos");

            Direccion direccion2 = new Direccion("Calle Comercio 456", "Puerto Varas", "Los Lagos");

            Direccion direccion3 = new Direccion("Pasaje Los Aromos 789", "Frutillar", "Los Lagos");

            Cliente cliente = new Cliente("Pedro", "Gonzalez", new Rut("21804672-k"), direccion1, "Cliente Frecuente");

            Cliente cliente2 = new Cliente("Camila", "Fernandez", new Rut("7254029-8"), direccion3, "Cliente Nuevo");

            Empleado empleado = new Empleado("Maria", "Soto", new Rut("11027636-2"), direccion2, "Guía Turístico");

            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

            System.out.println("🌎 Bienvenido al Sistema de " + CYAN.getColor() + "Llanquihue Tour" + RESET.getColor());

            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

            System.out.println();

            System.out.println(YELLOW.getColor() + "CLIENTE REGISTRADO" + RESET.getColor());

            System.out.println(BLUE.getColor() + "----------------------------------------------------" + RESET.getColor());

            System.out.println(cliente);

            System.out.println(YELLOW.getColor() + "CLIENTE REGISTRADO" + RESET.getColor());

            System.out.println(BLUE.getColor() + "----------------------------------------------------" + RESET.getColor());

            System.out.println(cliente2);

            System.out.println(GREEN.getColor() + "EMPLEADO REGISTRADO" + RESET.getColor());

            System.out.println(BLUE.getColor() + "----------------------------------------------------" + RESET.getColor());

            System.out.println(empleado);

            System.out.println(BLUE.getColor() + "====================================================" + RESET.getColor());

            // === GESTIÓN DE TOURS ===

            System.out.println();
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

        } catch (RutInvalidException e) {
            System.out.println(RED.getColor() + "ERROR: " + e.getMessage() + RESET.getColor());
        } catch (IOException e) {
            System.out.println(RED.getColor() + "ERROR al cargar datos: " + e.getMessage() + RESET.getColor());
        }
    }
}