package com.llanquihuetour.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.llanquihuetour.data.GestorEntidades;
import com.llanquihuetour.data.GestorServicios;
import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.Cliente;
import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.Empleado;
import com.llanquihuetour.model.ExcursionCultural;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.PaseoLacustre;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.Registrable;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.RutaGastronomica;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;
import com.llanquihuetour.util.GuardadorDatos;

/**
 * Ventana principal del sistema Llanquihue Tour.
 *
 * Muestra en una única tabla todas las entidades registradas
 * (personas, vehículos y servicios turísticos), tratadas de forma
 * polimórfica a través de {@link Registrable}. Desde la botonera
 * superior se pueden ingresar nuevas entidades mediante formularios,
 * y el campo de búsqueda filtra la tabla en vivo.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public class VentanaPrincipal extends JFrame {

    private static final String CAMPO_RUT = "RUT (12345678-9)";

    private final GestorEntidades gestorEntidades;
    private final GestorServicios gestorServicios;

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Categoría", "Nombre", "Identificación", "Detalle"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    private final JLabel barraEstado = new JLabel();

    /**
     * Crea la ventana principal del sistema.
     *
     * @param gestorEntidades gestor de personas y vehículos
     * @param gestorServicios gestor de servicios turísticos
     */
    public VentanaPrincipal(GestorEntidades gestorEntidades, GestorServicios gestorServicios) {
        super("Llanquihue Tour — Sistema de Gestión");
        this.gestorEntidades = gestorEntidades;
        this.gestorServicios = gestorServicios;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 560);
        setLocationRelativeTo(null);

        JTable tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(24);
        TableRowSorter<DefaultTableModel> ordenador = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(ordenador);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(crearPanelBotones(), BorderLayout.NORTH);
        panelSuperior.add(crearPanelBusqueda(ordenador), BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        barraEstado.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        add(barraEstado, BorderLayout.SOUTH);

        refrescarTabla();
    }

    // ==================== CONSTRUCCIÓN DE LA INTERFAZ ====================

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 2, 10));

        panel.add(crearBoton("Agregar Cliente", this::agregarCliente));
        panel.add(crearBoton("Agregar Empleado", this::agregarEmpleado));
        panel.add(crearBoton("Agregar Guía Turístico", this::agregarGuia));
        panel.add(crearBoton("Agregar Colaborador Externo", this::agregarColaborador));
        panel.add(crearBoton("Agregar Vehículo", this::agregarVehiculo));
        panel.add(crearBoton("Agregar Servicio Turístico", this::agregarServicio));
        panel.add(crearBoton("Acerca de", this::mostrarAcercaDe));
        panel.add(crearBoton("Salir", this::dispose));

        return panel;
    }

    private JButton crearBoton(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
                """
                Llanquihue Tour — Sistema de Gestión
                Prototipo aplicación desarrollado en Java 21.

                Los datos iniciales se cargan desde archivos .txt
                ubicados en src/main/resources/data.

                Autora: Sara Rioseco, 2026.
                """,
                "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel crearPanelBusqueda(TableRowSorter<DefaultTableModel> ordenador) {
        JTextField campoBusqueda = new JTextField();
        campoBusqueda.setToolTipText("Filtra por cualquier columna de la tabla");
        campoBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = campoBusqueda.getText().strip();
                ordenador.setRowFilter(texto.isEmpty()
                        ? null
                        : RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
            }
        });

        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        panel.add(new JLabel("🔍 Buscar:"), BorderLayout.WEST);
        panel.add(campoBusqueda, BorderLayout.CENTER);
        return panel;
    }

    // ==================== FORMULARIOS DE INGRESO ====================

    private void agregarCliente() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Cliente");
        agregarCamposPersona(formulario);
        formulario.agregarCampo("Tipo de cliente", "Nuevo", "Ocasional", "Frecuente");

        while (formulario.mostrar()) {
            if (registrarEntidad(() -> gestorEntidades.agregar(new Cliente(
                    formulario.getValor("Nombre"),
                    formulario.getValor("Apellido"),
                    new Rut(formulario.getValor(CAMPO_RUT)),
                    leerDireccion(formulario),
                    formulario.getValor("Tipo de cliente"))))) {
                return;
            }
        }
    }

    private void agregarEmpleado() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Empleado");
        agregarCamposPersona(formulario);
        formulario.agregarCampo("Cargo");

        while (formulario.mostrar()) {
            if (registrarEntidad(() -> gestorEntidades.agregar(new Empleado(
                    formulario.getValor("Nombre"),
                    formulario.getValor("Apellido"),
                    new Rut(formulario.getValor(CAMPO_RUT)),
                    leerDireccion(formulario),
                    formulario.getValor("Cargo"))))) {
                return;
            }
        }
    }

    private void agregarGuia() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Guía Turístico");
        agregarCamposPersona(formulario);
        formulario.agregarCampo("Idioma principal");
        formulario.agregarCampo("Años de experiencia");

        while (formulario.mostrar()) {
            if (registrarEntidad(() -> gestorEntidades.agregar(new GuiaTuristico(
                    formulario.getValor("Nombre"),
                    formulario.getValor("Apellido"),
                    new Rut(formulario.getValor(CAMPO_RUT)),
                    leerDireccion(formulario),
                    formulario.getValor("Idioma principal"),
                    leerEntero(formulario.getValor("Años de experiencia"), "Años de experiencia"))))) {
                return;
            }
        }
    }

    private void agregarColaborador() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Colaborador Externo");
        agregarCamposPersona(formulario);
        formulario.agregarCampo("Empresa");
        formulario.agregarCampo("Tipo de servicio");

        while (formulario.mostrar()) {
            if (registrarEntidad(() -> gestorEntidades.agregar(new ColaboradorExterno(
                    formulario.getValor("Nombre"),
                    formulario.getValor("Apellido"),
                    new Rut(formulario.getValor(CAMPO_RUT)),
                    leerDireccion(formulario),
                    formulario.getValor("Empresa"),
                    formulario.getValor("Tipo de servicio"))))) {
                return;
            }
        }
    }

    private void agregarVehiculo() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Vehículo");
        formulario.agregarCampo("Patente");
        formulario.agregarCampo("Marca");
        formulario.agregarCampo("Modelo");
        formulario.agregarCampo("Capacidad (pasajeros)");

        while (formulario.mostrar()) {
            if (registrarEntidad(() -> gestorEntidades.agregar(new Vehiculo(
                    formulario.getValor("Patente"),
                    formulario.getValor("Marca"),
                    formulario.getValor("Modelo"),
                    leerEntero(formulario.getValor("Capacidad (pasajeros)"), "Capacidad"))))) {
                return;
            }
        }
    }

    private void agregarServicio() {
        FormularioDialog formulario = new FormularioDialog(this, "Nuevo Servicio Turístico");
        formulario.agregarCampo("Tipo",
                "Ruta Gastronómica", "Paseo Lacustre", "Excursión Cultural");
        formulario.agregarCampo("Nombre");
        formulario.agregarCampo("Destino");
        formulario.agregarCampo("Precio (CLP)");
        formulario.agregarCampo("Duración (horas)");
        formulario.agregarCampo("Detalle (paradas / embarcación / lugar histórico)");

        while (formulario.mostrar()) {
            boolean registrado = registrarEntidad(() -> {
                String nombre = formulario.getValor("Nombre");
                String destino = formulario.getValor("Destino");
                int precio = leerEntero(formulario.getValor("Precio (CLP)"), "Precio");
                int duracion = leerEntero(formulario.getValor("Duración (horas)"), "Duración");
                String detalle = formulario.getValor("Detalle (paradas / embarcación / lugar histórico)");

                ServicioTuristico servicio = switch (formulario.getValor("Tipo")) {
                    case "Ruta Gastronómica" -> new RutaGastronomica(
                            nombre, destino, precio, duracion,
                            leerEntero(detalle, "Número de paradas"));
                    case "Paseo Lacustre" -> new PaseoLacustre(nombre, destino, precio, duracion, detalle);
                    default -> new ExcursionCultural(nombre, destino, precio, duracion, detalle);
                };
                gestorServicios.agregar(servicio);
            });
            if (registrado) {
                return;
            }
        }
    }

    private void agregarCamposPersona(FormularioDialog formulario) {
        formulario.agregarCampo("Nombre");
        formulario.agregarCampo("Apellido");
        formulario.agregarCampo(CAMPO_RUT);
        formulario.agregarCampo("Calle");
        formulario.agregarCampo("Ciudad");
        formulario.agregarCampo("Región");
    }

    private Direccion leerDireccion(FormularioDialog formulario) {
        return new Direccion(
                formulario.getValor("Calle"),
                formulario.getValor("Ciudad"),
                formulario.getValor("Región"));
    }

    private int leerEntero(String texto, String campo) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " debe ser un número entero.");
        }
    }

    /**
     * Ejecuta una acción de registro capturando las validaciones del
     * modelo (RUT inválido, campos vacíos, duplicados) y mostrando el
     * error al usuario sin cerrar la aplicación.
     *
     * @return {@code true} si el registro fue exitoso, {@code false}
     *         si hubo un error de validación
     */
    private boolean registrarEntidad(AccionRegistro accion) {
        try {
            accion.ejecutar();
            boolean guardado = GuardadorDatos.guardarTodo(gestorEntidades, gestorServicios);
            refrescarTabla();
            if (guardado) {
                JOptionPane.showMessageDialog(this, "Registro realizado exitosamente.",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "El registro se agregó pero no se pudo guardar en el archivo .txt",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            return true;
        } catch (RutInvalidException e) {
            mostrarError("RUT inválido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
        return false;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Acción de registro que puede lanzar la excepción personalizada
     * {@link RutInvalidException}.
     */
    @FunctionalInterface
    private interface AccionRegistro {
        void ejecutar() throws RutInvalidException;
    }

    // ==================== ACTUALIZACIÓN DE LA TABLA ====================

    /**
     * Reconstruye la tabla recorriendo las colecciones polimórficas y
     * usando {@code instanceof} (switch con pattern matching) para
     * mostrar la categoría y el detalle específico de cada entidad.
     */
    private void refrescarTabla() {
        modeloTabla.setRowCount(0);

        for (Registrable entidad : gestorEntidades.getEntidades()) {
            switch (entidad) {
                case Cliente cliente -> agregarFilaPersona(cliente, "Cliente", cliente.getTipoCliente());
                case Empleado empleado -> agregarFilaPersona(empleado, "Empleado", empleado.getCargo());
                case GuiaTuristico guia -> agregarFilaPersona(guia, "Guía Turístico",
                        String.format("%s · %d años de experiencia", guia.getIdioma(), guia.getAniosExperiencia()));
                case ColaboradorExterno colaborador -> agregarFilaPersona(colaborador, "Colaborador Externo",
                        String.format("%s · %s", colaborador.getEmpresa(), colaborador.getTipoServicio()));
                case Vehiculo vehiculo -> modeloTabla.addRow(new Object[]{
                        "Vehículo",
                        vehiculo.getMarca() + " " + vehiculo.getModelo(),
                        vehiculo.getPatente(),
                        vehiculo.getCapacidad() + " pasajeros"});
                default -> modeloTabla.addRow(new Object[]{
                        "Otro", entidad.mostrarResumen(), "", ""});
            }
        }

        for (ServicioTuristico servicio : gestorServicios.getServicios()) {
            switch (servicio) {
                case RutaGastronomica ruta -> agregarFilaServicio(ruta,
                        "Ruta Gastronómica", ruta.getNumeroDeParadas() + " paradas");
                case PaseoLacustre paseo -> agregarFilaServicio(paseo,
                        "Paseo Lacustre", paseo.getTipoEmbarcacion());
                case ExcursionCultural excursion -> agregarFilaServicio(excursion,
                        "Excursión Cultural", excursion.getLugarHistorico());
                default -> agregarFilaServicio(servicio, "Servicio Turístico", "");
            }
        }

        barraEstado.setText(String.format(
                "%d personas registradas   ·   %d vehículos   ·   %d servicios turísticos",
                gestorEntidades.getPersonas().size(),
                gestorEntidades.getVehiculos().size(),
                gestorServicios.getServicios().size()));
    }

    private void agregarFilaPersona(Persona persona, String categoria, String detalle) {
        modeloTabla.addRow(new Object[]{
                categoria,
                persona.getNombre() + " " + persona.getApellido(),
                persona.getRut().getNumero() + " · " + persona.getDireccion().getCiudad(),
                detalle});
    }

    private void agregarFilaServicio(ServicioTuristico servicio, String categoria, String detalle) {
        modeloTabla.addRow(new Object[]{
                categoria,
                servicio.getNombre(),
                String.format("%s · $%,d CLP · %d hrs",
                        servicio.getDestino(), servicio.getPrecio(), servicio.getDuracionHoras()),
                detalle});
    }
}
