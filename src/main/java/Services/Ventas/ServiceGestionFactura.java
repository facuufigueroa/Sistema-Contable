package Services.Ventas;
import DataBase.ConexionBD;
import Model.Ventas.TablaGestionFactura;
import Querys.Ventas.GestionFacturaQuery;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServiceGestionFactura {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    public ServiceGestionFactura(){}

    public void cerrarConexion(){
        setConnection(null);
        setPs(null);
        setResultSet(null);
    }

    public ArrayList<TablaGestionFactura> listarFacturas(boolean facturada){
        try{
            cerrarConexion();
            ArrayList<TablaGestionFactura> listado = new ArrayList<>();

            setConnection(ConexionBD.conexion());
            if (facturada){
                setPs(getConnection().prepareStatement(GestionFacturaQuery.listarFacturasCobradas()));
            }else{
                setPs(getConnection().prepareStatement(GestionFacturaQuery.listarFacturasNoCobradas()));
            }
            getPs().setBoolean(1, facturada);
            setResultSet(getPs().executeQuery());
            while (getResultSet().next()){
                TablaGestionFactura factura = crearFactura(getResultSet(), facturada);
                listado.add(factura);
            }
            return listado;
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return null;
    }


    /**Metodos privados**/
    private TablaGestionFactura crearFactura(ResultSet resultSet, boolean facturada) throws SQLException {
        String numero = getResultSet().getString("numero");
        String nombre = getResultSet().getString("nombre");
        String apellido = getResultSet().getString("apellido");
        String nombreCompleto = nombre + " " + apellido;
        Date fecha;
        if (facturada){
            fecha = getResultSet().getDate("fecha_pago");
        }else{
            fecha = getResultSet().getDate("fecha_emision");
        }
        double totalPagado = getResultSet().getDouble("total_pagado");
        int idFactura = getResultSet().getInt("idfactura");
        if (fecha != null){
            Format formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaAString = formato.format(fecha);
            if (apellido == null){
                return new TablaGestionFactura(numero, nombre, fechaAString, totalPagado, idFactura);
            }
            return new TablaGestionFactura(numero, nombreCompleto, fechaAString, totalPagado, idFactura);
        }
        return new TablaGestionFactura(numero, nombre, "Fecha nula", totalPagado, idFactura);
    }

    public Connection getConnection() { return connection; }
    public void setConnection(Connection connection) { this.connection = connection; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getResultSet() { return resultSet; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}