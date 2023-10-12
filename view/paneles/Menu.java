package view.paneles;

import controller.Control;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Menu extends JPanel {

    public JButton botonPasajeros;
    public JButton botonPersonal; // Nuevo botón para Personal

    public Menu() {
        setLayout(new FlowLayout());
        botonPasajeros = new JButton("Pasajeros");
        botonPersonal = new JButton("Personal"); // Agregar el botón de Personal
        add(botonPasajeros);
        add(botonPersonal); // Agregar el botón de Personal al panel
    }
}
