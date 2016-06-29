package ulima.edu.pe.beans;

import java.util.List;

public class Cliente {

    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;
    private String edad;
    private List<Direccion> direcciones;
    private Usuario usuario;

    public Cliente() {
    }

//    public Cliente(int id, String telefono, String nombres, String apellidos, String dni, String edad, Usuario usuario) {
//        this.id = id;
//        this.telefono = telefono;
//        this.nombres = nombres;
//        this.apellidos = apellidos;
//        this.dni = dni;
//        this.edad = edad;
//        this.usuario = usuario;
//    }

    public Cliente(int id, String nombres, String apellidos, String telefono, String dni, String edad, List<Direccion> direcciones, Usuario usuario) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.edad = edad;
        this.direcciones = direcciones;
        this.usuario = usuario;
    }
//
    
    
    
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getNombres() {
//        return nombres;
//    }
//
//    public void setNombres(String nombres) {
//        this.nombres = nombres;
//    }
//
//    public String getApellidos() {
//        return apellidos;
//    }
//
//    public void setApellidos(String apellidos) {
//        this.apellidos = apellidos;
//    }
//
//    public String getDni() {
//        return dni;
//    }
//
//    public void setDni(String dni) {
//        this.dni = dni;
//    }
//
//    public String getEdad() {
//        return edad;
//    }
//
//    public void setEdad(String edad) {
//        this.edad = edad;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
