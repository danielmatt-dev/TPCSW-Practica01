package org.uv.tpcsw_practica01.inyeccion;

public class DespedidaConcreto implements IMensajeID{
    
    @Override
    public void imprimir() {
        System.out.println("Adios mundo ID");
    }
    
}
