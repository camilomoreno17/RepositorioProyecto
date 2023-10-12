import controller.Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Datos.Personal;

public class PersonalDB {

    Control control;

    public PersonalDB() {

    }

    public void setControl(Control control) {
        this.control = control;
    }

    public String createPersonal(Personal personal) {
        if (personal == null) {
            return "El personal es nulo.";
        }

        if (buscarPersonal(personal.getIdPersonal())) {
            return "El personal ya existe en la base de datos.";
        }

        String query = "INSERT INTO personal (idPersonal, nombre, apellido, cargo) VALUES (?, ?, ?, ?)";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, personal.getIdPersonal());
            pstmt.setString(2, personal.getNombre());
            pstmt.setString(3, personal.getApellido());
            pstmt.setString(4, personal.getCargo());

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                return "Personal creado con éxito.";
            } else {
                return "No se pudo crear el personal.";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al crear personal: " + e.getMessage());
            return "Error al crear personal.";
        }
    }

    public Personal consultPersonal(int idPersonal) {
        String query = "SELECT * FROM personal WHERE idPersonal = ?";
        Personal personal = null;

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idPersonal);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idPersonalResultado = rs.getInt("idPersonal");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String cargo = rs.getString("cargo");

                    personal = new Personal(idPersonalResultado, nombre, apellido, cargo);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al consultar personal: " + e.getMessage());
        }

        return personal;
    }

    public String updatePersonal(Personal personal) {
        if (personal == null) {
            return "El personal es nulo.";
        }

        if (!buscarPersonal(personal.getIdPersonal())) {
            return "El personal no existe en la base de datos.";
        }

        String query = "UPDATE personal SET nombre = ?, apellido = ?, cargo = ? WHERE idPersonal = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, personal.getNombre());
            pstmt.setString(2, personal.getApellido());
            pstmt.setString(3, personal.getCargo());
            pstmt.setInt(4, personal.getIdPersonal());

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                return "Personal actualizado con éxito.";
            } else {
                return "No se pudo actualizar el personal.";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al actualizar personal: " + e.getMessage());
            return "Error al actualizar personal.";
        }
    }

    public boolean deletePersonal(int idPersonal) {
        String query = "DELETE FROM personal WHERE idPersonal = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idPersonal);

            int rowCount = pstmt.executeUpdate();
            return rowCount > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al eliminar personal: " + e.getMessage());
            return false;
        }
    }

    public boolean buscarPersonal(int idPersonal) {
        String query = "SELECT * FROM personal WHERE idPersonal = ?";

        try (Connection con = getConexion(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idPersonal);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Devuelve true si se encontró el personal, false si no
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al buscar personal: " + e.getMessage());
            return false; // Devuelve false en caso de error
        }
    }

    public Connection getConexion() throws ClassNotFoundException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aeropuerto", "root", "camiloedw");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}
