package Model.Ventas;

public class Cliente {
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
    private String condicionIva;

    public Cliente(){}
    public Cliente(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono){
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
    public Cliente(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, String condicionIva){
        setDni(dni);
        setCuit(cuit);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setDireccion(direccion);
        setTelefono(telefono);
        setCondicionIva(condicionIva);

        //Persona Juridica
        setRazonSocial(null);
        setEstado(true);
    }
    public Cliente(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, boolean estado){
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
    public Cliente(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, String razonSocial, boolean estado){
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
    public Cliente(String cuit, String razonSocial, String email, String direccion, String telefono){
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
    } //
    public Cliente(String cuit, String nombre, String razonSocial, String email, String direccion, String telefono, String condicionIva){
        //Persona Fisica
        setDni(null);
        setApellido(null);

        //Persona Juridica
        setCuit(cuit);
        setNombre(nombre);
        setEmail(email);
        setRazonSocial(razonSocial);
        setDireccion(direccion);
        setTelefono(telefono);
        setEstado(true);
        setCondicionIva(condicionIva);
    }
    public Cliente(String cuit, String razonSocial, String email, String direccion, String telefono, String condicionIva){
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
        setCondicionIva(condicionIva);
    }
    public Cliente(Long dni, String cuit, String nombre, String apellido, String email, String direccion, String telefono, String razonSocial, String tipoPersona , boolean estado){
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

    @Override
    public String toString() {
        if (getDni() != null) {
            return "{ Nombre y apellido: "
                    + getNombre() + " " + getApellido()
                    + "\nDni: " + getDni()
                    + "\nCuit: " + getCuit()
                    + "\nEmail: " + getEmail()
                    + "\nDireccion: " + getDireccion()
                    + "\nTelefono: " + getTelefono()
                    + "\n}";
        }
        return "{ Razon social: "
                + getRazonSocial()
                + "\nCuit: " + getCuit()
                + "\nEmail: " + getEmail()
                + "\nDireccion: " + getDireccion()
                + "\nTelefono: " + getTelefono()
                + "\n}";
    }

    public int tipoPersona(){
        return (getDni() == null || getDni() == 0L) ? 2 : 1;
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

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }
}
