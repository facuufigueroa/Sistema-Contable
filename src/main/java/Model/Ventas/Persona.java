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
        setEstado(false);
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
        setEstado(false);
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
