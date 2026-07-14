package com.llanquihuetour.data;

import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.PaseoLacustre;
import com.llanquihuetour.model.RutaGastronomica;
import com.llanquihuetour.model.ServicioTuristico;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor encargado de crear y mostrar los distintos servicios
 * turísticos que ofrece Llanquihue Tour. La lista de servicios se
 * construye una sola vez al instanciar el gestor y queda disponible
 * a través de {@link #getServicios()}.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GestorServicios {

    private final List<ServicioTuristico> servicios = new ArrayList<>();

    /**
     * Crea el gestor y carga los servicios turísticos de prueba.
     */
    public GestorServicios() {
        crearServicios();
    }

    /**
     * Crea al menos dos objetos de cada subclase de
     * {@link ServicioTuristico} y los agrega a la colección.
     */
    private void crearServicios() {
        // Rutas gastronómicas
        servicios.add(new RutaGastronomica("Sabores del Lago", "Frutillar", 28000, 4, 5));
        servicios.add(new RutaGastronomica("Cervecería y Cocina Alemana", "Puerto Varas", 22000, 3, 3));

        // Paseos lacustres
        servicios.add(new PaseoLacustre("Navegación Lago Llanquihue", "Puerto Varas", 35000, 2, "Catamarán"));
        servicios.add(new PaseoLacustre("Atardecer en el Lago", "Frutillar", 30000, 3, "Velero"));

        // Excursiones culturales
        servicios.add(new ExcursionCultural("Patrimonio de Frutillar", "Frutillar", 40000, 5, "Teatro del Lago"));
        servicios.add(new ExcursionCultural("Colonización Alemana", "Puerto Varas", 38000, 4, "Museo Colonial Alemán"));
    }

    /**
     * Retorna la colección de servicios turísticos.
     *
     * @return lista con los servicios turísticos creados
     */
    public List<ServicioTuristico> getServicios() {
        return servicios;
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
