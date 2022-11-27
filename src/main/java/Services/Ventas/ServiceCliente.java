package Services.Ventas;

import DataBase.ConexionBD;
import Model.Ventas.AlertaVenta;
import Model.Ventas.Cliente;
import Model.Ventas.TablaPersona;
import Querys.Ventas.ClienteQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceCliente {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    public Cliente getPersonaByEmail(String email){
        try{ //Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.getPersonaById()));
            getPs().setString(1, email);
            setResultSet(getPs().executeQuery());
            if (getResultSet().next()){
                return new Cliente(
                         getResultSet().getLong("dni")
                        ,getResultSet().getString("cuit")
                        ,getResultSet().getString("nombre")
                        ,getResultSet().getString("apellido")
                        ,getResultSet().getString("email")
                        ,getResultSet().getString("direccion")
                        ,getResultSet().getString("telefono")
                        //Ver el tipo de persona
                        //Si dni no es nulo, entonces es persona juridica
                );
            }
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return null;
    }
    public void modificarCliente(Cliente persona) {
        setConnection(null);
        setPs(null);
        try {
            setConnection(ConexionBD.conexion());
            if (persona.getDni() == null || persona.getDni().equals(0L)){
                setPs(getConnection().prepareStatement(ClienteQuery.modificarPersonaJuridica()));
                // 1|razon_social, 2|email, 3|direccion, 4|telefono, 5|cuit
                getPs().setString(1, persona.getRazonSocial());
                getPs().setString(2, persona.getEmail());
                getPs().setString(3, persona.getDireccion());
                getPs().setString(4, persona.getTelefono());
                getPs().setString(5, persona.getCuit());
                getPs().executeUpdate();
            }else{
                setPs(getConnection().prepareStatement(ClienteQuery.modificarPersonaFisica()));
                //1|nombre, 2|apellido, 3|email, 4|direccion, 5|telefono, 6|cuit
                getPs().setString(1, persona.getNombre());
                getPs().setString(2, persona.getApellido());
                getPs().setString(3, persona.getEmail());
                getPs().setString(4, persona.getDireccion());
                getPs().setString(5, persona.getTelefono());
                getPs().setString(6, persona.getCuit());
                getPs().executeUpdate();
            }
            AlertaVenta.clienteModificadoCorrectamente();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            AlertaVenta.clienteNoModificado();
        }
    }
    public boolean insertarPersona(Cliente cliente){
        //dni, cuit, nombre, apellido, email, direccion, telefono, razon_social, estado, id_tipo_persona
        try {
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.insertarPersona()));
            if (cliente.getDni() == null) {
                getPs().setNull(1,-5);
            }else {
                getPs().setLong(1, cliente.getDni());
            }
            getPs().setString(2, cliente.getCuit());
            getPs().setString(3, cliente.getNombre());
            getPs().setString(4, cliente.getApellido());
            getPs().setString(5, cliente.getEmail());
            getPs().setString(6, cliente.getDireccion());
            getPs().setString(7, cliente.getTelefono());
            getPs().setString(8, cliente.getRazonSocial());
            getPs().setBoolean(9, cliente.isEstado());
            getPs().setInt(10, cliente.tipoPersona());
            return getPs().executeUpdate() != 0;
        }catch (SQLException e){ System.out.println(e.getMessage());}
        return false;
    }
    public ArrayList<TablaPersona> listadoPersona(){
        ArrayList<TablaPersona> lista = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.getListadoPersonas()));
            setResultSet(getPs().executeQuery());
            while (getResultSet().next()) {
                //Long dni, String cuit, String nombre, String apellido, String email, String direccion
                // String telefono, String razonSocial, String tipoPersona , boolean estado
                Cliente cliente = new Cliente(
                                          getResultSet().getLong("dni")
                                        , getResultSet().getString("cuit")
                                        , getResultSet().getString("nombre")
                                        , getResultSet().getString("apellido")
                                        , getResultSet().getString("email")
                                        , getResultSet().getString("direccion")
                                        , getResultSet().getString("telefono")
                                        , getResultSet().getString("razon_social")
                                        , getResultSet().getString("tipo")
                                        , getResultSet().getBoolean("estado")
                );
                TablaPersona tablaPersona = new TablaPersona(cliente);
                lista.add(tablaPersona);
            }
            return lista;
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return lista;
    }

    public Connection getConnection() { return connection ;}
    public void setConnection(Connection connection) { this.connection = connection; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getResultSet() { return resultSet; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}
