package Services;
import DataBase.ConexionBD;
import Model.AdminRol;
import Model.Alerta;
import Model.Roles;
import Model.UserRol;
import Querys.RolesQuery;
import Querys.UserQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceRoles extends Service{
    private Roles rol;

    public ServiceRoles(){}

    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }

    public void insertarUsuarioRol(String email, String nombreRol) throws SQLException{
        try{
            setConexion(ConexionBD.conexion());
            PreparedStatement ps = getConexion().prepareStatement( RolesQuery.asignarRol());
            ps.setInt(1, obtenerIdUser(email));
            ps.setInt(2, obtenerIdRol(nombreRol));
            ps.execute();
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
    }
    public int obtenerIdRol(String nombreRol){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(RolesQuery.obtenerIdRol()));
            getPs().setString(1, nombreRol);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){
                return getTupla().getInt(1);
            }
        }catch (SQLException e){ System.out.println("Id rol " + e.getMessage()); }
        return -1;
    }
    public boolean obtenerUsuarioYRol(String email){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.obtenerUsuarioRolPorEmail()));
            getPs().setString(1, email);


        }catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return false;
    }
    public void obtenerUsuario(String email) {
        if (existeObjeto("usuarios", "email", (String) email)) {
            getRol().setUser(obtenerUsuarioPorEmail(email));
        } else { Alerta.alertaEmailInexistente(); }
    }
    private UserRol rolUsuario(String rol){
        if (rol.equals("usuario")){
            return new UserRol();
        }
        return null;
    }
    private AdminRol rolAdministrador(String rol){
        if (rol.equals("admin")){
            return new AdminRol();
        }
        return null;
    }
    public Roles roles(String rol){
        if (rol.equals("usuario")){ return (UserRol)  rolUsuario(rol); }
        if (rol.equals("admin")){ return (AdminRol) rolAdministrador(rol); }
        return rolUsuario(rol);
    }
    public String obtenerRolUsuarioPorEmail(String email){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(RolesQuery.obtenerRolUsuarioPorEmail()));
            getPs().setString(1, email);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getString(1); }
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return null;
    }
}
