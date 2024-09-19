package org.uv.tpcsw_practica01.inyeccion;

public class MensajeController {
    public void mostrar(IMensajeID mensaje){
        mensaje.imprimir();
    }
}
