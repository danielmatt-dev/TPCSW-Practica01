package org.uv.tpcsw_practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uv.tpcsw_practica01.dao.ConexionDB;
import org.uv.tpcsw_practica01.dao.EmpleadoPojo;
import org.uv.tpcsw_practica01.dao.TransaccionDB;

public class TPCSW_Practica01 {

    private static final Logger logger = Logger.getLogger(TPCSW_Practica01.class.getName());
    
    public static void main(String[] args) {
        
       
        ConexionDB conexion = ConexionDB.getInstance();
        
        EmpleadoPojo pojo = new EmpleadoPojo();
        pojo.setClave("20");
        pojo.setNombre("Daniel");
        pojo.setTelefono("1234567890");
        pojo.setDireccion("Calle 11");
        
        TransaccionDB<EmpleadoPojo> transaction = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="insert into empleados (clave, nombre, direccion, telefono) values"
                            + " (?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    pst.setString(2, pojo.getNombre());
                    pst.setString(3, pojo.getDireccion());
                    pst.setString(4, pojo.getTelefono());
                    return pst.execute();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        conexion.execute(transaction);
        
        pojo.setClave("20"); 
        pojo.setNombre("Armando");
        pojo.setDireccion("Nueva Dirección");
        pojo.setTelefono("0987654321");
        
                TransaccionDB<EmpleadoPojo> transactionUpdate = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "update empleados set nombre = ?, direccion = ?, telefono = ? WHERE clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getNombre());
                    pst.setString(2, pojo.getDireccion());
                    pst.setString(3, pojo.getTelefono());
                    pst.setString(4, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        conexion.execute(transactionUpdate);
        
        pojo.setClave("20");

        TransaccionDB<EmpleadoPojo> transactionDelete = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "delete from empleados where clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        conexion.execute(transactionDelete);
        
        /*  
        
//        Mensaje msgV = null;
//        msgV = new Saludo();
//        msgV.imprimir();
//        
//        msgV = new Despedida();
//        msgV.imprimir();
// 
//        msgV = new Mensaje() {
//           @Override
//           protected void msg(){
//               System.out.println("Otro mensaje");
//           }
//       };
//        
//        msgV.imprimir();
        
//        MensajeConcreto msg = null;
//        msg = new MensajeConcreto("Daniel");
//        msg.imprimir();
//      
//        System.out.println("Hello World!");

//        IMensaje msgV = null;
//        msgV = new Saludo2();
//        msgV.imprimir();
//        
//        msgV = new Despedida2();
//        msgV.imprimir();
// 
//        msgV = () -> {
//            System.out.println("Otro mensaje...");
//        };
//
//        msgV.imprimir();
    */
//        Singleton s1 = Singleton.getInstance();
//        Singleton s2 = Singleton.getInstance();
//        
//        Logger.getLogger(TPCSW_Practica01.class.getName()).log(Level.INFO, null, "Adiossss");
//        
//        logger.log(Level.INFO, "Singleton Instance 1: {0}", s1);
//        logger.log(Level.INFO, "Singleton Instance 2: {0}", s2);
        
    }
}
