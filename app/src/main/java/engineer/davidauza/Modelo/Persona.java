package engineer.davidauza.Modelo;

public class Persona {

    private String nombre;
    private String correo;
    private String telefono;
    private String id;
    private String cedula;
    private String tipoDeSangre;

    public Persona() {
    }

    public Persona(String nombre, String correo, String telefono, String id, String cedula, String tipoDeSangre) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.id = id;
        this.cedula = cedula;
        this.tipoDeSangre = tipoDeSangre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }
}
