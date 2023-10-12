package view;

import controller.Control;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Datos.Pasajeros;
import view.paneles.Menu;
import view.paneles.PasajerosInterfaz;
import view.paneles.PersonalInterfaz;

public class Ventana extends JFrame implements IVentana {

    Menu menu = new Menu();
    PasajerosInterfaz pasajeros = new PasajerosInterfaz();
    PersonalInterfaz personal = new PersonalInterfaz();
    Pasajeros datos = new Pasajeros();
    Control control;

    public Ventana() {
        setSize(500, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(menu);
    }

    @Override
    public void interfazPasajeros() {
        remove(menu);
        add(pasajeros);
        repaint();
        revalidate();
    }

    @Override
    public void setControl(Control control) {
        this.control = control;
    }

    @Override
    public void arrancar() {
        setVisible(true);
        revalidate();
    }

    @Override
    public void inicializar() {
        menu.botonPasajeros.setActionCommand(PASAJERO);
        menu.botonPasajeros.addActionListener(control);
        menu.botonPersonal.setActionCommand(PERSONAL); // Agregar el botón de Personal al menú
        menu.botonPersonal.addActionListener(control); // Agregar el listener para el botón de Personal

        pasajeros.btnAgregar.setActionCommand(CREARPASAJERO);
        pasajeros.btnAgregar.addActionListener(control);
        pasajeros.btnBuscar.setActionCommand(CONSULTARPASAJERO);
        pasajeros.btnBuscar.addActionListener(control);
        pasajeros.btnActualizar.setActionCommand(ACTUALIZARPASAJERO);
        pasajeros.btnActualizar.addActionListener(control);
        pasajeros.btnEliminar.setActionCommand(LIMPIAR);
        pasajeros.btnEliminar.addActionListener(control);

        // Ahora, los botones relacionados con PersonalInterfaz
        personal.btnAgregar.setActionCommand(CREARPERSONAL);
        personal.btnAgregar.addActionListener(control);
        personal.btnBuscar.setActionCommand(CONSULTARPERSONAL);
        personal.btnBuscar.addActionListener(control);
        personal.btnActualizar.setActionCommand(ACTUALIZARPERSONAL);
        personal.btnActualizar.addActionListener(control);
        personal.btnEliminar.setActionCommand(ELIMINARPERSONAL);
        personal.btnEliminar.addActionListener(control);
    }

    @Override
    public Pasajeros datosPasajero() {
        datos.setCedula(Integer.parseInt(pasajeros.cedulaField.getText()));
        datos.setNombre_pasajero(pasajeros.nombreField.getText());
        datos.setApellido_pasajero(pasajeros.apellidoField.getText());
        datos.setEquipaje(pasajeros.equipajeField.getText());
        return datos;
    }

    @Override
    public void mensajes(String exito) {
        JOptionPane.showMessageDialog(null, exito);
    }

    @Override
    public void consultaPasajeros(Pasajeros pasajero) {
        if (pasajero == null) {
            mensajes("no existe el pasajero");
        } else {
            pasajeros.cedulaField.setText(pasajero.getCedula() + "");
            pasajeros.nombreField.setText(pasajero.getNombre_pasajero());
            pasajeros.apellidoField.setText(pasajero.getApellido_pasajero());
            pasajeros.equipajeField.setText(pasajero.getEquipaje());
        }
    }

    @Override
    public int obtenerCedula() {
        return pasajeros.getCedula();
    }

    @Override
    public void limpiar() {
        pasajeros.cedulaField.setText("");
        pasajeros.nombreField.setText("");
        pasajeros.apellidoField.setText("");
        pasajeros.equipajeField.setText("");
    }

    @Override
    public void interfazPersonal() {
        remove(menu);
        add(personal);
        repaint();
        revalidate();
    }

}
