package ulima.edu.pe.beans;

public class Estado {
    private String fechaHora;
    private int id;
    private String estado;

    public Estado() {
    }

    public Estado(String fechaHora, int id, String estado) {
        this.fechaHora = fechaHora;
        this.id = id;
        this.estado = estado;
    }

    public String getFechahora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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
