package Services;
import Model.Roles;
import com.administrativos.sistema.utilidades.Alerta;

public class ServiceRoles extends Service{
    private Roles rol;

    public ServiceRoles(){}

    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }


    public void obtenerUsuario(String email) {
        if (existeObjeto("usuarios", "email", (String) email)) {
            getRol().setUser(obtenerUsuarioPorEmail(email));
        } else {
            Alerta.alertaEmailInexistente();
        }
    }

}
