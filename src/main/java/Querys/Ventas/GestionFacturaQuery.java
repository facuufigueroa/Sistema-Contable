package Querys.Ventas;

public class GestionFacturaQuery {//numero, nombre, apellido, fecha_pago, total_pagado
    public static String listarFacturas(){ return
              "SELECT numero, c.nombre as nombre, c.apellido as apellido, fecha_pago, total_pagado "
            + "FROM facturas "
            + "INNER JOIN ventas AS v ON v.idventa = facturas.id_venta "
            + "INNER JOIN clientes AS c ON c.idcliente = v.id_cliente ";
    }


}
