package com.llanquihuetour.ui;

import com.llanquihuetour.data.GestorEntidades;
import com.llanquihuetour.exception.RutInvalidException;
import com.llanquihuetour.model.ColaboradorExterno;
import com.llanquihuetour.model.Direccion;
import com.llanquihuetour.model.GuiaTuristico;
import com.llanquihuetour.model.Persona;
import com.llanquihuetour.model.Registrable;
import com.llanquihuetour.model.Rut;
import com.llanquihuetour.model.ServicioTuristico;
import com.llanquihuetour.model.Vehiculo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * Interfaz gráfica simple que permite ingresar nuevas entidades
 * (guías turísticos, vehículos y colaboradores externos) y
 * visualizar el resumen de todas las entidades registradas
 * en {@link GestorEntidades}.
 *
 * @author Sara Rioseco
 * @version 1.0
 */
public class GestorEntidadesFrame extends JFrame {

    private final GestorEntidades gestorEntidades;
    private final JTextArea areaResumenes = new JTextArea();

    /**
     * Crea la ventana de gestión de entidades.
     *
     * @param gestorEntidades gestor sobre el cual se agregan y muestran las entidades
     */
    public GestorEntidadesFrame(GestorEntidades gestorEntidades) {
        super("Llanquihue Tour - Gestión de Entidades");
        this.gestorEntidades = gestorEntidades;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        areaResumenes.setEditable(false);

        JButton botonAgregarGuia = new JButton("Agregar Guía Turístico");
        JButton botonAgregarVehiculo = new JButton("Agregar Vehículo");
        JButton botonAgregarColaborador = new JButton("Agregar Colaborador Externo");
        JButton botonMostrar = new JButton("Mostrar Resúmenes");

        botonAgregarGuia.addActionListener(e -> agregarGuiaTuristico());
        botonAgregarVehiculo.addActionListener(e -> agregarVehiculo());
        botonAgregarColaborador.addActionListener(e -> agregarColaboradorExterno());
        botonMostrar.addActionListener(e -> mostrarResumenes());

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 5, 5));
        panelBotones.add(botonAgregarGuia);
        panelBotones.add(botonAgregarVehiculo);
        panelBotones.add(botonAgregarColaborador);
        panelBotones.add(botonMostrar);

        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(areaResumenes), BorderLayout.CENTER);
    }

    private void agregarGuiaTuristico() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del guía:");
            if (nombre == null) return;
            String apellido = JOptionPane.showInputDialog(this, "Apellido del guía:");
            String rutTexto = JOptionPane.showInputDialog(this, "RUT (formato 12345678-9):");
            String calle = JOptionPane.showInputDialog(this, "Calle:");
            String ciudad = JOptionPane.showInputDialog(this, "Ciudad:");
            String region = JOptionPane.showInputDialog(this, "Región:");
            String idioma = JOptionPane.showInputDialog(this, "Idioma principal:");
            String aniosTexto = JOptionPane.showInputDialog(this, "Años de experiencia:");

            GuiaTuristico guia = new GuiaTuristico(
                    nombre, apellido, new Rut(rutTexto),
                    new Direccion(calle, ciudad, region),
                    idioma, Integer.parseInt(aniosTexto));

            gestorEntidades.agregar(guia);
            mostrarResumenes();
            JOptionPane.showMessageDialog(this, "Guía agregado:\n" + guia.mostrarResumen());
        } catch (RutInvalidException ex) {
            JOptionPane.showMessageDialog(this, "RUT inválido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los años de experiencia deben ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarVehiculo() {
        try {
            String patente = JOptionPane.showInputDialog(this, "Patente:");
            if (patente == null) return;
            String marca = JOptionPane.showInputDialog(this, "Marca:");
            String modelo = JOptionPane.showInputDialog(this, "Modelo:");
            String capacidadTexto = JOptionPane.showInputDialog(this, "Capacidad (pasajeros):");

            Vehiculo vehiculo = new Vehiculo(patente, marca, modelo, Integer.parseInt(capacidadTexto));

            gestorEntidades.agregar(vehiculo);
            mostrarResumenes();
            JOptionPane.showMessageDialog(this, "Vehículo agregado:\n" + vehiculo.mostrarResumen());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarColaboradorExterno() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del colaborador:");
            if (nombre == null) return;
            String apellido = JOptionPane.showInputDialog(this, "Apellido del colaborador:");
            String rutTexto = JOptionPane.showInputDialog(this, "RUT (formato 12345678-9):");
            String calle = JOptionPane.showInputDialog(this, "Calle:");
            String ciudad = JOptionPane.showInputDialog(this, "Ciudad:");
            String region = JOptionPane.showInputDialog(this, "Región:");
            String empresa = JOptionPane.showInputDialog(this, "Empresa colaboradora:");
            String tipoServicio = JOptionPane.showInputDialog(this, "Tipo de servicio que presta:");

            ColaboradorExterno colaborador = new ColaboradorExterno(
                    nombre, apellido, new Rut(rutTexto),
                    new Direccion(calle, ciudad, region),
                    empresa, tipoServicio);

            gestorEntidades.agregar(colaborador);
            mostrarResumenes();
            JOptionPane.showMessageDialog(this, "Colaborador agregado:\n" + colaborador.mostrarResumen());
        } catch (RutInvalidException ex) {
            JOptionPane.showMessageDialog(this, "RUT inválido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResumenes() {
        if (gestorEntidades.getEntidades().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay entidades registradas todavía.");
            return;
        }

        StringBuilder texto = new StringBuilder();
        for (Registrable entidad : gestorEntidades.getEntidades()) {
            texto.append(entidad.mostrarResumen());

            if (entidad instanceof Vehiculo vehiculo) {
                texto.append(String.format(" [Vehículo -> capacidad: %d]", vehiculo.getCapacidad()));
            } else if (entidad instanceof GuiaTuristico guia) {
                texto.append(String.format(" [Guía -> idioma: %s]", guia.getIdioma()));
            } else if (entidad instanceof ColaboradorExterno colaborador) {
                texto.append(String.format(" [Colaborador -> empresa: %s]", colaborador.getEmpresa()));
            } else if (entidad instanceof Persona) {
                texto.append(" [Persona registrada]");
            } else if (entidad instanceof ServicioTuristico servicio) {
                texto.append(String.format(" [Servicio -> destino: %s]", servicio.getDestino()));
            }

            texto.append(System.lineSeparator());
        }

        areaResumenes.setText(texto.toString());
    }
}
