package com.llanquihuetour.data;

import java.util.ArrayList;
import java.util.List;

import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.Registrable;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;

/**
 * Gestor encargado de almacenar y recorrer las distintas
 * entidades de la agencia Llanquihue Tour (guías turísticos,
 * vehículos, colaboradores externos, etc.) a través del
 * contrato común {@link Registrable}.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GestorEntidades {

    private final List<Registrable> entidades = new ArrayList<>();

    /**
     * Agrega una entidad a la colección.
     *
     * @param entidad entidad a registrar
     */
    public void agregar(Registrable entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula.");
        }
        entidades.add(entidad);
    }

    /**
     * Retorna la colección de entidades registradas.
     *
     * @return lista de entidades
     */
    public List<Registrable> getEntidades() {
        return entidades;
    }

    /**
     * Carga entidades de ejemplo para demostrar el funcionamiento del sistema:
     * un guía turístico, un vehículo, un colaborador externo, un cliente,
     * un empleado y un servicio turístico, todos tratados de forma polimórfica
     * a través de {@link Registrable}.
     *
     * @throws RutInvalidException si alguno de los RUT de ejemplo es inválido
     */
    public void cargarEntidadesDeEjemplo() throws RutInvalidException {
        agregar(new GuiaTuristico(
                "Matías", "Soto", new Rut("11111111-1"),
                new Direccion("Av. Vicente Pérez Rosales 1234", "Puerto Varas", "Los Lagos"),
                "Alemán", 6));

        agregar(new Vehiculo("PVCT-24", "Mercedes-Benz", "Sprinter", 18));

        agregar(new ColaboradorExterno(
                "Carla", "Muñoz", new Rut("22222222-2"),
                new Direccion("Los Colonos 456", "Frutillar", "Los Lagos"),
                "Transportes Lago Azul", "Traslados turísticos"));

        agregar(new Cliente(
                "Josefa", "Vargas", new Rut("12345678-5"),
                new Direccion("San Francisco 789", "Puerto Montt", "Los Lagos"),
                "Frecuente"));

        agregar(new Empleado(
                "Diego", "Fuentes", new Rut("15555555-6"),
                new Direccion("Del Salvador 321", "Puerto Varas", "Los Lagos"),
                "Coordinador de Turismo"));

        agregar(new ExcursionCultural("Patrimonio de Frutillar", "Frutillar", 40000, 5, "Teatro del Lago"));
    }

    /**
     * Recorre la colección de entidades e imprime el resumen de cada una,
     * utilizando {@code instanceof} para agregar un detalle adicional
     * según el tipo específico de la entidad.
     */
    public void mostrarResumenes() {
        for (Registrable entidad : entidades) {
            System.out.println(entidad.mostrarResumen());

            switch (entidad) {
                case Vehiculo vehiculo -> System.out.printf("   -> Vehículo con capacidad para %d pasajeros.%n", vehiculo.getCapacidad());
                case GuiaTuristico guia -> System.out.printf("   -> Guía turístico especializado en idioma %s.%n", guia.getIdioma());
                case ColaboradorExterno colaborador -> System.out.printf("   -> Colaborador externo de la empresa %s.%n", colaborador.getEmpresa());
                case Persona persona -> System.out.printf("   -> Persona registrada: %s.%n", persona.getNombre());
                case ServicioTuristico servicio -> System.out.printf("   -> Servicio turístico con destino %s.%n", servicio.getDestino());
                default -> {
                }
            }
        }
    }
}
