package view.paneles;
import controller.Control;
import javax.swing.*;
import java.awt.*;

public class PersonalInterfaz extends JPanel {

    public JTextField idPersonalField;
    public JTextField nombreField;
    public JTextField apellidoField;
    public JTextField cargoField;
    public JButton btnAgregar;
    public JButton btnBuscar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public PersonalInterfaz() {
        setLayout(new GridLayout(5, 2));

        JLabel lblIdPersonal = new JLabel("ID Personal:");
        idPersonalField = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        apellidoField = new JTextField();
        JLabel lblCargo = new JLabel("Cargo:");
        cargoField = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("LIMPIAR");

        add(lblIdPersonal);
        add(idPersonalField);
        add(lblNombre);
        add(nombreField);
        add(lblApellido);
        add(apellidoField);
        add(lblCargo);
        add(cargoField);
        add(btnAgregar);
        add(btnBuscar);
        add(btnActualizar);
        add(btnEliminar);
    }

    public int getIdPersonal() {
        try {
            return Integer.parseInt(idPersonalField.getText());
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

    public String getCargo() {
        return cargoField.getText();
    }

    public void limpiarCampos() {
        idPersonalField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        cargoField.setText("");
    }
}
