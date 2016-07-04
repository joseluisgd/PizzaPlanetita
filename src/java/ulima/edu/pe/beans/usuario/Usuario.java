package ulima.edu.pe.beans.usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
    public final static String TIPO_USUARIO_NOMBRE_ID1 = "Empleado";
    public final static String TIPO_USUARIO_NOMBRE_ID2 = "Administrador";
    public final static String TIPO_USUARIO_NOMBRE_ID3 = "Cliente";
    
    private String username;
    private String password;
    private String correo;
    private int tipo;
    private int puntos; //ChF: Si el usuario pertenece a un empleado, debería siempre tener 0 puntos

    public Usuario() {
    }

    public Usuario(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.puntos = 0;
    }

    public Usuario(String username, String password, String correo, int tipo, int puntos) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    private String getTipoNombre() {
        switch (tipo) {
            case 1:
                return TIPO_USUARIO_NOMBRE_ID1;
            case 2:
                return TIPO_USUARIO_NOMBRE_ID2;
            case 3:
                return TIPO_USUARIO_NOMBRE_ID3;
            default:
                return "";
        }
    }
    
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
