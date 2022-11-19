package Services.Ventas;

import DataBase.ConexionBD;
import Model.Ventas.Persona;
import Model.Ventas.TablaPersona;
import Querys.Ventas.PersonaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceCliente {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    public Persona getPersonaByEmail(String email){
        try{ //Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(PersonaQuery.getPersonaById()));
            getPs().setString(1, email);
            setResultSet(getPs().executeQuery());
            if (getResultSet().first()){
                return new Persona(
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

    public ArrayList<TablaPersona> listadoPersona(){
        ArrayList<TablaPersona> lista = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(PersonaQuery.getListadoPersonas()));
            setResultSet(getPs().executeQuery());
            while (getResultSet().next()) {
                //Long dni, String cuit, String nombre, String apellido, String email, String direccion
                // String telefono, String razonSocial, String tipoPersona , boolean estado
                Persona persona = new Persona(
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
                TablaPersona tablaPersona = new TablaPersona(persona);
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
