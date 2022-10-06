package Services;
import DataBase.ConexionBD;
import Model.Asiento;
import Querys.QueryAsiento;
import java.sql.SQLException;
public class ServiceAsientoCuenta extends Service{
    public ServiceAsientoCuenta(){ super(); }

    public void insertarAsiento(Asiento asiento){
        setConexion(null);
        setPs(null);
        setTupla(null);
        try {
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(QueryAsiento.insertarAsiento()));
            getPs().setString(1, asiento.getDetalle());
            getPs().setInt(2, asiento.getUsuario());
            getPs().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

}
