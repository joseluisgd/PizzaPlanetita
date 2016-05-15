package ulima.edu.pe.beans;

/**
 * Created by fixt on 10/05/16.
 */
public class Estado {
    private String fechahora;
    private int id;
    private String estado;

    public Estado() {
    }

    public Estado(String hora, int id, String estado) {
        this.fechahora = hora;
        this.id = id;
        this.estado = estado;
    }

    public String getHora() {
        return fechahora;
    }

    public void setHora(String hora) {
        this.fechahora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
