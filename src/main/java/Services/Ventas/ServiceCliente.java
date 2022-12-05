package Services.Ventas;
import DataBase.ConexionBD;
import Model.Producto;
import Model.Ventas.AlertaVenta;
import Model.Ventas.Cliente;
import Model.Ventas.TablaPersona;
import Querys.QueryAsiento;
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

    public boolean existeDni(Long dni){
        setConnection(null);
        setPs(null);
        setResultSet(null);
        try {
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.existeDni()));
            getPs().setLong(1, dni);
            setResultSet(getPs().executeQuery());
            if (getResultSet().next()){ return getResultSet().getLong(1) == dni; }
        }catch (SQLException exception){ AlertaVenta.clienteNoRegistrado(); }
        return false;
    }
    public boolean existeCuit(String cuit){
        setConnection(null);
        setPs(null);
        setResultSet(null);
        try {
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.existeCuit()));
            getPs().setString(1, cuit);
            setResultSet(getPs().executeQuery());
            if (getResultSet().next()){ return getResultSet().getString(1).equals(cuit); }
        }catch (SQLException exception){ AlertaVenta.clienteNoRegistrado(); }
        return false;
    }

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
    public void modificarEstado(Cliente cliente){
        setConnection(null);
        setPs(null);
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.modificarEstado()));
            getPs().setBoolean(1, cliente.isEstado());
            getPs().setString(2, cliente.getCuit());
            getPs().executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
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

    public ArrayList<Cliente> listarClientesHabilitados() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.listarClientesHabilitados()));
            setResultSet(getPs().executeQuery());
            while(getResultSet().next()){
                Cliente cliente = new Cliente();
                cliente.setNombre(getResultSet().getString(2));
                cliente.setApellido(getResultSet().getString(3));
                cliente.setDni(getResultSet().getLong(4));
                cliente.setCuit(getResultSet().getString(5));
                cliente.setDireccion(getResultSet().getString(6));
                cliente.setTelefono(getResultSet().getString(7));
                cliente.setEmail(getResultSet().getString(8));
                cliente.setRazonSocial(getResultSet().getString(9));
                cliente.setEstado(getResultSet().getBoolean(10));
                cliente.setTipoPersona(getResultSet().getString(11));
                clientes.add(cliente);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return clientes;
    }

    public int obtenerIdCliente(String nombre){
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.obtenerIdCliente(nombre)));
            setResultSet(getPs().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public String obtenerCondicionIva(int id) {
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.obtenerCondicionIva()));
            getPs().setInt(1,id);
            setResultSet(getPs().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }

    public String obtenerNombreCliente(int idCliente) {
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(ClienteQuery.obtenerNombreCliente()));
            getPs().setInt(1,idCliente);
            setResultSet(getPs().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }

    public Connection getConnection() { return connection ;}
    public void setConnection(Connection connection) { this.connection = connection; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getResultSet() { return resultSet; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}
