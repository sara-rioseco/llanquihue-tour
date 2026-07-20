package com.llanquihuetour.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Diálogo modal reutilizable que construye un formulario con campos
 * etiquetados. Cada ventana de ingreso del sistema (clientes, guías,
 * vehículos, servicios, etc.) se arma agregando campos a este
 * formulario, evitando duplicar código de interfaz.
 *
 * @author Sara Rioseco
 * @version 2.0
 */
public class FormularioDialog extends JDialog {

    private final Map<String, JComponent> campos = new LinkedHashMap<>();
    private final JPanel panelCampos = new JPanel(new GridBagLayout());
    private int fila = 0;
    private boolean aceptado = false;

    /**
     * Crea un nuevo formulario modal.
     *
     * @param propietario ventana principal sobre la que se centra el diálogo
     * @param titulo título del formulario
     */
    public FormularioDialog(JFrame propietario, String titulo) {
        super(propietario, titulo, true);
        setLayout(new BorderLayout());

        panelCampos.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        add(panelCampos, BorderLayout.CENTER);

        JButton botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");

        botonGuardar.addActionListener(e -> {
            aceptado = true;
            dispose();
        });
        botonCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));
        panelBotones.add(botonCancelar);
        panelBotones.add(botonGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(botonGuardar);
    }

    /**
     * Agrega un campo de texto al formulario.
     *
     * @param etiqueta etiqueta descriptiva del campo
     */
    public void agregarCampo(String etiqueta) {
        agregarComponente(etiqueta, new JTextField(20));
    }

    /**
     * Agrega un campo de selección (combo) al formulario.
     * Sobrecarga de {@link #agregarCampo(String)}.
     *
     * @param etiqueta etiqueta descriptiva del campo
     * @param opciones opciones disponibles para seleccionar
     */
    public void agregarCampo(String etiqueta, String... opciones) {
        agregarComponente(etiqueta, new JComboBox<>(opciones));
    }

    private void agregarComponente(String etiqueta, JComponent componente) {
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.insets = new Insets(4, 4, 4, 4);
        restricciones.anchor = GridBagConstraints.WEST;

        restricciones.gridx = 0;
        restricciones.gridy = fila;
        panelCampos.add(new JLabel(etiqueta + ":"), restricciones);

        restricciones.gridx = 1;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.weightx = 1.0;
        panelCampos.add(componente, restricciones);

        campos.put(etiqueta, componente);
        fila++;
    }

    /**
     * Retorna el valor ingresado o seleccionado en un campo.
     *
     * @param etiqueta etiqueta del campo
     * @return texto ingresado (sin espacios en los extremos)
     */
    public String getValor(String etiqueta) {
        JComponent componente = campos.get(etiqueta);
        if (componente instanceof JTextField campoTexto) {
            return campoTexto.getText().strip();
        }
        if (componente instanceof JComboBox<?> combo) {
            Object seleccion = combo.getSelectedItem();
            return seleccion == null ? "" : seleccion.toString();
        }
        return "";
    }

    /**
     * Muestra el formulario y espera a que el usuario lo cierre.
     *
     * @return {@code true} si el usuario presionó Guardar
     */
    public boolean mostrar() {
        aceptado = false;
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
        setVisible(true);
        return aceptado;
    }
}
