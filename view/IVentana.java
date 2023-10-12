package view;

import controller.Control;
import model.Datos.Pasajeros;

public interface IVentana {
    final String PASAJERO = "PASAJERO";
    final String CREARPASAJERO = "CRPASAJERO";
    final String CONSULTARPASAJERO = "CONSPASAJERO";
    final String ACTUALIZARPASAJERO = "ACTPASAJERO";
    final String BUSCARPASAJERO = "BUSPASAJERO";
    final String LIMPIAR = "LIMPIAR";
    final String PERSONAL = "PERSONAL";
    final String CREARPERSONAL = "CRPERSONAL";
    final String CONSULTARPERSONAL = "CONSPERSONAL";
    final String ACTUALIZARPERSONAL = "ACTPERSONAL";
    final String BUSCARPERSONAL = "BUSPERSONAL";
    final String ELIMINARPERSONAL = "ELIPERSONAL";
    final String LIMPIARPERSONAL = "LIMPIARPERSONAL";

    public void setControl(Control control);

    public void inicializar();

    public void interfazPasajeros();

    public void arrancar();

    public Pasajeros datosPasajero();

    public void mensajes(String exito);

    public void consultaPasajeros(Pasajeros pasajero);

    public void limpiar();

    public int obtenerCedula();
    
    public void interfazPersonal();

}
