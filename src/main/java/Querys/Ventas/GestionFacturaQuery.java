package Querys.Ventas;

public class GestionFacturaQuery {//numero, nombre, apellido, fecha_pago, total_pagado
    public static String listarFacturasCobradas(){ return
              "SELECT numero, c.nombre as nombre, c.apellido as apellido, fecha_pago, total_pagado, idfactura "
            + "FROM facturas "
            + "INNER JOIN ventas AS v ON v.idventa = facturas.id_venta "
            + "INNER JOIN clientes AS c ON c.idcliente = v.id_cliente "
            + "WHERE facturada = ? ";
    }
    public static String listarFacturasNoCobradas(){ return
            "SELECT numero, c.nombre as nombre, c.apellido as apellido, fecha_emision, total_pagado, idfactura "
                    + "FROM facturas "
                    + "INNER JOIN ventas AS v ON v.idventa = facturas.id_venta "
                    + "INNER JOIN clientes AS c ON c.idcliente = v.id_cliente "
                    + "WHERE facturada = ? ";
    }


}
