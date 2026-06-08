package com.llanquihuetour.app;

import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.Rut;

import static com.llanquihuetour.model.Color.*;

public class Main {

    public static void main(String[] args) {

        try {

            Direccion direccion1 = new Direccion(
                    "Av. Costanera 123",
                    "Llanquihue",
                    "Los Lagos"
            );

            Direccion direccion2 = new Direccion(
                    "Calle Comercio 456",
                    "Puerto Varas",
                    "Los Lagos"
            );

            Direccion direccion3 = new Direccion(
                    "Pasaje Los Aromos 789",
                    "Frutillar",
                    "Los Lagos"
            );

            Cliente cliente = new Cliente(
                    "Pedro",
                    "Gonzalez",
                    new Rut("12345678-9"),
                    direccion1,
                    "Cliente Frecuente"
            );

            Cliente cliente2 = new Cliente(
                    "Camila",
                    "Fernandez",
                    new Rut("11222333-4"),
                    direccion3,
                    "Cliente Nuevo"
            );

            Empleado empleado = new Empleado(
                    "Maria",
                    "Soto",
                    new Rut("98765432-K"),
                    direccion2,
                    "Guía Turístico"
            );

            System.out.println(BLUE.getColor()
                    + "===================================================="
                    + RESET.getColor());

            System.out.println(
                    "🌎 Bienvenido al Sistema de "
                            + CYAN.getColor()
                            + "Llanquihue Tour"
                            + RESET.getColor()
            );

            System.out.println(BLUE.getColor()
                    + "===================================================="
                    + RESET.getColor());

            System.out.println();

            System.out.println(YELLOW.getColor()
                    + "CLIENTE REGISTRADO"
                    + RESET.getColor());

            System.out.println(BLUE.getColor()
                    + "----------------------------------------------------"
                    + RESET.getColor());

            System.out.println(cliente);

            System.out.println(YELLOW.getColor()
                    + "CLIENTE REGISTRADO"
                    + RESET.getColor());

            System.out.println(BLUE.getColor()
                    + "----------------------------------------------------"
                    + RESET.getColor());

            System.out.println(cliente2);

            System.out.println(GREEN.getColor()
                    + "EMPLEADO REGISTRADO"
                    + RESET.getColor());

            System.out.println(BLUE.getColor()
                    + "----------------------------------------------------"
                    + RESET.getColor());

            System.out.println(empleado);

            System.out.println(BLUE.getColor()
                    + "===================================================="
                    + RESET.getColor());

            System.out.println(
                    "Total de registros procesados: "
                            + WHITE.getColor()
                            + "2"
                            + RESET.getColor()
            );

            System.out.println(BLUE.getColor()
                    + "===================================================="
                    + RESET.getColor());

        } catch (RutInvalidException e) {

            System.out.println(
                    RED.getColor()
                            + "ERROR: "
                            + e.getMessage()
                            + RESET.getColor()
            );
        }
    }
}