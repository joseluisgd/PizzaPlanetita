package ulima.edu.pe.beans;

import java.io.Serializable;

public class Usuario implements Serializable {
    
    private String username;
    private String password;
    private String correo;
    private int puntos; //ChF: Si el usuario pertenece a un empleado, debería siempre tener 0 puntos

    public Usuario() {
    }

    public Usuario(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.puntos = 0;
    }

    public Usuario(String username, String password, String correo, int puntos) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.puntos = puntos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
