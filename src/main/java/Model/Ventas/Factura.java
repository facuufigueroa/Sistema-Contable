package Model.Ventas;

import java.sql.Date;

public class Factura {

    private Date fecha_pago;
    private String numero;
    private boolean facturada;
    private double total_pagado;
    private Date fecha_emision;
    private String letra;
    private int id_venta;


    public Factura(Date fecha_pago, String numero, boolean facturada, double total_pagado, Date fecha_emision, String letra, int id_venta) {
        this.fecha_pago = fecha_pago;
        this.numero = numero;
        this.facturada = facturada;
        this.total_pagado = total_pagado;
        this.fecha_emision = fecha_emision;
        this.letra = letra;
        this.id_venta = id_venta;
    }


    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    public double getTotal_pagado() {
        return total_pagado;
    }

    public void setTotal_pagado(double total_pagado) {
        this.total_pagado = total_pagado;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
}


