package com.llanquihuetour.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.Registrable;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;

/**
 * Gestor encargado de almacenar, buscar y recorrer las distintas
 * entidades de la agencia Llanquihue Tour (personas, vehículos,
 * servicios, etc.) a través del contrato común {@link Registrable}.
 *
 * Mantiene dos colecciones:
 * una lista polimórfica con todas las entidades y un {@link HashMap}
 * que indexa las personas por RUT para búsquedas directas y para
 * impedir registros duplicados.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public class GestorEntidades {

    private final List<Registrable> entidades = new ArrayList<>();
    private final Map<String, Persona> personasPorRut = new HashMap<>();

    /**
     * Agrega una entidad a la colección. Si la entidad es una
     * {@link Persona}, además se indexa por RUT y se rechaza si ya
     * existe otra persona registrada con el mismo RUT.
     *
     * @param entidad entidad a registrar
     * @throws IllegalArgumentException si la entidad es nula o el RUT está duplicado
     */
    public void agregar(Registrable entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula.");
        }

        if (entidad instanceof Persona persona) {
            String rut = persona.getRut().getNumero();
            if (personasPorRut.containsKey(rut)) {
                throw new IllegalArgumentException(
                        "Ya existe una persona registrada con el RUT " + rut + ".");
            }
            personasPorRut.put(rut, persona);
        }

        entidades.add(entidad);
    }

    /**
     * Agrega varias entidades de una vez.
     * Sobrecarga de {@link #agregar(Registrable)}.
     *
     * @param nuevas entidades a registrar
     */
    public void agregar(List<? extends Registrable> nuevas) {
        for (Registrable entidad : nuevas) {
            agregar(entidad);
        }
    }

    /**
     * Busca una persona por su RUT en formato texto (ej: 12345678-5).
     *
     * @param rut número de RUT a buscar
     * @return la persona registrada con ese RUT, o {@code null} si no existe
     */
    public Persona buscarPersona(String rut) {
        if (rut == null) {
            return null;
        }
        return personasPorRut.get(rut.strip().toUpperCase(Locale.ROOT));
    }

    /**
     * Busca una persona por su objeto {@link Rut}.
     * Sobrecarga de {@link #buscarPersona(String)}.
     *
     * @param rut RUT a buscar
     * @return la persona registrada con ese RUT, o {@code null} si no existe
     */
    public Persona buscarPersona(Rut rut) {
        return rut == null ? null : buscarPersona(rut.getNumero());
    }

    /**
     * Retorna la colección completa de entidades registradas.
     *
     * @return lista polimórfica de entidades
     */
    public List<Registrable> getEntidades() {
        return entidades;
    }

    /**
     * Filtra y retorna solo las personas registradas
     * (clientes, empleados, guías y colaboradores).
     *
     * @return lista de personas
     */
    public List<Persona> getPersonas() {
        List<Persona> personas = new ArrayList<>();
        for (Registrable entidad : entidades) {
            if (entidad instanceof Persona persona) {
                personas.add(persona);
            }
        }
        return personas;
    }

    /**
     * Filtra y retorna solo los vehículos registrados.
     *
     * @return lista de vehículos
     */
    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Registrable entidad : entidades) {
            if (entidad instanceof Vehiculo vehiculo) {
                vehiculos.add(vehiculo);
            }
        }
        return vehiculos;
    }

    /**
     * Recorre la colección de entidades e imprime el resumen de cada una,
     * utilizando {@code instanceof} (switch con pattern matching) para
     * agregar un detalle adicional según el tipo específico de la entidad.
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
