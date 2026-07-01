package com.llanquihuetour.data;

import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.PaseoLacustre;
import com.llanquihuetour.model.RutaGastronomica;
import com.llanquihuetour.model.ServicioTuristico;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor encargado de crear instancias de prueba de los
 * distintos servicios turísticos que ofrece Llanquihue Tour.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GestorServicios {

    /**
     * Recorre la lista de servicios turísticos e invoca
     * {@code mostrarInformacion()} polimórficamente sobre cada elemento.
     */
    public void mostrarServicios() {
        List<ServicioTuristico> servicios = crearServicios();
        for (ServicioTuristico servicio : servicios) {
            servicio.mostrarInformacion();
        }
    }

    /**
     * Crea al menos dos objetos de cada subclase de
     * {@link ServicioTuristico} y los devuelve en una lista.
     *
     * @return lista con los servicios turísticos de prueba
     */
    public List<ServicioTuristico> crearServicios() {
        List<ServicioTuristico> servicios = new ArrayList<>();

        // Rutas gastronómicas
        servicios.add(new RutaGastronomica("Sabores del Lago", "Frutillar", 28000, 4, 5));
        servicios.add(new RutaGastronomica("Cervecería y Cocina Alemana", "Puerto Varas", 22000, 3, 3));

        // Paseos lacustres
        servicios.add(new PaseoLacustre("Navegación Lago Llanquihue", "Puerto Varas", 35000, 2, "Catamarán"));
        servicios.add(new PaseoLacustre("Atardecer en el Lago", "Frutillar", 30000, 3, "Velero"));

        // Excursiones culturales
        servicios.add(new ExcursionCultural("Patrimonio de Frutillar", "Frutillar", 40000, 5, "Teatro del Lago"));
        servicios.add(new ExcursionCultural("Colonización Alemana", "Puerto Varas", 38000, 4, "Museo Colonial Alemán"));

        return servicios;
    }
}
