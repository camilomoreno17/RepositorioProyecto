package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Consultas.PasajerosDB;
import view.IVentana;

public class Control implements ActionListener{
    IVentana ventana;
    PasajerosDB pasajero;
    
    public Control(){
        
    }
    public Control(IVentana ventana, PasajerosDB pasajero){
        this.ventana = ventana;
        this.pasajero = pasajero;
    }
    public void init(){
        ventana.inicializar();
        ventana.arrancar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case IVentana.PASAJERO:
                ventana.interfazPasajeros();
                break;
            case IVentana.CREARPASAJERO:
                ventana.mensajes(pasajero.createPasajeros(ventana.datosPasajero()));
                break;
            case IVentana.CONSULTARPASAJERO:
                ventana.consultaPasajeros(pasajero.consultPasajeros(ventana.obtenerCedula()));
                break;
            case IVentana.ACTUALIZARPASAJERO:
                ventana.mensajes(pasajero.updatePasajeros(ventana.datosPasajero()));
                break;
            case IVentana.LIMPIAR:
                ventana.limpiar();
                break;
            case IVentana.PERSONAL:
                ventana.interfazPersonal();
                break;
            default:
                
        }
    }    
}