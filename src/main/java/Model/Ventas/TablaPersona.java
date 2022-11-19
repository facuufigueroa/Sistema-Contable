package Model.Ventas;
public class TablaPersona {
    private Long dni;
    private String cuit;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String razonSocial;
    private String tipoPersona;
    private String estado;

    public TablaPersona(){}
    public TablaPersona(Long dni, String nombre, String apellido, String cuit, String direccion, String telefono
            , String email, String razonSocial, String  tipoPersona, String estado){
        setDni(dni);
        setNombre(nombre);
        setApellido(apellido);
        setCuit(cuit);
        setDireccion(direccion);
        setTelefono(telefono);
        setEmail(email);
        setRazonSocial(razonSocial);
        setTipoPersona(tipoPersona);
        setEstado(estado);
    }
    public TablaPersona(Persona persona){
        if (persona.getDni() == null){
            setDni(0L);
            setNombre("");
            setApellido("");
        }else{
            setDni(persona.getDni());
            setNombre(persona.getNombre());
            setApellido(persona.getApellido());
            setCuit(persona.getCuit());
            setDireccion(persona.getDireccion());
            setTelefono(persona.getTelefono());
            setEmail(persona.getEmail());
            setRazonSocial(persona.getRazonSocial());
        }
        setTipoPersona(persona.getTipoPersona());
        if (persona.isEstado()){ setEstado("Habilitado");
        }else { setEstado("Deshabilitado"); }
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
