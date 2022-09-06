package Services;
import Model.Roles;
import Model.User;
import com.administrativos.sistema.utilidades.Alerta;

public class ServiceRoles extends Service{
    private Roles rol;

    public ServiceRoles(){
        //setUser(getRol().getUser());
    }

    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }

    /*
    public void obtenerUsuario(String email){
        if (existeObjeto("usuarios", "email", (String) email)){
            setUser(obtenerUsuarioPorEmail(email));
        }else{ Alerta.alertaEmailInexistente(); }
        System.out.print(getUser());
    }

     */
}
