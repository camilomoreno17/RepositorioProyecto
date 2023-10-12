package runner;

import controller.Control;
import model.Consultas.PasajerosDB;
import view.Ventana;
import view.IVentana;
import view.paneles.Menu;

/**
 *
 * @author CAMILO MORENO
 */
public class Run {

    public static void main(String[] args) {
        IVentana ventana = new Ventana();
        PasajerosDB modelo = new PasajerosDB();
        
        Control control = new Control(ventana, modelo);
        ventana.setControl(control);
        modelo.setControl(control);
        control.init();
    }
    
}
