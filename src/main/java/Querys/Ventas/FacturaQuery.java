package Querys.Ventas;

public class FacturaQuery {

    public static String insertarFactura(){
        return "INSERT INTO facturas (fecha_pago,facturada,total_pagado,fecha_emision,letra_factura,id_venta, numero) VALUES (?,?,?,?,?,?,?)";
    }

    public static  String ultimaFactura(){
        return "select MAX(idfactura) as idfactura from facturas";
    }

}
