package Model.Ventas;

public class Persona {
    private Long dni;
    private String cuit;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String razonSocial;
    private String tipoPersona;
    private boolean estado;

    public Persona(){}
    public Persona(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono){
        setDni(dni);
        setCuit(cuit);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setDireccion(direccion);
        setTelefono(telefono);

        //Persona Juridica
        setRazonSocial(null);
        setEstado(true);
    }
    public Persona(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, boolean estado){
        setDni(dni);
        setCuit(cuit);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setDireccion(direccion);
        setTelefono(telefono);

        //Persona Juridica
        setRazonSocial(null);
        setEstado(estado);
    }
    public Persona(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, String razonSocial, boolean estado){
        setDni(dni);
        setCuit(cuit);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setDireccion(direccion);
        setTelefono(telefono);

        //Persona Juridica
        setRazonSocial(razonSocial);
        setEstado(estado);
    }
    public Persona(String cuit, String razonSocial, String email, String direccion, String telefono){
        //Persona Fisica
        setDni(null);
        setNombre(null);
        setApellido(null);

        //Persona Juridica
        setCuit(cuit);
        setEmail(email);
        setRazonSocial(razonSocial);
        setDireccion(direccion);
        setTelefono(telefono);
        setEstado(true);
    }
    public Persona(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, String razonSocial, String tipoPersona , boolean estado){
        setDni(dni);
        setCuit(cuit);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setDireccion(direccion);
        setTelefono(telefono);
        setTipoPersona(tipoPersona);
        //Persona Juridica
        setRazonSocial(razonSocial);
        setEstado(estado);
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

    public boolean isEstado() {
        return estado;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
