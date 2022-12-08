package Services.Ventas;


import DataBase.ConexionBD;
import Model.Ventas.Factura;
import Querys.Ventas.FacturaQuery;

import java.sql.*;

public class ServiceFactura {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    private FacturaQuery facturaQuery;

    public void insertarFactura(Factura factura) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(facturaQuery.insertarFactura()));
            getPs().setDate(1, factura.getFecha_pago());
            getPs().setBoolean(2,factura.isFacturada());
            getPs().setDouble(3,factura.getTotal_pagado());
            getPs().setDate(4, factura.getFecha_emision());
            getPs().setString(5,factura.getLetra());
            getPs().setInt(6,factura.getId_venta());
            getPs().setString(7, factura.getNumero());
            getPs().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public int ultimaFactura() throws SQLException {
        int id_ultimaFactura=0;
        setConnection(ConexionBD.conexion());
        setPs(getConnection().prepareStatement(facturaQuery.ultimaFactura()));
        setResultSet(ps.executeQuery());
        if (resultSet.next()) {
            id_ultimaFactura = getResultSet().getInt(1);
        }
        return id_ultimaFactura;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
