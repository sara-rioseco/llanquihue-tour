package com.llanquihuetour.data;

import com.llanquihuetour.model.ServicioTuristico;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Gestor encargado de administrar los servicios turísticos que ofrece
 * Llanquihue Tour: mantiene la colección, permite agregar nuevos
 * servicios y ofrece operaciones de búsqueda y filtrado.
 *
 * Los servicios iniciales se cargan desde el archivo
 * {@code data/servicios.txt} mediante la clase utilitaria
 * {@code CargadorDatos} y se entregan al constructor.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public class GestorServicios {

    private final List<ServicioTuristico> servicios = new ArrayList<>();

    /**
     * Crea el gestor con la colección inicial de servicios.
     *
     * @param serviciosIniciales servicios cargados desde el archivo de datos
     */
    public GestorServicios(List<ServicioTuristico> serviciosIniciales) {
        if (serviciosIniciales != null) {
            servicios.addAll(serviciosIniciales);
        }
    }

    /**
     * Agrega un nuevo servicio turístico a la colección.
     *
     * @param servicio servicio a agregar
     * @throws IllegalArgumentException si el servicio es nulo
     */
    public void agregar(ServicioTuristico servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }
        servicios.add(servicio);
    }

    /**
     * Retorna la colección de servicios turísticos.
     *
     * @return lista con los servicios turísticos registrados
     */
    public List<ServicioTuristico> getServicios() {
        return servicios;
    }

    /**
     * Busca un servicio por su nombre exacto (sin distinguir mayúsculas).
     *
     * @param nombre nombre del servicio a buscar
     * @return el servicio encontrado, o {@code null} si no existe
     */
    public ServicioTuristico buscarPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (ServicioTuristico servicio : servicios) {
            if (servicio.getNombre().equalsIgnoreCase(nombre.strip())) {
                return servicio;
            }
        }
        return null;
    }

    /**
     * Filtra los servicios cuyo destino contiene el texto indicado.
     *
     * @param destino texto a buscar en el destino
     * @return lista de servicios que coinciden con el destino
     */
    public List<ServicioTuristico> filtrar(String destino) {
        List<ServicioTuristico> resultado = new ArrayList<>();
        if (destino == null) {
            return resultado;
        }
        String buscado = destino.strip().toLowerCase(Locale.ROOT);
        for (ServicioTuristico servicio : servicios) {
            if (servicio.getDestino().toLowerCase(Locale.ROOT).contains(buscado)) {
                resultado.add(servicio);
            }
        }
        return resultado;
    }

    /**
     * Filtra los servicios cuyo precio es menor o igual al máximo indicado.
     * Sobrecarga de {@link #filtrar(String)}.
     *
     * @param precioMaximo precio máximo en pesos chilenos
     * @return lista de servicios dentro del presupuesto
     */
    public List<ServicioTuristico> filtrar(int precioMaximo) {
        List<ServicioTuristico> resultado = new ArrayList<>();
        for (ServicioTuristico servicio : servicios) {
            if (servicio.getPrecio() <= precioMaximo) {
                resultado.add(servicio);
            }
        }
        return resultado;
    }

    /**
     * Recorre la lista de servicios turísticos e invoca
     * {@code mostrarInformacion()} polimórficamente sobre cada elemento.
     */
    public void mostrarServicios() {
        for (ServicioTuristico servicio : servicios) {
            servicio.mostrarInformacion();
        }
    }
}
