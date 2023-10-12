package view.paneles;

import controller.Control;
import javax.swing.*;
import java.awt.*;

public class PasajerosInterfaz extends JPanel {

    public JTextField cedulaField;
    public JTextField nombreField;
    public JTextField apellidoField;
    public JTextField equipajeField;
    public JButton btnAgregar;
    public JButton btnBuscar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public PasajerosInterfaz() {
        setLayout(new GridLayout(5, 2));

        JLabel lblCedula = new JLabel("Cédula:");
        cedulaField = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        apellidoField = new JTextField();
        JLabel lblEquipaje = new JLabel("Equipaje:");
        equipajeField = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("LIMPIAR");

        add(lblCedula);
        add(cedulaField);
        add(lblNombre);
        add(nombreField);
        add(lblApellido);
        add(apellidoField);
        add(lblEquipaje);
        add(equipajeField);
        add(btnAgregar);
        add(btnBuscar);
        add(btnActualizar);
        add(btnEliminar);
    }

    public int getCedula() {
        try {
            return Integer.parseInt(cedulaField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public String getApellido() {
        return apellidoField.getText();
    }

    public String getEquipaje() {
        return equipajeField.getText();
    }

    public void limpiarCampos() {
        cedulaField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        equipajeField.setText("");
    }
}
