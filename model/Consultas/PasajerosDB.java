package model.Consultas;

import controller.Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Datos.Pasajeros;

public class PasajerosDB {

    Control control;

    public PasajerosDB() {

    }

    public void setControl(Control control) {
        this.control = control;
    }

    public String createPasajeros(Pasajeros pasajeros) {
        if (pasajeros == null) {
            return "El pasajero es nulo.";
        }

        if (buscarPasajero(pasajeros.getCedula())) {
            return "El pasajero ya existe en la base de datos.";
        }

        String query = "INSERT INTO pasajero (cedula, nombre_pasajero, apellido_pasajero, equipaje) VALUES (?, ?, ?, ?)";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, pasajeros.getCedula());
            pstmt.setString(2, pasajeros.getNombre_pasajero());
            pstmt.setString(3, pasajeros.getApellido_pasajero());
            pstmt.setString(4, pasajeros.getEquipaje());

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                return "Pasajero creado con éxito.";
            } else {
                return "No se pudo crear el pasajero.";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al crear un pasajero: " + e.getMessage());
            return "Error al crear un pasajero.";
        }
    }

    public Pasajeros consultPasajeros(int cedula) {
        String query = "SELECT * FROM pasajero WHERE cedula = ?";
        Pasajeros pasajero = null;

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, cedula);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int cedulaResultado = rs.getInt("cedula");
                    String nombre = rs.getString("nombre_pasajero");
                    String apellido = rs.getString("apellido_pasajero");
                    String equipaje = rs.getString("equipaje");

                    pasajero = new Pasajeros(cedulaResultado, nombre, apellido, equipaje);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al consultar un pasajero: " + e.getMessage());
        }

        return pasajero;
    }

    public String updatePasajeros(Pasajeros pasajeros) {
        if (pasajeros == null) {
            return "El pasajero es nulo.";
        }

        if (!buscarPasajero(pasajeros.getCedula())) {
            return "El pasajero no existe en la base de datos.";
        }

        String query = "UPDATE pasajero SET nombre_pasajero = ?, apellido_pasajero = ?, equipaje = ? WHERE cedula = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, pasajeros.getNombre_pasajero());
            pstmt.setString(2, pasajeros.getApellido_pasajero());
            pstmt.setString(3, pasajeros.getEquipaje());
            pstmt.setInt(4, pasajeros.getCedula());

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                return "Pasajero actualizado con éxito.";
            } else {
                return "No se pudo actualizar el pasajero.";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al actualizar un pasajero: " + e.getMessage());
            return "Error al actualizar un pasajero.";
        }
    }

    public boolean deletePasajeros(Pasajeros pasajeros) {
        if (pasajeros == null) {
            return false;
        }
        String query = "DELETE FROM pasajero WHERE cedula = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, pasajeros.getCedula());

            int rowCount = pstmt.executeUpdate();
            return rowCount > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al eliminar un pasajero: " + e.getMessage());
            return false;
        }
    }

    public boolean buscarPasajero(int cedula) {
        String query = "SELECT * FROM pasajero WHERE cedula = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, cedula);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Devuelve true si se encontró el pasajero, false si no
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al buscar un pasajero: " + e.getMessage());
            return false; // Devuelve false en caso de error
        }
    }

    public Connection getConexion() throws ClassNotFoundException {
        Connection con = null;
        try {
            //   Class.forName("com.mysqljdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aeropuerto", "root", "camiloedw");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

}
