package ulima.edu.pe.beans;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;
    private String password;
    private String correo;
    private int puntos;

    public Usuario() {
    }

    public Usuario(String usuario, String password, String correo) {
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.puntos=0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
